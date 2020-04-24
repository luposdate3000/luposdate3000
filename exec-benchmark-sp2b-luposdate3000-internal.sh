#!/bin/bash

#in seconds
timemin=10

triples=1024

rm -rf log/benchtmp
mkdir -p log/benchtmp

./generate-buildfile.kts jvm Benchmark Off Fast Sequential Heap MapMapList Dummy Korio None Local Off BinaryTree None
./tool-gradle-build.sh
ln -s $(readlink -f build/executable) log/benchtmp/MapMap_BinaryTree.x
./generate-buildfile.kts jvm Benchmark Off Fast Sequential Heap SingleList Dummy Korio None Local Off BinaryTree None
./tool-gradle-build.sh
ln -s $(readlink -f build/executable) log/benchtmp/Single_BinaryTree.x
./generate-buildfile.kts jvm Benchmark Off Fast Sequential Heap SingleList Dummy Korio None Local Off HashMap None
./tool-gradle-build.sh
ln -s $(readlink -f build/executable) log/benchtmp/Single_HashMap.x


p=$(pwd)/benchmark_results/sp2b
mkdir -p $p


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
for version in "MapMap_BinaryTree" "Single_BinaryTree" "Single_HashMap"
do
	cp log/queries log/benchtmp/$version.queries
done
csvfile=$p/luposdate-$(git rev-parse HEAD)-internal.csv
while true
do
	triplesfolder=/mnt/sp2b-testdata/${triples}
	size=$(du -sbc ${triplesfolder}/*.n3 | grep total | sed 's/\t.*//g')
	for version in "MapMap_BinaryTree" "Single_BinaryTree" "Single_HashMap"
	do
		if true
		then
			./log/benchtmp/$version.x LOAD "$triplesfolder/data" "" $(paste -s -d ';' log/benchtmp/$version.queries) 10 $triples $size > log/benchtmp/x
		else
			./log/benchtmp/$version.x IMPORT "$triplesfolder/data" $(find $triplesfolder/*.n3 | paste -s -d ';') $(paste -s -d ';' log/benchtmp/$version.queries) 10 $triples $size > log/benchtmp/x
		fi
		cat log/benchtmp/x | grep "sparql,$triples," | sed "s/\$/,$version/g" >> $csvfile
		cat log/benchtmp/x | grep "sparql,1024," | grep -v "sparql,1024,0,.," | sed "s/,.*//" > log/benchtmp/$version.queries
	done
	triples=$(($triples * 2))
	if [[ $triples -le 0 ]]
	then
		break
	fi
done
rm benchtmp
