package com.flinders.poc.dto;

import com.flinders.poc.common.dto.BasicDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Admin Audit Log bean DTO
 * @author mswahithali
 */

@AllArgsConstructor
@NoArgsConstructor
public class AdminAuditLogDTO extends BasicDTO{

	private static final long serialVersionUID = 1L;

	@Getter @Setter 
	private long id;
	
	@Getter @Setter 
	private String appAcronym;
	
	@Getter @Setter 
	private String adminType;

	@Getter @Setter 
	private String action;
	
	@Getter @Setter 
	private String keyType;
	
	@Getter @Setter 
	private String keyValue;
	
	@Getter @Setter 
	private String memberType;
	
	@Getter @Setter 
	private String memberValue;

}
