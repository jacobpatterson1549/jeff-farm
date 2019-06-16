DROP FUNCTION IF EXISTS read_farm_permission;
CREATE FUNCTION read_farm_permission
	( IN user_id INT
	, IN id INT
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
	WHERE permission_check_farm_permission(set_user_id(read_farm_permission.user_id), read_farm_permission.id)
		AND fp.id = read_farm_permission.id;
$body$
LANGUAGE SQL;
