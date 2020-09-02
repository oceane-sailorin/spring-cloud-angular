package com.sba.services;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.sba.models.entities.CountryEntity;

/**
 * country interface repository
 * 
 */
public interface CountryManager {

	/**
	* get all countries
	* 
	* @param params
	* @return List<CountryEntity>
	* @throws Exception
	*/
	public List<CountryEntity> getAllCountries(@NotNull Map<String, Object> params) throws Exception; 

	/**
	* get one country
	* 
	* @param id
	* @return CountryEntity
	* @throws Exception
	*/
	public CountryEntity get(Integer id) throws Exception; 

	/**
	* create country
	* 
	* @param country
	* @return CountryEntity
	* @throws Exception
	*/
	public CountryEntity create(CountryEntity country) throws Exception;

	/**
	* update  country
	* 
	* @param id, country
	* @return CountryEntity
	* @throws Exception
	*/
	public CountryEntity update(Integer id, CountryEntity country) throws Exception;

	/**
	* delete country
	* 
	* @param id
	* @return 
	* @throws Exception
	*/
	public void delete(Integer id) throws Exception;
	
}
