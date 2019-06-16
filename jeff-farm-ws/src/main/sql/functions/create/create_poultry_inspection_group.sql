DROP FUNCTION IF EXISTS create_poultry_inspection_group;
CREATE FUNCTION create_poultry_inspection_group
	( IN user_id INT
	, IN farm_id INT
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
	WHERE permission_check_farm(set_user_id(create_poultry_inspection_group.user_id), create_poultry_inspection_group.farm_id)
		AND f.id = create_poultry_inspection_group.farm_id
	RETURNING id;
$body$
LANGUAGE SQL;
