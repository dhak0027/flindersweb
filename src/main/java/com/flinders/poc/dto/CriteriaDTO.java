package com.flinders.poc.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * Criteria bean DTO for all the form search
 * @author mswahithali
 *
 */

public class CriteriaDTO{

	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Getter @Setter 
	private Date fromDate;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Getter @Setter 
	private Date toDate;
	
	@Getter @Setter
	private String applicationType;
	
	@Getter @Setter 
	private String groupName;
	
	@Getter @Setter 
	private String userId;
	
	@Getter @Setter 
	private String userName;
	
	@Getter @Setter 
	private String studentId;
	
	@Getter @Setter 
	private String studentName;
	
	@Getter @Setter 
	private String teamName;
	
	@Getter @Setter 
	private String instCode;
	
	@Getter @Setter 
	private String appCode;
	
}
