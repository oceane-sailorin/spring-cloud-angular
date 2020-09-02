package com.sba.models.entities;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * need status creation dto
 * 
 */
@Getter
@Setter
@Table(name = "needs_status")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class NeedStatusEntityCreation {

	@ApiModelProperty(notes = "Code of the status", required = true)
    @NotNull(message = "{error.need_status.code.required}")
    @Column(name="need_status_code", nullable = false)
    private String code;
    
    @ApiModelProperty(notes = "Name of the status", required = true)
    @NotNull(message = "{error.need_status.naming.required}")
    @Column(name="need_status_naming", nullable = false)
    private String naming;
}
