CREATE OR REPLACE FUNCTION delete_poultry_inspections
	( IN poultry_inspection_group_id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM poultry_inspections AS pi
	WHERE pi.poultry_inspection_group_id = delete_poultry_inspections.poultry_inspection_group_id;
$body$
LANGUAGE SQL;
