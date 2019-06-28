package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.dao.api.crud.CrudItemMapDao;
import com.github.ants280.jeff.farm.ws.model.Cattle;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.sql.DataSource;

public class CattleMapDao extends CrudItemMapDao
{
	private final CattleDao cattleDao;

	@Inject
	public CattleMapDao(
		DataSource dataSource,
		CattleDao cattleDao,
		UserIdDao userIdDao)
	{
		super(dataSource, userIdDao, "cattle");
		this.cattleDao = cattleDao;
	}

	@Override
	public Map<Integer, String> getTargets(int parentId)
	{
		List<Cattle> targets = cattleDao.readList(parentId);
		return targets.stream()
			.collect(Collectors.toMap(Cattle::getId, Cattle::getName));
	}
}
