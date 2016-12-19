-- drop schema if exists crash;
-- create schema `crash` default character set utf8 ;
-- --drop user 'crash'@'localhost';
-- create user 'crash'@'localhost' identified by 'crash$1';
-- grant all on crash.* to 'crash'@'localhost';

--drop user 'crash'@'%';
-- create user 'crash'@'%' identified by 'crash$1';
-- grant all on crash.* to 'crash'@'%';
-- flush privileges;

create table app
  (
    app_id                  bigint not null auto_increment comment 'application id.  surrogate',
    name                    varchar(50) not null comment 'application name.  used for display purposes',
    package_name            varchar(250) not null comment 'bundle id or package name.  unique in app store',
  primary key (app_id),
  unique index app_unique (package_name, app_type_id, asc),
  )
default character set = utf8
comment = 'this table stores application related details';
