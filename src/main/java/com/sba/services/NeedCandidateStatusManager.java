package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.CandidateNeedEntity;
import com.sba.models.entities.NeedCandidateStatusEntity;
import com.sba.models.entities.NeedCandidateStatusEntityCreation;

/**
 * need candidate status interface repository
 * 
 */
public interface NeedCandidateStatusManager {

	/**
	* get all needCandidateStatuses
	* 
	* @param params
	* @return List<NeedCandidateStatusEntity>
	* @throws Exception
	*/
	public List<NeedCandidateStatusEntity> getAllNeedCandidateStatuses(@NotNull Map<String, Object> params) throws Exception; 

	/**
	* get one needCandidateStatus
	* 
	* @param id
	* @return NeedCandidateStatusEntity
	* @throws Exception
	*/
	public NeedCandidateStatusEntity get(Integer id) throws Exception; 

	/**
	* create needCandidateStatus
	* 
	* @param needCandidateStatus
	* @return NeedCandidateStatusEntity
	* @throws Exception
	*/
	public NeedCandidateStatusEntity create(NeedCandidateStatusEntityCreation needCandidateStatus) throws Exception;

	/**
	* update  needCandidateStatus
	* 
	* @param id, needCandidateStatus
	* @return NeedCandidateStatusEntity
	* @throws Exception
	*/
	public NeedCandidateStatusEntity update(Integer id, NeedCandidateStatusEntity needCandidateStatus) throws Exception;

	/**
	* delete needCandidateStatus
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
	public void delete(Integer id) throws Exception;

	
	/**
	* get all candidates_needs by status
	* 
	* @param params
	* @return Map<NeedCandidateStatusEntity,List<CandidateNeedEntity>
	* @throws Exception
	*/
	public Map<Integer,Map<String,List<CandidateNeedEntity>>> getAllCandidatesNeedsByStatus(@NotNull Map<String, Object> params) throws Exception; 
	

	/**
	* get all candidates_needs by status and need
	* 
	* @param params
	* @return Map<Integer,Map<String,Map<Integer, Map<String,List<CandidateNeedEntity>>>>>
	* @throws Exception
	*/
	public Map<Integer,Map<String,Map<Integer, Map<String,List<CandidateNeedEntity>>>>> getAllCandidatesNeedsByNeedByStatus(@NotNull Map<String, Object> params) throws Exception;   

}
