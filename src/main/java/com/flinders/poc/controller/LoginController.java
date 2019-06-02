package com.flinders.poc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.common.variable.AppDate;
import com.flinders.poc.constant.AppConstants;
import com.flinders.poc.dto.AdminLoginLogDTO;
import com.flinders.poc.dto.UserDetailDTO;
import com.flinders.poc.ldap.LoginBean;
import com.flinders.poc.service.AdminAuditLogService;
import com.flinders.poc.service.AppConfigService;
import com.flinders.poc.service.UserService;
import com.flinders.poc.util.AppUtil;
import com.flinders.poc.validator.LoginValidator;

import lombok.extern.log4j.Log4j;

/**
 * Login controller for authentication and authorization
 * 
 * @author mswahithali
 *
 */
@Log4j(topic = "flinderswebLogger")
@Controller
public class LoginController {

	@Autowired(required = true)
	@Qualifier(value = "userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("adminAuditLogService")
	private AdminAuditLogService adminAuditLogService;
	
	@Autowired
	@Qualifier("appConfigService")
	private AppConfigService appConfigService;
	
	@Autowired
	@Qualifier("loginValidator")
	private LoginValidator validator;

	@InitBinder("loginBean")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@ModelAttribute("loginBean")
	public LoginBean createNewModel() {
		return new LoginBean();
	}
	
	@RequestMapping(value = { "/", "index" })
	public String index(Model model) {
		return "redirect:/form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String getForm() {
		log.info("LoginController.getForm: redirecting to login");
		AppUtil.log_debug("LoginController.getForm", "redirecting to login");
		createNewModel();
		return "login";
	}

	
	@RequestMapping(value = "/auth/login", method = RequestMethod.POST)
	public String authenticateUser(Model model, HttpSession session, @Validated @ModelAttribute("loginBean") LoginBean loginBean,
			BindingResult result) throws AppException {
		
		if (result.hasErrors()) {
			return "login";
		}
		
		boolean successLogin = false;
		boolean successBULogin = false;
		boolean userBUFlag = false;
		boolean studentBUFlag = false;
		boolean reportBUFlag = false;
		boolean isValidUser = false;
		UserDetailDTO validUser = new UserDetailDTO();
		
		if (null != loginBean.getUserId() && null != loginBean.getPassword()) {
			AppUtil.log_debug("LoginController.authenticateUser", "Login - Authenticate user: userId=" + loginBean.getUserId());
			AppUtil.log_debug("LoginController.authenticateUser", "Login userId && Password is not empty");
		}
		
		AdminLoginLogDTO loginLog = new AdminLoginLogDTO();
		UserDetailDTO userLog = new UserDetailDTO();
		loginLog.setUserId(loginBean.getUserId());
		
		Base64 base64 = new Base64();
		
		loginLog.setAction(AppConstants.LOGIN_ACTION_LOGIN);
		loginLog.setLogDate(new AppDate(System.currentTimeMillis()));

		String errMsg = StringUtils.EMPTY;
		try {
			List<UserDetailDTO> userList = userService.getAllUsers();
			
			for (int i=0; i<userList.size(); i++) { 
				UserDetailDTO user = userList.get(i);
				if(user.getUserId().equalsIgnoreCase(loginBean.getUserId())) {
					validUser = user;
					isValidUser=true;
					loginBean.setAppId(user.getAppId());
					loginBean.setUsername(user.getUserName());
					loginBean.setUserId(user.getUserId());
					loginBean.setCreatedBy(user.getCreatedBy());
					loginBean.setCreationDate(user.getCreationDate());
					loginBean.setLastLoginStatus(user.getLastLoginStatus());
					loginBean.setLastLoginTime(user.getLastLoginTime());
				}
			}
			
			
			userLog.setAppId(loginBean.getAppId());
			userLog.setUserId(loginBean.getUserId());
			userLog.setUserName(loginBean.getUsername());
			userLog.setLastLoginTime(new AppDate(System.currentTimeMillis()));
			userLog.setModificationDate(new AppDate(System.currentTimeMillis()));
			userLog.setModifiedBy(loginBean.getUserId());
			
			if (isValidUser) {
				UserDetailDTO user = validUser;
				String decodedDBPass = new String(base64.decode(user.getPassword().getBytes()));
				AppUtil.log_debug("LoginController.authenticateUser", "login Bean Password: " + loginBean.getPassword());
				AppUtil.log_debug("LoginController.authenticateUser", "DB Password: " + decodedDBPass);
				if (loginBean.getPassword().equals(decodedDBPass)) {
					if (null != user.getGroupCode()) {
						if (user.getGroupCode().equalsIgnoreCase("G001")) {
							userBUFlag = true;
							studentBUFlag = true;
							reportBUFlag = true;
							successLogin = true;
						} else if (user.getGroupCode().equalsIgnoreCase("G002")) {
							studentBUFlag = true;
							successLogin = true;
						} else if (user.getGroupCode().equalsIgnoreCase("G003")) {
							reportBUFlag = true;
							successLogin = true;
						}
					} else {
						errMsg = AppConstants.MSG_LOGIN_FAIL_BU_UNAUTH_ID;
						AppUtil.log_debug("LoginController.authenticateUser", AppConstants.MSG_LOGIN_FAIL_BU_UNAUTH_ID);
					}
				} else if (!loginBean.getPassword().equals(decodedDBPass)){
					errMsg = AppConstants.MSG_LOGIN_FAIL_INCORRECT_ID;
					AppUtil.log_debug("LoginController.authenticateUser", AppConstants.MSG_LOGIN_FAIL_INCORRECT_ID);
				}
				} else {
					errMsg = AppConstants.MSG_LOGIN_FAIL_UNAUTH_ID;
					AppUtil.log_debug("LoginController.authenticateUser", AppConstants.MSG_LOGIN_FAIL_UNAUTH_ID);
				}	
		} catch (AppException e) {
				throw new AppException("LOGIN", "Error validating user", e);
		}
		
		if (successLogin) {
			session.setAttribute(AppConstants.LOGGED_IN_USER, loginBean);
			
			if (userBUFlag){
				loginBean.setUserBUFlag(true);
				successBULogin = true;
			} else {
				loginBean.setUserBUFlag(false);
			}
			
			if (studentBUFlag){
				loginBean.setStudentBUFlag(true);
				successBULogin = true;
			} else {
				loginBean.setStudentBUFlag(false);
			}
			
			if (reportBUFlag) {
				loginBean.setReportBUFlag(true);
				successBULogin = true;
			} else {
				loginBean.setReportBUFlag(false);
			}
			
			AppUtil.log_debug("LoginController.authenticateUser", "userBUFlag=" +userBUFlag);
			AppUtil.log_debug("LoginController.authenticateUser", "studentBUFlag=" +studentBUFlag);
			AppUtil.log_debug("LoginController.authenticateUser", "reportBUFlag=" + reportBUFlag);
			AppUtil.log_debug("LoginController.authenticateUser", "isUserBUFlag=" + loginBean.isUserBUFlag());
			AppUtil.log_debug("LoginController.authenticateUser", "isUserBUFlag=" + loginBean.isStudentBUFlag());
			AppUtil.log_debug("LoginController.authenticateUser", "isReportBUFlag=" + loginBean.isReportBUFlag());
			
		}
		
		if (successLogin && successBULogin && userBUFlag) {
			AppUtil.log_debug("LoginController.authenticateUser", "successLogin=" + successLogin);
			AppUtil.log_debug("LoginController.authenticateUser", "successBULogin=" + successBULogin);
			AppUtil.log_debug("LoginController.authenticateUser", "Login - Flindersweb page login success with userBUFlag role");
			loginLog.setStatus(AppConstants.LOGIN_STATUS_SUCCESS);
			userLog.setLastLoginStatus(AppConstants.LOGIN_STATUS_SUCCESS);
			adminAuditLogService.saveLoginLog(loginLog);
			userService.updateLoginLog(userLog);
			return "redirect:/user";
		} else if (successLogin && successBULogin && studentBUFlag) {
			AppUtil.log_debug("LoginController.authenticateUser", "successLogin=" + successLogin);
			AppUtil.log_debug("LoginController.authenticateUser", "successBULogin=" + successBULogin);
			AppUtil.log_debug("LoginController.authenticateUser", "Login - Flindersweb page login success with userBUFlag role");
			loginLog.setStatus(AppConstants.LOGIN_STATUS_SUCCESS);
			userLog.setLastLoginStatus(AppConstants.LOGIN_STATUS_SUCCESS);
			adminAuditLogService.saveLoginLog(loginLog);
			userService.updateLoginLog(userLog);
			return "redirect:/student";
		} else if (successLogin && successBULogin && reportBUFlag) {
			AppUtil.log_debug("LoginController.authenticateUser", "successLogin=" + successLogin);
			AppUtil.log_debug("LoginController.authenticateUser", "successBULogin=" + successBULogin);
			AppUtil.log_debug("LoginController.authenticateUser", "Login - Flindersweb page login success with userBUFlag role");
			loginLog.setStatus(AppConstants.LOGIN_STATUS_SUCCESS);
			userLog.setLastLoginStatus(AppConstants.LOGIN_STATUS_SUCCESS);
			adminAuditLogService.saveLoginLog(loginLog);
			userService.updateLoginLog(userLog);
			return "redirect:/report";
		} else if (!successBULogin) {
			AppUtil.log_debug("LoginController.authenticateUser", "successBULogin=" + successBULogin);
			AppUtil.log_debug("LoginController.authenticateUser", "Login - Flindersweb page login fail");
			loginLog.setStatus(AppConstants.LOGIN_STATUS_FAIL);
			userLog.setLastLoginStatus(AppConstants.LOGIN_STATUS_FAIL);
			loginLog.setFailReason(errMsg);
			adminAuditLogService.saveLoginLog(loginLog);
			userService.updateLoginLog(userLog);
			model.addAttribute(AppConstants.AUTH_ERROR_MSG, errMsg);
			model.addAttribute("loginBean", loginBean);
			AppUtil.log_debug("LoginController.authenticateUser", "User Authentication/Authorization Failed");
			return "login";
		} else {
			AppUtil.log_debug("LoginController.authenticateUser", "Login - Flindersweb page login fail");
			loginLog.setStatus(AppConstants.LOGIN_STATUS_FAIL);
			userLog.setLastLoginStatus(AppConstants.LOGIN_STATUS_FAIL);
			loginLog.setFailReason(errMsg);
			adminAuditLogService.saveLoginLog(loginLog);
			userService.updateLoginLog(userLog);
			model.addAttribute(AppConstants.AUTH_ERROR_MSG, errMsg);
			model.addAttribute("loginBean", loginBean);
			AppUtil.log_debug("LoginController.authenticateUser", "User Authentication/Authorization Failed. BU not assigned.");
			return "login";
		}
	}

	@RequestMapping(value = "/auth/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) throws AppException {
		AppUtil.log_debug("LoginController.logout", ((LoginBean)session.getAttribute(AppConstants.LOGGED_IN_USER)).getUserId());
		
		AdminLoginLogDTO loginLog = new AdminLoginLogDTO();
		loginLog.setUserId(((LoginBean)session.getAttribute(AppConstants.LOGGED_IN_USER)).getUserId());
		loginLog.setAction(AppConstants.LOGIN_ACTION_LOGOUT);
		loginLog.setStatus(AppConstants.LOGOUT_STATUS_SUCCESS);
		loginLog.setLogDate(new AppDate(System.currentTimeMillis()));
		adminAuditLogService.saveLoginLog(loginLog);
		session.invalidate();
		return "redirect:/form";
	}

}
