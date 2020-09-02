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

import com.sba.models.entities.NeedStatusEntity;
import com.sba.models.entities.NeedStatusEntityCreation;
import com.sba.services.NeedStatusManager;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/needs_statuses", produces = "application/json;charset=utf-8")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Success"), 
		@ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"), 
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure") })
public class NeedStatusController {

	 @Autowired
	 private NeedStatusManager needStatusManager;
	     
	 /**
	     * get all needStatuses
	     * 
	     * @param params
	     * @return list of  needStatus entities
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
	    public List<NeedStatusEntity> getAllNeedStatuses(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return needStatusManager.getAllNeedStatuses(params);
	    }
	    
	    /**
	     * Create the needStatus
	     * 
	     * @param needStatus
	     * @return the new needStatus entity
	     * @throws Exception
	     */
	    @PostMapping(consumes = "application/json")
	    @ApiOperation(value = "create")
	    public NeedStatusEntity create(@RequestBody NeedStatusEntityCreation needStatus) throws Exception {
	        return needStatusManager.create(needStatus);
	    }

	    /**
	     * Get the needStatus
	     * 
	     * @param id
	     * @return a needStatus entity
	     * @throws Exception
	     */
	    @GetMapping(value = "/{id:.+}")
	    @ApiOperation(value = "get")
	    @ResponseBody
	    public NeedStatusEntity get(@PathVariable("id") Integer id) throws Exception {
	        return needStatusManager.get(id);
	    }

	    /**
	     * Update needStatus
	     * 
	     * @param id , 	 NeedStatusEntity   
	     * @return NeedStatusEntity
	     * @throws Exception
	     */
	    @PutMapping(value = "/{id}",consumes = "application/json")
	    @ApiOperation(value = "update")
	    public NeedStatusEntity update(@PathVariable("id") Integer id, @RequestBody NeedStatusEntity needStatus) throws Exception {
	        return needStatusManager.update(id, needStatus);
	    }	  
	    
	    /**
	     * delete needStatus
	     * 
	     * @param id   
	     * @return NeedStatusEntity
	     * @throws Exception
	     */
	    @DeleteMapping(value = "/{id}")
	    @ApiOperation(value = "delete")
	    public  Map<String, Boolean> delete(@PathVariable("id") Integer id) throws Exception {
	       needStatusManager.delete(id);
	        Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	    }	
}
