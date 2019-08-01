# 基于哪个镜像.
#FROM openjdk:8-alpine
FROM openjdk:8-slim

MAINTAINER xueying
#设置时区
ENV TZ Asia/Shanghai
# 将本地文件夹挂载到当前容器
VOLUME /tmp
# 拷贝文件到容器
ADD seeker-auth-server*.jar app.jar
# 打开服务端口
EXPOSE 7777
#-Djava.security.egd为JAVA中的配置发生器,file:/dev/./urandom为Linux要表示urandom的路径,也就是/dev/urandom文件
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
