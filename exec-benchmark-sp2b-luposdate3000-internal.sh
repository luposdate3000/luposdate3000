#!/bin/bash

#in seconds
timemin=10
#in seconds
timeout=120
triples=1024

./generate-buildfile.kts jvm Benchmark Off Fast Sequential Heap MapMapList Dummy Korio none Local Off
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
	size=$(du -sbc ${triplesfolder}/*.n3 | grep total | sed 's/\t.*//g')
	if true
	then
		./build/executable LOAD "$triplesfolder/data" "" $(paste -s -d ';' log/queries) 10 $triples $size > benchtmp
	else
		./build/executable IMPORT "$triplesfolder/data" $(find $triplesfolder/*.n3 | paste -s -d ';') $(paste -s -d ';' log/queries) 10 $triples $size > benchtmp
	fi
	cat benchtmp | grep "sparql,$triples," >> $csvfile
	cat benchtmp | grep "sparql,1024," | grep -v "sparql,1024,0,.," | sed "s/,.*//" > log/queries
	triples=$(($triples * 2))
	if [[ $triples -le 0 ]]
	then
		break
	fi
done
