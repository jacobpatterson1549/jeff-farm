package com.github.ants280.jeff.farm.ws.model;

public abstract class CrudItemInspection<V extends CrudItem<V>, T extends CrudItemInspection<V, T>>
	extends CrudItem<T>
{
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
