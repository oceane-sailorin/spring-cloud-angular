package com.sba.adapters.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.models.entities.ClientEntity;
import com.sba.models.entities.ClientEntityCreation;

public interface ClientDAO extends JpaRepository<ClientEntity,Integer>{

	ClientEntity saveAndFlush(ClientEntityCreation client);
}
