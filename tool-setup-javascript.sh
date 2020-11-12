#!/bin/bash
mkdir -p build-cache/bin
unzip $(find ~/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-js/1.4.255-SNAPSHOT/ -name "kotlin-stdlib-js-1.4.255-SNAPSHOT.jar") kotlin.js
mv kotlin.js build-cache/bin/kotlin.js
unzip $(find ~/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-js/1.4.255-SNAPSHOT/ -name "kotlin-stdlib-js-1.4.255-SNAPSHOT.jar") kotlin.js.map
mv kotlin.js.map build-cache/bin/kotlin.js.map

unzip $(find ~/.gradle/caches/modules-2/files-2.1/com.soywiz.korlibs.krypto/krypto-js/1.9.1 -name "krypto-js-1.9.1.jar") krypto-root-krypto.js
mv krypto-root-krypto.js build-cache/bin/krypto-root-krypto.js
unzip $(find ~/.gradle/caches/modules-2/files-2.1/com.soywiz.korlibs.krypto/krypto-js/1.9.1 -name "krypto-js-1.9.1.jar") krypto-root-krypto.js.map
mv krypto-root-krypto.js.map build-cache/bin/krypto-root-krypto.js.map

echo build-cache/bin/index.html
cat > build-cache/bin/index.html << EOF
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Luposdate3000</title>
    <script src="kotlin.js"></script>
    <script src="krypto-root-krypto.js"></script>
    <script src="Luposdate3000_Shared.js"></script>
    <script src="Luposdate3000_Buffer_Manager_Inmemory.js"></script>
    <script src="Luposdate3000_Dictionary_Inmemory.js"></script>
    <script src="Luposdate3000_Jena_Wrapper_Off.js"></script>
    <script src="Luposdate3000_Operators.js"></script>
    <script src="Luposdate3000_Parser.js"></script>
    <script src="Luposdate3000_Result_Format.js"></script>
    <script src="Luposdate3000_Triple_Store_Id_Triple.js"></script>
    <script src="Luposdate3000_Triple_Store_All.js"></script>
    <script src="Luposdate3000_Optimizer.js"></script>
    <script src="Luposdate3000_Endpoint.js"></script>
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
