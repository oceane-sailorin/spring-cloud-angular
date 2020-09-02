package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.UserEntity;
import com.sba.models.entities.UserEntityCreation;

/**
 * user interface repository
 * 
 */
public interface UserManager {

	/**
	* get all users
	* 
	* @param params
	* @return List<UserEntity>
	* @throws Exception
	*/
	public List<UserEntity> getAllUsers(@NotNull Map<String, Object> params) throws Exception; 

	/**
	* get one user
	* 
	* @param id
	* @return UserEntity
	* @throws Exception
	*/
	public UserEntity get(Integer id) throws Exception; 

	/**
	* create user
	* 
	* @param user
	* @return UserEntity
	* @throws Exception
	*/
	public UserEntity create(UserEntityCreation user) throws Exception;

	/**
	* update  user
	* 
	* @param id, user
	* @return UserEntity
	* @throws Exception
	*/
	public UserEntity update(Integer id, UserEntityCreation user) throws Exception;

	/**
	* delete user
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
	public void delete(Integer id) throws Exception;
	   	  
	
}
