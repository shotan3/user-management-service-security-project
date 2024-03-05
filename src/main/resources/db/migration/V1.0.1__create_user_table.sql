CREATE TABLE "user"."user"
(
    user_uuid            UUID         NOT NULL,
    first_name           VARCHAR(64)  NOT NULL,
    middle_name          VARCHAR(64),
    last_name            VARCHAR(64)  NOT NULL,
    date_of_birth        DATE         NOT NULL,
    gender               VARCHAR(16)  NOT NULL,
    primary_email        VARCHAR(128) NOT NULL UNIQUE,
    contact_phone        VARCHAR(64) UNIQUE,
    backup_email         VARCHAR(128) UNIQUE,
    country_of_residence varchar(64),
    city_of_residence    VARCHAR(128),
    PRIMARY KEY (user_uuid)
);