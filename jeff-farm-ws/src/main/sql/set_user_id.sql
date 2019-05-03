DROP FUNCTION IF EXISTS set_user_id;
CREATE OR REPLACE FUNCTION set_user_id(IN id INT)
RETURNS INT
AS
$body$
	SELECT CAST(set_config(
		'jeff_farm_ws.user_id',
		CAST(set_user_id.id AS TEXT),
		true -- is_local (applies to transaction only)
		) AS INT);
$body$
LANGUAGE SQL;
