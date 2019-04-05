package com.github.ants280.jeff.farm.ws;


import java.security.NoSuchAlgorithmException;
import javax.inject.Singleton;
import org.apache.catalina.CredentialHandler;
import org.apache.catalina.realm.MessageDigestCredentialHandler;
import org.jvnet.hk2.annotations.Contract;

@Singleton
@Contract
public class PasswordGenerator
{
	private final CredentialHandler credentialHandler;
	
	public PasswordGenerator()
	{
		// TODO: Ensure multiple instances are not created.
		// TODO: Use properties to sync up MessageDigestCredentialHandler inputs in context.xml
		String algorithm = "SHA-256";
		int iterations = 1000;
		int saltLength = 8;
		MessageDigestCredentialHandler messageDigestCredentialHandler
				= new MessageDigestCredentialHandler();
		try
		{
			messageDigestCredentialHandler.setAlgorithm(algorithm);
		}
		catch (NoSuchAlgorithmException ex)
		{
			throw new RuntimeException(
					"Could not set password generator's algorithm.",
					ex);
		}
		messageDigestCredentialHandler.setIterations(iterations);
		messageDigestCredentialHandler.setSaltLength(saltLength);
		
		
		credentialHandler = messageDigestCredentialHandler;
	}
	
	public String getHashedPassword(String password)
	{
		return credentialHandler.mutate(password);
	}
}