package com.flinders.poc.dto;

import javax.persistence.Id;

import com.flinders.poc.common.dto.BasicDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Group bean DTO
 * @author mswahithali
 *
 */

@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO extends BasicDTO{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Getter @Setter 
	private String groupCode;
	
	@Getter @Setter 
	private String groupId;

	@Getter @Setter 
	private String groupDesc;
}
