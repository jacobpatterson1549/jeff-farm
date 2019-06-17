DROP FUNCTION IF EXISTS delete_hive;
CREATE FUNCTION delete_hive
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_hive(set_user_id(delete_hive.user_id), delete_hive.id) THEN
			DELETE
			FROM hives AS h
			WHERE h.id = delete_hive.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
