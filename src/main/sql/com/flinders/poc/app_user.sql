CREATE TABLE app_user (
seq NUMBER(8,2), user_id VARCHAR2(30), user_name VARCHAR2(30), 
password VARCHAR2(30), email_address VARCHAR2(35), app_id VARCHAR2(3), 
group_code VARCHAR2(4), last_login_status VARCHAR2(35), last_login_time DATE,
created_by VARCHAR2(35), creation_date DATE, modified_by VARCHAR2(35), 
modification_date DATE, versionNum int,
CONSTRAINT app_user_config_fk FOREIGN KEY (app_id) REFERENCES app_config (app_id),
CONSTRAINT app_user_grp_fk FOREIGN KEY (group_code) REFERENCES app_config (group_code),  
CONSTRAINT app_user_pk PRIMARY KEY (user_id, app_id));

CREATE OR REPLACE PUBLIC SYNONYM app_user FOR app_user;