package com.flinders.poc.common.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Parent abstract Basic DTO for all DTO
 * @author mswahithali
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public abstract class BasicDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private String createdBy;
	
	@Getter @Setter
	private Date creationDate;
	
	@Getter @Setter
	private String modifiedBy;
	
	@Getter @Setter
	private Date modificationDate;
	
	@Getter @Setter
	private int versionNum;
	
}
