package com.flinders.poc.constant;

/**
 * Application Constants
 * @author mswahithali
 *
 */

public interface AppConstants {
	String APP_CODE = "001";
	String APP_NAME = "FLINDERSPOC";
	
	String LOGGED_IN_USER = "loginUser";
	String LOGIN_ID = "loginId";
	String ACTIVE_STATUS = "A";
	String INACTIVE_STATUS = "I";
	String URL_ADD = "ADD";
	String URL_EDIT = "EDIT";
	String AUTH_ERROR_MSG = "authErrMsg";
	String WIN_OS_NAME = "Windows";
	String SH_EXTN = ".sh";
	String BAT_EXTN = ".bat";
	String SELECT_NONE = "0";
	String HOME_URL = "/form";
	String LOGIN_AUTH_URL = "/auth/login";
	String RESOURCES_URL = "/resources/";
	String DOT =  ".";
	String UNDERSCORE = "_";
	String dateFormat_mmddyyyy_slash = "MM/dd/yyyy";
	String dateFormat = "yyyy-MM-dd";
	String dateTimeFormat = "yyyy-MM-dd hh:mm:ss";
	String SYMBOL_QUESTION = "?";
	String FLAG_YES = "Y";
	String FLAG_NO = "N";
	String FLAG_NA = "N/A";
	String HIGH = "High";
	
	char SPACE_PADDING = ' ';
	char ZERO_PADDING = '0';
	
	int MAX_UPLOAD_SIZE_IN_MB = 5 * 1024 * 1024; // 5 MB
	int VERSION_ONE = 1;
	int DEFAULT_START_INDEX = 0;
	int NO_INDEX = -1;
	
	String OPEN_BRACE = "(";
	String CLOSE_BRACE = ")";
	String LIKE = "LIKE";
	String IN = "IN";
	String SINGLE_QOUTE_STR = "&#39;";
	String DOUBLE_QOUTE_STR = "&#34;";
	String SINGLE_QOUTE_SYMBOL = "\'";
	String DOUBLE_QOUTE_SYMBOL = "\"";
	String SEMICOLON = ";";	
	String EQUAL_SYMBOL = "=";
	String EQUAL_SYMBOL_JS = "===";
	String LESSEREQ_SYMBOL_REVERT = "<===";
	String GREATEREQ_SYMBOL_REVERT = ">===";
	String LESSEREQ_SYMBOL = "<=";
	String GREATEREQ_SYMBOL = ">=";
	String NOT = "Not";
	String NOT_SYMBOL_JS = "!";
	String AND = "AND";
	String OR = "OR";
	String DEFAULT_N_APPENDER = "N";
	String DEFAULT_Y_APPENDER = "Y";
	String DEFAULT_SPACE_APPENDER = " ";
	String DEFAULT_EMPTY_LOG_APPENDER = "null";
	
	String FLAG_SUCCESS = "SUCCESS";
	String FLAG_FAILURE = "FAIL";
	String FLAG_ERROR = "ERROR";
	
	String MSG_UPDATE_SUCCESS = "Update success.";
	String MSG_ADD_SUCCESS = "Add success.";
	String MSG_UPDATE_FAIL = "Update failed. Please contact application support for help.";
	String MSG_ADD_FAIL = "Add failed. Please contact application support for help.";
	String MSG_CREATE_SUCCESS = "Create success.";
	String MSG_CREATE_FAIL = "Create failed. Please contact application support for help.";
	String MSG_REMOVE_SUCCESS = "Remove success.";
	String MSG_REMOVE_FAIL = "Remove failed. Please contact application support for help.";
	String MSG_LOGIN_FAIL_INCORRECT_ID = "User ID or password incorrect. Please try again.";
	String MSG_LOGIN_FAIL_UNAUTH_ID = "User ID does not have access to App.";
	String MSG_LOGIN_FAIL_BU_UNAUTH_ID = "User ID does not have suitable BU assigned.";
	String MSG_INVALID_SESSION = "Session Expired/Invalid. Login Again";
	String MSG_INVALID_SERVICE = "Service Unavailable. Try Login after sometime";
	
	String LOGIN_STATUS_SUCCESS = "Success";
	String LOGOUT_STATUS_SUCCESS = "Success";
	String LOGIN_STATUS_FAIL = "Fail";
	String LOGIN_ACTION_LOGIN = "Login";
	String LOGIN_ACTION_LOGOUT = "Logout";
	String DELETE_ERROR = "deleteError";
	
}
