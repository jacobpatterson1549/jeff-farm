CREATE OR REPLACE FUNCTION can_delete_farm(IN id INT)
RETURNS BOOLEAN
AS
$body$
	SELECT CASE WHEN COUNT(*) = 0 THEN TRUE ELSE FALSE END AS can_delete
		FROM hives AS h
		WHERE h.farm_id = id;
$body$
LANGUAGE SQL;
