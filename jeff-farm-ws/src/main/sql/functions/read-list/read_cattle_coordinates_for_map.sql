DROP FUNCTION IF EXISTS read_cattle_coordinates_for_group;
CREATE FUNCTION read_cattle_coordinates_for_group
	( IN user_id INT
	, IN map_id INT
	)
RETURNS SETOF cattle_coordinates
AS
$body$
	BEGIN
		IF permission_check_cattle_map(set_user_id(read_cattle_coordinates_for_group.user_id), read_cattle_coordinates_for_group.map_id) THEN
			RETURN QUERY
			SELECT
				  cc.id
				, cc.map_id
				, cc.latitude
				, cc.longitude
				, cc.display_order
				, cc.created_date
				, cc.modified_date
			FROM cattle_coordinates AS cc
			WHERE cc.map_id = read_cattle_coordinates_for_group.map_id
			ORDER BY cc.display_order ASC;
		END IF;
	END
$body$
LANGUAGE plpgsql;
