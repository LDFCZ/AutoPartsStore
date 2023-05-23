DROP TABLE IF EXISTS cash_register CASCADE;

CREATE TABLE cash_register
(
    id               SERIAL PRIMARY KEY,
    transaction_date TIMESTAMP NOT NULL,
    order_id         INTEGER   NOT NULL REFERENCES client_orders (id) ON DELETE CASCADE UNIQUE ,
    sum              FLOAT   NOT NULL CHECK ( sum > 0 )
);