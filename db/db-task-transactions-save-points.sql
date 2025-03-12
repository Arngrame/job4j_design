psql -d job4j_design_db -U postgres;

create table colors
(
    id serial primary key,
    name varchar(50),
    code varchar(50)
);

insert into colors (name, code) values
('Red', 'FF0000'),
('Orange', 'FF8000'),
('Yellow', 'FFFF00'),
('Light Green', '80FF00'),
('Green', '00FF00');

begin transaction;

insert into colors (name, code) values ('Blue', '0000FF');

commit transaction;

select * from colors;

begin transaction;

delete from colors;
drop table colors;

rollback transaction;

select * from colors;

begin transaction;

insert into colors (name, code) values ('Purple', '8000FF');

savepoint first_savepoint;

delete from colors where name = 'Blue';
update colors set code = '00FF01' where name = 'Green';

select * from colors;

rollback to first_savepoint;

select * from colors;

commit transaction;