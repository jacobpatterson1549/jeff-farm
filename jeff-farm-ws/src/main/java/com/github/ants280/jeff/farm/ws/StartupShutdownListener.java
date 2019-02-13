package com.github.ants280.jeff.farm.ws;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupShutdownListener implements ServletContextListener
{
	private static EntityManagerFactory entityManagerFactory;
	
	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		this.closeEntityManagerFactory();
		entityManagerFactory
				= Persistence.createEntityManagerFactory("jeff-farm-persistence-unit");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		this.closeEntityManagerFactory();
	}

	private void closeEntityManagerFactory()
	{
		if (entityManagerFactory != null && entityManagerFactory.isOpen())
		{
			entityManagerFactory.close();
		}
	}

	// TODO: I don't like how this is static.
	public static EntityManager getEntityManager()
	{
		return entityManagerFactory.createEntityManager();
	}
}
