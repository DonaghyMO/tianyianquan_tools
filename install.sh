#!/bin/bash
echo 'source /etc/profile'>>~/.bashrc
#mv /sources.list /etc/apt/sources.list
apt-get update
apt-get install -y nmap wget python3-pip git inetutils-ping vim systemd

# go安装相关
rm -rf /usr/local/go
wget https://go.dev/dl/go1.21.4.linux-amd64.tar.gz
tar -xzf go1.21.4.linux-amd64.tar.gz -C /usr/local
echo 'export GOPATH=/usr/local/go'>>/root/.bashrc
echo 'export PATH=$GOPATH/bin:$PATH'>>/root/.bashrc
source ~/.bashrc

# 安装afrog
go install -v github.com/zan8in/afrog/v2/cmd/afrog@latest

# 安装nuclei
go install -v github.com/projectdiscovery/nuclei/v3/cmd/nuclei@latest

# python相关工具
mkdir /python_tools

# dirsearch
git clone https://github.com/maurosoria/dirsearch.git /python_tools/dirserch
echo "alias dirsearch='python3 /python_tools/dirserch/dirsearch.py'">>/root/.bashrc

#POC-bomber
git clone https://github.com/tr0uble-mAker/POC-bomber.git /python_tools/POC-bomber
cd /python_tools/POC-bomber
pip install -r requirements.txt
echo "alias POC-bomber='python3 /python_tools/POC-bomber/pocbomber.py'">>/root/.bashrc

# xray
bash -c "$(curl -L https://github.com/XTLS/Xray-install/raw/main/install-release.sh)" @ install
bash -c "$(curl -L https://github.com/XTLS/Xray-install/raw/main/install-release.sh)" @ install-geodata