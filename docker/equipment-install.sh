#!/bin/bash
apt-get update
packages=("nmap" "chromium-browser" "python3-pip")
for package in "${packages[@]}"; do
    if dpkg -l | grep -q "^ii\s*$package"; then
        echo "$package 已安装，跳过."
    else
        echo "安装 $package..."
        apt-get install -y $package
    fi
done