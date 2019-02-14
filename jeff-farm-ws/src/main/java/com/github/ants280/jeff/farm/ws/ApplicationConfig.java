package com.github.ants280.jeff.farm.ws;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/resources")
public class ApplicationConfig extends Application
{
	@Override 
	public Set<Class<?>> getClasses()
	{
		Set<Class<?>> resources = new java.util.HashSet<>();
		// TODO: figure out how to automatically add everything in the resources package
		resources.add(com.github.ants280.jeff.farm.ws.resources.FarmResource.class);
		return resources;
	}

	@Override
	public Set<Object> getSingletons()
	{
		Set<Object> singletons = new java.util.HashSet<>();
		singletons.add(new InjectionBinder());
		return singletons;
	}
}