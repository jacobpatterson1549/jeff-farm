DROP FUNCTION IF EXISTS update_poultry;
CREATE FUNCTION update_poultry
	( IN user_id INT
	, IN id INT
	, IN name VARCHAR(255)
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_poultry(set_user_id(user_id), update_poultry.id) THEN
			UPDATE poultry AS h
				SET
					  name = update_poultry.name
				WHERE h.id = update_poultry.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
