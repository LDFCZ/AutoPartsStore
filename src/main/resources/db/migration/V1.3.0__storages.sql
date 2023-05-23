DROP TABLE IF EXISTS storages CASCADE;
DROP TABLE IF EXISTS buffer CASCADE;

CREATE TABLE storages
(
    id       SERIAL PRIMARY KEY,
    address  VARCHAR(150) NOT NULL,
    max_size INTEGER      NOT NULL CHECK ( max_size > 0 )
);


CREATE TABLE buffer
(
    id         SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL REFERENCES products (id) ON DELETE CASCADE,
    count      INTEGER NOT NULL CHECK ( count > 0 )
);