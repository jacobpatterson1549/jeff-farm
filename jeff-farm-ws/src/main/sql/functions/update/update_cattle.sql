DROP FUNCTION IF EXISTS update_cattle;
CREATE FUNCTION update_cattle
	( IN user_id INT
	, IN id INT
	, IN name VARCHAR(255)
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_cattle(set_user_id(update_cattle.user_id), update_cattle.id) THEN
			UPDATE cattle AS h
				SET
					  name = update_cattle.name
				WHERE h.id = update_cattle.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
