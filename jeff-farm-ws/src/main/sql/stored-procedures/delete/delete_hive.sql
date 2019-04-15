CREATE OR REPLACE FUNCTION delete_hive(IN id INT)
RETURNS VOID
AS
$body$
	DELETE
	FROM hives AS h
	WHERE h.id = delete_hive.id;
$body$
LANGUAGE SQL;
