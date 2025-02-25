-- create table
create table street (
	id serial primary key,
	name varchar(255),
	postal_code integer ,
	created_date date
);

-- insert rows
-- Date has format = YYYY-MM-DD
insert into street	(name, postal_code, created_date)
			values 	('Гагарина', '880053', '1990-01-15'),
		   			('Московская', '880054', '1995-02-16'),
		   			('Гагарина', '880055', '2000-03-17');

select * from street;

-- update single row
update street
   set name = 'Космическая'
 where postal_code = 880055;

select * from street;

-- delete single row
delete from street
 where name = 'Космическая';
