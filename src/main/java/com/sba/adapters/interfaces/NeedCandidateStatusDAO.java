package com.sba.adapters.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sba.models.entities.NeedCandidateStatusEntity;

/**
 * needs candidate status repository interface
 * 
 */
public interface NeedCandidateStatusDAO extends JpaRepository<NeedCandidateStatusEntity,Integer>{

	/**
	 * get by code
	 * @param code
	 * @return List<NeedCandidateStatusEntity>
	 */
	@Query("select cs from NeedCandidateStatusEntity cs where cs.code = :code ")
	List<NeedCandidateStatusEntity> findByCode(@Param("code") String code);
	
	/**
	 * get by id
	 * @param id
	 * @return NeedCandidateStatusEntity
	 */
	@Query("select cs from NeedCandidateStatusEntity cs where cs.id = :id ")
	NeedCandidateStatusEntity findById(@Param("id") int id);	
	
	/**
	 * get all order by order
	 * @param 
	 * @return NeedCandidateStatusEntity
	 */
	@Query("select cs from NeedCandidateStatusEntity cs order by cs.order ")
	List<NeedCandidateStatusEntity> findAllByOrder();		

	
}
