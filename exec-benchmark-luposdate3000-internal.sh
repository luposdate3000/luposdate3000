#!/bin/bash
export JAVA_OPTS="-Xmx30g"

triples=1024

rm -rf log/benchtmp
mkdir -p log/benchtmp

{
  echo "KotlinVersion->1.4.255-SNAPSHOT"
  echo "Platform->jvm"
  echo "Launch->Benchmark"
  echo "Sanity->Off"
  echo "Execution->Sequential"
  echo "BufferManager->Heap"
  echo "Dictionary->MultiMap"
  echo "TripleStore->BPlusTree"
  echo "Endpoint->Korio"
  echo "Jena->Off"
  echo "Set->BTree"
  echo "Map->BTree"
  echo "IteratorVerbose->None"
  echo "OutputFormat->Empty"
  echo "Pagesize->8196"
  echo "BlockCapacity->1024"
  echo "BTreeBranching->512"
  echo "MergeSortRows->512"
  echo "BulkImportBlockSize->1048576"
  echo "AdvancedOptimisation->true"
  echo "Coverage->ECoverage.Disabled"
  echo "CoverageGenerate->DontChange"
  echo "ServerCommunication->None"
  echo "MaxTriplesDuringTest->-1"
} | ./generate-buildfile.kts
./tool-gradle-build.sh

ln -s $(readlink -f build/executable) log/benchtmp/Multi_BPlusTree_Empty.x
versions=( "Multi_BPlusTree_Empty" )

p=$(pwd)/benchmark_results/sp2b
mkdir -p $p

ls resources/sp2b/q*.sparql | grep -v "-" > log/queries

export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64

for version in "${versions[@]}"
do
	cp log/queries log/benchtmp/$version.queries
done
while true
do
	triplesfolder=/mnt/luposdate-testdata/sp2b/${triples}
	size=$(du -sbc ${triplesfolder}/*.n3 | grep total | sed 's/\t.*//g')
	i=0
	for version in "${versions[@]}"
	do
		queries=$(paste -s -d ';' log/benchtmp/$version.queries)
		if [ -n "$queries" ]
		then
			if [ $i -eq 0 ]
			then
				./log/benchtmp/$version.x "IMPORT" "$triplesfolder/data" "$(find $triplesfolder/*.n3 | paste -s -d ';')" "$queries" "10" "$triples" "$size" "$triplesfolder/bnodes.txt" > log/benchtmp/x
			else
				./log/benchtmp/$version.x "LOAD" "$triplesfolder/data" "" "$queries" "10" "$triples" "$size" "" > log/benchtmp/x
			fi
			cat log/benchtmp/x | grep "sparql,$triples," >> $p/luposdate-$version-$(git rev-parse HEAD)-internal.csv
			cat log/benchtmp/x | grep "sparql,$triples," | grep -v "sparql,$triples,0,.," | sed "s/,.*//" > log/benchtmp/$version.queries
			mv log/benchtmp/x log/benchtmp/x-$triples
		fi
		i=1
	done
	triples=$(($triples * 2))
	if [[ $triples -le 0 ]]
	then
		break
	fi
done
#rm -rf log/benchtmp
