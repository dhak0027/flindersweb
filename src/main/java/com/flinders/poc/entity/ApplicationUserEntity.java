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
 * Application User Entity 
 * @author mswahithali
 */


@Entity
@Table(name="APP_USER")
public class ApplicationUserEntity extends BasicChildEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SEQ", nullable=false)	
	@SequenceGenerator(name="APP_USER_SEQ", sequenceName="APP_USER_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO , generator="APP_USER_SEQ")
	@Getter @Setter 
	private long seq;
	
	@Column(name="USER_ID")
	@Getter @Setter 
	private String userId;
	
	@Column(name="USER_NAME")
	@Getter @Setter 
	private String userName;
	
	@Column(name="PASSWORD")
	@Getter @Setter 
	private String password;
	
	@Column(name="EMAIL_ADDRESS")
	@Getter @Setter 
	private String emailAddress;
	
	@Column(name="APP_ID")
	@Getter @Setter 
	private String appId;
	
	@Column(name="GROUP_CODE")
	@Getter @Setter 
	private String groupCode;	
	
	@Column(name="LAST_LOGIN_STATUS")
	@Getter @Setter 
	private String lastLoginStatus;
	
	@Column(name="LAST_LOGIN_TIME")
	@Getter @Setter 
	private Date lastLoginTime;
	
	@Column(name="CREATED_BY")
	@Getter @Setter
	private String createdBy;
	
	@Column(name="CREATION_DATE")
	@Getter @Setter
	private Date creationDate;
	
	@Column(name="MODIFIED_BY")
	@Getter @Setter
	private String modifiedBy;
	
	@Column(name="MODIFICATION_DATE")
	@Getter @Setter
	private Date modificationDate;
	
	@Column(name="VERSIONNUM")
	@Getter @Setter
	private int versionNum;

}
