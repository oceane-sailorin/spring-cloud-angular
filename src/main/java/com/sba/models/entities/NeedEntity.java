package com.sba.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
 * needs entity
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "needs")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class NeedEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "need_id", nullable = false)
    private Integer id;

    @ApiModelProperty(notes = "Title", required = true)
    @NotNull(message = "{error.need.title.required}")
    @Column(name="need_title", nullable = false)
    private String title;
    
    @ManyToOne(targetEntity = NeedStatusEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "need_status", referencedColumnName = "need_status_id")
    private NeedStatusEntity status;
    
    @ManyToOne(targetEntity = PriorityEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "need_priority", referencedColumnName = "priority_id")
    private PriorityEntity priority;
    
    @ManyToOne(targetEntity = ClientEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "need_client", referencedColumnName = "client_id")
    private ClientEntity client;    
    
    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "need_commercial", referencedColumnName = "user_id")
    private UserEntity commercial;    
    
    @ApiModelProperty(notes = "Poste")
    @Column(name="need_poste")
    private String poste;  
    
    @ApiModelProperty(notes = "Departement")
    @Column(name="need_departement")
    private String departement;     
    
    @ApiModelProperty(notes = "Description", required = false)
    @Column(name="need_description")
    private String description;    
    
    @DateTimeFormat
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "need_date_creation")
    private Date dateCreation;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "need_date_need")
    private Date dateNeed;
    
    @DateTimeFormat
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "need_date_end")
    private Date dateEnd;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "keywords_needs", 
    joinColumns = @JoinColumn(name="keyword_need", referencedColumnName = "need_id"),
    inverseJoinColumns = @JoinColumn(name="keyword_need_keyword", referencedColumnName = "keyword_id"))
    private Set<KeywordEntity> keywords = new HashSet<>();     
    
    @ApiModelProperty(notes = "FileName")
    @Column(name="need_filename")
    private String filename;      
}
