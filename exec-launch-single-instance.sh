{
  echo "KotlinVersion->1.4.255-SNAPSHOT"
  echo "Platform->jvm"
  echo "Launch->Endpoint"
  echo "Sanity->Off"
  echo "Execution->Sequential"
  echo "BufferManager->Heap"
  echo "Dictionary->Small"
  echo "TripleStore->BPlusTreePartition"
#  echo "Endpoint->Korio"
  echo "Endpoint->JavaNet"
  echo "Jena->On"
  echo "Set->BTree"
  echo "Map->BTree"
  echo "OutputFormat->XML"
  echo "EnumerateBnodes->false"
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
./build/executable $(hostname)
#wget localhost:80/import/turtle?query=%2Fmnt%2Fluposdate-testdata%2Fbtc2019%2Fbtc2019-triples.nt
#wget localhost:80/import/turtle?query=$(find /mnt/luposdate-testdata/btc2019/data/*.n3 | sort | paste -s -d ';' | sed "s-/-%2F-g")
#$(find $triplesfolder/*.n3 | paste -s -d ';')

exit

find /mnt/luposdate-testdata/btc2019/data/*.n3 | sort | paste -s -d ';' > tmpparams
curl -H "Content-Type: application/x-www-form-urlencoded" -d @tmpparams localhost:80/import/turtle
rm tmpparams

exit

curl -H "Content-Type: application/x-www-form-urlencoded" localhost:80/import/intermediate?query=/mnt/luposdate-testdata/btc2019/data/intermediate
curl -H "Content-Type: application/x-www-form-urlencoded" localhost:80/persistence/store
curl -H "Content-Type: application/x-www-form-urlencoded" -d "@resources/myqueries/execP.sparql" localhost:80/sparql/query
