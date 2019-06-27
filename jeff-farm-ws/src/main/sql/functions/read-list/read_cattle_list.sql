DROP FUNCTION IF EXISTS read_cattle_list;
CREATE FUNCTION read_cattle_list
	( IN user_id INT
	, IN farm_id INT
	)
RETURNS SETOF cattle
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(read_cattle_list.user_id), read_cattle_list.farm_id) THEN
			RETURN QUERY
			SELECT
				  c.id
				, c.farm_id
				, c.name
				, c.created_date
				, c.modified_date
			FROM cattle AS c
			WHERE c.farm_id = read_cattle_list.farm_id
			ORDER BY h.created_date DESC;
		END IF;
	END
$body$
LANGUAGE plpgsql;
