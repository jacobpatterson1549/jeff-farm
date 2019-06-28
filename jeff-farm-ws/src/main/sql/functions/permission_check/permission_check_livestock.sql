DROP FUNCTION IF EXISTS permission_check_livestock;
CREATE FUNCTION permission_check_livestock
	( IN user_id INT
	, IN livestock_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT EXISTS
		(
			SELECT fp.user_id, c.id
			FROM farm_permissions AS fp
			JOIN livestock AS c ON fp.farm_id = c.farm_id
			WHERE fp.user_id = permission_check_livestock.user_id
				AND c.id = permission_check_livestock.livestock_id
		)
		INTO permission_check;

		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to livestock %.', permission_check_livestock.user_id, permission_check_livestock.livestock_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
