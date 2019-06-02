package com.flinders.poc.repository;

import java.util.List;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.entity.ApplicationUserEntity;

/**
 * Application User JPA Repository Custom Interface for custom query
 * @author mswahithali
 *
 */

public interface ApplicationUserRepositoryCustom{
	public List<ApplicationUserEntity> findApplicationUser(String appId, String userId) throws AppException;
	public List<ApplicationUserEntity> findApplicationUser(String appId, String userId, String userName) throws AppException;
}
