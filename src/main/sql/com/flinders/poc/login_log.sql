CREATE TABLE login_log (
login_log_id NUMBER(8,2), user_id VARCHAR2(30), action VARCHAR2(30), 
status VARCHAR2(30), fail_reason VARCHAR2(100), log_date DATE);

CREATE OR REPLACE PUBLIC SYNONYM login_log FOR login_log;