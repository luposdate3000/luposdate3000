#!/bin/bash
#for i in $(ps -aux | grep "java " | sed "s/root *//g" | sed "s/ .*//g"); do kill $i;done
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
port="3030"
/opt/apache-jena-fuseki-3.14.0/fuseki-server --port=$port > /dev/null 2>&1 &
sleep 3
mkdir donefolder
curl -X POST --data-urlencode "dbName=sp2b" --data-urlencode "dbType=mem" -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets > /dev/null 2>&1
export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64
for f in $(ls crash-* --sort=size -r)
do
	echo $f
	cat $f | ./build/executable || break
	mv $f donefolder/
done
echo $f
