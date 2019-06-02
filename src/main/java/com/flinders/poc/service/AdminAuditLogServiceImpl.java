package com.flinders.poc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.common.utils.ModelEntityMapper;
import com.flinders.poc.dto.AdminAuditLogDTO;
import com.flinders.poc.dto.AdminLoginLogDTO;
import com.flinders.poc.dto.UserDetailDTO;
import com.flinders.poc.entity.AdminAuditLogEntity;
import com.flinders.poc.entity.AdminLoginLogEntity;
import com.flinders.poc.repository.AdminAuditLogRepository;
import com.flinders.poc.repository.AdminLoginLogRepository;

/**
 * Admin Audit Log Service implementation
 * @author mswahithali
 *
 */

@Service("adminAuditLogService")
public class AdminAuditLogServiceImpl implements AdminAuditLogService {

	@Autowired
	AdminAuditLogRepository adminAuditLogRepository;
	
	@Autowired
	AdminLoginLogRepository adminLoginLogRepository;
	
	@Override
	public List<AdminAuditLogDTO> getAuditLog(String adminType, Date periodFrom, Date periodTo) throws AppException {
		List<AdminAuditLogDTO> result = new ArrayList<AdminAuditLogDTO>();
		List<AdminAuditLogEntity> list = adminAuditLogRepository.findByAdminTypeAndCreationDateBetweenOrderByCreationDateDesc(adminType, periodFrom, periodTo);
		for (int i=0; i<list.size(); i++) {
			AdminAuditLogDTO auditLog = (AdminAuditLogDTO) ModelEntityMapper.converEntityToModel(list.get(i), AdminAuditLogDTO.class);
			result.add(auditLog);
		}
		return result;
	}

	@Override
	public void saveUserAuditLog(UserDetailDTO userDetail) throws AppException {

	}
	
	/*private LoginBean getLoginBean() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		return (LoginBean)session.getAttribute(AppConstants.LOGGED_IN_USER);
	}*/
	
	@Override
	public void saveLoginLog(AdminLoginLogDTO adminLoginLog) throws AppException {
		AdminLoginLogEntity loginLogEntity = (AdminLoginLogEntity) ModelEntityMapper.converModelToEntity(adminLoginLog, AdminLoginLogEntity.class);
		adminLoginLogRepository.save(loginLogEntity);
	}
	
}
