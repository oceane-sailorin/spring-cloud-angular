package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.CandidateEntity;
import com.sba.models.entities.CandidateEntityCreation;
import com.sba.models.entities.CandidateEntityStatusDateUpdate;
import com.sba.models.entities.CandidateEntityStatusUpdate;

/**
 * candidate interface repository
 * 
 */
public interface CandidateManager {

	/**
	* get all candidates
	* 
	* @param params
	* @return List<CandidateEntity>
	* @throws Exception
	*/
	public List<CandidateEntity> getAllCandidates(@NotNull Map<String, Object> params) throws Exception; 

	/**
	* get one candidate
	* 
	* @param id
	* @return CandidateEntity
	* @throws Exception
	*/
	public CandidateEntity get(Integer id) throws Exception; 

	/**
	* create candidate
	* 
	* @param candidate
	* @return CandidateEntity
	* @throws Exception
	*/
	public int create(CandidateEntityCreation candidate) throws Exception;

	/**
	* update  candidate
	* 
	* @param id, candidate
	* @return CandidateEntity
	* @throws Exception
	*/
	public CandidateEntity update(Integer id, CandidateEntityCreation candidate) throws Exception;

	
	/**
	* update  file
	* 
	* @param id, name
	* @return CandidateEntity
	* @throws Exception
	*/
	public CandidateEntity updateFile(Integer id, String fileName) throws Exception;

	
	/**
	* update  candidate status
	* 
	* @param id, candidate
	* @return CandidateEntityStatusUpdate
	* @throws Exception
	*/
	public CandidateEntity updateStatus(Integer id, CandidateEntityStatusUpdate candidate) throws Exception;

	
	/**
	* update  candidate status date 
	* 
	* @param id, candidate, date
	* @return CandidateEntityStatusDateUpdate
	* @throws Exception
	*/
	public CandidateEntity updateStatusDate(Integer id, CandidateEntityStatusDateUpdate candidate) throws Exception;

	
	
	/**
	* delete candidate
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
	public void delete(Integer id) throws Exception;
	
}
