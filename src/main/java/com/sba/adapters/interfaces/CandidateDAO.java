package com.sba.adapters.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sba.models.entities.CandidateEntity;
import com.sba.models.entities.CandidateEntityCreation;

/**
 * candidate repository interface
 * 
 */
public interface CandidateDAO extends JpaRepository<CandidateEntity,Integer>{

	CandidateEntity saveAndFlush(CandidateEntityCreation candidate);
	
	/**
	 * get by status
	 * @param status
	 * @return List<CandidateEntity>
	 */
	@Query("select ce from CandidateEntity ce where ce.candidateStatus.id = :status ")
	List<CandidateEntity> findByCandidateStatus(@Param("status") Integer status);
}
