DROP FUNCTION IF EXISTS permission_check_poultry;
CREATE FUNCTION permission_check_poultry
	( IN user_id INT
	, IN poultry_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT EXISTS
		(
			SELECT fp.user_id, p.id
			FROM farm_permissions AS fp
			JOIN poultry AS p ON fp.farm_id = p.farm_id
			WHERE fp.user_id = permission_check_poultry.user_id
				AND p.id = permission_check_poultry.poultry_id
		)
		INTO permission_check;

		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to poultry %.', permission_check_poultry.user_id, permission_check_poultry.poultry_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
