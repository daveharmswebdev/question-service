DROP DATABASE IF EXISTS eitherordb;
DROP USER IF EXISTS `eitheroradmin`@`%`;
DROP USER IF EXISTS `eitheroruser`@`%`;
CREATE DATABASE IF NOT EXISTS eitherordb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `eitheroradmin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `eitherordb`.* TO `eitheroradmin`@`%`;
CREATE USER IF NOT EXISTS `eitheroruser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `eitherordb`.* TO `eitheroruser`@`%`;
FLUSH PRIVILEGES;