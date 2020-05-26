#!/bin/bash
./generate-buildfile.kts 1.4.255-SNAPSHOT jvm SparqlTestSuite On  Fast Sequential Heap MultiMap BPlusTree Dummy Korio None Local On BTree BTree Count Empty 128 8 8 8 false ECoverage.Disabled DontChange None
./tool-gradle-build.sh
./generate-buildfile.kts 1.4.255-SNAPSHOT jvm Endpoint        On  Fast Sequential Heap MultiMap BPlusTree Dummy Korio None Local On BTree BTree Count Empty 128 8 8 8 false ECoverage.Disabled DontChange Ktor
./tool-gradle-build.sh
./generate-buildfile.kts 1.4.255-SNAPSHOT jvm Benchmark       Off Fast Sequential Heap MultiMap BPlusTree Dummy Korio None Local Off BTree BTree None Empty 8196 1024 512 512 true ECoverage.Disabled DontChange None
./tool-gradle-build.sh

cat build/compile* \
| sort \
| uniq \
| grep -e "^w: " -e "^e: " \
| grep -v "Expected performance impact from inlining is insignificant" \
| grep -v "kotlin/lupos/datastructures" \
| grep -v "kotlin/lupos/s02buildSyntaxTree" \
| grep -v "This class can only be used with the compiler argument" \
| grep -v "'UseExperimental' is deprecated. Please use OptI" \
| grep -v "Parameter.*is never used" \
| grep -v "commonConfig.*No cast needed" \
| grep -v "commonConfig.*Unchecked cast: Any? to" \
| grep -v "commonConfig.*Unnecessary non-null assertion" \
| grep -v "kotlin/lupos/s01io"
