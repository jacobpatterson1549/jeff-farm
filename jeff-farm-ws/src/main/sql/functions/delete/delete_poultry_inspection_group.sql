CREATE OR REPLACE FUNCTION delete_poultry_inspection_group
	( IN id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM poultry_inspection_groups AS pig
	WHERE pig.id = delete_poultry_inspection_group.id;
$body$
LANGUAGE SQL;
