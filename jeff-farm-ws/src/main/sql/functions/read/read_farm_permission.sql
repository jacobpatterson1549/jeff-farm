DROP FUNCTION IF EXISTS read_farm_permission;
CREATE FUNCTION read_farm_permission
	( IN user_id INT
	, IN farm_permission_id INT
	)
RETURNS SETOF farm_permissions_readonly
AS
$body$
	BEGIN
		IF permission_check_farm_permission(set_user_id(read_farm_permission.user_id), read_farm_permission.id) THEN
			RETURN QUERY
			SELECT
				  fp.id
				, fp.farm_id
				, u.user_name
				, fp.created_date
				, fp.modified_date
			FROM farm_permissions AS fp
			JOIN users AS u ON fp.user_id = u.id
			WHERE fp.id = read_farm_permission.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
