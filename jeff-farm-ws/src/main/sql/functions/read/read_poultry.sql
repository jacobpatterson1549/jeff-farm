DROP FUNCTION IF EXISTS read_poultry;
CREATE FUNCTION read_poultry
	( IN user_id INT
	, IN id INT
	)
RETURNS SETOF poultry
AS
$body$
	SELECT
		  h.id
		, h.farm_id
		, h.name
		, h.created_date
		, h.modified_date
	FROM poultry AS h
	WHERE permission_check_poultry(set_user_id(read_poultry.user_id), read_poultry.id)
		AND h.id = read_poultry.id;
$body$
LANGUAGE SQL;
