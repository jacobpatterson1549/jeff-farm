CREATE OR REPLACE FUNCTION read_hive_inspections(IN hive_id INT)
RETURNS SETOF hive_inspections
AS
$body$
	SELECT hi.*
	FROM hive_inspections AS hi
	WHERE hi.hive_id = hive_id;
$body$
LANGUAGE SQL;
