#!/bin/bash
./generate-buildfile-module.kts "Luposdate3000_Shared" "src/luposdate3000_shared" "linuxX64" --inline --nosuspend --release
gradle build
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
gradle publishToMavenLocal
mkdir build-cache
rm -rf build-cache/build-module-shared build-cache/src-module-shared .gradle
mv build build-cache/build-module-shared
mv src.generated build-cache/src-module-shared
