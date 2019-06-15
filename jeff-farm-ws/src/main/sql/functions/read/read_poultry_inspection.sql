DROP FUNCTION IF EXISTS read_poultry_inspection;
CREATE FUNCTION read_poultry_inspection
	( IN user_id INT
	, IN id INT
	)
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
	WHERE permission_check_poultry_inspection(set_user_id(user_id), read_poultry_inspection.id)
		AND pi.id = read_poultry_inspection.id;
$body$
LANGUAGE SQL;
