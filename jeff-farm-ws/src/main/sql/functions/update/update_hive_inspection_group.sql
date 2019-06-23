DROP FUNCTION IF EXISTS update_hive_inspection_group;
CREATE FUNCTION update_hive_inspection_group
	( IN user_id INT
	, IN id INT
	, IN notes VARCHAR(4095)
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_hive_inspection_group(set_user_id(update_hive_inspection_group.user_id), update_hive_inspection_group.id) THEN
			UPDATE hive_inspection_groups AS pig
				SET
					  notes = update_hive_inspection_group.notes
				WHERE pig.id = update_hive_inspection_group.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
