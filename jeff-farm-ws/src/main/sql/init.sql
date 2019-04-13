-- $ sudo mysql
CREATE DATABASE jeff_farm_db;
CREATE USER farmer with PASSWORD 'flint5+0N3';
--GRANT ALL PRIVILEGES ON JEFF_FARM_DB TO farmer;
-- exit


-- $ sudo adduser farmer --quiet
-- $ su farmer
-- $ # type password
-- $ psql -d jeff_farm_db


-- !!! Connector-J !!! --  Should only be needed to run code in ide.  Default Tomcat libs should suffice for jdbc interaction.
-- wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java_8.0.15-1ubuntu18.04_all.deb
-- sudo dpkg -i mysql-connector-java_8.0.15-1ubuntu18.04_all.deb || sudo -k
-- ./asadmin add-library /usr/share/java/mysql-connector-java-8.0.15.jar
-- cp ~/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar ~/apps/apache-tomcat-9.0.16/lib/

-- SETUP:
INSERT INTO roles (role_name) VALUES ('user');
