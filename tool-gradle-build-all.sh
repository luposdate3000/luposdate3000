#!/bin/bash
kotlinc -script generate-buildfile.kts jvm commonS00LaunchGenerateTestsMain commonS00ExecutionSequentialMain commonS00TraceOnMain jvmS01BufferMemoryMappedMain jvmS12DummyMain jvmS14ServerKorioMain jvmS14ClientKorioMain || exit
./tool-gradle-build.sh
kotlinc -script generate-buildfile.kts jvm commonS00LaunchGenerateTestsMain commonS00ExecutionSequentialMain commonS00TraceOnMain jvmS01BufferMemoryMappedUnsafeMain jvmS12DummyMain jvmS14ServerKorioMain jvmS14ClientKtorTarget || exit
./tool-gradle-build.sh
kotlinc -script generate-buildfile.kts jvm commonS00LaunchGenerateTestsMain commonS00ExecutionSequentialMain commonS00TraceOnMain jvmS01BufferRandomAccessMain jvmS12DummyMain jvmS14ServerKorioMain commonS14ClientNoneMain || exit
./tool-gradle-build.sh
kotlinc -script generate-buildfile.kts jvm commonS00LaunchBinaryTestsMain commonS00ExecutionParallelMain commonS01HeapMain commonS12LocalMain || exit
./tool-gradle-build.sh
kotlinc -script generate-buildfile.kts jvm commonS00LaunchEndpointMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS03DictionaryNoneMain || exit
./tool-gradle-build.sh
kotlinc -script generate-buildfile.kts linuxX64 commonS00LaunchEndpointMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain commonS00TraceOffMain commonS01HeapMain commonS03DictionaryIntArrayMain || exit
./tool-gradle-build.sh
