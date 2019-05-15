CREATE OR REPLACE FUNCTION create_poultry_inspection
	( poultry_inspection_group_id INT
	, poultry_id INT
	, bird_count INT
	, egg_count INT
	, OUT id INT
	)
AS
$body$
	INSERT INTO poultry_inspections
		( poultry_inspection_group_id
		, poultry_id
		, bird_count
		, egg_count
		)
	SELECT
		  create_poultry_inspection.poultry_inspection_group_id
		, create_poultry_inspection.poultry_id
		, create_poultry_inspection.bird_count
		, create_poultry_inspection.egg_count
	FROM poultry_inspection_groups AS pig
	WHERE pig.id = create_poultry_inspection.poultry_inspection_group_id
	RETURNING id;
$body$
LANGUAGE SQL;
