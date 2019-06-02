alter session set "_ORACLE_SCRIPT"=true;
create user flinders_admin identified by Flindersadmin123;
grant sysdba to flinders_admin;
grant dba to flinders_admin; 