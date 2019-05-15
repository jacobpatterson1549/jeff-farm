CREATE OR REPLACE FUNCTION update_poultry_inspection_group
	( IN id INT
	, IN notes VARCHAR(4095)
	)
RETURNS VOID
AS
$body$
	UPDATE poultry_inspection_groups AS pig
		SET
			  notes = update_poultry_inspection_group.notes
		WHERE pig.id = update_poultry_inspection_group.id;
$body$
LANGUAGE SQL;
