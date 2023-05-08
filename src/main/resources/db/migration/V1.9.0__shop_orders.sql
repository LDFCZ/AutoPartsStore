DROP TABLE IF EXISTS shop_orders CASCADE;

CREATE TABLE shop_orders
(
    id          SERIAL PRIMARY KEY,
    order_date  TIMESTAMP NOT NULL,
    product_id  INTEGER   NOT NULL REFERENCES products (id) ON DELETE CASCADE,
    total_count INTEGER   NOT NULL CHECK ( total_count > 0 ),
    done_count  INTEGER   NOT NULL CHECK ( done_count >= 0 AND done_count <= total_count),
    contract_id VARCHAR(150)
);

DROP TABLE IF EXISTS completed_shop_orders CASCADE;

CREATE TABLE completed_shop_orders
(
    id            SERIAL PRIMARY KEY,
    complete_date TIMESTAMP NOT NULL,
    shop_order_id INTEGER   NOT NULL REFERENCES shop_orders (id) ON DELETE CASCADE,
    offer_id      INTEGER   NOT NULL REFERENCES offers (id) ON DELETE CASCADE
);