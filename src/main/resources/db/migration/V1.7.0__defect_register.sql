DROP TABLE IF EXISTS defect_register CASCADE;

CREATE TABLE defect_register
(
    id         SERIAL PRIMARY KEY,
    issue_date TIMESTAMP NOT NULL,
    order_id   INTEGER   NOT NULL REFERENCES client_orders (id) ON DELETE CASCADE,
    product_id INTEGER   NOT NULL REFERENCES products (id) ON DELETE CASCADE,
    cause      VARCHAR(150)
);