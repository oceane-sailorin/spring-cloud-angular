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
 * country entity
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "countries")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CountryEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", nullable = false)
    private Integer id;

    @ApiModelProperty(notes = "Code of the country", required = true)
    @NotNull(message = "{error.country.code.required}")
    @Column(name="country_code", nullable = false)
    private String code;
    
    @ApiModelProperty(notes = "English name of the country", required = true)
    @NotNull(message = "{error.country.naming_en.required}")
    @Column(name="country_naming_en", nullable = false)
    private String namingEn;   
    
    @ApiModelProperty(notes = "Name of the country", required = true)
    @NotNull(message = "{error.country.naming.required}")
    @Column(name="country_naming", nullable = false)
    private String naming;    
}
