package com.sba.adapters.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sba.models.entities.CandidateStatusEntity;

/**
 * candidates status repository interface
 * 
 */
public interface CandidateStatusDAO extends JpaRepository<CandidateStatusEntity,Integer>{
	
	/**
	 * get by code
	 * @param code
	 * @return List<CandidateStatusEntity>
	 */
	@Query("select cs from CandidateStatusEntity cs where cs.code = :code ")
	List<CandidateStatusEntity> findByCode(@Param("code") String code);
	
	/**
	 * get by id
	 * @param id
	 * @return CandidateStatusEntity
	 */
	@Query("select cs from CandidateStatusEntity cs where cs.id = :id ")
	CandidateStatusEntity findById(@Param("id") int id);	
	
	/**
	 * get all order by order
	 * @param 
	 * @return CandidateStatusEntity
	 */
	@Query("select cs from CandidateStatusEntity cs order by cs.order ")
	List<CandidateStatusEntity> findAllByOrder();		

}
