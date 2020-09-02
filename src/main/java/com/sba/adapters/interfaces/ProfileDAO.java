package com.sba.adapters.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.models.entities.ProfileEntity;
import com.sba.models.entities.ProfileEntityCreation;

/**
 * profile repository interface
 * 
 */
public interface ProfileDAO extends JpaRepository<ProfileEntity,Integer>{

	ProfileEntity saveAndFlush(ProfileEntityCreation profile);


	
}
