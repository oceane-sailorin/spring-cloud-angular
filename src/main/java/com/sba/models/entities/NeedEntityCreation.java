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
 * needs creation dto
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class NeedEntityCreation {
	
    @ApiModelProperty(notes = "Title", required = true)
    @NotNull(message = "{error.need.title.required}")
	private String title;
    
    @ApiModelProperty(notes = "Gender", required = true)
    @NotNull(message = "{error.candidate.gender.required}")
    private String gender;    
    
    private int status;
    
    private int priority;    
    
    private int client;    
    
    private int commercial;    
    
    @ApiModelProperty(notes = "Poste")
    private String poste;
    
    @ApiModelProperty(notes = "Departement")
    private String departement;
    
    @ApiModelProperty(notes = "Description")
    private String description;   
   
    @DateTimeFormat
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    
    @Temporal(TemporalType.DATE)
    private Date dateNeed;
    
    @DateTimeFormat
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;
    
    private Set<KeywordEntity> keywords = new HashSet<>();  

}
