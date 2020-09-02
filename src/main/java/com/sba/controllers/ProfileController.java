package com.sba.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import com.sba.models.entities.ProfileEntity;
import com.sba.models.entities.ProfileEntityCreation;
import com.sba.services.ProfileManager;

/**
 * profile controller
 * 
 */
@RestController
@RequestMapping(value = "/api/profiles", produces = "application/json;charset=utf-8")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Success"), 
		@ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"), 
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure") })
public class ProfileController {

	 @Autowired
	 private ProfileManager profileManager;

	    /**
	     * get all profiles
	     * 
	     * @param params
	     * @return list of  profile entities
	     * @throws Exception
	     */
	    @GetMapping
	    @ApiImplicitParams({ @ApiImplicitParam(value = "Pagination start", name = "_page", paramType = "query", dataType = "int"),
        @ApiImplicitParam(value = "Max number of elements", name = "_perPage", paramType = "query", dataType = "int"),
        @ApiImplicitParam(value = "Sort column", name = "_sortColumn", paramType = "query", dataType = "String"),
        @ApiImplicitParam(value = "Sort order", name = "_sortOrder", paramType = "query", dataType = "String"),
        @ApiImplicitParam(value = "Filter on id", name = "id", paramType = "query", dataType = "int"),
        @ApiImplicitParam(value = "Filter on name", name = "naming", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "Authorization", value = "Authorization token", dataType = "string", paramType = "header") })
        @ResponseBody
	    public List<ProfileEntity> getAllProfiles(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return profileManager.getAllProfiles(params);
	    }
	    
	    /**
	     * Create the profile
	     * 
	     * @param profile
	     * @return the new profile entity
	     * @throws Exception
	     */
	    @PostMapping(consumes = "application/json")
	    @ApiOperation(value = "create")
	    public ProfileEntity create(@RequestBody ProfileEntityCreation profile) throws Exception {
	        return profileManager.create(profile);
	    }

	    /**
	     * Get the profile
	     * 
	     * @param id
	     * @return a profile entity
	     * @throws Exception
	     */
	    @GetMapping(value = "/{id:.+}")
	    @ApiOperation(value = "get")
	    @ResponseBody
	    public ProfileEntity get(@PathVariable("id") Integer id) throws Exception {
	        return profileManager.get(id);
	    }

	    /**
	     * Update profile
	     * 
	     * @param id , 	 ProfileEntity   
	     * @return ProfileEntity
	     * @throws Exception
	     */
	    @PutMapping(value = "/{id}",consumes = "application/json")
	    @ApiOperation(value = "update")
	    public ProfileEntity update(@PathVariable("id") Integer id, @RequestBody ProfileEntity profile) throws Exception {
	        return profileManager.update(id, profile);
	    }	  
	    
	    /**
	     * delete profile
	     * 
	     * @param id   
	     * @return ProfileEntity
	     * @throws Exception
	     */
	    @DeleteMapping(value = "/{id}")
	    @ApiOperation(value = "delete")
	    public  Map<String, Boolean> delete(@PathVariable("id") Integer id) throws Exception {
	        profileManager.delete(id);
	        Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	    }	
	       
}
