DROP FUNCTION IF EXISTS update_poultry_inspection_group;
CREATE FUNCTION update_poultry_inspection_group
	( IN id INT
	, IN user_id INT
	, IN notes VARCHAR(4095)
	)
RETURNS VOID
AS
$body$
	UPDATE poultry_inspection_groups AS pig
		SET
			  notes = update_poultry_inspection_group.notes
		WHERE permission_check_poultry_inspection_group(set_user_id(user_id), id)
			AND pig.id = update_poultry_inspection_group.id;
$body$
LANGUAGE SQL;
