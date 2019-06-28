DROP FUNCTION IF EXISTS create_cattle_map;
CREATE FUNCTION create_cattle_map
	( IN user_id INT
	, IN farm_id INT
	, IN target_id INT
	, OUT id INT
	)
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(create_cattle_map.user_id), create_cattle_map.farm_id)
				AND permission_check_cattle(set_user_id(create_cattle_map.user_id), create_cattle_map.target_id)
			THEN
			INSERT INTO cattle_maps
				( farm_id
				, target_id
				)
			SELECT
				  create_cattle_map.farm_id
				, create_cattle_map.target_id
			FROM farms AS f
			WHERE f.id = create_cattle_map.farm_id
			RETURNING LASTVAL()
			INTO create_cattle_map.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
