create table roles (
	id serial primary key,
	name varchar(255)
);

create table categories (
	id serial primary key,
	description varchar(255)
);

create table states (
	id serial primary key,
	description varchar(255)
);

create table users (
	id serial primary key,
	full_name varchar(255),
	role_id integer references roles(id)
);

create table rules (
	id serial primary key,
	name varchar(255)
);

create table role_to_rule (
	id serial primary key,
	rule_id integer references rules(id),
	role_id integer references roles(id)
);

create table items (
	id serial primary key,
	name varchar(255),
	user_id integer references users(id),
	category_id integer references categories(id),
	state_id integer references states(id)
);

create table comments (
	id serial primary key,
	content varchar(255),
	item_id integer references items(id)
);

create table attachs (
	id serial primary key,
	content varchar(255),
	item_id integer references items(id)
);