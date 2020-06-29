#!/bin/bash
export JAVA_OPTS="-Xmx60g"
port="3030"
{
  echo "KotlinVersion->1.4.255-SNAPSHOT"
  echo "Platform->jvm"
  echo "Launch->SparqlTestSuite"
  echo "Sanity->On"
  echo "Execution->Sequential"
  echo "BufferManager->Heap"
  echo "Dictionary->MultiMap"
  echo "TripleStore->BPlusTree"
  echo "Endpoint->Korio"
  echo "Jena->On"
  echo "Set->BTree"
  echo "Map->BTree"
  echo "IteratorVerbose->Count"
  echo "OutputFormat->Empty"
  echo "Pagesize->128"
  echo "BlockCapacity->8"
  echo "BTreeBranching->8"
  echo "MergeSortRows->8"
  echo "BulkImportBlockSize->8"
  echo "AdvancedOptimisation->false"
#  echo "Coverage->ECoverage.Disabled"
#  echo "CoverageGenerate->DontChange"
  echo "Coverage->ECoverage.VeryVerbose"
  echo "CoverageGenerate->On"
  echo "ServerCommunication->None"
  echo "MaxTriplesDuringTest->2000"
  echo "ConnectionPool->Off"
  echo "Inline->Off"
} | ./generate-buildfile.kts
./tool-gradle-build.sh
ret=$?
if [ $ret -ne 0 ]
then
	exit $ret
fi
IFS=$'\n'
function execJvm
{
	export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64
	export LUPOS_HOME=/tmp/luposdate3000-test/
	./build/executable "$@" > log/x 2>&1
}

{ { time execJvm "$@"; } > log/c 2>&1 ;}
cd log
cat x | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line"|grep -v "<h1>Success</h1>"| sort | uniq -c | sed "s/kotlin.//g" | sed "s/java.lang.//g" >>c
grep -e "Test: " -e Failed -e Success x | grep -v "Failed requirement" |grep -v "<h1>Success</h1>"| grep -B1 -e Failed -e Success >> c
echo "diff c c2"
diff c c2
echo "diff a c"
diff a c
diff a c -y |grep "|" -B1|grep "Failed.*|.*Success" -B1
diff a c -y |grep "|" -B1|grep "Success.*|.*Failed" -B1
cd ..
