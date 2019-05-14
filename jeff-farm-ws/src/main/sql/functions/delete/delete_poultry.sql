CREATE OR REPLACE FUNCTION delete_poultry
	( IN id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM poultry AS h
	WHERE h.id = delete_poultry.id;
$body$
LANGUAGE SQL;
