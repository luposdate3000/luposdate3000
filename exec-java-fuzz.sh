#!/bin/bash
mount /opt/tmpdata javafuzz
kotlinc -script generate-buildfile.kts jvm jvmS00LaunchJavaFuzzMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS12DummyMain commonS14ServerNoneMain jvmS14ClientKtorTarget commonS15DistributedMain
./tool-gradle-build.sh
buildfile="build.gradle.kts"
output1=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/build_-" | sed "s/\".*//g")
kotlinc -script generate-buildfile.kts jvm jvmS00LaunchWarnkeFuzzMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS12DummyMain commonS14ServerNoneMain jvmS14ClientKtorTarget commonS15DistributedMain
./tool-gradle-build.sh
buildfile="build.gradle.kts"
output2=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/build_-" | sed "s/\".*//g")
port="3030"
/opt/apache-jena-fuseki-3.14.0/fuseki-server --port=$port > /dev/null 2>&1 &
for db in $(seq 1 11)
do
	mkdir javafuzz/db${db}
	size=$(echo "scale=0;(12*1.6^${db})/1" | bc)
	for i in $(seq 0 100)
	do
		dd if=/dev/urandom of=javafuzz/db${db}/input$i bs=$size count=1 > /dev/null 2>&1
	done
	curl -X POST --data-urlencode "dbName=db${db}" --data-urlencode "dbType=mem" -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets > /dev/null 2>&1
	(
		while true
		do
			if [ "$db" == 1 ]
			then
				java -javaagent:dependencies/jacocoagent.jar=destfile=jacoco.exec -cp "$(pwd)/${output1}/distributions/luposdate3000/lib/*" MainKt "db${db}"
			else
				java -cp "$(pwd)/${output2}/distributions/luposdate3000/lib/*" MainKt "db${db}" $size
			fi
		done
	)  >> log/db$db 2>&1 &
done
wait
