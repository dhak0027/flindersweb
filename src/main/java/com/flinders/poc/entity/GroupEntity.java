package com.flinders.poc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Group Entity 
 * @author mswahithali
 */


@Entity
@Table(name="APP_GROUP")
public class GroupEntity{
	
	@Id
	@Column(name="GROUP_CODE")
	@Getter @Setter 
	private String groupCode;
	
	@Column(name="GROUP_ID")
	@Getter @Setter 
	private String groupId;

	@Column(name="GROUP_DESC")
	@Getter @Setter 
	private String groupDesc;

}
