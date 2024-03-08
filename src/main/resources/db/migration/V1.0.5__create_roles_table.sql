CREATE TABLE "user".role
(
    role_name VARCHAR(16) NOT NULL UNIQUE,
    PRIMARY KEY (role_name)
);

INSERT INTO "user".role(role_name)
VALUES ('ADMIN');

INSERT INTO "user".role(role_name)
VALUES ('USER');