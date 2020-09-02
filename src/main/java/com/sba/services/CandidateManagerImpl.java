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

import com.sba.adapters.repositories.CandidateRepository;
import com.sba.adapters.repositories.CandidateStatusRepository;
import com.sba.adapters.repositories.CountryRepository;
import com.sba.adapters.repositories.SourceRepository;
import com.sba.adapters.repositories.UserRepository;
import com.sba.models.entities.CandidateEntity;
import com.sba.models.entities.CandidateEntityCreation;
import com.sba.models.entities.CandidateEntityStatusDateUpdate;
import com.sba.models.entities.CandidateEntityStatusUpdate;
import com.sba.models.entities.CandidateStatusEntity;
import com.sba.models.entities.CountryEntity;
import com.sba.models.entities.Gender;
import com.sba.models.entities.SourceEntity;
import com.sba.models.entities.UserEntity;

/**
 * candidate service
 * 
 */
@Service
public class CandidateManagerImpl implements CandidateManager{

	  @Autowired
	   CandidateRepository candidateRepository;
	  
	  @Autowired
	   UserRepository userRepository;
	  
	  @Autowired
	   CandidateStatusRepository candidateStatusRepository;
	  
	  @Autowired
	   SourceRepository sourceRepository;
	  
	  @Autowired
	   CountryRepository countryRepository;
	  
	   @Autowired
	   private UtilService utilService;
	   
	   @Autowired
	   FileStorageManager fileStorageManager;
	    
		/**
		* get all candidates
		* 
		* @param params
		* @return List<CandidateEntity>
		* @throws Exception
		*/   
	   public List<CandidateEntity> getAllCandidates(@NotNull Map<String, Object> params) throws Exception  {	   
		  
		   List<CandidateEntity> candidates = new ArrayList<>();
		   try {
			   List<CandidateEntity> entities = candidateRepository.getAll();
			   for (CandidateEntity candidate : entities) {	   	    		
				   	candidates.add(candidate);
			   }   
		   } catch (Exception e) {        
	             throw new Exception("error.candidate.getall");
	        }
	       return candidates;
	      
	   }
	 
		/**
		* get one candidate
		* 
		* @param id
		* @return CandidateEntity
		* @throws Exception
		*/
	   public CandidateEntity get(Integer id) throws Exception {
		   return candidateRepository.findById(id);
	   }

		/**
		* create candidate
		* 
		* @param candidate
		* @return int
		* @throws Exception
		*/
	   public int create(CandidateEntityCreation candidate) throws Exception {	
		   CandidateEntity candidateEntity = new CandidateEntity();
		   try {
			   candidateEntity.setIdentity(candidate.getIdentity());		  
			   candidateEntity.setGender(Gender.valueOf(candidate.getGender()));
			   candidateEntity.setLastname(candidate.getLastname());
			   candidateEntity.setFirstname(candidate.getFirstname());
			   candidateEntity.setEmail(candidate.getEmail());
			   candidateEntity.setPhone(candidate.getPhone());
			   candidateEntity.setAddress(candidate.getAddress());
			   CountryEntity country = countryRepository.findById(candidate.getCountry());		  
			   candidateEntity.setCountry(country);
			   UserEntity recruiter = userRepository.findById(candidate.getRecruiter());		  
			   candidateEntity.setRecruiter(recruiter);
			   UserEntity sourcer = userRepository.findById(candidate.getSourcer());			  
			   candidateEntity.setSourcer(sourcer);
			   SourceEntity source = sourceRepository.findById(candidate.getSource());		  
			   candidateEntity.setSource(source);
			   CandidateStatusEntity candidateStatus = candidateStatusRepository.findById(candidate.getCandidateStatus());			  
			   candidateEntity.setCandidateStatus(candidateStatus);
			   candidateEntity.setStack(candidate.getStack());
			   candidateEntity.setProfile(candidate.getProfile());
			   candidateEntity.setSalary(candidate.getSalary());
			   candidateEntity.setDateLastUpdate(new Date());
			   candidateEntity.setDateDisponibility(candidate.getDateDisponibility());
			   candidateEntity.setDateInterview(candidate.getDateInterview());
			   candidateEntity.setDateSourcing(candidate.getDateSourcing());
			   candidateEntity.setDateProposition(candidate.getDateProposition());
			   candidateEntity.setDateSignature(candidate.getDateSignature());
			   
			   candidateEntity.setKeywords(candidate.getKeywords());
			   
		   } catch (Exception e) {        
	             throw new Exception("error.candidate.create");
	        }
		   CandidateEntity candie = candidateRepository.create(candidateEntity);		   
		   return candie.getId();		   
	   }

