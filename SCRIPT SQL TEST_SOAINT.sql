CREATE DATABASE IF NOT EXISTS test_soaint;
USE test_soaint;


INSERT INTO rol(name, log_file, log_console, log_message, log_warning, log_error, log_database)
VALUES('administrator', true, true, true, true, true, true);

INSERT INTO rol(name, log_file, log_console, log_message, log_warning, log_error, log_database)
VALUES('employee', false, true, true, false, false, true);

INSERT INTO rol(name, log_file, log_console, log_message, log_warning, log_error, log_database)
VALUES('customer', false, true, false, false, false, false);

SELECT * FROM rol;

-- Inserta los tres tipos de log en la base datos(message, warning, error)
INSERT INTO user(username, password, rol_id)
VALUES('admin', '123456', 1);
-- Inserta un tipo de log en la base datos(message)
INSERT INTO user(username, password, rol_id)
VALUES('jperez', 'jperez', 2);
-- No inserta nada solo muestra en consola
INSERT INTO user(username, password, rol_id)
VALUES('client', 'client', 3);

SELECT * FROM user;
