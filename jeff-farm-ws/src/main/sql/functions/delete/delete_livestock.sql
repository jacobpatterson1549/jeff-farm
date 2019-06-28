DROP FUNCTION IF EXISTS delete_livestock;
CREATE FUNCTION delete_livestock
	( IN user_id INT
	, IN id INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_livestock(set_user_id(delete_livestock.user_id), delete_livestock.id) THEN
			DELETE
			FROM livestock AS c
			WHERE c.id = delete_livestock.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
