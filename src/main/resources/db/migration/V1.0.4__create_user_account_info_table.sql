CREATE TABLE "user".user_account_info
(
    id                    BIGINT GENERATED ALWAYS AS IDENTITY,
    created_at            TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at            TIMESTAMP WITH TIME ZONE NOT NULL,
    marked_for_deletion   BOOLEAN                  NOT NULL,
    deletion_scheduled_at TIMESTAMP WITH TIME ZONE,
    user_uuid             UUID                     NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_account_info_user
        FOREIGN KEY (user_uuid)
            REFERENCES "user"."user" (user_uuid)
);