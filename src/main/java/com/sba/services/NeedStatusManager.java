package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.NeedStatusEntity;
import com.sba.models.entities.NeedStatusEntityCreation;

/**
 * need status interface repository
 * 
 */
public interface NeedStatusManager {

	/**
	* get all needStatuses
	* 
	* @param params
	* @return List<NeedStatusEntity>
	* @throws Exception
	*/
	public List<NeedStatusEntity> getAllNeedStatuses(@NotNull Map<String, Object> params) throws Exception; 

	/**
	* get one needStatus
	* 
	* @param id
	* @return NeedStatusEntity
	* @throws Exception
	*/
	public NeedStatusEntity get(Integer id) throws Exception; 

	/**
	* create needStatus
	* 
	* @param needStatus
	* @return NeedStatusEntity
	* @throws Exception
	*/
	public NeedStatusEntity create(NeedStatusEntityCreation needStatus) throws Exception;

	/**
	* update  needStatus
	* 
	* @param id, needStatus
	* @return NeedStatusEntity
	* @throws Exception
	*/
	public NeedStatusEntity update(Integer id, NeedStatusEntity needStatus) throws Exception;

	/**
	* delete needStatus
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
	public void delete(Integer id) throws Exception;
	
}
