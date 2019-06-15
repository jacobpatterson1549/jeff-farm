DROP FUNCTION IF EXISTS read_poultry_inspection_groups;
CREATE FUNCTION read_poultry_inspection_groups
	( IN farm_id INT
	, IN user_id INT
	)
RETURNS SETOF poultry_inspection_groups
AS
$body$
	SELECT
		  pig.id
		, pig.farm_id
		, pig.notes
		, pig.created_date
		, pig.modified_date
	FROM poultry_inspection_groups AS pig
	WHERE permission_check_farm(set_user_id(user_id), farm_id)
		AND pig.farm_id = read_poultry_inspection_groups.farm_id;
$body$
LANGUAGE SQL;
