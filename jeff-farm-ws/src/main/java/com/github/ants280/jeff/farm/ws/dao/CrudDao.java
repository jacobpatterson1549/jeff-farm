package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.CrudItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T extends CrudItem>
{
	public int create(T entity);

	public T read(int id);
	
	public List<T> readList(int parentId);

	public void update(int id, T entity);

	public void delete(int id);
	
	public boolean canDelete(int id);

	public T mapRow(ResultSet rs) throws SQLException;
}
