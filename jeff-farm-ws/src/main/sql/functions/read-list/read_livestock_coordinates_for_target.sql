DROP FUNCTION IF EXISTS read_livestock_coordinates_for_target;
CREATE FUNCTION read_livestock_coordinates_for_target
	( IN user_id INT
	, IN livestock_id INT
	)
RETURNS SETOF livestock_coordinates
AS
$body$
	BEGIN
		IF permission_check_livestock(set_user_id(read_livestock_coordinates_for_target.user_id), read_livestock_coordinates_for_target.livestock_id) THEN
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
			JOIN livestock_maps AS cm ON cc.map_id = cm.id
			WHERE cm.livestock_id = read_livestock_coordinates_for_target.livestock_id
			ORDER BY cc.display_order ASC;
		END IF;
	END
$body$
LANGUAGE plpgsql;
