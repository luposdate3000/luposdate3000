FROM openjdk:14
COPY build/build_1.4.255-SNAPSHOT_jvm_Endpoint_On_Sequential_Heap_MultiMap_BPlusTree_Korio_On_BTree_BTree_Count_Empty_128_8_8_8_8_false_ECoverage.Disabled_DontChange_Ktor_2000/distributions/luposdate3000/lib /usr/src/myapp
COPY resources /usr/src/myapp/resources
WORKDIR /usr/src/myapp
EXPOSE 80
ENTRYPOINT ["java", "-classpath", "./*", "MainKt"]
