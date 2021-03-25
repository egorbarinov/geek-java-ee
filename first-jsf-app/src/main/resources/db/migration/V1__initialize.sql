create table users
(
    id      bigserial primary key,
    login    varchar(255) not null,
    password varchar(255)
);

insert into users (login, password)
values ('admin', 'admin'),
       ('guest', 'guest');

create table roles
(
    id      bigserial primary key,
    name    varchar(255) not null
);

insert into roles (name)
values ('ADMIN'), ('GUEST');

create table categories
(
    id   bigserial primary key,
    name varchar(255)
);

insert into categories (name)
values ('Food'),
       ('Notebooks'),
       ('Smartphones');

create table products
(
    id          bigserial primary key,
    name        varchar(255),
    description varchar(1024),
    price       decimal,
    category_id bigint references categories (id)
);

insert into products (name, description, price, category_id)
values ('Bread', 'Hot bread from bakery', 10, 1),
       ('Samsung V100', 'Extra cool model', 234.99, 3),
       ('Acer X1000', 'New flagman from Acer', 1549.99, 2);
