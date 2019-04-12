CREATE OR REPLACE FUNCTION delete_hive_(IN id INT)
RETURNS VOID
AS
$body$
	DELETE
	FROM hives AS h
	WHERE h.id = id;
$body$
LANGUAGE SQL;
