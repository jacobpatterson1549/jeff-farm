DROP FUNCTION IF EXISTS create_poultry_inspection;
CREATE OR REPLACE FUNCTION create_poultry_inspection
	( group_id INT
	, target_id INT
	, bird_count INT
	, egg_count INT
	, OUT id INT
	)
AS
$body$
	INSERT INTO poultry_inspections
		( group_id
		, target_id
		, bird_count
		, egg_count
		)
	SELECT
		  create_poultry_inspection.group_id
		, create_poultry_inspection.target_id
		, create_poultry_inspection.bird_count
		, create_poultry_inspection.egg_count
	FROM poultry_inspection_groups AS pig
	JOIN poultry AS p ON create_poultry_inspection.target_id = p.id
	WHERE pig.id = create_poultry_inspection.group_id
		AND pig.farm_id = p.farm_id
	RETURNING id;
$body$
LANGUAGE SQL;
