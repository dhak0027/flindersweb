package com.flinders.poc.service;

import java.util.List;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.dto.FunctionDTO;

/**
 * Function Service interface
 * @author mswahithali
 *
 */

public interface FunctionService {

	public List<FunctionDTO> getAllFunctions() throws AppException;

}
