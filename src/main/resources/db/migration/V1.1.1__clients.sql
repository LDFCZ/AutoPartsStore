DROP TABLE IF EXISTS clients CASCADE;

CREATE TABLE clients
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

INSERT INTO clients (name, email)
VALUES ('John Doe', 'johndoe@example.com'),
       ('Jane Smith', 'janesmith@example.com'),
       ('Bob Johnson', 'bobjohnson@example.com'),
       ('Samantha Lee', 'samanthalee@example.com'),
       ('Peter Brown', 'peterbrown@example.com'),
       ('Alice Johnson', 'alicejohnson@example.com'),
       ('Tom Smith', 'tomsmith@example.com'),
       ('Lisa Kim', 'lisakim@example.com'),
       ('David Lee', 'davidlee@example.com'),
       ('Sarah Brown', 'sarahbrown@example.com');