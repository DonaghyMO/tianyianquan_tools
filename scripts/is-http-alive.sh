#!/bin/bash
# -*- coding: utf-8 -*-
# Parse options
# while [[ "$#" -gt 0 ]]; do
#     case $1 in
#         -ss-type) ss_type="$2"; shift ;;
#         *) echo "Unknown parameter: $1"; exit 1 ;;
#     esac
#     shift
# done

# 工作目录
work_dir="$1"
# 输入文件
input_file="$2"
#echo "$work_dir"
#echo "$input_file"
# 输出文件
output_file="output_alive.txt"
http_output_file="http_output.txt"
https_output_file="https_output.txt"
report_output_file="report_output.txt"

cd $work_dir


tail -n +2 "$input_file" | cut -f1 > hosts_only.txt

# Extract hosts containing "投资类"
# fraud_file="${input_base}_fraud.txt"
# awk -F '\t' '$3 ~ "投资类" {print $1}' "$input_file" > "$fraud_file"
# condition: no cdn, alive
# 踩坑java中执行shell并不支持管道操作，它实际上创建了一个新的进程并执行sed命令

#echo "httpx开始执行"
/root/go/bin/httpx -silent -cdn -l hosts_only.txt | sed 's/\x1b\[[0-9;]*m//g' > raw_httpx.txt
#echo "httpx结束执行"

grep -P '^(https?://[^ \[]+)(?!.*\[(cloudfront|cloudflare|google|incapsula)\])' raw_httpx.txt > "$output_file"
grep -P '^http://' "$output_file"  > "$http_output_file" &
grep -P '^https://' "$output_file"  > "$https_output_file"
wait
# Clean up the intermediate file
# rm hosts_only.txt
# rm raw_httpx.txt

#echo "Alive hosts saved to $output_file"
#echo "Alive http hosts saved to $http_output_file"
#echo "Alive https hosts saved to $https_output_file"

# Run aquatone to take high-quality screenshots
# if [ "$ss_type" == "http" ]; then
#     cat "$http_output_file" | aquatone -out "./aquatone/" -threads 20 -silent -full-page -screenshot-timeout 50000 -chrome-path "/usr/bin/chromium"
# else
#     cat "$output_file" | aquatone -out "./aquatone/" -threads 20 -silent -full-page -screenshot-timeout 50000 -chrome-path "/usr/bin/chromium"
# fi





# 统一输出到终端
# output report
#echo "总数据: $(wc -l < "$input_file")" > "$report_output_file"
echo "total data count: $(wc -l < "$input_file")"
#echo "存活主机: $(wc -l < raw_httpx.txt)" > "$report_output_file"
echo "alive host: $(wc -l < raw_httpx.txt)"
#echo "alive no cdn: $(wc -l < "$output_file")" > "$report_output_file"
echo "alive no cdn: $(wc -l < "$output_file")"
#echo "alive HTTPS number: $(wc -l < "$https_output_file")" > "$report_output_file"
echo "alive HTTPS number: $(wc -l < "$https_output_file")"
#echo "alive HTTP number: $(wc -l < "$http_output_file")" > "$report_output_file"
echo "alive HTTP number: $(wc -l < "$http_output_file")"


# 同时输出到终端
echo "raw httpx:"
grep -P '^(https?://[^ \[]+)(?!.*\[(cloudfront|cloudflare|google|incapsula)\])' raw_httpx.txt

echo "http output:"
grep -P '^http://' "$output_file"&
wait

echo "https output:"
grep -P '^https://' "$output_file"&
wait

# aquatone相关
cat "$http_output_file" | /root/go/bin/aquatone -out "./aquatone/" -threads 20 -silent -full-page -screenshot-timeout 50000 -chrome-path "/usr/bin/chromium-browser"
#echo "Aquatone screenshots process completed"
echo "aquatone output dir:$work_dir"

# 清空httpx工作文件
rm -f ./*.txt

# # katana crawl -list url_list.txt | grep -Eo "(http://[a-zA-Z0-9./?=_-]*" > aggregated_urls.txt
# # Sort and deduplicate the aggregated results
# sort -u aggregated_urls.txt -o unique_urls.txt
# echo "Unique URLs have been saved to unique_urls.txt"
