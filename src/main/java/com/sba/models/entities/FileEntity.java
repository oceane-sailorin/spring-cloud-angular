package com.sba.models.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * file 
 * 
 */
@Getter
@Setter
public class FileEntity  {

	  private String name;
	  private String url;

	  public FileEntity(String name, String url) {
		    this.name = name;
		    this.url = url;
	  }

}
