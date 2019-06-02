package com.flinders.poc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.flinders.poc.common.controller.BasicController;
import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.constant.AppConstants;
import com.flinders.poc.dto.AppConfigDTO;
import com.flinders.poc.dto.CriteriaDTO;
import com.flinders.poc.dto.GroupDTO;
import com.flinders.poc.dto.UserDetailDTO;
import com.flinders.poc.service.AppConfigService;
import com.flinders.poc.service.GroupService;
import com.flinders.poc.service.UserService;
import com.flinders.poc.util.AppUtil;

import lombok.extern.log4j.Log4j;

/**
 * User controller for user maintenance
 * @author mswahithali
 *
 */

@Log4j(topic = "flinderswebLogger")
@Controller
@SessionAttributes(AppConstants.LOGGED_IN_USER)
@RequestMapping("/user")
public class UserController extends BasicController{
	
	Base64 base64 = new Base64();
	
	@Autowired(required = true)
	@Qualifier(value = "userService")
	private UserService userService;
	
	@Autowired(required = true)
	@Qualifier(value = "groupService")
	private GroupService groupService;
	
	@Autowired(required = true)
	@Qualifier(value = "appConfigService")
	private AppConfigService appConfigService;

	@InitBinder("user")
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(AppConstants.dateTimeFormat);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Override
	@ModelAttribute("user")
	public UserDetailDTO createNewModel() {
		return new UserDetailDTO();
	}
	
