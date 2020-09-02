package com.sba.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.CandidateStatusDAO;
import com.sba.models.entities.CandidateStatusEntity;

import lombok.NonNull;

/**
 * candidate status repository
 * 
 */
@Repository
public class CandidateStatusRepository {

	@Autowired
	private CandidateStatusDAO candidateStatusDAO;

	 /**
     * get all candidateStatuses
     * 
     * @param 
     * @return List<CandidateStatusEntity>
     * @throws 
     */
	public List<CandidateStatusEntity> getAll() 
    {                
	   return candidateStatusDAO.findAllByOrder();
	}

	 /**
     * find candidateStatus by id
     * 
     * @param id
     * @return CandidateStatusEntity
     * @throws 
     */
	public CandidateStatusEntity findById(@NonNull Integer id) {
		CandidateStatusEntity candidateStatusEntity= null;
		Optional<CandidateStatusEntity> value = candidateStatusDAO.findById(id);
		candidateStatusEntity = value.isPresent() ? value.get(): null;
		return candidateStatusEntity;
	}

	 /**
     * create candidateStatus
     * 
     * @param candidateStatus
     * @return CandidateStatusEntity
     * @throws 
     */
	public CandidateStatusEntity create(CandidateStatusEntity candidateStatus) {
		return candidateStatusDAO.saveAndFlush(candidateStatus);
	}

	 /**
     * update candidateStatus
     * 
     * @param candidateStatus
     * @return CandidateStatusEntity
     * @throws 
     */
	public CandidateStatusEntity update(CandidateStatusEntity candidateStatus) {
		return candidateStatusDAO.saveAndFlush(candidateStatus);
	}
	
	 /**
     * delete candidateStatus
     * 
     * @param id
     * @return 
     * @throws 
     */
	public void delete(Integer id) {
		candidateStatusDAO.deleteById(id);
	}
	
	
}
