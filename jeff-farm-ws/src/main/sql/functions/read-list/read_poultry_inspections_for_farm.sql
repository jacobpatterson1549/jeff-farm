DROP FUNCTION IF EXISTS read_poultry_inspections_for_farm;
CREATE FUNCTION read_poultry_inspections_for_farm
	( IN user_id INT
	, IN farm_id INT
	)
RETURNS SETOF poultry_inspections_readonly
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(read_poultry_inspections_for_farm.user_id), read_poultry_inspections_for_farm.farm_id) THEN
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
			WHERE p.farm_id = read_poultry_inspections_for_farm.farm_id
			ORDER BY pi.group_id, pi.created_date DESC;
		END IF;
	END
$body$
LANGUAGE plpgsql;
