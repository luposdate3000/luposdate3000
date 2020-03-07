classes1="build/build_jvm_jvmS00LaunchJavaFuzzMain_commonS00ExecutionSequentialMain_commonS00TraceOffMain_commonS01HeapMain_commonS12DummyMain_commonS14ServerNoneMain_jvmS14ClientKtorTarget_commonS15DistributedMain"
classes2="build/build_jvm_jvmS00LaunchWarnkeFuzzMain_commonS00ExecutionSequentialMain_commonS00TraceOffMain_commonS01HeapMain_commonS12DummyMain_commonS14ServerNoneMain_jvmS14ClientKtorTarget_commonS15DistributedMain"

#java -jar dependencies/jacococli.jar report jacoco.1.exec --classfiles "${classes1}/classes/kotlin/main/" --sourcefiles src/ --xml coverage1.xml
#for i in $(seq 2 11)
#do
#	java -jar dependencies/jacococli.jar report jacoco.$i.exec --classfiles "${classes2}/classes/kotlin/main/" --sourcefiles src/ --xml coverage$i.xml
#done


#exit


docker start sonarqube
java -jar dependencies/jacococli.jar report $(find -name "jacoco.*exec") --classfiles "${classes2}/classes/kotlin/main/" --sourcefiles src/ --xml coverage.xml
sonar-scanner

#view localhost:9000

