package com.flinders.poc.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.entity.ApplicationUserEntity;

/**
 * Application User JPA Repository custom implementation
 * @author mswahithali
 *
 */

public class ApplicationUserRepositoryImpl implements ApplicationUserRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ApplicationUserEntity> findApplicationUser(String appId, String userId)
			throws AppException {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ApplicationUserEntity> query = builder.createQuery(ApplicationUserEntity.class);

		Root<ApplicationUserEntity> root = query.from(ApplicationUserEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (StringUtils.isNotEmpty(appId)) {
			Predicate predicateKey = builder.equal(root.get("appId"), appId);
			predicates.add(predicateKey);
		}
		
		if (StringUtils.isNotEmpty(userId)) {
			Predicate predicateKey = builder.equal(root.get("userId"), userId);
			predicates.add(predicateKey);
		}
		
		if (predicates != null && predicates.size() > 0) {
			query.where(builder.and(predicates.toArray(new Predicate[] {})));
		}

		return entityManager.createQuery(query.select(root)).getResultList();
	}
	
	@Override
	public List<ApplicationUserEntity> findApplicationUser(String appId, String userId, String userName)
			throws AppException {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ApplicationUserEntity> query = builder.createQuery(ApplicationUserEntity.class);

		Root<ApplicationUserEntity> root = query.from(ApplicationUserEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (StringUtils.isNotEmpty(appId)) {
			Predicate predicateKey = builder.equal(root.get("appId"), appId);
			predicates.add(predicateKey);
		}
		
		if (StringUtils.isNotEmpty(userId)) {
			Predicate predicateKey = builder.equal(root.get("userId"), userId);
			predicates.add(predicateKey);
		}
		
		if (StringUtils.isNotEmpty(userName)) {
			Predicate predicateKey = builder.equal(root.get("userName"), userName);
			predicates.add(predicateKey);
		}
		
		if (predicates != null && predicates.size() > 0) {
			query.where(builder.and(predicates.toArray(new Predicate[] {})));
		}

		return entityManager.createQuery(query.select(root)).getResultList();
	}
}
