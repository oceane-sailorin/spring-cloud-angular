package com.sba.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.PriorityDAO;
import com.sba.models.entities.PriorityEntity;

import lombok.NonNull;

/**
 * priority repository
 * 
 */
@Repository
public class PriorityRepository {

	@Autowired
	private PriorityDAO priorityDAO;

	 /**
     * get all priorities
     * 
     * @param 
     * @return List<PriorityEntity>
     * @throws 
     */
	public List<PriorityEntity> getAll() 
    {                
	   return priorityDAO.findAll();
	}

	 /**
     * find Priority by id
     * 
     * @param id
     * @return PriorityEntity
     * @throws 
     */
	public PriorityEntity findById(@NonNull Integer id) {
		PriorityEntity priorityEntity= null;
		Optional<PriorityEntity> value = priorityDAO.findById(id);
		priorityEntity = value.isPresent() ? value.get(): null;
		return priorityEntity;
	}

	 /**
     * create priority
     * 
     * @param priority
     * @return PriorityEntity
     * @throws 
     */
	public PriorityEntity create(PriorityEntity priority) {
		return priorityDAO.saveAndFlush(priority);
	}

	 /**
     * update priority
     * 
     * @param priority
     * @return PriorityEntity
     * @throws 
     */
	public PriorityEntity update(PriorityEntity priority) {
		return priorityDAO.saveAndFlush(priority);
	}
	
	 /**
     * delete priority
     * 
     * @param id
     * @return 
     * @throws 
     */
	public void delete(Integer id) {
		priorityDAO.deleteById(id);
	}
	
}
