package com.github.ants280.jeff.farm.ws;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupShutdownListener implements ServletContextListener
{
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		entityManagerFactory
				= Persistence.createEntityManagerFactory("jeff-farm-persistence-unit");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		if (entityManager != null && entityManager.isOpen())
		{
			entityManager.close();
		}
		if (entityManagerFactory != null && entityManagerFactory.isOpen())
		{
			entityManagerFactory.close();
		}
	}

	// TODO: I don't like how this is static.
	public static EntityManager getEntityManager()
	{
		return entityManager;
	}
}
