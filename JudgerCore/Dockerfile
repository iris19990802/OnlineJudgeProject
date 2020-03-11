FROM openjdk:8u242-jdk-slim


RUN apt-get update && apt-get install -y gcc

RUN mkdir /app && mkdir /app/script && mkdir /app/workdir

RUN  mkdir /app/workdir/default
RUN  touch /app/workdir/default/default-input.txt
RUN  touch  /app/workdir/default/default-output.txt

WORKDIR /app

COPY onlinejudge.judger-1.0-SNAPSHOT.jar .
COPY judgecore.c ./script

RUN gcc -o ./script/judgecore.c ./script/judgecore

EXPOSE 8088

CMD ["java", "-jar", 'onlinejudge.judger-1.0-SNAPSHOT.jar']
