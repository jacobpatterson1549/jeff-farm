package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.StartupShutdownListener;
import com.github.ants280.jeff.farm.ws.entity.Farm;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.jvnet.hk2.annotations.Service;

@Service
public class FarmDao implements BaseDao<Farm>
{
	@Override
	public int create(Farm farm)
	{
		EntityManager entityManager = StartupShutdownListener.getEntityManager();
		try
		{
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createFarm")
					.registerStoredProcedureParameter("farmName", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("farmLocation", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("farmID", Integer.class, ParameterMode.OUT)
					.setParameter("farmName", farm.getName())
					.setParameter("farmLocation", farm.getLocation());
			boolean resultSetReturned = query.execute();
			assert !resultSetReturned;

			Object outputParameterValue = query.getOutputParameterValue("farmId");

			return (Integer) outputParameterValue;
		}
		finally
		{
			entityManager.close();
		}
	}

	@Override
	public List<Farm> read()
	{
		EntityManager entityManager = StartupShutdownListener.getEntityManager();
		try
		{
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("readFarms", Farm.class);
			boolean resultSetReturned = query.execute();
			assert resultSetReturned;

			List resultList = query.getResultList();
			assert !query.hasMoreResults();

			return (List<Farm>) resultList;
		}
		finally
		{
			entityManager.close();
		}
	}

	@Override
	public void update(Farm farm)
	{
		EntityManager entityManager = StartupShutdownListener.getEntityManager();
		EntityTransaction transaction = null;
		try
		{
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("updateFarm")
					.registerStoredProcedureParameter("farmID", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("farmName", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("farmLocation", String.class, ParameterMode.IN)
					.setParameter("farmID", farm.getId())
					.setParameter("farmName", farm.getName())
					.setParameter("farmLocation", farm.getLocation());
			transaction = entityManager.getTransaction();
			transaction.begin();
			int executeUpdate = query.executeUpdate();
			transaction.commit();
			assert executeUpdate == 1;
		}
		finally
		{
			if (transaction != null && transaction.isActive())
			{
				transaction.rollback();
			}
			entityManager.close();
		}
	}

	@Override
	public void delete(int id)
	{
		EntityManager entityManager = StartupShutdownListener.getEntityManager();
		EntityTransaction transaction = null;
		try
		{
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("deleteFarm")
					.registerStoredProcedureParameter("farmID", Integer.class, ParameterMode.IN)
					.setParameter("farmID", id);
			transaction = entityManager.getTransaction();
			transaction.begin();
			int executeUpdate = query.executeUpdate();
			transaction.commit();
			assert executeUpdate == 1;
		}
		finally
		{
			if (transaction != null && transaction.isActive())
			{
				transaction.rollback();
			}
			entityManager.close();
		}
	}
}
