package com.sba.adapters.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.models.entities.UserStatusEntity;

/**
 * user repository interface
 * 
 */
public interface UserStatusDAO extends JpaRepository<UserStatusEntity,Integer>{

}
