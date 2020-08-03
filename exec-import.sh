#!/bin/bash
{
 echo "KotlinVersion->1.4.255-SNAPSHOT"
 echo "Platform->jvm"
 echo "Launch->Import"
 echo "Sanity->Off"
 echo "Execution->Sequential"
 echo "BufferManager->Heap"
 echo "Dictionary->MultiMap"
 echo "TripleStore->BPlusTree"
 echo "Endpoint->Korio"
 echo "Jena->Off"
 echo "Set->BTree"
 echo "Map->BTree"
 echo "OutputFormat->Empty"
 echo "Pagesize->8196"
 echo "BlockCapacity->1048576"
 echo "BTreeBranching->512"
 echo "MergeSortRows->512"
 echo "BulkImportBlockSize->1048576"
 echo "AdvancedOptimisation->true"
 echo "Coverage->ECoverage.Disabled"
 echo "CoverageGenerate->Off"
 echo "ServerCommunication->None"
 echo "MaxTriplesDuringTest->-1"
 echo "ConnectionPool->Off"
 echo "Inline->On"
 echo "UsePartitions->true"
 echo "IteratorDebug->EPOPDebugMode.NONE"
} | ./generate-buildfile.kts
./tool-gradle-build.sh
ret=$?
if [ $ret -ne 0 ]
then
	exit $ret
fi
cpus=$( ls -d /sys/devices/system/cpu/cpu[[:digit:]]* | wc -w )

### configure all the files here -->>
find /mnt/luposdate-testdata/sp2b/ -mindepth 1 -maxdepth 1 -type d > exec-import.sh.tmp
find /mnt/luposdate-testdata/bsbm/ -mindepth 1 -maxdepth 1 -type d >> exec-import.sh.tmp
find /mnt/luposdate-testdata/lupos*/ -mindepth 1 -maxdepth 1 -type d >> exec-import.sh.tmp
###

for directory in $(cat exec-import.sh.tmp | awk '{ print system("du -sb "$0" | sed \"s/[^0-9].*//g\" | tr -d \"\n\""), $0 }' | sort -n | cut -d" " -f2-)
do
	echo $directory
	if test -f "$directory/intermediate.stat"
	then
		echo "skip because it is done"
		continue
	fi
	rm -rf $directory/out
	find $directory -name "*.n3" | xargs --max-args=1 --max-procs=$cpus ./build/executable IMPORT_STRING
	find $directory -name "*.n3" | xargs --max-args=4 --max-procs=$cpus ./build/executable MERGE_INTERMEDIATE
	while true
	do
		remaining=$(ls -la $directory/out/ | wc -l)
		echo $remaining
		if [ "$remaining" -le "10" ]
		then
			break
		fi
		find $directory/out -name "*.n3" | xargs --max-args=4 --max-procs=$cpus ./build/executable MERGE_INTERMEDIATE
		rm $directory/out/*
		mv $directory/out/out/* $directory/out
		rm -rf $directory/out/out/
	done
	rm $directory/*.n3.triples $directory/*.n3.stat $directory/*.n3.dictionary
	mv $directory/out/out_*.n3.triples $directory/intermediate.triples
	mv $directory/out/out_*.n3.dictionary $directory/intermediate.dictionary
	mv $directory/out/out_*.n3.stat $directory/intermediate.stat
	rm -rf $directory/out
done
rm exec-import.sh.tmp
