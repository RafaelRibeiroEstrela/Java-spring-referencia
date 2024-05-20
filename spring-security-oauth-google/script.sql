create table roles (

                       id bigserial,
                       authority varchar(255) not null,
                       constraint roles_id_pk primary key(id),
                       constraint roles_authority_un unique(authority)
);

insert into roles (authority)
values ('ADMIN');

create table users (

                       id bigserial,
                       username varchar(255) not null,
                       password varchar(255) not null,
                       constraint users_id_pk primary key(id)
);

insert into users (username, password)
values ('carlos@gmail.com', '$2a$12$k1er6FT9oXcxmpFujjzJxOP0QopFxwUXaPt/.iA8dQQTVleaz0uNu');

create table user_roles (

                            id bigserial,
                            user_id bigint,
                            role_id bigint,
                            constraint user_roles_id_pk primary key(id),
                            constraint user_roles_user_id_fk foreign key(user_id) references users(id),
                            constraint user_roles_role_id_fk foreign key(role_id) references roles(id)
);

insert into user_roles (user_id, role_id)
values (1,1);