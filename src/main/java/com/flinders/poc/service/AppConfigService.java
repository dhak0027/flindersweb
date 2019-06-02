package com.flinders.poc.service;

import java.util.List;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.dto.AdminLoginLogDTO;
import com.flinders.poc.dto.AppConfigDTO;

/**
 * App Config Service interface
 * @author mswahithali
 *
 */

public interface AppConfigService {

	public List<AppConfigDTO> getAllApps() throws AppException;

}
