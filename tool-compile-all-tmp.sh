
{
  echo 1.4.255-SNAPSHOT
  echo jvm
  echo SparqlTestSuite
  echo On
  echo Sequential
  echo Heap
  echo ObjectMap
  echo MapMapList
  echo Korio
  echo On
  echo Bisection
  echo BTree
  echo None
  echo XML
  echo 128
  echo 8
  echo 8
  echo 8
  echo true
  echo ECoverage.Disabled
  echo DontChange
  echo None
} | ./generate-buildfile.kts
./tool-gradle-build.sh
if [ $? -ne 0 ]; then exit ; fi
{
  echo 1.4.255-SNAPSHOT
  echo jvm
  echo Endpoint
  echo Off
  echo Parallel
  echo Heap
  echo MultiMap
  echo SingleList
  echo None
  echo Off
  echo BTree
  echo Bisection
  echo Count
  echo Empty
  echo 256
  echo 16
  echo 16
  echo 16
  echo false
  echo ECoverage.Count
  echo On
  echo Ktor
} | ./generate-buildfile.kts
./tool-gradle-build.sh
if [ $? -ne 0 ]; then exit ; fi
{
  echo 1.4.255-SNAPSHOT
  echo jvm
  echo Benchmark
  echo On
  echo Sequential
  echo Heap
  echo ObjectMap
  echo BPlusTree
  echo Korio
  echo On
  echo Bisection
  echo HashMap
  echo Verbose
  echo EmptyWithDictionary
  echo 512
  echo 32
  echo 32
  echo 32
  echo true
  echo ECoverage.Verbose
  echo DontChange
  echo None
} | ./generate-buildfile.kts
./tool-gradle-build.sh
if [ $? -ne 0 ]; then exit ; fi
{
  echo 1.4.255-SNAPSHOT
  echo jvm
  echo SparqlTestSuite
  echo On
  echo Sequential
  echo Heap
  echo ObjectMap
  echo MapMapList
  echo Korio
  echo On
  echo Bisection
  echo BTree
  echo None
  echo XML
  echo 1024
  echo 64
  echo 64
  echo 64
  echo true
  echo ECoverage.VeryVerbose
  echo Off
  echo None
} | ./generate-buildfile.kts
./tool-gradle-build.sh
if [ $? -ne 0 ]; then exit ; fi
{
  echo 1.4.255-SNAPSHOT
  echo jvm
  echo SparqlTestSuite
  echo On
  echo Sequential
  echo Heap
  echo ObjectMap
  echo MapMapList
  echo Korio
  echo On
  echo Bisection
  echo BTree
  echo None
  echo XML
  echo 2048
  echo 128
  echo 128
  echo 128
  echo true
  echo ECoverage.Disabled
  echo DontChange
  echo None
} | ./generate-buildfile.kts
./tool-gradle-build.sh
if [ $? -ne 0 ]; then exit ; fi
