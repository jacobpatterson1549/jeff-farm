package com.github.ants280.jeff.farm.ws.model;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import javax.json.bind.annotation.JsonbTransient;

public class Hive extends CrudItem<Hive>
{
	public static final String ID_COLUMN = "id";
	public static final String CREATED_DATE_COLUMN = "created_date";
	public static final String MODIFIED_DATE_COLUMN = "modified_date";
	public static final String CAN_DELETE_ITEM = "can_delete";
	public static final String FARM_ID_COLUMN = "farm_id"; // parent column
	public static final String NAME_COLUMN = "name";
	public static final String QUEEN_COLOR_COLUMN = "queen_color";
	private static final Predicate<String>
		IS_VALID_QUEEN_COLOR_PREDICATE
		= Pattern.compile("^#[0-9a-fA-F]{6}$").asPredicate(); // must be same on ui
	private String name;
	private String queenColor;

	@Override
	protected Hive getThis()
	{
		return this;
	}

	public String getName()
	{
		return name;
	}

	public Hive setName(String name)
	{
		this.name = name;
		return this;
	}

	public String getQueenColor()
	{
		return queenColor;
	}

	public Hive setQueenColor(String queenColor)
	{
		if (queenColor == null || !IS_VALID_QUEEN_COLOR_PREDICATE.test(
			queenColor))
		{
			throw new IllegalArgumentException("Invalid color: " + queenColor);
		}
		this.queenColor = queenColor;
		return this;
	}

	@JsonbTransient
	public int getQueenColorInteger()
	{
		return Integer.parseInt(queenColor.substring(1), 16);
	}

	@JsonbTransient
	public Hive setQueenColorInteger(int queenColor)
	{
		return this.setQueenColor(String.format("#%06x", queenColor));
	}
}
