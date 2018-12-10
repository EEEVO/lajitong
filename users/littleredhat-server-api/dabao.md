# 1、上传打包

1、本地编译mvn clean package 
2. 拷贝targe/littleredhat-server-api-0.1-SNAPSHOT.jar  到服务器/root/deploy/redhat/backend-api 
3. ssh远程登录到服务器47.100.163.14 
4. cd /root/deploy/redhat/backend-api 
5. ps -ef | grep java 
6. 找到后台服务的进程号  
7. kill -9 进程号 
8. 后台启动服务 nohup java -jar littleredhat-server-api-0.1-SNAPSHOT.jar > /dev/null 2>1& 
或者nohup java -jar littleredhat-server-api-0.1-SNAPSHOT.jar 2>1&
9. 查看日志 tail -f logs/lajitong-server-backend.log



# 2、数据库
47.100.163.14

数据库 redhat  用户 redhat/redhat2018

远程 用户 root / 
