#!/bin/bash
# -*- coding: utf-8 -*-
MYSQL_PWD=1998
while true; do
    # 查询数据库获取命令列表，并存储在变量中
    commands=$(mysql -u root -p$MYSQL_PWD -h tianyi_mysql -D tianyianquan -e "SELECT id, command FROM tbl_result WHERE status=20052;" -B -N)
    mysql -u root -p$MYSQL_PWD -h tianyi_mysql -D tianyianquan -e "UPDATE tbl_result SET status=20053 WHERE status=20052;"
    # 定义一个空的命令列表数组
    commands_array=()

    # 将命令列表按行分隔存入数组
    IFS=$'\n' read -r -d '' -a commands_array <<< "$commands"

    # 遍历数组执行命令
    for row in "${commands_array[@]}"; do
        id=$(echo "$row" | awk '{print $1}')
        command=$(echo "$row" | awk '{$1=""; print $0}' | sed 's/^[ \t]*//')
        echo "Executing command: $command"
        # 在子进程中执行命令
        {
            data=$(eval "$command")
#            echo $data
            # 更新状态为20050

            # 对data中的特殊字符进行转义
            escaped_data=$(echo "$data" | sed 's/"/\\"/g') # 转义双引号

            # 更新状态为20050，并将data存入数据库，假设$id为脚本中定义的某个变量
            update_query="UPDATE tbl_result t SET t.status=20050, t.data=\"$escaped_data\" WHERE id=$id"

            echo "$update_query" | mysql -u root -p$MYSQL_PWD -h tianyi_mysql -D tianyianquan

        } &
    done

    # 等待所有子进程结束
    wait


    # 等待1分钟后继续执行
    sleep 60
done