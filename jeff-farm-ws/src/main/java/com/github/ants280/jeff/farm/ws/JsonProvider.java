package com.github.ants280.jeff.farm.ws;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class JsonProvider extends JacksonJaxbJsonProvider
{
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	static
	{
		Module parameterNamesModule
				= new ParameterNamesModule(JsonCreator.Mode.PROPERTIES);
		OBJECT_MAPPER.registerModule(parameterNamesModule);
	}
	
	public JsonProvider()
	{
		this.setMapper(OBJECT_MAPPER);
	}
}
