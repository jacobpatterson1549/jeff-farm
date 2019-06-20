DROP FUNCTION IF EXISTS update_hive;
CREATE FUNCTION update_hive
	( IN user_id INT
	, IN id INT
	, IN name VARCHAR(255)
	, IN queen_color INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_hive(set_user_id(update_hive.user_id), update_hive.id) THEN
			UPDATE hives AS h
				SET
					  name = update_hive.name
					, queen_color = update_hive.queen_color
				WHERE h.id = update_hive.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
