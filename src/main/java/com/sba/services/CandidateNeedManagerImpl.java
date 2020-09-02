package com.sba.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.adapters.repositories.CandidateNeedRepository;
import com.sba.adapters.repositories.CandidateRepository;
import com.sba.adapters.repositories.NeedCandidateStatusRepository;
import com.sba.adapters.repositories.NeedRepository;
import com.sba.models.entities.CandidateEntity;
import com.sba.models.entities.CandidateNeedEntity;
import com.sba.models.entities.CandidateNeedEntityCreation;
import com.sba.models.entities.CandidateNeedEntityStatusDateUpdate;
import com.sba.models.entities.CandidateNeedEntityStatusUpdate;
import com.sba.models.entities.NeedCandidateStatusEntity;
import com.sba.models.entities.NeedEntity;

/**
 * candidateNeed need service
 * 
 */
@Service
public class CandidateNeedManagerImpl implements CandidateNeedManager{

	  @Autowired
	   CandidateNeedRepository candidateNeedRepository;
	  
	  @Autowired
	   NeedCandidateStatusRepository needCandidateStatusRepository;
	  
	  @Autowired
	   CandidateRepository candidateRepository;
	  
	  @Autowired
	   NeedRepository needRepository;
	  
	  @Autowired
	   private UtilService utilService;
	   
	    
		/**
		* get all candidateNeeds
		* 
		* @param params
		* @return List<CandidateNeedEntity>
		* @throws Exception
		*/   
	   public List<CandidateNeedEntity> getAllCandidateNeeds(@NotNull Map<String, Object> params) throws Exception  {	   
		  
		   List<CandidateNeedEntity> candidateNeeds = new ArrayList<>();
		   try {
			   List<CandidateNeedEntity> entities = candidateNeedRepository.getAll();
			   for (CandidateNeedEntity candidateNeed : entities) {	   	    		
				   	candidateNeeds.add(candidateNeed);
			   }   
		   } catch (Exception e) {        
	             throw new Exception("error.candidateNeed.getall");
	        }
	       return candidateNeeds;
	      
	   }
	 
		/**
		* get one candidateNeed
		* 
		* @param id
		* @return CandidateNeedEntity
		* @throws Exception
		*/
	   public CandidateNeedEntity get(Integer id) throws Exception {
		   return candidateNeedRepository.findById(id);
	   }

		/**
		* create candidateNeed
		* 
		* @param candidateNeed
		* @return int
		* @throws Exception
		*/
	   public int create(CandidateNeedEntityCreation candidateNeed) throws Exception {	
		   CandidateNeedEntity candidateNeedEntity = new CandidateNeedEntity();
		   try {
			  
			   NeedEntity need = needRepository.findById(candidateNeed.getNeed());		  
			   candidateNeedEntity.setNeed(need);
			   CandidateEntity candidate = candidateRepository.findById(candidateNeed.getCandidate());		  
			   candidateNeedEntity.setCandidate(candidate);			  
			   NeedCandidateStatusEntity needCandidateStatus = needCandidateStatusRepository.findById(candidateNeed.getNeedCandidateStatus());			  
			   candidateNeedEntity.setNeedCandidateStatus(needCandidateStatus);
			  
			   candidateNeedEntity.setDateCreation(new Date());
			   candidateNeedEntity.setDateSent(candidateNeed.getDateSent());
			   candidateNeedEntity.setInterviewDate(candidateNeed.getInterviewDate());
			   candidateNeedEntity.setDateFeedback(candidateNeed.getDateFeedback());
			   candidateNeedEntity.setDateOk(candidateNeed.getDateOk());
			   candidateNeedEntity.setDateKoClient(candidateNeed.getDateKoClient());   
			   candidateNeedEntity.setDateKoCandidate(candidateNeed.getDateKoCandidate());
			   
		   } catch (Exception e) {        
	             throw new Exception("error.candidateNeed.create");
	        }
		   CandidateNeedEntity candie = candidateNeedRepository.create(candidateNeedEntity);		   
		   return candie.getId();		   
	   }

		/**
		* update candidateNeed
		* 
		* @param id, candidateNeed
		* @return CandidateNeedEntity
		* @throws Exception
		*/
	   public CandidateNeedEntity update(Integer id, CandidateNeedEntityCreation candidateNeed) throws Exception {	
		   CandidateNeedEntity entity = candidateNeedRepository.findById(id);
		   try {			   
			   entity.setId(entity.getId());			  
			   NeedEntity need = needRepository.findById(candidateNeed.getNeed());		  
			   entity.setNeed(need);
			   CandidateEntity candidate = candidateRepository.findById(candidateNeed.getCandidate());		  
			   entity.setCandidate(candidate);
			  
			   NeedCandidateStatusEntity needCandidateStatus = needCandidateStatusRepository.findById(candidateNeed.getNeedCandidateStatus());			  
			   entity.setNeedCandidateStatus(needCandidateStatus);
			  
			   entity.setDateSent(candidateNeed.getDateSent());
			   entity.setInterviewDate(candidateNeed.getInterviewDate());
			   entity.setDateFeedback(candidateNeed.getDateFeedback());
			   entity.setDateOk(candidateNeed.getDateOk());
			   entity.setDateKoClient(candidateNeed.getDateKoClient());   
			   entity.setDateKoCandidate(candidateNeed.getDateKoCandidate());			   
			   					   			   			   
		   } catch (Exception e) {        
	             throw new Exception("error.candidateNeed.update");
	        }
		   return candidateNeedRepository.update(entity);
		}
	   
