package com.flinders.poc.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.flinders.poc.common.constants.ActionMode;

import lombok.Getter;
import lombok.Setter;

/**
 * Parent abstract entity for all entities
 * @author mswahithali
 */

@MappedSuperclass
public abstract class BasicEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="CREATED_BY", updatable=false)
	@Getter @Setter
	private String createdBy;
	
	@Column(name="CREATION_DATE",updatable=false)
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
		
	@Transient
	@Getter @Setter
	private ActionMode actionMode;

}
