DROP FUNCTION IF EXISTS read_farms;
CREATE FUNCTION read_farms
	( IN user_id INT
	)
RETURNS SETOF farms
AS
$body$
	SELECT
		  f.id
		, f.name
		, f.location
		, f.created_date
		, f.modified_date
	FROM farms AS f
	JOIN farm_permissions AS fp ON f.id = fp.farm_id
	WHERE fp.user_id = read_farms.user_id;
$body$
LANGUAGE SQL;
