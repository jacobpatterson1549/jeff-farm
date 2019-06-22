package com.github.ants280.jeff.farm.ws.model;

public abstract class CrudItemInspection<V extends CrudItem, T extends CrudItemInspection<V, T>>
	extends CrudItem<T>
{
	public static final String GROUP_ID_COLUMN = "group_id";
	public static final String TARGET_ID_COLUMN = "target_id";
	public static final String TARGET_NAME_COLUMN = "target_name";
	private int targetId;
	private String targetName;

	public int getTargetId()
	{
		return targetId;
	}

	public T setTargetId(int targetId)
	{
		this.targetId = targetId;
		return getThis();
	}

	public String getTargetName()
	{
		return targetName;
	}

	public T setTargetName(String targetName)
	{
		this.targetName = targetName;
		return getThis();
	}
}
