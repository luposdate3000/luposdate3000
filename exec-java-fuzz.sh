#!/bin/bash
/opt/apache-jena-fuseki-3.14.0/fuseki-server  > /dev/null 2>&1 &
mount /opt/tmpdata javafuzz
kotlinc -script generate-buildfile.kts jvm jvmS00LaunchJavaFuzzMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS12DummyMain commonS14ServerNoneMain jvmS14ClientKtorTarget commonS15DistributedMain
./tool-gradle-build.sh
buildfile=$1
if [ -z "$buildfile" ]
then
      buildfile="build.gradle.kts"
fi
output=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/build_-" | sed "s/\".*//g")
cachefile=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/gradle_-" | sed "s/\".*//g")
logfile=$(cat "$buildfile" | grep "project.buildDir" | sed "s-[^_]*_-build/compile_-" | sed "s/\".*//g")
gradle --project-cache-dir="$cachefile" build > $logfile 2>&1
(
	cd "${output}/distributions"
	tar -xf luposdate3000.tar
)
while true
do
java -javaagent:dependencies/jacocoagent.jar -cp "$(pwd)/${output}/distributions/luposdate3000/lib/*" MainKt
done
