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
	UPDATE farms AS f
		SET
			  name = update_farm.name
			, location = update_farm.location
		WHERE permission_check_farm(set_user_id(update_farm.user_id), update_farm.id)
			AND f.id = update_farm.id;
$body$
LANGUAGE SQL;
