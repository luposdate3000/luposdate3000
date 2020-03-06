#!/bin/bash
#kotlinc -script generate-buildfile.kts jvm commonS00LaunchBinaryTestsMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS12DummyMain commonS14ServerNoneMain jvmS14ClientKtorTarget commonS15DistributedMain
./tool-gradle-build.sh
for f in crash-*; do echo $f;cat $f | ./build/executable || break;mv $f done/ ;done > x 2>&1;cat x;head x -n1
