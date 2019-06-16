DROP FUNCTION IF EXISTS can_delete_hive;
CREATE FUNCTION can_delete_hive
    ( IN user_id INT
    , IN id INT
    , OUT can_delete BOOLEAN
	)
AS
$body$
	SELECT CASE WHEN COUNT(*) = 0 THEN TRUE ELSE FALSE END
		FROM hive_inspections AS hi
		WHERE permission_check_hive(set_user_id(can_delete_hive.user_id), can_delete_hive.id)
			AND hi.hive_id = can_delete_hive.id;
$body$
LANGUAGE SQL;
