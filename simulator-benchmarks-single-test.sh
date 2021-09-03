rm -rf simulator_output
#./launcher.main.kts --setup --intellijMode=Disable
#./gradlew assemble
/usr/lib/jvm/java-16-openjdk-amd64/bin/java -server -XX:+UnlockExperimentalVMOptions -Xmx100g -XX:+UseShenandoahGC -XX:ShenandoahUncommitDelay=1000 -XX:ShenandoahGuaranteedGCInterval=10000 -cp /root/.gradle/caches/modules-2/files-2.1/com.soywiz.korlibs.krypto/krypto-jvm/1.9.1/4b5015389842f05ff9f7edfed791ccfc5b74dafa/krypto-jvm-1.9.1.jar:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-common/1.5.10/6b84d926e28493be69daf673e40076f89492ef7/kotlin-stdlib-common-1.5.10.jar:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk7/1.5.10/c49d0703d16c6cb1526cc07b9b46486da1dd8a60/kotlin-stdlib-jdk7-1.5.10.jar:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk8/1.5.10/3f4af7aff21c4ec46e3cdd645639d0a63a68d3d0/kotlin-stdlib-jdk8-1.5.10.jar:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/1.5.10/da6a904b132f0402fa4d79169a3c1770598d4702/kotlin-stdlib-1.5.10.jar:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-datetime-jvm/0.2.1/11f9a2e0cf44d986812d79ae78a545b6713971e5/kotlinx-datetime-jvm-0.2.1.jar:/root/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar:/root/.m2/repository/com/ionspin/kotlin/bignum-jvm/0.3.1-SNAPSHOT/bignum-jvm-0.3.1-SNAPSHOT.jar:src/luposdate3000_buffer_manager_inmemory/build/libs/luposdate3000_buffer_manager_inmemory-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_00/build/libs/luposdate3000_code_gen_test_00-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_01/build/libs/luposdate3000_code_gen_test_01-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_02/build/libs/luposdate3000_code_gen_test_02-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_03/build/libs/luposdate3000_code_gen_test_03-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_04/build/libs/luposdate3000_code_gen_test_04-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_05/build/libs/luposdate3000_code_gen_test_05-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_06/build/libs/luposdate3000_code_gen_test_06-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_07/build/libs/luposdate3000_code_gen_test_07-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_08/build/libs/luposdate3000_code_gen_test_08-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_09/build/libs/luposdate3000_code_gen_test_09-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_10/build/libs/luposdate3000_code_gen_test_10-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_11/build/libs/luposdate3000_code_gen_test_11-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_12/build/libs/luposdate3000_code_gen_test_12-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_13/build/libs/luposdate3000_code_gen_test_13-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_14/build/libs/luposdate3000_code_gen_test_14-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_15/build/libs/luposdate3000_code_gen_test_15-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_16/build/libs/luposdate3000_code_gen_test_16-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_17/build/libs/luposdate3000_code_gen_test_17-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_18/build/libs/luposdate3000_code_gen_test_18-jvm-0.0.1.jar:src/luposdate3000_code_gen_test_19/build/libs/luposdate3000_code_gen_test_19-jvm-0.0.1.jar:src/luposdate3000_code_generator_shared/build/libs/luposdate3000_code_generator_shared-jvm-0.0.1.jar:src/luposdate3000_dictionary/build/libs/luposdate3000_dictionary-jvm-0.0.1.jar:src/luposdate3000_endpoint/build/libs/luposdate3000_endpoint-jvm-0.0.1.jar:src/luposdate3000_endpoint_launcher_java_sockets/build/libs/luposdate3000_endpoint_launcher_java_sockets-jvm-0.0.1.jar:src/luposdate3000_jena_wrapper_off/build/libs/luposdate3000_jena_wrapper_off-jvm-0.0.1.jar:src/luposdate3000_kv/build/libs/luposdate3000_kv-jvm-0.0.1.jar:src/luposdate3000_launch_simulator_config/build/libs/luposdate3000_launch_simulator_config-jvm-0.0.1.jar:src/luposdate3000_operator_arithmetik/build/libs/luposdate3000_operator_arithmetik-jvm-0.0.1.jar:src/luposdate3000_operator_base/build/libs/luposdate3000_operator_base-jvm-0.0.1.jar:src/luposdate3000_operator_factory/build/libs/luposdate3000_operator_factory-jvm-0.0.1.jar:src/luposdate3000_operator_logical/build/libs/luposdate3000_operator_logical-jvm-0.0.1.jar:src/luposdate3000_operator_physical/build/libs/luposdate3000_operator_physical-jvm-0.0.1.jar:src/luposdate3000_optimizer_ast/build/libs/luposdate3000_optimizer_ast-jvm-0.0.1.jar:src/luposdate3000_optimizer_distributed_query/build/libs/luposdate3000_optimizer_distributed_query-jvm-0.0.1.jar:src/luposdate3000_optimizer_logical/build/libs/luposdate3000_optimizer_logical-jvm-0.0.1.jar:src/luposdate3000_optimizer_physical/build/libs/luposdate3000_optimizer_physical-jvm-0.0.1.jar:src/luposdate3000_parser/build/libs/luposdate3000_parser-jvm-0.0.1.jar:src/luposdate3000_result_format/build/libs/luposdate3000_result_format-jvm-0.0.1.jar:src/luposdate3000_shared/build/libs/luposdate3000_shared-jvm-0.0.1.jar:src/luposdate3000_simulator_core/build/libs/luposdate3000_simulator_core-jvm-0.0.1.jar:src/luposdate3000_simulator_db/build/libs/luposdate3000_simulator_db-jvm-0.0.1.jar:src/luposdate3000_simulator_iot/build/libs/luposdate3000_simulator_iot-jvm-0.0.1.jar:src/luposdate3000_spa_client/build/libs/luposdate3000_spa_client-jvm-0.0.1.jar:src/luposdate3000_test/build/libs/luposdate3000_test-jvm-0.0.1.jar:src/luposdate3000_test_buffermanager/build/libs/luposdate3000_test_buffermanager-jvm-0.0.1.jar:src/luposdate3000_test_dictionary_encoding/build/libs/luposdate3000_test_dictionary_encoding-jvm-0.0.1.jar:src/luposdate3000_triple_store_id_triple/build/libs/luposdate3000_triple_store_id_triple-jvm-0.0.1.jar:src/luposdate3000_triple_store_manager/build/libs/luposdate3000_triple_store_manager-jvm-0.0.1.jar:src/luposdate3000_visualize_distributed_database/build/libs/luposdate3000_visualize_distributed_database-jvm-0.0.1.jar:src/luposdate3000_vk/build/libs/luposdate3000_vk-jvm-0.0.1.jar MainKt src/luposdate3000_simulator_iot/src/jvmMain/resources/campus.json src/luposdate3000_simulator_iot/src/jvmMain/resources/distributed.json src/luposdate3000_simulator_iot/src/jvmMain/resources/Q3.json src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000_by_id_2_all_collations.json src/luposdate3000_simulator_iot/src/jvmMain/resources/evaluation.json src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000.json src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000_distribution_routing.json src/luposdate3000_simulator_iot/src/jvmMain/resources/luposdate3000MulticastDisabled.json
csvtool transpose \
simulator_output/_campus_distributed_Q3_luposdate3000_by_id_2_all_collations_evaluation_luposdate3000_luposdate3000_distribution_routing_luposdate3000MulticastDisabled/measurement.csv\
 | column -s, -t
