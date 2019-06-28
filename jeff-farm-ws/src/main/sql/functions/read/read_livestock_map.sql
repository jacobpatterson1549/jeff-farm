DROP FUNCTION IF EXISTS read_livestock_map;
CREATE FUNCTION read_livestock_map
	( IN user_id INT
	, IN id INT
	)
RETURNS SETOF livestock_maps_readonly
AS
$body$
	BEGIN
		IF permission_check_livestock_map(set_user_id(read_livestock_map.user_id), read_livestock_map.id) THEN
			RETURN QUERY
			SELECT
				  cm.id
				, cm.farm_id
				, cm.target_id
				, c.name
				, cm.created_date
				, cm.modified_date
			FROM livestock_maps AS cm
			JOIN livestock AS c ON cm.target_id = c.id
			WHERE cm.id = read_livestock_map.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
