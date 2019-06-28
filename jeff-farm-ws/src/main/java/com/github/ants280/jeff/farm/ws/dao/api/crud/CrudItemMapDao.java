package com.github.ants280.jeff.farm.ws.dao.api.crud;

import com.github.ants280.jeff.farm.ws.dao.UserIdDao;
import com.github.ants280.jeff.farm.ws.dao.api.SqlFunctionDao;
import com.github.ants280.jeff.farm.ws.dao.api.call.BatchCommandSqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.call.SideEffectSqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.call.SimpleCommandSqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.call.SqlFunctionCall;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.DoubleSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.IntegerSqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.parameter.SqlFunctionParameter;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.ListResultSetTransformer;
import com.github.ants280.jeff.farm.ws.dao.api.transformer.SimpleResultSetTransformer;
import com.github.ants280.jeff.farm.ws.model.CrudItem;
import com.github.ants280.jeff.farm.ws.model.CrudItemCoordinate;
import com.github.ants280.jeff.farm.ws.model.CrudItemMap;
import com.github.ants280.jeff.farm.ws.model.CrudItemMapUpdate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.sql.DataSource;

public abstract class CrudItemMapDao extends SqlFunctionDao
{
	private final String crudItemName;

	public CrudItemMapDao(DataSource dataSource, UserIdDao userIdDao, String crudItemName)
	{
		super(dataSource, userIdDao);
		this.crudItemName = crudItemName;
	}

	public abstract Map<Integer, String> getTargets(int parentId);

	public int create(CrudItemMap crudItemMap)
	{
		Function<CrudItemCoordinate, List<SqlFunctionParameter>> createCoordinateParameterMapper
			= crudItemCoordinate ->Arrays.asList(
				new IntegerSqlFunctionParameter(CrudItemCoordinate.MAP_ID_COLUMN, -1), // Is reset by SideEffectSqlFunctionCall.
				new DoubleSqlFunctionParameter(CrudItemCoordinate.LATITUDE_COLUMN, crudItemCoordinate.getLatitude()),
				new DoubleSqlFunctionParameter(CrudItemCoordinate.LONGITUDE_COLUMN, crudItemCoordinate.getLongitude()),
				new IntegerSqlFunctionParameter(CrudItemCoordinate.DISPLAY_ORDER_COLUMN, crudItemCoordinate.getDisplayOrder()));

		String createMapFunctionName = String.format("create_%s_map", crudItemName);
		String createCoordinatesFunctionName = String.format("create_%s_coordinate", crudItemName);
		List<List<SqlFunctionParameter>> coordinatesInParameters = crudItemMap.getCoordinates()
			.stream()
			.map(createCoordinateParameterMapper)
			.collect(Collectors.toList());

		SqlFunctionCall<Integer> createGroupFunctionCall
			= new SideEffectSqlFunctionCall<>(
			createMapFunctionName,
			Collections.emptyList(),
			new SimpleResultSetTransformer<>(rs -> rs.getInt(CrudItem.ID_COLUMN)),
			parentId -> this.setParentId(parentId, coordinatesInParameters),
			userIdDao);
		SqlFunctionCall<Void>
			createItemsFunctionCall
			= new BatchCommandSqlFunctionCall(createCoordinatesFunctionName,
			coordinatesInParameters,
			userIdDao);

		return this.execute((a, b) -> a,
			createGroupFunctionCall,
			createItemsFunctionCall);
	}

	public CrudItemMap read(int id)
	{
		String readMapFunctionName = String.format("read_%s_map", crudItemName);
		String readCoordinatesFunctionName = String.format("read_%s_coordinates_for_map", crudItemName);
		List<SqlFunctionParameter> readMapInParameters = Collections.singletonList(
			new IntegerSqlFunctionParameter(CrudItemMap.ID_COLUMN, id));
		List<SqlFunctionParameter> readCoordinatesInParameters = Collections.singletonList(
			new IntegerSqlFunctionParameter(CrudItemCoordinate.MAP_ID_COLUMN, id));

		SqlFunctionCall<CrudItemMap>
			readGroupFunctionCall
			= new SimpleCommandSqlFunctionCall<>(readMapFunctionName,
			readMapInParameters,
			new SimpleResultSetTransformer<>(this::mapMap),
			userIdDao);
		SqlFunctionCall<List<CrudItemCoordinate>>
			readItemsFunctionCall
			= new SimpleCommandSqlFunctionCall<>(readCoordinatesFunctionName,
			readCoordinatesInParameters,
			new ListResultSetTransformer<>(this::mapCoordinate),
			userIdDao);

		return this.execute(CrudItemMap::setCoordinates,
			readGroupFunctionCall,
			readItemsFunctionCall);
	}

