DROP FUNCTION IF EXISTS delete_farm_permission;
 CREATE FUNCTION delete_farm_permission
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF can_delete_farm_permission(delete_farm_permission.user_id, delete_farm_permission.id) THEN
			DELETE
			FROM farm_permissions AS fp
			WHERE fp.id = delete_farm_permission.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
