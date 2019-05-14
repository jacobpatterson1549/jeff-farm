package com.github.ants280.jeff.farm.ws.model;

public class PoultryInspection extends CrudItem<PoultryInspection>
{
	public static final String POULTRY_INSPECTION_GROUP_ID_COLUMN
		= "poultry_inspection_group_id";
	public static final String POULTRY_ID_COLUMN = "poultry_id";
	public static final String BIRD_COUNT_COLUMN = "bird_count";
	public static final String EGG_COUNT_COLUMN = "egg_count";
	private int poultryInspectionGroupId;
	private int poultryId;
	private int birdCount;
	private int eggCount;

	@Override
	protected PoultryInspection getThis()
	{
		return this;
	}

	public int getPoultryInspectionGroupId()
	{
		return poultryInspectionGroupId;
	}

	public PoultryInspection setPoultryInspectionGroupId(int poultryInspectionGroupId)
	{
		this.poultryInspectionGroupId = poultryInspectionGroupId;
		return this;
	}

	public int getPoultryId()
	{
		return poultryId;
	}

	public PoultryInspection setPoultryId(int poultryId)
	{
		this.poultryId = poultryId;
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
