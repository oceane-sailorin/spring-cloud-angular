package com.sba.models.entities;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * keyword creation entity
 * 
 */
@Getter
@Setter
@Table(name = "keywords")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class KeywordEntityCreation {

	   @ApiModelProperty(notes = "Code of the keyword", required = true)
	   @NotNull(message = "{error.keyword.code.required}")
	   @Column(name="keyword_code", nullable = false)
	   private String code;
	       
}
