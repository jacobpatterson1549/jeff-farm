package com.github.ants280.jeff.farm.ws;

import com.github.ants280.jeff.farm.ws.resources.*;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.glassfish.jersey.jsonb.*;

@ApplicationPath("/")
public class ApplicationConfig extends Application
{
	@Override
	public Set<Class<?>> getClasses()
	{
		Set<Class<?>> resources = new HashSet<>();
		resources.add(JsonBindingFeature.class);
		resources.add(JeffFarmWsExceptionMapper.class);
		resources.add(LoginResource.class);
		resources.add(UserResource.class);
		resources.add(RootResource.class);
		resources.add(FarmResource.class);
		resources.add(FarmPermissionResource.class);
		resources.add(HiveResource.class);
		resources.add(HiveInspectionGroupResource.class);
		resources.add(PoultryResource.class);
		resources.add(PoultryInspectionGroupResource.class);
		resources.add(CattleResource.class);
		resources.add(CattleMapResource.class);
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
