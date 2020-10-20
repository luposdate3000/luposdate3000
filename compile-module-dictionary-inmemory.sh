#!/bin/bash
./generate-buildfile-module.kts "Luposdate3000_Dictionary_Inmemory" "src/luposdate3000_dictionary_inmemory" "linuxX64" --inline --nosuspend --release
gradle jvmJar #build
gradle publishJvmPublicationToMavenLocal #publishToMavenLocal
mkdir build-cache
rm -rf build-cache/build-module-dictionary_inmemory build-cache/src-module-dictionary_inmemory .gradle
mv build build-cache/build-module-dictionary_inmemory
mv src.generated build-cache/src-module-dictionary_inmemory
