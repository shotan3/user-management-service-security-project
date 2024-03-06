ALTER TABLE "user"."user"."user"
    ADD COLUMN role_name VARCHAR(8);

ALTER TABLE "user"."user"."user"
    ADD CONSTRAINT fk_user_role_id
        FOREIGN KEY (role_name)
            REFERENCES "role" (role_name);