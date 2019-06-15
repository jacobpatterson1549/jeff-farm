DROP FUNCTION IF EXISTS  delete_poultry;
CREATE FUNCTION delete_poultry
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM poultry AS h
	WHERE permission_check_poultry(set_user_id(user_id), id)
		AND h.id = delete_poultry.id;
$body$
LANGUAGE SQL;
