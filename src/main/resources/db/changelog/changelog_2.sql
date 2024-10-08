create TABLE IF NOT EXISTS currency_converter_config(
    id                      BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    base_code               VARCHAR(8)  NOT NULL,
    enabled                 BOOLEAN     NOT NULL,
    start_date              DATETIME    NOT NULL,
    end_date                DATETIME    NULL,
    created_at              DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at             DATETIME    NULL ON update CURRENT_TIMESTAMP
);

create UNIQUE INDEX uidx_currency_converter_config_base_code ON currency_converter_config(base_code);