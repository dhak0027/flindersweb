package com.flinders.poc.common.controller;

import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.flinders.poc.common.constants.ActionMode;
import com.flinders.poc.common.dto.BasicDTO;
import com.flinders.poc.common.dto.ValidationErrorDTO;
import com.flinders.poc.common.variable.AppDate;
import com.flinders.poc.constant.AppConstants;
import com.flinders.poc.dto.CriteriaDTO;
import com.flinders.poc.ldap.LoginBean;

/**
 * Parent Abstract Controller for all controller implementation
 * @author mswahithali
 */

public abstract class BasicController {

	/** Resource bundle file name. */
	private static final String RESOURCE_BUNDLE = "errorMessages";
	
	protected abstract void initBinder(WebDataBinder binder);
	
	protected abstract BasicDTO createNewModel();

	protected abstract CriteriaDTO createNewCriteria();
	
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        
        return processFieldErrors(fieldErrors);
    }
 
    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();
 
        for (FieldError fieldError: fieldErrors) {
            dto.addFieldError(fieldError.getField(), getMessageByCode(fieldError.getCode()));
        }
        return dto;
    }
 
    private String getMessageByCode(String code) {
		String rst = null;
		try {
			rst = ResourceBundle.getBundle(RESOURCE_BUNDLE, Locale.US).getString(code);
		} catch (MissingResourceException e) {
			rst = null;
		}
		return rst;
	}
    
	public void fillDefaultValue(BasicDTO dto, ActionMode action) {
		
		LoginBean loginUser = (LoginBean)getSession().getAttribute(AppConstants.LOGGED_IN_USER);
		switch (action) {
		case CREATE:
//			dto.setActionMode(action);
			dto.setCreatedBy(loginUser.getUserId());
			dto.setCreationDate(new AppDate(System.currentTimeMillis()));
			dto.setVersionNum(AppConstants.VERSION_ONE);
			break;

		case MODIFY:
//			dto.setActionMode(action);
			dto.setModifiedBy(loginUser.getUserId());
			dto.setModificationDate(new AppDate(System.currentTimeMillis()));
			dto.setVersionNum(dto.getVersionNum() + AppConstants.VERSION_ONE);
			break;

		case DELETE:
//			dto.setActionMode(action);
			dto.setModifiedBy(loginUser.getUserId());
			dto.setModificationDate(new AppDate(System.currentTimeMillis()));
			dto.setVersionNum(dto.getVersionNum() + AppConstants.VERSION_ONE);
			break;

		case VALIDATE:
			break;

		default:
			break;
		}
	}
	
	private HttpSession getSession(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		return session;
	}

}

