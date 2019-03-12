CREATE OR REPLACE FUNCTION read_hives(IN farm_id INT)
RETURNS SETOF hives
AS
$body$
	SELECT h.*
	FROM hives AS h
	WHERE h.farm_id = farm_id;
$body$
LANGUAGE SQL;
