package com.sba.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.adapters.repositories.ProfileRepository;
import com.sba.models.entities.PermissionEntity;
import com.sba.models.entities.ProfileEntity;
import com.sba.models.entities.ProfileEntityCreation;

/**
 * profile service
 * 
 */
@Service
public class ProfileManagerImpl implements ProfileManager{
	
   @Autowired
   ProfileRepository profileRepository;
   
   @Autowired
   private UtilService utilService;
 
	/**
	* get all profiles
	* 
	* @param params
	* @return List<ProfileEntity>
	* @throws Exception
	*/
   public List<ProfileEntity> getAllProfiles(@NotNull Map<String, Object> params) throws Exception  {	   
	   Map<String, Object> filters = getFilterParams(params);
	   List<ProfileEntity> profiles = new ArrayList<>();
	   try {
	       List<ProfileEntity> entities = profileRepository.getAll();
		   for (ProfileEntity profile : entities) {	   	    		
			   	profiles.add(profile);
		   } 
	   } catch (Exception e) {        
           throw new Exception("error.profile.get");
       }   
       return profiles;
      
   }
 
	/**
	* get one profile
	* 
	* @param id
	* @return ProfileEntity
	* @throws Exception
	*/
   public ProfileEntity get(Integer id) throws Exception {
	   return profileRepository.findById(id);
   }

	/**
	* create profile
	* 
	* @param profile
	* @return ProfileEntity
	* @throws Exception
	*/
   public ProfileEntity create(ProfileEntityCreation profile) throws Exception {	
	   ProfileEntity profileEntity = new ProfileEntity();
	   try {
		   profileEntity.setNaming(profile.getNaming());
		   Set<PermissionEntity> permisionEntities = new HashSet<>();
		   for (PermissionEntity permission_entity : profile.getPermissions()) {
			   permisionEntities.add(permission_entity);
		   }
		   profileEntity.setPermissions(permisionEntities);
	   } catch (Exception e) {        
           throw new Exception("error.profile.create");
       }   
	   return profileRepository.create(profileEntity);
   }

	/**
	* update profile
	* 
	* @param id, profile
	* @return ProfileEntity
	* @throws Exception
	*/
   public ProfileEntity update(Integer id, ProfileEntity profile) throws Exception {	
	   return profileRepository.update(profile);
	}
  
	/**
	* delete profile
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
   public void delete(Integer id) throws Exception {	
	   profileRepository.delete(id);
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
       result.putAll(utilService.convertStringParameter(params, Integer.class, null, "id", "id", "error.profile.filter.id"));
       result.putAll(utilService.convertStringParameter(params, String.class, null, "code", "code", "error.profile.filter.code"));
       return result;
   }

   
   
 
   
   /**
    * Extract all pagination and filter params in the incoming map
    * @param params
    * @return
    */
   public static Map<String, Object> getFilterAndPaginationParams(final Map<String, Object> params) {
       Map<String, Object> result = new HashMap<>();

       String sortDirParam = "_sortDir";
       String getAllParam = "_getAll";
       String perPageParam = "_perPage";
       String pageParam = "_page";
       // sortField managed in convertStringParamaters

       // Sort direction
       if (params.get(sortDirParam) != null) {
           result.put(sortDirParam, params.get(sortDirParam));
       }

       // No filter
       if (params.get(getAllParam) != null) {
           result.put(getAllParam, params.get(getAllParam));
       }

       // Elements per page
       if (params.get(perPageParam) != null) {
           result.put(perPageParam, params.get(perPageParam));
       }

       // Page number
       if (params.get(pageParam) != null) {
           result.put(pageParam, params.get(pageParam));
       }

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
