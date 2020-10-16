#!/bin/bash
./generate-buildfile-module.kts "Luposdate3000_Value_Definition" "src/luposdate3000_value_definition" "linuxX64" --inline --nosuspend --release
gradle build
gradle publishToMavenLocal
mkdir build-cache
rm -rf build-cache/build-module-value_definition build-cache/src-module-value_definition
mv build build-cache/build-module-value_definition
mv src.generated build-cache/src-module-value_definition
