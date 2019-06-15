DROP FUNCTION IF EXISTS  delete_hive;
CREATE FUNCTION delete_hive
	( IN id INT
	, IN user_id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM hives AS h
	WHERE permission_check_hive(set_user_id(user_id), id)
		AND h.id = delete_hive.id;
$body$
LANGUAGE SQL;
