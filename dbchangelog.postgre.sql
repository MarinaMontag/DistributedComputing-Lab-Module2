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

--changeset marina:3
insert into movies values (default, 'Mortal Engines', 'Christian Rivers', 'Amanda Walker', 
'Fran Walsh', 'MRC', 128, 'USA, New Zealand', 2018, 7);
insert into movies values (default, 'Old Guard', 'Gina Prince-Bythewood', 'Dana Goldberg', 
'Greg Rucka', 'Marc Evans Productions', 125, 'USA', 2020, 2);
insert into movies values (default, 'Hannibal', 'Ridley Scott', 'Ridley Scott', 
'David Mamet', 'Universal Pictures', 132, 'USA', 2001, 8);
insert into movies values (default, 'The Lord of the Rings:The Fellowship of the Ring', 
'Peter Jackson', 'Peter Jackson', 'Peter Jackson', 'New Line Cinema', 178, 'USA, New Zealand', 2001, 2);
insert into movies values (default, 'Underwater', 'William Eubank', 'Tonia Davis', 
'Brian Duffield', '20th Century Fox', 95, 'USA', 2020, 8);
