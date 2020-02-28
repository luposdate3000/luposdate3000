#!/bin/bash
mkdir log
if [ "$(uname)" == "Darwin" ]
then
gradle linkReleaseExecutableMacosX64
else
gradle linkReleaseExecutableLinuxX64
fi
