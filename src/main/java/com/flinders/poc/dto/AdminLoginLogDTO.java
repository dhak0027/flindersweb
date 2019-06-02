package com.flinders.poc.dto;

import java.util.Date;

import com.flinders.poc.common.dto.BasicChildDTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Admin Login Log Bean DTO
 * @author mswahithali
 *
 */

public class AdminLoginLogDTO extends BasicChildDTO {
	
	private static final long serialVersionUID = 1L;

	@Getter @Setter 
	private Long id;
	
	@Getter @Setter 
	private String userId;
	
	@Getter @Setter 
	private String action;
	
	@Getter @Setter 
	private String status;
	
	@Getter @Setter 
	private String failReason;

	@Getter @Setter 
	private Date logDate;
	
}
