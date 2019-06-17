DROP FUNCTION IF EXISTS read_poultry_inspection_groups;
CREATE FUNCTION read_poultry_inspection_groups
	( IN user_id INT
	, IN farm_id INT
	)
RETURNS SETOF poultry_inspection_groups
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(read_poultry_inspection_groups.user_id), read_poultry_inspection_groups.farm_id) THEN
			RETURN QUERY
			SELECT
				  pig.id
				, pig.farm_id
				, pig.notes
				, pig.created_date
				, pig.modified_date
			FROM poultry_inspection_groups AS pig
			WHERE pig.farm_id = read_poultry_inspection_groups.farm_id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
