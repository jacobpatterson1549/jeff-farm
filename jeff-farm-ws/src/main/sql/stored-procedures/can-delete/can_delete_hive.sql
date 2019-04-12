CREATE OR REPLACE FUNCTION can_delete_hive(IN id INT)
RETURNS BOOLEAN
AS
$body$
	SELECT CASE WHEN COUNT(*) = 0 THEN TRUE ELSE FALSE END AS can_delete
		FROM hive_inspections AS hi
		WHERE hi.hive_id = id;
$body$
LANGUAGE SQL;
