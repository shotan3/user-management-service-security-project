CREATE TABLE "user".role_to_permission
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY,
    role_name       VARCHAR(16) NOT NULL,
    permission_name VARCHAR(16) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_role_name_mapping
        FOREIGN KEY (role_name)
            REFERENCES "user"."role" (role_name),
    CONSTRAINT fk_permission_name_mapping
        FOREIGN KEY (permission_name)
            REFERENCES "user"."permission" (permission_name)
);

INSERT INTO "user".role_to_permission(role_name, permission_name)
VALUES ('ADMIN', 'CREATE_USER_ANY');

INSERT INTO "user".role_to_permission(role_name, permission_name)
VALUES ('ADMIN', 'VIEW_USER_ANY');

INSERT INTO "user".role_to_permission(role_name, permission_name)
VALUES ('ADMIN', 'UPDATE_USER_ANY');

INSERT INTO "user".role_to_permission(role_name, permission_name)
VALUES ('ADMIN', 'DELETE_USER_ANY');

INSERT INTO "user".role_to_permission(role_name, permission_name)
VALUES ('ADMIN', 'CREATE_USER_SELF');

INSERT INTO "user".role_to_permission(role_name, permission_name)
VALUES ('ADMIN', 'VIEW_USER_SELF');

INSERT INTO "user".role_to_permission(role_name, permission_name)
VALUES ('ADMIN', 'UPDATE_USER_SELF');

INSERT INTO "user".role_to_permission(role_name, permission_name)
VALUES ('ADMIN', 'DELETE_USER_SELF');

INSERT INTO "user".role_to_permission(role_name, permission_name)
VALUES ('USER', 'CREATE_USER_SELF');

INSERT INTO "user".role_to_permission(role_name, permission_name)
VALUES ('USER', 'VIEW_USER_SELF');

INSERT INTO "user".role_to_permission(role_name, permission_name)
VALUES ('USER', 'UPDATE_USER_SELF');

INSERT INTO "user".role_to_permission(role_name, permission_name)
VALUES ('USER', 'DELETE_USER_SELF');
