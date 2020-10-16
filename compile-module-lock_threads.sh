#!/bin/bash
./generate-buildfile-module.kts "Luposdate3000_Lock_Threads" "src/luposdate3000_lock_threads" "linuxX64" --inline --nosuspend --release
gradle build
gradle publishToMavenLocal
mkdir build-cache
rm -rf build-cache/build-module-lock_threads build-cache/src-module-lock_threads
mv build build-cache/build-module-lock_threads
mv src.generated build-cache/src-module-lock_threads
