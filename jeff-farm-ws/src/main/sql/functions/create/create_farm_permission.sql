DROP FUNCTION IF EXISTS create_farm_permission;
CREATE FUNCTION create_farm_permission
	( IN user_id INT
	, IN farm_id INT
	, IN user_name VARCHAR(20) -- the user to ad the permission for
	, OUT id INT
	)
AS
$body$
		INSERT INTO farm_permissions
    		( farm_id
    		, user_id
    		)
    	SELECT
    		  f.id
    		, u.id
    	FROM farms AS f
    	CROSS JOIN users AS u
    	WHERE f.id = create_farm_permission.farm_id
    		AND u.user_name = create_farm_permission.user_name
    		AND permission_check_farm(set_user_id(create_farm_permission.user_id), create_farm_permission.farm_id)
    		AND permission_check_user(set_user_id(create_farm_permission.user_id), u.id)
    	RETURNING id;
$body$
LANGUAGE SQL;
