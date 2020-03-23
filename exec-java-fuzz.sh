#!/bin/bash
rm -rf jacoco* log/db* nohup.out run javafuzz donefolder
#./generate-buildfile.kts jvm jvmS00LaunchJavaFuzzMain commonS00SanityChecksOnMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS12DummyMain commonS14ServerNoneMain jvmS14ClientKtorTarget commonS15DistributedMain
#./tool-gradle-build.sh
buildfile="build.gradle.kts"
output1=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/build_-" | sed "s/\".*//g")
./generate-buildfile.kts jvm jvmS00LaunchWarnkeFuzzMain commonS00SanityChecksOnMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS12DummyMain commonS14ServerNoneMain jvmS14ClientKtorTarget commonS15DistributedMain
./tool-gradle-build.sh
buildfile="build.gradle.kts"
output2=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/build_-" | sed "s/\".*//g")
port="3030"
/opt/apache-jena-fuseki-3.14.0/fuseki-server --port=$port > /dev/null 2>&1 &
sleep 3
for db in $(seq 1 4)
do
	size=$(echo "scale=0;(12*1.6^${db})/1" | bc)
	(
		curl -X POST --data-urlencode "dbName=db${db}" --data-urlencode "dbType=mem" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets
		curl -X GET -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets
		while true
		do
			timeout -s SIGTERM "2m" java -cp "$(pwd)/${output2}/distributions/luposdate3000/lib/*" MainKt "db${db}" $size 0
		done
	) >> log/db$db 2>&1 &
done
for db in $(seq 5 8)
do
	size=$(echo "scale=0;(12*1.6^${db})/1" | bc)
	(
		curl -X POST --data-urlencode "dbName=db${db}" --data-urlencode "dbType=mem" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets
		curl -X GET -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets
		while true
		do
			timeout -s SIGTERM "2m" java -cp "$(pwd)/${output2}/distributions/luposdate3000/lib/*" MainKt "db${db}" $size 1
		done
	) >> log/db$db 2>&1 &
done
for db in $(seq 9 12)
do
	size=$(echo "scale=0;(12*1.6^${db})/1" | bc)
	(
		curl -X POST --data-urlencode "dbName=db${db}" --data-urlencode "dbType=mem" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets
		curl -X GET -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets
		while true
		do
			timeout -s SIGTERM "2m" java -cp "$(pwd)/${output2}/distributions/luposdate3000/lib/*" MainKt "db${db}" $size 2
		done
	) >> log/db$db 2>&1 &
done
wait
