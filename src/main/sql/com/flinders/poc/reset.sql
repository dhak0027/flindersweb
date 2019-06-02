drop table app_user;
drop table app_config;
drop table app_function;
drop table app_group;
drop table app_log;
drop table login_log;
drop table student;
drop table course;
drop table tutor;

drop sequence app_log_seq;
drop sequence app_user_seq;
drop sequence login_log_seq;

drop public synonym app_user;
drop public synonym app_config;
drop public synonym app_function;
drop public synonym app_group;
drop public synonym app_log;
drop public synonym login_log;
drop public synonym student;
drop public synonym course;
drop public synonym tutor;

drop public synonym app_log_seq;
drop public synonym app_user_seq;
drop public synonym login_log_seq;

--drop table tutor;

CREATE TABLE app_config (
app_id VARCHAR(3), app_name VARCHAR2(30), app_desc VARCHAR2(30), 
CONSTRAINT app_config_pk PRIMARY KEY (app_id));

CREATE OR REPLACE PUBLIC SYNONYM app_config FOR app_config;


CREATE TABLE app_function (
function_code VARCHAR2(4), function_id VARCHAR2(15), function_desc VARCHAR2(30),
status VARCHAR2(1),
CONSTRAINT app_function_pk PRIMARY KEY (function_code));

CREATE OR REPLACE PUBLIC SYNONYM app_function FOR app_function;


CREATE TABLE app_group (
group_code VARCHAR2(4), group_id VARCHAR2(10), group_desc VARCHAR2(30),
CONSTRAINT app_group_pk PRIMARY KEY (group_code));

CREATE OR REPLACE PUBLIC SYNONYM app_group FOR app_group;

CREATE TABLE app_log (
app_log_id NUMBER(8,2), user_id VARCHAR2(30), action VARCHAR2(30), 
description VARCHAR2(100), log_date DATE);

CREATE OR REPLACE PUBLIC SYNONYM app_log FOR app_log;

CREATE SEQUENCE app_log_seq
       MINVALUE 1
       MAXVALUE 9999999999999999999999999
       START WITH 1
       INCREMENT BY 1
       NOCACHE;

CREATE OR REPLACE PUBLIC SYNONYM app_log_seq FOR app_log_seq;


CREATE TABLE app_user (
seq NUMBER(8,2), user_id VARCHAR2(30), user_name VARCHAR2(30), 
password VARCHAR2(30), email_address VARCHAR2(35), app_id VARCHAR2(3), 
group_code VARCHAR2(4), last_login_status VARCHAR2(35), last_login_time DATE,
created_by VARCHAR2(35), creation_date DATE, modified_by VARCHAR2(35), 
modification_date DATE, versionNum int,
CONSTRAINT app_user_config_fk FOREIGN KEY (app_id) REFERENCES app_config (app_id),
CONSTRAINT app_user_grp_fk FOREIGN KEY (group_code) REFERENCES app_group (group_code),  
CONSTRAINT app_user_pk PRIMARY KEY (user_id, app_id));

CREATE OR REPLACE PUBLIC SYNONYM app_user FOR app_user;

CREATE OR REPLACE PUBLIC SYNONYM app_user FOR app_user;

CREATE SEQUENCE app_user_seq
       MINVALUE 1
       MAXVALUE 9999999999999999999999999
       START WITH 1
       INCREMENT BY 1
       NOCACHE;

CREATE OR REPLACE PUBLIC SYNONYM app_user_seq FOR app_user_seq;

CREATE TABLE login_log (
login_log_id NUMBER(8,2), user_id VARCHAR2(30), action VARCHAR2(30), 
status VARCHAR2(30), fail_reason VARCHAR2(100), log_date DATE);

CREATE OR REPLACE PUBLIC SYNONYM login_log FOR login_log;

CREATE SEQUENCE login_log_seq
       MINVALUE 1
       MAXVALUE 9999999999999999999999999
       START WITH 1
       INCREMENT BY 1
       NOCACHE;

CREATE OR REPLACE PUBLIC SYNONYM login_log_seq FOR login_log_seq;

create table course
(courseid int,courseno varchar(10),coursename varchar(15));

CREATE OR REPLACE PUBLIC SYNONYM course FOR course;

create table tutor
(tutorid int,name varchar(20), Department varchar(20), Phone int,email varchar(30),
CONSTRAINT tutor_pk PRIMARY KEY (tutorid));

CREATE OR REPLACE PUBLIC SYNONYM tutor FOR tutor;

create table student
(studentid int,name varchar(20),Department varchar(20),
CONSTRAINT student_pk PRIMARY KEY (studentid));

CREATE OR REPLACE PUBLIC SYNONYM student FOR student;




