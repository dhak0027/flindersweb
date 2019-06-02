package com.flinders.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flinders.poc.entity.FunctionEntity;

/**
 * Application Function JPA Repository Interface
 * @author mswahithali
 *
 */

@Repository
public interface FunctionRepository extends JpaRepository<FunctionEntity, Long>, FunctionRepositoryCustom {
	
	List<FunctionEntity> findAll();
}
