package com.sba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.adapters.repositories.PriorityRepository;
import com.sba.models.entities.PriorityEntity;
import com.sba.models.entities.PriorityEntityCreation;

/**
 * priority service
 * 
 */
@Service
public class PriorityManagerImpl implements PriorityManager{

	  @Autowired
	   PriorityRepository priorityRepository;
	    
		/**
		* get all priorities
		* 
		* @param params
		* @return List<PriorityEntity>
		* @throws Exception
		*/   
	   public List<PriorityEntity> getAllPriorities(@NotNull Map<String, Object> params) throws Exception  {	   
		  
		   List<PriorityEntity> priorityes = new ArrayList<>();
		   try {
		       List<PriorityEntity> entities = priorityRepository.getAll();
			   for (PriorityEntity priority : entities) {	   	    		
				   	priorityes.add(priority);
			   }  
		   } catch (Exception e) {        
	             throw new Exception("error.priority.get");
	       }			   
	       return priorityes;
	      
	   }
	 
		/**
		* get one priority
		* 
		* @param id
		* @return PriorityEntity
		* @throws Exception
		*/
	   public PriorityEntity get(Integer id) throws Exception {
		   return priorityRepository.findById(id);
	   }

		/**
		* create priority
		* 
		* @param priority
		* @return PriorityEntity
		* @throws Exception
		*/
	   public PriorityEntity create(PriorityEntityCreation priority) throws Exception {	
		   PriorityEntity priorityEntity = new PriorityEntity();
		   try {
			   priorityEntity.setCode(priority.getCode());
			   priorityEntity.setNaming(priority.getNaming());	
		   } catch (Exception e) {        
	             throw new Exception("error.priority.create");
	       }			   
		   return priorityRepository.create(priorityEntity);
	   }

		/**
		* update priority
		* 
		* @param id, priority
		* @return PriorityEntity
		* @throws Exception
		*/
	   public PriorityEntity update(Integer id, PriorityEntity priority) throws Exception {	
		   return priorityRepository.update(priority);
		}
	 
		/**
		* delete priority
		* 
		* @param id
		* @return 
		* @throws Exception
		*/
	   public void delete(Integer id) throws Exception {	
		   priorityRepository.delete(id);
		}
	  
	   
}
