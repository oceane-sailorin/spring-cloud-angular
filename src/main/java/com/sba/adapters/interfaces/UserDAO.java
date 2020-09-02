package com.sba.adapters.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.models.entities.UserEntity;
import com.sba.models.entities.UserEntityCreation;

/**
 * user repository interface
 * 
 */
public interface UserDAO extends JpaRepository<UserEntity,Integer>{
	
	UserEntity saveAndFlush(UserEntityCreation user);

}
