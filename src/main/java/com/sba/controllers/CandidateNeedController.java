package com.sba.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sba.models.entities.CandidateNeedEntity;
import com.sba.models.entities.CandidateNeedEntityCreation;
import com.sba.models.entities.CandidateNeedEntityStatusDateUpdate;
import com.sba.models.entities.CandidateNeedEntityStatusUpdate;
import com.sba.services.CandidateNeedManager;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/candidatesneeds", produces = "application/json;charset=utf-8")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Success"), 
		@ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"), 
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure") })
public class CandidateNeedController {

	 @Autowired
	 private CandidateNeedManager candidateNeedManager;
	     
	 /**
	     * get all candidateNeeds
	     * 
	     * @param params
	     * @return list of  candidateNeed entities
	     * @throws Exception
	     */
	    @GetMapping
	    @ApiImplicitParams({ @ApiImplicitParam(value = "Pagination start", name = "_page", paramType = "query", dataType = "int"),
	     @ApiImplicitParam(value = "Max number per page", name = "_perPage", paramType = "query", dataType = "int"),
	     @ApiImplicitParam(value = "Sort column", name = "_sortColumn", paramType = "query", dataType = "String"),
	     @ApiImplicitParam(value = "Sort order", name = "_sortOrder", paramType = "query", dataType = "String"),
	     @ApiImplicitParam(value = "Filter on id", name = "id", paramType = "query", dataType = "int"),
	     @ApiImplicitParam(value = "Filter on login", name = "login", paramType = "query", dataType = "String"),	    
	     @ApiImplicitParam(name = "Authorization", value = "Authorization token", dataType = "string", paramType = "header") })
	     @ResponseBody
	    public List<CandidateNeedEntity> getAllCandidateNeeds(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return candidateNeedManager.getAllCandidateNeeds(params);
	    }
	    
	    /**
	     * Create the candidateNeed
	     * 
	     * @param candidateNeed
	     * @return the new candidateNeed entity
	     * @throws Exception
	     */
	    @PostMapping(consumes = "application/json")
	    @ApiOperation(value = "create")
	    public int create(@RequestBody CandidateNeedEntityCreation candidateNeed) throws Exception {
	        return candidateNeedManager.create(candidateNeed);
	    }

	    /**
	     * Get the candidateNeed
	     * 
	     * @param id
	     * @return a candidateNeed entity
	     * @throws Exception
	     */
	    @GetMapping(value = "/{id:.+}")
	    @ApiOperation(value = "get")
	    @ResponseBody
	    public CandidateNeedEntity get(@PathVariable("id") Integer id) throws Exception {
	        return candidateNeedManager.get(id);
	    }

	    /**
	     * Update candidateNeed
	     * 
	     * @param id , 	 CandidateNeedEntity   
	     * @return CandidateNeedEntity
	     * @throws Exception
	     */
	    @PutMapping(value = "/{id}",consumes = "application/json")
	    @ApiOperation(value = "update")
	    public CandidateNeedEntity update(@PathVariable("id") Integer id, @RequestBody CandidateNeedEntityCreation candidateNeed) throws Exception {
	        return candidateNeedManager.update(id, candidateNeed);
	    }	 
	    

	    /**
	     * Update candidateNeed status
	     * 
	     * @param id , 	 CandidateNeedEntityStatusUpdate   
	     * @return CandidateNeedEntity
	     * @throws Exception
	     */
	    @PutMapping(value = "/status/{id}",consumes = "application/json")
	    @ApiOperation(value = "update")
	    public CandidateNeedEntity updateStatus(@PathVariable("id") Integer id, @RequestBody CandidateNeedEntityStatusUpdate candidateNeed) throws Exception {
	        return candidateNeedManager.updateStatus(id, candidateNeed);
	    }	 	 
	    
	    /**
	     * Update candidateNeed status date
	     * 
	     * @param id , 	 CandidateNeedEntityStatusDateUpdate   
	     * @return CandidateNeedEntity
	     * @throws Exception
	     */
	    @PutMapping(value = "/statusdate/{id}",consumes = "application/json")
	    @ApiOperation(value = "update")
	    public CandidateNeedEntity updateStatusDate(@PathVariable("id") Integer id, @RequestBody CandidateNeedEntityStatusDateUpdate candidateNeed) throws Exception {
	        return candidateNeedManager.updateStatusDate(id, candidateNeed);
	    }	 	  	    
	    
	    /**
	     * delete candidateNeed
	     * 
	     * @param id   
	     * @return CandidateNeedEntity
	     * @throws Exception
	     */
	    @DeleteMapping(value = "/{id}")
	    @ApiOperation(value = "delete")
	    public  Map<String, Boolean> delete(@PathVariable("id") Integer id) throws Exception {
	       candidateNeedManager.delete(id);
	        Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	    }	
	    
}
