package com.github.ants280.jeff.farm.ws;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class Main
{
	static // load properties before any code is executed from main
	{
		try (
			InputStream inputStream = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("app.properties"))
		{
			System.getProperties().load(inputStream);
		}
		catch (IOException ex)
		{
			throw new JeffFarmWsException("Could not read properties.", ex);
		}
	}

	public static void main(String[] args) throws Exception
	{
		String scheme = Property.SERVER_SCHEME.getValue();
		String host = Property.SERVER_HOST.getValue();
		String port = Property.SERVER_PORT.getValue();
		String envPort = System.getenv("PORT");
		String path = Property.SERVER_PATH.getValue();
		int portNum = Integer.parseInt(envPort == null ? port : envPort);
		URI uri = new URI(scheme, null, // userInfo
			host, portNum, path, null, // query
			null); // fragment

		String userDir = Property.USER_DIR.getValue();
		File webAppFolder = new File(userDir, "src/main/webapp/");
		File targetFolder = new File(userDir, "target");

		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir(targetFolder.getAbsolutePath());
		tomcat.enableNaming();
		tomcat.setHostname(uri.getHost());
		tomcat.setPort(uri.getPort());

		// Create context, load META-INF/context.xml and WEB-INF/web.xml
		Context context = tomcat.addWebapp("", webAppFolder.getAbsolutePath());
		context.setParentClassLoader(Main.class.getClassLoader());

		tomcat.start();
		Logger logger = Logger.getLogger(Main.class.getName());
		logger.log(Level.INFO,
			"Server started at {0}/ - Press Ctrl-C to stop.",
			uri);
		tomcat.getServer().await();
	}
}
