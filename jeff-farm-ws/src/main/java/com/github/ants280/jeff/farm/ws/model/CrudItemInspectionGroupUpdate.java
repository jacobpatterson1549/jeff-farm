package com.github.ants280.jeff.farm.ws.model;

public abstract class CrudItemInspectionGroupUpdate
	<V extends CrudItemInspection<?, V>, T extends CrudItemInspectionGroup<V, T>>
{
	// The group and the updates for it.  The items to update must already be in the group.
	private T group;
	// The items to add to the group.
	private V[] addItems;
	// The items to delete from the group.
	private int[] removeItemIds;

	public T getGroup()
	{
		return group;
	}

	public void setGroup(T group)
	{
		this.group = group;
	}

	public V[] getAddItems()
	{
		return addItems;
	}

	public void setAddItems(V[] addItems)
	{
		this.addItems = addItems;
	}

	public int[] getRemoveItemIds()
	{
		return removeItemIds;
	}

	public void setRemoveItemIds(int[] removeItemIds)
	{
		this.removeItemIds = removeItemIds;
	}
}
