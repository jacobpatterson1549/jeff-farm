CREATE OR REPLACE FUNCTION read_farm
	( IN id INT
	, OUT farms
	)
RETURNS farms
AS
$body$
	SELECT
		  f.id
		, f.name
		, f.location
		, f.created_date
		, f.modified_date
	FROM farms AS f
	WHERE f.id = read_farm.id;
$body$
LANGUAGE SQL;
