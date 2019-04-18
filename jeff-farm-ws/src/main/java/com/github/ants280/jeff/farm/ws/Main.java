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
		Logger logger = Logger.getGlobal();
		logger.log(Level.INFO,
			"Server started at {0}.  Press ENTER to stop",
			uri);

		logger.log(Level.INFO, "[DONE]{0}", (char) System.in.read()); // BLOCKS
		tomcat.stop();
		tomcat.destroy();
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
			throw new RuntimeException("Could not read properties.", ex);
		}
	}
}
