package com.sba.adapters.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.models.entities.PriorityEntity;

/**
 * priority repository interface
 * 
 */
public interface PriorityDAO extends JpaRepository<PriorityEntity,Integer>{

}
