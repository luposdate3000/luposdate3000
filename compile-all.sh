#!/bin/bash
./generate-buildfile.kts 1.4.255-SNAPSHOT jvm SparqlTestSuite On  Fast Sequential Heap MultiMap BPlusTree Dummy Korio None Local On BTree BTree Count Empty 128 8 8 8 false ECoverage.Disabled DontChange None
./tool-gradle-build.sh
./generate-buildfile.kts 1.4.255-SNAPSHOT jvm Endpoint        On  Fast Sequential Heap MultiMap BPlusTree Dummy Korio None Local On BTree BTree Count Empty 128 8 8 8 false ECoverage.Disabled DontChange Ktor
./tool-gradle-build.sh
./generate-buildfile.kts 1.4.255-SNAPSHOT jvm Benchmark       Off Fast Sequential Heap MultiMap BPlusTree Dummy Korio None Local Off BTree BTree None Empty 8196 1024 512 512 true ECoverage.Disabled DontChange None
./tool-gradle-build.sh
