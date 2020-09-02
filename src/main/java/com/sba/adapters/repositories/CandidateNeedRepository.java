package com.sba.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.CandidateNeedDAO;
import com.sba.models.entities.CandidateNeedEntity;

import lombok.NonNull;

/**
 * candidateNeed need repository
 * 
 */
@Repository
public class CandidateNeedRepository {

	@Autowired
	private CandidateNeedDAO candidateNeedDAO;

	 /**
     * get all candidateNeeds
     * 
     * @param 
     * @return List<CandidateNeedEntity>
     * @throws 
     */
	public List<CandidateNeedEntity> getAll() 
    {                
	   return candidateNeedDAO.findAll();
	}

	 /**
     * find candidateNeed by id
     * 
     * @param id
     * @return CandidateNeedEntity
     * @throws 
     */
	public CandidateNeedEntity findById(@NonNull Integer id) {
		CandidateNeedEntity candidateNeedEntity= null;
		Optional<CandidateNeedEntity> value = candidateNeedDAO.findById(id);
		candidateNeedEntity = value.isPresent() ? value.get(): null;
		return candidateNeedEntity;
	}

	 /**
     * create candidateNeed
     * 
     * @param candidateNeed
     * @return CandidateNeedEntity
     * @throws 
     */
	public CandidateNeedEntity create(CandidateNeedEntity candidateNeed) {
		return candidateNeedDAO.saveAndFlush(candidateNeed);
	}

	 /**
     * update candidateNeed
     * 
     * @param candidateNeed
     * @return CandidateNeedEntity
     * @throws 
     */
	public CandidateNeedEntity update(CandidateNeedEntity candidateNeed) {
		return candidateNeedDAO.saveAndFlush(candidateNeed);
	}
	
	 /**
     * delete candidateNeed
     * 
     * @param id
     * @return 
     * @throws 
     */
	public void delete(Integer id) {
		candidateNeedDAO.deleteById(id);
	}
	
	/**
	 * get by status
	 * @param status
	 * @return List<CandidateNeedEntity>
	 */
	 public List<CandidateNeedEntity> findByCandidateNeedStatus(Integer status){
		 return candidateNeedDAO.findByNeedCandidateStatus(status);
	 }

	/**
	 * get by need
	 * @param need
	 * @return List<CandidateNeedEntity>
	 */
	 public List<CandidateNeedEntity> findByNeed(Integer need){
		 return candidateNeedDAO.findByNeed(need);
	 }
	 
	/**
	 * get by need and status
	 * @param need, status
	 * @return List<CandidateNeedEntity>
	 */
	 public List<CandidateNeedEntity> findByNeedAndStatus(Integer need, Integer status){
		 return candidateNeedDAO.findByNeedAndStatus(need, status);
	 }	 
	
}

