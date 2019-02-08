package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.entity.Farm;
import java.util.Collections;
import java.util.List;
import org.jvnet.hk2.annotations.Service;

@Service
public class FarmDao implements BaseDao<Farm>
{
	@Override
	public int create(Farm entity)
	{
		return -1;
	}

	@Override
	public List<Farm> read()
	{
		return Collections.emptyList();
	}

	@Override
	public void update(Farm entity)
	{
		// NOOP
	}

	@Override
	public void delete(Farm entity)
	{
		// NOOP
	}
//	@PersistenceContext
//	private EntityManager entityManager = null;
//	
//	@Override
//	public int create(Farm farm)
//	{
//		StoredProcedureQuery query = entityManager
//				.createStoredProcedureQuery("createFarm")
//				.setParameter("farmName", farm.getName())
//				.setParameter("farmLocation", farm.getLocation());
//		boolean resultSetReturned = query.execute();
//		assert resultSetReturned;
//
//		Object farmId = query.getOutputParameterValue("farmID");
//		return farmId == null
//				? -1
//				: Integer.valueOf(farmId.toString());
//	}
//
//	@Override
//	public List<Farm> read()
//	{
//		StoredProcedureQuery query = entityManager
//				.createStoredProcedureQuery("readFarms", Farm.class);
//		boolean resultSetReturned = query.execute();
//		assert resultSetReturned;
//
//		List resultList = query.getResultList();
//		return (List<Farm>) resultList;
//	}
//
//	@Override
//	public void update(Farm farm)
//	{
//		StoredProcedureQuery query = entityManager
//				.createStoredProcedureQuery("updateFarm")
//				.setParameter("farmID", farm.getId())
//				.setParameter("farmName", farm.getName())
//				.setParameter("farmLocation", farm.getLocation());
//		int executeUpdate = query.executeUpdate();
//		assert executeUpdate == 1;
//		
//	}
//
//	@Override
//	public void delete(Farm farm)
//	{
//		StoredProcedureQuery query = entityManager
//				.createStoredProcedureQuery("deleteFarm")
//				.setParameter("farmID", farm.getId());
//		int executeUpdate = query.executeUpdate();
//		assert executeUpdate == 1;
//	}
	
}
