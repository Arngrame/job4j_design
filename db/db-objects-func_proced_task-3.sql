-- Тестовая таблица
create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

-- Процедура
create or replace procedure p_delete_data_by_count(_count integer)
language 'plpgsql' as
$$
    BEGIN
		delete from products p
		 where p.count >= _count;
    END
$$;

-- Тестовые данные
insert into products (name, producer, count, price)
             values ('Тирамису', 'Вкус Вилл', 10, 100),
					('Графские развалины', 'Вкус Вилл', 5, 80),
					('Медовик', 'У Палыча', 7, 50),
					('Наполеон', 'У Палыча', 10, 150);

-- Вызов процедуры
call p_delete_data_by_count(8);

-- Проверка
select * from products;

-- Чистим перед работой с функцией
truncate products;

-- Функция
create or replace function f_delete_products_by_price(_price integer) returns void
language 'plpgsql' as
$$
    begin
		delete from products p
		 where p.price >= _price;
    end;
$$;

-- Тестовые данные
insert into products (name, producer, count, price)
             values ('Тирамису', 'Вкус Вилл', 10, 100),
					('Графские развалины', 'Вкус Вилл', 5, 80),
					('Медовик', 'У Палыча', 7, 50),
					('Наполеон', 'У Палыча', 10, 150);

-- Вызов функции
select f_delete_products_by_price(60);

-- Проверка
select * from products;
