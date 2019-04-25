package com.github.ants280.jeff.farm.ws;

import com.github.ants280.jeff.farm.ws.dao.ConnectionDao;
import com.github.ants280.jeff.farm.ws.dao.FarmDao;
import com.github.ants280.jeff.farm.ws.dao.HiveDao;
import com.github.ants280.jeff.farm.ws.dao.HiveInspectionDao;
import com.github.ants280.jeff.farm.ws.dao.LoginDao;
import com.github.ants280.jeff.farm.ws.dao.UserDao;
import com.github.ants280.jeff.farm.ws.resources.Property;
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
