package com.sba.models.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *creation entity for profile
 * 
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ProfileEntityCreation {
	
	@NotNull
	@Pattern(regexp = "^[\\w\\s]{3,100}$", message = "{error.profile.naming.invalid}")
	@ApiModelProperty(notes = "The name of the Profile", required = true)
	private String naming;

   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
   @JoinTable(name = "profiles_permissions", 
   joinColumns = @JoinColumn(name="profile_permission_profile"),
   inverseJoinColumns = @JoinColumn(name="profile_permission_permission"))
   private Set<PermissionEntity> permissions = new HashSet<>();  
   
  
}



