package com.flinders.poc.common.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * Parent abstract child entity for all entities
 * @author mswahithali
 */

@MappedSuperclass
public abstract class BasicChildEntity implements Serializable {

	private static final long serialVersionUID = 1L;

}
