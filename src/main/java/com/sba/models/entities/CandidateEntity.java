package com.sba.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * candidates entity
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "candidates")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CandidateEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id", nullable = false)
    private Integer id;

    @ApiModelProperty(notes = "Identity", required = true)
    @NotNull(message = "{error.candidate.identity.required}")
    @Column(name="candidate_identity", nullable = false)
    private String identity;
    
    @ApiModelProperty(notes = "Gender", required = true)
    @NotNull(message = "{error.candidate.gender.required}")
    @Column(name="candidate_gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @ApiModelProperty(notes = "LastName", required = true)
    @NotNull(message = "{error.candidate.lastname.required}")
    @Column(name="candidate_lastname", nullable = false)
    private String lastname;
    
    @ApiModelProperty(notes = "FirstName", required = true)
    @NotNull(message = "{error.candidate.firstname.required}")
    @Column(name="candidate_firstname", nullable = false)
    private String firstname;
    
    @ApiModelProperty(notes = "Email", required = true)
    @NotNull(message = "{error.candidate.email.required}")
    @Column(name="candidate_email", nullable = false)
    private String email;    
    
    @ApiModelProperty(notes = "Phone", required = true)
    @NotNull(message = "{error.candidate.phone.required}")
    @Column(name="candidate_phone", nullable = false)
    private String phone;   
    
    @ApiModelProperty(notes = "Address", required = true)
    @NotNull(message = "{error.candidate.address.required}")
    @Column(name="candidate_address", nullable = false)
    private String address;  
    
    @ManyToOne(targetEntity = CountryEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_country", referencedColumnName = "country_id")
    private CountryEntity country;    
    
    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_recruiter", referencedColumnName = "user_id")
    private UserEntity recruiter;    
    
    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_sourcer", referencedColumnName = "user_id")
    private UserEntity sourcer;    
    
    @ManyToOne(targetEntity = SourceEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_source", referencedColumnName = "source_id")
    private SourceEntity source;    
    
    @ManyToOne(targetEntity = CandidateStatusEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_candidate_status", referencedColumnName = "candidate_status_id")
    private CandidateStatusEntity candidateStatus;        
    
    @ApiModelProperty(notes = "Stack", required = true)
    @NotNull(message = "{error.candidate.stack.required}")
    @Column(name="candidate_stack", nullable = false)
    private String stack;  
    
    @ApiModelProperty(notes = "Profile", required = true)
    @NotNull(message = "{error.candidate.profile.required}")
    @Column(name="candidate_profile", nullable = false)
    private String profile;  
    
    @ApiModelProperty(notes = "Salary", required = true)
    @NotNull(message = "{error.candidate.salary.required}")
    @Column(name="candidate_salary", nullable = false)
    private String salary;    
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "candidate_date_disponibility")
    private Date dateDisponibility;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "candidate_date_interview")
    private Date dateInterview;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "candidate_date_sourcing")
    private Date dateSourcing;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "candidate_date_proposition")
    private Date dateProposition;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "candidate_date_signature")
    private Date dateSignature;
    
    @DateTimeFormat
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "candidate_date_last_update")
    private Date dateLastUpdate;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "keywords_candidates", 
    joinColumns = @JoinColumn(name="keyword_candidate_candidate", referencedColumnName = "candidate_id"),
    inverseJoinColumns = @JoinColumn(name="keyword_candidate_keyword", referencedColumnName = "keyword_id"))
    private Set<KeywordEntity> keywords = new HashSet<>();  
    
    @ApiModelProperty(notes = "FileName")
    @Column(name="candidate_filename")
    private String filename;         
}
