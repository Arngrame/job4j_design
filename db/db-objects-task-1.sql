-- develops
create table dev_team (
	id serial primary key,
	name varchar(255),
	birth_date date,
	start_job_date date
);

-- quality assurance
create table qa_team (
	id serial primary key,
	name varchar(255),
	birth_date date,
	start_job_date date
);

-- test automation
create table ta_team (
	id serial primary key,
	name varchar(255),
	birth_date date,
	start_job_date date
);

-- business analytics
create table ba_team (
	id serial primary key,
	name varchar(255),
	birth_date date,
	start_job_date date
);

-- system analytics
create table sa_team (
	id serial primary key,
	name varchar(255),
	birth_date date,
	start_job_date date
);

-- filling tables
insert into dev_team (name, birth_date, start_job_date)
values
	('Илья Муромец', '1990-01-31', '2010-01-01'),
	('Добрыня Никитич', '1991-02-28', '2010-02-01'),
	('Алёша Попович', '1992-03-27', '2010-03-01'),
	('Святогор', '1989-04-26', '2010-04-01'),
	('Микула Селянинович', '1988-05-25', '2010-05-01'),
	('Настасья Микулишна', '1991-05-25', '2010-06-01')
;

insert into qa_team (name, birth_date, start_job_date)
values
	('Вольга Святославович', '2000-01-31', '2018-02-01'),
	('Дунай Иванович', '2000-09-13', '2018-09-30'),
	('Никита Кожемяка', '1999-03-11', '2018-05-01'),
	('Ставр Годинович', '1998-04-12', '2018-06-01'),
	('Евпатий Коловрат', '1997-10-22', '2018-07-01')
;

insert into ta_team (name, birth_date, start_job_date)
values
	('Змей Тугарин', '2001-11-29', '2020-01-01'),
	('Змей Горыныч', '2001-12-28', '2020-01-01'),
	('Соловей Разбойник', '1985-10-27', '2005-06-01'),
	('Кощей Бессмертный', '1985-08-26', '2005-08-01'),
	('Чудо-Юдо', '1985-07-25', '2005-10-01')
;

insert into ba_team (name, birth_date, start_job_date)
values
	('Ёжик в тумане', '2001-11-25', '2022-01-20'),
	('Кот Матроскин', '2001-12-28', '2022-02-22'),
	('Попугай Кеша', '1985-10-27', '2005-05-15'),
	('Золотая рыбка', '1985-08-26', '2006-06-12'),
	('Крокодил Гена', '1985-07-25', '2007-08-11')
;


insert into sa_team (name, birth_date, start_job_date)
values
	('Настасья Филипповна', '2001-11-04', '2022-12-01'),
	('Алёнушка', '2001-12-28', '2023-01-01'),
	('Любава', '1985-10-27', '2006-01-01')
;

-- query + view
create view empl_job_more_5_years as
with t as (
	select * from dev_team
	union all
	select * from qa_team
	union all
	select * from ta_team
	union all
	select * from ba_team
	union all
	select * from sa_team
)
select t.name, t.birth_date, t.start_job_date, now()::date, date_part('year', age(now(), t.start_job_date))
  from t
 group by t.name, t.birth_date, t.start_job_date
having date_part('year', age(now(), t.start_job_date)) > 5;

select * from empl_job_more_5_years;
