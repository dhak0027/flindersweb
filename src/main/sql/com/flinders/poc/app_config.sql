CREATE TABLE app_config (
app_id VARCHAR(3), app_name VARCHAR2(30), app_desc VARCHAR2(30), 
CONSTRAINT app_config_pk PRIMARY KEY (app_id));

CREATE OR REPLACE PUBLIC SYNONYM app_config FOR app_config;
