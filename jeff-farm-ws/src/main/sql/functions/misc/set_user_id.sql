DROP FUNCTION IF EXISTS set_user_id;
CREATE OR REPLACE FUNCTION set_user_id
	( IN user_id INT
	)
RETURNS INT
AS
$body$
	BEGIN
		RETURN
		(
			SELECT CAST(set_config(
				'jeff_farm_ws.user_id',
				CAST(set_user_id.user_id AS TEXT),
				true -- is_local (applies to transaction only)
				) AS INT)
		);
	END
$body$
LANGUAGE plpgsql;
