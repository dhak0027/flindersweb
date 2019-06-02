package com.flinders.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flinders.poc.entity.AppConfigEntity;

/**
 * Application Config JPA Repository Interface
 * @author mswahithali
 *
 */

@Repository
public interface AppConfigRepository extends JpaRepository<AppConfigEntity, Long>, AppConfigRepositoryCustom {
	
	List<AppConfigEntity> findAll();
}
