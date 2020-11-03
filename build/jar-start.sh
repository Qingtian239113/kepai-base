#!/bin/sh

# 启动端口
START_PORT=8787

# 查询端口是否被占用，如果被占用则关闭该端口
echo 'search PID ...'
ID=`ps -ef | grep $START_PORT | grep -v "grep" | awk '{print $2}'`
echo 'PID is' $ID
for id in $ID
do
kill -9 $id
done

# 找到最新的jar，然后启动jar
jarFile=`find . -name '*.jar' | sort -rf | head -n 1`
echo 'start jar with ' $jarFile
nohup java -jar $jarFile --server.port=$START_PORT --spring.profiles.active=dev > ./log.file 2>&1 &