		/**
		* update candidateNeed status
		* 
		* @param id, candidateNeed
		* @return CandidateNeedEntityStatusUpdate
		* @throws Exception
		*/
	   public CandidateNeedEntity updateStatus(Integer id, CandidateNeedEntityStatusUpdate candidateNeedStatusUpdate) throws Exception {	
		   CandidateNeedEntity candidateNeed = candidateNeedRepository.findById(id);
		   if (candidateNeed == null) {
			   throw new Exception("error.updatestatus.candidateNeed.notfound");
		   }
		   try {	
			   NeedCandidateStatusEntity needCandidateStatus = needCandidateStatusRepository.findById(candidateNeedStatusUpdate.getNeedCandidateStatus());
			   if (needCandidateStatus == null) {
				   throw new Exception("error.updateStatus.candidateNeedStatus.notfound");
			   }
			   candidateNeed.setNeedCandidateStatus(needCandidateStatus);	
			   
		   } catch (Exception e) {        
	             throw new Exception("error.candidateNeed.updatestatus");
	        }
		   return candidateNeedRepository.update(candidateNeed);
		}	   
	   
		/**
		* update candidateNeed status date
		* 
		* @param id, candidateNeed, date
		* @return CandidateNeedEntityStatusDateUpdate
		* @throws Exception
		*/
	   public CandidateNeedEntity updateStatusDate(Integer id, CandidateNeedEntityStatusDateUpdate candidateNeedStatusDateUpdate) throws Exception {	
		   CandidateNeedEntity candidateNeed = candidateNeedRepository.findById(id);
		   if (candidateNeed == null) {
			   throw new Exception("error.updatestatus.candidateNeed.notfound");
		   }
		   try {	
			   NeedCandidateStatusEntity needCandidateStatus = needCandidateStatusRepository.findById(candidateNeedStatusDateUpdate.getNeedCandidateStatus());
			   if (needCandidateStatus == null) {
				   throw new Exception("error.updateStatus.candidateNeedStatus.notfound");
			   }
			   candidateNeed.setNeedCandidateStatus(needCandidateStatus);
			   if(needCandidateStatus.getCode().equals("RENDEZ_VOUS_PRIS") && candidateNeedStatusDateUpdate.getDateUpdate() != null) {
				  	 
			   }else if(needCandidateStatus.getCode().equals("PROPALE_FAITE") && candidateNeedStatusDateUpdate.getDateUpdate() != null) {
				 		  
			   }else if(needCandidateStatus.getCode().equals("PROPALE_ACCEPTEE") && candidateNeedStatusDateUpdate.getDateUpdate() != null) {
				   
			   }
			 
		   } catch (Exception e) {        
	             throw new Exception("error.candidateNeed.updatestatusdate");
	       }
		   return candidateNeedRepository.update(candidateNeed);
		}	
	   
	 
		/**
		* delete candidateNeed
		* 
		* @param id
		* @return 
		* @throws Exception
		*/
	   public void delete(Integer id) throws Exception {	
		   CandidateNeedEntity entity = candidateNeedRepository.findById(id);		   
		   try {			   
			   candidateNeedRepository.delete(id);			   
		   } catch (Exception e) {        
	             throw new Exception("error.need.delete");
	       }	
		}

		/**
	    * getFilterParams
	    * @param params
	    * @return Map<String, Object>
	    * @throws BusinessException
	    */   
	   private Map<String, Object> getFilterParams(Map<String, Object> params) throws Exception {
	   	Map<String, Object> result = new HashMap<>();
	       result.putAll(UtilService.getFilterAndPaginationParams(params));
	       result.putAll(utilService.convertStringParameter(params, Integer.class, null, "id", "id", "error.candidateNeed.filter.id"));
	       result.putAll(utilService.convertStringParameter(params, Integer.class, null, "candidateNeedStatus.id", "candidateNeedStatus.id", "error.profile.filter.candidateNeedStatus"));
	       return result;
	   }	   
	   
	   
	   /**
	    * 
	    * @param params the parameters from the request
	    * @param targetClass the target class
	    * @param targetIdClass the class of the primary key 
	    * @param requestKey the key to search
	    * @param targetKey the attribute name of the entity 
	    * @param errorKey the error code to return if the entity is not found
	    * @return a map containing all parameters found in the incoming list, with converted values to the requested target class
	    * @throws Exception
	    */
	   public <T, I> Map<String, Object> convertStringParameter(final Map<String, Object> params, Class<T> targetClass, Class<I> targetIdClass,
	           String requestKey, String targetKey, String errorKey) throws Exception {
	       Map<String, Object> result = new HashMap<>();

	       // Parse all parameters received by the API
	       for (Entry<String, Object> param : params.entrySet()) {
	           String key = param.getKey();
	           String paramName = "_sortColumn";
	           // sortColumn management
	           if (params.get(paramName) != null && params.get(paramName).equals(requestKey)) {
	               // Add the converted value in the result map with the incoming type
	               result.put(paramName, targetKey);
	           }

	           // Search our key starting with the requested param: code, code_gt,
	           // code_nl, ...
	           if (key.equals(requestKey) || key.startsWith(requestKey + "_")) {

	               // Target value in the right class
	               Object convertedValue = null;

	               // Suffix added to the parameter: gt, lt, nl, ...
	               String suffix = key.indexOf('_') >= 0 ? key.split("_")[1] : null;
	            

	               // Add the converted value in the result map with the incoming type
	               result.put(targetKey + (suffix != null ? "_" + suffix : ""), convertedValue);
	           }
	       }

	       return result;
	   }	   
	  
	   
}
