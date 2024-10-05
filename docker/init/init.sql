CREATE USER 'currency'@'%' IDENTIFIED BY 'currency_password';
GRANT ALL PRIVILEGES ON currency_converter_service.* TO 'currency'@'%';
FLUSH PRIVILEGES;
