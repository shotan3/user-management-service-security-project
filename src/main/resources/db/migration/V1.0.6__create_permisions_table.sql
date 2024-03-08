CREATE TABLE "user".permission
(
    permission_name VARCHAR(16) NOT NULL UNIQUE,
    PRIMARY KEY (permission_name)
);

INSERT INTO "user".permission(permission_name)
VALUES ('CREATE_USER_SELF');

INSERT INTO "user".permission(permission_name)
VALUES ('VIEW_USER_SELF');

INSERT INTO "user".permission(permission_name)
VALUES ('UPDATE_USER_SELF');

INSERT INTO "user".permission(permission_name)
VALUES ('DELETE_USER_SELF');

INSERT INTO "user".permission(permission_name)
VALUES ('CREATE_USER_ANY');

INSERT INTO "user".permission(permission_name)
VALUES ('VIEW_USER_ANY');

INSERT INTO "user".permission(permission_name)
VALUES ('UPDATE_USER_ANY');

INSERT INTO "user".permission(permission_name)
VALUES ('DELETE_USER_ANY');
