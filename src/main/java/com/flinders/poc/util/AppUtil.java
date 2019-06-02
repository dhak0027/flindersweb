package com.flinders.poc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.flinders.poc.constant.AppConstants;
import com.flinders.poc.ldap.LoginBean;

/**
 * Application Common Singleton Utility
 * @author mswahithali
 */

//public enum AppUtil {
	//INSTANCE;
public enum AppUtil {
	INSTANCE;
	private static final Logger logger = Logger.getLogger(AppUtil.class.getName());
	
	Base64 base64 = new Base64();
	
	public static void log_debug(String action, String msg) {
		logger.debug("[FLINDERSWAR] ["+action+"] [" + msg + "]");
	}
	
	public static void log_info(String action, String msg) {
		logger.info("[FLINDERSWAR] ["+action+"] [" + msg + "]");
	}

	public static void log_error(String action, String msg) {
		logger.error("[FLINDERSWAR] ["+action+"] [" + msg + "]");
	}
	
	
	private static Map<String, String> operatorMap = new HashMap<>();
	
	public Date stringToDate(String value) {
		Date date = null;
		try {
			if (value != null) {
				date = new SimpleDateFormat(AppConstants.dateFormat).parse(value);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public Date stringToDateWithTime(String value) {
		Date date = null;
		try {
			if (value != null) {
				date = new SimpleDateFormat(AppConstants.dateTimeFormat).parse(value);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public String dateToString(Date date) {
		String value = null;
		if (date != null) {
			value = new SimpleDateFormat(AppConstants.dateFormat).format(date);
		}
		return value;
	}
	
	public String dateToStringWithTime(Date date) {
		String value = null;
		if (date != null) {
			value = new SimpleDateFormat(AppConstants.dateTimeFormat).format(date);
		}
		return value;
	}
	
	public Map<String, String> operators(){
		if (CollectionUtils.isEmpty(operatorMap)) {
			operatorMap.put("", "--Select--");
			operatorMap.put(">", ">");
			operatorMap.put("<", "<");
			operatorMap.put(">=", ">=");
			operatorMap.put("<=", "<=");
			operatorMap.put("=", "=");
			/*operatorMap.put("LIKE", "LIKE");*/
			operatorMap.put("IN", "IN");
		}
		return operatorMap;
	}
	
	public LoginBean getLoginBean(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		return (LoginBean)session.getAttribute(AppConstants.LOGGED_IN_USER);
	}
	
	public String getEncodedPassword (String password) {
		String encodedPassword = null;
		encodedPassword = new String(base64.encode(password.getBytes()));
		return encodedPassword;
	}
	
	public String getDecodedPassword (String password) {
		String decodedPassword = null;
		decodedPassword = new String(base64.decode(password.getBytes()));
		return decodedPassword;
	}
}
