kotlinc -script generate-buildfile.kts linuxX64 commonS00LaunchBinaryTestsMain commonS00ExecutionSequentialMain commonS00TraceOnMain
./tool-gradle-build.sh
function execNative
{
	./build/executable > log/x 2>&1
}

{ { time execNative ; } > log/c 2>&1 ;} &
wait
