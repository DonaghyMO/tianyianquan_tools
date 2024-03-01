#!/bin/bash

set -e
if ! grep -q 'source /etc/profile' ~/.bashrc; then
    echo 'source /etc/profile' >> ~/.bashrc
fi

# 更新apt源
# mv /sources.list /etc/apt/sources.list  # 暂时注释掉
apt-get update

# 安装软件包列表
packages=("openjdk-8-jdk" "mysql-client" "nmap" "chromium-browser" "python3-pip" "wget" "curl" "git" "inetutils-ping" "vim" "systemd" "unzip")
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

# 安装httpx
echo '开始httpx安装'
go install -v github.com/projectdiscovery/httpx/cmd/httpx@latest

# 安装aquatone
echo '开始 aquatone 安装'
GO111MODULE=off go get github.com/shelld3v/aquatone

# 安装afrog
echo '开始afrog安装'
go install -v github.com/zan8in/afrog/v2/cmd/afrog@latest

# 安装nuclei
echo '开始nuclei安装'
go install -v github.com/projectdiscovery/nuclei/v3/cmd/nuclei@latest

# 安装python相关工具
mkdir -p /python_tools

# 安装dirsearch
echo '开始dirsearch安装'
if [ ! -d "/python_tools/dirsearch" ]; then
    git clone https://github.com/maurosoria/dirsearch.git /python_tools/dirsearch
    echo "alias dirsearch='python3 /python_tools/dirsearch/dirsearch.py'" >> /root/.bashrc
fi

# 安装POC-bomber
echo '开始POC-bomber安装'
if [ ! -d "/python_tools/POC-bomber" ]; then
    git clone https://github.com/tr0uble-mAker/POC-bomber.git /python_tools/POC-bomber
    cd /python_tools/POC-bomber
    pip install -r requirements.txt
    echo "alias POC-bomber='python3 /python_tools/POC-bomber/pocbomber.py'" >> /root/.bashrc
fi

# 安装xray
echo '开始xray安装'
if ! command -v xray &> /dev/null; then
    bash -c "$(curl -L https://github.com/XTLS/Xray-install/raw/main/install-release.sh)" @ install
    bash -c "$(curl -L https://github.com/XTLS/Xray-install/raw/main/install-release.sh)" @ install-geodata
else
    echo "Xray 已安装，跳过."
fi
