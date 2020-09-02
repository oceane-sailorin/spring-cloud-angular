package com.sba.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.CandidateDAO;
import com.sba.models.entities.CandidateEntity;

import lombok.NonNull;

/**
 * candidate repository
 * 
 */
@Repository
public class CandidateRepository {

	@Autowired
	private CandidateDAO candidateDAO;

	 /**
     * get all candidates
     * 
     * @param 
     * @return List<CandidateEntity>
     * @throws 
     */
	public List<CandidateEntity> getAll() 
    {                
	   return candidateDAO.findAll();
	}

	 /**
     * find candidate by id
     * 
     * @param id
     * @return CandidateEntity
     * @throws 
     */
	public CandidateEntity findById(@NonNull Integer id) {
		CandidateEntity candidateEntity= null;
		Optional<CandidateEntity> value = candidateDAO.findById(id);
		candidateEntity = value.isPresent() ? value.get(): null;
		return candidateEntity;
	}

	 /**
     * create candidate
     * 
     * @param candidate
     * @return CandidateEntity
     * @throws 
     */
	public CandidateEntity create(CandidateEntity candidate) {
		return candidateDAO.saveAndFlush(candidate);
	}

	 /**
     * update candidate
     * 
     * @param candidate
     * @return CandidateEntity
     * @throws 
     */
	public CandidateEntity update(CandidateEntity candidate) {
		return candidateDAO.saveAndFlush(candidate);
	}
	
	 /**
     * delete candidate
     * 
     * @param id
     * @return 
     * @throws 
     */
	public void delete(Integer id) {
		candidateDAO.deleteById(id);
	}
	
	/**
	 * get by status
	 * @param status
	 * @return List<CandidateEntity>
	 */
	 public List<CandidateEntity> findByCandidateStatus(Integer status){
		 return candidateDAO.findByCandidateStatus(status);
	 }
}
