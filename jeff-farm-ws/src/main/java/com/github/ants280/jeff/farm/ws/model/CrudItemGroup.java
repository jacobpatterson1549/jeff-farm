package com.github.ants280.jeff.farm.ws.model;

import java.util.List;

public abstract class CrudItemGroup<V extends CrudItem, T extends CrudItemGroup<V, T>>
	extends CrudItem<T>
{
	public abstract List<V> getItems();

	public abstract T setItems(List<V> crudItems);
}
