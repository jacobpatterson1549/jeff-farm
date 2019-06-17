DROP FUNCTION IF EXISTS read_poultry_inspection_group;
CREATE FUNCTION read_poultry_inspection_group
	( IN user_id INT
	, IN id INT
	)
RETURNS SETOF poultry_inspection_groups
AS
$body$
	BEGIN
		IF permission_check_poultry_inspection_group(set_user_id(read_poultry_inspection_group.user_id), read_poultry_inspection_group.id) THEN
			RETURN QUERY
			SELECT
				  pig.id
				, pig.farm_id
				, pig.notes
				, pig.created_date
				, pig.modified_date
			FROM poultry_inspection_groups AS pig
			WHERE pig.id = read_poultry_inspection_group.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
