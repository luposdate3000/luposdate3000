#!/bin/bash
mkdir log
if [ "$(uname)" == "Darwin" ]
then
gradle linkReleaseExecutableMacosX64 --project-cache-dir="gradleNative"
else
gradle linkDebugExecutableLinuxX64 --project-cache-dir="gradleNative"
fi
