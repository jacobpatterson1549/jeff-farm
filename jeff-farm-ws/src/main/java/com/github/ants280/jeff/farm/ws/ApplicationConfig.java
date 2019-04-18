package com.github.ants280.jeff.farm.ws;

import com.github.ants280.jeff.farm.ws.resources.*;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.jsonb.JsonBindingFeature;

@ApplicationPath("/")
public class ApplicationConfig extends Application
{
	@Override
	public Set<Class<?>> getClasses()
	{
		Set<Class<?>> resources = new HashSet<>();
		resources.add(JsonBindingFeature.class);
		resources.add(LoginResource.class);
		resources.add(UserResource.class);
		resources.add(RootResource.class);
		resources.add(FarmResource.class);
		resources.add(HiveResource.class);
		resources.add(HiveInspectionResource.class);
		return resources;
	}

	@Override
	public Set<Object> getSingletons()
	{
		Set<Object> singletons = new HashSet<>();
		singletons.add(new InjectionBinder());
		return singletons;
	}
}
