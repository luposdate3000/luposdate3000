#!/bin/bash
./generate-buildfile-module.kts "Luposdate3000_Parser" "src/luposdate3000_parser" "linuxX64" --inline --nosuspend
gradle build
gradle publishToMavenLocal
mkdir build-cache
rm -rf build-cache/build-module-parser build-cache/src-module-parser
mv build build-cache/build-module-parser
mv src.generated build-cache/src-module-parser
