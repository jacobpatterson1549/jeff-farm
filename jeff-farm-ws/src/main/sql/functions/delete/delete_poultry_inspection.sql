DROP FUNCTION IF EXISTS delete_poultry_inspection;
CREATE FUNCTION delete_poultry_inspection
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM poultry_inspections AS pi
	WHERE permission_check_poultry_inspection(set_user_id(delete_poultry_inspection.user_id), delete_poultry_inspection.id)
		AND pi.id = delete_poultry_inspection.id;
$body$
LANGUAGE SQL;
