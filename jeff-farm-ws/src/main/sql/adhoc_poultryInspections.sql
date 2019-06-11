DO
$$
BEGIN
	IF EXISTS
	(
		SELECT table_name, column_name
		FROM information_schema.columns
		WHERE table_name = 'poultry_inspections'
			AND column_name = 'poultry_id'
	)
	THEN
		ALTER TABLE poultry_inspections_audit RENAME poultry_id TO target_id;
	END IF;
	IF EXISTS
	(
		SELECT table_name, column_name
		FROM information_schema.columns
		WHERE table_name = 'poultry_inspections'
			AND column_name = 'poultry_inspection_group_id'
	)
	THEN
		ALTER TABLE poultry_inspections RENAME poultry_inspection_group_id TO group_id;
	END IF;

	IF EXISTS
	(
		SELECT table_name, column_name
		FROM information_schema.columns
		WHERE table_name = 'poultry_inspections_audit'
			AND column_name = 'poultry_id'
	)
	THEN
		ALTER TABLE poultry_inspections_audit RENAME poultry_id TO target_id;
	END IF;
	IF EXISTS
	(
		SELECT table_name, column_name
		FROM information_schema.columns
		WHERE table_name = 'poultry_inspections_audit'
			AND column_name = 'poultry_inspection_group_id'
	)
	THEN
		ALTER TABLE poultry_inspections_audit RENAME poultry_inspection_group_id TO group_id;
	END IF;


	IF EXISTS
	(
		SELECT table_name, constraint_type
		FROM information_schema.table_constraints
		WHERE constraint_name = 'poultry_inspections_poultry_inspection_group_id_fkey'
	)
	THEN
		ALTER TABLE poultry_inspections DROP CONSTRAINT poultry_inspections_poultry_inspection_group_id_fkey;
	END IF;
	IF NOT EXISTS
	(
		SELECT table_name, constraint_type
		FROM information_schema.table_constraints
		WHERE constraint_name = 'poultry_inspections_group_id_fkey'
	)
	THEN
		ALTER TABLE poultry_inspections ADD CONSTRAINT poultry_inspections_group_id_fkey FOREIGN KEY (group_id) REFERENCES poultry_inspection_groups(id);
	END IF;
END
$$;

