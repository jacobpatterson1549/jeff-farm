DROP FUNCTION IF EXISTS read_poultry_inspection;
CREATE FUNCTION read_poultry_inspection(IN id INT)
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
	WHERE pi.id = read_poultry_inspection.id;
$body$
LANGUAGE SQL;
