package com.flinders.poc.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.common.utils.ModelEntityMapper;
import com.flinders.poc.common.variable.AppDate;
import com.flinders.poc.dto.UserDetailDTO;
import com.flinders.poc.entity.ApplicationUserEntity;
import com.flinders.poc.repository.ApplicationUserRepository;
import com.flinders.poc.util.AppUtil;

/**
 * User service implementation
 * @author mswahithali
 *
 */

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("adminAuditLogService")
	private AdminAuditLogService adminAuditLogService;
	
	@Autowired
	ApplicationUserRepository applicationUserRepository;
	
	@Override
	public List<UserDetailDTO> getAllUsers() throws AppException {
		List<UserDetailDTO> userList = new ArrayList<UserDetailDTO>();
		for (ApplicationUserEntity applicationUserEntity : applicationUserRepository.findAll()) {
			UserDetailDTO userDetailDTO =  ModelEntityMapper.converEntityToModel(applicationUserEntity, UserDetailDTO.class);
			userList.add(userDetailDTO);
		}
		return userList;
	}

	@Override
	public List<UserDetailDTO> searchUsersByIdAndName(String app_id, String userId, String userName) throws AppException {
		List<UserDetailDTO> userList = new ArrayList<UserDetailDTO>();
		List<ApplicationUserEntity> entityList = applicationUserRepository.findApplicationUser(app_id, userId, userName);
		for (ApplicationUserEntity entity : entityList) {
			UserDetailDTO userDetail = (UserDetailDTO) ModelEntityMapper.converEntityToModel(entity, UserDetailDTO.class);
			userList.add(userDetail);
		}
		return userList;
	}

	@Override
	public List<UserDetailDTO> findApplicationUser(String instCode, String appCode, String userId) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addApplicationUser(UserDetailDTO userDetail)
			throws AppException {
		ApplicationUserEntity userEntity = (ApplicationUserEntity) ModelEntityMapper.converModelToEntity(userDetail, ApplicationUserEntity.class);
		userEntity.setModificationDate(new AppDate(System.currentTimeMillis()));
		userEntity.setModifiedBy(AppUtil.INSTANCE.getLoginBean().getUserId());
		System.out.println(userEntity.getSeq());
		try {
			applicationUserRepository.saveAndFlush(userEntity);
		} catch (DataIntegrityViolationException e) {
			throw new AppException("addApplicationUser", "DataIntegrityViolationException:", e);
		} catch (ConstraintViolationException e) {
			throw new AppException("addApplicationUser", "ConstraintViolationException:", e);
		}
		return true;
	}
	
	/*private ApplicationUserEntity initEntity(String appId, String userId, String userName, 
			String password, String emailAddress, String groupCode, int versionNum, String createdBy,
			Date createdDate) throws AppException {
		ApplicationUserEntity newEntity = new ApplicationUserEntity();
		
		newEntity.setAppId(appId);
		newEntity.setUserId(userId);
		newEntity.setUserName(userName);
		newEntity.setPassword(password);
		newEntity.setEmailAddress(emailAddress);
		newEntity.setGroupCode(groupCode);
		newEntity.setVersionNum(0);
		newEntity.setCreatedBy(createdBy);
		newEntity.setCreationDate(createdDate);
		newEntity.setModificationDate(new AppDate(System.currentTimeMillis()));
		newEntity.setModifiedBy(AppUtil.INSTANCE.getLoginBean().getUserId());
		newEntity.setLastLoginStatus("");
		newEntity.setLastLoginTime(null);
		return newEntity;
	}*/

	@Transactional
	@Override
	public boolean updateApplicationUser(UserDetailDTO userDetail) throws AppException {
		
		ApplicationUserEntity userEntity = (ApplicationUserEntity) ModelEntityMapper.converModelToEntity(userDetail, ApplicationUserEntity.class);
		userEntity.setModificationDate(new AppDate(System.currentTimeMillis()));
		userEntity.setModifiedBy(AppUtil.INSTANCE.getLoginBean().getUserId());
		
		/*
		ApplicationUserEntity newEntity = initEntity(userDetail.getAppId(),
				userDetail.getUserId(), userDetail.getUserName(), userDetail.getPassword(), userDetail.getEmailAddress(), 
				userDetail.getGroupCode(), userDetail.getVersionNum(), userDetail.getCreatedBy(), userDetail.getCreationDate());
		*/
		
		applicationUserRepository.updateApplicationUser(userEntity.getUserName(), userEntity.getPassword(), userEntity.getEmailAddress(),
				userEntity.getGroupCode(), userEntity.getModifiedBy(), userEntity.getModificationDate(), userEntity.getUserId());
		return true;
	}
	
	@Transactional
	@Override
	public boolean updateLoginLog(UserDetailDTO userDetail) throws AppException {
		
		ApplicationUserEntity userEntity = (ApplicationUserEntity) ModelEntityMapper.converModelToEntity(userDetail, ApplicationUserEntity.class);
		userEntity.setModificationDate(new AppDate(System.currentTimeMillis()));
		userEntity.setModifiedBy(AppUtil.INSTANCE.getLoginBean().getUserId());
		
		applicationUserRepository.updateLoginLog(userEntity.getLastLoginStatus(), userEntity.getLastLoginTime(), 
				userEntity.getModifiedBy(), userEntity.getModificationDate(), userEntity.getUserId());
		return true;
	}

}
