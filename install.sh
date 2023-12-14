#!/bin/bash
echo 'source /etc/profile'>>~/.bashrc
#mv /sources.list /etc/apt/sources.list
apt-get update
apt-get install -y nmap wget python3-pip git inetutils-ping vim systemd

# go安装相关
echo '开始go安装'
rm -rf /usr/local/go
wget https://go.dev/dl/go1.21.4.linux-amd64.tar.gz
tar -xzf go1.21.4.linux-amd64.tar.gz -C /usr/local
echo 'export GOPATH=/usr/local/go'>>/root/.bashrc
echo 'export PATH=$GOPATH/bin:$PATH'>>/root/.bashrc
source ~/.bashrc

# httpx
echo '开始httpx安装'
go install -v github.com/projectdiscovery/httpx/cmd/httpx@latest

# 安装afrog
echo '开始afrog安装'
go install -v github.com/zan8in/afrog/v2/cmd/afrog@latest

# 安装nuclei
echo '开始nuclei安装'
go install -v github.com/projectdiscovery/nuclei/v3/cmd/nuclei@latest

# python相关工具
mkdir /python_tools

# dirsearch
echo '开始dirsearch安装'
git clone https://github.com/maurosoria/dirsearch.git /python_tools/dirserch
echo "alias dirsearch='python3 /python_tools/dirsearch/dirsearch.py'">>/root/.bashrc

#POC-bomber
echo '开始POC-bomber安装'
git clone https://github.com/tr0uble-mAker/POC-bomber.git /python_tools/POC-bomber
cd /python_tools/POC-bomber
pip install -r requirements.txt
echo "alias POC-bomber='python3 /python_tools/POC-bomber/pocbomber.py'">>/root/.bashrc

# xray
echo '开始xray安装'
bash -c "$(curl -L https://github.com/XTLS/Xray-install/raw/main/install-release.sh)" @ install
bash -c "$(curl -L https://github.com/XTLS/Xray-install/raw/main/install-release.sh)" @ install-geodata
