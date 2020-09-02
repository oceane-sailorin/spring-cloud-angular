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

import com.sba.models.entities.NeedEntity;
import com.sba.models.entities.NeedEntityCreation;
import com.sba.services.NeedManager;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/needs", produces = "application/json;charset=utf-8")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Success"), 
		@ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"), 
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure") })
public class NeedController {

	 @Autowired
	 private NeedManager needManager;
	     
	 /**
	     * get all needs
	     * 
	     * @param params
	     * @return list of  need entities
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
	    public List<NeedEntity> getAllNeeds(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return needManager.getAllNeeds(params);
	    }
	    
	    /**
	     * Create the need
	     * 
	     * @param need
	     * @return the new need entity
	     * @throws Exception
	     */
	    @PostMapping(consumes = "application/json")
	    @ApiOperation(value = "create")
	    public int create(@RequestBody NeedEntityCreation need) throws Exception {
	        return needManager.create(need);
	    }

	    /**
	     * Get the need
	     * 
	     * @param id
	     * @return a need entity
	     * @throws Exception
	     */
	    @GetMapping(value = "/{id:.+}")
	    @ApiOperation(value = "get")
	    @ResponseBody
	    public NeedEntity get(@PathVariable("id") Integer id) throws Exception {
	        return needManager.get(id);
	    }

	    /**
	     * Update need
	     * 
	     * @param id , 	 NeedEntity   
	     * @return NeedEntity
	     * @throws Exception
	     */
	    @PutMapping(value = "/{id}",consumes = "application/json")
	    @ApiOperation(value = "update")
	    public NeedEntity update(@PathVariable("id") Integer id, @RequestBody NeedEntityCreation need) throws Exception {
	        return needManager.update(id, need);
	    }	 
	    
   
	    
	    /**
	     * delete need
	     * 
	     * @param id   
	     * @return NeedEntity
	     * @throws Exception
	     */
	    @DeleteMapping(value = "/{id}")
	    @ApiOperation(value = "delete")
	    public  Map<String, Boolean> delete(@PathVariable("id") Integer id) throws Exception {
	       needManager.delete(id);
	        Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	    }	
	    
	    
		 /**
	     * get all needs by client naming
	     * 
	     * @param params
	     * @return list of  need entities
	     * @throws Exception
	     */
	     @GetMapping(value = "/byclient")
	     @ApiImplicitParams({ @ApiImplicitParam(value = "Pagination start", name = "_page", paramType = "query", dataType = "int"),
	     @ApiImplicitParam(value = "Max number per page", name = "_perPage", paramType = "query", dataType = "int"),
	     @ApiImplicitParam(value = "Sort column", name = "_sortColumn", paramType = "query", dataType = "String"),
	     @ApiImplicitParam(value = "Sort order", name = "_sortOrder", paramType = "query", dataType = "String"),
	     @ApiImplicitParam(value = "Filter on id", name = "id", paramType = "query", dataType = "int"),
	     @ApiImplicitParam(value = "Filter on login", name = "login", paramType = "query", dataType = "String"),	    
	     @ApiImplicitParam(name = "Authorization", value = "Authorization token", dataType = "string", paramType = "header") })
	     @ResponseBody
	    public List<NeedEntity> getAllNeedsByClientNaming(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return needManager.getAllNeedsByClientNaming(params);
	    }	    
	    
}
