DROP FUNCTION IF EXISTS read_hive;
CREATE FUNCTION read_hive
	( IN user_id INT
	, IN id INT
	)
RETURNS SETOF hives
AS
$body$
	BEGIN
		IF permission_check_hive(set_user_id(read_hive.user_id), read_hive.id) THEN
			RETURN QUERY
			SELECT
				  h.id
				, h.farm_id
				, h.name
				, h.queen_color
				, h.created_date
				, h.modified_date
			FROM hives AS h
			WHERE h.id = read_hive.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
