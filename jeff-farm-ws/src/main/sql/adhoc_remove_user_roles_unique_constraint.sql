DO $$
BEGIN
	IF EXISTS
	(
		SELECT ccu.*
		FROM information_schema.constraint_column_usage AS ccu
		WHERE ccu.table_name ='user_roles'
			AND ccu.constraint_name = 'user_roles_user_name_key'
	)
	THEN
		ALTER TABLE user_roles DROP CONSTRAINT user_roles_user_name_key;
	END IF;
END
$$;
