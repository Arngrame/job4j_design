create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna (name, avg_age) values ('Гренландский кит', 75000);
insert into fauna (name, avg_age) values ('Слон', 32000);
insert into fauna (name, avg_age) values ('Галапагосская черепаха', 65000);
insert into fauna (name, avg_age, discovery_date) values ('Гренландская полярная акула', 165000, '1500-01-15');
insert into fauna (name, avg_age, discovery_date) values ('Атлантический большеголов', 55000, '2000-03-16');
insert into fauna (name, avg_age, discovery_date) values ('Суринамский амазон', 21000, '1980-02-26');
insert into fauna (name, avg_age, discovery_date) values ('Гриф-индейка', 44000, '2000-06-28');
insert into fauna (name, avg_age, discovery_date) values ('Японская гигантская саламандра', 21000, '1880-10-07');
insert into fauna (name, avg_age, discovery_date) values ('Бурый медведь', 14600, '1705-03-22');
insert into fauna (name, avg_age, discovery_date) values ('Каролинская коробчатая черепаха', 51500, '1984-07-31');
insert into fauna (name, avg_age, discovery_date) values ('Golden fish', 1080, '1965-07-23');
insert into fauna (name, avg_age, discovery_date) values ('Red fish atlantic коробчатая черепаха', 4200, '1888-05-20');
insert into fauna (name, avg_age, discovery_date) values ('Fish er', 5321, '1689-01-05');
insert into fauna (name, avg_age, discovery_date) values ('Бубка Гоп', 5321, '1949-12-31');


-- Извлечение данных, у которых имя name содержит подстроку fish (без учёта регистра)
select * from fauna where name like '%fish%';

-- Извлечение данных, у которых сред. продолжительность жизни находится в диапазоне 10 000 и 21 000
select * from fauna where avg_age between 10000 and 21000;
select * from fauna where avg_age >= 10000 and avg_age <= 21000;

-- Извлечение данных, у которых дата открытия не известна (null)
select * from fauna where discovery_date is null;

-- Извлечение данных видов, у которых дата открытия раньше 1950 года
select * from fauna where discovery_date <= '1949-12-31';