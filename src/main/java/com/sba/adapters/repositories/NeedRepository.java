package com.sba.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.NeedDAO;
import com.sba.models.entities.NeedEntity;

import lombok.NonNull;

/**
 * need repository
 * 
 */
@Repository
public class NeedRepository {

	@Autowired
	private NeedDAO needDAO;

	 /**
     * get all needs
     * 
     * @param 
     * @return List<NeedEntity>
     * @throws 
     */
	public List<NeedEntity> getAll() 
    {                
	   return needDAO.findAll();
	}
	
	 /**
     * get all needs by client naming
     * 
     * @param 
     * @return List<NeedEntity>
     * @throws 
     */	
	 public List<NeedEntity> getAllByClientNaming(){
		return needDAO.getAllByClientNaming();
	 }

	 /**
     * find need by id
     * 
     * @param id
     * @return NeedEntity
     * @throws 
     */
	public NeedEntity findById(@NonNull Integer id) {
		NeedEntity needEntity= null;
		Optional<NeedEntity> value = needDAO.findById(id);
		needEntity = value.isPresent() ? value.get(): null;
		return needEntity;
	}

	 /**
     * create need
     * 
     * @param need
     * @return NeedEntity
     * @throws 
     */
	public NeedEntity create(NeedEntity need) {
		return needDAO.saveAndFlush(need);
	}

	 /**
     * update need
     * 
     * @param need
     * @return NeedEntity
     * @throws 
     */
	public NeedEntity update(NeedEntity need) {
		return needDAO.saveAndFlush(need);
	}
	
	 /**
     * delete need
     * 
     * @param id
     * @return 
     * @throws 
     */
	public void delete(Integer id) {
		needDAO.deleteById(id);
	}
}
