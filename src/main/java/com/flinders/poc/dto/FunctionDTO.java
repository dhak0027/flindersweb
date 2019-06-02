package com.flinders.poc.dto;

import javax.persistence.Id;

import com.flinders.poc.common.dto.BasicDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Function bean DTO
 * @author mswahithali
 *
 */

@AllArgsConstructor
@NoArgsConstructor
public class FunctionDTO extends BasicDTO{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Getter @Setter 
	private String functionCode;
	
	@Getter @Setter 
	private String functionId;

	@Getter @Setter 
	private String functionDesc;
	
	@Getter @Setter 
	private String status;
	
}
