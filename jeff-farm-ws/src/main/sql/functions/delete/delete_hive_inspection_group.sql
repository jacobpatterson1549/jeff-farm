DROP FUNCTION IF EXISTS delete_hive_inspection_group;
CREATE FUNCTION delete_hive_inspection_group
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_hive_inspection_group(set_user_id(delete_hive_inspection_group.user_id), delete_hive_inspection_group.id) THEN
			DELETE
			FROM hive_inspection_groups AS pig
			WHERE pig.id = delete_hive_inspection_group.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
