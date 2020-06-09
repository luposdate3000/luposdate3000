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
  echo "ServerCommunication->Ktor"
  echo "MaxTriplesDuringTest->2000"
} | ./generate-buildfile.kts
./tool-gradle-build.sh

p=$(readlink build/executable | sed "s-bin/luposdate3000-lib-g")

/bin/cat << EOF > Dockerfile
FROM openjdk:14
COPY $p /usr/src/myapp
COPY resources /usr/src/myapp/resources
WORKDIR /usr/src/myapp
EXPOSE 80
ENTRYPOINT ["java", "-classpath", "./*", "MainKt"]
EOF

docker-compose up --detach --build --remove-orphans
