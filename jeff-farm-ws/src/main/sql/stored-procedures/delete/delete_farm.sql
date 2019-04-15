CREATE OR REPLACE FUNCTION delete_farm
	( IN id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM farms AS f
	WHERE f.id = delete_farm.id;
$body$
LANGUAGE SQL;
