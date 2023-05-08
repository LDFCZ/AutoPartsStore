DROP TABLE IF EXISTS suppliers CASCADE;
DROP TABLE IF EXISTS big_suppliers CASCADE;

CREATE TABLE suppliers
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(50)  NOT NULL,
    address VARCHAR(150) NOT NULL
);

CREATE TABLE big_suppliers
(
    license_id  VARCHAR(150),
    customs_tax INTEGER CHECK (customs_tax >= 0)
) INHERITS (suppliers);
