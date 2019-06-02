package com.flinders.poc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.common.utils.ModelEntityMapper;
import com.flinders.poc.dto.FunctionDTO;
import com.flinders.poc.entity.FunctionEntity;
import com.flinders.poc.repository.FunctionRepository;

/**
 * Function service implementation
 * @author mswahithali
 *
 */

@Service("functionService")
public class FunctionServiceImpl implements FunctionService {
	
	@Autowired
	@Qualifier("adminAuditLogService")
	private AdminAuditLogService adminAuditLogService;
	
	@Autowired
	@Qualifier("functionService")
	private FunctionService functionService;
	
	@Autowired
	FunctionRepository functionRepository;

	@Override
	public List<FunctionDTO> getAllFunctions() throws AppException {
		List<FunctionDTO> functionList = new ArrayList<FunctionDTO>();
		for (FunctionEntity functionEntity : functionRepository.findAll()) {
			FunctionDTO groupDTO =  ModelEntityMapper.converEntityToModel(functionEntity, FunctionDTO.class);
			functionList.add(groupDTO);
		}
		return functionList;
	}
	
}
