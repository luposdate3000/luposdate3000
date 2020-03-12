#!/bin/bash
#for i in $(ps -aux | grep "java " | sed "s/root *//g" | sed "s/ .*//g"); do kill $i;done
kotlinc -script generate-buildfile.kts jvm commonS00LaunchBinaryTestsMain commonS00SanityChecksOnMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS12DummyMain commonS14ServerNoneMain jvmS14ClientKtorTarget commonS15DistributedMain
./tool-gradle-build.sh
mkdir donefolder
curl -X POST --data-urlencode "dbName=sp2b" --data-urlencode "dbType=mem" -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets > /dev/null 2>&1
for f in crash-*
do
	echo $f
	cat $f | ./build/executable || break
	mv $f donefolder/
done
