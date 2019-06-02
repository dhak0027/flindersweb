CREATE TABLE app_log (
app_log_id NUMBER(8,2), user_id VARCHAR2(30), action VARCHAR2(30), 
description VARCHAR2(100), log_date DATE);

CREATE OR REPLACE PUBLIC SYNONYM app_log FOR app_log;