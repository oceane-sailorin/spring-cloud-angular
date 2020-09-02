package com.sba.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.KeywordDAO;
import com.sba.models.entities.KeywordEntity;

import lombok.NonNull;

/**
 * keyword repository
 * 
 */
@Repository
public class KeywordRepository {
	
	@Autowired
	private KeywordDAO keywordDAO;

	 /**
     * get all keywords
     * 
     * @param 
     * @return List<KeywordEntity>
     * @throws 
     */
	public List<KeywordEntity> getAll() 
    {                
	   return keywordDAO.findAll();
	}

	 /**
     * find keyword by id
     * 
     * @param id
     * @return KeywordEntity
     * @throws 
     */
	public KeywordEntity findById(@NonNull Integer id) {
		KeywordEntity keywordEntity= null;
		Optional<KeywordEntity> value = keywordDAO.findById(id);
		keywordEntity = value.isPresent() ? value.get(): null;
		return keywordEntity;
	}

	 /**
     * create keyword
     * 
     * @param keyword
     * @return KeywordEntity
     * @throws 
     */
	public KeywordEntity create(KeywordEntity keyword) {
		return keywordDAO.saveAndFlush(keyword);
	}

	 /**
     * update keyword
     * 
     * @param keyword
     * @return KeywordEntity
     * @throws 
     */
	public KeywordEntity update(KeywordEntity keyword) {
		return keywordDAO.saveAndFlush(keyword);
	}
	
	 /**
     * delete keyword
     * 
     * @param id
     * @return 
     * @throws 
     */
	public void delete(Integer id) {
		keywordDAO.deleteById(id);
	}

}
