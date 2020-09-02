package com.sba.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.sba.models.ResponseMessage;
import com.sba.models.entities.FileEntity;
import com.sba.services.FileStorageManager;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/uploads")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Success"), 
		@ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"), 
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure") })
public class FileStorageController {

	  @Autowired
	  FileStorageManager fileStorageManager;

	  @PostMapping("/need")
	  public ResponseEntity<ResponseMessage> uploadFileNeed(@RequestParam("file") MultipartFile file, String name, Integer id) {
	    String message = "";
	    try {
	    	fileStorageManager.saveNeed(file, name, id);

	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }
	  
	  @PostMapping("/candidate")
	  public ResponseEntity<ResponseMessage> uploadFileCandidate(@RequestParam("file") MultipartFile file, String name, Integer id) {
	    String message = "";
	    try {
	    	fileStorageManager.saveCandidate(file, name, id);

	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }	  

	  @GetMapping
	  public ResponseEntity<List<FileEntity>> getListFiles() {
	    List<FileEntity> fileInfos = fileStorageManager.loadAll().map(path -> {
	      String filename = path.getFileName().toString();
	      String url = MvcUriComponentsBuilder
	          .fromMethodName(FileStorageController.class, "getFile", path.getFileName().toString()).build().toString();

	      return new FileEntity(filename, url);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	  }
	 
	  @GetMapping("/{filename:.+}")
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = fileStorageManager.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	  
	
	}
