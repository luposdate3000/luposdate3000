#!/bin/bash

testname="*"
x=$(find /src/luposdate3000/src/luposdate3000_code_gen_test_* -maxdepth 0 | sed "s-\$-/build/classes/kotlin/jvm/test-g" | tr "\n" ":")
y=$(find /src/luposdate3000/src/luposdate3000_code_gen_test_* -maxdepth 0 | sed "s-\$-/build/classes/kotlin/jvm/main-g" | tr "\n" ":")
classpath="$classpath:$x"
classpath="$classpath:$y"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_simulator_iot/build/libs/luposdate3000_simulator_iot-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_visualize_distributed_database/build/libs/luposdate3000_visualize_distributed_database-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_simulator_db/build/libs/luposdate3000_simulator_db-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_endpoint/build/libs/luposdate3000_endpoint-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_result_format/build/libs/luposdate3000_result_format-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_optimizer_distributed_query/build/libs/luposdate3000_optimizer_distributed_query-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_operator_factory/build/libs/luposdate3000_operator_factory-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_optimizer_ast/build/libs/luposdate3000_optimizer_ast-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_optimizer_physical/build/libs/luposdate3000_optimizer_physical-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_optimizer_logical/build/libs/luposdate3000_optimizer_logical-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_triple_store_manager/build/libs/luposdate3000_triple_store_manager-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_operator_physical/build/libs/luposdate3000_operator_physical-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_operator_logical/build/libs/luposdate3000_operator_logical-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_operator_arithmetik/build/libs/luposdate3000_operator_arithmetik-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_operator_base/build/libs/luposdate3000_operator_base-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_simulator_core/build/libs/luposdate3000_simulator_core-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_dictionary/build/libs/luposdate3000_dictionary-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_jena_wrapper_off/build/libs/luposdate3000_jena_wrapper_off-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_parser/build/libs/luposdate3000_parser-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_kv/build/libs/luposdate3000_kv-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_buffer_manager_inmemory/build/libs/luposdate3000_buffer_manager_inmemory-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_vk/build/libs/luposdate3000_vk-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_test_buffermanager/build/libs/luposdate3000_test_buffermanager-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_triple_store_id_triple/build/libs/luposdate3000_triple_store_id_triple-jvm-0.0.1.jar"
classpath="$classpath:/src/luposdate3000/src/luposdate3000_shared/build/libs/luposdate3000_shared-jvm-0.0.1.jar"
classpath="$classpath:/root/.gradle/caches/modules-2/files-2.1/com.soywiz.korlibs.krypto/krypto-jvm/1.9.1/4b5015389842f05ff9f7edfed791ccfc5b74dafa/krypto-jvm-1.9.1.jar"
classpath="$classpath:/root/.m2/repository/com/ionspin/kotlin/bignum-jvm/0.3.1-SNAPSHOT/bignum-jvm-0.3.1-SNAPSHOT.jar"
classpath="$classpath:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk8/1.5.10/3f4af7aff21c4ec46e3cdd645639d0a63a68d3d0/kotlin-stdlib-jdk8-1.5.10.jar"
classpath="$classpath:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-test-common/1.5.0/76b8f329f75b3d9116dda879b2c85e68035599b7/kotlin-test-common-1.5.0.jar"
classpath="$classpath:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-test-annotations-common/1.5.0/f7737b199fe1970c61110fec5259b2e06485902d/kotlin-test-annotations-common-1.5.0.jar"
classpath="$classpath:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-test-junit/1.5.0/ff74797b3ac346e79c22fd25434314fb3c099674/kotlin-test-junit-1.5.0.jar"
classpath="$classpath:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-test/1.5.0/f5e080ef0ab21bb6e38ef2da87205cb382f541eb/kotlin-test-1.5.0.jar"
classpath="$classpath:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-datetime-jvm/0.2.1/11f9a2e0cf44d986812d79ae78a545b6713971e5/kotlinx-datetime-jvm-0.2.1.jar"
classpath="$classpath:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk7/1.5.10/c49d0703d16c6cb1526cc07b9b46486da1dd8a60/kotlin-stdlib-jdk7-1.5.10.jar"
classpath="$classpath:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/1.5.10/da6a904b132f0402fa4d79169a3c1770598d4702/kotlin-stdlib-1.5.10.jar"
classpath="$classpath:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-common/1.5.10/6b84d926e28493be69daf673e40076f89492ef7/kotlin-stdlib-common-1.5.10.jar"
classpath="$classpath:/root/.gradle/caches/modules-2/files-2.1/junit/junit/4.12/2973d150c0dc1fefe998f834810d68f278ea58ec/junit-4.12.jar"
classpath="$classpath:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar"
classpath="$classpath:/root/.gradle/caches/modules-2/files-2.1/org.hamcrest/hamcrest-core/1.3/42a25dc3219429f0e5d060061f71acb49bf010a0/hamcrest-core-1.3.jar"
echo $classpath | tr ":" "\n"
mkdir src/tmp
cp -r /src/luposdate3000/src/luposdate3000_code_gen_test_*/* src/tmp/
cd src/tmp
java -cp $classpath org.junit.runner.JUnitCore $(find /src/luposdate3000/src/luposdate3000_code_gen_test_*/src/jvmTest/kotlin/lupos/code_gen_test_*/ -type f | sed "s/.kt$//g" | sed "s/.*code_gen_test/lupos.code_gen_test/g" | sed "s-/-\.-g" | tr "\n" " ")
cd ..
rm -rf tmp
