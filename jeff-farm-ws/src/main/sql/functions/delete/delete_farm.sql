DROP FUNCTION IF EXISTS delete_farm;
 CREATE FUNCTION delete_farm
	( IN id INT
	, IN user_id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM farms AS f
	WHERE permission_check_farm(set_user_id(user_id), id)
		AND f.id = delete_farm.id;
$body$
LANGUAGE SQL;
