package com.flinders.poc.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import com.flinders.poc.common.dto.BasicDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User Detail bean DTO
 * @author mswahithali
 *
 */

@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDTO extends BasicDTO{
	
	private static final long serialVersionUID = 1L;

	@Getter @Setter 
	private long seq;
	
	@Id
	@Getter @Setter 
	private String userId;
	
	@Getter @Setter 
	private String userName;

	@Getter @Setter 
	private String appId;

	@Getter @Setter 
	private String password;

	@Getter @Setter 
	private String emailAddress;
	
	@Getter @Setter 
	private String groupCode;	
	
	@Getter @Setter 
	private String fullGroupDesc;	

	@Getter @Setter 
	private String lastLoginStatus;
	
	@Getter @Setter 
	private Date lastLoginTime;
	
	@Getter @Setter 
	private boolean isEditable;
	
	@Getter @Setter 
	private boolean isAdd;
	
	@Getter @Setter 
	private List<String> groupList = new ArrayList<String>();
	
	@Getter @Setter 
	private List<String> appList = new ArrayList<String>();

}
