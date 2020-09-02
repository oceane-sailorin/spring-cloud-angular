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

import com.sba.models.entities.CandidateEntity;
import com.sba.models.entities.CandidateEntityCreation;
import com.sba.models.entities.CandidateEntityStatusDateUpdate;
import com.sba.models.entities.CandidateEntityStatusUpdate;
import com.sba.services.CandidateManager;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/candidates", produces = "application/json;charset=utf-8")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Success"), 
		@ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"), 
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure") })
public class CandidateController {

	 @Autowired
	 private CandidateManager candidateManager;
	     
	 /**
	     * get all candidates
	     * 
	     * @param params
	     * @return list of  candidate entities
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
	    public List<CandidateEntity> getAllCandidates(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return candidateManager.getAllCandidates(params);
	    }
	    
	    /**
	     * Create the candidate
	     * 
	     * @param candidate
	     * @return the new candidate entity
	     * @throws Exception
	     */
	    @PostMapping(consumes = "application/json")
	    @ApiOperation(value = "create")
	    public int create(@RequestBody CandidateEntityCreation candidate) throws Exception {
	        return candidateManager.create(candidate);
	    }

	    /**
	     * Get the candidate
	     * 
	     * @param id
	     * @return a candidate entity
	     * @throws Exception
	     */
	    @GetMapping(value = "/{id:.+}")
	    @ApiOperation(value = "get")
	    @ResponseBody
	    public CandidateEntity get(@PathVariable("id") Integer id) throws Exception {
	        return candidateManager.get(id);
	    }

	    /**
	     * Update candidate
	     * 
	     * @param id , 	 CandidateEntity   
	     * @return CandidateEntity
	     * @throws Exception
	     */
	    @PutMapping(value = "/{id}",consumes = "application/json")
	    @ApiOperation(value = "update")
	    public CandidateEntity update(@PathVariable("id") Integer id, @RequestBody CandidateEntityCreation candidate) throws Exception {
	        return candidateManager.update(id, candidate);
	    }	 
	    

	    /**
	     * Update candidate status
	     * 
	     * @param id , 	 CandidateEntityStatusUpdate   
	     * @return CandidateEntity
	     * @throws Exception
	     */
	    @PutMapping(value = "/status/{id}",consumes = "application/json")
	    @ApiOperation(value = "update")
	    public CandidateEntity updateStatus(@PathVariable("id") Integer id, @RequestBody CandidateEntityStatusUpdate candidate) throws Exception {
	        return candidateManager.updateStatus(id, candidate);
	    }	 	 
	    
	    /**
	     * Update candidate status date
	     * 
	     * @param id , 	 CandidateEntityStatusDateUpdate   
	     * @return CandidateEntity
	     * @throws Exception
	     */
	    @PutMapping(value = "/statusdate/{id}",consumes = "application/json")
	    @ApiOperation(value = "update")
	    public CandidateEntity updateStatusDate(@PathVariable("id") Integer id, @RequestBody CandidateEntityStatusDateUpdate candidate) throws Exception {
	        return candidateManager.updateStatusDate(id, candidate);
	    }	 	  	    
	    
	    /**
	     * delete candidate
	     * 
	     * @param id   
	     * @return CandidateEntity
	     * @throws Exception
	     */
	    @DeleteMapping(value = "/{id}")
	    @ApiOperation(value = "delete")
	    public  Map<String, Boolean> delete(@PathVariable("id") Integer id) throws Exception {
	       candidateManager.delete(id);
	        Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	    }	
	    
}
