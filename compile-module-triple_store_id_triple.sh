#!/bin/bash
./generate-buildfile-module.kts "Luposdate3000_Triple_Store_Id_Triple" "src/luposdate3000_triple_store_id_triple" "linuxX64" --inline --nosuspend --release
gradle jvmJar #build
gradle publishJvmPublicationToMavenLocal #publishToMavenLocal
mkdir build-cache
rm -rf build-cache/build-module-triple_store_id_triple build-cache/src-module-triple_store_id_triple .gradle
mv build build-cache/build-module-triple_store_id_triple
mv src.generated build-cache/src-module-triple_store_id_triple
