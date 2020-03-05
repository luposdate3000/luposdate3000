kotlinc -script generate-buildfile.kts jvm commonS00LaunchBinaryTestsMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS12DummyMain commonS14ServerNoneMain jvmS14ClientKtorTarget commonS15DistributedMain
./tool-gradle-build.sh
function execJvm
{
	./build/executable > log/x 2>&1
}

{ { time execJvm ; } > log/c 2>&1 ;} &
wait
