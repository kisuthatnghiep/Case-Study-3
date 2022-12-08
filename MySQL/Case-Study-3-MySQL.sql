drop database webmusic;
create database webmusic;
use webmusic;

create table users(
id bigint not null auto_increment primary key,
userAccount varchar(100),
userPassword varchar(100),
fullName varchar(100),
email varchar(100),
telephone varchar(50),
wallet double check(wallet >= 0),
status int
);
create table singer(
id bigint not null auto_increment primary key,
userAccount varchar(100),
userPassword varchar(100),
fullName varchar(100),
telephone varchar(50),
email varchar(100),
income double check(income >= 0),
status int
);

create table song(
id bigint not null auto_increment primary key,
name varchar(100),
link varchar(300),
description varchar(300),
price double check(price >= 0),
singerId bigint not null,
status int,
foreign key (singerId) references singer(id)
);

create table playlist(
id bigint not null auto_increment primary key,
name varchar(100),
date date,
userId bigint not null,
foreign key (userId) references users(id)
);

create table playlistdetail(
playlistId  bigint not null,
songId  bigint not null,
date date,
status int,
foreign key (playlistId) references playlist(id),
foreign key (songId) references song(id),
primary key(playlistId,songId)
);