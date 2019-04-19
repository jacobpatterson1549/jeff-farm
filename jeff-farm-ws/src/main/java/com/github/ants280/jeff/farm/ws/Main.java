package com.github.ants280.jeff.farm.ws;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.startup.Tomcat;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		loadProperties();

		String scheme = System.getProperty("server.scheme");
		String host = System.getProperty("server.host");
		String port = System.getProperty("server.port");
		String path = System.getProperty("servlet.url.prefix");
		int portNum = Integer.parseInt(port);
		URI uri = new URI(scheme, null, // userInfo
			host, portNum, path,
			null, // query
			null); // fragment
		
		
		String userDir = System.getProperty("user.dir");
		File webAppFolder = new File(userDir, "src/main/webapp/");
		File targetFolder = new File(userDir, "target");
		
		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir(targetFolder.getAbsolutePath());
		tomcat.enableNaming();
		tomcat.setHostname(uri.getHost());
		tomcat.setPort(uri.getPort());

		// Create context, load META-INF/context.xml and WEB-INF/web.xml
		tomcat.addWebapp("", webAppFolder.getAbsolutePath());

		tomcat.start();
		Logger.getGlobal().log(Level.INFO,
			"Server started at {0} - Press Ctrl-C to stop.",
			uri);
		tomcat.getServer().await();
	}

	private static void loadProperties()
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
}
