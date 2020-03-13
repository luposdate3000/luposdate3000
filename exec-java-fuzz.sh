#!/bin/bash
mount /opt/tmpdata javafuzz
kotlinc -script generate-buildfile.kts jvm jvmS00LaunchJavaFuzzMain commonS00SanityChecksOnMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS12DummyMain commonS14ServerNoneMain jvmS14ClientKtorTarget commonS15DistributedMain
./tool-gradle-build.sh
buildfile="build.gradle.kts"
output1=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/build_-" | sed "s/\".*//g")
kotlinc -script generate-buildfile.kts jvm jvmS00LaunchWarnkeFuzzMain commonS00SanityChecksOnMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS12DummyMain commonS14ServerNoneMain jvmS14ClientKtorTarget commonS15DistributedMain
./tool-gradle-build.sh
buildfile="build.gradle.kts"
output2=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/build_-" | sed "s/\".*//g")
port="3030"
/opt/apache-jena-fuseki-3.14.0/fuseki-server --port=$port > /dev/null 2>&1 &
for db in $(seq 1 11)
do
	size=$(echo "scale=0;(12*1.6^${db})/1" | bc)
	(
		curl -X POST --data-urlencode "dbName=db${db}" --data-urlencode "dbType=mem" -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets
		curl -X GET  -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets
		while true
		do
			java -javaagent:dependencies/jacocoagent.jar=destfile=jacoco.${db}.exec -cp "$(pwd)/${output2}/distributions/luposdate3000/lib/*" MainKt "db${db}" $size 1
		done
	)  >> log/db$db 2>&1 &
done
wait
