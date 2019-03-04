-- $ sudo mysql
CREATE DATABASE JEFF_FARM_DB;
CREATE USER 'fred'@'localhost' IDENTIFIED BY 'flintstone';
GRANT ALL PRIVILEGES ON JEFF_FARM_DB.* TO 'fred'@'localhost' WITH GRANT OPTION;
-- exit

-- !!! Connector-J !!! --  Should only be needed to run code in ide.  Default Tomcat libs should suffice for jdbc interaction.
-- wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java_8.0.15-1ubuntu18.04_all.deb
-- sudo dpkg -i mysql-connector-java_8.0.15-1ubuntu18.04_all.deb || sudo -k
-- ./asadmin add-library /usr/share/java/mysql-connector-java-8.0.15.jar
