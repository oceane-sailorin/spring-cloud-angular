package com.sba.adapters.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.models.entities.NeedStatusEntity;


/**
 * needs status repository interface
 * 
 */
public interface NeedStatusDAO extends JpaRepository<NeedStatusEntity,Integer>{

}
