package com.sba.adapters.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sba.models.entities.CandidateNeedEntity;
import com.sba.models.entities.CandidateNeedEntityCreation;

/**
 * candidate need repository interface
 * 
 */
public interface CandidateNeedDAO extends JpaRepository<CandidateNeedEntity,Integer>{

	CandidateNeedEntity saveAndFlush(CandidateNeedEntityCreation needCandidate);
	
	/**
	 * get by status
	 * @param status
	 * @return List<NeedCandidateEntity>
	 */
	@Query("select ce from CandidateNeedEntity ce where ce.needCandidateStatus.id = :status ")
	List<CandidateNeedEntity> findByNeedCandidateStatus(@Param("status") Integer status);
	
	
	/**
	 * get by need
	 * @param need
	 * @return List<NeedCandidateEntity>
	 */
	@Query("select ce from CandidateNeedEntity ce where ce.need.id = :need ")
	List<CandidateNeedEntity> findByNeed(@Param("need") Integer need);	
	
	
	/**
	 * get by need and status
	 * @param need, status
	 * @return List<NeedCandidateEntity>
	 */
	@Query("select ce from CandidateNeedEntity ce where ce.need.id = :need and ce.needCandidateStatus.id = :status ")
	List<CandidateNeedEntity> findByNeedAndStatus(@Param("need") Integer need, @Param("status") Integer status);		
}

