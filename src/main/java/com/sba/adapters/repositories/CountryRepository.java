package com.sba.adapters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sba.adapters.interfaces.CountryDAO;
import com.sba.models.entities.CountryEntity;

import lombok.NonNull;

/**
 * country repository
 * 
 */
@Repository
public class CountryRepository {

	@Autowired
	private CountryDAO countryDAO;

	 /**
     * get all country
     * 
     * @param 
     * @return List<CountryEntity>
     * @throws 
     */
	public List<CountryEntity> getAll() 
    {                
	   return countryDAO.findAll();
	}

	 /**
     * find country by id
     * 
     * @param id
     * @return CountryEntity
     * @throws 
     */
	public CountryEntity findById(@NonNull Integer id) {
		CountryEntity countryEntity= null;
		Optional<CountryEntity> value = countryDAO.findById(id);
		countryEntity = value.isPresent() ? value.get(): null;
		return countryEntity;
	}

	 /**
     * create country
     * 
     * @param country
     * @return CountryEntity
     * @throws 
     */
	public CountryEntity create(CountryEntity country) {
		return countryDAO.saveAndFlush(country);
	}

	 /**
     * update country
     * 
     * @param country
     * @return CountryEntity
     * @throws 
     */
	public CountryEntity update(CountryEntity country) {
		return countryDAO.saveAndFlush(country);
	}
	
	 /**
     * delete country
     * 
     * @param id
     * @return 
     * @throws 
     */
	public void delete(Integer id) {
		countryDAO.deleteById(id);
	}
}
