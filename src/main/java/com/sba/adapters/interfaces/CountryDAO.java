package com.sba.adapters.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.models.entities.CountryEntity;

/**
 * country repository interface
 * 
 */
public interface CountryDAO extends JpaRepository<CountryEntity,Integer>{

}
