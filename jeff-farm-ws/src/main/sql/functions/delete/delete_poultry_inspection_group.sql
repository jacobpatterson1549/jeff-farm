DROP FUNCTION IF EXISTS delete_poultry_inspection_group;
CREATE FUNCTION delete_poultry_inspection_group
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM poultry_inspection_groups AS pig
	WHERE permission_check_poultry_inspection_group(set_user_id(user_id), id)
		AND pig.id = delete_poultry_inspection_group.id;
$body$
LANGUAGE SQL;
