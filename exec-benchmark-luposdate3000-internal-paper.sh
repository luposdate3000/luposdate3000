#!/bin/bash
export JAVA_OPTS="-Xmx60g"


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
  echo "TripleStore->BPlusTreePartition"
  echo "Endpoint->Korio"
  echo "Jena->Off"
  echo "Set->BTree"
  echo "Map->BTree"
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
  echo "ConnectionPool->Off"
  echo "Inline->On"
  echo "UsePartitions->true"
  echo "IteratorDebug->EPOPDebugMode.NONE"
} | ./generate-buildfile.kts
./tool-gradle-build.sh

ln -s $(readlink -f build/executable) log/benchtmp/Multi_BPlusTree_Empty.x
versions=( "Multi_BPlusTree_Empty" )

ls resources/lupos/q*.sparql | grep -v "-" > log/queries-lupos
#echo resources/lupos/q6.sparql > log/queries-lupos

export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64

for variant in 4T1T1024 4T1T0 4T1T1 4T1T128 4T1T32
do
#	for partitions in $(seq 1 12)
#	for partitions in 12 1 6 2 3 4 5 7 8 9 10 11
	for partitions in 12 1 6
	do
		for version in "${versions[@]}"
		do
			cp log/queries-lupos log/benchtmp/$version.lupos.queries
		done
		triples=1024
		while true
		do
			plupos=$(pwd)/benchmark_results/lupos/v_${variant}_${partitions}P
			mkdir -p $plupos
			triplesfolder=/mnt/luposdate-testdata/lupos_${variant}/${triples}
			if $( ls ${triplesfolder}/*.n3 2>&1 | grep -q "cannot access")
			then
				break
			fi
			size=$(du -sbc ${triplesfolder}/*.n3 | grep total | sed 's/\t.*//g')
			for version in "${versions[@]}"
			do
				queries=$(paste -s -d ';' log/benchtmp/$version.lupos.queries)
				if [ -n "$queries" ]
				then
					export LUPOS_HOME=$triplesfolder/data
					./log/benchtmp/$version.x "IMPORT_INTERMEDIATE" "$triplesfolder/data" "$triplesfolder/intermediate" "$queries" "4" "$triples" "$size" "$triplesfolder/bnodes.txt" "lupos" "$partitions" > log/benchtmp/x
					cat log/benchtmp/x
					cat log/benchtmp/x | grep "sparql,$triples," >> $plupos/luposdate3000-$version-$(git rev-parse HEAD)-internal.csv
					cat log/benchtmp/x | grep "sparql,$triples," | grep -v "sparql,$triples,0,.," | sed "s/,.*//" > log/benchtmp/$version.lupos.queries
				fi
			done
			triples=$(($triples * 2))
			if [[ $triples -le 0 ]]
			then
				break
			fi
		done
	done
done
#rm -rf log/benchtmp
