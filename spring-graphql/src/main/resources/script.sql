create table categories (
                            id bigserial,
                            name varchar(255) not null,
                            constraint categories_id_pk primary key(id),
                            constraint categories_name_un unique(name)
);

create table products (
                          id bigserial,
                          name varchar(255) not null,
                          price numeric not null,
                          quantity int not null,
                          category_id bigint not null,
                          constraint products_id_pk primary key(id),
                          constraint products_category_id_fk foreign key(category_id) references categories(id)
);

insert into categories (name)
values ('Processador');

insert into products (name, price, quantity, category_id)
values ('Intel Core I5 10400', 600.00, 10, 3);

insert into products (name, price, quantity, category_id)
values ('Intel Core I7 10700k', 1200.00, 4, 3);

insert into products (name, price, quantity, category_id)
values ('AMD Ryzen 7 7800X3D', 2400.20, 2, 3);