		/**
		* update candidate
		* 
		* @param id, candidate
		* @return CandidateEntity
		* @throws Exception
		*/
	   public CandidateEntity update(Integer id, CandidateEntityCreation candidate) throws Exception {	
		   CandidateEntity entity = candidateRepository.findById(id);
		   try {			   
			   entity.setId(entity.getId());
			   entity.setIdentity(candidate.getIdentity());		  
			   entity.setGender(Gender.valueOf(candidate.getGender()));
			   entity.setLastname(candidate.getLastname());
			   entity.setFirstname(candidate.getFirstname());
			   entity.setEmail(candidate.getEmail());
			   entity.setPhone(candidate.getPhone());
			   entity.setAddress(candidate.getAddress());
			   CountryEntity country = countryRepository.findById(candidate.getCountry());		  
			   entity.setCountry(country);
			   UserEntity recruiter = userRepository.findById(candidate.getRecruiter());		  
			   entity.setRecruiter(recruiter);
			   UserEntity sourcer = userRepository.findById(candidate.getSourcer());			  
			   entity.setSourcer(sourcer);
			   SourceEntity source = sourceRepository.findById(candidate.getSource());		  
			   entity.setSource(source);
			   CandidateStatusEntity candidateStatus = candidateStatusRepository.findById(candidate.getCandidateStatus());			  
			   entity.setCandidateStatus(candidateStatus);
			   entity.setStack(candidate.getStack());
			   entity.setProfile(candidate.getProfile());
			   entity.setSalary(candidate.getSalary());
			   entity.setDateLastUpdate(new Date());
			   entity.setDateDisponibility(candidate.getDateDisponibility());
			   entity.setDateInterview(candidate.getDateInterview());
			   entity.setDateSourcing(candidate.getDateSourcing());
			   entity.setDateProposition(candidate.getDateProposition());
			   entity.setDateSignature(candidate.getDateSignature());
			   
			   entity.setKeywords(candidate.getKeywords());
			   
			   
			   
			   
		   } catch (Exception e) {        
	             throw new Exception("error.candidate.update");
	        }
		   return candidateRepository.update(entity);
		}
	   
		/**
		* update candidate status
		* 
		* @param id, candidate
		* @return CandidateEntityStatusUpdate
		* @throws Exception
		*/
	   public CandidateEntity updateStatus(Integer id, CandidateEntityStatusUpdate candidateStatusUpdate) throws Exception {	
		   CandidateEntity candidate = candidateRepository.findById(id);
		   if (candidate == null) {
			   throw new Exception("error.updatestatus.candidate.notfound");
		   }
		   try {	
			   CandidateStatusEntity candidateStatus = candidateStatusRepository.findById(candidateStatusUpdate.getCandidateStatus());
			   if (candidateStatus == null) {
				   throw new Exception("error.updateStatus.candidateStatus.notfound");
			   }
			   candidate.setCandidateStatus(candidateStatus);	
			   if(candidateStatus.getCode().equals("CV_SELECTIONNES")) {			  
				   candidate.setDateInterview(null);				  		   
			   }
			   candidate.setDateSignature(null);
			   candidate.setDateProposition(null);		
			   candidate.setDateLastUpdate(new Date()); 
		   } catch (Exception e) {        
	             throw new Exception("error.candidate.updatestatus");
	        }
		   return candidateRepository.update(candidate);
		}	   
	   
		/**
		* update candidate status date
		* 
		* @param id, candidate, date
		* @return CandidateEntityStatusDateUpdate
		* @throws Exception
		*/
	   public CandidateEntity updateStatusDate(Integer id, CandidateEntityStatusDateUpdate candidateStatusDateUpdate) throws Exception {	
		   CandidateEntity candidate = candidateRepository.findById(id);
		   if (candidate == null) {
			   throw new Exception("error.updatestatus.candidate.notfound");
		   }
		   try {	
			   CandidateStatusEntity candidateStatus = candidateStatusRepository.findById(candidateStatusDateUpdate.getCandidateStatus());
			   if (candidateStatus == null) {
				   throw new Exception("error.updateStatus.candidateStatus.notfound");
			   }
			   candidate.setCandidateStatus(candidateStatus);
			   if(candidateStatus.getCode().equals("RENDEZ_VOUS_PRIS") && candidateStatusDateUpdate.getDateUpdate() != null) {
				   candidate.setDateInterview(candidateStatusDateUpdate.getDateUpdate());
				   candidate.setDateProposition(null);
				   candidate.setDateSignature(null);			 
			   }else if(candidateStatus.getCode().equals("PROPALE_FAITE") && candidateStatusDateUpdate.getDateUpdate() != null) {
				   candidate.setDateProposition(candidateStatusDateUpdate.getDateUpdate());
				   candidate.setDateSignature(null);			  
			   }else if(candidateStatus.getCode().equals("PROPALE_ACCEPTEE") && candidateStatusDateUpdate.getDateUpdate() != null) {
				   candidate.setDateSignature(candidateStatusDateUpdate.getDateUpdate());				  	  
			   }
			   candidate.setDateLastUpdate(new Date()); 
		   } catch (Exception e) {        
	             throw new Exception("error.candidate.updatestatusdate");
	       }
		   return candidateRepository.update(candidate);
		}	
	   
		/**
		* update file
		* 
		* @param id, filename
		* @return CandidateEntity
		* @throws Exception
		*/
	   public CandidateEntity updateFile(Integer id, String fileName) throws Exception {	
		   CandidateEntity entity = candidateRepository.findById(id);		   
		   try {			   
			   entity.setFilename(fileName);	
			 		   
		   } catch (Exception e) {        
	             throw new Exception("error.need.create");
	       }		 
		   return candidateRepository.update(entity);
		}	   
	 
		/**
		* delete candidate
		* 
		* @param id
		* @return 
		* @throws Exception
		*/
	   public void delete(Integer id) throws Exception {	
		   CandidateEntity entity = candidateRepository.findById(id);		   
		   try {
			   if(entity.getFilename() != null) {
				   fileStorageManager.delete(entity.getFilename());
			   }
			   candidateRepository.delete(id);			   
		   } catch (Exception e) {        
	             throw new Exception("error.candidate.delete");
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
	       result.putAll(utilService.convertStringParameter(params, Integer.class, null, "id", "id", "error.candidate.filter.id"));
	       result.putAll(utilService.convertStringParameter(params, Integer.class, null, "candidateStatus.id", "candidateStatus.id", "error.profile.filter.candidateStatus"));
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
