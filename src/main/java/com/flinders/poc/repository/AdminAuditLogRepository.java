package com.flinders.poc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.entity.AdminAuditLogEntity;

/**
 * Admin Audit Log JPA Repository Interface
 * @author mswahithali
 *
 */

@Repository
public interface AdminAuditLogRepository extends JpaRepository<AdminAuditLogEntity, Long>, AdminAuditLogRepositoryCustom {
	public List<AdminAuditLogEntity> findByAdminTypeAndCreationDateBetweenOrderByCreationDateDesc(String adminType, Date from, Date to) throws AppException;
}
