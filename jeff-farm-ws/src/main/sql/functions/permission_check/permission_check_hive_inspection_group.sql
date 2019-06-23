DROP FUNCTION IF EXISTS permission_check_hive_inspection_group;
CREATE FUNCTION permission_check_hive_inspection_group
	( IN user_id INT
	, IN hive_inspection_group_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT EXISTS
		(
			SELECT fp.user_id, hig.id
			FROM farm_permissions AS fp
			JOIN hive_inspection_groups AS hig ON fp.farm_id = hig.farm_id
			WHERE fp.user_id = permission_check_hive_inspection_group.user_id
				AND hig.id = permission_check_hive_inspection_group.hive_inspection_group_id
		)
		INTO permission_check;

		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to hive inspection group %.', permission_check_hive_inspection_group.user_id, permission_check_hive_inspection_group.hive_inspection_group_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
