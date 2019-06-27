DROP FUNCTION IF EXISTS read_cattle_coordinates_for_farm;
CREATE FUNCTION read_cattle_coordinates_for_farm
	( IN user_id INT
	, IN farm_id INT
	)
RETURNS SETOF cattle_coordinates
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(read_cattle_coordinates_for_farm.user_id), read_cattle_coordinates_for_farm.farm_id) THEN
			RETURN QUERY
			SELECT
				  cc.id
				, cc.group_id
				, cc.latitude
				, cc.longitude
				, cc.display_order
				, cc.created_date
				, cc.modified_date
			FROM cattle_coordinates AS cc
			JOIN cattle_maps AS cm ON cc.group_id = cm.id
			WHERE cm.farm_id = read_cattle_coordinates_for_farm.farm_id
			ORDER BY cc.display_order ASC;
		END IF;
	END
$body$
LANGUAGE plpgsql;
