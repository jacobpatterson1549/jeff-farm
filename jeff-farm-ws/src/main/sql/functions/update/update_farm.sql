DROP FUNCTION IF EXISTS update_farm;
CREATE FUNCTION update_farm
	( IN user_id INT
	, IN id INT
	, IN name VARCHAR(255)
	, IN location VARCHAR(255)
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(update_farm.user_id), update_farm.id) THEN
			UPDATE farms AS f
				SET
					  name = update_farm.name
					, location = update_farm.location
				WHERE f.id = update_farm.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
