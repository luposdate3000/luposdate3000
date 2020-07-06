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
 echo "IteratorVerbose->None"
 echo "OutputFormat->Empty"
 echo "Pagesize->8196"
 echo "BlockCapacity->1024"
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
} | ./generate-buildfile.kts
./tool-gradle-build.sh
ret=$?
if [ $ret -ne 0 ]
then
	exit $ret
fi
cpus=$( ls -d /sys/devices/system/cpu/cpu[[:digit:]]* | wc -w )
#find /mnt/luposdate-testdata/btc2019/data/ -name "*.n3" | xargs --max-args=1 --max-procs=$cpus --replace ./build/executable IMPORT_STRING {}
./build/executable IMPORT_STRING /mnt/luposdate-testdata/btc2019/data/data97.n3
