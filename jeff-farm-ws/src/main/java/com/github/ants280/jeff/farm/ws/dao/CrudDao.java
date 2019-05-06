package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.CrudItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T extends CrudItem>
{
	 int create(T entity);

	 T read(int id);
	
	 List<T> readList(int parentId);

	 void update(int id, T entity);

	 void delete(int id);
	
	 boolean canDelete(int id);

	 T mapRow(ResultSet rs) throws SQLException;
}
