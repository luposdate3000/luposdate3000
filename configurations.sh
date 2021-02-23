#!/bin/bash
for partition_mode in NoPartitions WithProcessPartitions WithThreadPartitions
#for partition_mode in NoPartitions
do
#for buffermanager in Inmemory Persistent
for buffermanager in Inmemory
do
#for endpoint_launcher in Java_Sockets None
for endpoint_launcher in Java_Sockets
do
#for jena in On Off
for jena in On
do
cat <<EOT > config.pro
-injars ./Luposdate3000_Buffer_Manager_${buffermanager}-jvm.jar
#-injars ./Luposdate3000_Code_Generator-jvm.jar
-injars ./Luposdate3000_Dictionary_Inmemory-jvm.jar
-injars ./Luposdate3000_Endpoint_${endpoint_launcher}-jvm.jar
-injars ./Luposdate3000_Endpoint-jvm.jar
-injars ./Luposdate3000_Jena_Wrapper_${jena}-jvm.jar
-injars ./Luposdate3000_Operators-jvm.jar
-injars ./Luposdate3000_Optimizer_${partition_mode}-jvm.jar
-injars ./Luposdate3000_Parser-jvm.jar
-injars ./Luposdate3000_Result_Format_${partition_mode}-jvm.jar
-injars ./Luposdate3000_Shared-jvm.jar
-injars ./Luposdate3000_Test-jvm.jar
-injars ./Luposdate3000_Triple_Store_All_${partition_mode}-jvm.jar
-injars ./Luposdate3000_Triple_Store_Id_Triple-jvm.jar
-outjars XXX_${partition_mode}_${buffermanager}_${endpoint_launcher}_${jena}.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/com.benasher44/uuid-jvm/0.0.7/208de8dbbaa3392a3b3dac5dad317ca4ec8646a9/uuid-jvm-0.0.7.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-annotations/2.10.1/54d72475c0d6819f2d0e9a09d25c3ed876a4972f/jackson-annotations-2.10.1.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-core/2.10.1/2c8b5e26ba40e5f91eb37a24075a2028b402c5f9/jackson-core-2.10.1.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-databind/2.10.1/18eee15ffc662d27538d5b6ee84e4c92c0a9d03e/jackson-databind-2.10.1.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/com.github.andrewoma.dexx/collection/0.7/264efc08bdcd22126bd429aaea9efaf5158b2b90/collection-0.7.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/com.github.jsonld-java/jsonld-java/0.12.5/f85d7947d40ad30803a00b0d268baf6a00c6d32b/jsonld-java-0.12.5.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/commons-cli/commons-cli/1.4/c51c00206bb913cd8612b24abd9fa98ae89719b1/commons-cli-1.4.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/commons-codec/commons-codec/1.13/3f18e1aa31031d89db6f01ba05d501258ce69d2c/commons-codec-1.13.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/commons-io/commons-io/2.6/815893df5f31da2ece4040fe0a12fd44b577afaf/commons-io-2.6.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/commons-logging/commons-logging/1.2/4bfc12adfe4842bf07b657f0369c4cb522955686/commons-logging-1.2.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/com.soywiz.korlibs.krypto/krypto-jvm/1.9.1/4b5015389842f05ff9f7edfed791ccfc5b74dafa/krypto-jvm-1.9.1.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/javax.annotation/javax.annotation-api/1.3.2/934c04d3cfef185a8008e7bf34331b79730a9d43/javax.annotation-api-1.3.2.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.apache.commons/commons-compress/1.19/7e65777fb451ddab6a9c054beb879e521b7eab78/commons-compress-1.19.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.apache.commons/commons-csv/1.7/cb5d05520f8fe1b409aaf29962e47dc5764f8f39/commons-csv-1.7.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.apache.commons/commons-lang3/3.9/122c7cee69b53ed4a7681c03d4ee4c0e2765da5/commons-lang3-3.9.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.apache.httpcomponents/httpclient/4.5.10/7ca2e4276f4ef95e4db725a8cd4a1d1e7585b9e5/httpclient-4.5.10.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.apache.httpcomponents/httpclient-cache/4.5.10/b195778247a21e980cb9f80c41364dc0c38feaef/httpclient-cache-4.5.10.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.apache.httpcomponents/httpcore/4.4.12/21ebaf6d532bc350ba95bd81938fa5f0e511c132/httpcore-4.4.12.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.apache.jena/jena-arq/3.14.0/22c7d14a0183754318490f49ac4674eda722e501/jena-arq-3.14.0.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.apache.jena/jena-base/3.14.0/8dc46d2966d93f3fa973fb1f4232ecac2c9400fb/jena-base-3.14.0.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.apache.jena/jena-core/3.14.0/85a06780b0c77275e093ed1ec2cc01c7f675de9/jena-core-3.14.0.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.apache.jena/jena-iri/3.14.0/8887c279fc6fc105658fa2d87044aed8c420da13/jena-iri-3.14.0.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.apache.jena/jena-shaded-guava/3.14.0/4ab6b2640bdd53bc03f11b0115836d35ec28f9f0/jena-shaded-guava-3.14.0.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.apache.thrift/libthrift/0.13.0/e5af3b6dc164eb2c699b70bf67a0babef507faf/libthrift-0.13.0.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.slf4j/jcl-over-slf4j/1.7.26/33fbc2d93de829fa5e263c5ce97f5eab8f57d53e/jcl-over-slf4j-1.7.26.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-api/1.7.26/77100a62c2e6f04b53977b9f541044d7d722693d/slf4j-api-1.7.26.jar
-libraryjars /root/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-simple/1.7.25/8dacf9514f0c707cbbcdd6fd699e8940d42fb54e/slf4j-simple-1.7.25.jar
-libraryjars /root/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib/1.5.255-SNAPSHOT/kotlin-stdlib-1.5.255-SNAPSHOT.jar
-libraryjars /root/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-common/1.5.255-SNAPSHOT/kotlin-stdlib-common-1.5.255-SNAPSHOT.jar
-libraryjars /root/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-jdk7/1.5.255-SNAPSHOT/kotlin-stdlib-jdk7-1.5.255-SNAPSHOT.jar
-libraryjars /root/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-jdk8/1.5.255-SNAPSHOT/kotlin-stdlib-jdk8-1.5.255-SNAPSHOT.jar
EOT
echo '-libraryjars /usr/lib/jvm/java-1.11.0-openjdk-amd64/jmods/java.base.jmod(!**.jar;!module-info.class)' >>config.pro
cat <<EOT >> config.pro
-forceprocessing
-optimizationpasses 5
-allowaccessmodification
-dontobfuscate
-printmapping XXX_${partition_mode}_${buffermanager}_${endpoint_launcher}_${jena}.mapping.txt
-printconfiguration XXX_${partition_mode}_${buffermanager}_${endpoint_launcher}_${jena}.config.pro.generated
-printusage XXX_${partition_mode}_${buffermanager}_${endpoint_launcher}_${jena}.usage.txt

#-keep public class MainKt {
#    public static void main(java.lang.String[]);
#}
-keep public class lupos.s16network.LuposdateEndpoint {
    *;
}
-keep public class lupos.s00misc.BinaryTestCase {
    *;
}
-keep public class lupos.s16network.HttpEndpointLauncher {
    *;
}
-keep public class lupos.SparqlTestSuite {
    *;
}

-ignorewarnings
EOT
/usr/lib/jvm/java-1.11.0-openjdk-amd64/bin/java -jar /usr/share/java/proguard.jar @config.pro > XXX_${partition_mode}_${buffermanager}_${endpoint_launcher}_${jena}.log 2>&1
done
done
done
done
