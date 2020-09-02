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

import com.sba.models.entities.ClientEntity;
import com.sba.models.entities.ClientEntityCreation;
import com.sba.services.ClientManager;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/clients", produces = "application/json;charset=utf-8")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Success"), 
		@ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"), 
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure") })
public class ClientController {

	 @Autowired
	 private ClientManager clientManager;
	     
	 /**
	     * get all clients
	     * 
	     * @param params
	     * @return list of  client entities
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
	    public List<ClientEntity> getAllClients(Map<String, Object> params, HttpServletResponse res) throws Exception
	    {
	        return clientManager.getAllClients(params);
	    }
	    
	    /**
	     * Create the client
	     * 
	     * @param client
	     * @return the new client entity
	     * @throws Exception
	     */
	    @PostMapping(consumes = "application/json")
	    @ApiOperation(value = "create")
	    public ClientEntity create(@RequestBody ClientEntityCreation client) throws Exception {
	        return clientManager.create(client);
	    }

	    /**
	     * Get the client
	     * 
	     * @param id
	     * @return a client entity
	     * @throws Exception
	     */
	    @GetMapping(value = "/{id:.+}")
	    @ApiOperation(value = "get")
	    @ResponseBody
	    public ClientEntity get(@PathVariable("id") Integer id) throws Exception {
	        return clientManager.get(id);
	    }

	    /**
	     * Update client
	     * 
	     * @param id , 	 ClientEntity   
	     * @return ClientEntity
	     * @throws Exception
	     */
	    @PutMapping(value = "/{id}",consumes = "application/json")
	    @ApiOperation(value = "update")
	    public ClientEntity update(@PathVariable("id") Integer id, @RequestBody ClientEntity client) throws Exception {
	        return clientManager.update(id, client);
	    }	 
	        
	    
	    /**
	     * delete client
	     * 
	     * @param id   
	     * @return ClientEntity
	     * @throws Exception
	     */
	    @DeleteMapping(value = "/{id}")
	    @ApiOperation(value = "delete")
	    public  Map<String, Boolean> delete(@PathVariable("id") Integer id) throws Exception {
	       clientManager.delete(id);
	        Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	    }	
	    
}
