#!/bin/bash
./generate-buildfile-module.kts "Luposdate3000_Jena_Wrapper_On" "src/luposdate3000_jena_wrapper_on" "linuxX64" --inline --nosuspend --release
gradle build
gradle publishToMavenLocal
mkdir build-cache
rm -rf build-cache/build-module-jena-on build-cache/src-module-jena-on
mv build build-cache/build-module-jena-on
mv src.generated build-cache/src-module-jena-on
