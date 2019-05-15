CREATE OR REPLACE FUNCTION update_poultry_inspection
	( IN id INT
	, IN bird_count INT
	, IN egg_count INT
	)
RETURNS VOID
AS
$body$
	UPDATE poultry_inspections AS h
		SET
			  bird_count = update_poultry_inspection.bird_count
			, egg_count = update_poultry_inspection.egg_count
		WHERE h.id = update_poultry_inspection.id;
$body$
LANGUAGE SQL;
