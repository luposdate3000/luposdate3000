#!/bin/bash
ports=(8080 8081 8082 8083 8084 8085 8086 8087)
intermediates=( btc2010/btc-2010.n4 yago3/yago3.ttl yago1/yago-1.0.0-turtle.ttl yago2/yago-2.n3 yago4/yago-all.nt yago2s/yago-2.5.3-turtle-simple.ttl barton/barton.nt btc2019/btc2019-triples.nt )
storages=( btc2010 yago3 yago1 yago2 yago4 yago2s barton btc2019 )


for i in $(seq 0 7)
do
echo $i ${ports[i]} ${intermediates[i]} ${storages[i]}
port=${ports[i]}
intermediate=/mnt/luposdate-testdata/${intermediates[i]}
storage=/mnt/db/${storages[i]}/
(
rm -rf $storage
mkdir -p $storage
#./launcher.main.kts --run --inlineMode=Enable --releaseMode=Enable --garbageCollector=Shenandoah --compilerVersion=1.5.255-SNAPSHOT --Buffer_Manager=Persistent_Cached --Endpoint_Launcher=Java_Sockets --processUrls=localhost:$port --dryMode=Enable
export LUPOS_HOME=$storage
export LUPOS_PROCESS_URLS=localhost:$port
export LUPOS_THREAD_COUNT=1
export LUPOS_PARTITION_MODE=None
export LUPOS_DICTIONARY_MODE=KV
/usr/lib/jvm/java-15-openjdk-amd64/bin/java -XX:+UnlockExperimentalVMOptions -Xmx8g -XX:+UseShenandoahGC -XX:ShenandoahUncommitDelay=1000 -XX:ShenandoahGuaranteedGCInterval=10000 -cp /root/.gradle/caches/modules-2/files-2.1/com.benasher44/uuid-jvm/0.0.7/208de8dbbaa3392a3b3dac5dad317ca4ec8646a9/uuid-jvm-0.0.7.jar:/root/.gradle/caches/modules-2/files-2.1/com.soywiz.korlibs.krypto/krypto-jvm/1.9.1/4b5015389842f05ff9f7edfed791ccfc5b74dafa/krypto-jvm-1.9.1.jar:/root/.m2/repository/com/ionspin/kotlin/bignum-jvm/0.3.0-SNAPSHOT/bignum-jvm-0.3.0-SNAPSHOT.jar:/root/.m2/repository/org/jetbrains/annotations/13.0/annotations-13.0.jar:/root/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-common/1.5.255-SNAPSHOT/kotlin-stdlib-common-1.5.255-SNAPSHOT.jar:/root/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-jdk7/1.5.255-SNAPSHOT/kotlin-stdlib-jdk7-1.5.255-SNAPSHOT.jar:/root/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-jdk8/1.5.255-SNAPSHOT/kotlin-stdlib-jdk8-1.5.255-SNAPSHOT.jar:/root/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib/1.5.255-SNAPSHOT/kotlin-stdlib-1.5.255-SNAPSHOT.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Buffer_Manager_Persistent_Cached-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Dictionary-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Endpoint-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Endpoint_Launcher_Java_Sockets-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Jena_Wrapper_Off-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_KV-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Launch_Endpoint-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Operator_Arithmetik-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Operator_Base-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Operator_Factory-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Operator_Logical-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Operator_Physical-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Optimizer_Ast-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Optimizer_Distributed_Query-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Optimizer_Logical-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Optimizer_Physical-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Parser-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Result_Format-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Shared-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Test-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Triple_Store_Id_Triple-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_Triple_Store_Manager-jvm.jar:build-cache/bin_Threads_Inline_Release/Luposdate3000_VK-jvm.jar MainKt &
pid=$!
while ! nc -z localhost $port
do
sleep 0.1
done
echo "$port is open"
curl localhost:$port/import/intermediate?file=$intermediate
jmap -dump:live,format=b,file="$storage/after-import-$(date +"%Y_%m_%d_%H_%M_%S").hprof" $pid
curl localhost:$port/shutdown
)&
done

wait
