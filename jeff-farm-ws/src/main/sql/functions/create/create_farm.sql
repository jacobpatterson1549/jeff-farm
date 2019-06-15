DROP FUNCTION IF EXISTS create_farm;
CREATE FUNCTION create_farm
	( IN user_id INT
	, IN name VARCHAR(255)
	, IN location VARCHAR(255)
	, OUT id INT
	)
AS
$body$
	INSERT INTO farms
		( name
		, location
		)
	SELECT
		  create_farm.name
		, create_farm.location
	WHERE permission_check_user(set_user_id(user_id), user_id)
	RETURNING id;
$body$
LANGUAGE SQL;
