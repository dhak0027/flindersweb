package com.flinders.poc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * App Config Entity 
 * @author mswahithali
 */


@Entity
@Table(name="APP_CONFIG")
public class AppConfigEntity{
	
	@Id
	@Column(name="APP_ID")
	@Getter @Setter 
	private String appId;
	
	@Column(name="APP_NAME")
	@Getter @Setter 
	private String appName;

	@Column(name="APP_DESC")
	@Getter @Setter 
	private String appDesc;

}
