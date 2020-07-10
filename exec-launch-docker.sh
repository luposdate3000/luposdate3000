#!/bin/bash

rm build/executable
{
  echo "KotlinVersion->1.4.255-SNAPSHOT"
  echo "Platform->jvm"
  echo "Launch->Endpoint"
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
  echo "Coverage->ECoverage.Disabled"
  echo "CoverageGenerate->DontChange"
  echo "ServerCommunication->Sockets"
  echo "MaxTriplesDuringTest->2000"
  echo "ConnectionPool->Off"
  echo "Inline->Off"
  echo "UsePartitions->true"
} | ./generate-buildfile.kts
./tool-gradle-build.sh
ret=$?
if [ $ret -ne 0 ]
then
	exit $ret
fi
p=$(readlink build/executable | sed "s-bin/luposdate3000-lib-g" | sed "s-$(pwd)/--g")
cat << EOF > Dockerfile
FROM openjdk:14
COPY $p /usr/src/myapp
COPY resources /usr/src/myapp/resources
COPY resources /src/luposdate3000/resources
WORKDIR /usr/src/myapp
EXPOSE 80
ENTRYPOINT ["java", "-classpath", "./*", "MainKt"]
EOF

docker-compose up --detach --build --remove-orphans
sleep 3
curl http://localhost:8000/debug/knownHosts
curl http://localhost:8001/debug/knownHosts
curl http://localhost:8002/debug/knownHosts
curl http://localhost:8003/debug/knownHosts
docker-compose logs --follow > log/x &
pd=$!
tail -f log/x| grep -e "Test: " -e Failed
kill $pd

#curl http://localhost:8000/debug/unittest