	public List<CrudItemMap> readList(int farmId)
	{
		String readMapFunctionName = String.format("read_%s_maps", crudItemName);
		List<SqlFunctionParameter> readMapsInParameters = Collections.singletonList(
			new IntegerSqlFunctionParameter(CrudItem.ID_COLUMN, farmId)); // TODO: should be parentId!!! (+all parameters on read-list functions)
		String readCoordinatesFunctionName = String.format("read_%s_coordinates_for_farm", crudItemName);
		List<SqlFunctionParameter> readCoordinatesInParameters = Collections.singletonList(
			new IntegerSqlFunctionParameter(CrudItem.ID_COLUMN, farmId));

		SimpleCommandSqlFunctionCall<List<CrudItemMap>>
			readGroupFunctionCall
			= new SimpleCommandSqlFunctionCall<>(readMapFunctionName,
			readMapsInParameters,
			new ListResultSetTransformer<>(this::mapMap),
			userIdDao);
		SqlFunctionCall<List<CrudItemCoordinate>>
			readItemsFunctionCall
			= new SimpleCommandSqlFunctionCall<>(readCoordinatesFunctionName,
			readCoordinatesInParameters,
			new ListResultSetTransformer<>(this::mapCoordinate),
			userIdDao);

		return this.execute(this::partitionCoordinatesForMaps,
			readGroupFunctionCall,
			readItemsFunctionCall);
	}

	public void update(CrudItemMapUpdate entityUpdate)
	{
		Function<CrudItemMap, List<SqlFunctionParameter>> updateMapParameterMapper
			= crudItemMap ->Arrays.asList(
			new IntegerSqlFunctionParameter(CrudItemMap.ID_COLUMN, crudItemMap.getId()),
			new IntegerSqlFunctionParameter(CrudItemMap.TARGET_ID_COLUMN, crudItemMap.getTargetId()));
		Function<CrudItemCoordinate, List<SqlFunctionParameter>> updateCoordinateParameterMapper
			= crudItemCoordinate ->Arrays.asList(
				new IntegerSqlFunctionParameter(CrudItemCoordinate.ID_COLUMN, crudItemCoordinate.getId()),
				new DoubleSqlFunctionParameter(CrudItemCoordinate.LATITUDE_COLUMN, crudItemCoordinate.getLatitude()),
				new DoubleSqlFunctionParameter(CrudItemCoordinate.LONGITUDE_COLUMN, crudItemCoordinate.getLongitude()),
				new IntegerSqlFunctionParameter(CrudItemCoordinate.DISPLAY_ORDER_COLUMN, crudItemCoordinate.getDisplayOrder()));
		Function<CrudItemCoordinate, List<SqlFunctionParameter>> addCoordinateParameterMapper
			= crudItemCoordinate ->Arrays.asList(
				new IntegerSqlFunctionParameter(CrudItemCoordinate.MAP_ID_COLUMN, entityUpdate.getMap().getId()),
				new DoubleSqlFunctionParameter(CrudItemCoordinate.LATITUDE_COLUMN, crudItemCoordinate.getLatitude()),
				new DoubleSqlFunctionParameter(CrudItemCoordinate.LONGITUDE_COLUMN, crudItemCoordinate.getLongitude()),
				new IntegerSqlFunctionParameter(CrudItemCoordinate.DISPLAY_ORDER_COLUMN, crudItemCoordinate.getDisplayOrder()));
		IntFunction<List<SqlFunctionParameter>> deleteCoordinateParameterMapper
			= coordinateId -> Collections.singletonList(
				new IntegerSqlFunctionParameter(CrudItemCoordinate.ID_COLUMN, coordinateId));

		String updateMapFunctionName = String.format("update_%s_map", crudItemName);
		List<SqlFunctionParameter> updateMapInParameters
			= updateMapParameterMapper.apply(entityUpdate.getMap());
		String updateCoordinatesFunctionName = String.format("update_%s_coordinate", crudItemName);
		List<List<SqlFunctionParameter>> updateCoordinatesInParameters
			= Arrays.stream(entityUpdate.getAddCoordinates())
			.map(updateCoordinateParameterMapper)
			.collect(Collectors.toList());
		String createCoordinatesFunctionName = String.format("create_%s_coordinate", crudItemName);
		List<List<SqlFunctionParameter>> createCoordinatesInParameters
			= Arrays.stream(entityUpdate.getAddCoordinates())
				.map(addCoordinateParameterMapper)
				.collect(Collectors.toList());
		String deleteCoordinatesFunctionName = String.format("delete_%s_coordinate", crudItemName);
		List<List<SqlFunctionParameter>> deleteCoordinatesInParameters
			= IntStream.of(entityUpdate.getRemoveCoordinateIds())
				.mapToObj(deleteCoordinateParameterMapper)
				.collect(Collectors.toList());

		SqlFunctionCall<Void>
			updateMapFunctionCall
			= new SimpleCommandSqlFunctionCall<>(updateMapFunctionName,
			updateMapInParameters,
			null,
			userIdDao);
		SqlFunctionCall<Void>
			updateCoordinatesFunctionCall
			= new BatchCommandSqlFunctionCall(updateCoordinatesFunctionName,
			updateCoordinatesInParameters,
			userIdDao);
		SqlFunctionCall<Void>
			createCoordinatesFunctionCall
			= new BatchCommandSqlFunctionCall(createCoordinatesFunctionName,
			createCoordinatesInParameters,
			userIdDao);
		SqlFunctionCall<Void>
			deleteCoordinatesFunctionCall
			= new BatchCommandSqlFunctionCall(deleteCoordinatesFunctionName,
			deleteCoordinatesInParameters,
			userIdDao);

		this.execute(
			updateMapFunctionCall,
			updateCoordinatesFunctionCall,
			createCoordinatesFunctionCall,
			deleteCoordinatesFunctionCall);
	}


