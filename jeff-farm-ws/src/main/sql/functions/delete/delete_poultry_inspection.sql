DROP FUNCTION IF EXISTS delete_poultry_inspection;
CREATE FUNCTION delete_poultry_inspection
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_poultry_inspection(set_user_id(delete_poultry_inspection.user_id), delete_poultry_inspection.id) THEN
			DELETE
			FROM poultry_inspections AS pi
			WHERE pi.id = delete_poultry_inspection.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
