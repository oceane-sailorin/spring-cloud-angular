package com.sba.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * candidates_needs entity
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "candidates_needs")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CandidateNeedEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	 
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_need_id", nullable = false)
    private Integer id;
	
	@ManyToOne(targetEntity = NeedEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_need_need", referencedColumnName = "need_id")
	private NeedEntity need;    
	
	@ManyToOne(targetEntity = CandidateEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_need_candidate", referencedColumnName = "candidate_id")
	private CandidateEntity candidate;    	
    
    @ManyToOne(targetEntity = NeedCandidateStatusEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_need_need_candidate_status", referencedColumnName = "need_candidate_status_id")
    private NeedCandidateStatusEntity needCandidateStatus;        
    
    @DateTimeFormat
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "candidate_need_date_creation")
    private Date dateCreation;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "candidate_need_date_sent")
    private Date dateSent;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "candidate_need_date_interview")
    private Date interviewDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "candidate_need_date_waiting_feedback")
    private Date dateFeedback;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "candidate_need_date_ok")
    private Date dateOk;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "candidate_need_date_ko_client")
    private Date dateKoClient;
    
    @DateTimeFormat
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "candidate_need_date_ko_candidate")
    private Date dateKoCandidate;
    
}
