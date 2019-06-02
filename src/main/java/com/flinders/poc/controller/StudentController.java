package com.flinders.poc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flinders.poc.common.controller.BasicController;
import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.constant.AppConstants;
import com.flinders.poc.dto.CriteriaDTO;
import com.flinders.poc.dto.UserDetailDTO;
import com.flinders.poc.service.UserService;
import com.flinders.poc.util.AppUtil;

/**
 * Student controller for user maintenance
 * @author mswahithali
 *
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BasicController{

	@Autowired(required = true)
	@Qualifier(value = "userService")
	private UserService userService;

	@InitBinder("student")
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(AppConstants.dateTimeFormat);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Override
	@ModelAttribute("student")
	public UserDetailDTO createNewModel() {
		return new UserDetailDTO();
	}
	
	@Override
	@ModelAttribute("criteria")
	public CriteriaDTO createNewCriteria() {
		return new CriteriaDTO();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initStudentSearchPage(Model model) throws AppException {	
		AppUtil.log_debug("StudentController.initStudentSearchPage", "Initialize student search page");
		createNewCriteria();
		return "studentSearch";
	}
	
	@RequestMapping(value = "/searchByCriteria", method = RequestMethod.POST)
	public String getStudentListByCriteria(Model model, @ModelAttribute("criteria") CriteriaDTO criteria) throws AppException {
		AppUtil.log_debug("StudentController.getUserListByCriteria", "Search student list by user Id=" + criteria.getUserId() + ", user name=" + criteria.getUserName());
		
		//model.addAttribute("userList", userService.searchUsersByIdAndName(criteria.getUserId(), criteria.getUserName()));
		model.addAttribute("criteria", criteria);
		return "studentSearch";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String viewUser(Model model, @RequestParam("id") String userId, @RequestParam("name") String userName) throws AppException {
		
		UserDetailDTO userDetail = new UserDetailDTO();

		// Retrieve user detail information
		List<UserDetailDTO> userList = null;//userService.searchUsersByIdAndName(userId, StringUtils.EMPTY);
		if (userList.size() > 0) {
			UserDetailDTO userInfo = userList.get(0);
			userDetail.setUserId(userInfo.getUserId());
			userDetail.setUserName(userInfo.getUserName());
		}
		
		userDetail.setUserId(userId);
		userDetail.setUserName(userName);
		
		List<UserDetailDTO> result = userService.findApplicationUser(StringUtils.EMPTY, StringUtils.EMPTY, userId);
		if (result.size() > 0) {
			UserDetailDTO existingUser = result.get(0);
		}
		
		model.addAttribute("user", userDetail);
		return "userMaint";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editUser(Model model, @ModelAttribute("user") UserDetailDTO userDetail) throws AppException {
		model.addAttribute("actionMsg", AppConstants.MSG_UPDATE_SUCCESS);
		model.addAttribute("user", userDetail);
		return "userMaint";
	}
	
}
