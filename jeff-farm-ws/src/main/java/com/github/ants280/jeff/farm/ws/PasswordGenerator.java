package com.github.ants280.jeff.farm.ws;


import com.github.ants280.jeff.farm.ws.resources.Property;
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
		String algorithm = Property.PASSWORD_GENERATOR_ALGORITHM.getValue();
		String iterations = Property.PASSWORD_GENERATOR_ITERATIONS.getValue();
		String saltLength = Property.PASSWORD_GENERATOR_SALT_LENGTH.getValue();
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
