package com.sba.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.NeedStatusDAO;
import com.sba.models.entities.NeedStatusEntity;

import lombok.NonNull;

/**
 * need status repository
 * 
 */
@Repository
public class NeedStatusRepository {

	@Autowired
	private NeedStatusDAO needStatusDAO;

	 /**
     * get all needStatuses
     * 
     * @param 
     * @return List<NeedStatusEntity>
     * @throws 
     */
	public List<NeedStatusEntity> getAll() 
    {                
	   return needStatusDAO.findAll();
	}

	 /**
     * find needStatus by id
     * 
     * @param id
     * @return NeedStatusEntity
     * @throws 
     */
	public NeedStatusEntity findById(@NonNull Integer id) {
		NeedStatusEntity needStatusEntity= null;
		Optional<NeedStatusEntity> value = needStatusDAO.findById(id);
		needStatusEntity = value.isPresent() ? value.get(): null;
		return needStatusEntity;
	}

	 /**
     * create needStatus
     * 
     * @param needStatus
     * @return NeedStatusEntity
     * @throws 
     */
	public NeedStatusEntity create(NeedStatusEntity needStatus) {
		return needStatusDAO.saveAndFlush(needStatus);
	}

	 /**
     * update needStatus
     * 
     * @param needStatus
     * @return NeedStatusEntity
     * @throws 
     */
	public NeedStatusEntity update(NeedStatusEntity needStatus) {
		return needStatusDAO.saveAndFlush(needStatus);
	}
	
	 /**
     * delete needStatus
     * 
     * @param id
     * @return 
     * @throws 
     */
	public void delete(Integer id) {
		needStatusDAO.deleteById(id);
	}
	

}
