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
 * need status entity
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "priorities")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class PriorityEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_id", nullable = false)
    private Integer id;

    @ApiModelProperty(notes = "Code of the priority", required = true)
    @NotNull(message = "{error.priority.code.required}")
    @Column(name="priority_code", nullable = false)
    private String code;
    
    @ApiModelProperty(notes = "Name of the priority", required = true)
    @NotNull(message = "{error.priority.naming.required}")
    @Column(name="priority_naming", nullable = false)
    private String naming;

}
