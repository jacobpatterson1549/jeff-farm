DROP FUNCTION IF EXISTS read_livestock_list;
CREATE FUNCTION read_livestock_list
	( IN user_id INT
	, IN farm_id INT
	)
RETURNS SETOF livestock
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(read_livestock_list.user_id), read_livestock_list.farm_id) THEN
			RETURN QUERY
			SELECT
				  c.id
				, c.farm_id
				, c.name
				, c.created_date
				, c.modified_date
			FROM livestock AS c
			WHERE c.farm_id = read_livestock_list.farm_id
			ORDER BY c.created_date DESC;
		END IF;
	END
$body$
LANGUAGE plpgsql;
