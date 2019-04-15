CREATE OR REPLACE FUNCTION can_delete_hive
    ( IN id INT
    , OUT can_delete BOOLEAN
	)
AS
$body$
	SELECT CASE WHEN COUNT(*) = 0 THEN TRUE ELSE FALSE END
		FROM hive_inspections AS hi
		WHERE hi.hive_id = can_delete_hive.id;
$body$
LANGUAGE SQL;
