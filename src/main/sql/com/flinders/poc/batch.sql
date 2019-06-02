INSERT INTO app_config VALUES ('001','FLINDERSPOC', 'FLinders POC App');

INSERT INTO app_group VALUES ('G001','G_Admin', 'Admin Menu Access');
INSERT INTO app_group VALUES ('G002','G_Student', 'User Menu Access');
INSERT INTO app_group VALUES ('G003','G_Report', 'Report Menu Access');

--abcd1234 - YWJjZDEyMzQ=
--zaq!2wsx - emFxITJ3c3g=
INSERT INTO app_user VALUES (app_user_seq.nextval,'MSWAHITHALI', 'Mohamed Sirajudeen Wahith Ali',
'YWJjZDEyMzQ=', 'mohamedcraj@gmail.com', '001', 'G001', null, null, 
'system', sysdate, null, 
null, 1);

INSERT INTO app_user VALUES (app_user_seq.nextval,'DKATHICK', 'Karthick Dhakshinamoorthy',
'YWJjZDEyMzQ=', 'test@gmail.com', '001', 'G002', null, null, 
'system', sysdate, null, 
null, 1);

INSERT INTO app_user VALUES (app_user_seq.nextval,'KSANDHYA', 'Sandhya Karthick',
'YWJjZDEyMzQ=', 'test@gmail.com', '001', 'G003', null, null, 
'system', sysdate, null, 
null, 1);

INSERT INTO app_user VALUES (app_user_seq.nextval,'MSARJU', 'Sarjana Siraj',
'YWJjZDEyMzQ=', 'test@gmail.com', '001', null, null, null, 
'system', sysdate, null, 
null, 1);

INSERT INTO app_function VALUES ('F001','F_Admin_Read', 'Admin Read Access','A');
INSERT INTO app_function VALUES ('F002','F_Admin_Write', 'Admin Write Access','A');
INSERT INTO app_function VALUES ('F003','F_Student_Read', 'Student Read Access','A');
INSERT INTO app_function VALUES ('F004','F_Student_Write', 'Student Write Access','A');
INSERT INTO app_function VALUES ('F005','F_Report', 'Report Access','A');

insert into course values(1100,'CIS1100','DBMS1');
insert into course values(2100,'CIS2100','OOP1');
insert into course values(3100,'CIS3100','Network1');
insert into course values(4100,'CIS4100','Network2');

insert into course values(1200,'MKT1200','Marketing1');
insert into course values(2200,'MKT2200','Marketing2');
insert into course values(3200,'MKT3200','Advertising1');
insert into course values(4200,'MKT4200','Advertising2');

insert into course values(1300,'FNC1300','DBMS1');
insert into course values(2300,'FNC2300','OOP1');
insert into course values(3300,'FNC3300','Marketing1');
insert into course values(4300,'FNC4300','Advertising1');


insert into tutor values(101,'Teddy','CIS','52603981','teddy@gmail.com');
insert into tutor values(102,'Carl','Marketing','52603982','carl@gmail.com');
insert into tutor values(103,'Martin','Finance','52603983','martin@gmail.com');

insert into student values(1001,'Alex','CIS');
insert into student values(1002,'Butt','CIS');
insert into student values(1003,'Charles','CIS');
insert into student values(1004,'David','CIS');

insert into student values(1005,'Elen','CIS');
insert into student values(1006,'Faeming','CIS');
insert into student values(1007,'Glen','CIS');
insert into student values(1008,'Harvard','CIS');

insert into student values(1009,'Irwin','Marketing');
insert into student values(1010,'Jack','Marketing');
insert into student values(1011,'Kevin','Marketing');
insert into student values(1012,'Luke','Marketing');

insert into student values(1013,'Marx','Finance');
insert into student values(1014,'Nick','Finance');
insert into student values(1015,'Peter','Finance');
insert into student values(1016,'Rock','Finance');

/*
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
*/

commit;