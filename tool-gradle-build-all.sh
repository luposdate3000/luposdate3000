#!/bin/bash
kotlinc -script generate-buildfile.kts jvm commonS00LaunchGenerateTestsMain commonS00ExecutionSequentialMain commonS00TraceOnMain jvmS01BufferMemoryMappedMain commonS12DummyMain jvmS14ServerKorioMain jvmS14ClientKorioMain
./tool-gradle-build.sh
kotlinc -script generate-buildfile.kts jvm commonS00LaunchGenerateTestsMain commonS00ExecutionSequentialMain commonS00TraceOnMain jvmS01BufferMemoryMappedUnsafeMain commonS12DummyMain jvmS14ServerKorioMain jvmS14ClientKtorTarget
./tool-gradle-build.sh
kotlinc -script generate-buildfile.kts jvm commonS00LaunchGenerateTestsMain commonS00ExecutionSequentialMain commonS00TraceOnMain jvmS01BufferRandomAccessMain commonS12DummyMain jvmS14ServerKorioMain commonS14ClientNoneMain
./tool-gradle-build.sh
kotlinc -script generate-buildfile.kts jvm commonS00LaunchBinaryTestsMain commonS00ExecutionParallelMain jvmS01BufferUnsafeMain commonS12LocalMain
./tool-gradle-build.sh
kotlinc -script generate-buildfile.kts jvm commonS00LaunchEndpointMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS03DictionaryNoneMain
./tool-gradle-build.sh
kotlinc -script generate-buildfile.kts linuxX64 commonS00LaunchEndpointMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS03DictionaryIntArrayMain commonS12DummyMain nativeS14ClientKtorTarget commonS15DistributedMain
./tool-gradle-build.sh
echo "----------------------------------------------------"
echo "----------------------------------------------------"
echo "----------------------------------------------------"
cat build/compile* | sort | uniq
