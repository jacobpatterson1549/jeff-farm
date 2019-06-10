DROP FUNCTION IF EXISTS read_poultry_inspections;
CREATE FUNCTION read_poultry_inspections(IN group_id INT)
RETURNS TABLE
	( id INT
	, group_id INT
	, target_id INT
	, target_name VARCHAR(255)
	, bird_count INT
	, egg_count INT
	, created_date TIMESTAMP
	, modified_date TIMESTAMP
	)
AS
$body$
	SELECT
		  pi.id
		, pi.group_id
		, pi.target_id
		, p.name
		, pi.bird_count
		, pi.egg_count
		, pi.created_date
		, pi.modified_date
	FROM poultry_inspections AS pi
	JOIN poultry AS p ON pi.target_id = p.id
	WHERE pi.group_id = read_poultry_inspections.group_id;
$body$
LANGUAGE SQL;
