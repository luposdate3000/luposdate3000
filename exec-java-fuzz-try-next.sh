#!/bin/bash
#for i in $(ps -aux | grep "java " | sed "s/root *//g" | sed "s/ .*//g"); do kill $i;done
{
  echo "KotlinVersion->1.4.255-SNAPSHOT"
  echo "Platform->jvm"
  echo "Launch->WarnkeFuzz"
  echo "Sanity->On"
  echo "Execution->Sequential"
  echo "BufferManager->Heap"
  echo "Dictionary->MultiMap"
  echo "TripleStore->MapMapList"
  echo "Endpoint->None"
  echo "Jena->On"
  echo "Set->BTree"
  echo "Map->BTree"
  echo "IteratorVerbose->None"
  echo "OutputFormat->Empty"
  echo "Pagesize->128"
  echo "BlockCapacity->8"
  echo "BTreeBranching->8"
  echo "MergeSortRows->8"
  echo "AdvancedOptimisation->true"
  echo "Coverage->ECoverage.Disabled"
  echo "CoverageGenerate->Off"
  echo "ServerCommunication->None"
} | ./generate-buildfile.kts
./tool-gradle-build.sh
port="3030"
/opt/apache-jena-fuseki-3.14.0/fuseki-server --port=$port > /dev/null 2>&1 &
sleep 3
mkdir donefolder
curl -X POST --data-urlencode "dbName=sp2b" --data-urlencode "dbType=mem" -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets > /dev/null 2>&1
export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64
for f in $(ls crash-* --sort=size -r)
do
	echo $f
	cat $f | ./build/executable || break
	mv $f donefolder/
done
echo $f
