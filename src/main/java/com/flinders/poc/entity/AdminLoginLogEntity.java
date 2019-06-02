package com.flinders.poc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.flinders.poc.common.entity.BasicChildEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * Admin Login Log Entity 
 * @author mswahithali
 */

@Entity
@Table(name="LOGIN_LOG")
public class AdminLoginLogEntity extends BasicChildEntity{

	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name="LOGIN_LOG_ID", nullable=false)	
	@SequenceGenerator(name="LOGIN_LOG_SEQ", sequenceName="LOGIN_LOG_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="LOGIN_LOG_SEQ")
	@Getter @Setter 
	private Long id;
	
	@Column(name="USER_ID", nullable=false)
	@Getter @Setter 
	private String userId;
	
	@Column(name="ACTION", nullable=false)
	@Getter @Setter 
	private String action;
	
	@Column(name="STATUS", nullable=true)
	@Getter @Setter 
	private String status;
	
	@Column(name="FAIL_REASON", nullable=true)
	@Getter @Setter 
	private String failReason;

	@Column(name="LOG_DATE", nullable=false)
	@Getter @Setter 
	private Date logDate;
	
}
