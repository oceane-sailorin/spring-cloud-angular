package com.sba.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.ClientDAO;
import com.sba.models.entities.ClientEntity;

import lombok.NonNull;

/**
 * client repository
 * 
 */
@Repository
public class ClientRepository {

	@Autowired
	private ClientDAO clientDAO;

	 /**
     * get all clients
     * 
     * @param 
     * @return List<ClientEntity>
     * @throws 
     */
	public List<ClientEntity> getAll() 
    {                
	   return clientDAO.findAll();
	}

	 /**
     * find client by id
     * 
     * @param id
     * @return ClientEntity
     * @throws 
     */
	public ClientEntity findById(@NonNull Integer id) {
		ClientEntity clientEntity= null;
		Optional<ClientEntity> value = clientDAO.findById(id);
		clientEntity = value.isPresent() ? value.get(): null;
		return clientEntity;
	}

	 /**
     * create client
     * 
     * @param client
     * @return ClientEntity
     * @throws 
     */
	public ClientEntity create(ClientEntity client) {
		return clientDAO.saveAndFlush(client);
	}

	 /**
     * update client
     * 
     * @param client
     * @return ClientEntity
     * @throws 
     */
	public ClientEntity update(ClientEntity client) {
		return clientDAO.saveAndFlush(client);
	}
	
	 /**
     * delete client
     * 
     * @param id
     * @return 
     * @throws 
     */
	public void delete(Integer id) {
		clientDAO.deleteById(id);
	}
}

