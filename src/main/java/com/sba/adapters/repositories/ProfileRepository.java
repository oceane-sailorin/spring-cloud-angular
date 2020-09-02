package com.sba.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.ProfileDAO;
import com.sba.models.entities.ProfileEntity;

/**
 * profile repository
 * 
 */
@Repository
public class ProfileRepository  {
	
	@Autowired
	private ProfileDAO profileDAO;

    /**
     * get all profiles
     * 
     * @param 
     * @return List<ProfileEntity>
     * @throws 
     */
	public List<ProfileEntity> getAll() 
   {          
	   return profileDAO.findAll();
	}

	 /**
     * finf profile by id
     * 
     * @param id
     * @return ProfileEntity
     * @throws 
     */
	public ProfileEntity findById(Integer id) {
		ProfileEntity profileEntity= null;
		Optional<ProfileEntity> value = profileDAO.findById(id);
		profileEntity = value.isPresent() ? value.get(): null;
		return profileEntity;
	}

	 /**
     * create profile
     * 
     * @param profile
     * @return ProfileEntity
     * @throws 
     */
	public ProfileEntity create(ProfileEntity profile) {
		return profileDAO.saveAndFlush(profile);
	}

	 /**
     * update profile
     * 
     * @param profile
     * @return ProfileEntity
     * @throws 
     */
	public ProfileEntity update(ProfileEntity profile) {
		return profileDAO.saveAndFlush(profile);
	}
	
	 /**
     * delete profile
     * 
     * @param id
     * @return 
     * @throws 
     */
	public void delete(Integer id) {
		profileDAO.deleteById(id);
	}
	   
	
}
