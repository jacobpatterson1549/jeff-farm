DROP FUNCTION IF EXISTS delete_hive_inspections;
CREATE FUNCTION delete_hive_inspections
	( IN user_id INT
	, IN group_id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_hive_inspection_group(set_user_id(delete_hive_inspections.user_id), delete_hive_inspections.group_id) THEN
			DELETE
			FROM hive_inspections AS pi
			WHERE pi.group_id = delete_hive_inspections.group_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
