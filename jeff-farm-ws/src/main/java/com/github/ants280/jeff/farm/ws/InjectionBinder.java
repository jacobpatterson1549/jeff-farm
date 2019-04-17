package com.github.ants280.jeff.farm.ws;

import com.github.ants280.jeff.farm.ws.dao.ConnectionDao;
import com.github.ants280.jeff.farm.ws.dao.FarmDao;
import com.github.ants280.jeff.farm.ws.dao.HiveDao;
import com.github.ants280.jeff.farm.ws.dao.HiveInspectionDao;
import com.github.ants280.jeff.farm.ws.dao.LoginDao;
import com.github.ants280.jeff.farm.ws.dao.UserDao;
import javax.inject.Singleton;
import javax.sql.DataSource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class InjectionBinder extends AbstractBinder
{
	@Override
	protected void configure()
	{
		bind(getDataSource()).to(DataSource.class);
		bindAsSingleton(PasswordGenerator.class);
		bindAsSingleton(ConnectionDao.class);
		bindAsSingleton(UserDao.class); // has PasswordGenerator
		bindAsSingleton(LoginDao.class); // has UserDao
		bindAsSingleton(FarmDao.class);
		bindAsSingleton(HiveDao.class);
		bindAsSingleton(HiveInspectionDao.class);
	}
	
	private <T> void bindAsSingleton(Class<T> singletonClass)
	{
		if (!singletonClass.isAnnotationPresent(Singleton.class))
		{
			throw new IllegalArgumentException(
					singletonClass.getSimpleName() + " must be singleton");
		}
		
		bindAsContract(singletonClass).in(Singleton.class);
	}

	private DataSource getDataSource()
	{
		return null;
//		MultiEndpointPool pool = MultiEndpointPool
//			.builder(SocketAddress.class)
//			.connectorHandler(transport)
//			.maxConnectionsPerEndpoint(4)
//			.maxConnectionsTotal(16)
//			.build();
//
//
//		try
//		{
//			// https://tomcat.apache.org/tomcat-9.0-doc/jndi-datasource-examples-howto.html#PostgreSQL
//			Context initCtx = new InitialContext();
//			Context envCtx = (Context) initCtx.lookup("java:comp/env");
//			return (DataSource) envCtx.lookup("jdbc/jeff-farm-data-source"); // also in pom.xml ${resource.data.source.name}
//		}
//		catch (NamingException ex)
//		{
//			Logger.getLogger(InjectionBinder.class.getName())
//					.log(
//							Level.SEVERE,
//							"Could not lookup DataSource",
//							ex);
//			return null;
//		}
	}
}
