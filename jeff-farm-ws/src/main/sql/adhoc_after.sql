DO $$
BEGIN

	ALTER TABLE poultry_inspections_audit ALTER COLUMN group_id   DROP NOT NULL;
	ALTER TABLE poultry_inspections_audit ALTER COLUMN target_id  DROP NOT NULL;
	ALTER TABLE poultry_inspections_audit ALTER COLUMN bird_count DROP NOT NULL;
	ALTER TABLE poultry_inspections_audit ALTER COLUMN egg_count  DROP NOT NULL;

	ALTER TABLE hive_inspection_groups    ALTER COLUMN notes DROP NOT NULL;
	ALTER TABLE poultry_inspection_groups ALTER COLUMN notes DROP NOT NULL;

END
$$;
