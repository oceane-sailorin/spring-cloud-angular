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
import com.sba.models.entities.CandidateStatusEntity;
import com.sba.models.entities.CandidateStatusEntityCreation;
import com.sba.services.CandidateStatusManager;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/candidates_statuses", produces = "application/json;charset=utf-8")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Success"), 
		@ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"), 
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure") })
public class CandidateStatusController {

	 @Autowired
	 private CandidateStatusManager candidateStatusManager;
	     
	 /**
	     * get all candidateStatuses
	     * 
	     * @param params
	     * @return list of  candidateStatus entities
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
	    public List<CandidateStatusEntity> getAllCandidateStatuses(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return candidateStatusManager.getAllCandidateStatuses(params);
	    }
	    
	    /**
	     * Create the candidateStatus
	     * 
	     * @param candidateStatus
	     * @return the new candidateStatus entity
	     * @throws Exception
	     */
	    @PostMapping(consumes = "application/json")
	    @ApiOperation(value = "create")
	    public CandidateStatusEntity create(@RequestBody CandidateStatusEntityCreation candidateStatus) throws Exception {
	        return candidateStatusManager.create(candidateStatus);
	    }

	    /**
	     * Get the candidateStatus
	     * 
	     * @param id
	     * @return a candidateStatus entity
	     * @throws Exception
	     */
	    @GetMapping(value = "/{id:.+}")
	    @ApiOperation(value = "get")
	    @ResponseBody
	    public CandidateStatusEntity get(@PathVariable("id") Integer id) throws Exception {
	        return candidateStatusManager.get(id);
	    }

	    /**
	     * Update candidateStatus
	     * 
	     * @param id , 	 CandidateStatusEntity   
	     * @return CandidateStatusEntity
	     * @throws Exception
	     */
	    @PutMapping(value = "/{id}",consumes = "application/json")
	    @ApiOperation(value = "update")
	    public CandidateStatusEntity update(@PathVariable("id") Integer id, @RequestBody CandidateStatusEntity candidateStatus) throws Exception {
	        return candidateStatusManager.update(id, candidateStatus);
	    }	  
	    
	    /**
	     * delete candidateStatus
	     * 
	     * @param id   
	     * @return CandidateStatusEntity
	     * @throws Exception
	     */
	    @DeleteMapping(value = "/{id}")
	    @ApiOperation(value = "delete")
	    public  Map<String, Boolean> delete(@PathVariable("id") Integer id) throws Exception {
	       candidateStatusManager.delete(id);
	        Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	    }	
	    
		 /**
	     * get all candidates by status
	     * 
	     * @param params
	     * @return Map<CandidateStatusEntity,List<CandidateEntity>>
	     * @throws Exception
	     */
	    @GetMapping(value = "/candidates_by_status")
	    @ApiOperation(value = "get")
	    @ApiImplicitParams({ @ApiImplicitParam(value = "Pagination start", name = "_page", paramType = "query", dataType = "int"),
	    @ApiImplicitParam(value = "Max number per page", name = "_perPage", paramType = "query", dataType = "int"),
	    @ApiImplicitParam(value = "Sort column", name = "_sortColumn", paramType = "query", dataType = "String"),
	    @ApiImplicitParam(value = "Sort order", name = "_sortOrder", paramType = "query", dataType = "String"),
	    @ApiImplicitParam(value = "Filter on id", name = "id", paramType = "query", dataType = "int"),
	    @ApiImplicitParam(value = "Filter on login", name = "login", paramType = "query", dataType = "String"),	    
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", dataType = "string", paramType = "header") })
	    @ResponseBody
	    public Map<Integer,Map<String,List<CandidateEntity>>> getAllCandidatesByStatus(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return candidateStatusManager.getAllCandidatesByStatus(params);
	    }	    
	    
}
