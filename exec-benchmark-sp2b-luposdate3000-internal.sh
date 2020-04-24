#!/bin/bash

#in seconds
timemin=10

triples=1024

rm -rf log/benchtmp
mkdir -p log/benchtmp

./generate-buildfile.kts jvm Benchmark Off Fast Sequential Heap MapMapList Dummy Korio None Local Off BinaryTree None Empty
./tool-gradle-build.sh
ln -s $(readlink -f build/executable) log/benchtmp/MapMap_BinaryTree_Empty.x
./generate-buildfile.kts jvm Benchmark Off Fast Sequential Heap SingleList Dummy Korio None Local Off BinaryTree None Empty
./tool-gradle-build.sh
ln -s $(readlink -f build/executable) log/benchtmp/Single_BinaryTree_Empty.x
./generate-buildfile.kts jvm Benchmark Off Fast Sequential Heap SingleList Dummy Korio None Local Off HashMap None Empty
./tool-gradle-build.sh
ln -s $(readlink -f build/executable) log/benchtmp/Single_HashMap_Empty.x
./generate-buildfile.kts jvm Benchmark Off Fast Sequential Heap SingleList Dummy Korio None Local Off HashMap None EmptyWithDictionary
./tool-gradle-build.sh
ln -s $(readlink -f build/executable) log/benchtmp/Single_HashMap_EmptyWithDictionary.x
./generate-buildfile.kts jvm Benchmark Off Fast Sequential Heap SingleList Dummy Korio None Local Off HashMap None XML
./tool-gradle-build.sh
ln -s $(readlink -f build/executable) log/benchtmp/Single_HashMap_XML.x


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
for version in "MapMap_BinaryTree_Empty" "Single_BinaryTree_Empty" "Single_HashMap_Empty" "Single_HashMap_EmptyWithDictionary" "Single_HashMap_XML"
do
	cp log/queries log/benchtmp/$version.queries
done
while true
do
	triplesfolder=/mnt/sp2b-testdata/${triples}
	size=$(du -sbc ${triplesfolder}/*.n3 | grep total | sed 's/\t.*//g')
	for version in "MapMap_BinaryTree_Empty" "Single_BinaryTree_Empty" "Single_HashMap_Empty" "Single_HashMap_EmptyWithDictionary" "Single_HashMap_XML"
	do
		queries=$(paste -s -d ';' log/benchtmp/$version.queries)
		if [ -n "$queries" ]
		then
			if true
			then
				./log/benchtmp/$version.x "LOAD" "$triplesfolder/data" "" "$queries" "10" "$triples" "$size" > log/benchtmp/x
			else
				./log/benchtmp/$version.x "IMPORT" "$triplesfolder/data" "$(find $triplesfolder/*.n3 | paste -s -d ';')" "$queries" "10" "$triples" "$size" > log/benchtmp/x
			fi
			cat log/benchtmp/x | grep "sparql,$triples," >> $p/luposdate-$version-$(git rev-parse HEAD)-internal.csv
			cat log/benchtmp/x | grep "sparql,$triples," | grep -v "sparql,$triples,0,.," | sed "s/,.*//" > log/benchtmp/$version.queries
		fi
	done
	triples=$(($triples * 2))
	if [[ $triples -le 0 ]]
	then
		break
	fi
done
rm -rf log/benchtmp
