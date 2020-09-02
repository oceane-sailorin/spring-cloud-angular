package com.sba.models.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * candidate creation dto
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CandidateNeedEntityCreation {
	
		
		private int need;    
			
		private int candidate;    	
	        
	    private int needCandidateStatus;        
	    
	    @DateTimeFormat
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date dateCreation;
	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Temporal(TemporalType.DATE)
	    private Date dateSent;
	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Temporal(TemporalType.DATE)
	    private Date interviewDate;
	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Temporal(TemporalType.DATE)
	    private Date dateFeedback;
	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Temporal(TemporalType.DATE)
	    private Date dateOk;
	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Temporal(TemporalType.DATE)
	    private Date dateKoClient;
	    
	    @DateTimeFormat
	    @Temporal(TemporalType.TIMESTAMP)	    
	    private Date dateKoCandidate;

}
