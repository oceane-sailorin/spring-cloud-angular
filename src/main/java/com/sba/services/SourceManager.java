package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.SourceEntity;

/**
 * source interface repository
 * 
 */
public interface SourceManager {

	/**
	* get all sources
	* 
	* @param params
	* @return List<SourceEntity>
	* @throws Exception
	*/
	public List<SourceEntity> getAllSources(@NotNull Map<String, Object> params) throws Exception; 

	/**
	* get one source
	* 
	* @param id
	* @return SourceEntity
	* @throws Exception
	*/
	public SourceEntity get(Integer id) throws Exception; 

	/**
	* create source
	* 
	* @param source
	* @return SourceEntity
	* @throws Exception
	*/
	public SourceEntity create(SourceEntity source) throws Exception;

	/**
	* update  source
	* 
	* @param id, source
	* @return SourceEntity
	* @throws Exception
	*/
	public SourceEntity update(Integer id, SourceEntity source) throws Exception;

	/**
	* delete source
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
	public void delete(Integer id) throws Exception;
}
