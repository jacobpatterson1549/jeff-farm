package com.github.ants280.jeff.farm.ws;


import java.security.NoSuchAlgorithmException;
import javax.inject.Singleton;
import org.apache.catalina.CredentialHandler;
import org.apache.catalina.realm.MessageDigestCredentialHandler;

@Singleton
public class PasswordGenerator
{
	private final CredentialHandler credentialHandler;
	
	public PasswordGenerator()
	{
		String algorithm = System.getProperty("credential.handler.algorithm");
		String iterations = System.getProperty("credential.handler.iterations");
		String saltLength = System.getProperty("credential.handler.salt.length");
		int iterationsNum = Integer.parseInt(iterations);
		int saltLengthNum = Integer.parseInt(saltLength);
		MessageDigestCredentialHandler messageDigestCredentialHandler
				= new MessageDigestCredentialHandler();
		try
		{
			messageDigestCredentialHandler.setAlgorithm(algorithm);
		}
		catch (NoSuchAlgorithmException ex)
		{
			throw new JeffFarmWsException(
					"Could not set password generator's algorithm.",
					ex);
		}
		messageDigestCredentialHandler.setIterations(iterationsNum);
		messageDigestCredentialHandler.setSaltLength(saltLengthNum);
		
		
		credentialHandler = messageDigestCredentialHandler;
	}
	
	public String getHashedPassword(String password)
	{
		return credentialHandler.mutate(password);
	}
}