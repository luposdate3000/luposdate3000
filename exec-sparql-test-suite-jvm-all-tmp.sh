i=0

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
  echo ECoverage.Count
  echo DontChange
  echo None
} | ./generate-buildfile.kts
./tool-gradle-build.sh
ret=$?
if [ $ret -ne 0 ]; then exit $ret; fi
ln -s $(readlink -f build/executable ) log/alltest/$i.exec
i=$(($i + 1))
{
  echo 1.4.255-SNAPSHOT
  echo jvm
  echo SparqlTestSuite
  echo On
  echo Sequential
  echo Heap
  echo MultiMap
  echo SingleList
  echo None
  echo On
  echo BTree
  echo Bisection
  echo None
  echo Empty
  echo 256
  echo 16
  echo 16
  echo 16
  echo false
  echo ECoverage.Count
  echo DontChange
  echo Ktor
} | ./generate-buildfile.kts
./tool-gradle-build.sh
ret=$?
if [ $ret -ne 0 ]; then exit $ret; fi
ln -s $(readlink -f build/executable ) log/alltest/$i.exec
i=$(($i + 1))
{
  echo 1.4.255-SNAPSHOT
  echo jvm
  echo SparqlTestSuite
  echo On
  echo Sequential
  echo Heap
  echo ObjectMap
  echo BPlusTree
  echo Korio
  echo On
  echo Bisection
  echo HashMap
  echo None
  echo EmptyWithDictionary
  echo 512
  echo 32
  echo 32
  echo 32
  echo true
  echo ECoverage.Count
  echo DontChange
  echo None
} | ./generate-buildfile.kts
./tool-gradle-build.sh
ret=$?
if [ $ret -ne 0 ]; then exit $ret; fi
ln -s $(readlink -f build/executable ) log/alltest/$i.exec
i=$(($i + 1))
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
  echo ECoverage.Count
  echo DontChange
  echo None
} | ./generate-buildfile.kts
./tool-gradle-build.sh
ret=$?
if [ $ret -ne 0 ]; then exit $ret; fi
ln -s $(readlink -f build/executable ) log/alltest/$i.exec
i=$(($i + 1))
