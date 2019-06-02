package com.flinders.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flinders.poc.entity.GroupEntity;

/**
 * Application Group JPA Repository Interface
 * @author mswahithali
 *
 */

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long>, GroupRepositoryCustom {
	
	List<GroupEntity> findAll();
}
