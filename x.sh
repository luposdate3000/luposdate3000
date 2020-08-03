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

export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64

partitions=12
timelimit=20
bytes=1
realtriples=4195326
folder=/mnt/luposdate-testdata/lupos_4T1T1024/4194304

./log/benchtmp/Multi_BPlusTree_Empty.x \
 IMPORT_INTERMEDIATE \
 $folder/data \
 $folder/intermediate \
 resources/lupos/q6.sparql \
 $timelimit \
 $realtriples \
 $bytes \
 $folder/bnodes.txt \
 lupos \
 $partitions
