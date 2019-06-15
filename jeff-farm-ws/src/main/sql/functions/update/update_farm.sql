DROP FUNCTION IF EXISTS update_farm;
CREATE FUNCTION update_farm
	( IN id INT
	, IN user_id INT
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
		WHERE permission_check_farm(set_user_id(user_id), id)
			AND f.id = update_farm.id;
$body$
LANGUAGE SQL;
