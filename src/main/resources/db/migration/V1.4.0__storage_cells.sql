DROP TABLE IF EXISTS storage_cells CASCADE;

CREATE TABLE storage_cells
(
    id         SERIAL PRIMARY KEY,
    storage_id INTEGER NOT NULL REFERENCES storages (id) ON DELETE CASCADE,
    size       INTEGER NOT NULL CHECK (size > 0),
    free_space INTEGER NOT NULL CHECK (free_space >= 0 and free_space <= storage_cells.size)
);

DROP TABLE IF EXISTS storage_cells_products CASCADE;

CREATE TABLE storage_cells_products
(
    cell_id      INTEGER REFERENCES storage_cells (id) ON DELETE CASCADE,
    product_id   INTEGER REFERENCES products (id) ON DELETE CASCADE,
    arrival_date TIMESTAMP NOT NULL,
    count        INTEGER   NOT NULL CHECK ( count > 0 ),
    PRIMARY KEY (cell_id, product_id)
);