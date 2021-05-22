-- liquibase formatted sql changeLogId:7fe24eff-f4a7-4c57-a29c-a9fd538d902a
--changeset marina:1
create table genres(
    id serial primary key,
    name varchar(25) not null
);

create table movies(
    id serial primary key,
    name varchar(50) not null,
    director varchar(100) not null,
    producer varchar(100) not null,
    screenwriter varchar(100) not null,
    company varchar(150) not null,
    duration int not null,
    country varchar(50) not null,
    year int not null,
    genre_id int not null,
    foreign key (genre_id) references genres (id)
);

--changeset marina:2
alter table genres add unique (name);
