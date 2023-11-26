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