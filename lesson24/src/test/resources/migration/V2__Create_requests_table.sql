CREATE TABLE requests
(
    id           BIGSERIAL NOT NULL UNIQUE,
    sender_id    BIGINT  NOT NULL,
    recipient_id BIGINT  NOT NULL,
    date   TIMESTAMP NOT NULL DEFAULT now(),

    PRIMARY KEY (id)
);

INSERT INTO requests (sender_id, recipient_id) VALUES (1, 2);
INSERT INTO requests (sender_id, recipient_id) VALUES (1, 3);
INSERT INTO requests (sender_id, recipient_id) VALUES (2, 3);
