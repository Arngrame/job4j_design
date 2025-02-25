-- 1. Структура
create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

-- 2. Заполнить таблицы данными
-- Девайсы
insert into devices (name, price) values ('Мышь', 500.0);
insert into devices (name, price) values ('Клавиатура', 1000.0);
insert into devices (name, price) values ('Видеокарта', 35000.0);
insert into devices (name, price) values ('Монитор', 15000.0);
insert into devices (name, price) values ('Материнская плата', 20000.0);
insert into devices (name, price) values ('Наушники', 7500.0);
-- Люди
insert into people(name) values ('Иван Иванов');
insert into people(name) values ('Пётр Петров');
insert into people(name) values ('Сергей Сергеев');
insert into people(name) values ('Павел Павлов');
-- Промежуточная

-- Девайсы Ивана Иванова, avg = 35 000 + 15 000 + 20 000 + 7 500 = 77 500 / 4 = 19 375
insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (4, 1);
insert into devices_people(device_id, people_id) values (5, 1);
insert into devices_people(device_id, people_id) values (6, 1);

-- Девайсы Пётр Петров, avg = 500 + 1000 = 1500 / 2 = 750
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (2, 2);

-- Девайсы Сергей Сергеев, avg = 500 + 1000 + 15 000 + 7 500 = 24 000 / 4 = 6 000
insert into devices_people(device_id, people_id) values (1, 3);
insert into devices_people(device_id, people_id) values (2, 3);
insert into devices_people(device_id, people_id) values (4, 3);
insert into devices_people(device_id, people_id) values (6, 3);

-- Девайсы Павла Павлова, avg = 15 000 + 15 000 + 20 000 + 7 500 = 14 375
insert into devices_people(device_id, people_id) values (4, 4);
insert into devices_people(device_id, people_id) values (4, 4);
insert into devices_people(device_id, people_id) values (5, 4);
insert into devices_people(device_id, people_id) values (6, 4);
--

select * from devices;
select * from people;
select * from devices_people;

-- 3. Используя агрегатные функции вывести среднюю цену устройств.
select avg(d.price) as "Средняя цена устройств"
  from devices d;

-- 4. Используя группировку вывести для каждого человека среднюю цену его устройств.
select 	p.name, avg(d.price)
  from 	people p,
  		devices_people dp,
		devices d
 where	p.id = dp.people_id
   and 	dp.device_id = d.id
group by p.name

-- 5. Дополнить запрос п.4. условием, что средняя стоимость устройств должна быть больше 5000.
select 	p.name, avg(d.price)
  from 	people p,
  		devices_people dp,
		devices d
 where	p.id = dp.people_id
   and 	dp.device_id = d.id
group by p.name
having	avg(d.price) > 5000;
