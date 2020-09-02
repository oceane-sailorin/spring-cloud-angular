package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.UserStatusEntity;

/**
 * userStatus interface repository
 * 
 */
public interface UserStatusManager {

	/**
	* get all usersStatuses
	* 
	* @param params
	* @return List<UserStatusEntity>
	* @throws Exception
	*/
	public List<UserStatusEntity> getAllUsersStatuses(@NotNull Map<String, Object> params) throws Exception; 

	/**
	* get one userStatus
	* 
	* @param id
	* @return UserStatusEntity
	* @throws Exception
	*/
	public UserStatusEntity get(Integer id) throws Exception; 

	
	
}
