CREATE TABLE "user".login_history
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY,
    login_time        TIMESTAMP WITH TIME ZONE NOT NULL,
    login_status_code INT                      NOT NULL,
    user_uuid         UUID                     NOT NULL,
    ip_address        VARCHAR(64)              NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_logged_in_user_uuid
        FOREIGN KEY (user_uuid)
            REFERENCES "user"."user" (user_uuid)
);