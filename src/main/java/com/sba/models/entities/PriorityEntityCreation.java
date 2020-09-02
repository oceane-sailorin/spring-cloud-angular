package com.sba.models.entities;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * priority creation dto
 * 
 */
@Getter
@Setter
@Table(name = "priorities")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class PriorityEntityCreation {
	
	@ApiModelProperty(notes = "Code of the priority", required = true)
    @NotNull(message = "{error.priority.code.required}")
    @Column(name="priority_code", nullable = false)
    private String code;
    
    @ApiModelProperty(notes = "Name of the priority", required = true)
    @NotNull(message = "{error.priority.naming.required}")
    @Column(name="priority_naming", nullable = false)
    private String naming;

}
