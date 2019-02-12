package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.entity.Farm;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.jvnet.hk2.annotations.Service;

@Service
public class FarmDao implements BaseDao<Farm>
{
	private final StoredProcedureQuery createStoredProcedure;
	private final StoredProcedureQuery readStoredProcedure;
	private final StoredProcedureQuery updateStoredProcedure;
	private final StoredProcedureQuery deleteStoredProcedure;

	@Inject
	public FarmDao(EntityManager entityManager)
	{
		this.createStoredProcedure = entityManager.createStoredProcedureQuery("createFarm");
		this.readStoredProcedure = entityManager.createStoredProcedureQuery("readFarms", Farm.class);
		this.updateStoredProcedure = entityManager.createStoredProcedureQuery("updateFarm");
		this.deleteStoredProcedure = entityManager.createStoredProcedureQuery("deleteFarm");

		createStoredProcedure.registerStoredProcedureParameter("farmName", String.class, ParameterMode.IN);
		createStoredProcedure.registerStoredProcedureParameter("farmLocation", String.class, ParameterMode.IN);
		createStoredProcedure.registerStoredProcedureParameter("farmId", Integer.class, ParameterMode.OUT);

		updateStoredProcedure.registerStoredProcedureParameter("farmId", Integer.class, ParameterMode.IN);
		updateStoredProcedure.registerStoredProcedureParameter("farmName", String.class, ParameterMode.IN);
		updateStoredProcedure.registerStoredProcedureParameter("farmLocation", String.class, ParameterMode.IN);

		updateStoredProcedure.registerStoredProcedureParameter("delete", Integer.class, ParameterMode.IN);
	}

	@Override
	public int create(Farm farm)
	{
		StoredProcedureQuery query = createStoredProcedure
				.setParameter("farmName", farm.getName())
				.setParameter("farmLocation", farm.getLocation());
		boolean resultSetReturned = query.execute();
		assert !resultSetReturned;

		Object outputParameterValue = query.getOutputParameterValue("farmId");
		
		return (Integer) outputParameterValue;
	}

	@Override
	public List<Farm> read()
	{
		StoredProcedureQuery query = readStoredProcedure;
		boolean resultSetReturned = query.execute();
		assert resultSetReturned;

		List resultList = query.getResultList();
		assert !query.hasMoreResults();
		
		return (List<Farm>) resultList;
	}

	@Override
	public void update(Farm farm)
	{
		StoredProcedureQuery query = updateStoredProcedure
				.setParameter("farmID", farm.getId())
				.setParameter("farmName", farm.getName())
				.setParameter("farmLocation", farm.getLocation());
		int executeUpdate = query.executeUpdate();
		assert executeUpdate == 1;
	}

	@Override
	public void delete(int id)
	{
		StoredProcedureQuery query = deleteStoredProcedure
				.setParameter("farmID", id);
		int executeUpdate = query.executeUpdate();
		assert executeUpdate == 1;
	}
}
