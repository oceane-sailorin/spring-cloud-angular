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
 * client creation dto
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ClientEntityCreation {


    @ApiModelProperty(notes = "client code", required = true)
    @NotNull(message = "{error.client.code.required}")
    private String code;
    
    @ApiModelProperty(notes = "client naming", required = true)
    @NotNull(message = "{error.client.naming.required}")
    private String naming;    
 
    @DateTimeFormat
    private Date dateCreation;

    @DateTimeFormat
    private Date dateArchive;
    
}
