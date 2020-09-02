package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.ClientEntity;
import com.sba.models.entities.ClientEntityCreation;

/**
 * client interface repository
 * 
 */
public interface ClientManager {

	/**
	* get all clients
	* 
	* @param params
	* @return List<ClientEntity>
	* @throws Exception
	*/
	public List<ClientEntity> getAllClients(@NotNull Map<String, Object> params) throws Exception; 

	/**
	* get one client
	* 
	* @param id
	* @return ClientEntity
	* @throws Exception
	*/
	public ClientEntity get(Integer id) throws Exception; 

	/**
	* create client
	* 
	* @param client
	* @return ClientEntity
	* @throws Exception
	*/
	public ClientEntity create(ClientEntityCreation client) throws Exception;

	/**
	* update  client
	* 
	* @param id, client
	* @return ClientEntity
	* @throws Exception
	*/
	public ClientEntity update(Integer id, ClientEntity client) throws Exception;
	

	/**
	* delete client
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
	public void delete(Integer id) throws Exception;
	
}
