DROP FUNCTION IF EXISTS permission_check_poultry_inspection_group;
CREATE FUNCTION permission_check_poultry_inspection_group
	( IN user_id INT
	, IN poultry_inspection_group_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT CASE WHEN COUNT(*) = 0 THEN FALSE ELSE TRUE END INTO permission_check
			FROM farm_permissions AS fp
			JOIN poultry_inspection_groups AS pig ON h.id = pig.hive_id
			WHERE fp.user_id = permission_check_hive.user_id
				AND pig.id = permission_check_poultry_inspection_group.poultry_inspection_group_id;
		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to poultry inspection group %.', permission_check_poultry_inspection_group.user_id, permission_check_poultry_inspection_group.poultry_inspection_group_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
