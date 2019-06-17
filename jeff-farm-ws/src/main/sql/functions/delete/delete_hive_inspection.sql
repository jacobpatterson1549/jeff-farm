DROP FUNCTION IF EXISTS delete_hive_inspection;
CREATE FUNCTION delete_hive_inspection
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_hive_inspection(set_user_id(delete_hive_inspection.user_id), delete_hive_inspection.id) THEN
			DELETE
			FROM hive_inspections AS hi
			WHERE hi.id = delete_hive_inspection.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
