package com.sba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.adapters.repositories.SourceRepository;
import com.sba.models.entities.SourceEntity;


/**
 * source service
 * 
 */
@Service
public class SourceManagerImpl implements SourceManager{

	  @Autowired
	   SourceRepository sourceRepository;
	    
		/**
		* get all sources
		* 
		* @param params
		* @return List<SourceEntity>
		* @throws Exception
		*/   
	   public List<SourceEntity> getAllSources(@NotNull Map<String, Object> params) throws Exception  {	   
		  
		   List<SourceEntity> sources = new ArrayList<>();
		   try {
		       List<SourceEntity> entities = sourceRepository.getAll();
			   for (SourceEntity source : entities) {	   	    		
				   	sources.add(source);
			   } 
		   } catch (Exception e) {        
	             throw new Exception("error.source.get");
	       }			   
	       return sources;
	      
	   }
	 
		/**
		* get one source
		* 
		* @param id
		* @return SourceEntity
		* @throws Exception
		*/
	   public SourceEntity get(Integer id) throws Exception {
		   return sourceRepository.findById(id);
	   }

		/**
		* create source
		* 
		* @param source
		* @return SourceEntity
		* @throws Exception
		*/
	   public SourceEntity create(SourceEntity source) throws Exception {	
		   SourceEntity sourceEntity = new SourceEntity();
		   try {
			   sourceEntity.setNaming(source.getNaming());	 
		   } catch (Exception e) {        
	             throw new Exception("error.source.create");
	       }   
		   return sourceRepository.create(sourceEntity);
	   }

		/**
		* update source
		* 
		* @param id, source
		* @return SourceEntity
		* @throws Exception
		*/
	   public SourceEntity update(Integer id, SourceEntity source) throws Exception {	
		   return sourceRepository.update(source);
		}
	 
		/**
		* delete source
		* 
		* @param id
		* @return 
		* @throws Exception
		*/
	   public void delete(Integer id) throws Exception {	
		   sourceRepository.delete(id);
		}
	  
	   
}
