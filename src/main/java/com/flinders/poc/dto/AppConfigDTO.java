package com.flinders.poc.dto;

import java.util.Date;

import javax.persistence.Id;

import com.flinders.poc.common.dto.BasicDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * App Config bean DTO
 * @author mswahithali
 *
 */

@AllArgsConstructor
@NoArgsConstructor
public class AppConfigDTO extends BasicDTO{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Getter @Setter 
	private String appId;
	
	@Getter @Setter 
	private String appName;

	@Getter @Setter 
	private String appDesc;
	
	@Getter @Setter 
	private Date lastLoginTime;
	
	@Getter @Setter 
	private String lastLoginStatus;
}
