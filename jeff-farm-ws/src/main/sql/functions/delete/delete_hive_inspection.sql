DROP FUNCTION IF EXISTS  delete_hive_inspection;
CREATE FUNCTION delete_hive_inspection
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM hive_inspections AS hi
	WHERE permission_check_hive_inspection(set_user_id(delete_hive_inspection.user_id), delete_hive_inspection.id)
		AND hi.id = delete_hive_inspection.id;
$body$
LANGUAGE SQL;
