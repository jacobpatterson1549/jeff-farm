DROP FUNCTION IF EXISTS read_hive_inspection_group;
CREATE FUNCTION read_hive_inspection_group
	( IN user_id INT
	, IN id INT
	)
RETURNS SETOF hive_inspection_groups
AS
$body$
	BEGIN
		IF permission_check_hive_inspection_group(set_user_id(read_hive_inspection_group.user_id), read_hive_inspection_group.id) THEN
			RETURN QUERY
			SELECT
				  hig.id
				, hig.farm_id
				, hig.notes
				, hig.created_date
				, hig.modified_date
			FROM hive_inspection_groups AS hig
			WHERE hig.id = read_hive_inspection_group.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
