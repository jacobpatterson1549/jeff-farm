DROP FUNCTION IF EXISTS update_poultry_inspection;
CREATE FUNCTION update_poultry_inspection
	( IN user_id INT
	, IN id INT
	, IN bird_count INT
	, IN egg_count INT
	)
RETURNS VOID
AS
$body$
	BEGIN
		IF permission_check_poultry_inspection(set_user_id(user_id), update_poultry_inspection.id) THEN
			UPDATE poultry_inspections AS h
				SET
					  bird_count = update_poultry_inspection.bird_count
					, egg_count = update_poultry_inspection.egg_count
				WHERE h.id = update_poultry_inspection.id;
		END IF;
	END
$body$
LANGUAGE plpgsql;
