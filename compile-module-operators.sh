#!/bin/bash
./generate-buildfile-module.kts "Luposdate3000_Operators" "src/luposdate3000_operators" "linuxX64" --inline --nosuspend --release
gradle build
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
gradle publishToMavenLocal
mkdir build-cache
rm -rf build-cache/build-module-operators build-cache/src-module-operators .gradle
mv build build-cache/build-module-operators
mv src.generated build-cache/src-module-operators
