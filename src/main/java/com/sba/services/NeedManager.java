package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.NeedEntity;
import com.sba.models.entities.NeedEntityCreation;


/**
 * need interface repository
 * 
 */
public interface NeedManager {

	/**
	* get all needs
	* 
	* @param params
	* @return List<NeedEntity>
	* @throws Exception
	*/
	public List<NeedEntity> getAllNeeds(@NotNull Map<String, Object> params) throws Exception; 

	/**
	* get one need
	* 
	* @param id
	* @return NeedEntity
	* @throws Exception
	*/
	public NeedEntity get(Integer id) throws Exception; 

	/**
	* create need
	* 
	* @param need
	* @return NeedEntity
	* @throws Exception
	*/
	public int create(NeedEntityCreation need) throws Exception;

	/**
	* update  need
	* 
	* @param id, need
	* @return NeedEntity
	* @throws Exception
	*/
	public NeedEntity update(Integer id, NeedEntityCreation need) throws Exception;

	/**
	* update  file
	* 
	* @param id, name
	* @return NeedEntity
	* @throws Exception
	*/
	public NeedEntity updateFile(Integer id, String fileName) throws Exception;
	
	
	/**
	* delete need
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
	public void delete(Integer id) throws Exception;
	
	/**
	* get all needs by client naming
	* 
	* @param params
	* @return List<NeedEntity>
	* @throws Exception
	*/
	public List<NeedEntity> getAllNeedsByClientNaming(@NotNull Map<String, Object> params) throws Exception;
	
}
