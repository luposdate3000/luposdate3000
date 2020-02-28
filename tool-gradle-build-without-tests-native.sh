#!/bin/bash
mkdir log
if [ "$(uname)" == "Darwin" ]
then
gradle linkReleaseExecutableMacosX64 --project-cache-dir="gradleNativeOSX"
else
gradle linkDebugExecutableLinuxX64 --project-cache-dir="gradleNativeLinux"
fi
