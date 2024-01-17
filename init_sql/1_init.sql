CREATE DATABASE IF NOT EXISTS tianyianquan;
USE tianyianquan;
create table tbl_result
(
    id          int auto_increment
        primary key,
    data        text                                null comment '输出结果',
    command     char(100)                           null,
    is_sync     int       default 0                 not null comment '是否同步到soar',
    status      int       default 20050             null,
    create_time timestamp default CURRENT_TIMESTAMP null
);
-- auto-generated definition
create table tbl_user
(
    id       int auto_increment
        primary key,
    username varchar(20)  not null,
    password varchar(100) not null
);
INSERT INTO tianyianquan.tbl_user (username, password) VALUES ('admin', 'byVG5sOkKtPxqJZVeY5C8n1GMk+s+DHsauYXfci/5hA=');

