package com.sba.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.adapters.repositories.CandidateNeedRepository;
import com.sba.adapters.repositories.NeedCandidateStatusRepository;
import com.sba.adapters.repositories.NeedRepository;
import com.sba.models.entities.CandidateNeedEntity;
import com.sba.models.entities.NeedCandidateStatusEntity;
import com.sba.models.entities.NeedCandidateStatusEntityCreation;
import com.sba.models.entities.NeedEntity;

/**
 * need status service
 * 
 */
@Service
public class NeedCandidateStatusManagerImpl implements NeedCandidateStatusManager{

	  @Autowired
	   NeedCandidateStatusRepository needCandidateStatusRepository;
	  
	  @Autowired
	  CandidateNeedRepository candidateNeedRepository;
	  
	  @Autowired
	  NeedRepository needRepository;
	    
		/**
		* get all needCandidateStatuses
		* 
		* @param params
		* @return List<NeedCandidateStatusEntity>
		* @throws Exception
		*/   
	   public List<NeedCandidateStatusEntity> getAllNeedCandidateStatuses(@NotNull Map<String, Object> params) throws Exception  {	   
		  
		   List<NeedCandidateStatusEntity> needCandidateStatuses = new ArrayList<>();
		   try {
		       List<NeedCandidateStatusEntity> entities = needCandidateStatusRepository.getAll();
			   for (NeedCandidateStatusEntity needCandidateStatus : entities) {	   	    		
				   	needCandidateStatuses.add(needCandidateStatus);
			   }  
		   } catch (Exception e) {        
	             throw new Exception("error.needstatus.get");
	       }			   
	       return needCandidateStatuses;
	      
	   }
	 
		/**
		* get one needCandidateStatus
		* 
		* @param id
		* @return NeedCandidateStatusEntity
		* @throws Exception
		*/
	   public NeedCandidateStatusEntity get(Integer id) throws Exception {
		   return needCandidateStatusRepository.findById(id);
	   }

		/**
		* create needCandidateStatus
		* 
		* @param needCandidateStatus
		* @return NeedCandidateStatusEntity
		* @throws Exception
		*/
	   public NeedCandidateStatusEntity create(NeedCandidateStatusEntityCreation needCandidateStatus) throws Exception {	
		   NeedCandidateStatusEntity needCandidateStatusEntity = new NeedCandidateStatusEntity();
		   try {
			   needCandidateStatusEntity.setCode(needCandidateStatus.getCode());
			   needCandidateStatusEntity.setNaming(needCandidateStatus.getNaming());	
		   } catch (Exception e) {        
	             throw new Exception("error.needstatus.create");
	       }			   
		   return needCandidateStatusRepository.create(needCandidateStatusEntity);
	   }

		/**
		* update needCandidateStatus
		* 
		* @param id, needCandidateStatus
		* @return NeedCandidateStatusEntity
		* @throws Exception
		*/
	   public NeedCandidateStatusEntity update(Integer id, NeedCandidateStatusEntity needCandidateStatus) throws Exception {	
		   return needCandidateStatusRepository.update(needCandidateStatus);
		}
	 
		/**
		* delete needCandidateStatus
		* 
		* @param id
		* @return 
		* @throws Exception
		*/
	   public void delete(Integer id) throws Exception {	
		   needCandidateStatusRepository.delete(id);
		}
	  

		/**
		* get all candidates_needs by status
		* 
		* @param params
		* @return Map<NeedCandidateStatusEntity,List<CandidateNeedEntity>
		* @throws Exception
		*/
		public Map<Integer,Map<String,List<CandidateNeedEntity>>> getAllCandidatesNeedsByStatus(@NotNull Map<String, Object> params) throws Exception  {	   
			  
			Map<Integer,Map<String,List<CandidateNeedEntity>>> candidatesNeedsByStatus = new HashMap<>();		
			  
			   try {
			       List<NeedCandidateStatusEntity> entities = needCandidateStatusRepository.getAll();
				   for (NeedCandidateStatusEntity needCandidateStatus : entities) {	
					   Map<String,List<CandidateNeedEntity>> namingEntities = new HashMap<>();
					   namingEntities.put(needCandidateStatus.getNaming(),candidateNeedRepository.findByCandidateNeedStatus(needCandidateStatus.getId()));
					   candidatesNeedsByStatus.put(needCandidateStatus.getId(),namingEntities);
				   }  
			   } catch (Exception e) {        
		             throw new Exception("error.needCandidateStatus.getCandidatesNeedsByStatus");
		       }			   
		       return candidatesNeedsByStatus; 
		}
			   

	

	/**
	* get all candidates_needs by status and need
	* 
	* @param params
	* @return Map<Integer,Map<String,Map<Integer, Map<String,List<CandidateNeedEntity>>>>>
	* @throws Exception
	*/
	public Map<Integer,Map<String,Map<Integer, Map<String,List<CandidateNeedEntity>>>>> getAllCandidatesNeedsByNeedByStatus(@NotNull Map<String, Object> params) throws Exception  {	   
		  
		Map<Integer,Map<String,Map<Integer, Map<String,List<CandidateNeedEntity>>>>> candidatesNeedsByNeedByStatus = new HashMap<>();		
		  
		   try {
		      
			   List<NeedEntity> needs = needRepository.getAllByClientNaming();	   	   
			   for (NeedEntity need : needs) {	
				   Map<Integer,Map<String,List<CandidateNeedEntity>>> candidatesNeedByStatus = new HashMap<>();
				   Map<String,Map<Integer, Map<String,List<CandidateNeedEntity>>>> namingNeedsEntities = new HashMap<>();
				   List<NeedCandidateStatusEntity> entitiesStatus = needCandidateStatusRepository.getAll();
				   for (NeedCandidateStatusEntity needCandidateStatus : entitiesStatus) {	
					   Map<String,List<CandidateNeedEntity>> namingEntities = new HashMap<>();
					   namingEntities.put(needCandidateStatus.getNaming(),candidateNeedRepository.findByNeedAndStatus(need.getId(),needCandidateStatus.getId()));
					   candidatesNeedByStatus.put(needCandidateStatus.getId(),namingEntities);
				   }  
				      
				   namingNeedsEntities.put(need.getTitle(),candidatesNeedByStatus);
				   candidatesNeedsByNeedByStatus.put(need.getId(),namingNeedsEntities);
			   } 
			     
			   
		   } catch (Exception e) {        
	             throw new Exception("error.needCandidateStatus.getCandidatesNeedsByStatus");
	       }			   
	       return candidatesNeedsByNeedByStatus; 
	}
}








