package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.PriorityEntity;
import com.sba.models.entities.PriorityEntityCreation;

/**
 * priority interface repository
 * 
 */
public interface PriorityManager {

	/**
	* get all priorities
	* 
	* @param params
	* @return List<PriorityEntity>
	* @throws Exception
	*/
	public List<PriorityEntity> getAllPriorities(@NotNull Map<String, Object> params) throws Exception; 

	/**
	* get one priority
	* 
	* @param id
	* @return PriorityEntity
	* @throws Exception
	*/
	public PriorityEntity get(Integer id) throws Exception; 

	/**
	* create priority
	* 
	* @param priority
	* @return PriorityEntity
	* @throws Exception
	*/
	public PriorityEntity create(PriorityEntityCreation priority) throws Exception;

	/**
	* update  priority
	* 
	* @param id, priority
	* @return PriorityEntity
	* @throws Exception
	*/
	public PriorityEntity update(Integer id, PriorityEntity priority) throws Exception;

	/**
	* delete priority
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
	public void delete(Integer id) throws Exception;
	
}
