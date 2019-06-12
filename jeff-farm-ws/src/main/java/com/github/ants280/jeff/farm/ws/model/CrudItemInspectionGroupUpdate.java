package com.github.ants280.jeff.farm.ws.model;

import java.util.List;

public abstract class CrudItemInspectionGroupUpdate
	<V extends CrudItemInspection<?, V>, T extends CrudItemInspectionGroup<V, T>>
{
	// The group and the updates for it.  The items to update must already be in the group.
	private T group;
	// The items to add to the group.
	private List<V> addItems;
	// The items to delete from the group.
	private List<Integer> removeItemIds;

	public T getGroup()
	{
		return group;
	}

	public void setGroup(T group)
	{
		this.group = group;
	}

	public List<V> getAddItems()
	{
		return addItems;
	}

	public void setAddItems(List<V> addItems)
	{
		this.addItems = addItems;
	}

	public List<Integer> getRemoveItemIds()
	{
		return removeItemIds;
	}

	public void setRemoveItemIds(List<Integer> removeItemIds)
	{
		this.removeItemIds = removeItemIds;
	}
}
