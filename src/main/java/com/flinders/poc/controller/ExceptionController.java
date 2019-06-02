package com.flinders.poc.controller;

import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.flinders.poc.constant.AppConstants;
import com.flinders.poc.ldap.LoginBean;


/**
 * Exception controller for exception logging and redirecting
 * @author mswahithali
 *
 */
@ControllerAdvice
public class ExceptionController {
	
	public LoginBean createNewModel() {
		return new LoginBean();
	}
	
	@ExceptionHandler(HttpSessionRequiredException.class)
	public ModelAndView handleCustomException(HttpSessionRequiredException ex) {
		ex.printStackTrace();
		//log.error("Session Invalid", ex);
		ModelAndView model = new ModelAndView("login");
		model.addObject("loginBean", createNewModel());
		model.addObject(AppConstants.AUTH_ERROR_MSG, AppConstants.MSG_INVALID_SESSION);
		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ex.printStackTrace();
		//log.error("Service Unavailable", ex);
		ModelAndView model = new ModelAndView("login");
		model.addObject("loginBean", createNewModel());
		model.addObject(AppConstants.AUTH_ERROR_MSG, AppConstants.MSG_INVALID_SERVICE);
		return model;
	}
	
	
	
}
