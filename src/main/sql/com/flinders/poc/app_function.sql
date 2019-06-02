CREATE TABLE app_function (
function_code VARCHAR2(4), function_id VARCHAR2(15), function_desc VARCHAR2(30),
status VARCHAR2(1),
CONSTRAINT app_function_pk PRIMARY KEY (function_code));

CREATE OR REPLACE PUBLIC SYNONYM app_function FOR app_function;
