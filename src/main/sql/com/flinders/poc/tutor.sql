create table tutor
(tutorid int,name varchar(20), Department varchar(20), Phone int,email varchar(30),
CONSTRAINT tutor_pk PRIMARY KEY (tutorid));

CREATE OR REPLACE PUBLIC SYNONYM tutor FOR tutor;