DROP FUNCTION IF EXISTS permission_check_poultry_inspection;
CREATE FUNCTION permission_check_poultry_inspection
	( IN user_id INT
	, IN poultry_inspection_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT EXISTS
		(
			SELECT fp.user_id, pi.id
			FROM farm_permissions AS fp
			JOIN poultry_inspection_groups AS pig ON fp.farm_id = pig.farm_id
			JOIN poultry_inspections AS pi ON pig.id = pi.group_id
			WHERE fp.user_id = permission_check_poultry_inspection.user_id
				AND pi.id = permission_check_poultry_inspection.poultry_inspection_id
		)
		INTO permission_check;

		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to poultry inspection %.', permission_check_poultry_inspection.user_id, permission_check_poultry_inspection.poultry_inspection_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
