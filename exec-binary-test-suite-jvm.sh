#!/bin/bash

export JAVA_OPTS="-Xmx60g"
export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64
export LUPOS_HOME=/tmp/luposdate3000-test/
rm -rf $LUPOS_HOME build-cache/bin-effective
mkdir build-cache/bin-effective log
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Buffer_Manager_Inmemory-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Buffer_Manager_Inmemory-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Dictionary_Inmemory-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Dictionary_Inmemory-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Endpoint_Java_Sockets-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Endpoint_Java_Sockets-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Endpoint-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Endpoint-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Endpoint_None-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Endpoint_None-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Jena_Wrapper_Off-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Jena_Wrapper_Off-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Launch_Binary_Test_Suite-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Launch_Binary_Test_Suite-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Operators-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Operators-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Optimizer-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Optimizer-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Parser-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Parser-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Result_Format-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Result_Format-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Shared-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Shared-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Test-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Test-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Triple_Store_All-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Triple_Store_All-jvm.jar"
ln -s "$(pwd)/build-cache/bin/Luposdate3000_Triple_Store_Id_Triple-jvm.jar" "$(pwd)/build-cache/bin-effective/Luposdate3000_Triple_Store_Id_Triple-jvm.jar"
ln -s /root/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib/1.4.255-SNAPSHOT/kotlin-stdlib-1.4.255-SNAPSHOT.jar "$(pwd)/build-cache/bin-effective/kotlin-stdlib.jar"
java -Xmx60g -cp $(printf %s: $(pwd)/build-cache/bin-effective/*.jar) MainKt $@ > log/x 2>&1

cat log/x | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line"|grep -v "<h1>Success</h1>"| sort | uniq -c | sed "s/kotlin.//g" | sed "s/java.lang.//g"
diff resources/binary/configsequential resources/binary/config2 -y | grep -e "|" -e "<" -e ">"
