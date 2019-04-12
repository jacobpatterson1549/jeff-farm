CREATE OR REPLACE FUNCTION delete_hive_inspection(IN id INT)
RETURNS VOID
AS
$body$
	DELETE
	FROM hive_inspections AS hi
	WHERE hi.id = id;
$body$
LANGUAGE SQL;
