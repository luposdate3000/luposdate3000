#!/bin/bash
./generate-buildfile-module.kts "Luposdate3000_Operators" "src/luposdate3000_operators" "linuxX64" --inline --nosuspend --release
gradle jvmJar #build
gradle publishJvmPublicationToMavenLocal #publishToMavenLocal
mkdir build-cache
rm -rf build-cache/build-module-operators build-cache/src-module-operators .gradle
mv build build-cache/build-module-operators
mv src.generated build-cache/src-module-operators
