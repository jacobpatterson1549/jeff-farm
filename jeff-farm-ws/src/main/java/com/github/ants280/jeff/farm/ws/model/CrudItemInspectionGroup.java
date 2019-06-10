package com.github.ants280.jeff.farm.ws.model;

import java.util.List;

public abstract class CrudItemInspectionGroup
	<V extends CrudItemInspection<?, V>, T extends CrudItemInspectionGroup<V, T>>
	extends CrudItem<T>
{
	public abstract List<V> getInspectionItems();

	public abstract T setInspectionItems(List<V> crudItems);
}
