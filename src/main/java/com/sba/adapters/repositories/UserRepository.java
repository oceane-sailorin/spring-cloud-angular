package com.sba.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.UserDAO;
import com.sba.models.entities.UserEntity;

import lombok.NonNull;

/**
 * user repository
 * 
 */
@Repository
public class UserRepository  {

	@Autowired
	private UserDAO userDAO;

	 /**
     * get all users
     * 
     * @param 
     * @return List<UserEntity>
     * @throws 
     */
	public List<UserEntity> getAll() 
    {                
	   return userDAO.findAll();
	}

	 /**
     * find user by id
     * 
     * @param id
     * @return UserEntity
     * @throws 
     */
	public UserEntity findById(@NonNull Integer id) {
		UserEntity userEntity= null;
		Optional<UserEntity> value = userDAO.findById(id);
		userEntity = value.isPresent() ? value.get(): null;
		return userEntity;
	}

	 /**
     * create user
     * 
     * @param user
     * @return UserEntity
     * @throws 
     */
	public UserEntity create(UserEntity user) {
		return userDAO.saveAndFlush(user);
	}

	 /**
     * update user
     * 
     * @param user
     * @return UserEntity
     * @throws 
     */
	public UserEntity update(UserEntity user) {
		return userDAO.saveAndFlush(user);
	}
	
	 /**
     * delete user
     * 
     * @param id
     * @return 
     * @throws 
     */
	public void delete(Integer id) {
		userDAO.deleteById(id);
	}
	   
}
