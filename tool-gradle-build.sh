#!/bin/bash
mkdir log
buildfile=$1
if [ -z "$buildfile" ]
then
      buildfile="build.gradle.kts"
fi
output=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/build_-" | sed "s/\".*//g")
cachefile=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/gradle_-" | sed "s/\".*//g")
if grep -q "build_jvm_" "$buildfile"
then
	gradle --project-cache-dir="$cachefile" build
	(
	        cd "${output}/distributions"
	        tar -xf luposdate3000.tar
		rm ../../executable
		ln -s $(pwd)/luposdate3000/bin/luposdate3000 ../../executable
	)
elif [ "$(uname)" == "Darwin" ]
then
	gradle --project-cache-dir="$cachefile" linkReleaseExecutableMacosX64
	rm build/executable
	ln -s $(output)/bin/macosX64/releaseExecutable/luposdate3000.kexe build/executable
else
	gradle --project-cache-dir="$cachefile" linkReleaseExecutableLinuxX64
	rm build/executable
	ln -s $(output)/bin/linux64/releaseExecutable/luposdate3000.kexe build/executable
fi
