package com.sba.models.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
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
public class CandidateEntityCreation {

	    @ApiModelProperty(notes = "Identity", required = true)
	    @NotNull(message = "{error.candidate.identity.required}")
	    private String identity;
	    
	    @ApiModelProperty(notes = "Gender", required = true)
	    @NotNull(message = "{error.candidate.gender.required}")
	    private String gender;
	    
	    @ApiModelProperty(notes = "LastName", required = true)
	    @NotNull(message = "{error.candidate.lastname.required}")
	    private String lastname;
	    
	    @ApiModelProperty(notes = "FirstName", required = true)
	    @NotNull(message = "{error.candidate.firstname.required}")
	    private String firstname;
	    
	    @ApiModelProperty(notes = "Email")
	    private String email;    
	    
	    @ApiModelProperty(notes = "Phone")
	    private String phone;   
	    
	    @ApiModelProperty(notes = "Address")
	    private String address;  
	    
	    private int country;    
	    
	    private int recruiter;    
	   	   
	    private int sourcer;    
	    
	    private int source;    

	    private int candidateStatus;        
	    
	    @ApiModelProperty(notes = "Stack")
	    private String stack;  
	    
	    @ApiModelProperty(notes = "Profile")
	    private String profile;  
	    
	    @ApiModelProperty(notes = "Salary")	   
	    private String salary;    
	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Temporal(TemporalType.DATE)
	    private Date dateDisponibility;
	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Temporal(TemporalType.DATE)
	    private Date dateInterview;
	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Temporal(TemporalType.DATE)
	    private Date dateSourcing;
	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Temporal(TemporalType.DATE)
	    private Date dateProposition;
	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Temporal(TemporalType.DATE)
	    private Date dateSignature;
	    
	    @DateTimeFormat
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date dateLastUpdate;
	    
	    private Set<KeywordEntity> keywords = new HashSet<>();  
}
