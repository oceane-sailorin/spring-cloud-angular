package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.KeywordEntity;
import com.sba.models.entities.KeywordEntityCreation;

/**
 * keyword interface repository
 * 
 */
public interface KeywordManager {

	/**
	* get all keywords
	* 
	* @param params
	* @return List<KeywordEntity>
	* @throws Exception
	*/
	public List<KeywordEntity> getAllKeywords(@NotNull Map<String, Object> params) throws Exception; 

	/**
	* get one keyword
	* 
	* @param id
	* @return KeywordEntity
	* @throws Exception
	*/
	public KeywordEntity get(Integer id) throws Exception; 

	/**
	* create keyword
	* 
	* @param keyword
	* @return KeywordEntity
	* @throws Exception
	*/
	public KeywordEntity create(KeywordEntityCreation keyword) throws Exception;

	/**
	* update  keyword
	* 
	* @param id, keyword
	* @return KeywordEntity
	* @throws Exception
	*/
	public KeywordEntity update(Integer id, KeywordEntity keyword) throws Exception;

	/**
	* delete keyword
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
	public void delete(Integer id) throws Exception;
}
