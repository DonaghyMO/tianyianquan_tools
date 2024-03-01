-- 检查数据库是否存在，如果不存在则创建
CREATE DATABASE IF NOT EXISTS tianyianquan;
USE tianyianquan;

-- 检查表是否存在，如果不存在则创建
CREATE TABLE IF NOT EXISTS tbl_result (
                                          id          INT AUTO_INCREMENT PRIMARY KEY,
                                          data        TEXT NULL COMMENT '输出结果',
                                          command     CHAR(100) NULL,
    is_sync     INT DEFAULT 0 NOT NULL COMMENT '是否同步到soar',
    status      INT DEFAULT 20050 NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL
    );

-- 检查表是否存在，如果不存在则创建
CREATE TABLE IF NOT EXISTS tbl_user (
                                        id       INT AUTO_INCREMENT PRIMARY KEY,
                                        username VARCHAR(20) NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL
    );

-- 插入用户数据，仅在用户表中不存在该用户时才插入
INSERT INTO tbl_user (username, PASSWORD)
SELECT 'admin', 'byVG5sOkKtPxqJZVeY5C8n1GMk+s+DHsauYXfci/5hA='
    WHERE NOT EXISTS (
    SELECT 1 FROM tbl_user WHERE username = 'admin'
);

-- 修改表结构，仅在字段不存在时才添加
alter table tbl_result add only_id char(50) not null;
alter table tbl_result modify command char(255) null;
alter table tbl_result modify data longtext null comment '输出结果';
