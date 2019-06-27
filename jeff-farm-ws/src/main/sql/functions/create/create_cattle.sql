DROP FUNCTION IF EXISTS create_cattle;
CREATE FUNCTION create_cattle
	( IN user_id INT
	, IN farm_id INT
	, IN name VARCHAR(255)
	, OUT id INT
	)
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(create_cattle.user_id), create_cattle.farm_id) THEN
			INSERT INTO cattle
				( farm_id
				, name
				)
			SELECT
				  create_cattle.farm_id
				, create_cattle.name
			FROM farms AS f
			WHERE f.id = create_cattle.farm_id
			RETURNING LASTVAL()
			INTO create_cattle.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
