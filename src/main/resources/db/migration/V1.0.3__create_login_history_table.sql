CREATE TABLE "user".login_history
(
    id             BIGINT GENERATED ALWAYS AS IDENTITY,
    login_time     TIMESTAMP WITH TIME ZONE NOT NULL,
    session_id     VARCHAR(64)              NOT NULL,
    user_uuid      UUID                     NOT NULL,
    was_successful BOOLEAN                  NOT NULL,
    ip_address     VARCHAR(64)              NOT NULL,
    failure_reason VARCHAR(64),
    PRIMARY KEY (id),
    CONSTRAINT fk_logged_in_user_uuid
        FOREIGN KEY (user_uuid)
            REFERENCES "user"."user" (user_uuid)
);