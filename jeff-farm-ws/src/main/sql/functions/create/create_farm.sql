DROP FUNCTION IF EXISTS create_farm;
CREATE FUNCTION create_farm
	( IN user_id INT
	, IN name VARCHAR(255)
	, IN location VARCHAR(255)
	, OUT id INT
	)
AS
$body$
	BEGIN
		IF permission_check_user(set_user_id(create_farm.user_id), create_farm.user_id) THEN
			WITH new_farm (id) AS (
				INSERT INTO farms
					( name
					, location
					)
				SELECT
					  create_farm.name
					, create_farm.location
				RETURNING LASTVAL()
			)
			, new_farm_permission AS (
				INSERT INTO farm_permissions
					( farm_id
					, user_id
					)
				SELECT
					 f.id
					, create_farm.user_id
				FROM new_farm AS f
			)
			SELECT CAST(nf.id AS INT)
			FROM new_farm AS nf
			INTO create_farm.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
