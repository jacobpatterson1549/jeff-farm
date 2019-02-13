package com.github.ants280.jeff.farm.ws;

import com.github.ants280.jeff.farm.ws.dao.FarmDao;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class InjectionBinder extends AbstractBinder
{
	@Resource(lookup = "jdbc/jeff=farm")
	private DataSource dataSource;
	
	@Override
	protected void configure()
	{
		bind(dataSource).to(DataSource.class);
		bindAsContract(FarmDao.class);
	}
}