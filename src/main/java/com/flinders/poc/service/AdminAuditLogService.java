package com.flinders.poc.service;

import java.util.Date;
import java.util.List;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.dto.AdminAuditLogDTO;
import com.flinders.poc.dto.AdminLoginLogDTO;
import com.flinders.poc.dto.UserDetailDTO;

/**
 * Admin Audit Log Service interface
 * @author mswahithali
 *
 */

public interface AdminAuditLogService {
	
	public List<AdminAuditLogDTO> getAuditLog(String adminType, Date periodFrom, Date periodTo) throws AppException;
	
	void saveUserAuditLog(UserDetailDTO userDetail) throws AppException;
	
	void saveLoginLog(AdminLoginLogDTO adminLoginLog) throws AppException;
}
