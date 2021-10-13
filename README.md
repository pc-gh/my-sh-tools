## MYSHTOOL

#### 介绍

my_sh_tool是一个Java语言编写的自动巡检工具。目前支持巡检项有Ping检测、进程检测、端口检测、网页检测：

| type_code | command | 必填项                                                      | 说明 |
| --------- | ------- | ----------------------------------------------------------- | ---- |
| 1         | ping    | host（主机，例：www.baidu.com）                             |      |
| 2         | ps      | item（监控指标，例：systemctl）、threshold（阈值，例：5）。 |      |
| 3         | telnet  | host（主机、例：baidu.com）、port（端口，例：80）           |      |
| 4         | curl    | url（网址，例：www.baidu.com）                              |      |
|           |         |                                                             |      |
|           |         |                                                             |      |

#### 启动方式

* 放置`myShTool.jar`和`env.properties`（<font color='red'>配置文件名称和配置文件类型不支持更改</font>）为同一目录下。
* 配置`env.properties`各项参数。参数介绍：

```
#主机配置
HOST=120.79.197.164
USER=root
PASSWORD=xxx
#选填，默认22
SSHPORT=22

#数据库连接设置
MYSQL_URL=jdbc:mysql://120.79.197.164/sys?useSSL=false&useUnicode=true&characterEncoding=UTF-8
#驱动程序
MYSQL_DRIVER=com.mysql.cj.jdbc.Driver
#取得用户
MYSQL_USER=root
#登录密码
MYSQL_PASSWORD=zabbix
```

* 当前目录下`java -jar myShTool.jar`，回车。

#### 执行测试

程序运行前：

![image-20211013154806304](C:\Users\1960128653\AppData\Roaming\Typora\typora-user-images\image-20211013154806304.png)



![image-20211013154825832](C:\Users\1960128653\AppData\Roaming\Typora\typora-user-images\image-20211013154825832.png)

程序运行结束后：

![image-20211013155033553](C:\Users\1960128653\AppData\Roaming\Typora\typora-user-images\image-20211013155033553.png)

![image-20211013155057133](C:\Users\1960128653\AppData\Roaming\Typora\typora-user-images\image-20211013155057133.png)

#### 编码逻辑

程序从连接远程主机，读取数据库表`user_command`获取巡检项各项参数，利用远程主机执行巡检命令处理返回，将执行结果`status`存储到数据库表`command_result`中。

已经存在的巡检项（id）再次巡检不在`command_result`表中新增记录，而是查找user_command_id为id的记录做更新。

#### 注意

* 测试环境为：jdk1.8，mysql5.7（数据库驱动为数据库8驱动）。
* 检测结果表中status为0：告警、1：正常。
* 数据库表结构见当前目录下的“数据库表结构”文件夹。
* 数据量特大或发生意外。