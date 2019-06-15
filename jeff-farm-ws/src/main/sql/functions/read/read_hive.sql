DROP FUNCTION IF EXISTS read_hive;
CREATE FUNCTION read_hive
	( IN id INT
	, IN user_id INT
	)
RETURNS SETOF hives
AS
$body$
	SELECT
		  h.id
		, h.farm_id
		, h.name
		, h.queen_color
		, h.created_date
		, h.modified_date
	FROM hives AS h
	WHERE permission_check_hive(set_user_id(user_id), id)
		AND h.id = read_hive.id;
$body$
LANGUAGE SQL;
