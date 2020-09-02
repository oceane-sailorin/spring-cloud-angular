package com.sba.models.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * keyword entity
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "keywords")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class KeywordEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id", nullable = false)
    private Integer id;

    @ApiModelProperty(notes = "Code of the keyword", required = true)
    @NotNull(message = "{error.keyword.code.required}")
    @Column(name="keyword_code", nullable = false)
    private String code;
    
    @ManyToMany(mappedBy = "keywords", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<CandidateEntity> candidates = new HashSet<>();          

}
