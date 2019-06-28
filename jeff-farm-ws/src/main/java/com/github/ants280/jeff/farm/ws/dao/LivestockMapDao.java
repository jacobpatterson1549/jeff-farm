package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.api.crud.CrudItemMapDao;
import com.github.ants280.jeff.farm.ws.model.Livestock;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class LivestockMapDao extends CrudItemMapDao
{
	private final LivestockDao livestockDao;

	@Inject
	public LivestockMapDao(
		DataSource dataSource,
		LivestockDao livestockDao,
		UserIdDao userIdDao)
	{
		super(dataSource, userIdDao, "livestock");
		this.livestockDao = livestockDao;
	}

	@Override
	public Map<Integer, String> getTargets(int parentId)
	{
		List<Livestock> targets = livestockDao.readList(parentId);
		return targets.stream()
			.collect(Collectors.toMap(Livestock::getId, Livestock::getName));
	}
}
