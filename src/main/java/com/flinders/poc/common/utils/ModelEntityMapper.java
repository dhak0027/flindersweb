package com.flinders.poc.common.utils;

import org.modelmapper.ModelMapper;

/**
 * Common Utility for Model2Entity and Entity2Model conversion
 * @author mswahithali
 */

public class ModelEntityMapper {
	
	public static <T> T converModelToEntity(Object modelObject, Class<T> entityName){
		try{
			
			if(modelObject==null)return null;
			
			ModelMapper modelMapper = new ModelMapper();
			return modelMapper.map(modelObject, entityName);
			
		}
		catch(Exception e){
			//logger.error("Error while Conver ObjectMapper for Class "+entityName.getName(), e);
			return null;
		}
	
	}
	
	public static <T> T converEntityToModel(Object entityObject,Class<T> modelName){
		try{
			
			if(entityObject==null)return null;
			
			ModelMapper modelMapper = new ModelMapper();
			return modelMapper.map(entityObject, modelName);
			
		}
		catch(Exception e){
			//logger.error("Error while Conver ObjectMapper for Class "+modelName.getName(), e);
			return null;
		}
	
	}
	
}
