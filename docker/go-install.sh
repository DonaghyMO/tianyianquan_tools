#!/bin/bash
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

# 安装httpx
echo '开始httpx安装'
go install -v github.com/projectdiscovery/httpx/cmd/httpx@latest

