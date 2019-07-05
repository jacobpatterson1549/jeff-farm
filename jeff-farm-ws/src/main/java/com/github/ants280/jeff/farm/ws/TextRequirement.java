package com.github.ants280.jeff.farm.ws;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TextRequirement
{
	private final String name;
	private final String regex;
	private final int count;

	public TextRequirement(String name, String regex, int count)
	{
		this.name = name;
		this.regex = regex;
		this.count = count;
	}

	public static void validate(
		String text,
		String name,
		int minLength,
		TextRequirement... textRequirements)
		throws JeffFarmWsException
	{
		if (text == null || text.length() < minLength)
		{
			throw new JeffFarmWsException(String.format(
				"%s must be at least %d characters long.",
				name,
				minLength));
		}

		for (TextRequirement textRequirement : textRequirements)
		{
			if (!textRequirement.isValid(text))
			{
				throw new JeffFarmWsException(String.format(
					"%s (%s) must occur at least %d %s in %s.",
					textRequirement.getName(),
					textRequirement.getRegex(),
					textRequirement.getCount(),
					textRequirement.getCount() == 1 ? "time" : "times",
					name));
			}
		}

		String combinedRegex = Arrays.stream(textRequirements)
			.map(TextRequirement::getRegex)
			.collect(Collectors.joining());
		if (!text.matches(String.format("^[%s]*$", combinedRegex)))
		{
			String combinedNames = Arrays.stream(textRequirements)
				.map(TextRequirement::getName)
				.collect(Collectors.joining(", "));
			throw new JeffFarmWsException(String.format(
				"%s must be made of of only [%s].",
				name,
				combinedNames));
		}
	}

	public String getName()
	{
		return name;
	}

	public String getRegex()
	{
		return regex;
	}

	public int getCount()
	{
		return count;
	}

	boolean isValid(String text)
	{
		return text != null
			&& (count == 0
				|| text.matches(String.format(
					"(.*?[%s].*?){%d,}",
					regex,
					count)));
	}
}
