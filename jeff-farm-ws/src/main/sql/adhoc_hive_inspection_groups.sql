DO $$
BEGIN

IF EXISTS
(
SELECT proname FROM pg_proc WHERE proname = 'read_hive_inspections'
)
THEN

DROP FUNCTION read_hive_inspections;

--SELECT * FROM hive_inspections AS hi, delete_hive_inspection(61, hi.id);
IF EXISTS (SELECT * FROM hive_inspections) THEN
	RAISE EXCEPTION 'hives_inspections EXIST';
END IF;

ALTER TABLE hive_inspections ADD COLUMN group_id INT;
ALTER TABLE hive_inspections RENAME COLUMN hive_id TO target_id;

ALTER TABLE hive_inspections ALTER COLUMN group_id SET NOT NULL;

ALTER TABLE hive_inspections_audit ADD COLUMN group_id INT;
ALTER TABLE hive_inspections_audit RENAME COLUMN hive_id TO target_id;

END IF;

END
$$;
