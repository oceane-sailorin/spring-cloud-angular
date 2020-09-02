package com.sba.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * sources entity
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "sources")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class SourceEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "source_id", nullable = false)
    private Integer id;

    @ApiModelProperty(notes = "Name of the source", required = true)
    @NotNull(message = "{error.source.naming.required}")
    @Column(name="source_naming", nullable = false)
    private String naming;

}
