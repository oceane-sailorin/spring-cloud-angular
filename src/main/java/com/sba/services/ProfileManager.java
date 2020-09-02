package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.ProfileEntity;
import com.sba.models.entities.ProfileEntityCreation;

/**
 * profile service interface
 * 
 */
public interface ProfileManager {
	
	/**
	* get all profiles
	* 
	* @param params
	* @return List<ProfileEntity>
	* @throws Exception
	*/
	public List<ProfileEntity> getAllProfiles(@NotNull Map<String, Object> params) throws Exception; 

	
	/**
	* get one profile
	* 
	* @param id
	* @return ProfileEntity
	* @throws Exception
	*/
	public ProfileEntity get(Integer id) throws Exception; 

	/**
	* create profile
	* 
	* @param profile
	* @return ProfileEntity
	* @throws Exception
	*/
	public ProfileEntity create(ProfileEntityCreation profile) throws Exception;

	/**
	* update profile
	* 
	* @param id, profile
	* @return ProfileEntity
	* @throws Exception
	*/	
	public ProfileEntity update(Integer id, ProfileEntity profile) throws Exception;

	/**
	* delete profile
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
	public void delete(Integer id) throws Exception;

}
