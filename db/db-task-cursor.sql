-- description's task start
-- create example table
create table products
(
    id    serial primary key,
    name  varchar(50),
    count integer default 0,
    price integer
);

-- insert data
insert into products (name, count, price)
VALUES ('product_1', 1, 5);
insert into products (name, count, price)
VALUES ('product_2', 2, 10);
insert into products (name, count, price)
VALUES ('product_3', 3, 15);
insert into products (name, count, price)
VALUES ('product_4', 4, 20);
insert into products (name, count, price)
VALUES ('product_5', 5, 25);
insert into products (name, count, price)
VALUES ('product_6', 6, 30);
insert into products (name, count, price)
VALUES ('product_7', 7, 35);
insert into products (name, count, price)
VALUES ('product_8', 8, 40);
insert into products (name, count, price)
VALUES ('product_9', 9, 45);
insert into products (name, count, price)
VALUES ('product_10', 10, 50);
insert into products (name, count, price)
VALUES ('product_11', 11, 55);
insert into products (name, count, price)
VALUES ('product_12', 12, 60);
insert into products (name, count, price)
VALUES ('product_13', 13, 65);
insert into products (name, count, price)
VALUES ('product_14', 14, 70);
insert into products (name, count, price)
VALUES ('product_15', 15, 75);
insert into products (name, count, price)
VALUES ('product_16', 16, 80);
insert into products (name, count, price)
VALUES ('product_17', 17, 85);
insert into products (name, count, price)
VALUES ('product_18', 18, 90);
insert into products (name, count, price)
VALUES ('product_19', 19, 95);
insert into products (name, count, price)
VALUES ('product_20', 20, 100);

-- start transaction and declare cursor
BEGIN;
DECLARE
cursor_products cursor for
select * from products;

-- upload 10 rows
FETCH 10 FROM cursor_products;

-- upload next 2 rows
FETCH NEXT FROM cursor_products;
FETCH NEXT FROM cursor_products;

-- move cursor from 12 to 14 row
MOVE FORWARD 2 FROM cursor_products;

-- upload next row (15)
FETCH NEXT FROM cursor_products;

-- closing cursor
CLOSE cursor_products;

-- commit transaction
COMMIT;

-- descrition's task finished

-- real task start
-- begin transaction and declare scrolling cursor
BEGIN;
DECLARE
cursor_scrolling_products SCROLL cursor for
select * from products;

-- upload last record
FETCH LAST FROM cursor_scrolling_products;

-- Move to 16 and get/upload 15th row
MOVE BACKWARD 4 FROM cursor_scrolling_products;
FETCH PRIOR FROM cursor_scrolling_products;

-- Move to 8 and get/upload 7th row
MOVE BACKWARD 7 FROM cursor_scrolling_products;
FETCH PRIOR FROM cursor_scrolling_products;

-- Move to 3 and get/upload 2nd row
MOVE BACKWARD 4 FROM cursor_scrolling_products;
FETCH PRIOR FROM cursor_scrolling_products;

-- Get/Upload 1st row
FETCH PRIOR FROM cursor_scrolling_products;

-- Close cursor
CLOSE cursor_scrolling_products;

-- commit transaction
COMMIT;

-- finish real task