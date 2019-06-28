DROP FUNCTION IF EXISTS read_livestock_coordinates_for_map;
CREATE FUNCTION read_livestock_coordinates_for_map
	( IN user_id INT
	, IN map_id INT
	)
RETURNS SETOF livestock_coordinates
AS
$body$
	BEGIN
		IF permission_check_livestock_map(set_user_id(read_livestock_coordinates_for_map.user_id), read_livestock_coordinates_for_map.map_id) THEN
			RETURN QUERY
			SELECT
				  cc.id
				, cc.map_id
				, cc.latitude
				, cc.longitude
				, cc.display_order
				, cc.created_date
				, cc.modified_date
			FROM livestock_coordinates AS cc
			WHERE cc.map_id = read_livestock_coordinates_for_map.map_id
			ORDER BY cc.display_order ASC;
		END IF;
	END
$body$
LANGUAGE plpgsql;
