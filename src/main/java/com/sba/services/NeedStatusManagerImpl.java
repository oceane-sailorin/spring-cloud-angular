package com.sba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.adapters.repositories.NeedStatusRepository;
import com.sba.models.entities.NeedStatusEntity;
import com.sba.models.entities.NeedStatusEntityCreation;

/**
 * need status service
 * 
 */
@Service
public class NeedStatusManagerImpl implements NeedStatusManager{

	  @Autowired
	   NeedStatusRepository needStatusRepository;
	    
		/**
		* get all needStatuses
		* 
		* @param params
		* @return List<NeedStatusEntity>
		* @throws Exception
		*/   
	   public List<NeedStatusEntity> getAllNeedStatuses(@NotNull Map<String, Object> params) throws Exception  {	   
		  
		   List<NeedStatusEntity> needStatuses = new ArrayList<>();
		   try {
		       List<NeedStatusEntity> entities = needStatusRepository.getAll();
			   for (NeedStatusEntity needStatus : entities) {	   	    		
				   	needStatuses.add(needStatus);
			   }  
		   } catch (Exception e) {        
	             throw new Exception("error.needstatus.get");
	       }			   
	       return needStatuses;
	      
	   }
	 
		/**
		* get one needStatus
		* 
		* @param id
		* @return NeedStatusEntity
		* @throws Exception
		*/
	   public NeedStatusEntity get(Integer id) throws Exception {
		   return needStatusRepository.findById(id);
	   }

		/**
		* create needStatus
		* 
		* @param needStatus
		* @return NeedStatusEntity
		* @throws Exception
		*/
	   public NeedStatusEntity create(NeedStatusEntityCreation needStatus) throws Exception {	
		   NeedStatusEntity needStatusEntity = new NeedStatusEntity();
		   try {
			   needStatusEntity.setCode(needStatus.getCode());
			   needStatusEntity.setNaming(needStatus.getNaming());	
		   } catch (Exception e) {        
	             throw new Exception("error.needstatus.create");
	       }			   
		   return needStatusRepository.create(needStatusEntity);
	   }

		/**
		* update needStatus
		* 
		* @param id, needStatus
		* @return NeedStatusEntity
		* @throws Exception
		*/
	   public NeedStatusEntity update(Integer id, NeedStatusEntity needStatus) throws Exception {	
		   return needStatusRepository.update(needStatus);
		}
	 
		/**
		* delete needStatus
		* 
		* @param id
		* @return 
		* @throws Exception
		*/
	   public void delete(Integer id) throws Exception {	
		   needStatusRepository.delete(id);
		}
	  
	   
}
