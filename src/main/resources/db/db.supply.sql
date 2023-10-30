--liquibase formatted sql

--changeset zzhang14@scu.edu:init DB
CREATE TABLE IF NOT EXISTS products (
    id                          BIGINT                      NOT NULL    PRIMARY KEY,
    product_name                VARCHAR(100)                NOT NULL,
    description                 VARCHAR(100)                NOT NULL,
    price                       INTEGER                     NOT NULL,
    stage                       VARCHAR(100)                NOT NULL
);

insert into products (id, product_name, description, price, stage) values (1, 'iPhone', 'wifi and expensive', 1000, 'ON_MARKET');
insert into products (id, product_name, description, price, stage) values (2, 'AirPods', 'voice', 200, 'OFF_MARKET');
