DROP FUNCTION IF EXISTS read_poultry_inspection_group_list;
CREATE FUNCTION read_poultry_inspection_group_list(IN farm_id INT)
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
	WHERE pig.farm_id = read_poultry_inspection_group_list.farm_id;
$body$
LANGUAGE SQL;
