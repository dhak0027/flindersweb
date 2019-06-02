create table student
(studentid int,name varchar(20),Department varchar(20),
CONSTRAINT student_pk PRIMARY KEY (studentid));

CREATE OR REPLACE PUBLIC SYNONYM student FOR student;

