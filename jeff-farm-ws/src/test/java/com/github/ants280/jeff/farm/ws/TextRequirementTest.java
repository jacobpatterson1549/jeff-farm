package com.github.ants280.jeff.farm.ws;

import java.util.Arrays;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TextRequirementTest
{
	private final String text;
	private final TextRequirement textRequirement;
	private final boolean expectedValidity;

	public TextRequirementTest(String text, String regex, int count, boolean expectedValidity)
	{
		this.text = text;
		this.textRequirement = new TextRequirement(
			"req", regex, count);
		this.expectedValidity = expectedValidity;
	}

	@Parameterized.Parameters
	public static Iterable<Object[]> data()
	{
		return Arrays.asList(
			new Object[]{"apple4ever", "a-z", 3, true},
			new Object[]{"ALL_UPPERCASE", "a-z", 1, false},
			new Object[]{"$symbols_are_here.", "$^_.,", 4, true},
			new Object[]{"not enough d1g1t5", "0-9", 4, false},
			new Object[]{"it is ok to contain no digits here", "0-9", 0, true});
	}

	@Test
	public void testIsValid()
	{
		boolean actualValidity = textRequirement.isValid(text);

		assertThat(actualValidity, is(expectedValidity));
	}
}
