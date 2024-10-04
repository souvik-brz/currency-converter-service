CREATE TABLE IF NOT EXISTS currency_converter_configs(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    base_code VARCHAR(8) NOT NULL,
    enabled BOOLEAN NOT NULL,
    frequency_in_minutes INT NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS currency_exchange_rates(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    result VARCHAR(8) NOT NULL,
    provider VARCHAR(8) NOT NULL,
    documentation VARCHAR(8) NOT NULL,
    terms_of_use VARCHAR(8) NOT NULL,
    time_last_update_utc DATETIME NOT NULL,
    time_next_update_utc DATETIME NULL,
    base_code VARCHAR(8) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP
);
