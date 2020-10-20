#!/bin/bash
./generate-buildfile-module.kts "Luposdate3000_Buffer_Manager_Inmemory" "src/luposdate3000_buffer_manager_inmemory" "linuxX64" --inline --nosuspend --release --BUFFER_MANAGER_PAGE_SIZE_IN_BYTES=8192 --BUFFER_MANAGER_USE_FREE_LIST=true
gradle jvmJar #build
gradle publishJvmPublicationToMavenLocal #publishToMavenLocal
mkdir build-cache
rm -rf build-cache/build-module-buffer_manager_inmemory build-cache/src-module-buffer_manager_inmemory .gradle
mv build build-cache/build-module-buffer_manager_inmemory
mv src.generated build-cache/src-module-buffer_manager_inmemory