	public void delete(int id)
	{
		String deleteMapFunctionName = String.format("delete_%s_map", crudItemName);
		List<SqlFunctionParameter> deleteMapInParameters = Collections.singletonList(
			new IntegerSqlFunctionParameter(CrudItemMap.ID_COLUMN, id));
		String deleteCoordinatesForMapFunctionName = String.format("delete_%s_coordinates_for_map", crudItemName);
		List<SqlFunctionParameter> deleteCoordinatesInParameters = Collections.singletonList(
			new IntegerSqlFunctionParameter(CrudItemCoordinate.MAP_ID_COLUMN, id));

		SqlFunctionCall<Void>
			deleteMapFunctionCall
			= new SimpleCommandSqlFunctionCall<>(deleteMapFunctionName,
			deleteMapInParameters,
			null,
			userIdDao);
		SqlFunctionCall<Void>
			deleteCoordinatesForMapFunctionCall
			= new SimpleCommandSqlFunctionCall<>(deleteCoordinatesForMapFunctionName,
			deleteCoordinatesInParameters,
			null,
			userIdDao);

		this.execute(deleteCoordinatesForMapFunctionCall, deleteMapFunctionCall);
	}

	private CrudItemMap mapMap(ResultSet rs) throws SQLException
	{
		return new CrudItemMap()
			.setId(rs.getInt(CrudItemMap.ID_COLUMN))
			.setTargetId(rs.getInt(rs.getInt(CrudItemMap.TARGET_ID_COLUMN)))
			.setTargetName(rs.getString(rs.getString(CrudItemMap.TARGET_NAME_COLUMN)))
			.setCreatedTimestamp(rs.getTimestamp(CrudItemMap.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(CrudItemMap.MODIFIED_DATE_COLUMN));
	}

	private CrudItemCoordinate mapCoordinate(ResultSet rs) throws SQLException
	{
		return new CrudItemCoordinate()
			.setId(rs.getInt(CrudItemCoordinate.ID_COLUMN))
			.setLatitude(rs.getDouble(CrudItemCoordinate.LATITUDE_COLUMN))
			.setLongitude(rs.getDouble(CrudItemCoordinate.LONGITUDE_COLUMN))
			.setCreatedTimestamp(rs.getTimestamp(CrudItemCoordinate.CREATED_DATE_COLUMN))
			.setModifiedTimestamp(rs.getTimestamp(CrudItemCoordinate.MODIFIED_DATE_COLUMN));
	}

	private List<CrudItemMap> partitionCoordinatesForMaps(
		List<CrudItemMap> crudItemMaps, List<CrudItemCoordinate> crudItemCoordinates)
	{

		Map<Integer, CrudItemMap> crudItemMapsById = crudItemMaps.stream()
			.collect(Collectors.toMap(
				CrudItemMap::getId,
				Function.identity()));
		Map<Integer, List<CrudItemCoordinate>> crudItemCoordinatesByMapId = crudItemCoordinates.stream()
			.collect(Collectors.groupingBy(CrudItem::getParentId));

		crudItemMapsById.forEach((id, crudItemMap) ->
			crudItemMap.setCoordinates(crudItemCoordinatesByMapId.get(id)));

		return crudItemMaps;
	}

	private void setParentId(
		Integer parentId,
		List<List<SqlFunctionParameter>> itemInParameters)
	{
		// set the parentId in the itemInParameters
		itemInParameters.stream()
			.flatMap(List::stream)
			.filter(sqlFunctionParameter -> sqlFunctionParameter.getName()
				.equals(CrudItemCoordinate.MAP_ID_COLUMN)
				&& sqlFunctionParameter instanceof IntegerSqlFunctionParameter)
			.map(sqlFunctionParameter -> (IntegerSqlFunctionParameter) sqlFunctionParameter)
			.forEach(sqlFunctionParameter -> sqlFunctionParameter.setValue(
				parentId));
	}
}
