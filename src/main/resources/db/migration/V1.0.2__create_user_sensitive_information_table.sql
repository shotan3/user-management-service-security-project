CREATE TABLE "user".user_secret
(
    id                        BIGINT GENERATED ALWAYS AS IDENTITY,
    curr_password             VARCHAR(512)             NOT NULL,
    prev_password             VARCHAR(512),
    password_last_update_time TIMESTAMP WITH TIME ZONE NOT NULL,
    user_uuid                 UUID                     NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_uuid
        FOREIGN KEY (user_uuid)
            REFERENCES "user"."user" (user_uuid)
);