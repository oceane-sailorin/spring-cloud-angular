package com.sba.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.NeedCandidateStatusDAO;
import com.sba.models.entities.NeedCandidateStatusEntity;

import lombok.NonNull;

/**
 * need candidate status repository
 * 
 */
@Repository
public class NeedCandidateStatusRepository {

	@Autowired
	private NeedCandidateStatusDAO needCandidateStatusDAO;

	 /**
     * get all needCandidateStatuses
     * 
     * @param 
     * @return List<NeedCandidateStatusEntity>
     * @throws 
     */
	public List<NeedCandidateStatusEntity> getAll() 
    {                
	   return needCandidateStatusDAO.findAllByOrder();
	}

	 /**
     * find needCandidateStatus by id
     * 
     * @param id
     * @return NeedCandidateStatusEntity
     * @throws 
     */
	public NeedCandidateStatusEntity findById(@NonNull Integer id) {
		NeedCandidateStatusEntity needCandidateStatusEntity= null;
		Optional<NeedCandidateStatusEntity> value = needCandidateStatusDAO.findById(id);
		needCandidateStatusEntity = value.isPresent() ? value.get(): null;
		return needCandidateStatusEntity;
	}

	 /**
     * create needCandidateStatus
     * 
     * @param needCandidateStatus
     * @return NeedCandidateStatusEntity
     * @throws 
     */
	public NeedCandidateStatusEntity create(NeedCandidateStatusEntity needCandidateStatus) {
		return needCandidateStatusDAO.saveAndFlush(needCandidateStatus);
	}

	 /**
     * update needCandidateStatus
     * 
     * @param needCandidateStatus
     * @return NeedCandidateStatusEntity
     * @throws 
     */
	public NeedCandidateStatusEntity update(NeedCandidateStatusEntity needCandidateStatus) {
		return needCandidateStatusDAO.saveAndFlush(needCandidateStatus);
	}
	
	 /**
     * delete needCandidateStatus
     * 
     * @param id
     * @return 
     * @throws 
     */
	public void delete(Integer id) {
		needCandidateStatusDAO.deleteById(id);
	}
}
