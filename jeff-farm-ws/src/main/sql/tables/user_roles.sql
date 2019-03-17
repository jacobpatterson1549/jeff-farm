-- DROP TABLE user_roles;

CREATE TABLE IF NOT EXISTS user_roles
(
	user_name VARCHAR(20) UNIQUE NOT NULL,
	role_name VARCHAR(20) NOT NULL,

	FOREIGN KEY (user_name)
		REFERENCES users (user_name),
	FOREIGN KEY (role_name)
		REFERENCES roles (role_name),
	PRIMARY KEY (user_name, role_name)
);