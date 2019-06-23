DROP FUNCTION IF EXISTS delete_poultry_inspections_for_group;
CREATE FUNCTION delete_poultry_inspections_for_group
	( IN user_id INT
	, IN group_id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_poultry_inspection_group(set_user_id(delete_poultry_inspections_for_group.user_id), delete_poultry_inspections_for_group.group_id) THEN
			DELETE
			FROM poultry_inspections AS pi
			WHERE pi.group_id = delete_poultry_inspections_for_group.group_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
