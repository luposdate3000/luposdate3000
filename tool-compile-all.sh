#!/bin/bash
./generate-buildfile.kts listAll 1.4.255-SNAPSHOT jvm SparqlTestSuite Endpoint Benchmark Heap | sed "s/generate-buildfile.kts/generate-buildfile.kts\n.\/tool-gradle-build.sh/g" > tool-compile-all-tmp.sh
chmod +x tool-compile-all-tmp.sh
rm build/compile*
./tool-compile-all-tmp.sh
rm tool-compile-all-tmp.sh
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
