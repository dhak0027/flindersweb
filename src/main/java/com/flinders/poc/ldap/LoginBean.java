package com.flinders.poc.ldap;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Basic Login Session bean for all the form search
 * @author mswahithali
 *
 */

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private String userId;
	
	@Getter @Setter
	private String password;
	
	@Getter @Setter
	private String username;
	
	@Getter @Setter
	private String appId;
	
	@Getter @Setter
	private String role;
	
	@Getter @Setter
	private String groupId;
	
	@Getter @Setter
	private String groupCode;
	
	@Getter @Setter
	private boolean userBUFlag;
	
	@Getter @Setter
	private boolean studentBUFlag;
	
	@Getter @Setter
	private boolean reportBUFlag;
	
	@Getter @Setter
	private String createdBy;
	
	@Getter @Setter
	private Date creationDate;
	
	@Getter @Setter
	private String lastLoginStatus;
	
	@Getter @Setter
	private Date lastLoginTime;
	
	
}
