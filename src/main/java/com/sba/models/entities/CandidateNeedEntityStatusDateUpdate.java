package com.sba.models.entities;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * candidate need update status + date dto
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CandidateNeedEntityStatusDateUpdate {

	 private int id;

	 private int needCandidateStatus;   
	 
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 @Temporal(TemporalType.DATE)
	 private Date dateUpdate;
}
