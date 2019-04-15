CREATE OR REPLACE FUNCTION can_delete_farm
    ( IN id INT
    , OUT can_delete BOOLEAN
	)
AS
$body$
	SELECT CASE WHEN COUNT(*) = 0 THEN TRUE ELSE FALSE END AS can_delete
		FROM hives AS h
		WHERE h.farm_id = can_delete_farm.id;
$body$
LANGUAGE SQL;
