#!/bin/bash
./tool-clear-caches.sh
for r in Enable Disable
do
for i in Enable Disable
do
for s in Disable
do
./compile-module-all.main.kts --releaseMode=$r --inlineMode=$i --suspendMode=$s --dryMode=Disable --fastMode=Disable --intellijMode=Disable > all-test-$r-$i-$s.compile-log 2>&1
./launcher.main.kts --releaseMode=$r --inlineMode=$i --suspendMode=$s --partitionMode=On  --memoryMode=inmemory   --proguardMode=Off --mainClass=Binary_Test_Suite --jenaWrapper=On --endpointMode=None > all-test-$r-$i-$s-Partitions.test-log 2>&1
./launcher.main.kts --releaseMode=$r --inlineMode=$i --suspendMode=$s --partitionMode=Off --memoryMode=inmemory   --proguardMode=Off --mainClass=Binary_Test_Suite --jenaWrapper=On --endpointMode=None > all-test-$r-$i-$s-NoPartitions.test-log 2>&1
rm -rf /tmp/luposdate3000/
./launcher.main.kts --releaseMode=$r --inlineMode=$i --suspendMode=$s --partitionMode=Off --memoryMode=persistent --proguardMode=Off --mainClass=Binary_Test_Suite --jenaWrapper=On --endpointMode=None > all-test-$r-$i-$s-NoPartitions-Persistent.test-log 2>&1
./launcher.main.kts --releaseMode=$r --inlineMode=$i --suspendMode=$s --partitionMode=On  --memoryMode=inmemory   --proguardMode=On  --mainClass=Binary_Test_Suite --jenaWrapper=On --endpointMode=None > all-test-$r-$i-$s-Partitions-Proguard.test-log 2>&1
./launcher.main.kts --releaseMode=$r --inlineMode=$i --suspendMode=$s --partitionMode=Off --memoryMode=inmemory   --proguardMode=On  --mainClass=Binary_Test_Suite --jenaWrapper=On --endpointMode=None > all-test-$r-$i-$s-NoPartitions-Proguard.test-log 2>&1
rm -rf /tmp/luposdate3000/
./launcher.main.kts --releaseMode=$r --inlineMode=$i --suspendMode=$s --partitionMode=Off --memoryMode=persistent --proguardMode=On  --mainClass=Binary_Test_Suite --jenaWrapper=On --endpointMode=None > all-test-$r-$i-$s-NoPartitions-Persistent-Proguard.test-log 2>&1
done
done
done
