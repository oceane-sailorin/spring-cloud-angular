package com.sba.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.adapters.repositories.ProfileRepository;
import com.sba.adapters.repositories.UserRepository;
import com.sba.adapters.repositories.UserStatusRepository;
import com.sba.models.entities.ProfileEntity;
import com.sba.models.entities.UserEntity;
import com.sba.models.entities.UserEntityCreation;
import com.sba.models.entities.UserStatusEntity;

/**
 * user service
 * 
 */
@Service
public class UserManagerImpl implements UserManager{

   @Autowired
   UserRepository userRepository;
   
   @Autowired
   UserStatusRepository userStatusRepository;
   
   @Autowired
   ProfileRepository profileRepository;
   
   private static final String ACTIF = "ACTIF";
    
	/**
	* get all users
	* 
	* @param params
	* @return List<UserEntity>
	* @throws Exception
	*/   
   public List<UserEntity> getAllUsers(@NotNull Map<String, Object> params) throws Exception  {	   
	  
	   List<UserEntity> users = new ArrayList<>();
	   try {
	       List<UserEntity> entities = userRepository.getAll();
		   for (UserEntity user : entities) {	   	    		
			   	users.add(user);
		   } 
	   } catch (Exception e) {        
           throw new Exception("error.user.get");
       }		   
       return users;
      
   }
 
	/**
	* get one user
	* 
	* @param id
	* @return UserEntity
	* @throws Exception
	*/
   public UserEntity get(Integer id) throws Exception {
	   return userRepository.findById(id);
   }

	/**
	* create user
	* 
	* @param user
	* @return UserEntity
	* @throws Exception
	*/
   public UserEntity create(UserEntityCreation user) throws Exception {	
	   UserEntity userEntity = new UserEntity();
	   try {
		   userEntity.setLogin(user.getLogin());
		   userEntity.setLastname(user.getLastname());
		   userEntity.setFirstname(user.getFirstname());
		   UserStatusEntity status = userStatusRepository.findById(user.getStatus());
		   userEntity.setStatus(status);
		   ProfileEntity profile = profileRepository.findById(user.getProfile());
		   userEntity.setProfile(profile);
		   userEntity.setDateCreation(new Date());
		   userEntity.setDateActivation(null);
		   userEntity.setDateLastConnection(null);
		   userEntity.setDateArchive(null);
	   } catch (Exception e) {        
           throw new Exception("error.user.create");
       }		   
	   return userRepository.create(userEntity);
   }

	/**
	* update user
	* 
	* @param id, user
	* @return UserEntity
	* @throws Exception
	*/
   public UserEntity update(Integer id, UserEntityCreation user) throws Exception {	
	   
	   UserEntity userEntity = new UserEntity();
	   UserEntity entity = userRepository.findById(id);
	   try {
		   userEntity.setId(id);
		   userEntity.setLogin(user.getLogin());
		   userEntity.setLastname(user.getLastname());
		   userEntity.setFirstname(user.getFirstname());
		   UserStatusEntity status = userStatusRepository.findById(user.getStatus());
		   userEntity.setStatus(status);
		   ProfileEntity profile = profileRepository.findById(user.getProfile());
		   userEntity.setProfile(profile);
		   userEntity.setDateCreation(user.getDateCreation());	  
		   if(status!= null) {
			   if(!status.getCode().equals(entity.getStatus().getCode()) && status.getCode().equals(ACTIF)) {
				   userEntity.setDateActivation(new Date());
			   }else {
				   userEntity.setDateActivation(user.getDateActivation());
			   }
		   }	   
		   userEntity.setDateLastConnection(user.getDateLastConnection());
		   userEntity.setDateArchive(user.getDateArchive());
	   } catch (Exception e) {        
           throw new Exception("error.user.update");
       }		
	   return userRepository.update(userEntity);
	}
 
	/**
	* delete user
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
   public void delete(Integer id) throws Exception {	
	   userRepository.delete(id);
	}
  
   
}
