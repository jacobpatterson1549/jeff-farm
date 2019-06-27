DROP FUNCTION IF EXISTS permission_check_cattle;
CREATE FUNCTION permission_check_cattle
	( IN user_id INT
	, IN cattle_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT EXISTS
		(
			SELECT fp.user_id, c.id
			FROM farm_permissions AS fp
			JOIN cattle AS c ON fp.farm_id = c.farm_id
			WHERE fp.user_id = permission_check_cattle.user_id
				AND c.id = permission_check_cattle.cattle_id
		)
		INTO permission_check;

		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to cattle %.', permission_check_cattle.user_id, permission_check_cattle.cattle_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
