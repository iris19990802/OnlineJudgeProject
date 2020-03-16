# Base镜像：java8（open jdk）
FROM openjdk:8u242-jdk-slim

# 安装gcc和g++环境（因为要跑C代码）
RUN apt-get update && apt-get install -y gcc && apt-get install g++

# 构建docker镜像内的目录结构
RUN mkdir /app && mkdir /app/script && mkdir /app/workdir
RUN  mkdir /app/workdir/default
RUN  touch /app/workdir/default/default-input.txt
RUN  touch  /app/workdir/default/default-output.txt

WORKDIR /app

# 把jar包和c脚本拷入镜像
COPY target/onlinejudge.judger-1.0-SNAPSHOT.jar .
COPY judgecore.c ./script

# 编译c脚本
RUN gcc -o ./script/judgecore.c ./script/judgecore

# springboot 运行在容器内8088端口上 （创建容器时映射）
EXPOSE 8088

# 运行 jar 包
CMD ["java", "-jar", 'onlinejudge.judger-1.0-SNAPSHOT.jar']
