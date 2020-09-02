package com.sba.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.adapters.repositories.ClientRepository;
import com.sba.models.entities.ClientEntity;
import com.sba.models.entities.ClientEntityCreation;


/**
 * client service
 * 
 */
@Service
public class ClientManagerImpl implements ClientManager{

	  @Autowired
	   ClientRepository clientRepository;
	  


	    
		/**
		* get all clients
		* 
		* @param params
		* @return List<ClientEntity>
		* @throws Exception
		*/   
	   public List<ClientEntity> getAllClients(@NotNull Map<String, Object> params) throws Exception  {	   
		  
		   List<ClientEntity> clients = new ArrayList<>();
		   try {
		       List<ClientEntity> entities = clientRepository.getAll();
			   for (ClientEntity client : entities) {	   	    		
				   	clients.add(client);
			   }  
		   } catch (Exception e) {        
	             throw new Exception("error.client.get");
	       }			   
	       return clients;
	      
	   }
	 
		/**
		* get one client
		* 
		* @param id
		* @return ClientEntity
		* @throws Exception
		*/
	   public ClientEntity get(Integer id) throws Exception {
		   return clientRepository.findById(id);
	   }

		/**
		* create client
		* 
		* @param client
		* @return ClientEntity
		* @throws Exception
		*/
	   public ClientEntity create(ClientEntityCreation client) throws Exception {	
		   ClientEntity clientEntity = new ClientEntity();
		   try {
			   clientEntity.setCode(client.getCode());
			   clientEntity.setNaming(client.getNaming());	  
			
			   clientEntity.setDateCreation(new Date());
			   clientEntity.setDateArchive(client.getDateArchive());
		   } catch (Exception e) {        
	             throw new Exception("error.client.create");
	       }		
		   return clientRepository.create(clientEntity);
	   }

		/**
		* update client
		* 
		* @param id, client
		* @return ClientEntity
		* @throws Exception
		*/
	   public ClientEntity update(Integer id, ClientEntity client) throws Exception {	
		   return clientRepository.update(client);
		}
	   
	 
		/**
		* delete client
		* 
		* @param id
		* @return 
		* @throws Exception
		*/
	   public void delete(Integer id) throws Exception {	
		   clientRepository.delete(id);
		}
	  
	   
}
