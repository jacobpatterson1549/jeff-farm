DROP FUNCTION IF EXISTS delete_farm;
 CREATE FUNCTION delete_farm
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM farm_permissions AS fp
	WHERE permission_check_farm_permission(set_user_id(delete_farm.user_id), fp.id)
		AND fp.farm_id = delete_farm.id;

	DELETE
	FROM farms AS f
	WHERE f.id = delete_farm.id; -- (permission check for user_id done above)

$body$
LANGUAGE SQL;
