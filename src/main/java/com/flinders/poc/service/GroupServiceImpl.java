package com.flinders.poc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flinders.poc.common.exception.AppException;
import com.flinders.poc.common.utils.ModelEntityMapper;
import com.flinders.poc.dto.GroupDTO;
import com.flinders.poc.entity.GroupEntity;
import com.flinders.poc.repository.GroupRepository;

/**
 * Group service implementation
 * @author mswahithali
 *
 */

@Service("groupService")
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	GroupRepository groupRepository;

	@Override
	public List<GroupDTO> getAllGroups() throws AppException {
		List<GroupDTO> groupList = new ArrayList<GroupDTO>();
		for (GroupEntity groupEntity : groupRepository.findAll()) {
			GroupDTO groupDTO =  ModelEntityMapper.converEntityToModel(groupEntity, GroupDTO.class);
			groupList.add(groupDTO);
		}
		return groupList;
	}

}
