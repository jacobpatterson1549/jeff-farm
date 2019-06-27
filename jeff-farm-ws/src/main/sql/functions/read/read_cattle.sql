DROP FUNCTION IF EXISTS read_cattle;
CREATE FUNCTION read_cattle
	( IN user_id INT
	, IN id INT
	)
RETURNS SETOF cattle
AS
$body$
	BEGIN
		IF permission_check_cattle(set_user_id(read_cattle.user_id), read_cattle.id) THEN
			RETURN QUERY
			SELECT
				  c.id
				, c.farm_id
				, c.name
				, c.created_date
				, c.modified_date
			FROM cattle AS c
			WHERE c.id = read_cattle.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
