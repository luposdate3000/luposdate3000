#!/bin/bash
echo "package lupos" > src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "import lupos.s02buildSyntaxTree.sparql1_1.*" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "import lupos.s03resultRepresentation.*" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "import lupos.s04arithmetikOperators.*" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "import lupos.s04arithmetikOperators.multiinput.*" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "import lupos.s04arithmetikOperators.noinput.*" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "import lupos.s04arithmetikOperators.singleinput.*" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "import lupos.s04logicalOperators.*" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "import lupos.s04logicalOperators.noinput.*" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "import lupos.s04logicalOperators.singleinput.*" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "import lupos.s04logicalOperators.singleinput.modifiers.*" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "import lupos.s08logicalOptimisation.*" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "import org.junit.jupiter.api.*" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "import org.junit.jupiter.api.Assertions.*" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "class AOPVariableTest {" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "    fun setAggregationMode(node: OPBase, mode: Boolean, count: Int) {" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "        for (n in node.children)" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "            setAggregationMode(n, mode, count)" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "        if (node is AOPAggregation) {" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "            node.count = count" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "            node.collectMode = mode" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "            if (node.collectMode)" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "                node.a = null" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "        }" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "    }" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
cat log/x | grep "^--MicroTest--" | sed "s/^--MicroTest--//g" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
echo "}" >> src/commonTest/kotlin/lupos/GeneratedTest.kt
