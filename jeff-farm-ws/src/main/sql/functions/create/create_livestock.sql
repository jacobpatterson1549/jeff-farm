DROP FUNCTION IF EXISTS create_livestock;
CREATE FUNCTION create_livestock
	( IN user_id INT
	, IN farm_id INT
	, IN name VARCHAR(255)
	, OUT id INT
	)
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(create_livestock.user_id), create_livestock.farm_id) THEN
			INSERT INTO livestock
				( farm_id
				, name
				)
			SELECT
				  create_livestock.farm_id
				, create_livestock.name
			FROM farms AS f
			WHERE f.id = create_livestock.farm_id
			RETURNING LASTVAL()
			INTO create_livestock.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
