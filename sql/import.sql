drop database if exists google_auth_server;
create database google_auth_server;

grant all on google_auth_server.* to 'go-auth-server'@'%' identified by '123456';