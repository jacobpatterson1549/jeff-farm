DROP FUNCTION IF EXISTS permission_check_hive_inspection;
CREATE FUNCTION permission_check_hive_inspection
	( IN user_id INT
	, IN hive_inspection_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT CASE WHEN COUNT(*) = 0 THEN FALSE ELSE TRUE END INTO permission_check
			FROM farm_permissions AS fp
			JOIN hives AS h ON fp.farm_id = h.farm_id
			JOIN hive_inspections AS hi ON h.id = hi.hive_id
			WHERE fp.user_id = permission_check_hive.user_id
				AND hi.id = permission_check_hive_inspection.hive_inspection_id;
		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to hive inspection %.', user_id, hive_inspection_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
