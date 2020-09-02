package com.sba.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * user entity
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;
    
    @ManyToOne(targetEntity = ProfileEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile", referencedColumnName = "profile_id")
    private ProfileEntity profile;

    @ManyToOne(targetEntity = UserStatusEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_status", referencedColumnName = "user_status_id")
    private UserStatusEntity status;    

    @ApiModelProperty(notes = "user login", required = true)
    @NotNull(message = "{error.user.login.required}")
    @Column(name="user_login", nullable = false)
    private String login;
    
    @ApiModelProperty(notes = "user lastname", required = true)
    @NotNull(message = "{error.user.lastname.required}")
    @Column(name="user_lastname", nullable = false)
    private String lastname;
    
    @ApiModelProperty(notes = "user firstname", required = true)
    @NotNull(message = "{error.user.firstname.required}")
    @Column(name="user_firstname", nullable = false)
    private String firstname;    
 
    @DateTimeFormat
    @Column(name = "user_date_creation")
    private Date dateCreation;

    @DateTimeFormat
    @Column(name = "user_date_activation")
    private Date dateActivation;
    
    @DateTimeFormat
    @Column(name = "user_date_last_connexion")
    private Date dateLastConnection;
    
    @DateTimeFormat
    @Column(name = "user_date_archive")
    private Date dateArchive;
    
    
  

}
