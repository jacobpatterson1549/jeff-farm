DROP FUNCTION IF EXISTS read_livestock;
CREATE FUNCTION read_livestock
	( IN user_id INT
	, IN id INT
	)
RETURNS SETOF livestock
AS
$body$
	BEGIN
		IF permission_check_livestock(set_user_id(read_livestock.user_id), read_livestock.id) THEN
			RETURN QUERY
			SELECT
				  c.id
				, c.farm_id
				, c.name
				, c.created_date
				, c.modified_date
			FROM livestock AS c
			WHERE c.id = read_livestock.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
