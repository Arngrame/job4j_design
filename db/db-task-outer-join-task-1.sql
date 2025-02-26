-- структура
create table departments (
	id serial primary key,
	name varchar(255)
);


create table employees (
	id serial primary key,
	name varchar(255),
	department_id integer references departments(id)
);

-- наполнение
insert into departments(name) values ('R&D');
insert into departments(name) values ('I&M');
insert into departments(name) values ('HR');
insert into departments(name) values ('IT');
insert into departments(name) values ('QA/TA');

-- R&D
insert into employees (name, department_id) values ('Иван Иванов', 1);
insert into employees (name, department_id) values ('Максим Петров', 1);
insert into employees (name, department_id) values ('Александр Павлов', 1);

-- HR
insert into employees (name, department_id) values ('Мария Медлякова', 3);
insert into employees (name, department_id) values ('Александра Шустрая', 3);
insert into employees (name, department_id) values ('Алексей Быстров', 3);

-- IT
insert into employees (name, department_id) values ('Александр Пресняков', 4);
insert into employees (name, department_id) values ('Константин Свежов', 4);
insert into employees (name, department_id) values ('Анастасия Сочная', 4);

-- QA/TA
insert into employees (name, department_id) values ('Илья Багофиксов', 5);
insert into employees (name, department_id) values ('Татьяна Мержреквестова', 5);
insert into employees (name, department_id) values ('Кристина Откатова', 5);

-- Задания
-- 1. Выполнить запросы с left, right, full, cross соединениями
-- 1.1 left
select * from departments d left join employees e on d.id = e.department_id;
-- 1.2 right
select * from employees e right join departments d on d.id = e.department_id;
-- 1.3 cross
select * from employees cross join departments;

-- 2. Используя left join найти департаменты, у которых нет работников
select d.*
  from departments d left join employees e on d.id = e.department_id
 where e.department_id is null;

-- 3. Используя left и right join написать запросы, которые давали бы одинаковый результат
-- (порядок вывода колонок в эти запросах также должен быть идентичный).
select d.*, e.* from departments d left join employees e on d.id = e.department_id;
select d.*, e.* from employees e right join departments d on d.id = e.department_id;

-- 4. Создать таблицу teens с атрибутами name, gender и заполнить ее.
-- Используя cross join составить все возможные разнополые пары.
-- Исключите дублирование пар вида Вася-Маша и Маша-Вася.
create table teens (
	id serial primary key,
	name varchar(255),
	gender boolean
);

insert into teens (name, gender) values ('Катя', false);
insert into teens (name, gender) values ('Маша', false);
insert into teens (name, gender) values ('Вика', false);
insert into teens (name, gender) values ('Егор', true);
insert into teens (name, gender) values ('Илья', true);
insert into teens (name, gender) values ('Саня', true);

select male.name, female.name
  from teens male cross join teens female
 where male.gender = true
   and female.gender = false;
