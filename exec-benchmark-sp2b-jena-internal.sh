#!/bin/bash

#in milliseconds
timemin=60000
#in seconds
timeout=300
triples=1000

./generate-buildfile.kts jvm commonS00LaunchEndpointMain commonS00SanityChecksOffMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain commonS01HeapMain commonS03DictionaryIntArrayMain commonS12DummyMain jvmS14ServerKorioMain commonS14ClientNoneMain commonS15LocalMain jvmS00WrapperJenaOnMain
./tool-gradle-build.sh

p=$(pwd)/benchmark_results/sp2b
mkdir -p $p
rm log/queries2

find resources/sp2b/ -name "*.sparql" > log/queries
csvfile=$p/jena-internal.csv
while true
do
triplesfile=$p/sp2b-${triples}.n3
(
	cd /opt/sp2b/bin
	# t parameter specifies amount of tupels
	./sp2b_gen -t $triples > /dev/null 2>&1
)
cat /opt/sp2b/bin/sp2b.n3 | sed "s/\.$/ ./g" > "$triplesfile"
size=$(du -sb $triplesfile | sed -E "s/([0-9]+).*/\1/g")
echo $triples $size $(bc <<< "scale=4; $size / $triples")
pkill java
sleep 3
(./build/executable 127.0.0.1 > log/server 2>&1)&
sleep 3
a=$(($(date +%s%N)/1000000))
curl -X GET http://localhost:80/jena/turtle?query="$triplesfile" --header "Content-Type:text/plain" > /dev/null 2>&1
code=$?
b=$(($(date +%s%N)/1000000))
c=$((b - a))
qps=$(bc <<< "scale=2; 1000 / $c")
echo "resources/sp2b/load.sparql,$triples,$code,1,$c,$qps" >> $csvfile
curl -X POST http://localhost:80/stacktrace > /dev/null 2>&1
while read query; do
	res=$(timeout -s SIGTERM "${timeout}s" curl -X POST --data-binary "@$query" http://localhost:80/jena/benchmark?timeout=$timemin)
	code=$?
echo $res
c=$(echo $res| sed 's/<benchmark time="//g' | sed 's/" count=".*//g')
n=$(echo $res| sed 's/.*" count="//g' | sed 's/".*//g')
c1=$(bc <<< "scale=2;$c * 1000")
echo $n -- $c1
	qps=$(bc <<< "scale=2;$n / $c")
# code -> return code
# c1   -> time used in seconds
# qps  -> query per second
# size -> raw uncompressed triple size
# n    -> repetition count
	echo "$query,$triples,$code,$n,$c1,$qps,$size" >> $csvfile
	if [[ "$code" == "0" ]]
	then
		if [[ "$n" != "0" ]]
		then
			echo $query >> log/queries2
		fi
	fi
	echo $f >> log/server
	curl -X POST http://localhost:80/stacktrace > /dev/null 2>&1
	echo "$query"
done < log/queries
mv log/queries2 log/queries
triples=$(($triples * 2))
if [[ $triples -le 0 ]]
then
break
fi
done
