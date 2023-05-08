DROP TABLE IF EXISTS storages CASCADE;

CREATE TABLE storages
(
    id       SERIAL PRIMARY KEY,
    address  VARCHAR(150) NOT NULL,
    max_size INTEGER      NOT NULL CHECK ( max_size > 0 )
);