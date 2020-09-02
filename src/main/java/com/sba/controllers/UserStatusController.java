package com.sba.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sba.models.entities.UserStatusEntity;
import com.sba.services.UserStatusManager;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value = "/api/usersstatuses", produces = "application/json;charset=utf-8")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Success"), 
		@ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"), 
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure") })
public class UserStatusController {
		
	 
	 @Autowired
	 private UserStatusManager userStatusManager;
	     
	 /**
	     * get all usersStatuses
	     * 
	     * @param params
	     * @return list of  userStatus entities
	     * @throws Exception
	     */
	    @GetMapping
	    @ApiImplicitParams({ @ApiImplicitParam(value = "Pagination start", name = "_page", paramType = "query", dataType = "int"),
	     @ApiImplicitParam(value = "Max number of elements", name = "_perPage", paramType = "query", dataType = "int"),
	     @ApiImplicitParam(value = "Filter on id", name = "id", paramType = "query", dataType = "int"), 
	     @ApiImplicitParam(name = "Authorization", value = "Authorization token", dataType = "string", paramType = "header") })
	     @ResponseBody
	    public List<UserStatusEntity> getAllUsersStatuses(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return userStatusManager.getAllUsersStatuses(params);
	    }
	    
	  

	    /**
	     * Get the userStatus
	     * 
	     * @param id
	     * @return a userStatus entity
	     * @throws Exception
	     */
	    @GetMapping(value = "/{id:.+}")
	    @ApiOperation(value = "get")
	    @ResponseBody
	    public UserStatusEntity get(@PathVariable("id") Integer id) throws Exception {
	        return userStatusManager.get(id);
	    }

	
	    
	   
	

}
