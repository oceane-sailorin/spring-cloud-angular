package com.sba.models.entities;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * candidate status creation dto
 * 
 */
@Getter
@Setter
@Table(name = "candidates_status")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CandidateStatusEntityCreation {

	@ApiModelProperty(notes = "Code of the status", required = true)
    @NotNull(message = "{error.candidate_status.code.required}")
    @Column(name="candidate_status_code", nullable = false)
    private String code;
    
    @ApiModelProperty(notes = "Name of the status", required = true)
    @NotNull(message = "{error.candidate_status.naming.required}")
    @Column(name="candidate_status_naming", nullable = false)
    private String naming;
    
    @ApiModelProperty(notes = "Order of the status", required = true)
    @NotNull(message = "{error.candidate_status.order.required}")
    @Column(name="candidate_status_order", nullable = false)
    private int order;    
	    
}
