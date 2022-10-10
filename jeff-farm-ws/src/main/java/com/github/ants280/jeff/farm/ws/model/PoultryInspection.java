package com.github.ants280.jeff.farm.ws.model;

public class PoultryInspection extends CrudItemInspection<Poultry, PoultryInspection>
{
	public static final String ID_COLUMN = "id";
	public static final String CREATED_DATE_COLUMN = "created_date";
	public static final String MODIFIED_DATE_COLUMN = "modified_date";
	public static final String BIRD_COUNT_COLUMN = "bird_count";
	public static final String EGG_COUNT_COLUMN = "egg_count";
	public static final String GROUP_ID_COLUMN = "group_id";
	public static final String TARGET_ID_COLUMN = "target_id";
	public static final String TARGET_NAME_COLUMN = "target_name";
	private int birdCount;
	private int eggCount;

	@Override
	protected PoultryInspection getThis()
	{
		return this;
	}

	public int getBirdCount()
	{
		return birdCount;
	}

	public PoultryInspection setBirdCount(int birdCount)
	{
		this.birdCount = birdCount;
		return this;
	}

	public int getEggCount()
	{
		return eggCount;
	}

	public PoultryInspection setEggCount(int eggCount)
	{
		this.eggCount = eggCount;
		return this;
	}
}
