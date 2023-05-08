DROP TABLE IF EXISTS client_orders CASCADE;
DROP TABLE IF EXISTS client_orders_products CASCADE;

CREATE TABLE client_orders
(
    id             SERIAL PRIMARY KEY,
    order_date     TIMESTAMP NOT NULL,
    order_end_date TIMESTAMP NOT NULL,
    client_id      INTEGER   NOT NULL REFERENCES clients (id) ON DELETE CASCADE,
    is_completed   BOOLEAN   NOT NULL
);

CREATE TABLE client_orders_products
(
    order_id   INTEGER REFERENCES client_orders (id) ON DELETE CASCADE,
    product_id INTEGER REFERENCES products (id) ON DELETE CASCADE,
    count      INTEGER NOT NULL CHECK ( count > 0 ),
    PRIMARY KEY (order_id, product_id)
);