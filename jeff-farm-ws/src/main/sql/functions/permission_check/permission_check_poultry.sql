DROP FUNCTION IF EXISTS permission_check_poultry;
CREATE FUNCTION permission_check_poultry
	( IN user_id INT
	, IN poultry_id INT
	, OUT permission_check BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT CASE WHEN COUNT(*) = 0 THEN FALSE ELSE TRUE END INTO permission_check
			FROM farm_permissions AS fp
			JOIN poultry AS p ON fp.farm_id = p.farm_id
			WHERE fp.user_id = permission_check_poultry.user_id
				AND p.id = permission_check_poultry.poultry_id;
		IF NOT permission_check THEN
			RAISE EXCEPTION 'User % does not have access to poultry %.', user_id, poultry_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
