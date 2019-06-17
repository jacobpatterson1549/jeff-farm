DROP FUNCTION IF EXISTS delete_poultry;
CREATE FUNCTION delete_poultry
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_poultry(set_user_id(delete_poultry.user_id), delete_poultry.id) THEN
			DELETE
			FROM poultry AS h
			WHERE h.id = delete_poultry.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
