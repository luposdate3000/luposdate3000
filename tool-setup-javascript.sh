#!/bin/bash

unzip $(find ~/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-js/1.4.255-SNAPSHOT/ -name "kotlin-stdlib-js-1.4.255-SNAPSHOT.jar") kotlin.js
mv kotlin.js build-cache/bin/kotlin-stdlib.js

unzip $(find ~/.gradle/caches/modules-2/files-2.1/com.soywiz.korlibs.krypto/krypto-js/1.9.1 -name "krypto-js-1.9.1.jar") krypto-root-krypto.js
mv krypto-root-krypto.js build-cache/bin/krypto-js-1.9.1.js

echo build-cache/bin/index.html
cat > build-cache/bin/index.html << EOF
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Luposdate3000</title>
    <script src="kotlin-stdlib.js"></script>
    <script src="krypto-js-1.9.1.js"></script>
    <script src="Luposdate3000_Shared-js.js"></script>
    <script src="Luposdate3000_Buffer_Manager_Inmemory-js.js"></script>
    <script src="Luposdate3000_Dictionary_Inmemory-js.js"></script>
    <script src="Luposdate3000_Jena_Wrapper_Off-js.js"></script>
    <script src="Luposdate3000_Operators-js.js"></script>
    <script src="Luposdate3000_Parser-js.js"></script>
    <script src="Luposdate3000_Result_Format-js.js"></script>
    <script src="Luposdate3000_Triple_Store_Id_Triple-js.js"></script>
    <script src="Luposdate3000_Triple_Store_All-js.js"></script>
    <script src="Luposdate3000_Optimizer-js.js"></script>
    <script src="Luposdate3000_Endpoint-js.js"></script>
</head>
<body>
<script>
Luposdate3000_Endpoint.lupos.s16network.LuposdateEndpoint.initialize()
Luposdate3000_Endpoint.lupos.s16network.LuposdateEndpoint.evaluate_sparql_to_result_b("SELECT (5 as ?x) ?s {?s ?p ?o .}")
</script>
</body>
</html>
EOF
