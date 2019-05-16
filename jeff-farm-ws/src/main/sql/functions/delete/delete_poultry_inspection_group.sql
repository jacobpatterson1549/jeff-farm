CREATE OR REPLACE FUNCTION delete_poultry_inspection_group
	( IN id INT
	)
RETURNS VOID
AS
$body$
	SELECT delete_poultry_inspections(delete_poultry_inspection_group.id);

	DELETE
	FROM poultry_inspection_groups AS pig
	WHERE pig.id = delete_poultry_inspection_group.id;
$body$
LANGUAGE SQL;
