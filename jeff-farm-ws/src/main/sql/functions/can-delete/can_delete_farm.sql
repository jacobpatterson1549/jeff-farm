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
		CROSS JOIN poultry AS p
		WHERE permission_check_farm(set_user_id(can_delete_farm.user_id), can_delete_farm.id)
			AND can_delete_farm.id IN (h.farm_id, p.farm_id);
$body$
LANGUAGE SQL;
