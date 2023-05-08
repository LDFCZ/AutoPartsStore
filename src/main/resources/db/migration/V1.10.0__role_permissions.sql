DROP TABLE IF EXISTS role_permissions CASCADE;

CREATE TABLE role_permissions
(
    id          SERIAL PRIMARY KEY,
    manager     BOOLEAN      NOT NULL,
    supplier    BOOLEAN      NOT NULL,
    client      BOOLEAN      NOT NULL,
    module_name VARCHAR(150) NOT NULL UNIQUE
);