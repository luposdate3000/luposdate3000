#!/bin/bash
rm -rf .idea
./compile-module-all.sh
./generate-buildfile-module.main.kts --module="Luposdate3000_Shared_Inline" --inline --nosuspend --release --fast --idea
cat > settings.gradle << EOF
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
rootProject.name = "Luposdate3000"
include(":src")
EOF
cat > build.gradle.kts << EOF
dependencies {
    project(":src")
}
EOF
echo "dependencies {" > src/build.gradle.kts
for f in src/lupos*
do
fshort=$(echo $f | sed "s-src/--g")
echo "include(\":src:$fshort\")" >> settings.gradle
echo "    project(\":src:$fshort\")" >> src/build.gradle.kts
done
echo "}" >> src/build.gradle.kts

