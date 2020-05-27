#!/bin/bash
rm -rf jacoco* log/db* nohup.out run javafuzz donefolder
buildfile="build.gradle.kts"
output1=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/build_-" | sed "s/\".*//g")
{
  echo 1.4.255-SNAPSHOT
  echo jvm
  echo WarnkeFuzz
  echo On
  echo Sequential
  echo Heap
  echo MultiMap
  echo MapMapList
  echo None
  echo None
  echo On
  echo BTree
  echo BTree
  echo None
  echo Empty
  echo 128
  echo 8
  echo 8
  echo 8
  echo true
  echo ECoverage.Disabled
  echo Off
  echo None
} | ./generate-buildfile.kts
./tool-gradle-build.sh
buildfile="build.gradle.kts"
output2=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/build_-" | sed "s/\".*//g")
port="3030"
/opt/apache-jena-fuseki-3.14.0/fuseki-server --port=$port > /dev/null 2>&1 &
sleep 3
for db in $(seq 1 12)
do
	size=$(echo "scale=0;(12*1.6^${db})/1" | bc)
	(
		curl -X POST --data-urlencode "dbName=db${db}" --data-urlencode "dbType=mem" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets
		curl -X GET -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets
		while true
		do
			timeout --kill-after="10s" --verbose --signal=TERM "30s" java -cp "$(pwd)/${output2}/distributions/luposdate3000/lib/*" MainKt "db${db}" $size 0
		done
	) >> log/db$db 2>&1 &
done
wait
