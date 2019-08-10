drop table diary if exists;
drop table user if exists;
drop table dibs if exists;

create table diary (id bigint generated by default as identity, content clob not null, created_by timestamp, place varchar(255) not null, sometime timestamp not null, title varchar(30) not null, with_who varchar(255) not null, user_seq bigint not null, primary key (id));
create table user (seq bigint generated by default as identity, created_by timestamp, user_id bigint not null, user_name varchar(255), primary key (seq));
create table dibs (id bigint generated by default as identity,start_date varchar(255), end_date varchar(255), image_url varchar(255), place varchar(255), title varchar(255), user_seq bigint not null, primary key (id));

alter table user add constraint UK_USERID unique (user_id);

alter table diary add constraint DIARY_USER_FK foreign key (user_seq) references user
alter table dibs add constraint DIBS_USER_FK foreign key (user_seq) references user;
alter table diary add constraint DIARY_CULTURE_FK foreign key (culture_id) references culture