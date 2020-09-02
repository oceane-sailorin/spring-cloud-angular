package com.sba.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.SourceDAO;
import com.sba.models.entities.SourceEntity;

import lombok.NonNull;

/**
 * source repository
 * 
 */
@Repository
public class SourceRepository {
	@Autowired
	private SourceDAO sourceDAO;

	 /**
     * get all sources
     * 
     * @param 
     * @return List<SourceEntity>
     * @throws 
     */
	public List<SourceEntity> getAll() 
    {                
	   return sourceDAO.findAll();
	}

	 /**
     * find source by id
     * 
     * @param id
     * @return SourceEntity
     * @throws 
     */
	public SourceEntity findById(@NonNull Integer id) {
		SourceEntity sourceEntity= null;
		Optional<SourceEntity> value = sourceDAO.findById(id);
		sourceEntity = value.isPresent() ? value.get(): null;
		return sourceEntity;

	}

	 /**
     * create source
     * 
     * @param source
     * @return SourceEntity
     * @throws 
     */
	public SourceEntity create(SourceEntity source) {
		return sourceDAO.saveAndFlush(source);
	}

	 /**
     * update source
     * 
     * @param source
     * @return SourceEntity
     * @throws 
     */
	public SourceEntity update(SourceEntity source) {
		return sourceDAO.saveAndFlush(source);
	}
	
	 /**
     * delete source
     * 
     * @param id
     * @return 
     * @throws 
     */
	public void delete(Integer id) {
		sourceDAO.deleteById(id);
	}
}
