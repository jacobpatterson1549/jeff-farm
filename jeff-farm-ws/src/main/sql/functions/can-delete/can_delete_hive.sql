DROP FUNCTION IF EXISTS can_delete_hive;
CREATE FUNCTION can_delete_hive
    ( IN user_id INT
    , IN id INT
    , OUT can_delete BOOLEAN
	)
AS
$body$
	BEGIN
		SELECT permission_check_hive(set_user_id(can_delete_hive.user_id), can_delete_hive.id)
			AND
		NOT EXISTS
		(
			SELECT hi.hive_id
			FROM hive_inspections AS hi
			WHERE hi.hive_id = can_delete_hive.id
		)
		into can_delete_hive.can_delete;
	END
$body$
LANGUAGE plpgsql;
