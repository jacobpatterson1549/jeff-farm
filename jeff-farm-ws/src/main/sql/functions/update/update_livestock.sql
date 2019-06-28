DROP FUNCTION IF EXISTS update_livestock;
CREATE FUNCTION update_livestock
	( IN user_id INT
	, IN id INT
	, IN name VARCHAR(255)
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_livestock(set_user_id(update_livestock.user_id), update_livestock.id) THEN
			UPDATE livestock AS h
				SET
					  name = update_livestock.name
				WHERE h.id = update_livestock.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
