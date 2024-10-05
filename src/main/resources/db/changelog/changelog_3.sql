create TABLE IF NOT EXISTS conversion_rate(
    id                          BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    currency_code               VARCHAR(8)  NOT NULL,
    rate                        DECIMAL     NOT NULL,
    currency_exchange_rate_id   BIGINT      NOT NULL,
    created_at                  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at                 DATETIME    NULL ON update CURRENT_TIMESTAMP
);

create TABLE IF NOT EXISTS currency_exchange_rate(
    id                      BIGINT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    result                  VARCHAR(255)    NULL,
    documentation           VARCHAR(255)    NULL,
    terms_of_use            VARCHAR(255)    NULL,
    time_last_update_utc    DATETIME        NOT NULL,
    time_next_update_utc    DATETIME        NOT NULL,
    base_code               VARCHAR(8)      NOT NULL,
    created_at              DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at             DATETIME        NULL ON update CURRENT_TIMESTAMP
);

create UNIQUE INDEX uidx_currency_exchange_rate_base_code ON currency_exchange_rate(base_code);

alter table conversion_rate add FOREIGN KEY (currency_exchange_rate_id) REFERENCES currency_exchange_rate(id);