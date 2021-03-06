DROP FUNCTION IF EXISTS read_poultry;
CREATE FUNCTION read_poultry
	( IN user_id INT
	, IN id INT
	)
RETURNS SETOF poultry
AS
$body$
	BEGIN
		IF permission_check_poultry(set_user_id(read_poultry.user_id), read_poultry.id) THEN
			RETURN QUERY
			SELECT
				  h.id
				, h.farm_id
				, h.name
				, h.created_date
				, h.modified_date
			FROM poultry AS h
			WHERE h.id = read_poultry.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
