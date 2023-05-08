DROP TABLE IF EXISTS offers CASCADE;

CREATE TABLE offers
(
    id          SERIAL PRIMARY KEY,
    product_id  INTEGER NOT NULL REFERENCES products (id) ON DELETE CASCADE,
    supplier_id INTEGER NOT NULL REFERENCES suppliers (id) ON DELETE CASCADE,
    count       INTEGER NOT NULL CHECK ( count > 0 )
);