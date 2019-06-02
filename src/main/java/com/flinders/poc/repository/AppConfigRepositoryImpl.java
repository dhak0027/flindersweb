package com.flinders.poc.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Application Group JPA Repository custom implementation
 * @author mswahithali
 *
 */

public class AppConfigRepositoryImpl implements AppConfigRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

}
