CREATE TABLE users
(
    name VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL UNIQUE
);

INSERT INTO users (name, password)
VALUES ('Andrey', '12345');
