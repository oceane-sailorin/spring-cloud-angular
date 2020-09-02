package com.sba.exceptions;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sba.exceptions.BccException;

import java.io.IOException;

/**
 * Created by 
 */
public class BccExceptionSerializer extends JsonSerializer<BccException> {

	@Override
	public void serialize(
	        BccException value, JsonGenerator gen,
	        SerializerProvider serializers) throws IOException, JsonProcessingException
	{
	    gen.writeStartObject();
	    gen.writeFieldName("code");
	    gen.writeNumber(value.getCode());
	    gen.writeFieldName("message");
	    gen.writeString(value.getMessage());
	    gen.writeEndObject();
	}

}
