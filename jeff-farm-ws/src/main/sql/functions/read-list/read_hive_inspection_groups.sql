DROP FUNCTION IF EXISTS read_hive_inspection_groups;
CREATE FUNCTION read_hive_inspection_groups
	( IN user_id INT
	, IN farm_id INT
	)
RETURNS SETOF hive_inspection_groups
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(read_hive_inspection_groups.user_id), read_hive_inspection_groups.farm_id) THEN
			RETURN QUERY
			SELECT
				  hig.id
				, hig.farm_id
				, hig.notes
				, hig.created_date
				, hig.modified_date
			FROM hive_inspection_groups AS hig
			WHERE hig.farm_id = read_hive_inspection_groups.farm_id
			ORDER BY hig.created_date DESC;
		END IF;
	END
$body$
LANGUAGE plpgsql;
