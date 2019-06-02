package com.flinders.poc.validator;


import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.flinders.poc.common.validator.BasicValidator;
import com.flinders.poc.ldap.LoginBean;

/**
 * Login Bean Validator
 * @author mswahithali
 */

@Component
public class LoginValidator extends BasicValidator {
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return LoginBean.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "required.auth.userId");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.auth.password");
	}
	
	public boolean containsAKeyword(String businessUnit, List<String> businessUnits){
		for (String bu: businessUnits){
			if(bu.contains(businessUnit))
				return true;
		}
		return false;
	}

}