	@Override
	@ModelAttribute("criteria")
	public CriteriaDTO createNewCriteria() {
		return new CriteriaDTO();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initUserSearchPage(Model model) throws AppException {	
		log.info("User - Initialize user search page");
		createNewCriteria();
		return "userSearch";
	}
	
	@RequestMapping(value = "/searchByCriteria", method = RequestMethod.POST)
	public String getUserListByCriteria(Model model, @ModelAttribute("criteria") CriteriaDTO criteria) throws AppException {
		log.info("User - Search user list by user Id=" + criteria.getUserId().toUpperCase() + ", user name=" + criteria.getUserName().toUpperCase());
		model.addAttribute("userList", userService.searchUsersByIdAndName(AppConstants.APP_CODE, criteria.getUserId().toUpperCase(), criteria.getUserName().toUpperCase()));
		model.addAttribute("criteria", criteria);
		return "userSearch";
	}
	
	/*@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String viewUser(Model model, @RequestParam("id") String userId, @RequestParam("name") String userName) throws AppException {
		log.info("User - View user detail of " + userId);
		Boolean isEditable = false;
		String decodedDBPass = null;
		String fullGroupDesc = null;
		List<String> groupListStr = new ArrayList<String>();
		List<String> appListStr = new ArrayList<String>();
		UserDetailDTO userDetail = new UserDetailDTO();
		Base64 base64 = new Base64();
		List<UserDetailDTO> userList = userService.searchUsersByIdAndName(AppConstants.APP_CODE, userId, StringUtils.EMPTY);
		List<GroupDTO> groupList = groupService.getAllGroups();
		List<AppConfigDTO> appList = appConfigService.getAllApps();
		if (userList.size() > 0) {
			UserDetailDTO userInfo = userList.get(0);
			for (int i = 0; i < groupList.size(); i++) {
				GroupDTO groupInfo = groupList.get(i);
				groupListStr.add(groupInfo.getGroupCode());
				if (userInfo.getGroupCode().equals(groupInfo.getGroupCode())) {
					fullGroupDesc = groupInfo.getGroupCode().concat("-").concat(groupInfo.getGroupId());
				}
			}
			for (int i = 0; i < appList.size(); i++) {
				AppConfigDTO appInfo = appList.get(i);
				appListStr.add(appInfo.getAppId().concat("-").concat(appInfo.getAppName()));
			}
			decodedDBPass = new String(base64.decode(userInfo.getPassword().getBytes()));
			userDetail.setUserId(userInfo.getUserId());
			userDetail.setUserName(userInfo.getUserName());
			userDetail.setEmailAddress(userInfo.getEmailAddress());
			userDetail.setPassword(decodedDBPass);
			userDetail.setAppId(userInfo.getAppId());
			userDetail.setGroupCode(userInfo.getGroupCode());
			userDetail.setCreatedBy(userInfo.getCreatedBy());
			userDetail.setCreationDate(userInfo.getCreationDate());
			userDetail.setLastLoginStatus(userInfo.getLastLoginStatus());
			userDetail.setLastLoginTime(userInfo.getLastLoginTime());
			userDetail.setFullGroupDesc(fullGroupDesc);
			userDetail.setGroupList(groupListStr);
			userDetail.setAppList(appListStr);
			userDetail.setEditable(isEditable);
		}
		model.addAttribute("user", userDetail);
		return "userMaint";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editUser(Model model, @ModelAttribute("user") UserDetailDTO userDetail) throws AppException {	
		log.info("User - Update user informations");
		Boolean isEditable = false;
		Boolean isProcess = false;
		if (userDetail.isAdd() == new Boolean(null)) {
			userDetail.setAdd(false);
		} else {
			userDetail.setAdd(true);
		}
		String decodedDBPass = null;
		String encodedDBPass = null;
		String fullGroupDesc = null;
		List<String> groupListStr = new ArrayList<String>();
		List<String> appListStr = new ArrayList<String>();
		Base64 base64 = new Base64();
		userDetail.setAppId(AppUtil.INSTANCE.getLoginBean().getAppId());
		encodedDBPass = new String(base64.encode(userDetail.getPassword().getBytes()));
		UserDetailDTO updatedUserDetail = new UserDetailDTO();
		
		
		if (!userDetail.isAdd()) {
			isProcess = userService.updateApplicationUser(userDetail);
		} else {
			isProcess = userService.addApplicationUser(userDetail);
		}
		
		if (isProcess) {
			List<UserDetailDTO> userList = userService.searchUsersByIdAndName(AppConstants.APP_CODE, userDetail.getUserId(), StringUtils.EMPTY);
			List<GroupDTO> groupList = groupService.getAllGroups();
			List<AppConfigDTO> appList = appConfigService.getAllApps();
			if (userList.size() > 0) {
				UserDetailDTO userInfo = userList.get(0);
				for (int i = 0; i < groupList.size(); i++) {
					GroupDTO groupInfo = groupList.get(i);
					groupListStr.add(groupInfo.getGroupCode());
					if (userInfo.getGroupCode().equals(groupInfo.getGroupCode())) {
						fullGroupDesc = groupInfo.getGroupCode().concat("-").concat(groupInfo.getGroupId());
					}
				}
				for (int i = 0; i < appList.size(); i++) {
					AppConfigDTO appInfo = appList.get(i);
					appListStr.add(appInfo.getAppId().concat("-").concat(appInfo.getAppName()));
				}
				decodedDBPass = new String(base64.decode(encodedDBPass.getBytes()));
				updatedUserDetail.setUserId(userInfo.getUserId());
				updatedUserDetail.setUserName(userInfo.getUserName());
				updatedUserDetail.setEmailAddress(userInfo.getEmailAddress());
				updatedUserDetail.setPassword(decodedDBPass);
				updatedUserDetail.setAppId(userInfo.getAppId());
				updatedUserDetail.setGroupCode(userInfo.getGroupCode());
				updatedUserDetail.setCreatedBy(userInfo.getCreatedBy());
				updatedUserDetail.setCreationDate(userInfo.getCreationDate());
				updatedUserDetail.setLastLoginStatus(userInfo.getLastLoginStatus());
				updatedUserDetail.setLastLoginTime(userInfo.getLastLoginTime());
				updatedUserDetail.setFullGroupDesc(fullGroupDesc);
				updatedUserDetail.setGroupList(groupListStr);
				updatedUserDetail.setAppList(appListStr);
				updatedUserDetail.setEditable(isEditable);
			}
			model.addAttribute("actionMsg", AppConstants.MSG_UPDATE_SUCCESS);
		} else {
			model.addAttribute("actionMsg", AppConstants.MSG_UPDATE_FAIL);
		}
		AppUtil.INSTANCE.getLoginBean().setGroupCode(updatedUserDetail.getGroupCode());
		model.addAttribute("user", updatedUserDetail);
		//model.addAttribute("loginBean", AppUtil.INSTANCE.getLoginBean());
		return "userMaint";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String addUser(Model model, @ModelAttribute("user") UserDetailDTO userDetail) throws AppException {	
		log.info("User - Add user informations");
		Boolean isEditable = true;
		Boolean isAdd = true;
		String fullGroupDesc = null;
		List<GroupDTO> groupList = groupService.getAllGroups();
		List<AppConfigDTO> appList = appConfigService.getAllApps();
		List<String> appListStr = new ArrayList<String>();
		List<String> groupListStr = new ArrayList<String>();
		for (int i = 0; i < groupList.size(); i++) {
			GroupDTO groupInfo = groupList.get(i);
			groupListStr.add(groupInfo.getGroupCode());
			if (i == 0) {
				fullGroupDesc = groupInfo.getGroupCode().concat("-").concat(groupInfo.getGroupId());
			}
		}
		for (int i = 0; i < appList.size(); i++) {
			AppConfigDTO appInfo = appList.get(i);
			appListStr.add(appInfo.getAppId().concat("-").concat(appInfo.getAppName()));
		}
		UserDetailDTO updatedUserDetail = new UserDetailDTO();
		updatedUserDetail.setEditable(isEditable);
		updatedUserDetail.setGroupList(groupListStr);
		updatedUserDetail.setAppList(appListStr);
		updatedUserDetail.setFullGroupDesc(fullGroupDesc);
		updatedUserDetail.setAppId(AppUtil.INSTANCE.getLoginBean().getAppId());
		updatedUserDetail.setAdd(isAdd);
		model.addAttribute("user", updatedUserDetail);
		return "userMaint";
	}*/
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String viewUser(Model model, @RequestParam("id") String userId, @RequestParam("name") String userName) throws AppException {
		log.info("User - View user detail of " + userId);
		Boolean isEditable = false;
		Boolean isAdd = false;
		String decodedDBPass = null;
		String fullGroupDesc = null;
		List<String> groupListStr = new ArrayList<String>();
		List<String> appListStr = new ArrayList<String>();
		UserDetailDTO userDetail = new UserDetailDTO();
		List<UserDetailDTO> userList = userService.searchUsersByIdAndName(AppConstants.APP_CODE, userId.toUpperCase(), StringUtils.EMPTY);
		List<GroupDTO> groupList = groupService.getAllGroups();
		List<AppConfigDTO> appList = appConfigService.getAllApps();
		if (userList.size() > 0) {
			UserDetailDTO userInfo = userList.get(0);
			groupListStr = getGroupListStr(groupList);
			fullGroupDesc = getFullGroupDesc(userList, groupList);
			appListStr = getAppListStr(appList);
			decodedDBPass = AppUtil.INSTANCE.getDecodedPassword(userInfo.getPassword());
			userDetail = getUpdatedUserDetailEdit(userInfo, fullGroupDesc, groupListStr, appListStr, isEditable, decodedDBPass, isAdd);
		}
		model.addAttribute("user", userDetail);
		return "userMaint";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editUser(Model model, @ModelAttribute("user") UserDetailDTO userDetail) throws AppException {	
		log.info("User - Update user informations");
		Boolean isEditable = false;
		Boolean isAdd = false;
		Boolean isProcess = false;
		String decodedDBPass = null;
		String fullGroupDesc = null;
		List<String> groupListStr = new ArrayList<String>();
		List<String> appListStr = new ArrayList<String>();
		if (userDetail.isAdd() == false) {
			userDetail.setAdd(false);
		} else {
			userDetail.setAdd(true);
		}		
		userDetail.setAppId(AppUtil.INSTANCE.getLoginBean().getAppId());
		userDetail.setPassword(AppUtil.INSTANCE.getEncodedPassword(userDetail.getPassword()));
		UserDetailDTO updatedUserDetail = new UserDetailDTO();
		if (!userDetail.isAdd()) {
			isProcess = userService.updateApplicationUser(userDetail);
		} else {
			isProcess = userService.addApplicationUser(userDetail);
		}
		
		if (isProcess) {
			List<UserDetailDTO> userList = userService.searchUsersByIdAndName(AppConstants.APP_CODE, userDetail.getUserId(), StringUtils.EMPTY);
			List<GroupDTO> groupList = groupService.getAllGroups();
			List<AppConfigDTO> appList = appConfigService.getAllApps();
			if (userList.size() > 0) {
				UserDetailDTO userInfo = userList.get(0);
				groupListStr = getGroupListStr(groupList);
				fullGroupDesc = getFullGroupDesc(userList, groupList);
				appListStr = getAppListStr(appList);
				decodedDBPass = AppUtil.INSTANCE.getDecodedPassword(userInfo.getPassword());
				updatedUserDetail = getUpdatedUserDetailEdit(userInfo, fullGroupDesc, groupListStr, appListStr, isEditable, decodedDBPass, isAdd);
				AppUtil.INSTANCE.getLoginBean().setGroupCode(updatedUserDetail.getGroupCode());
			}
			model.addAttribute("actionMsg", AppConstants.MSG_UPDATE_SUCCESS);
		} else {
			model.addAttribute("actionMsg", AppConstants.MSG_UPDATE_FAIL);
		}
		model.addAttribute("user", updatedUserDetail);
		return "userMaint";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String addUser(Model model, @ModelAttribute("user") UserDetailDTO userDetail) throws AppException {	
		log.info("User - Add user informations");
		Boolean isEditable = true;
		Boolean isAdd = true;
		String fullGroupDesc = null;
		List<GroupDTO> groupList = groupService.getAllGroups();
		List<AppConfigDTO> appList = appConfigService.getAllApps();
		List<String> appListStr = new ArrayList<String>();
		List<String> groupListStr = new ArrayList<String>();
		groupListStr = getGroupListStr(groupList);
		fullGroupDesc = getInitFullGroupDesc(groupList);
		appListStr = getAppListStr(appList);
		UserDetailDTO updatedUserDetail = new UserDetailDTO();
		updatedUserDetail = getUpdatedUserDetailAdd (fullGroupDesc, groupListStr, appListStr, isEditable, isAdd);
		model.addAttribute("user", updatedUserDetail);
		return "userMaint";
	}
	
	public UserDetailDTO getUpdatedUserDetailAdd (String fullGroupDesc, 
			List<String> groupListStr,  List<String> appListStr, boolean isEditable, boolean isAdd) {
		UserDetailDTO updatedUserDetail = new UserDetailDTO();
		updatedUserDetail.setEditable(isEditable);
		updatedUserDetail.setGroupList(groupListStr);
		updatedUserDetail.setAppList(appListStr);
		updatedUserDetail.setFullGroupDesc(fullGroupDesc);
		updatedUserDetail.setAppId(AppUtil.INSTANCE.getLoginBean().getAppId());
		updatedUserDetail.setAdd(isAdd);
		return updatedUserDetail;
	}

	public UserDetailDTO getUpdatedUserDetailEdit (UserDetailDTO userInfo, String fullGroupDesc, 
			List<String> groupListStr,  List<String> appListStr, boolean isEditable, String decodedDBPass, boolean isAdd) {
		UserDetailDTO updatedUserDetail = new UserDetailDTO();
		updatedUserDetail.setUserId(userInfo.getUserId());
		updatedUserDetail.setUserName(userInfo.getUserName());
		updatedUserDetail.setEmailAddress(userInfo.getEmailAddress());
		updatedUserDetail.setPassword(decodedDBPass);
		updatedUserDetail.setAppId(userInfo.getAppId());
		updatedUserDetail.setGroupCode(userInfo.getGroupCode());
		updatedUserDetail.setCreatedBy(userInfo.getCreatedBy());
		updatedUserDetail.setCreationDate(userInfo.getCreationDate());
		updatedUserDetail.setLastLoginStatus(userInfo.getLastLoginStatus());
		updatedUserDetail.setLastLoginTime(userInfo.getLastLoginTime());
		updatedUserDetail.setFullGroupDesc(fullGroupDesc);
		updatedUserDetail.setGroupList(groupListStr);
		updatedUserDetail.setAppList(appListStr);
		updatedUserDetail.setEditable(isEditable);
		updatedUserDetail.setAdd(isAdd);
		return updatedUserDetail;
	}
	
	public List<String> getAppListStr(List<AppConfigDTO> appList) throws AppException {	
		List<String> appListStr = new ArrayList<String>();
		for (int i = 0; i < appList.size(); i++) {
			AppConfigDTO appInfo = appList.get(i);
			appListStr.add(appInfo.getAppId().concat("-").concat(appInfo.getAppName()));
		}
		return appListStr;
	}	
	
	public List<String> getGroupListStr(List<GroupDTO> groupList) throws AppException {	
		List<String> groupListStr = new ArrayList<String>();
		for (int i = 0; i < groupList.size(); i++) {
			GroupDTO groupInfo = groupList.get(i);
			groupListStr.add(groupInfo.getGroupCode());
		}
		return groupListStr;
	}	
	
	
	public String getFullGroupDesc(List<UserDetailDTO> userList, List<GroupDTO> groupList) throws AppException {	
		String fullGroupDesc = "";
		if (userList.size() > 0) {
			UserDetailDTO userInfo = userList.get(0);
			for (int i = 0; i < groupList.size(); i++) {
				GroupDTO groupInfo = groupList.get(i);
				if (userInfo.getGroupCode().equals(groupInfo.getGroupCode())) {
					fullGroupDesc = groupInfo.getGroupCode().concat("-").concat(groupInfo.getGroupId());
				}
			}
		}
		return fullGroupDesc;
	}
	
	public String getInitFullGroupDesc(List<GroupDTO> groupList) throws AppException {	
		String fullGroupDesc = "";
			for (int i = 0; i < groupList.size(); i++) {
				GroupDTO groupInfo = groupList.get(i);
				if (i == 0) {
					fullGroupDesc = groupInfo.getGroupCode().concat("-").concat(groupInfo.getGroupId());
				}
			}
		return fullGroupDesc;
	}
	
}
