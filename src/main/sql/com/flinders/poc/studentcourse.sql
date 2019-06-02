drop table student;

create table student
(sid int,name varchar(20),major varchar(10),mark float,tutorid int,
PRIMARY KEY (sid),
CONSTRAINT studentcourse_tutor_fk FOREIGN KEY (tutorid) REFERENCES tutor (tutorid));

insert into student values(1001,'Alex','CIS',85.5,101);
insert into student values(1002,'Butt','CIS',70,101);
insert into student values(1003,'Charles','CIS',60,101);
insert into student values(1004,'David','CIS',50,101;

insert into student values(1005,'Elen','CIS',85.5,101);
insert into student values(1006,'Faeming','CIS',70,101);
insert into student values(1007,'Glen','CIS',60,101);
insert into student values(1008,'Harvard','CIS',50,101);

insert into student values(1009,'Irwin','Marketing',85.5,102);
insert into student values(1010,'Jack','Marketing',70,102);
insert into student values(1011,'Kevin','Marketing',60,102);
insert into student values(1012,'Luke','Marketing',50,102);

insert into student values(1013,'Marx','Finance',85.5,103);
insert into student values(1014,'Nick','Finance',70,103);
insert into student values(1015,'Peter','Finance',60,103);
insert into student values(1016,'Rock','Finance',50,103);

commit;