package com.sba.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * client entity
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "clients")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ClientEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    private Integer id;
    
    @ApiModelProperty(notes = "client code", required = true)
    @NotNull(message = "{error.client.code.required}")
    @Column(name="client_code", nullable = false)
    private String code;
    
    @ApiModelProperty(notes = "client name", required = true)
    @NotNull(message = "{error.client.naming.required}")
    @Column(name="client_naming", nullable = false)
    private String naming;    
 
    @DateTimeFormat
    @Column(name = "client_date_creation")
    private Date dateCreation;
    
    @DateTimeFormat
    @Column(name = "client_date_archive")
    private Date dateArchive;
    

}
