DROP FUNCTION IF EXISTS read_poultry_inspection_group;
CREATE FUNCTION read_poultry_inspection_group
	( IN user_id INT
	, IN id INT
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
	WHERE permission_check_poultry_inspection_group(set_user_id(user_id), id)
		AND pig.id = read_poultry_inspection_group.id;
$body$
LANGUAGE SQL;
