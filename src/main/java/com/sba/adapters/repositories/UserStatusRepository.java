package com.sba.adapters.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.UserStatusDAO;
import com.sba.models.entities.UserStatusEntity;

/**
 * userstatus repository
 * 
 */
@Repository
public class UserStatusRepository  {

	@Autowired
	private UserStatusDAO userStatusDAO;

	 /**
     * get all usersstatuses
     * 
     * @param 
     * @return List<ProfileEntity>
     * @throws 
     */
	public List<UserStatusEntity> getAll() 
    {   
	   List<UserStatusEntity> entities = userStatusDAO.findAll();           
	   return entities;
	}

	 /**
     * find user by id
     * 
     * @param id
     * @return UserEntity
     * @throws 
     */
	public UserStatusEntity findById(Integer id) {
		return userStatusDAO.findById(id).get();
	}



	   
}
