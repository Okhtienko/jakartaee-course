CREATE TABLE users
(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    password VARCHAR NOT NULL
);

INSERT INTO users (name, password) VALUES ('Andrey', '12345');
INSERT INTO users (name, password) VALUES ('Mike', '12345');
INSERT INTO users (name, password) VALUES ('Mary', '12345');
