package com.sba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.adapters.repositories.KeywordRepository;
import com.sba.models.entities.KeywordEntity;
import com.sba.models.entities.KeywordEntityCreation;

/**
 * keyword service
 * 
 */
@Service
public class KeywordManagerImpl implements KeywordManager{

	  @Autowired
	   KeywordRepository keywordRepository;
	    
		/**
		* get all keywords
		* 
		* @param params
		* @return List<KeywordEntity>
		* @throws Exception
		*/   
	   public List<KeywordEntity> getAllKeywords(@NotNull Map<String, Object> params) throws Exception  {	   
		  
		   List<KeywordEntity> keywords = new ArrayList<>();
		   try {
		       List<KeywordEntity> entities = keywordRepository.getAll();
			   for (KeywordEntity keyword : entities) {	   	    		
				   	keywords.add(keyword);
			   }  
		   } catch (Exception e) {        
	             throw new Exception("error.keyword.get");
	       }			   
	       return keywords;
	      
	   }
	 
		/**
		* get one keyword
		* 
		* @param id
		* @return KeywordEntity
		* @throws Exception
		*/
	   public KeywordEntity get(Integer id) throws Exception {
		   return keywordRepository.findById(id);
	   }

		/**
		* create keyword
		* 
		* @param keyword
		* @return KeywordEntity
		* @throws Exception
		*/
	   public KeywordEntity create(KeywordEntityCreation keyword) throws Exception {	
		   KeywordEntity keywordEntity = new KeywordEntity();
		   try {
			   keywordEntity.setCode(keyword.getCode());
		   } catch (Exception e) {        
	             throw new Exception("error.keyword.create");
	       }			   
		   return keywordRepository.create(keywordEntity);
	   }

		/**
		* update keyword
		* 
		* @param id, keyword
		* @return KeywordEntity
		* @throws Exception
		*/
	   public KeywordEntity update(Integer id, KeywordEntity keyword) throws Exception {	
		   return keywordRepository.update(keyword);
		}
	 
		/**
		* delete keyword
		* 
		* @param id
		* @return 
		* @throws Exception
		*/
	   public void delete(Integer id) throws Exception {	
		   keywordRepository.delete(id);
		}
	  
	   
}
