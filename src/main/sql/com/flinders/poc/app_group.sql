CREATE TABLE app_group (
group_code VARCHAR2(4), group_id VARCHAR2(10), group_desc VARCHAR2(30),
CONSTRAINT app_group_pk PRIMARY KEY (group_code));

CREATE OR REPLACE PUBLIC SYNONYM app_group FOR app_group;