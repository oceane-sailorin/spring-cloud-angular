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
import com.sba.models.entities.NeedCandidateStatusEntity;
import com.sba.models.entities.NeedCandidateStatusEntityCreation;
import com.sba.services.NeedCandidateStatusManager;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/need_candidate_statuses", produces = "application/json;charset=utf-8")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Success"), 
		@ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"), 
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure") })
public class NeedCandidateStatusController {

	 @Autowired
	 private NeedCandidateStatusManager needCandidateStatusManager;
	     
	 /**
	     * get all needCandidateStatuses
	     * 
	     * @param params
	     * @return list of  needCandidateStatus entities
	     * @throws Exception
	     */
	    @GetMapping
	    @ApiImplicitParams({ @ApiImplicitParam(value = "Pagination start", name = "_page", paramType = "query", dataType = "int"),
	     @ApiImplicitParam(value = "Max number of elements", name = "_perPage", paramType = "query", dataType = "int"),
	     @ApiImplicitParam(value = "Sort column", name = "_sortColumn", paramType = "query", dataType = "String"),
	     @ApiImplicitParam(value = "Sort order", name = "_sortOrder", paramType = "query", dataType = "String"),
	     @ApiImplicitParam(value = "Filter on id", name = "id", paramType = "query", dataType = "int"),
	     @ApiImplicitParam(value = "Filter on login", name = "login", paramType = "query", dataType = "String"),	    
	     @ApiImplicitParam(name = "Authorization", value = "Authorization token", dataType = "string", paramType = "header") })
	     @ResponseBody
	    public List<NeedCandidateStatusEntity> getAllNeedCandidateStatuses(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return needCandidateStatusManager.getAllNeedCandidateStatuses(params);
	    }
	    
	    /**
	     * Create the needCandidateStatus
	     * 
	     * @param needCandidateStatus
	     * @return the new needCandidateStatus entity
	     * @throws Exception
	     */
	    @PostMapping(consumes = "application/json")
	    @ApiOperation(value = "create")
	    public NeedCandidateStatusEntity create(@RequestBody NeedCandidateStatusEntityCreation needCandidateStatus) throws Exception {
	        return needCandidateStatusManager.create(needCandidateStatus);
	    }

	    /**
	     * Get the needCandidateStatus
	     * 
	     * @param id
	     * @return a needCandidateStatus entity
	     * @throws Exception
	     */
	    @GetMapping(value = "/{id:.+}")
	    @ApiOperation(value = "get")
	    @ResponseBody
	    public NeedCandidateStatusEntity get(@PathVariable("id") Integer id) throws Exception {
	        return needCandidateStatusManager.get(id);
	    }

	    /**
	     * Update needCandidateStatus
	     * 
	     * @param id , 	 NeedCandidateStatusEntity   
	     * @return NeedCandidateStatusEntity
	     * @throws Exception
	     */
	    @PutMapping(value = "/{id}",consumes = "application/json")
	    @ApiOperation(value = "update")
	    public NeedCandidateStatusEntity update(@PathVariable("id") Integer id, @RequestBody NeedCandidateStatusEntity needCandidateStatus) throws Exception {
	        return needCandidateStatusManager.update(id, needCandidateStatus);
	    }	  
	    
	    /**
	     * delete needCandidateStatus
	     * 
	     * @param id   
	     * @return NeedCandidateStatusEntity
	     * @throws Exception
	     */
	    @DeleteMapping(value = "/{id}")
	    @ApiOperation(value = "delete")
	    public  Map<String, Boolean> delete(@PathVariable("id") Integer id) throws Exception {
	       needCandidateStatusManager.delete(id);
	        Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	    }
	    
		 /**
	     * get all candidates_needs by status
	     * 
	     * @param params
	     * @return Map<NeedCandidateStatusEntity,List<CandidateNeedEntity>>
	     * @throws Exception
	     */
	    @GetMapping(value = "/candidates_needs_by_status")
	    @ApiOperation(value = "get")
	    @ApiImplicitParams({ @ApiImplicitParam(value = "Pagination start", name = "_page", paramType = "query", dataType = "int"),
	    @ApiImplicitParam(value = "Max number per page", name = "_perPage", paramType = "query", dataType = "int"),
	    @ApiImplicitParam(value = "Sort column", name = "_sortColumn", paramType = "query", dataType = "String"),
	    @ApiImplicitParam(value = "Sort order", name = "_sortOrder", paramType = "query", dataType = "String"),
	    @ApiImplicitParam(value = "Filter on id", name = "id", paramType = "query", dataType = "int"),
	    @ApiImplicitParam(value = "Filter on login", name = "login", paramType = "query", dataType = "String"),	    
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", dataType = "string", paramType = "header") })
	    @ResponseBody
	    public Map<Integer,Map<String,List<CandidateNeedEntity>>> getAllCandidatesNeedsByStatus(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return needCandidateStatusManager.getAllCandidatesNeedsByStatus(params);
	    }	    

		 /**
	     * get all candidates_needs by need by status
	     * 
	     * @param params
	     * @return Map<Integer,Map<String,Map<Integer, Map<String,List<CandidateNeedEntity>>>>>
	     * @throws Exception
	     */
	    @GetMapping(value = "/candidates_needs_by_need_by_status")
	    @ApiOperation(value = "get")
	    @ApiImplicitParams({ @ApiImplicitParam(value = "Pagination start", name = "_page", paramType = "query", dataType = "int"),
	    @ApiImplicitParam(value = "Max number per page", name = "_perPage", paramType = "query", dataType = "int"),
	    @ApiImplicitParam(value = "Sort column", name = "_sortColumn", paramType = "query", dataType = "String"),
	    @ApiImplicitParam(value = "Sort order", name = "_sortOrder", paramType = "query", dataType = "String"),
	    @ApiImplicitParam(value = "Filter on id", name = "id", paramType = "query", dataType = "int"),
	    @ApiImplicitParam(value = "Filter on login", name = "login", paramType = "query", dataType = "String"),	    
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", dataType = "string", paramType = "header") })
	    @ResponseBody
	    public Map<Integer,Map<String,Map<Integer, Map<String,List<CandidateNeedEntity>>>>> getAllCandidatesNeedsByNeedByStatus(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return needCandidateStatusManager.getAllCandidatesNeedsByNeedByStatus(params);
	    }	 	    
	    
	    
}

