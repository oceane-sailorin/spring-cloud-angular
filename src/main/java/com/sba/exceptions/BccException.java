package com.sba.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by 
 */
@JsonSerialize(using = BccExceptionSerializer.class)
public class BccException extends RuntimeException
{	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Error code
     */
    private int code;


    /**
     * Simple Constructor
     */
    public BccException()
    {

    }

    public BccException(String message)
    {
        super(message);
    }

    /**
     * Exception Code
     *
     * @return int code
     */
    public int getCode()
    {
        return code;
    }
    




}
