DROP FUNCTION IF EXISTS read_cattle_map;
CREATE FUNCTION read_cattle_map
	( IN user_id INT
	, IN id INT
	)
RETURNS SETOF cattle_maps_readonly
AS
$body$
	BEGIN
		IF permission_check_cattle_map(set_user_id(read_cattle_map.user_id), read_cattle_map.id) THEN
			RETURN QUERY
			SELECT
				  cm.id
				, cm.farm_id
				, cm.target_id
				, c.name
				, cm.created_date
				, cm.modified_date
			FROM cattle_maps AS cm
			JOIN cattle AS c ON cm.target_id = c.id
			WHERE cm.id = read_cattle_map.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
