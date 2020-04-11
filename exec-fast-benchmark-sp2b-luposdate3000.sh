#!/bin/bash

#in seconds
timeout=120
triples=64000
query=resources/sp2b/q3a.sparql

./generate-buildfile.kts jvm commonS00LaunchEndpointMain commonS00SanityChecksOffMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain commonS01HeapMain commonS03DictionaryIntArrayMain commonS12DummyMain jvmS14ServerKorioMain commonS14ClientNoneMain commonS15LocalMain commonS00WrapperJenaOffMain
./tool-gradle-build.sh
p=$(pwd)/benchmark_results/sp2b
mkdir -p $p
rm log/queries2
#find resources/sp2b/ -name "*.sparql" > log/queries
echo "resources/sp2b/q1.sparql" > log/queries
echo "resources/sp2b/q3a.sparql" >> log/queries
echo "resources/sp2b/q3b.sparql" >> log/queries
echo "resources/sp2b/q3c.sparql" >> log/queries
echo "resources/sp2b/q6.sparql" >> log/queries
echo "resources/sp2b/q10.sparql" >> log/queries
echo "resources/sp2b/q12a.sparql" >> log/queries
echo "resources/sp2b/q12b.sparql" >> log/queries
echo "resources/sp2b/q12c.sparql" >> log/queries
triplesfile=$p/sp2b-${triples}.n3
(
	cd /opt/sp2b/bin
	# t parameter specifies amount of tupels
	./sp2b_gen -t $triples > /dev/null 2>&1
)
cat /opt/sp2b/bin/sp2b.n3 | sed "s/\.$/ ./g" > "$triplesfile"
pkill java
sleep 3
(./build/executable 127.0.0.1 > log/server 2>&1)&
sleep 3

curl -X POST --data-binary "@${triplesfile}" http://localhost:80/import/turtle --header "Content-Type:text/plain" > /dev/null 2>&1



pkill java
exit



curl -X POST http://localhost:80/stacktrace > /dev/null 2>&1
a=$(($(date +%s%N)/1000000))
timeout -s SIGTERM "${timeout}s" curl -X POST --data-binary "@$query" http://localhost:80/sparql/query > /dev/null 2>&1
code=$?
b=$(($(date +%s%N)/1000000))
c=$((b - a))
qps=$(bc <<< "scale=2;1000 / $c")
echo "$code,$c,$qps"
curl -X POST http://localhost:80/stacktrace > /dev/null 2>&1
