create table student (
	id serial primary key,
	ticket_id integer unique,
	first_name varchar(255),
	last_name varchar(255)
);

insert into student (ticket_id, first_name, last_name) values(100, 'Василий', 'Иванов');
insert into student (ticket_id, first_name, last_name) values(200, 'Арсений', 'Медведев');

select * from student;
--

create table faculty (
	id serial primary key,
	code integer unique,
	name varchar(255),
	address varchar(255)
);

insert into faculty(code, name, address) values (1000, 'Факультет информационных технологий', 'Гагарина 30');
insert into faculty(code, name, address) values (2000, 'Факультет электроники и техники', 'Гагарина 31');

select * from faculty;
--

create table dean_office (
	id serial primary key,
	office_number integer,
	floor_number integer,
	faculty_id integer references faculty(id) unique
);

insert into dean_office (office_number, floor_number, faculty_id) values (10, 4, 1);
insert into dean_office (office_number, floor_number, faculty_id) values (20, 5, 2);

select * from dean_office;
--

create table student_faculty (
	id serial primary key,
	student_id integer references student(id),
	faculty_id integer references faculty(id),
	type varchar(255)
);

insert into student_faculty(student_id, faculty_id, type) values (1, 1, 'очно');
insert into student_faculty(student_id, faculty_id, type) values (1, 2, 'заочно');
insert into student_faculty(student_id, faculty_id, type) values (2, 2, 'очно');

select * from student_faculty;

-- вывод для проверки кейса, что студент может учиться на > 1 факультете
select  f.name as "Название факультета",
		s.ticket_id as "Номер студенческого билета",
		s.first_name as "Имя",
		s.last_name as "Фамилия",
		sf.type as "Тип обучения"
  from 	student s,
  		faculty f,
		student_faculty sf
 where 	s.id = sf.student_id
   and 	sf.faculty_id = f.id;