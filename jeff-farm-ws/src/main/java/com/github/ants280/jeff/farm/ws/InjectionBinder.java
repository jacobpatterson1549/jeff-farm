package com.github.ants280.jeff.farm.ws;

import com.github.ants280.jeff.farm.ws.dao.FarmDao;
import javax.persistence.EntityManager;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class InjectionBinder extends AbstractBinder
{
	@Override
	protected void configure()
	{
		bind(StartupShutdownListener.getEntityManager()).to(EntityManager.class);
		bindAsContract(FarmDao.class);
	}
}