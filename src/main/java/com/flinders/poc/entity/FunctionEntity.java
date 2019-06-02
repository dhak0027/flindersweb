package com.flinders.poc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Function Entity
 * @author mswahithali
 */


@Entity
@Table(name="APP_FUNCTION")
public class FunctionEntity{
	
	@Id
	@Column(name="FUNCTION_CODE")
	@Getter @Setter 
	private String functionCode;
	
	@Column(name="FUNCTION_ID")
	@Getter @Setter 
	private String functionId;

	@Column(name="FUNCTION_DESC")
	@Getter @Setter 
	private String functionDesc;

	@Column(name="STATUS")
	@Getter @Setter 
	private String status;
	
}
