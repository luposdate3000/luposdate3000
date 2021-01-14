package lupos.s00misc
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToMemoryTable
import lupos.s11outputResult.QueryResultToXMLStream
import lupos.s15tripleStoreDistributed.distributedTripleStore
public object BinaryTestCase {
    private var outSummary = MyPrintWriter(false)
    private var lastInput = MemoryTable(Array(0) { "" })
    private fun rowToString(row: IntArray, dict: Array<String>): String {
        var res = "${row.map { it }}::"
        if (row.isNotEmpty()) {
            for (i in row.indices) {
                if (i > 0) {
                    res += ","
                }
                if (row[i] == -2) {
                    res += "_:b"
                } else if (row[i] >= 0) {
                    res += dict[row[i]]
                }
            }
        }
        return res
    }
    private fun helperCleanString(s: String): String {
        var res: String = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res) ?: break
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
        return res
    }
    public fun executeAllTestCase() {
        executeAllTestCase("resources/binary/")
    }
    public fun executeAllTestCase(folder: String) {
        outSummary = File("log/error").myPrintWriter()
        File("$folder/config2").printWriter { newConfig ->
            File("$folder/config").forEachLine { line ->
                val setting = line.split("=")
                try {
                    when (setting[1]) {
                        "disabled", "missingFeatures" -> {
                            newConfig.println(line)
                        }
                        "hadSuccess" -> {
                            val res = executeTestCase(folder + "/" + setting[0])
                            if (res) {
                                newConfig.println(line)
                            } else {
                                newConfig.println(setting[0] + "=hadSuccessButFailedNow")
                            }
                        }
                        else -> {
                            val res = executeTestCase(folder + "/" + setting[0])
                            if (res) {
                                newConfig.println(setting[0] + "=hadSuccess")
                            } else {
                                newConfig.println(setting[0] + "=enabled")
                            }
                        }
                    }
                } catch (e: NotImplementedException) {
                    newConfig.println(setting[0] + "=missingFeatures")
                } finally {
                    newConfig.flush()
                }
            }
            outSummary.close()
        }
    }
    private fun operatorGraphToTable(node: IOPBase, partition: Partition = Partition()): MemoryTable {
        println(node)
        val tmp = QueryResultToMemoryTable(node, partition)
        if (tmp.size != 1) {
            throw Exception("multi-queries are not supported right now")
        }
        return tmp[0]
    }
    private fun verifyEqual(expected: MemoryTable, actual: MemoryTable, mapping_live_to_target: Map<Int, Int>, dict: Map<String, Int>, dict2: Array<String>, allowOrderBy: Boolean, out: MyPrintWriter): Boolean {
        if (expected.booleanResult != null) {
            if (expected.booleanResult != actual.booleanResult) {
                out.println("invalid result to ask query expected:${expected.booleanResult} found:${actual.booleanResult}")
                return false
            }
        }
        val expectedRows = mutableListOf<IntArray>()
        val actualRows = mutableListOf<IntArray>()
        val columnCount = expected.columns.size
        if (expected.columns.size != actual.columns.size) {
            out.println("wrong result column count expected:${expected.columns.map { it }} found:${actual.columns.map { it }}")
            return false
        }
        for (i in 0 until columnCount) {
            val tmp = expected.columns.indexOf(actual.columns[i])
            if (tmp != i) {
                out.println("wrong column order expected:${expected.columns.map { it }} found:${actual.columns.map { it }}")
                return false
            }
        }
        for (row in actual.data) {
            val tmpRow = IntArray(columnCount) { -1 }
            for ((i, col) in row.withIndex()) {
                val m = mapping_live_to_target[col]
                val value = nodeGlobalDictionary.getValue(col).valueToString()
                if (m == null) {
                    if (value != null && !value.startsWith("_:")) {
                        out.println("found wrong $value")
                        out.println("row :: ${row.map { nodeGlobalDictionary.getValue(it).valueToString() }}")
                        out.println("dict :: $dict")
                        out.println("missing value in dictionary")
                    }
                } else {
                    if (value != null) {
                        if (!value.startsWith("_:")) {
                            tmpRow[i] = m
                        } else {
                            tmpRow[i] = -2
                        }
                    }
                }
            }
            actualRows.add(tmpRow)
        }
        for (row in expected.data) {
            val tmpRow = IntArray(columnCount) { -1 }
            for ((i, col) in row.withIndex()) {
                val m = mapping_live_to_target[col]
                val value = nodeGlobalDictionary.getValue(col).valueToString()
                if (m == null) {
                    if (value != null && !value.startsWith("_:")) {
                        out.println("found wrong $value")
                        out.println("row :: ${row.map { nodeGlobalDictionary.getValue(it).valueToString() }}")
                        out.println("dict :: $dict")
                        out.println("missing value in dictionary")
                    }
                } else {
                    if (value != null) {
                        if (!value.startsWith("_:")) {
                            tmpRow[i] = m
                        } else {
                            tmpRow[i] = -2
                        }
                    }
                }
            }
            expectedRows.add(tmpRow)
        }
        val comparator = IntArrayComparator()
        if (allowOrderBy) {
            expectedRows.sortWith(comparator)
            actualRows.sortWith(comparator)
        }
        var flag = false
        var idxExpected = 0
        var idxActual = 0
        while (idxExpected < expectedRows.size && idxActual < actualRows.size) {
            val tmp = comparator.compare(expectedRows[idxExpected], actualRows[idxActual])
            when {
                tmp < 0 -> {
                    out.println("missing row $allowOrderBy ${rowToString(expectedRows[idxExpected], dict2)}")
                    flag = true
                    idxExpected++
                }
                tmp > 0 -> {
                    out.println("additional row $allowOrderBy ${rowToString(actualRows[idxActual], dict2)}")
                    flag = true
                    idxActual++
                }
                else -> {
                    idxExpected++
                    idxActual++
                }
            }
        }
        while (idxExpected < expectedRows.size) {
            out.println("missing row $allowOrderBy ${rowToString(expectedRows[idxExpected], dict2)}")
            flag = true
            idxExpected++
        }
        while (idxActual < actualRows.size) {
            out.println("additional row $allowOrderBy ${rowToString(actualRows[idxActual], dict2)}")
            flag = true
            idxActual++
        }
        if (flag) {
            return false
        }
        return true
    }
    internal class IntArrayComparator : Comparator<IntArray> {
        override fun compare(a: IntArray, b: IntArray): Int {
            for (i in a.indices) {
                if (a[i] < b[i]) {
                    return -1
                } else if (a[i] > b[i]) {
                    return 1
                }
            }
            return 0
        }
    }
    private fun verifyEqual(expected: MemoryTable, actual: MemoryTable, mapping_live_to_target: Map<Int, Int>, dict: Map<String, Int>, dict2: Array<String>, allowOrderBy: Boolean, query_name: String, query_folder: String, tag: String): Boolean {
        val out = MyPrintWriter(true)
        val res = verifyEqual(expected, actual, mapping_live_to_target, dict, dict2, allowOrderBy, out)
        if (!res && tag != "this is no error") {
            out.println("----------Failed($tag)")
            val x = out.toString()
            SanityCheck.println { x }
            outSummary.println("Test: $query_folder named: $query_name")
            outSummary.println(x)
        }
        return res
    }
    private var notImplementedFeaturesList = mutableSetOf( //
        "rdfs:subPropertyOf", //
        "rdfs:subClassOf", //
        "rdfs:domain", //
        // "rdfs:label",//
        "rdfs:range", //
        // "owl:Class",//
        "owl:allValuesFrom", //
        "owl:complementOf", //
        "owl:DatatypeProperty", //
        "owl:intersectionOf", //
        "owl:maxQualifiedCardinality", //
        "owl:minCardinality", //
        "owl:minQualifiedCardinality", //
        "owl:Nothing", //
        "owl:ObjectProperty", //
        "owl:onClass", //
        "owl:onProperty", //
        "owl:qualifiedCardinality", //
        "owl:Restriction", //
        "owl:sameAs", //
        "owl:someValuesFrom", //
        "owl:Thing", //
        "owl:unionOf", //
        "<http://www.w3.org/2000/01/rdf-schema#domain>", //
        "<http://www.w3.org/2000/01/rdf-schema#range>", //
        // "<http://www.w3.org/2000/01/rdf-schema#label>",//
        "<http://www.w3.org/2000/01/rdf-schema#seeAlso>", //
        "<http://www.w3.org/2000/01/rdf-schema#subClassOf>", //
        "<http://www.w3.org/2000/01/rdf-schema#subPropertyOf>", //
        "<http://www.w3.org/2002/07/owl#allValuesFrom>", //
        // "<http://www.w3.org/2002/07/owl#Class>",//
        "<http://www.w3.org/2002/07/owl#DatatypeProperty>", //
        "<http://www.w3.org/2002/07/owl#disjointWith>", //
        "<http://www.w3.org/2002/07/owl#equivalentClass>", //
        "<http://www.w3.org/2002/07/owl#FunctionalProperty>", //
        "<http://www.w3.org/2002/07/owl#intersectionOf>", //
        "<http://www.w3.org/2002/07/owl#inverseOf>", //
        "<http://www.w3.org/2002/07/owl#minCardinality>", //
        "<http://www.w3.org/2002/07/owl#NamedIndividual>", //
        "<http://www.w3.org/2002/07/owl#Nothing>", //
        "<http://www.w3.org/2002/07/owl#ObjectProperty>", //
        "<http://www.w3.org/2002/07/owl#oneOf>", //
        "<http://www.w3.org/2002/07/owl#onProperty>", //
        "<http://www.w3.org/2002/07/owl#Ontology>", //
        "<http://www.w3.org/2002/07/owl#Restriction>", //
        "<http://www.w3.org/2002/07/owl#sameAs>", //
        "<http://www.w3.org/2002/07/owl#someValuesFrom>", //
        "<http://www.w3.org/2002/07/owl#Thing>", //
    )
    public fun executeTestCase(query_folder: String): Boolean {
        var returnValue = true
        File("$query_folder/query.stat").dataInputStream { targetStat ->
            File("$query_folder/query.dictionary").dataInputStream { targetDictionary ->
                File("$query_folder/query.triples").dataInputStream { targetTriples ->
                    File("$query_folder/query.result").dataInputStream { targetResult ->
                        func@ while (true) {
                            val mode = targetStat.readInt()
                            println("WWW stat.read 4 288 ${BinaryTestCaseOutputModeExt.names[mode]} outputmode")
                            val variables = mutableListOf<String>()
                            var targetResultCount = 0
                            when (mode) {
                                BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT, BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT_COUNT, BinaryTestCaseOutputModeExt.MODIFY_RESULT -> {
                                    val variablesSize = targetStat.readInt()
                                    println("WWW stat.read 4 294 $variablesSize variablesSize")
                                    for (i in 0 until variablesSize) {
                                        val len = targetStat.readInt()
                                        println("WWW stat.read 4 297 $len variablenamelength")
                                        val buf = ByteArray(len)
                                        val read = targetStat.read(buf, 0, len)
                                        println("WWW stat.read $len 300 ${buf.map{it}} variablename")
                                        if (read < len) {
                                            throw Exception("not enough data available")
                                        }
                                        variables.add(buf.decodeToString())
                                        println("WWW as String ${buf.decodeToString()}")
                                    }
                                    targetResultCount = targetStat.readInt()
                                    println("WWW stat.read 4 307 $targetResultCount resultcount")
                                    if (MAX_TRIPLES_DURING_TEST in 1 until targetResultCount) {
                                        SanityCheck.println { "Test: $query_folder named: $query_folder" }
                                        SanityCheck.println { "----------Skipped" }
                                        returnValue = true
                                        break@func
                                    }
                                }
                            }
                            val len = targetStat.readInt()
                            println("WWW stat.read 4 318 $len querynamelength")
                            val buf = ByteArray(len) { 0 }
                            val read = targetStat.read(buf, 0, len)
                            println("WWW stat.read $len 321 ${buf.map{it}} queryname")
                            if (read < len) {
                                throw Exception("not enough data available")
                            }
                            val queryName = buf.decodeToString()
                            println("WWW as String ${buf.decodeToString()}")
                            SanityCheck.println { "Test: $query_folder named: $queryName" }
                            val dictionarySize = targetStat.readInt()
                            println("WWW stat.read 4 329 $dictionarySize dictionarysize")
                            val targetInputCount = targetStat.readInt()
                            println("WWW stat.read 4 331 $targetInputCount resultcount")
                            if (MAX_TRIPLES_DURING_TEST in 1 until targetInputCount) {
                                SanityCheck.println { "Test: $query_folder named: $query_folder" }
                                SanityCheck.println { "----------Skipped" }
                                returnValue = true
                                break@func
                            }
                            val targetDict = mutableMapOf<String, Int>()
                            val targetDict2 = Array(dictionarySize) { "" }
                            val mappingTargetToLive = IntArray(dictionarySize) { 0 }
                            val mappingLiveToTarget = mutableMapOf(ResultSetDictionaryExt.undefValue to -1, ResultSetDictionaryExt.errorValue to -1, ResultSetDictionaryExt.nullValue to -1)
                            for (i in 0 until dictionarySize) {
                                val len2 = targetDictionary.readInt()
                                println("WWW dict.read 4 344 $len2")
                                val buf2 = ByteArray(len2)
                                val read2 = targetDictionary.read(buf2, 0, len2)
                                println("WWW dict.read $len 347 ${buf2.map{it}}")
                                if (read2 < len2) {
                                    throw Exception("not enough data available")
                                }
                                val s = buf2.decodeToString()
                                if (notImplementedFeaturesList.contains(s)) {
                                    throw object : NotImplementedException("NotImplementedException", "Inference not implemented '$s'") {}
                                }
                                if (s.startsWith("<http://www.w3.org/2000/01/rdf-schema") || s.startsWith("<http://www.w3.org/2002/07/owl")) {
                                    outSummary.println(s)
                                }
                                targetDict[s] = i
                                targetDict2[i] = s
                                SanityCheck.println { "XXX added value:$s" }
                                val tmp = nodeGlobalDictionary.createValue(s)
                                mappingTargetToLive[i] = tmp
                                mappingLiveToTarget[tmp] = i
                            }
                            val tableInput = MemoryTable(arrayOf("s", "p", "o"))
                            SanityCheck.println { "----------Triple-Store-Target" }
                            for (i in 0 until targetInputCount) {
                                val s1 = targetTriples.readInt()
                                println("WWW trip.read 4 369 $s1")
                                val p1 = targetTriples.readInt()
                                println("WWW trip.read 4 371 $s1")
                                val o1 = targetTriples.readInt()
                                println("WWW trip.read 4 373 $s1")
                                SanityCheck.println { "[$s1, $p1, $o1] :: [${targetDict2[s1]}, ${targetDict2[p1]}, ${targetDict2[o1]}]" }
                                val s = mappingTargetToLive[s1]
                                val p = mappingTargetToLive[p1]
                                val o = mappingTargetToLive[o1]
                                tableInput.data.add(intArrayOf(s, p, o))
                            }
                            if (!verifyEqual(lastInput, tableInput, mappingLiveToTarget, targetDict, targetDict2, true, queryName, query_folder, "this is no error")) {
                                val query1 = Query()
                                distributedTripleStore.getLocalStore().getDefaultGraph(query1).clear()
                                for (g in distributedTripleStore.getGraphNames()) {
                                    distributedTripleStore.dropGraph(query1, g)
                                }
                                distributedTripleStore.commit(query1)
                                query1.commited = true
                                val query2 = Query()
                                val store = distributedTripleStore.getDefaultGraph(query2)
                                store.bulkImport { bulk ->
                                    for (row in tableInput.data) {
                                        bulk.insert(row[0], row[1], row[2])
                                    }
                                }
                                val query3 = Query()
                                val queryParam = arrayOf<IAOPBase>(AOPVariable(query3, "s"), AOPVariable(query3, "p"), AOPVariable(query3, "o"))
                                val enablesPartitions = distributedTripleStore.getLocalStore().getDefaultGraph(query3).getEnabledPartitions()
                                var success = true
                                for (p in enablesPartitions) {
                                    val idx = p.index.toList().first()
                                    var tmpTable: MemoryTable? = null
                                    if (p.partitionCount == 1) {
                                        val node = distributedTripleStore.getDefaultGraph(query3).getIterator(queryParam, idx, Partition())
                                        tmpTable = operatorGraphToTable(OPBaseCompound(query3, arrayOf(node), listOf(listOf("s", "p", "o"))))
                                        SanityCheck.println { "storage content ${EIndexPatternExt.names[idx]} x/${p.partitionCount} '' ${tmpTable.columns.map { it }}" }
                                        for (r in tmpTable.data) {
                                            SanityCheck.println { r.map { it } }
                                        }
                                    } else {
                                        for (value in 0 until p.partitionCount) {
                                            val partition = Partition()
                                            val key = EIndexPatternExt.names[idx].substring(p.column, p.column + 1).toLowerCase()
                                            SanityCheck.println { "extractKey :: ${EIndexPatternExt.names[idx]} ${p.column} $key" }
                                            partition.limit[key] = p.partitionCount
                                            partition.data[key] = value
                                            val node = OPBaseCompound(
                                                query3,
                                                arrayOf(
                                                    POPSplitPartitionFromStore(
                                                        query3, listOf("s", "p", "o"), key, p.partitionCount, -1,
                                                        distributedTripleStore.getDefaultGraph(query3).getIterator(queryParam, idx, partition)
                                                    )
                                                ),
                                                listOf(listOf("s", "p", "o"))
                                            )
                                            val table = operatorGraphToTable(node, partition)
                                            SanityCheck.println { "storage content ${EIndexPatternExt.names[idx]} $value/${p.partitionCount} '$key' ${table.columns.map { it }}" }
                                            for (r in table.data) {
                                                SanityCheck.println { r.map { it } }
                                            }
/*
TODO
if (tmpTable != null) {
                                                tmpTable = MemoryTable(tmpTable!!, table)
                                            } else {
                                                tmpTable = table
                                            }
*/
                                        }
                                    }
                                    if (tmpTable != null) {
                                        success = verifyEqual(tableInput, tmpTable, mappingLiveToTarget, targetDict, targetDict2, true, queryName, query_folder, "import (${EIndexPatternExt.names[idx]} ${p.column} ${p.partitionCount})") && success
                                        SanityCheck.println { "success after idx ${EIndexPatternExt.names[idx]} $success" }
                                    }
                                }
                                if (!success) {
                                    returnValue = false
                                    SanityCheck.println { "----------Failed(import)" }
                                    break@func
                                }
                            }
                            val tableOutput = MemoryTable(variables.toTypedArray())
                            if (mode == BinaryTestCaseOutputModeExt.ASK_QUERY_RESULT) {
                                tableOutput.booleanResult = targetResult.readInt() == 1
                                println("WWW resu.read 4 455 ${tableOutput.booleanResult}")
                            } else {
                                for (i in 0 until targetResultCount) {
                                    val row = IntArray(variables.size) { -1 }
                                    for (j in 0 until variables.size) {
                                        val tmp = targetResult.readInt()
                                        println("WWW resu.read 4 461 $tmp")
                                        if (tmp == -1) {
                                            row[j] = ResultSetDictionaryExt.undefValue
                                        } else {
                                            row[j] = mappingTargetToLive[tmp]
                                        }
                                    }
                                    tableOutput.data.add(row)
                                }
                            }
                            SanityCheck.println { "----------String query" }
                            val toParse = File("$query_folder/query.sparql").readAsString()
                            SanityCheck.println { toParse }
                            for (f in notImplementedFeaturesList) {
                                if (toParse.contains(f)) {
                                    throw object : NotImplementedException("NotImplementedException", "Inference not implemented '$f'") {}
                                }
                            }
                            SanityCheck.println { "----------AST" }
                            val lcit = LexerCharIterator(toParse)
                            val tit = TokenIteratorSPARQLParser(lcit)
                            val ltit = LookAheadTokenIterator(tit, 3)
                            val parser = SPARQLParser(ltit)
                            val astNode = parser.expr()
                            SanityCheck.println { astNode }
                            SanityCheck.println { "----------Logical Operatorgraph" }
                            val query4 = Query()
                            val lopNode = astNode.visit(OperatorGraphVisitor(query4))
                            SanityCheck.println { lopNode.toXMLElement().toPrettyString() }
                            SanityCheck.println { "----------Logical Operatorgraph optimized" }
                            val lopNode2 = LogicalOptimizer(query4).optimizeCall(lopNode)
                            SanityCheck.println { lopNode2.toXMLElement().toPrettyString() }
                            SanityCheck.println { "----------Physical Operatorgraph optimized" }
                            val popOptimizer = PhysicalOptimizer(query4)
                            val popNode = popOptimizer.optimizeCall(lopNode2)
                            SanityCheck.println { popNode.toXMLElement().toPrettyString() }
                            val allowOrderBy = !toParse.toLowerCase().contains("order")
                            if (mode == BinaryTestCaseOutputModeExt.MODIFY_RESULT) {
                                val resultWriter = MyPrintWriter(false)
                                QueryResultToXMLStream(popNode, resultWriter)
                                val query5 = Query()
                                val popOptimizer = PhysicalOptimizer(query5)
                                val actualResult = operatorGraphToTable(popOptimizer.optimizeCall(distributedTripleStore.getDefaultGraph(query5).getIterator(arrayOf(AOPVariable(query5, "s"), AOPVariable(query5, "p"), AOPVariable(query5, "o")), EIndexPatternExt.SPO, Partition())))
                                if (!verifyEqual(tableOutput, actualResult, mappingLiveToTarget, targetDict, targetDict2, allowOrderBy, queryName, query_folder, "result in store (SPO) is wrong")) {
                                    returnValue = false
                                    break@func
                                }
                                distributedTripleStore.commit(query5)
                                query5.commited = true
                            } else {
                                val actualResult = operatorGraphToTable(popNode)
                                if (!verifyEqual(tableOutput, actualResult, mappingLiveToTarget, targetDict, targetDict2, allowOrderBy, queryName, query_folder, "query result is wrong")) {
                                    returnValue = false
                                    break@func
                                }
                            }
                            SanityCheck.println { "----------Success" }
                            returnValue = true
                            break@func
                        }
                    }
                }
            }
        }
        return returnValue
    }
    public fun generateTestcase(query_input_file: String, query_file: String, query_output_file: String, output_folder: String, query_name: String, output_mode_tmp: BinaryTestCaseOutputMode): Boolean {
        try {
            println("generating for $query_input_file $query_file $query_output_file $output_folder $query_name ${BinaryTestCaseOutputModeExt.names[output_mode_tmp]}")
            var outputMode = output_mode_tmp
            File(output_folder).deleteRecursively()
            File(output_folder).mkdirs()
            var containsOrderBy = false
            File("$output_folder/query.sparql").printWriter { out ->
                File(query_file).forEachLine { line ->
                    if (line.isNotEmpty()) {
                        if (line.toLowerCase().contains("order")) {
                            containsOrderBy = true
                        }
                        out.println(line)
                    }
                }
            }
            val dict = mutableMapOf<String, Int>()
            var dictBnodeCount = 0
            File("$output_folder/query.dictionary").dataOutputStream { outDictionary ->
                File("$output_folder/query.triples").dataOutputStream { outTriples ->
                    val data = XMLElement.parseFromAny(File(query_input_file).readAsString(), query_input_file)!!
                    var inputCounter = 0
                    for (node in data["results"]!!.childs) {
                        inputCounter++
                        val row = IntArray(3) { -1 }
                        for (v in node.childs) {
                            val i: Int = when (val name = v.attributes["name"]) {
                                "s" -> 0
                                "p" -> 1
                                "o" -> 2
                                else -> throw Exception("unknown name '$name'")
                            }
                            val child = v.childs.first()
                            val content = helperCleanString(child.content)
                            val datatype = child.attributes["datatype"]
                            val lang = child.attributes["xml:lang"]
                            if ((datatype != null) && (lang != null)) {
                                throw Exception("overspecification")
                            }
                            val s: String = when {
                                child.tag == "uri" -> "<$content>"
                                child.tag == "literal" && datatype != null -> "\"$content\"^^<$datatype>"
                                child.tag == "literal" && lang != null -> "\"$content\"@$lang"
                                child.tag == "bnode" -> "_:$content"
                                else -> "\"" + content + "\""
                            }
                            val id = dict[s]
                            if (id != null) {
                                row[i] = id
                            } else {
                                val id2 = dict.size
                                row[i] = id2
                                dict[s] = id2
                                val tmp: ByteArray = if (s.startsWith("_:")) {
                                    "_:b${dictBnodeCount++}".encodeToByteArray()
                                } else {
                                    s.encodeToByteArray()
                                }
                                outDictionary.writeInt(tmp.size)
                                println("WWW dict.write 4 587 ${tmp.size}")
                                outDictionary.write(tmp)
                                println("WWW dict.write ${tmp.size} 589 ${tmp.map{it}}")
                                println("WWW as String ${tmp.decodeToString()}")
                            }
                        }
                        for (i in 0 until 3) {
                            outTriples.writeInt(row[i])
                            println("WWW trip.write 4 595 ${row[i]}")
                        }
                    }
                    val target = XMLElement.parseFromAny(File(query_output_file).readAsString(), query_output_file)!!
                    if (target["results"] == null && target["boolean"] != null) {
                        outputMode = BinaryTestCaseOutputModeExt.ASK_QUERY_RESULT
                    }
                    File("$output_folder/query.stat").dataOutputStream { outStat ->
                        File("$output_folder/query.result").dataOutputStream { outResult ->
                            var resultCounter = 0
                            when (outputMode) {
                                BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT, BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT_COUNT, BinaryTestCaseOutputModeExt.MODIFY_RESULT -> {
                                    val variablesTmp = mutableListOf<String>()
                                    for (node in target["head"]!!.childs) {
                                        variablesTmp.add(node.attributes["name"]!!)
                                    }
                                    val variables = variablesTmp.toTypedArray()
                                    if (variables.isEmpty()) {
                                        outputMode = BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT_COUNT
                                    }
                                    outStat.writeInt(outputMode)
                                    println("WWW stat.write 4 616 ${BinaryTestCaseOutputModeExt.names[outputMode]} outputmode")
                                    outStat.writeInt(variables.size)
                                    println("WWW stat.write 4 618 ${variables.size} variables.size")
                                    for (element in variables) {
                                        val tmp = element.encodeToByteArray()
                                        outStat.writeInt(tmp.size)
                                        println("WWW stat.write 4 622 ${tmp.size} variablenamelength")
                                        outStat.write(tmp)
                                        println("WWW stat.write ${tmp.size} 624 ${tmp.map{it}} variablename")
                                        println("WWW as String ${tmp.decodeToString()}")
                                    }
                                    val allRows = mutableListOf<IntArray>()
                                    for (node in target["results"]!!.childs) {
                                        val rowOut = IntArray(variables.size) { -1 }
                                        resultCounter++
                                        for (v in node.childs) {
                                            val name = v.attributes["name"]
                                            val i = variables.indexOf(name)
                                            if (i < 0) {
                                                throw Exception("unknown name '$name'")
                                            }
                                            if (v.childs.size > 0) {
                                                val child = v.childs.first()
                                                val content = helperCleanString(child.content)
                                                val datatype = child.attributes["datatype"]
                                                val lang = child.attributes["xml:lang"]
                                                if ((datatype != null) && (lang != null)) {
                                                    throw Exception("overspecification")
                                                }
                                                val s: String = when {
                                                    child.tag == "uri" -> "<$content>"
                                                    child.tag == "literal" && datatype != null -> "\"$content\"^^<$datatype>"
                                                    child.tag == "literal" && lang != null -> "\"$content\"@$lang"
                                                    child.tag == "bnode" -> "_:$content"
                                                    else -> "\"" + content + "\""
                                                }
                                                val id = dict[s]
                                                if (id != null) {
                                                    rowOut[i] = id
                                                } else {
                                                    val id2 = dict.size
                                                    rowOut[i] = id2
                                                    dict[s] = id2
                                                    val tmp: ByteArray = if (s.startsWith("_:")) {
                                                        "_:b${dictBnodeCount++}".encodeToByteArray()
                                                    } else {
                                                        s.encodeToByteArray()
                                                    }
                                                    outDictionary.writeInt(tmp.size)
                                                    println("WWW dict.write 4 665 ${tmp.size}")
                                                    outDictionary.write(tmp)
                                                    println("WWW dict.write ${tmp.size} 667 ${tmp.map{it}}")
                                                    println("WWW as String ${tmp.decodeToString()}")
                                                }
                                            }
                                        }
                                        if (containsOrderBy) {
                                            for (i in 0 until variables.size) {
                                                outResult.writeInt(rowOut[i])
                                                println("WWW resu.write 4 675 ${rowOut[i]}")
                                            }
                                        } else {
                                            allRows.add(rowOut)
                                        }
                                    }
                                    outStat.writeInt(resultCounter)
                                    println("WWW stat.write 4 682 $resultCounter resultcounter")
                                    if (!containsOrderBy) {
                                        allRows.sortWith(IntArrayComparator())
                                        for (row2 in allRows) {
                                            for (i in 0 until variables.size) {
                                                outResult.writeInt(row2[i])
                                                println("WWW resu.write 4 688 ${row2[i]}")
                                            }
                                        }
                                    }
                                }
                                BinaryTestCaseOutputModeExt.ASK_QUERY_RESULT -> {
                                    outStat.writeInt(outputMode)
                                    println("WWW stat.write 4 695 ${BinaryTestCaseOutputModeExt.names[outputMode]} outputmode")
                                    if (target["boolean"]!!.content.toLowerCase() == "true") {
                                        outResult.writeInt(1)
                                        println("WWW resu.write 4 698 1")
                                    } else {
                                        outResult.writeInt(0)
                                        println("WWW resu.write 4 701 0")
                                    }
                                }
                                else -> {
                                    throw Exception("not implemented")
                                }
                            }
                            val tmp2 = query_name.encodeToByteArray()
                            outStat.writeInt(tmp2.size)
                            println("WWW stat.write 4 710 ${tmp2.size} querynamelength")
                            outStat.write(tmp2)
                            println("WWW stat.write ${tmp2.size} 712 ${tmp2.map{it}} queryname")
                            println("WWW as String ${tmp2.decodeToString()}")
                            outStat.writeInt(dict.size)
                            println("WWW stat.write 4 715 ${dict.size} dictsize")
                            outStat.writeInt(inputCounter)
                            println("WWW stat.write 4 717 $inputCounter inputcounter")
                            SanityCheck.println { "added Testcase $output_folder ${BinaryTestCaseOutputModeExt.names[outputMode]} (${BinaryTestCaseOutputModeExt.names[output_mode_tmp]}) $query_name $query_input_file $query_output_file $query_file" }
                        }
                    }
                }
            }
            return true
        } catch (e: UnknownDataFileException) {
            File(output_folder).deleteRecursively()
            e.printStackTrace()
            return false
        }
    }
}
