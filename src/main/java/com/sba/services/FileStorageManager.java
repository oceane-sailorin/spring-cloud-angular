package com.sba.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageManager {

	  public void init();

	  public void saveNeed(MultipartFile file, String name, Integer id);
	  
	  public void saveCandidate(MultipartFile file, String name, Integer id);

	  public Resource load(String filename);

	  public void deleteAll();
	  
	  public void delete(String filename);

	  public Stream<Path> loadAll();
}
