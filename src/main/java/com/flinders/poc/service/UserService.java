package com.flinders.poc.service;

import java.util.List;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.dto.UserDetailDTO;

/**
 * User Service interface
 * @author mswahithali
 *
 */

public interface UserService {

	public List<UserDetailDTO> getAllUsers() throws AppException;

	public List<UserDetailDTO> searchUsersByIdAndName(String app_id, String userId, String userName) throws AppException;
	
	public List<UserDetailDTO> findApplicationUser(String instCode, String appCode, String userId) throws AppException;

	public boolean addApplicationUser(UserDetailDTO userDetail) throws AppException;
	
	public boolean updateApplicationUser(UserDetailDTO userDetail) throws AppException;
	
	public boolean updateLoginLog(UserDetailDTO userDetail) throws AppException;

}
