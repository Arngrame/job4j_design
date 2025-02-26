-- Задания:
-- 1) car_bodies с атрибутами id и name;
create table car_bodies (
	id serial primary key,
	name varchar(255)
);

-- 2) car_engines с атрибутами id и name;
create table car_engines (
	id serial primary key,
	name varchar(255)
);

-- 3) car_transmissions с атрибутами id и name;
create table car_transmissions (
	id serial primary key,
	name varchar(255)
);

-- 4) cars с атрибутами id, name, body_id, engine_id и transmission_id.
-- Последние 3 атрибута должны быть внешними ключами к соответствующим таблицам.
create table cars (
	id serial primary key,
	name varchar(255),
	body_id integer references car_bodies(id),
	engine_id integer references car_engines(id),
	transmission_id integer references car_transmissions(id)
);

-- Заполнить таблицы данными.
-- Типы кузова
insert into car_bodies(name) values ('Седан');
insert into car_bodies(name) values ('Хэтчбек');
insert into car_bodies(name) values ('Лифтбек');
insert into car_bodies(name) values ('Универсал');
insert into car_bodies(name) values ('Кроссовер');

-- Типы двигателей
insert into car_engines(name) values ('Бензиновый');
insert into car_engines(name) values ('Дизельный');
insert into car_engines(name) values ('Газовый');
insert into car_engines(name) values ('Электрический');
insert into car_engines(name) values ('Водяной');

-- Типы трансмиссии
insert into car_transmissions(name) values ('Механическая');
insert into car_transmissions(name) values ('Гидромеханическая');
insert into car_transmissions(name) values ('Гидравлическая');
insert into car_transmissions(name) values ('Электромеханическая');
insert into car_transmissions(name) values ('Автоматическая');

-- Машины
insert into cars (name, body_id, engine_id, transmission_id) values ('Лада Веста Седан', 1, 1, 1);
insert into cars (name, body_id, engine_id, transmission_id) values ('Лада Гранта Седан', 1, 1, 1);
insert into cars (name, body_id, engine_id, transmission_id) values ('Лада Гранта Универсал', 4, 2, 1);
insert into cars (name, body_id, engine_id, transmission_id) values ('Лада Веста Кросс', 1, 4, 5);
insert into cars (name, body_id, engine_id, transmission_id) values ('Рено Лагуна Лифтбек', 3, 2, 5);
insert into cars (name, body_id, engine_id, transmission_id) values ('Рено Дастер Кроссовер', 5, 4, 3);
insert into cars (name, body_id, engine_id, transmission_id) values ('Порш Панамера', 4, 3, 2);
insert into cars (name, body_id) values ('Битый порш', 4);
insert into cars (name, body_id, transmission_id) values ('Крашеный порш', 4, 2);
insert into cars (name, transmission_id) values ('Тойота', 2);

select * from cars;
-- 2. Создать SQL запросы:
-- 1. Вывести список всех машин и все привязанные к ним детали. Нужно учесть, что каких-то деталей машина может и не содержать.
-- В таком случае значение может быть null при выводе (например, название двигателя null);
-- Пример "шапки" при выводе:
-- id, car_name, body_name, engine_name, transmission_name
select c.id, c.name, cb.name, ct.name, ce.name
  from cars c left join car_engines ce on c.engine_id = ce.id
  	   left join car_bodies cb on c.body_id = cb.id
	   left join car_transmissions ct on c.transmission_id = ct.id;

-- 2. Вывести кузова, которые не используются НИ в одной машине.
-- "Не используются" значит, что среди записей таблицы cars отсутствует внешние ключи, ссылающие на таблицу car_bodies.
-- Например, Вы добавили в car_bodies "седан", "хэтчбек" и "пикап", а при добавлении в таблицу cars указали только
-- внешние ключи на записи "седан" и "хэтчбек". Запрос, касающийся этого пункта, должен вывести "пикап", т.к. среди
-- машин нет тех, что обладают таким кузовом;
select cb.*
  from car_bodies cb
 where cb.id not in (select c.body_id from cars c where c.body_id is not null);

select cb.*
  from car_bodies cb left join cars c on cb.id = c.body_id
 where c.id is null;

-- 3. Вывести двигатели, которые не используются НИ в одной машине, аналогично п.2;
select ce.*
  from car_engines ce
 where ce.id not in (select c.engine_id from cars c where c.engine_id is not null);

select ce.*
  from car_engines ce left join cars c on ce.id = c.engine_id
 where c.id is null;

-- 4. Вывести коробки передач, которые не используются НИ в одной машине, аналогично п.2.
select ct.*
  from car_transmissions ct
 where ct.id not in (select c.transmission_id from cars c where c.transmission_id is not null);

select ct.*
  from car_transmissions ct left join cars c on ct.id = c.transmission_id
 where c.id is null;