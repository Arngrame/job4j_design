-- задание:
-- 1. Создать таблицу из описания
create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer,
	tax 	 integer
);

-- 2. Создать триггер, срабатывающий после вставки данных: для любого товара рассчитывать налог. Триггер на уровне запроса
-- 2.1 Функция расчёта НДС
create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set tax = price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- 2.2 Триггер
create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each row
    execute procedure tax();

-- 2.3 Тест
insert into products (name, producer, count, price) values ('Тирамису', 'Вкус Вилл', 10, 100);
select * from products;


-- 3. Создать триггер, срабатывающий до вставки данных: цель та же, но триггер на уровне строки
-- 3.1 Функция
create
or replace function tax2()
    returns trigger as
$$
    BEGIN
        update products
        set tax = price * 0.15
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- 3.2 Триггер
create trigger tax_trigger_row
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax2();

-- 3.3 Тест
insert into products (name, producer, count, price) values ('Медовик', 'Вкус Вилл', 2, 250);
select * from products;

-- 4. Создать дополнительную таблицу
create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

-- 5. Создать триггер уровня строки, срабатывающий после вставки данных в таблицу PRODUCTS: заносить имя, цену и текущую дату
-- в таблицу history_of_price

-- 5.1 Функция
create
or replace function fill_product_history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
        select name, price, now()
		  from inserted;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- 5.2 Триггер
create trigger product_history
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure fill_product_history();

-- 5.3 Тестируем
insert into products (name, producer, count, price) values ('Колбаса молочная', 'У Палыча', 100, 300);
select * from history_of_price;