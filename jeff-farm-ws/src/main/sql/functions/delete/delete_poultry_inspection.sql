CREATE OR REPLACE FUNCTION delete_poultry_inspection
	( IN id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM poultry_inspections AS pi
	WHERE pi.id = delete_poultry_inspection.id;
$body$
LANGUAGE SQL;
