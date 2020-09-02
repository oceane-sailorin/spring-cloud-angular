package com.sba.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.adapters.repositories.ClientRepository;
import com.sba.adapters.repositories.NeedRepository;
import com.sba.adapters.repositories.NeedStatusRepository;
import com.sba.adapters.repositories.PriorityRepository;
import com.sba.adapters.repositories.UserRepository;
import com.sba.models.entities.ClientEntity;
import com.sba.models.entities.NeedEntity;
import com.sba.models.entities.NeedEntityCreation;
import com.sba.models.entities.NeedStatusEntity;
import com.sba.models.entities.PriorityEntity;
import com.sba.models.entities.UserEntity;

/**
 * need service
 * 
 */
@Service
public class NeedManagerImpl implements NeedManager{

	  @Autowired
	   NeedRepository needRepository;
	  
	  @Autowired
	   UserRepository userRepository;
	  
	  @Autowired
	   ClientRepository clientRepository;
	  
	  @Autowired
	   NeedStatusRepository needStatusRepository;
	  
	  @Autowired
	   PriorityRepository priorityRepository;	 
	  
	  @Autowired
	  FileStorageManager fileStorageManager;
	    
		/**
		* get all needs
		* 
		* @param params
		* @return List<NeedEntity>
		* @throws Exception
		*/   
	   public List<NeedEntity> getAllNeeds(@NotNull Map<String, Object> params) throws Exception  {	   
		  
		   List<NeedEntity> needs = new ArrayList<>();
		   try {
		       List<NeedEntity> entities = needRepository.getAll();
			   for (NeedEntity need : entities) {	   	    		
				   	needs.add(need);
			   } 
		   } catch (Exception e) {        
	             throw new Exception("error.need.get");
	       }			   
	       return needs;
	      
	   }
	 
		/**
		* get one need
		* 
		* @param id
		* @return NeedEntity
		* @throws Exception
		*/
	   public NeedEntity get(Integer id) throws Exception {
		   return needRepository.findById(id);
	   }

		/**
		* create need
		* 
		* @param need
		* @return NeedEntity
		* @throws Exception
		*/
	   public int create(NeedEntityCreation need) throws Exception {	
		   NeedEntity needEntity = new NeedEntity();
		   try {
			   needEntity.setTitle(need.getTitle());	
			   NeedStatusEntity status = needStatusRepository.findById(need.getStatus());
			   needEntity.setStatus(status);
			   PriorityEntity priority = priorityRepository.findById(need.getPriority());
			   needEntity.setPriority(priority);
			   needEntity.setPoste(need.getPoste());
			   needEntity.setDepartement(need.getDepartement());
			   needEntity.setDescription(need.getDescription());
		
			   UserEntity commercial = userRepository.findById(need.getCommercial());		  
			   needEntity.setCommercial(commercial);
			   ClientEntity client = clientRepository.findById(need.getClient());		  
			   needEntity.setClient(client);
			  
			   needEntity.setDateCreation(new Date());
			   needEntity.setDateNeed(need.getDateNeed());
			   needEntity.setDateEnd(need.getDateEnd());
			   
			   needEntity.setKeywords(need.getKeywords());
			   
		   } catch (Exception e) {        
	             throw new Exception("error.need.create");
	       }		 
		   NeedEntity needy = needRepository.create(needEntity);		   
		   return needy.getId();
	   }

		/**
		* update need
		* 
		* @param id, need
		* @return NeedEntity
		* @throws Exception
		*/
	   public NeedEntity update(Integer id, NeedEntityCreation need) throws Exception {	
		   NeedEntity needEntity = needRepository.findById(id);
		   
		   try {
			   
			   needEntity.setTitle(need.getTitle());	
			   NeedStatusEntity status = needStatusRepository.findById(need.getStatus());
			   needEntity.setStatus(status);
			   PriorityEntity priority = priorityRepository.findById(need.getPriority());
			   needEntity.setPriority(priority);
			   needEntity.setPoste(need.getPoste());
			   needEntity.setDepartement(need.getDepartement());
			   needEntity.setDescription(need.getDescription());
		
			   UserEntity commercial = userRepository.findById(need.getCommercial());		  
			   needEntity.setCommercial(commercial);
			   ClientEntity client = clientRepository.findById(need.getClient());		  
			   needEntity.setClient(client);
			  
			   needEntity.setDateCreation(need.getDateCreation());
			   needEntity.setDateNeed(need.getDateNeed());
			   needEntity.setDateEnd(need.getDateEnd());
			   
			   needEntity.setKeywords(need.getKeywords());
			   
			   
		   } catch (Exception e) {        
	             throw new Exception("error.need.create");
	       }		 
		   return needRepository.update(needEntity);
		}
	   

		/**
		* update file
		* 
		* @param id, filename
		* @return NeedEntity
		* @throws Exception
		*/
	   public NeedEntity updateFile(Integer id, String fileName) throws Exception {	
		   NeedEntity entity = needRepository.findById(id);		   
		   try {			   
			   entity.setFilename(fileName);	
			 		   
		   } catch (Exception e) {        
	             throw new Exception("error.need.create");
	       }		 
		   return needRepository.update(entity);
		}
		/**
		* delete need
		* 
		* @param id
		* @return 
		* @throws Exception
		*/
	   public void delete(Integer id) throws Exception {	
		   NeedEntity entity = needRepository.findById(id);		   
		   try {
			   if(entity.getFilename() != null) {
				   fileStorageManager.delete(entity.getFilename());
			   }
			   needRepository.delete(id);			   
		   } catch (Exception e) {        
	             throw new Exception("error.need.delete");
	       }	
		}
	  

		/**
		* get all needs by client naming
		* 
		* @param params
		* @return List<NeedEntity>
		* @throws Exception
		*/   
	   public List<NeedEntity> getAllNeedsByClientNaming(@NotNull Map<String, Object> params) throws Exception  {	   
		  
		   List<NeedEntity> needs = new ArrayList<>();
		   try {
		       List<NeedEntity> entities = needRepository.getAllByClientNaming();
			   for (NeedEntity need : entities) {	   	    		
				   	needs.add(need);
			   } 
		   } catch (Exception e) {        
	             throw new Exception("error.need.get");
	       }			   
	       return needs;
	      
	   }
	   
}
