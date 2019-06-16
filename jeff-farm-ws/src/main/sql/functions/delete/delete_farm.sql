DROP FUNCTION IF EXISTS delete_farm;
 CREATE FUNCTION delete_farm
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	DELETE
	FROM farms AS f
	-- TODO: Have delete scripts call can_delete functions (which should call permission_check functions).
	WHERE permission_check_farm(set_user_id(delete_farm.user_id), delete_farm.id)
		AND f.id = delete_farm.id;

	DELETE
	FROM farm_permissions AS fp
	WHERE fp.farm_id = delete_farm.id;
$body$
LANGUAGE SQL;
