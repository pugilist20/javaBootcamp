drop schema if exists chat cascade;
create schema if not exists chat;

create table chat.users
(
    id  serial primary key,
    login    varchar(16) unique not null,
    password varchar(16)        not null
);


create table chat.chatrooms
(
    id  serial primary key,
    name    varchar(16) unique not null,
    ownerID int,
    foreign key (ownerID) references chat.users (id)
);

create table chat.messages
(
    id  serial primary key,
    authorID int not null,
    foreign key (authorID) references chat.users (id),
    chatroomID int not null,
    foreign key (chatroomID) references chat.chatrooms (id),
    text text not null,
    dateTime timestamp not null
);

create table  chat.user_chatrooms(
    userID int,
    foreign key (userID) references chat.users(id),
    chatroomID int,
    foreign key (chatroomID) references  chat.chatrooms(id)
)