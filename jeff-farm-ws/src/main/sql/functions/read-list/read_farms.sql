DROP FUNCTION IF EXISTS read_farms;
CREATE FUNCTION read_farms
	( IN user_id INT
	)
RETURNS SETOF farms
AS
$body$
	BEGIN
		-- Ensure the user exists:
		IF permission_check_user(set_user_id(read_farms.user_id), read_farms.user_id) THEN
			RETURN QUERY
			SELECT
				  f.id
				, f.name
				, f.location
				, f.created_date
				, f.modified_date
			FROM farms AS f
			JOIN farm_permissions AS fp ON f.id = fp.farm_id
			WHERE fp.user_id = read_farms.user_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
