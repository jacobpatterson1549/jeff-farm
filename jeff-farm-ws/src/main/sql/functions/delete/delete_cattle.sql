DROP FUNCTION IF EXISTS delete_cattle;
CREATE FUNCTION delete_cattle
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_cattle(set_user_id(delete_cattle.user_id), delete_cattle.id) THEN
			DELETE
			FROM cattle AS h
			WHERE h.id = delete_cattle.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
