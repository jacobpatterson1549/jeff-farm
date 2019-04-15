CREATE OR REPLACE FUNCTION delete_hive_inspection
	( IN id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM hive_inspections AS hi
	WHERE hi.id = delete_hive_inspection.id;
$body$
LANGUAGE SQL;
