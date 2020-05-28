#!/bin/bash
mkdir log build
buildfile=$1
if [ -z "$buildfile" ]
then
      buildfile="build.gradle.kts"
fi
output=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/build_-" | sed "s/\".*//g")
cachefile=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/gradle_-" | sed "s/\".*//g")
logfile=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/compile_-" | sed "s/\".*//g")
if grep -q "_jvm_" "$buildfile"
then
	gradle --project-cache-dir="$cachefile" build > $logfile 2>&1
	ret=$?
	if [ $ret -ne 0 ]
	then
		exit $ret
	fi
	(
	        cd "${output}/distributions"
	        tar -xf luposdate3000.tar
		rm ../../executable
		ln -s $(pwd)/luposdate3000/bin/luposdate3000 ../../executable
	)
elif [ "$(uname)" == "Darwin" ]
then
	gradle --project-cache-dir="$cachefile" linkReleaseExecutableMacosX64 > $logfile 2>&1
	ret=$?
	if [ $ret -ne 0 ]
	then
		exit $ret
	fi
	rm build/executable
	ln -s $(pwd)/${output}/bin/macosX64/releaseExecutable/luposdate3000.kexe build/executable
else
	gradle --project-cache-dir="$cachefile" linkReleaseExecutableLinuxX64 > $logfile 2>&1
	ret=$?
	if [ $ret -ne 0 ]
	then
		exit $ret
	fi
	rm build/executable
	ln -s $(pwd)/${output}/bin/linuxX64/releaseExecutable/luposdate3000.kexe build/executable
fi
cat $logfile
