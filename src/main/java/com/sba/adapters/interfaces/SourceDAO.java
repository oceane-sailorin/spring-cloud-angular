package com.sba.adapters.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.models.entities.SourceEntity;

/**
 * source repository interface
 * 
 */
public interface SourceDAO extends JpaRepository<SourceEntity,Integer>{

}
