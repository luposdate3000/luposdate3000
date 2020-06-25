#!/bin/bash
export JAVA_OPTS="-Xmx30g"
port="3030"
{
  echo "KotlinVersion->1.4.255-SNAPSHOT"
  echo "Platform->jvm"
  echo "Launch->SparqlTestSuite"
  echo "Sanity->On"
  echo "Execution->Sequential"
  echo "BufferManager->Heap"
#  echo "Dictionary->ObjectMap"
  echo "Dictionary->MultiMap"
#  echo "TripleStore->BPlusTree"
  echo "TripleStore->MapMapList"
#  echo "TripleStore->SingleList"
  echo "Endpoint->Korio"
  echo "Jena->On"
  echo "Set->Bisection"
#  echo "Set->BTree"
#  echo "Map->BTree"
  echo "Map->Bisection"
  echo "IteratorVerbose->Count"
  echo "OutputFormat->Empty"
  echo "Pagesize->8196"
  echo "BlockCapacity->1024"
  echo "BTreeBranching->512"
  echo "MergeSortRows->512"
  echo "BulkImportBlockSize->1048576"
  echo "AdvancedOptimisation->true"
#  echo "Coverage->ECoverage.VeryVerbose"
  echo "Coverage->ECoverage.Count"
  echo "CoverageGenerate->DontChange"
  echo "ServerCommunication->None"
  echo "MaxTriplesDuringTest->-1"
  echo "ConnectionPool->Off"
  echo "Inline->Off"
} | ./generate-buildfile.kts
./tool-gradle-build.sh
ret=$?
if [ $ret -ne 0 ]
then
	exit $ret
fi
function execJvm
{
	export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64
	./build/executable > log/x 2>&1
}

{ { time execJvm ; } > log/c 2>&1 ;}
cd log
cat x | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line"|grep -v "<h1>Success</h1>"| sort | uniq -c | sed "s/kotlin.//g" | sed "s/java.lang.//g" >>c
grep -e "Test: " -e Failed -e Success x | grep -v "Failed requirement" |grep -v "<h1>Success</h1>"| grep -B1 -e Failed -e Success >> c
echo "diff c c2"
diff c c2
echo "diff a c"
diff a c
diff a c -y |grep "|" -B1|grep "Success.*|.*Failed" -B1 | grep -v "Failed(NotImplemented)" |grep "Success.*|.*Failed" -B1
cd ..
