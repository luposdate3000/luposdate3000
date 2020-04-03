#!/bin/bash
#for i in $(ps -aux | grep "java " | sed "s/root *//g" | sed "s/ .*//g"); do kill $i;done
./generate-buildfile.kts jvm commonS00LaunchBinaryTestsMain commonS00SanityChecksOnMain commonS00ExecutionSequentialMain commonS01HeapMain commonS12DummyMain commonS14ServerNoneMain jvmS14ClientKtorTarget commonS15DistributedMain
./tool-gradle-build.sh
port="3030"
/opt/apache-jena-fuseki-3.14.0/fuseki-server --port=$port > /dev/null 2>&1 &
sleep 3
mkdir donefolder
curl -X POST --data-urlencode "dbName=sp2b" --data-urlencode "dbType=mem" -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets > /dev/null 2>&1
for f in $(ls crash-* --sort=size -r)
do
	echo $f
	cat $f | ./build/executable || break
	mv $f donefolder/
done
echo $f
