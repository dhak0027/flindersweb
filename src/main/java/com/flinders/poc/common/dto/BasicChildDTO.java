package com.flinders.poc.common.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

/**
 * Abstract Basic Child DTO for all DTO
 * @author mswahithali
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public abstract class BasicChildDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
}
