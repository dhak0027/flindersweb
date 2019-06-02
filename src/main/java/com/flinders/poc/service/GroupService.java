package com.flinders.poc.service;

import java.util.List;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.dto.GroupDTO;

/**
 * Group Service interface
 * @author mswahithali
 *
 */

public interface GroupService {

	public List<GroupDTO> getAllGroups() throws AppException;

}
