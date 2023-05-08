DROP TABLE IF EXISTS product_types CASCADE;
DROP TABLE IF EXISTS products CASCADE;


CREATE TABLE product_types
(
    id                SERIAL PRIMARY KEY,
    product_type_name VARCHAR(150) NOT NULL
);


CREATE TABLE products
(
    id              SERIAL PRIMARY KEY,
    product_name    VARCHAR(150) NOT NULL,
    product_type_id INTEGER      NOT NULL REFERENCES product_types (id) ON DELETE CASCADE,
    product_size    INTEGER      NOT NULL CHECK ( product_size > 0 AND product_size < 1000),
    supplier_id     INTEGER      NOT NULL REFERENCES suppliers (id) ON DELETE CASCADE,
    price           FLOAT        NOT NULL CHECK (price >= 0),
    discount        INTEGER      NOT NULL CHECK (discount >= 0 AND discount <= 100),
    document_id     VARCHAR(150),
    is_guarantee    BOOLEAN      NOT NULL,
    customs_price   FLOAT        NOT NULL CHECK (customs_price >= 0),
    final_price     FLOAT        NOT NULL CHECK (price >= 0),
    final_discount  INTEGER      NOT NULL CHECK (discount >= 0 AND discount <= 100)
);
