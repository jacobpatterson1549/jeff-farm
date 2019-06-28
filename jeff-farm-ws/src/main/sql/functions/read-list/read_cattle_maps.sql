DROP FUNCTION IF EXISTS read_cattle_maps;
CREATE FUNCTION read_cattle_maps
	( IN user_id INT
	, IN farm_id INT
	)
RETURNS SETOF cattle_maps_readonly
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(read_cattle_maps.user_id), read_cattle_maps.farm_id) THEN
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
			WHERE cm.farm_id = read_cattle_maps.farm_id
			ORDER BY cm.created_date DESC;
		END IF;
	END
$body$
LANGUAGE plpgsql;
