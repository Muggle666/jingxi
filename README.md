# 京西项目 #


## 开发环境： ##
开发工具：STS-3.8.3

数据库：MySQL 5.6

应用服务器：Tomcat -9.0

数据缓存：Redis 3.2.1
Web服务器：Nginx 1.8.0


## 技术实现： ##
本平台采用目前流行的SpringMVC+Mybatis框架，使用MAVEN来构建，采用三层架构的模式，界面层使用SpringMVC控制结合JSP作为界面展示，数据访问层使用Mybatis框架映射MySQL数据库来提供数据存储服务，使用Redis处理Session共享，以应对大规模的用户量的并发，使用GIT进行版本控制。

Spring、SpringMVC、Mybatis

JSP、JSTL、jQuery、jQuery plugin、EasyUI、KindEditor（富文本编辑器）、CSS+DIV

Redis（缓存服务器）

httpclient（调用系统服务）

Mysql

Nginx（web服务器）