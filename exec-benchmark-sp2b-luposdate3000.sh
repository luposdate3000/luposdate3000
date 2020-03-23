#!/bin/bash

#in milliseconds
timemin=10000
#in seconds
timeout=120
triples=1000

./generate-buildfile.kts jvm commonS00LaunchEndpointMain commonS00SanityChecksOffMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain commonS00TraceOnMain commonS01HeapMain commonS03DictionaryIntArrayMain commonS12DummyMain jvmS14ServerKorioMain commonS14ClientNoneMain commonS15DistributedMain
./tool-gradle-build.sh

p=$(pwd)/benchmark_results/sp2b
mkdir -p $p
rm log/queries2
find resources/sp2b/ -name "*.sparql" > log/queries
while true
do
triplesfile=$p/sp2b-${triples}.n3
csvfile=$p/luposdate.csv
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
curl -X POST http://localhost:80/stacktrace > /dev/null 2>&1
while read query; do
	a=$(($(date +%s%N)/1000000))
	n=1
	while true
	do
		timeout -s SIGTERM "${timeout}s" curl -X POST --data-binary "@$f" http://localhost:80/sparql/query > /dev/null 2>&1
		code=$?
		b=$(($(date +%s%N)/1000000))
		c=$((b - a))
		if((c > timemin))
		then
			qps=$(bc <<< "scale=2;$n * 1000 / $c")
			echo "$query,$triples,$code,$n,$c,${qps}" >> $csvfile
			if [[ "$code" == "0" ]]
			then
				echo $query >> log/queries2
			fi
			break
		fi
		n=$((n + 1))
	done
	echo $f >> log/server
curl -X POST http://localhost:80/stacktrace > /dev/null 2>&1
	echo "$query"
done < log/queries
mv log/queries2 log/queries
triples=$(($triples * 2))
done
