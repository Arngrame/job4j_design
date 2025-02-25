insert into roles(name) values ('Администратор');
insert into roles(name) values ('Инженер');
insert into roles(name) values ('Пользователь');

insert into categories(description) values ('Гарантийное обслуживание');
insert into categories(description) values ('Доставка');
insert into categories(description) values ('Ремонт');

insert into states(description) values ('Отклонена');
insert into states(description) values ('Открыта');
insert into states(description) values ('В процессе');

insert into users(full_name, role_id) values ('Иван Иванов', 1);
insert into users(full_name, role_id) values ('Ксения Петрова', 1);
insert into users(full_name, role_id) values ('Евгений Краснов', 2);
insert into users(full_name, role_id) values ('Константин Аршавин', 2);
insert into users(full_name, role_id) values ('Александр Белов', 3);
insert into users(full_name, role_id) values ('Мария Чернова', 3);

insert into rules(name) values ('Создание заявки');
insert into rules(name) values ('Закрытие заявки');
insert into rules(name) values ('Модификация заявки');

-- Администратор - все права
insert into role_to_rule(rule_id, role_id) values (1, 1);
insert into role_to_rule(rule_id, role_id) values (1, 2);
insert into role_to_rule(rule_id, role_id) values (1, 3);

-- Инженер - модификация заявки
insert into role_to_rule(rule_id, role_id) values (2, 2);

-- Пользователь - создание и модификация
insert into role_to_rule(rule_id, role_id) values (3, 1);
insert into role_to_rule(rule_id, role_id) values (3, 2);

insert into items(name, user_id, category_id, state_id) values ('Оборудование вышло из строя', 5, 3, 2);
insert into items(name, user_id, category_id, state_id) values ('Гарантийное обслуживание', 4, 1, 3);
insert into items(name, user_id, category_id, state_id) values ('Закупка оборудования по гарантии', 1, 1, 3);
insert into items(name, user_id, category_id, state_id) values ('Доставка оборудования', 3, 2, 3);

insert into comments(content, item_id) values ('Посмотрите видео с дефектом оборудования', 1);
insert into comments(content, item_id) values ('Приложите чек по гарантийному обслуживанию', 2);
insert into comments(content, item_id) values ('Покупка по списку', 3);
insert into comments(content, item_id) values ('Позвонить за 2 часа до доставки', 4);

insert into attachs (content, item_id) values ('Видео', 1);
insert into attachs (content, item_id) values ('Скриншот', 2);
insert into attachs (content, item_id) values ('Фото', 3);