package com.github.ants280.jeff.farm.ws.model;

import java.util.List;

public class PoultryInspectionGroup
	extends CrudItemInspectionGroup<PoultryInspection, PoultryInspectionGroup>
{
	public static final String FARM_ID_COLUMN = "farm_id";
	public static final String NOTES_COLUMN = "notes";
	private List<PoultryInspection> items;
	private int farmId;
	private String notes;

	@Override
	protected PoultryInspectionGroup getThis()
	{
		return this;
	}

	@Override
	public List<PoultryInspection> getInspectionItems()
	{
		return items;
	}

	@Override
	public PoultryInspectionGroup setInspectionItems(List<PoultryInspection> items)
	{
		this.items = items;
		return this;
	}

	public int getFarmId()
	{
		return farmId;
	}

	public PoultryInspectionGroup setFarmId(int farmId)
	{
		this.farmId = farmId;
		return this;
	}

	public String getNotes()
	{
		return notes;
	}

	public PoultryInspectionGroup setNotes(String notes)
	{
		this.notes = notes;
		return this;
	}
}
