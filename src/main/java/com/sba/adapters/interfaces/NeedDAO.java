package com.sba.adapters.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sba.models.entities.NeedEntity;
import com.sba.models.entities.NeedEntityCreation;

public interface NeedDAO extends JpaRepository<NeedEntity,Integer>{

	NeedEntity saveAndFlush(NeedEntityCreation need);
	
	@Query("select n from NeedEntity n where n.client.dateArchive is null and (n.dateEnd is null or n.dateEnd > now()) "
		+ "order by n.client.naming")
	public List<NeedEntity> getAllByClientNaming(); 
}
