package com.github.ants280.jeff.farm.ws.dao;

import com.github.ants280.jeff.farm.ws.model.PoultryInspection;
import com.github.ants280.jeff.farm.ws.model.PoultryInspectionGroup;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Singleton
public class PoultryInspectionGroupDao
	extends CrudItemGroupDao<PoultryInspection, PoultryInspectionGroup>
{
	private final LoginDao loginDao;

	@Inject
	public PoultryInspectionGroupDao(DataSource dataSource, LoginDao loginDao)
	{
		super(dataSource);
		this.loginDao = loginDao;
	}

	@Override
	public int create(PoultryInspectionGroup poultryInspectionGroup)
	{
		return this.executeCreate("create_poultry_inspection",
			Collections.singletonList(new SqlFunctionParameter<>(
				PoultryInspectionGroup.NOTES_COLUMN,
				poultryInspectionGroup.getNotes(),
				Types.VARCHAR)),
			"create_poultry_inspection_group",
			poultryInspectionGroup.getItems()
				.stream()
				.map(poultryInspection -> Arrays.asList(new SqlFunctionParameter<>(
						PoultryInspection.BIRD_COUNT_COLUMN,
						poultryInspection.getBirdCount(),
						Types.INTEGER),
					// TODO: fix generics
					new SqlFunctionParameter(PoultryInspection.EGG_COUNT_COLUMN,
						poultryInspection.getEggCount(),
						Types.INTEGER)))
				.collect(Collectors.toList()),
			loginDao.getUserId());
	}

	@Override
	public PoultryInspectionGroup read(int id)
	{
		return this.executeRead("read_poultry_inspection_group",
			Collections.singletonList(new SqlFunctionParameter<>(
				PoultryInspection.ID_COLUMN,
				id,
				Types.INTEGER)),
			this::mapGroup,
			this::mapItem,
			PoultryInspection::getPoultryInspectionGroupId);
	}

	@Override
	public List<PoultryInspectionGroup> readList(int parentId)
	{
		return this.executeReadList("read_poultry_inspection_groups",
			Collections.singletonList(new SqlFunctionParameter<>(
				PoultryInspection.POULDRY_INSPECTION_GROUP_ID_COLUMN,
				parentId,
				Types.INTEGER)),
			this::mapGroup,
			this::mapItem,
			PoultryInspection::getPoultryInspectionGroupId);
	}

	@Override
	public void update(
		int id, PoultryInspectionGroup poultryInspectionGroup)
	{
		this.executeUpdate("update_poultry_inspection_group",
			Arrays.asList(new SqlFunctionParameter<>(PoultryInspectionGroup.ID_COLUMN,
					id,
					Types.INTEGER),
				new SqlFunctionParameter<>(PoultryInspectionGroup.NOTES_COLUMN,
					poultryInspectionGroup.getNotes(),
					Types.VARCHAR)),
			"update_poultry_inspection",
			poultryInspectionGroup.getItems()
				.stream()
				.map(poultryInspection -> Arrays.asList(new SqlFunctionParameter(
						PoultryInspection.ID_COLUMN,
						poultryInspection.getId(),
						Types.INTEGER),
					new SqlFunctionParameter<>(PoultryInspection.BIRD_COUNT_COLUMN,
						poultryInspection.getBirdCount(),
						Types.INTEGER),
					new SqlFunctionParameter(PoultryInspection.EGG_COUNT_COLUMN,
						poultryInspection.getEggCount(),
						Types.INTEGER)))
				.collect(Collectors.toList()),
			loginDao.getUserId());
	}

	@Override
	public void delete(int id)
	{
		this.executeDelete("delete_poultry_inspection_group",
			Collections.singletonList(new SqlFunctionParameter<>(
				PoultryInspection.ID_COLUMN,
				id,
				Types.INTEGER)),
			loginDao.getUserId());
	}

	@Override
	public boolean canDelete(int id)
	{
		return true;
	}

	@Override
	public PoultryInspectionGroup mapGroup(ResultSet rs) throws SQLException
	{
		return new PoultryInspectionGroup().setId(rs.getInt(
			PoultryInspectionGroup.ID_COLUMN))
			.setNotes(rs.getString(PoultryInspectionGroup.NOTES_COLUMN))
			.setCreatedTimestamp(rs.getTimestamp(PoultryInspectionGroup.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(PoultryInspectionGroup.MODIFIED_DATE_COLUMN));
	}

	@Override
	public PoultryInspection mapItem(ResultSet rs) throws SQLException
	{
		return new PoultryInspection().setId(rs.getInt(PoultryInspection.ID_COLUMN))
			.setPoultryId(rs.getInt(PoultryInspection.POULTRY_ID_COLUMN))
			.setBirdCount(rs.getInt(PoultryInspection.BIRD_COUNT_COLUMN))
			.setEggCount(rs.getInt(PoultryInspection.EGG_COUNT_COLUMN))
			.setCreatedTimestamp(rs.getTimestamp(PoultryInspection.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(PoultryInspection.MODIFIED_DATE_COLUMN));
	}
}
