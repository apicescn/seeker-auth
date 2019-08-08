#!/bin/sh

#日志目录
LOG_DIR=/opt/logs
#新建日志目录
if [ ! -d ${LOG_DIR} ];then
  mkdir ${LOG_DIR}
fi
#预留1204m，单位M
RESERVED_MEGABYTES=1024
# 获取容器限制的内存数
limit_in_bytes=$(cat /sys/fs/cgroup/memory/memory.limit_in_bytes)

# If not default limit_in_bytes in cgroup
if [ "$limit_in_bytes" -ne "9223372036854771712" ]
then
    limit_in_megabytes=$(expr $limit_in_bytes \/ 1048576)
    heap_size=$(expr $limit_in_megabytes - $RESERVED_MEGABYTES)
    export JAVA_OPTS="-server -Xmx${heap_size}m -Xms${heap_size}m  -XX:NewRatio=4 -XX:SurvivorRatio=8 $JAVA_OPTS"
fi

export JAVA_OPTS="$JAVA_OPTS -XX:+UseParNewGC -XX:CMSFullGCsBeforeCompaction=9 -XX:+CMSParallelRemarkEnabled -XX:CMSInitiatingOccupancyFraction=60"
export JAVA_OPTS="$JAVA_OPTS -XX:+CMSClassUnloadingEnabled -XX:+UseCMSCompactAtFullCollection"
export JAVA_OPTS="$JAVA_OPTS -XX:+UseCMSInitiatingOccupancyOnly -XX:ParallelGCThreads=4 -XX:MaxTenuringThreshold=9 -XX:+UseConcMarkSweepGC"
export JAVA_OPTS="$JAVA_OPTS -XX:+DisableExplicitGC -XX:+ScavengeBeforeFullGC -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+ExplicitGCInvokesConcurrent"
export JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC -XX:+HeapDumpOnOutOfMemoryError -XX:-OmitStackTraceInFastThrow"
export JAVA_OPTS="$JAVA_OPTS -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=30m -verbose:gc -Xloggc:$LOG_DIR/heap_trace_%p.log"
export JAVA_OPTS="$JAVA_OPTS -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp -XX:ErrorFile=/tmp/hs_err_pid<pid>.log"
#-Djava.security.egd为JAVA中的配置发生器,file:/dev/./urandom为Linux要表示urandom的路径,也就是/dev/urandom文件
export JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom"

echo "JAVA_OPTS="$JAVA_OPTS

printf "$(date) ==== Starting ==== \n"

java ${JAVA_OPTS} -jar app.jar

rc=$?;

if [ $rc != 0 ];
then
    echo "$(date) Failed to start, return code: $rc"
    exit $rc;
fi

tail -f /dev/null