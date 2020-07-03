{
  echo "KotlinVersion->1.4.255-SNAPSHOT"
  echo "Platform->jvm"
  echo "Launch->Endpoint"
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
  echo "AdvancedOptimisation->false"
  echo "Coverage->ECoverage.Disabled"
  echo "CoverageGenerate->Off"
  echo "ServerCommunication->None"
  echo "MaxTriplesDuringTest->-1"
  echo "ConnectionPool->Off"
  echo "Inline->On"
} | ./generate-buildfile.kts
./tool-gradle-build.sh
./build/executable
#wget localhost:80/import/turtle?query=%2Fmnt%2Fluposdate-testdata%2Fbtc2019%2Fbtc2019-triples.nt
#wget localhost:80/import/turtle?query=$(find /mnt/luposdate-testdata/btc2019/data/*.n3 | sort | paste -s -d ';' | sed "s-/-%2F-g")
#$(find $triplesfolder/*.n3 | paste -s -d ';')

exit

find /mnt/luposdate-testdata/btc2019/data/*.n3 | sort | paste -s -d ';' > tmpparams
curl -H "Content-Type: application/x-www-form-urlencoded" -d @tmpparams localhost:80/import/turtle
rm tmpparams
