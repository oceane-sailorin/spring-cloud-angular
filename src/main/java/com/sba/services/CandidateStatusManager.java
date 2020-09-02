package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.CandidateEntity;
import com.sba.models.entities.CandidateStatusEntity;
import com.sba.models.entities.CandidateStatusEntityCreation;

/**
 * candidate status interface repository
 * 
 */
public interface CandidateStatusManager {

	/**
	* get all candidateStatuses
	* 
	* @param params
	* @return List<CandidateStatusEntity>
	* @throws Exception
	*/
	public List<CandidateStatusEntity> getAllCandidateStatuses(@NotNull Map<String, Object> params) throws Exception; 

	/**
	* get one candidateStatus
	* 
	* @param id
	* @return CandidateStatusEntity
	* @throws Exception
	*/
	public CandidateStatusEntity get(Integer id) throws Exception; 

	/**
	* create candidateStatus
	* 
	* @param candidateStatus
	* @return CandidateStatusEntity
	* @throws Exception
	*/
	public CandidateStatusEntity create(CandidateStatusEntityCreation candidateStatus) throws Exception;

	/**
	* update  candidateStatus
	* 
	* @param id, candidateStatus
	* @return CandidateStatusEntity
	* @throws Exception
	*/
	public CandidateStatusEntity update(Integer id, CandidateStatusEntity candidateStatus) throws Exception;

	/**
	* delete candidateStatus
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
	public void delete(Integer id) throws Exception;
	
	/**
	* get all candidates by status
	* 
	* @param params
	* @return Map<CandidateStatusEntity,List<CandidateEntity>
	* @throws Exception
	*/
	public Map<Integer,Map<String,List<CandidateEntity>>> getAllCandidatesByStatus(@NotNull Map<String, Object> params) throws Exception; 
	
	
}
