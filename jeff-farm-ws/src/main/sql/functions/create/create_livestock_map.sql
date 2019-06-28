DROP FUNCTION IF EXISTS create_livestock_map;
CREATE FUNCTION create_livestock_map
	( IN user_id INT
	, IN farm_id INT
	, IN target_id INT
	, OUT id INT
	)
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(create_livestock_map.user_id), create_livestock_map.farm_id)
				AND permission_check_livestock(set_user_id(create_livestock_map.user_id), create_livestock_map.target_id)
			THEN
			INSERT INTO livestock_maps
				( farm_id
				, target_id
				)
			SELECT
				  create_livestock_map.farm_id
				, create_livestock_map.target_id
			FROM farms AS f
			WHERE f.id = create_livestock_map.farm_id
			RETURNING LASTVAL()
			INTO create_livestock_map.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
