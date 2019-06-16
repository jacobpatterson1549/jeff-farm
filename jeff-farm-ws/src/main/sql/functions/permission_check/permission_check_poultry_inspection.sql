DROP FUNCTION IF EXISTS permission_check_poultry_inspection;
CREATE FUNCTION permission_check_poultry_inspection
	( IN user_id INT
	, IN poultry_inspection_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT CASE WHEN COUNT(*) = 0 THEN FALSE ELSE TRUE END INTO permission_check
			FROM farm_permissions AS fp
			JOIN poultry_inspection_groups AS pig ON fp.farm_id = pig.farm_id
			JOIN poultry_inspections AS pi ON pig.id = pi.group_id
			WHERE fp.user_id = permission_check_hive.user_id
				AND pi.id = permission_check_poultry_inspection.poultry_inspection_id;
		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to poultry inspection %.', permission_check_poultry_inspection.user_id, permission_check_poultry_inspection.poultry_inspection_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
