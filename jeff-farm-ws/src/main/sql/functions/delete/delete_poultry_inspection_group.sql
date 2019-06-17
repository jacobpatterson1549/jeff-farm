DROP FUNCTION IF EXISTS delete_poultry_inspection_group;
CREATE FUNCTION delete_poultry_inspection_group
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_poultry_inspection_group(set_user_id(delete_poultry_inspection_group.user_id), delete_poultry_inspection_group.id) THEN
			DELETE
			FROM poultry_inspection_groups AS pig
			WHERE pig.id = delete_poultry_inspection_group.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
