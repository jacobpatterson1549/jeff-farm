CREATE OR REPLACE FUNCTION read_hives(IN farm_id INT)
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
	WHERE h.farm_id = read_hives.farm_id;
$body$
LANGUAGE SQL;
