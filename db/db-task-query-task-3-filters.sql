-- структура
create table type
(
    id   serial primary key,
    name varchar(255)
);

create table product
(
    id    serial primary key,
    name  varchar(255),
    type_id integer references type(id),
	expired_date date,
	price float
);

-- наполнение
insert into type (name) values ('Сыр');
insert into type (name) values ('Колбаса');
insert into type (name) values ('Хлеб');
insert into type (name) values ('Молочный продукт');

select * from type;

insert into product (name, type_id, expired_date, price) values ('Докторская', 2, '2025-01-31', 350.0);
insert into product (name, type_id, expired_date, price) values ('Салями', 2, '2025-01-30', 400.0);
insert into product (name, type_id, expired_date, price) values ('Осетровая', 2, '2025-01-29', 375.0);
insert into product (name, type_id, expired_date, price) values ('Куриная', 2, '2025-01-28', 500.0);

insert into product (name, type_id, expired_date, price) values ('Адыгейский', 1, '2025-02-20', 600.0);
insert into product (name, type_id, expired_date, price) values ('Плавленный', 1, '2025-02-21', 150.0);
insert into product (name, type_id, expired_date, price) values ('Моцарелла', 1, '2025-02-22', 450.0);
insert into product (name, type_id, expired_date, price) values ('Гауда', 1, '2025-02-23', 300.0);

insert into product (name, type_id, expired_date, price) values ('Ржаной', 3, '2025-03-10', 50.0);
insert into product (name, type_id, expired_date, price) values ('Белый', 3, '2025-03-11', 55.0);
insert into product (name, type_id, expired_date, price) values ('Цельнозерновой', 3, '2025-03-12', 75.0);
insert into product (name, type_id, expired_date, price) values ('Бородинский', 3, '2025-03-13', 60.0);

insert into product (name, type_id, expired_date, price) values ('Twix (мороженое рожок)', 4, '2026-04-10', 600.0);
insert into product (name, type_id, expired_date, price) values ('Белая ворона', 4, '2026-05-11', 225.0);
insert into product (name, type_id, expired_date, price) values ('Сникерс (мороженое рожок)', 4, '2026-07-12', 452.0);
insert into product (name, type_id, expired_date, price) values ('Коровка из Кореновки (пломбир)', 4, '2026-09-13', 115.0);

select * from product;

-- Запросы
-- 1. Написать запрос получение всех продуктов с типом "СЫР"
select 	p.*
  from 	product p,
  		type t
 where 	t.id = p.type_id
   and 	t.name = 'Сыр';

select p.*
  from product p join type t on t.id = p.type_id
 where t.name = 'Сыр';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select p.*
  from product p
 where p.name like '%мороженое%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select p.*
  from product p
 where p.expired_date < now();

-- 4. Написать запрос, который выводит самый дорогой продукт. Запрос должен быть универсальный и находить все продукты с максимальной ценой. Например, если в таблице есть продукт типа СЫР с ценой 200 и продукт типа МОЛОКО с ценой 200, и эта цена максимальная во всей таблице, то запрос должен вывести оба этих продукта.
select p1.name, p1.price
  from product p1
 where p1.price = (select max(p2.price) from product p2);

-- 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select t.name, count(p.*)
  from product p join type t on p.type_id = t.id
 group by t.name

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.*
  from product p join type t on p.type_id = t.id
 where t.name in ('Сыр', 'Молочный продукт');

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. Под количеством подразумевается количество продуктов определенного типа. Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла", которые ему принадлежат, то количество продуктов типа "СЫР" будет 2.
select t.name, count(p.*)
  from product p join type t on p.type_id = t.id
 group by t.name
 having count(*) < 10;

-- 8. Вывести все продукты и их тип.
select p.*, t.name
  from product p join type t on p.type_id = t.id;
