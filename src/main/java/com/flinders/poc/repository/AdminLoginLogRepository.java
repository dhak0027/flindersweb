package com.flinders.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flinders.poc.entity.AdminLoginLogEntity;

/**
 * Admin Login Log JPA Repository Interface
 * @author mswahithali
 *
 */

@Repository
public interface AdminLoginLogRepository extends JpaRepository<AdminLoginLogEntity, Long>, AdminLoginLogRepositoryCustom {
}
