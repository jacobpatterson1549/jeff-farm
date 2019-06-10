DROP FUNCTION IF EXISTS delete_poultry_inspections;
CREATE OR REPLACE FUNCTION delete_poultry_inspections
	( IN group_id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM poultry_inspections AS pi
	WHERE pi.group_id = delete_poultry_inspections.group_id;
$body$
LANGUAGE SQL;
