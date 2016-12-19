-------------------------------------------------------------------------------
-- Schema Creation
-------------------------------------------------------------------------------
-- drop schema if exists mysql_jps_spring_loadbalance;
-- create schema mysql_jps_spring_loadbalance default character set utf8 ;
use mysql_jps_spring_loadbalance;

-------------------------------------------------------------------------------
-- Table Creation
-------------------------------------------------------------------------------
create table users
(
  email_id                  varchar(50) not null comment 'email id of the user',
  first_name                varchar(25) null comment 'first name of the user',
  last_name                 varchar(25) null comment 'last name of the user',
  created_timestamp timestamp not null default now() comment 'record creation timestamp' ,
  last_updated_timestamp timestamp not null comment 'record modification timestamp',
  primary key (email_id)
)
default character set = utf8
comment = 'this table stores user information';


-------------------------------------------------------------------------------
-- Seed Data
-------------------------------------------------------------------------------
 insert into users(first_name,last_name,email_id,created_timestamp,last_updated_timestamp) values('first1', 'last1', 'first1_last1@i.com', now(), now());
 insert into users(first_name,last_name,email_id,created_timestamp,last_updated_timestamp) values('first2', 'last2', 'first2_last2@i.com', now(), now());
 insert into users(first_name,last_name,email_id,created_timestamp,last_updated_timestamp) values('first3', 'last3', 'first3_last3@i.com', now(), now());
 insert into users(first_name,last_name,email_id,created_timestamp,last_updated_timestamp) values('first4', 'last4', 'first4_last4@i.com', now(), now());
 insert into users(first_name,last_name,email_id,created_timestamp,last_updated_timestamp) values('first5', 'last5', 'first5_last5@i.com', now(), now());
 commit;