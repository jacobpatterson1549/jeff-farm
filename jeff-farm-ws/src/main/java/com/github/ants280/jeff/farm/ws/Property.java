package com.github.ants280.jeff.farm.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

public enum Property
{
	USER_ROLE("security.role.user"),
	SERVER_SCHEME("server.scheme"),
	SERVER_HOST("server.host"),
	SERVER_PORT("server.port"),
	SERVER_PATH("servlet.url.prefix"),
	PASSWORD_GENERATOR_ALGORITHM("credential.handler.algorithm"),
	PASSWORD_GENERATOR_ITERATIONS("credential.handler.iterations"),
	PASSWORD_GENERATOR_SALT_LENGTH("credential.handler.salt.length"),
	DATA_SOURCE_NAME("resource.data.source.name"),
	USER_DIR("user.dir");

	static
	{
		Logger logger = Logger.getLogger(Property.class.getName());
		for (Property p : Property.values())
		{
			logger.log(
				Level.INFO,
				"{0} => {1}", new Object[] { p.name(), p.getValue() });
		}
	}

	private final String value;

	Property(String key)
	{
		this.value = System.getProperty(key);
	}

	public String getValue()
	{
		return value;
	}
}
