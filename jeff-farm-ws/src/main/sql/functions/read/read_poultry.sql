DROP FUNCTION IF EXISTS read_poultry;
CREATE FUNCTION read_poultry
	( IN id INT
	, IN user_id INT
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
	WHERE h.id = read_poultry.id;
$body$
LANGUAGE SQL;
