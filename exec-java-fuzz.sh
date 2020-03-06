#!/bin/bash
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
port="3030"
/opt/apache-jena-fuseki-3.14.0/fuseki-server --port=$port > /dev/null 2>&1 &
for db in $(seq 1 13)
do
	mkdir javafuzz/db${db}
	size=$(echo "scale=0;(12*1.6^${db})/1" | bc)
	for i in $(seq 0 100)
	do
		dd if=/dev/urandom of=javafuzz/db${db}/input$i bs=$size count=1
	done
	curl -X POST --	data-urlencode "dbName=db${db}" --data-urlencode "dbType=mem" -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets > /dev/null 2>&1
	(
		while true
		do
			java -javaagent:dependencies/jacocoagent.jar=destfile=jacoco.${db}.exec -cp "$(pwd)/${output}/distributions/luposdate3000/lib/*" MainKt "db${db}"
		done
	) &
done
