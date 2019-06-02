package com.flinders.poc.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Application Group JPA Repository custom implementation
 * @author mswahithali
 *
 */

public class GroupRepositoryImpl implements GroupRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

}
