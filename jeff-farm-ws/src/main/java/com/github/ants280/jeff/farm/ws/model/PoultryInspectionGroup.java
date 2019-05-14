package com.github.ants280.jeff.farm.ws.model;

import java.util.List;

public class PoultryInspectionGroup
	extends CrudItemGroup<PoultryInspection, PoultryInspectionGroup>
{
	public static final String NOTES_COLUMN = "notes";
	private List<PoultryInspection> items;
	private String notes;

	@Override
	protected PoultryInspectionGroup getThis()
	{
		return this;
	}

	@Override
	public List<PoultryInspection> getItems()
	{
		return items;
	}

	@Override
	public PoultryInspectionGroup setItems(List<PoultryInspection> items)
	{
		this.items = items;
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
