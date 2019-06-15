DROP FUNCTION IF EXISTS update_hive;
CREATE FUNCTION update_hive
	( IN user_id INT
	, IN id INT
	, IN name VARCHAR(255)
	, IN queen_color INT
	)
RETURNS VOID
AS
$body$
	UPDATE hives AS h
		SET
			  name = update_hive.name
			, queen_color = update_hive.queen_color
		WHERE permission_check_hive(set_user_id(user_id), id)
			AND h.id = update_hive.id;
$body$
LANGUAGE SQL;
