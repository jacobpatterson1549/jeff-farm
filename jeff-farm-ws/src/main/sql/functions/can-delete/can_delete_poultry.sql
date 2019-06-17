DROP FUNCTION IF EXISTS can_delete_poultry;
CREATE FUNCTION can_delete_poultry
    ( IN user_id INT
    , IN id INT
    , OUT can_delete BOOLEAN
	)
AS
$body$
	SELECT permission_check_poultry(set_user_id(can_delete_poultry.user_id), can_delete_poultry.id)
		AND
	NOT EXISTS
	(
		SELECT pi.target_id
		FROM poultry_inspections AS pi
		WHERE pi.target_id = can_delete_poultry.id
	);
$body$
LANGUAGE SQL;
