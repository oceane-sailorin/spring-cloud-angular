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

import com.sba.models.entities.KeywordEntity;
import com.sba.models.entities.KeywordEntityCreation;
import com.sba.services.KeywordManager;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/keywords", produces = "application/json;charset=utf-8")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Success"), 
		@ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"), 
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure") })
public class KeywordController {

	 @Autowired
	 private KeywordManager keywordManager;
	     
	 /**
	     * get all keywords
	     * 
	     * @param params
	     * @return list of  keyword entities
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
	    public List<KeywordEntity> getAllKeywords(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return keywordManager.getAllKeywords(params);
	    }
	    
	    /**
	     * Create the keyword
	     * 
	     * @param keyword
	     * @return the new keyword entity
	     * @throws Exception
	     */
	    @PostMapping(consumes = "application/json")
	    @ApiOperation(value = "create")
	    public KeywordEntity create(@RequestBody KeywordEntityCreation keyword) throws Exception {
	        return keywordManager.create(keyword);
	    }

	    /**
	     * Get the keyword
	     * 
	     * @param id
	     * @return a keyword entity
	     * @throws Exception
	     */
	    @GetMapping(value = "/{id:.+}")
	    @ApiOperation(value = "get")
	    @ResponseBody
	    public KeywordEntity get(@PathVariable("id") Integer id) throws Exception {
	        return keywordManager.get(id);
	    }

	    /**
	     * Update keyword
	     * 
	     * @param id , 	 KeywordEntity   
	     * @return KeywordEntity
	     * @throws Exception
	     */
	    @PutMapping(value = "/{id}",consumes = "application/json")
	    @ApiOperation(value = "update")
	    public KeywordEntity update(@PathVariable("id") Integer id, @RequestBody KeywordEntity keyword) throws Exception {
	        return keywordManager.update(id, keyword);
	    }	  
	    
	    /**
	     * delete keyword
	     * 
	     * @param id   
	     * @return KeywordEntity
	     * @throws Exception
	     */
	    @DeleteMapping(value = "/{id}")
	    @ApiOperation(value = "delete")
	    public  Map<String, Boolean> delete(@PathVariable("id") Integer id) throws Exception {
	       keywordManager.delete(id);
	        Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	    }	
	    
}
