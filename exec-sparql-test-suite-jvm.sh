pkill java
port="3030"
/opt/apache-jena-fuseki-3.14.0/fuseki-server --port=$port > /dev/null 2>&1 &
kotlinc -script generate-buildfile.kts jvm commonS00LaunchGenerateTestsMain commonS00SanityChecksOnMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS03DictionaryIntArrayMain commonS12DummyMain commonS14ServerNoneMain jvmS14ClientKtorTarget commonS15DistributedMain
./tool-gradle-build.sh
curl -X POST --data-urlencode "dbName=sp2b" --data-urlencode "dbType=mem" -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets
curl -X GET  -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets
function execJvm
{
	./build/executable > log/x 2>&1
}

{ { time execJvm ; } > log/c 2>&1 ;}
cd log
cat x | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line"|grep -v "<h1>Success</h1>"| sort | uniq -c | sed "s/kotlin.//g" | sed "s/java.lang.//g" >>c
grep -e "Test: " -e Failed -e Success x | grep -v "Failed requirement" |grep -v "<h1>Success</h1>"| grep -B1 -e Failed -e Success >> c
echo "diff c c2"
diff c c2
echo "diff a c"
diff a c

cd ..
