#!/bin/bash
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
-injars ./Luposdate3000_Dictionary-jvm.jar
-injars ./Luposdate3000_Endpoint_${endpoint_launcher}-jvm.jar
-injars ./Luposdate3000_Endpoint-jvm.jar
-injars ./Luposdate3000_Jena_Wrapper_${jena}-jvm.jar
-injars ./Luposdate3000_Operators-jvm.jar
-injars ./Luposdate3000_Optimizer_Ast-jvm.jar
-injars ./Luposdate3000_Optimizer_Logical-jvm.jar
-injars ./Luposdate3000_Optimizer_Physical-jvm.jar
-injars ./Luposdate3000_Parser-jvm.jar
-injars ./Luposdate3000_Result_Format-jvm.jar
-injars ./Luposdate3000_Shared-jvm.jar
-injars ./Luposdate3000_Test-jvm.jar
-injars ./Luposdate3000_Triple_Store_Manager-jvm.jar
-injars ./Luposdate3000_Triple_Store_Id_Triple-jvm.jar
-injars ./Luposdate3000_Launch_Endpoint-jvm.jar
-injars ./Luposdate3000_Launch_Benchmark-jvm.jar
-injars ./Luposdate3000_Launch_Sparql_Test_Suite-jvm.jar
-injars ./Luposdate3000_Launch_Import-jvm.jar
-injars ./Luposdate3000_Launch_Code_Gen_Example_KAPT-jvm.jar
-injars ./Luposdate3000_Launch_Binary_Test_Suite-jvm.jar
-injars ./Luposdate3000_Launch_Generate_Binary_Test_Suite_Single-jvm.jar
-injars ./Luposdate3000_Launch_Generate_Binary_Test_Suite_Multi-jvm.jar
-outjars XXX_${buffermanager}_${endpoint_launcher}_${jena}.jar
EOT
cat *classpath | sort | uniq | sed "s/^/-libraryjars /g" >> config.pro
echo '-libraryjars /usr/lib/jvm/java-1.11.0-openjdk-amd64/jmods/java.base.jmod(!**.jar;!module-info.class)' >>config.pro
cat <<EOT >> config.pro
-forceprocessing
-optimizationpasses 5
-allowaccessmodification
-dontobfuscate
-printmapping XXX_${buffermanager}_${endpoint_launcher}_${jena}.mapping.txt
-printconfiguration XXX_${buffermanager}_${endpoint_launcher}_${jena}.config.pro.generated
-printusage XXX_${buffermanager}_${endpoint_launcher}_${jena}.usage.txt

-keep public class lupos.launch.** {
    *;
}

-keep public class lupos.s16network.LuposdateEndpoint{
    *;
}

-ignorewarnings
EOT
/usr/lib/jvm/java-1.11.0-openjdk-amd64/bin/java -jar /usr/share/java/proguard.jar @config.pro > XXX_${buffermanager}_${endpoint_launcher}_${jena}.log 2>&1
done
done
done
