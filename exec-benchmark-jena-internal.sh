#!/bin/bash
export JAVA_OPTS="-Xmx60g"

triples=1024
products=1

rm -rf log/benchtmp
mkdir -p log/benchtmp

{
  echo "KotlinVersion->1.4.255-SNAPSHOT"
  echo "Platform->jvm"
  echo "Launch->BenchmarkJena"
  echo "Sanity->Off"
  echo "Execution->Sequential"
  echo "BufferManager->Heap"
  echo "Dictionary->MultiMap"
  echo "TripleStore->BPlusTree"
  echo "Endpoint->Korio"
  echo "Jena->On"
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
  echo "ConnectionPool->Off"
  echo "Inline->On"
} | ./generate-buildfile.kts
./tool-gradle-build.sh

ln -s $(readlink -f build/executable) log/benchtmp/Multi_BPlusTree_Empty.x
versions=( "Multi_BPlusTree_Empty" )

psp2b=$(pwd)/benchmark_results/sp2b
mkdir -p $psp2b
pbsbm=$(pwd)/benchmark_results/bsbm
mkdir -p $pbsbm

ls resources/sp2b/q*.sparql | grep -v "-" > log/queries-sp2b
touch log/queries-bsbm
truncate -s0 log/queries-bsbm
echo "resources/bsbm/bi_query1-1853.sparql" >> log/queries-bsbm
echo "resources/bsbm/bi_query2-1853.sparql" >> log/queries-bsbm
echo "resources/bsbm/bi_query3-1853.sparql" >> log/queries-bsbm
echo "resources/bsbm/bi_query5-1853.sparql" >> log/queries-bsbm
echo "resources/bsbm/bi_query6-1853.sparql" >> log/queries-bsbm
echo "resources/bsbm/bi_query7-1853.sparql" >> log/queries-bsbm
echo "resources/bsbm/explore_query10-1853.sparql" >> log/queries-bsbm
echo "resources/bsbm/explore_query11-1853.sparql" >> log/queries-bsbm
echo "resources/bsbm/explore_query1-1853.sparql" >> log/queries-bsbm
echo "resources/bsbm/explore_query12-1853.sparql" >> log/queries-bsbm
echo "resources/bsbm/explore_query3-1853.sparql" >> log/queries-bsbm
echo "resources/bsbm/explore_query4-1853.sparql" >> log/queries-bsbm
echo "resources/bsbm/explore_query5-1853.sparql" >> log/queries-bsbm

export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64

for version in "${versions[@]}"
do
	cp log/queries-sp2b log/benchtmp/$version.sp2b.queries
	cp log/queries-bsbm log/benchtmp/$version.bsbm.queries
done
while true
do
	triplesfolder=/mnt/luposdate-testdata/sp2b/${triples}
	size=$(du -sbc ${triplesfolder}/*.n3 | grep total | sed 's/\t.*//g')
	i=0
	for version in "${versions[@]}"
	do
		queries=$(paste -s -d ';' log/benchtmp/$version.sp2b.queries)
		if [ -n "$queries" ]
		then
			if [ $i -eq 0 ]
			then
				./log/benchtmp/$version.x "IMPORT" "$triplesfolder/data" "$(find $triplesfolder/*.n3 | paste -s -d ';')" "$queries" "10" "$triples" "$size" "$triplesfolder/bnodes.txt" "sp2b" > log/benchtmp/x
			else
				./log/benchtmp/$version.x "LOAD" "$triplesfolder/data" "" "$queries" "10" "$triples" "$size" "" "sp2b" > log/benchtmp/x
			fi
			cat log/benchtmp/x
			cat log/benchtmp/x | grep "sparql,$triples," >> $psp2b/jena-$version-$(git rev-parse HEAD)-internal.csv
			cat log/benchtmp/x | grep "sparql,$triples," | grep -v "sparql,$triples,0,.," | sed "s/,.*//" > log/benchtmp/$version.sp2b.queries
		fi
		i=1
	done
	triples=$(($triples * 2))
	if [[ $triples -le 0 ]]
	then
		break
	fi
	productsfolder=/mnt/luposdate-testdata/bsbm/${products}
	size=$(du -sbc ${productsfolder}/*.n3 | grep total | sed 's/\t.*//g')
	i=0
	for version in "${versions[@]}"
	do
		queries=$(paste -s -d ';' log/benchtmp/$version.bsbm.queries)
		if [ -n "$queries" ]
		then
			if [ $i -eq 0 ]
			then
				./log/benchtmp/$version.x "IMPORT" "$productsfolder/data" "$(find $productsfolder/*.n3 | paste -s -d ';')" "$queries" "10" "$products" "$size" "$productsfolder/bnodes.txt" "bsbm" > log/benchtmp/x
			else
				./log/benchtmp/$version.x "LOAD" "$productsfolder/data" "" "$queries" "10" "$products" "$size" "" "bsbm" > log/benchtmp/x
			fi
			cat log/benchtmp/x
			cat log/benchtmp/x | grep "sparql,$products," >> $pbsbm/jena-$version-$(git rev-parse HEAD)-internal.csv
			cat log/benchtmp/x | grep "sparql,$products," | grep -v "sparql,$products,0,.," | sed "s/,.*//" > log/benchtmp/$version.bsbm.queries
		fi
		i=1
	done
	products=$(($products * 2))
	if [[ $products -le 0 ]]
	then
		break
	fi
done
#rm -rf log/benchtmp
