DROP FUNCTION IF EXISTS create_poultry;
CREATE FUNCTION create_poultry
	( IN user_id INT
	, IN farm_id INT
	, IN name VARCHAR(255)
	, OUT id INT
	)
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(create_poultry.user_id), create_poultry.farm_id) THEN
			INSERT INTO poultry
				( farm_id
				, name
				)
			SELECT
				  create_poultry.farm_id
				, create_poultry.name
			FROM farms AS f
			WHERE f.id = create_poultry.farm_id
			RETURNING LASTVAL()
			INTO create_poultry.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
