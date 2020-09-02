package com.sba.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sba.adapters.repositories.CountryRepository;
import com.sba.models.entities.CountryEntity;

/**
 * country service
 * 
 */
@Service
public class CountryManagerImpl implements CountryManager{

	  @Autowired
	   CountryRepository countryRepository;
	    
		/**
		* get all countries
		* 
		* @param params
		* @return List<CountryEntity>
		* @throws Exception
		*/   
	   public List<CountryEntity> getAllCountries(@NotNull Map<String, Object> params) throws Exception  {	   
		  
		   List<CountryEntity> countrys = new ArrayList<>();
		   try {
		       List<CountryEntity> entities = countryRepository.getAll();
			   for (CountryEntity country : entities) {	   	    		
				   	countrys.add(country);
			   }  
		    } catch (Exception e) {        
	             throw new Exception("error.country.get");
	        }			   
	       return countrys;
	      
	   }
	 
		/**
		* get one country
		* 
		* @param id
		* @return CountryEntity
		* @throws Exception
		*/
	   public CountryEntity get(Integer id) throws Exception {
		   return countryRepository.findById(id);
	   }

		/**
		* create country
		* 
		* @param country
		* @return CountryEntity
		* @throws Exception
		*/
	   public CountryEntity create(CountryEntity country) throws Exception {	
		   CountryEntity countryEntity = new CountryEntity();
		   try {
			   countryEntity.setCode(country.getCode());
			   countryEntity.setNamingEn(country.getNamingEn());
			   countryEntity.setNaming(country.getNaming());
		   } catch (Exception e) {        
	             throw new Exception("error.country.create");
	       }			   
		   return countryRepository.create(countryEntity);
	   }

		/**
		* update country
		* 
		* @param id, country
		* @return CountryEntity
		* @throws Exception
		*/
	   public CountryEntity update(Integer id, CountryEntity country) throws Exception {	
		   return countryRepository.update(country);
		}
	 
		/**
		* delete country
		* 
		* @param id
		* @return 
		* @throws Exception
		*/
	   public void delete(Integer id) throws Exception {	
		   countryRepository.delete(id);
		}
	  
	   
}
