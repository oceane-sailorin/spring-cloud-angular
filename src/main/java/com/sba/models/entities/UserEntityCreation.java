package com.sba.models.entities;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * user creation dto
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class UserEntityCreation {

	
    private int profile;

    private int status;    

    @ApiModelProperty(notes = "user login", required = true)
    @NotNull(message = "{error.user.login.required}")
    private String login;
    
    @ApiModelProperty(notes = "user lastname", required = true)
    @NotNull(message = "{error.user.lastname.required}")
    private String lastname;
    
    @ApiModelProperty(notes = "user firstname", required = true)
    @NotNull(message = "{error.user.firstname.required}")
    private String firstname;    
 
    @DateTimeFormat
    private Date dateCreation;

    @DateTimeFormat
    private Date dateActivation;
    
    @DateTimeFormat
    private Date dateLastConnection;
    
    @DateTimeFormat
    private Date dateArchive;
    
}
