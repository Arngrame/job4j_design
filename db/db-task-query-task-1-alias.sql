create table team (
	id serial primary key,
	name varchar(255)
);

insert into team(name) values ('Зенит');
insert into team(name) values ('ЦСКА');
insert into team(name) values ('Спартак');
--
create table player (
	id serial primary key,
	name varchar(255),
	team_id integer references team(id)
);

insert into player(name, team_id) values ('Иван Иванов', 1);
insert into player(name, team_id) values ('Пётр Петров', 1);
insert into player(name, team_id) values ('Алексей Алексеев', 2);
insert into player(name, team_id) values ('Сергей Сергеев', 2);
insert into player(name, team_id) values ('Николай Николаев', 3);
--
select 	p.name as "Имя игрока",
		t.name as "Команда"
  from 	player p,
		team t
 where 	p.team_id = t.id;

select p.name as "Имя игрока", t.name as "Команда"
  from player p join team t on p.team_id = t.id;

select p.name, t.name
  from player as p join team as t on p.team_id = t.id;

select p.name Имя, t.name Команда
  from player as p join team as t on p.team_id = t.id;