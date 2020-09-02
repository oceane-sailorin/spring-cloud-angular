package com.sba.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.adapters.repositories.CandidateRepository;
import com.sba.adapters.repositories.CandidateStatusRepository;
import com.sba.models.entities.CandidateEntity;
import com.sba.models.entities.CandidateStatusEntity;
import com.sba.models.entities.CandidateStatusEntityCreation;

/**
 * candidate status service
 * 
 */
@Service
public class CandidateStatusManagerImpl implements CandidateStatusManager{

	  @Autowired
	  CandidateStatusRepository candidateStatusRepository;
	  
	  @Autowired
	  CandidateRepository candidateRepository;
	    
		/**
		* get all candidateStatuses
		* 
		* @param params
		* @return List<CandidateStatusEntity>
		* @throws Exception
		*/   
	   public List<CandidateStatusEntity> getAllCandidateStatuses(@NotNull Map<String, Object> params) throws Exception  {	   
		  
		   List<CandidateStatusEntity> candidateStatuses = new ArrayList<>();
		   try {
		       List<CandidateStatusEntity> entities = candidateStatusRepository.getAll();
			   for (CandidateStatusEntity candidateStatus : entities) {	   	    		
				   	candidateStatuses.add(candidateStatus);
			   }  
		   } catch (Exception e) {        
	             throw new Exception("error.candidatestatus.get");
	       }			   
	       return candidateStatuses;
	      
	   }
	 
		/**
		* get one candidateStatus
		* 
		* @param id
		* @return CandidateStatusEntity
		* @throws Exception
		*/
	   public CandidateStatusEntity get(Integer id) throws Exception {
		   return candidateStatusRepository.findById(id);
	   }

		/**
		* create candidateStatus
		* 
		* @param candidateStatus
		* @return CandidateStatusEntity
		* @throws Exception
		*/
	   public CandidateStatusEntity create(CandidateStatusEntityCreation candidateStatus) throws Exception {	
		   CandidateStatusEntity candidateStatusEntity = new CandidateStatusEntity();
		   try {
			   candidateStatusEntity.setCode(candidateStatus.getCode());
			   candidateStatusEntity.setNaming(candidateStatus.getNaming());	
		   } catch (Exception e) {        
	             throw new Exception("error.candidatestatus.create");
	       }			   
		   return candidateStatusRepository.create(candidateStatusEntity);
	   }

		/**
		* update candidateStatus
		* 
		* @param id, candidateStatus
		* @return CandidateStatusEntity
		* @throws Exception
		*/
	   public CandidateStatusEntity update(Integer id, CandidateStatusEntity candidateStatus) throws Exception {	
		   return candidateStatusRepository.update(candidateStatus);
		}
	 
		/**
		* delete candidateStatus
		* 
		* @param id
		* @return 
		* @throws Exception
		*/
	   public void delete(Integer id) throws Exception {	
		   candidateStatusRepository.delete(id);
		}

	   
		/**
		* get all candidates by status
		* 
		* @param params
		* @return Map<CandidateStatusEntity,List<CandidateEntity>
		* @throws Exception
		*/
		public Map<Integer,Map<String,List<CandidateEntity>>> getAllCandidatesByStatus(@NotNull Map<String, Object> params) throws Exception  {	   
			  
			Map<Integer,Map<String,List<CandidateEntity>>> candidatesByStatus = new HashMap<>();		
			  
			   try {
			       List<CandidateStatusEntity> entities = candidateStatusRepository.getAll();
				   for (CandidateStatusEntity candidateStatus : entities) {	
					   Map<String,List<CandidateEntity>> namingEntities = new HashMap<>();
					   namingEntities.put(candidateStatus.getNaming(),candidateRepository.findByCandidateStatus(candidateStatus.getId()));
					   candidatesByStatus.put(candidateStatus.getId(),namingEntities);
				   }  
			   } catch (Exception e) {        
		             throw new Exception("error.candidatestatus.getCandidatesByStatus");
		       }			   
		       return candidatesByStatus; 
		}
			   
	   
}
