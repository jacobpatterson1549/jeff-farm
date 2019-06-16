DROP FUNCTION IF EXISTS can_delete_farm;
CREATE FUNCTION can_delete_farm
    ( IN user_id INT
    , IN id INT
    , OUT can_delete BOOLEAN
	)
AS
$body$
	SELECT CASE WHEN COUNT(*) = 0 THEN TRUE ELSE FALSE END
		FROM hives AS h
		WHERE permission_check_farm(set_user_id(user_id), can_delete_farm.id)
			AND h.farm_id = can_delete_farm.id;
$body$
LANGUAGE SQL;
