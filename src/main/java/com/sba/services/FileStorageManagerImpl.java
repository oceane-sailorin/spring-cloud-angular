package com.sba.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageManagerImpl implements FileStorageManager {

	
	 private final Path UPLOADED_FOLDER = Paths.get("C:\\Users\\gmouette\\AppData\\Local\\Uploads");
	 
	  @Autowired
	  NeedManager needManager;
	  
	  @Autowired
	  CandidateManager candidateManager;
	  
	  @Override
	  public void init() {
	    try {
	      Files.createDirectory(UPLOADED_FOLDER);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }

	  @Override
	  public void saveNeed(MultipartFile file, String name, Integer id) {		  
	    try {
	      //Files.copy(file.getInputStream(), this.UPLOADED_FOLDER.resolve(file.getOriginalFilename()));
	    	Files.copy(file.getInputStream(), this.UPLOADED_FOLDER.resolve(name));	
			needManager.updateFile(id, name);
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }
	  
	  @Override
	  public void saveCandidate(MultipartFile file, String name, Integer id) {
	    try {
	      //Files.copy(file.getInputStream(), this.UPLOADED_FOLDER.resolve(file.getOriginalFilename()));
	    	Files.copy(file.getInputStream(), this.UPLOADED_FOLDER.resolve(name));
	    	candidateManager.updateFile(id, name);
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }	  

	  @Override
	  public Resource load(String filename) {
	    try {
	      Path file = UPLOADED_FOLDER.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	  @Override
	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(UPLOADED_FOLDER.toFile());
	  }
	  
	  @Override
	  public void delete(String fileName) {
		  try {
			  Files.delete(this.UPLOADED_FOLDER.resolve(fileName)); 
		  } catch (Exception e) {
		      throw new RuntimeException("Could not delete the file. Error: " + e.getMessage());
		  }
	  }	  

	  @Override
	  public Stream<Path> loadAll() {
	    try {
	      return Files.walk(this.UPLOADED_FOLDER, 1).filter(path -> !path.equals(this.UPLOADED_FOLDER)).map(this.UPLOADED_FOLDER::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }

	}
