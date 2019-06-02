package com.flinders.poc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.common.utils.ModelEntityMapper;
import com.flinders.poc.dto.AppConfigDTO;
import com.flinders.poc.entity.AppConfigEntity;
import com.flinders.poc.repository.AppConfigRepository;

/**
 * App Config service implementation
 * @author mswahithali
 *
 */

@Service("appConfigService")
public class AppConfigServiceImpl implements AppConfigService {
	
	@Autowired
	AppConfigRepository appConfigRepository;

	@Override
	public List<AppConfigDTO> getAllApps() throws AppException {
		List<AppConfigDTO> appList = new ArrayList<AppConfigDTO>();
		for (AppConfigEntity appConfigEntity : appConfigRepository.findAll()) {
			AppConfigDTO appConfigDTO =  ModelEntityMapper.converEntityToModel(appConfigEntity, AppConfigDTO.class);
			appList.add(appConfigDTO);
		}
		return appList;
	}

}
