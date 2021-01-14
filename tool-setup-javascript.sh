#!/bin/bash
./tool-clear-caches.sh
./compile-module-all.main.kts --fastMode=JS

unzip $(find ~/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-js/1.4.255-SNAPSHOT/ -name "kotlin-stdlib-js-1.4.255-SNAPSHOT.jar") kotlin.js
mv kotlin.js build-cache/kotlin.js
unzip $(find ~/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-js/1.4.255-SNAPSHOT/ -name "kotlin-stdlib-js-1.4.255-SNAPSHOT.jar") kotlin.js.map
mv kotlin.js.map build-cache/kotlin.js.map

unzip $(find ~/.gradle/caches/modules-2/files-2.1/com.soywiz.korlibs.krypto/krypto-js/1.9.1 -name "krypto-js-1.9.1.jar") krypto-root-krypto.js
mv krypto-root-krypto.js build-cache/krypto-root-krypto.js
unzip $(find ~/.gradle/caches/modules-2/files-2.1/com.soywiz.korlibs.krypto/krypto-js/1.9.1 -name "krypto-js-1.9.1.jar") krypto-root-krypto.js.map
mv krypto-root-krypto.js.map build-cache/krypto-root-krypto.js.map

cat > build-cache/index.html << EOF
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Luposdate3000</title>
    <script src="kotlin.js"></script>
    <script src="krypto-root-krypto.js"></script>
    <script src="bin_Threads_NoInline_Debug/Luposdate3000_Shared.js"></script>
    <script src="bin_Threads_NoInline_Debug/Luposdate3000_Buffer_Manager_Inmemory/Luposdate3000_Buffer_Manager.js"></script>
    <script src="bin_Threads_NoInline_Debug/Luposdate3000_Dictionary_Inmemory/Luposdate3000_Dictionary.js"></script>
    <script src="bin_Threads_NoInline_Debug/Luposdate3000_Jena_Wrapper_Off/Luposdate3000_Jena_Wrapper.js"></script>
    <script src="bin_Threads_NoInline_Debug/Luposdate3000_Operators.js"></script>
    <script src="bin_Threads_NoInline_Debug/Luposdate3000_Parser.js"></script>
    <script src="bin_Threads_NoInline_Debug/Luposdate3000_Result_Format.js"></script>
    <script src="bin_Threads_NoInline_Debug/Luposdate3000_Triple_Store_Id_Triple.js"></script>
    <script src="bin_Threads_NoInline_Debug/Luposdate3000_Triple_Store_All_NoPartitions/Luposdate3000_Triple_Store_All.js"></script>
    <script src="bin_Threads_NoInline_Debug/Luposdate3000_Optimizer_NoPartitions/Luposdate3000_Optimizer.js"></script>
    <script src="bin_Threads_NoInline_Debug/Luposdate3000_Endpoint.js"></script>
</head>
<body>
<script>
Luposdate3000_Endpoint.lupos.s16network.LuposdateEndpoint.initialize()
Luposdate3000_Endpoint.lupos.s16network.LuposdateEndpoint.evaluate_sparql_to_result_b("INSERT DATA { <s> <p> <o> } ")
Luposdate3000_Endpoint.lupos.s16network.LuposdateEndpoint.evaluate_sparql_to_result_b("SELECT (5 as ?x) ?s {?s ?p ?o .}")
</script>
</body>
</html>
EOF
