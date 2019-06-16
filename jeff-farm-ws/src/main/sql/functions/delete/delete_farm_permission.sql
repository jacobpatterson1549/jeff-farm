DROP FUNCTION IF EXISTS delete_farm_permission_permission;
 CREATE FUNCTION delete_farm_permission
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM farm_permissions AS fp
	WHERE can_delete_farm_permission(delete_farm_permission.user_id, delete_farm_permission.id)
		AND fp.id = delete_farm_permission.id;
$body$
LANGUAGE SQL;
