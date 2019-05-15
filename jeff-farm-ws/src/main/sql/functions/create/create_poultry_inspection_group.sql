CREATE OR REPLACE FUNCTION create_poultry_inspection_group
	( IN farm_id INT
	, IN notes VARCHAR(255)
	, OUT id INT
	)
AS
$body$
	INSERT INTO poultry_inspection_groups
		( farm_id
		, notes
		)
	SELECT
		  create_poultry_inspection_group.farm_id
		, create_poultry_inspection_group.notes
	FROM farms AS f
	WHERE f.id = create_poultry_inspection_group.farm_id
	RETURNING id;
$body$
LANGUAGE SQL;
