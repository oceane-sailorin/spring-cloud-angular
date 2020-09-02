package com.sba.adapters.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sba.models.entities.KeywordEntity;
import com.sba.models.entities.KeywordEntityCreation;

/**
 * keyword repository interface
 * 
 */
public interface KeywordDAO extends JpaRepository<KeywordEntity,Integer>{

	KeywordEntity saveAndFlush(KeywordEntityCreation keyword);
}
