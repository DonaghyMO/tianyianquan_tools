# 天翼安全工具服务器
## 已实现工具

## 请求方式
* restful风格的请求方式
* 示例
    * url：http://[doamin]/tools/nmap
    * 请求方式：
        * GET ：获取帮助信息
        * POST：
            * 请求结构：
                * ```json
            {
                "tool":"请求工具名",
                "command": "执行的命令",
                "is_asynchronous": "是否异步调用"   
            }
          ```
## 响应结构
```json
{
  "code": "响应状态码",
  "data": "执行结果",
  "message": "信息"
}
```
