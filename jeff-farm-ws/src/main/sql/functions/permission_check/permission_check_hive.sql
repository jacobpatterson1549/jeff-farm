DROP FUNCTION IF EXISTS permission_check_hive;
CREATE FUNCTION permission_check_hive
	( IN user_id INT
	, IN hive_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT CASE WHEN COUNT(*) = 0 THEN FALSE ELSE TRUE END INTO permission_check
			FROM farm_permissions AS fp
			JOIN hives AS h ON fp.farm_id = h.farm_id
			WHERE fp.user_id = permission_check_hive.user_id
				AND h.id = permission_check_hive.hive_id;
		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to hive %.', user_id, hive_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
