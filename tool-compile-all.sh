#!/bin/bash
./generate-buildfile.kts listAll jvm Heap | sed "s/generate-buildfile.kts/generate-buildfile.kts\n.\/tool-gradle-build.sh/g" > tmp.sh
chmod +x tmp.sh
rm build/compile*
./tmp.sh
rm tmp.sh
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
