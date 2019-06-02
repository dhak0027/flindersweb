package com.flinders.poc.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Application Function JPA Repository custom implementation
 * @author mswahithali
 *
 */

public class FunctionRepositoryImpl implements FunctionRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

}
