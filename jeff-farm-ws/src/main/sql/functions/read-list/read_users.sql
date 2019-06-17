DROP FUNCTION IF EXISTS read_users;
CREATE FUNCTION read_users
	( IN user_id INT
	)
RETURNS SETOF users
AS
$body$
	BEGIN
		IF permission_check_farm(set_user_id(read_users.user_id), read_users.farm_id) THEN
			RETURN QUERY
			SELECT
				  u.id
				, u.user_name
				, CAST(NULL AS CHAR(86)) -- password
				, u.first_name
				, u.last_name
				, u.created_date
				, u.modified_date
			FROM users AS u;
		END IF;
	END
$body$
LANGUAGE plpgsql;
