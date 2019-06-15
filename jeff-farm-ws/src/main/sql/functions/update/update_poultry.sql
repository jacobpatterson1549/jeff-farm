DROP FUNCTION IF EXISTS update_poultry;
CREATE FUNCTION update_poultry
	( IN id INT
	, IN user_id INT
	, IN name VARCHAR(255)
	)
RETURNS VOID
AS
$body$
	UPDATE poultry AS h
		SET
			  name = update_poultry.name
		WHERE permission_check_poultry(set_user_id(user_id), id)
			AND h.id = update_poultry.id;
$body$
LANGUAGE SQL;
