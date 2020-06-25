#!/bin/bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
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
		cat $logfile \
		| grep "e: " \
		| sort \
		| uniq \
		| grep -v "Expected performance impact from inlining is insignificant" \
		| grep -v "kotlin/lupos/datastructures" \
		| grep -v "kotlin/lupos/s02buildSyntaxTree" \
		| grep -v "This class can only be used with the compiler argument" \
		| grep -v "'UseExperimental' is deprecated. Please use OptI" \
		| grep -v "Parameter.*is never used" \
		| grep -v "commonConfig.*No cast needed" \
		| grep -v "commonConfig.*Unchecked cast: Any? to" \
		| grep -v "commonConfig.*Unnecessary non-null assertion" \
		| grep -v "kotlin/lupos/s01io" \
		| grep -v "This API is experimental. It could be removed or changed in future"
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



#classpath("net.sf.proguard:proguard-gradle:6.2.2")
