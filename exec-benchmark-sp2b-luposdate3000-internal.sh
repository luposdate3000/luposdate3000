#!/bin/bash

#in milliseconds
timemin=10000
#in seconds
timeout=120
triples=1024

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
csvfile=$p/luposdate-$(git rev-parse HEAD)-internal.csv
while true
do
triplesfolder=/mnt/sp2b-testdata/${triples}
size=$(du -sb $triplesfolder | sed 's/\t.*//g')
pkill java
sleep 3
(./build/executable 127.0.0.1 > log/server 2>&1)&
sleep 3


a=$(($(date +%s%N)/1000000))
curl -X POST --data-binary "$triplesfolder/data" http://localhost:80/persistence/load --header "Content-Type:text/plain"
b=$(($(date +%s%N)/1000000))
c=$((b - a))
qps=$(bc <<< "scale=2; 1000 / $c")
echo "resources/sp2b/persistence-load.sparql,$triples,$code,1,$c,$qps,$size" >> $csvfile
triples=$(($triples * 2))
continue

a=$(($(date +%s%N)/1000000))
for f in $(find $triplesfolder/*.n3)
do
curl -X POST --data-binary "@$f" http://localhost:80/import/turtle --header "Content-Type:text/plain"
done
mkdir -p $triplesfolder/data
code=$?
b=$(($(date +%s%N)/1000000))
c=$((b - a))
qps=$(bc <<< "scale=2; 1000 / $c")
echo "resources/sp2b/load.sparql,$triples,$code,1,$c,$qps,$size" >> $csvfile
a=$(($(date +%s%N)/1000000))
curl -X POST --data-binary "$triplesfolder/data" http://localhost:80/persistence/store --header "Content-Type:text/plain"
b=$(($(date +%s%N)/1000000))
c=$((b - a))
qps=$(bc <<< "scale=2; 1000 / $c")
echo "resources/sp2b/persistence-save.sparql,$triples,$code,1,$c,$qps,$size" >> $csvfile



while read query; do
	res=$(timeout -s SIGTERM "${timeout}s" curl -X POST --data-binary "@$query" http://localhost:80/sparql/benchmark?timeout=$timemin)
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
	echo "$query,$triples,$code,$n,$c1,$qps,$size" >> $csvfile
	if [[ "$code" == "0" ]]
	then
		if [[ "$n" -gt "1" ]]
		then
			echo $query >> log/queries2
		fi
	fi
	echo $f >> log/server
	echo "$query"
done < log/queries
mv log/queries2 log/queries
triples=$(($triples * 2))
if [[ $triples -le 0 ]]
then
break
fi
done
