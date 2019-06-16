package com.github.ants280.jeff.farm.ws;

import com.github.ants280.jeff.farm.ws.dao.*;
import javax.inject.Singleton;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
		bindAsSingleton(UserIdDao.class);
		bindAsSingleton(UserDao.class); // has PasswordGenerator
		bindAsSingleton(LoginDao.class); // has UserDao
		bindAsContract(UserIdDao.class); // has LoginDao :p
		bindAsSingleton(FarmDao.class);
		bindAsSingleton(FarmPermissionDao.class);
		bindAsSingleton(HiveDao.class);
		bindAsSingleton(HiveInspectionDao.class);
		bindAsSingleton(PoultryDao.class);
		bindAsSingleton(PoultryDao.class);
		bindAsSingleton(PoultryInspectionGroupDao.class);
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
		try
		{
			String dataSourceName = Property.DATA_SOURCE_NAME.getValue();
			Context initCtx = new InitialContext();
			// https://tomcat.apache.org/tomcat-9.0-doc/jndi-resources-howto.html#Using_resources
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			return (DataSource) envCtx.lookup(dataSourceName);
		}
		catch (NamingException ex)
		{
			throw new JeffFarmWsException("Could not lookup DataSource", ex);
		}
	}
}
