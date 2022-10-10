package com.github.ants280.jeff.farm.ws.model;

import java.util.List;

public class HiveInspectionGroup
	extends CrudItemInspectionGroup<HiveInspection, HiveInspectionGroup>
{
	public static final String ID_COLUMN = "id";
	public static final String CREATED_DATE_COLUMN = "created_date";
	public static final String MODIFIED_DATE_COLUMN = "modified_date";
	public static final String FARM_ID_COLUMN = "farm_id"; // parent column
	public static final String NOTES_COLUMN = "notes";
	private List<HiveInspection> items;
	private String notes;

	@Override
	protected HiveInspectionGroup getThis()
	{
		return this;
	}

	@Override
	public List<HiveInspection> getInspectionItems()
	{
		return items;
	}

	@Override
	public HiveInspectionGroup setInspectionItems(List<HiveInspection> items)
	{
		this.items = items;
		return this;
	}

	public String getNotes()
	{
		return notes;
	}

	public HiveInspectionGroup setNotes(String notes)
	{
		this.notes = notes;
		return this;
	}
}
