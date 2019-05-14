CREATE OR REPLACE FUNCTION create_poultry
	( IN farm_id INT
	, IN name VARCHAR(255)
	, OUT id INT
	)
AS
$body$
	INSERT INTO poultry
		( farm_id
		, name
		)
	SELECT
		  create_poultry.farm_id
		, create_poultry.name
	FROM farms AS f
	WHERE f.id = create_poultry.farm_id
	RETURNING id;
$body$
LANGUAGE SQL;
