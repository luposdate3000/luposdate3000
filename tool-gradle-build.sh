#!/bin/bash
mkdir log
buildfile=$1
if [ -z "$buildfile" ]
then
      buildfile="build.gradle.kts"
fi
cachefile=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/gradle_-" | sed "s/\".*//g")
if grep -q "build_jvm_" "$buildfile"
then
	gradle --project-cache-dir="$cachefile" build
	(
	        cd buildJvm/distributions
	        tar -xf luposdate3000.tar
	)
elif [ "$(uname)" == "Darwin" ]
then
	gradle --project-cache-dir="$cachefile" linkReleaseExecutableMacosX64
else
	gradle --project-cache-dir="$cachefile" linkDebugExecutableLinuxX64
fi
