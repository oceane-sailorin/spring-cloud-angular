package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.CandidateNeedEntity;
import com.sba.models.entities.CandidateNeedEntityCreation;
import com.sba.models.entities.CandidateNeedEntityStatusDateUpdate;
import com.sba.models.entities.CandidateNeedEntityStatusUpdate;

/**
 * candidateNeed interface repository
 * 
 */
public interface CandidateNeedManager {

	/**
	* get all candidateNeeds
	* 
	* @param params
	* @return List<CandidateNeedEntity>
	* @throws Exception
	*/
	public List<CandidateNeedEntity> getAllCandidateNeeds(@NotNull Map<String, Object> params) throws Exception; 

	/**
	* get one candidateNeed
	* 
	* @param id
	* @return CandidateNeedEntity
	* @throws Exception
	*/
	public CandidateNeedEntity get(Integer id) throws Exception; 

	/**
	* create candidateNeed
	* 
	* @param candidateNeed
	* @return CandidateNeedEntity
	* @throws Exception
	*/
	public int create(CandidateNeedEntityCreation candidateNeed) throws Exception;

	/**
	* update  candidateNeed
	* 
	* @param id, candidateNeed
	* @return CandidateNeedEntity
	* @throws Exception
	*/
	public CandidateNeedEntity update(Integer id, CandidateNeedEntityCreation candidateNeed) throws Exception;

	
	
	/**
	* update  candidateNeed status
	* 
	* @param id, candidateNeed
	* @return CandidateNeedEntityStatusUpdate
	* @throws Exception
	*/
	public CandidateNeedEntity updateStatus(Integer id, CandidateNeedEntityStatusUpdate candidateNeed) throws Exception;

	
	/**
	* update  candidateNeed status date 
	* 
	* @param id, candidateNeed, date
	* @return CandidateNeedEntityStatusDateUpdate
	* @throws Exception
	*/
	public CandidateNeedEntity updateStatusDate(Integer id, CandidateNeedEntityStatusDateUpdate candidateNeed) throws Exception;

	
	
	/**
	* delete candidateNeed
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
	public void delete(Integer id) throws Exception;
	
}
