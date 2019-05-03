DROP FUNCTION IF EXISTS set_user_id;
CREATE OR REPLACE FUNCTION set_user_id(IN id INT)
RETURNS INT
AS
$body$
	SELECT CAST(set_config(
		'jeff_farm_ws.user_id',
		CAST(set_user_id.id AS TEXT),
		-- TODO: Make this 'true' and do insert/update/delete in transaction:
		false -- is_local
		) AS INT);
$body$
LANGUAGE SQL;
