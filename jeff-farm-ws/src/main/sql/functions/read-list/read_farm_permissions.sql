DROP FUNCTION IF EXISTS read_farm_permissions;
CREATE FUNCTION read_farm_permissions
	( IN user_id INT
	, IN farm_id INT
	)
RETURNS TABLE
	( id INT
	, farm_id INT
	, user_name VARCHAR(20)
	, created_date TIMESTAMP
	, modified_date TIMESTAMP
	)
AS
$body$
	SELECT
		  fp.id
		, fp.farm_id
		, u.user_name
		, fp.created_date
		, fp.modified_date
	FROM farm_permissions AS fp
	JOIN users AS u ON fp.user_id = u.id
	WHERE permission_check_farm(set_user_id(read_farm_permissions.user_id), read_farm_permissions.farm_id)
		AND fp.farm_id = read_farm_permissions.farm_id;
$body$
LANGUAGE SQL;
