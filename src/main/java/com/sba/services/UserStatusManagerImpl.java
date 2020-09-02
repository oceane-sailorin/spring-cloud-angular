package com.sba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.adapters.repositories.UserStatusRepository;
import com.sba.models.entities.UserStatusEntity;

/**
 * user service
 * 
 */
@Service
public class UserStatusManagerImpl implements UserStatusManager{

   @Autowired
   UserStatusRepository userStatusRepository;
    
	/**
	* get all usersstatuses
	* 
	* @param params
	* @return List<UserStatusEntity>
	* @throws Exception
	*/   
   public List<UserStatusEntity> getAllUsersStatuses(@NotNull Map<String, Object> params) throws Exception  {	   
	  
	   List<UserStatusEntity> usersstatuses = new ArrayList<>();
	   try {
	       List<UserStatusEntity> entities = userStatusRepository.getAll();
		   for (UserStatusEntity userstatus : entities) {	   	    		
			   usersstatuses.add(userstatus);
		   }   
	   } catch (Exception e) {        
           throw new Exception("error.userstatus.get");
       }		   
       return usersstatuses;
      
   }
 
	/**
	* get one userstatus
	* 
	* @param id
	* @return UserStatusEntity
	* @throws Exception
	*/
   public UserStatusEntity get(Integer id) throws Exception {
	   return userStatusRepository.findById(id);
   }


   
}
