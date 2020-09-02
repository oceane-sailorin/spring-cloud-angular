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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * user status entity
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "users_status")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class UserStatusEntity implements Serializable {

private static final long serialVersionUID = 1L;
	 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_status_id", nullable = false)
    private Integer id;
    
    @ApiModelProperty(notes = "user status code", required = true)
    @NotNull(message = "{error.user_status.code.required}")
    @Column(name="user_status_code", nullable = false, unique = true)
    private String code;    
    
    @ApiModelProperty(notes = "user status name", required = true)
    @NotNull(message = "{error.user_status.naming.required}")
    @Column(name="user_status_naming", nullable = false, unique = true)
    private String naming;        
}
