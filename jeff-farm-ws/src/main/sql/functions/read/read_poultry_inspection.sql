DROP FUNCTION IF EXISTS read_poultry_inspection;
CREATE FUNCTION read_poultry_inspection
	( IN user_id INT
	, IN id INT
	)
RETURNS SETOF poultry_inspections_readonly
AS
$body$
	BEGIN
		IF permission_check_poultry_inspection(set_user_id(read_poultry_inspection.user_id), read_poultry_inspection.id) THEN
			RETURN QUERY
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
			WHERE pi.id = read_poultry_inspection.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
