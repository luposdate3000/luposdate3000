#!/bin/bash
rm generateTestcase.jar
classpath="./build/build_1.4.255-SNAPSHOT_jvm_BinaryTestSuite_On_Threads_MultiMap_BPlusTreePartition_None_On_BTree_BTree_Empty_false_128_8_8_8_8_false_ECoverage.Disabled_Off_None_2000_Off_Off_true_EPOPDebugMode.DEBUG2/libs/luposdate3000-all.jar"
/opt/kotlin/dist/kotlinc/bin/kotlinc generateTestcase.kt -cp $classpath -Xallow-jvm-ir-dependencies -d generateTestcase.jar -include-runtime
java -cp "generateTestcase.jar:$classpath" GenerateTestcaseKt TestSuite . resources/binary
