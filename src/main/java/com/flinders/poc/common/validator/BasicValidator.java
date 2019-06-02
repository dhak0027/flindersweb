package com.flinders.poc.common.validator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.flinders.poc.constant.AppConstants;

/**
 * Parent abstract custom validator for all the validators
 * @author mswahithali
 */

public abstract class BasicValidator implements Validator{
	
	protected HttpServletRequest getRequest(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
		.getRequest();
		return request;
	}
	
	protected boolean checkURLForDuplicateValid(){
		String URL = getRequest().getRequestURL().toString();
		return StringUtils.endsWithIgnoreCase(URL, AppConstants.URL_ADD);
	}
	
	protected boolean isDeleteRequest(){
		String URL = getRequest().getRequestURL().toString();
		String params = getRequest().getParameter("delete");
		return (StringUtils.endsWithIgnoreCase(URL, AppConstants.URL_EDIT) && params != null);
	}
}
