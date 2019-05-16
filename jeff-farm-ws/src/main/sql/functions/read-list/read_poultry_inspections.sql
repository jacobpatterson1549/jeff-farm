DROP FUNCTION IF EXISTS read_poultry_inspections;
CREATE FUNCTION read_poultry_inspections(IN poultry_inspection_group_id INT)
RETURNS SETOF poultry_inspections
AS
$body$
	SELECT
		  pi.id
		, pi.poultry_inspection_group_id
		, pi.poultry_id
		, pi.bird_count
		, pi.egg_count
		, pi.created_date
		, pi.modified_date
	FROM poultry_inspections AS pi
	WHERE pi.poultry_inspection_group_id = read_poultry_inspections.poultry_inspection_group_id;
$body$
LANGUAGE SQL;
