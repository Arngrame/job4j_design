psql -d job4j_design_db -U postgres;

create table game
(
    id       serial primary key,
    name     varchar(50),
    duration integer default 0,
    players_count integer
);

insert into game (name, duration, players_count) values
('Football', 90, 22),
('Basketball', 48, 12),
('Streetball', 10, 6);

-- read committed (PG uses the level by default)
begin transaction;

select * from game;

insert into game (name, duration, players_count) VALUES ('Rapid', 5, 2);
delete from game where duration = 90;
update game set duration = 10 where name = 'Rapid';

commit;

truncate game;

-- repeatable read
insert into game (name, duration, players_count) values
('Football', 90, 22),
('Basketball', 48, 12),
('Streetball', 10, 6);

\! cls

begin transaction isolation level repeatable read;

select * from game;

insert into game (name, duration, players_count) VALUES ('Rapid', 5, 2);
delete from game where duration = 90;
update game set duration = 15 where name = 'Streetball';

commit;

truncate game;

-- serializable
insert into game (name, duration, players_count) values
('Football', 90, 22),
('Basketball', 48, 12),
('Streetball', 10, 6);

\! cls

begin transaction isolation level serializable;

select * from game;

select sum(players_count) from game;
update game set players_count = 8 where name = 'Streetball';

select sum(players_count) from game;
update game set players_count = 15 where name = 'Basketball';

commit;