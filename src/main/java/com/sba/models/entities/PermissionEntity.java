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
 * permission entity
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "permissions")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class PermissionEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id", nullable = false)
    private Integer id;
    
    @ApiModelProperty(notes = "permission code", required = true)
    @NotNull(message = "{error.permission.code.required}")
    @Column(name="permission_code", nullable = false, unique = true)
    private String code;    
    
    @ApiModelProperty(notes = "permission name", required = true)
    @NotNull(message = "{error.permission.naming.required}")
    @Column(name="permission_naming", nullable = false, unique = true)
    private String naming; 
    
    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<ProfileEntity> profiles = new HashSet<>();      
    
  
}
