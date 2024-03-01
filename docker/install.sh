#!/bin/bash

set -e
if ! grep -q 'source /etc/profile' ~/.bashrc; then
    echo 'source /etc/profile' >> ~/.bashrc
fi

# 更新apt源
# mv /sources.list /etc/apt/sources.list  # 暂时注释掉
apt-get update

# 安装软件包列表
packages=("openjdk-8-jdk" "mysql-client" "wget" "curl" "git" "inetutils-ping" "vim" "systemd")
for package in "${packages[@]}"; do
    if dpkg -l | grep -q "^ii\s*$package"; then
        echo "$package 已安装，跳过."
    else
        echo "安装 $package..."
        apt-get install -y $package
    fi
done

# 安装supervisor
if ! dpkg -l | grep -q "^ii\s*supervisor"; then
    echo "安装 supervisor..."
    echo N | apt-get install -y supervisor
fi

# 安装go
echo '开始go安装'
if ! command -v go &> /dev/null; then
    rm -rf /usr/local/go
    wget https://go.dev/dl/go1.21.4.linux-amd64.tar.gz
    tar -xzf go1.21.4.linux-amd64.tar.gz -C /usr/local
    rm go1.21.4.linux-amd64.tar.gz
    mkdir -p ~/go
    echo 'export GOROOT=/usr/local/go/' >> /root/.bashrc
    echo 'export GOPATH=${HOME}/go' >> /root/.bashrc
    echo 'export PATH="/usr/local/go/bin:$GOPATH/bin:$PATH"' >> /root/.bashrc
    echo 'export GOPROXY=https://goproxy.cn,direct' >> /root/.bashrc
    source ~/.bashrc
else
    echo "Go 已安装，跳过."
fi


