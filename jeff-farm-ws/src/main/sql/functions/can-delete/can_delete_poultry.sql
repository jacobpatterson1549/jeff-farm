DROP FUNCTION IF EXISTS can_delete_poultry;
CREATE FUNCTION can_delete_poultry
    ( IN user_id INT
    , IN id INT
    , OUT can_delete BOOLEAN
	)
AS
$body$
	SELECT CASE WHEN COUNT(*) = 0 THEN TRUE ELSE FALSE END
		FROM poultry_inspections AS pi
		WHERE permission_check_poultry(set_user_id(can_delete_poultry.user_id), can_delete_poultry.id)
			AND pi.target_id = can_delete_poultry.id;
$body$
LANGUAGE SQL;
