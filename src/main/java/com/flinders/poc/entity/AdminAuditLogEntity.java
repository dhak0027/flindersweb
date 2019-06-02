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
 * Admin Audit Log Entity 
 * @author mswahithali
 */

@Entity
@Table(name="LOS_ADMIN_AUDIT_LOG")
public class AdminAuditLogEntity extends BasicChildEntity{

	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name="LOS_ADMIN_AUDIT_ID", nullable=false)	
	@SequenceGenerator(name="LOS_ADMIN_AUDIT_SEQ", sequenceName="LOS_ADMIN_AUDIT_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="LOS_ADMIN_AUDIT_SEQ")
	@Getter @Setter 
	private Long id;
	
	@Column(name="APP_ACRONYM", nullable=true)
	@Getter @Setter 
	private String appAcronym;
	
	@Column(name="ADMIN_TYPE", nullable=false)
	@Getter @Setter 
	private String adminType;

	@Column(name="ACTION", nullable=false)
	@Getter @Setter 
	private String action;
	
	@Column(name="KEY_TYPE", nullable=false)
	@Getter @Setter 
	private String keyType;
	
	@Column(name="KEY_VALUE", nullable=false)
	@Getter @Setter 
	private String keyValue;
	
	@Column(name="MEMBER_TYPE", nullable=false)
	@Getter @Setter 
	private String memberType;

	@Column(name="MEMBER_VALUE", nullable=false)
	@Getter @Setter 
	private String memberValue;
	
	@Column(name="CREATED_BY", updatable=false)
	@Getter @Setter
	private String createdBy;
	
	@Column(name="CREATION_DATE",updatable=false)
	@Getter @Setter
	private Date creationDate;
	
	@Column(name="RCD_STATUS")
	@Getter @Setter
	private String rcdStatus;
	
	@Column(name="VERSION_NUM")
	@Getter @Setter
	private int versionNum;

}
