CREATE OR REPLACE FUNCTION can_delete_poultry
    ( IN id INT
    , OUT can_delete BOOLEAN
	)
AS
$body$
	SELECT CASE WHEN COUNT(*) = 0 THEN TRUE ELSE FALSE END
		FROM poultry_inspections AS pi
		WHERE pi.poultry_id = can_delete_poultry.id;
$body$
LANGUAGE SQL;
