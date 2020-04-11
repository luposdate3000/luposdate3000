#!/bin/bash
rm build/compile*
./generate-buildfile.kts jvm commonS00LaunchSparqlTestSuiteMain commonS00SanityChecksOnMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain jvmS01BufferMemoryMappedMain commonS03DictionaryIntArray commonS12DummyMain jvmS14ServerKorioMain jvmS14ClientKorioMain jvmS00WrapperJenaOnMain
./tool-gradle-build.sh
./generate-buildfile.kts jvm commonS00LaunchSparqlTestSuiteMain commonS00SanityChecksOffMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain jvmS01BufferMemoryMappedMain commonS03DictionaryIntArrayMain commonS12DummyMain jvmS14ServerKorioMain jvmS14ClientKorioMain commonS15DistributedMain jvmS00WrapperJenaOnMain
./tool-gradle-build.sh
./generate-buildfile.kts jvm commonS00LaunchSparqlTestSuiteMain commonS00SanityChecksOffMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain jvmS01BufferMemoryMappedUnsafeMain commonS03DictionaryIntArrayMain commonS12DummyMain jvmS14ServerKorioMain jvmS14ClientKtorTarget commonS15LocalMain jvmS00WrapperJenaOnMain
./tool-gradle-build.sh
./generate-buildfile.kts jvm commonS00LaunchSparqlTestSuiteMain commonS00SanityChecksOnMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain jvmS01BufferRandomAccessMain commonS03DictionaryIntArray commonS12DummyMain jvmS14ServerKorioMain commonS14ClientNoneMain commonS15DistributedMain jvmS00WrapperJenaOnMain
./tool-gradle-build.sh
./generate-buildfile.kts jvm commonS00LaunchEndpointMain commonS00SanityChecksOnMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain commonS01HeapMain commonS03DictionaryIntArrayMain commonS12LocalMain commonS03DictionaryIntArray jvmS00WrapperJenaOnMain
./tool-gradle-build.sh
./generate-buildfile.kts linuxX64 commonS00LaunchEndpointMain commonS00SanityChecksOnMain commonS00ResultFlowFastMain commonS00ExecutionSequentialMain commonS01HeapMain commonS03DictionaryIntArrayMain commonS12DummyMain nativeS14ClientKtorTarget commonS15DistributedMain jvmS00WrapperJenaOnMain
./tool-gradle-build.sh
./generate-buildfile.kts jvm commonS00LaunchBinaryTestsMain commonS00SanityChecksOnMain commonS00ExecutionParallelMain jvmS01BufferUnsafeMain commonS03DictionaryIntArrayMain commonS12LocalMain jvmS00WrapperJenaOnMain
./tool-gradle-build.sh
echo "----------------------------------------------------"
echo "----------------------------------------------------"
echo "----------------------------------------------------"
cat build/compile* | sort | uniq
