CREATE TABLE users
(
    id         BIGSERIAL NOT NULL UNIQUE,
    name       VARCHAR   NOT NULL UNIQUE,
    password   VARCHAR   NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT now()
);

INSERT INTO users (name, password) VALUES ('Andrey', '12345');
INSERT INTO users (name, password) VALUES ('Mike', '12345');
INSERT INTO users (name, password) VALUES ('Mary', '12345');
