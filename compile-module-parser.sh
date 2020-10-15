#!/bin/bash
./generate-buildfile-module-parser.kts --inline --nosuspend
gradle build
gradle publishToMavenLocal
mkdir build-cache
rm -rf build-cache/build-module-parser
mv build build-cache/build-module-parser
mv src.generated build-cache/src-module-parser
