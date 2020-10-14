package lupos.s00misc

import java.io.PrintWriter
import java.io.StringWriter
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.DateHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.File
import lupos.s00misc.JenaBugException
import lupos.s00misc.JenaWrapper
import lupos.s00misc.Luposdate3000Exception
import lupos.s00misc.MAX_TRIPLES_DURING_TEST
import lupos.s00misc.MemoryTable
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.NotImplementedException
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.UnknownManifestException
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.ParseError
import lupos.s02buildSyntaxTree.rdf.BlankNode
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s02buildSyntaxTree.rdf.ID_Triple
import lupos.s02buildSyntaxTree.rdf.IRI
import lupos.s02buildSyntaxTree.rdf.SimpleLiteral
import lupos.s02buildSyntaxTree.sparql1_1.parseSPARQL
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithDictionary
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.index_IDTriple.NodeManager
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPValuesImportXML
import lupos.s09physicalOperators.POPBase
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToMemoryTable
import lupos.s11outputResult.QueryResultToXMLElement
import lupos.s11outputResult.QueryResultToXMLStream
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s14endpoint.convertToOPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.s16network.HttpEndpoint
import lupos.s16network.ServerCommunicationSend
import lupos.SparqlTestSuite

enum class BinaryTestCaseOutputMode {
    SELECT_QUERY_RESULT,
    SELECT_QUERY_RESULT_COUNT,
    ASK_QUERY_RESULT,
    MODIFY_RESULT
}

enum class BinaryTestCaseGenerateTestCaseMode {
    Single,
    TestSuite
}

class SparqlTestSuiteConverter(resource_folder: String, val output_folder: String) : SparqlTestSuite() {
    var counter = 0
    var lastFile: String = ""

    init {
        prefixDirectory = resource_folder + "/"
    }

    override fun parseSPARQLAndEvaluate(//
            executeJena: Boolean,
            testName: String,//
            expectedResult: Boolean,//
            queryFile: String,//
            inputDataFileName: String?,//
            resultDataFileName: String?,//
            services: List<Map<String, String>>?,//
            inputDataGraph: MutableList<MutableMap<String, String>>,//
            outputDataGraph: MutableList<MutableMap<String, String>>//
    ): Boolean {
        if (services != null && services.size > 0) {
            return false
        }
        if (inputDataGraph.size > 0) {
            return false
        }
        if (inputDataFileName == null) {
            return false
        }
        var inputFile = inputDataFileName
        if (inputFile == "#keep-data#") {
            inputFile = lastFile
        }
        lastFile = inputFile
        var outputFile = resultDataFileName
        var mode = BinaryTestCaseOutputMode.SELECT_QUERY_RESULT
        if (inputDataFileName == null) {
            return false
        }
        if (outputFile == null) {
            if (outputDataGraph.size == 1 && outputDataGraph[0]["name"] == "") {
                mode = BinaryTestCaseOutputMode.MODIFY_RESULT
                outputFile = outputDataGraph[0]["filename"]
            } else {
                return false
            }
        } else {
            if (outputDataGraph.size > 0) {
                return false
            }
        }
        var tmp = BinaryTestCase.generateTestcase(inputFile, queryFile, outputFile!!, output_folder + "/${counter++}/", testName, mode)
        if (!tmp) {
            counter--
        }
        return true
    }
}

object BinaryTestCase {
    var ourSummaryBuf = StringWriter()
    var outSummary = PrintWriter(ourSummaryBuf)
    fun rowToString(row: IntArray, dict: Array<String>): String {
        var res = "${row.map{it}}::"
        if (row.size > 0) {
            for (i in 0 until row.size) {
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

    fun helper_clean_string(s: String): String {
        var res: String = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
            if (match == null) {
                break
            }
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
        return res
    }

    fun executeAllTestCase(folder: String = "resources/binary/") {
        outSummary = java.io.File("log/error").printWriter()
        java.io.File(folder + "/config2").printWriter().use { newConfig ->
        java.io.File(folder + "/config").forEachLine { line ->
            val setting = line.split("=")
            try {
                when (setting[1]) {
                    "disabled", "missingFeatures" -> {
                        newConfig.println(line)
                    }
                    "hadSuccess" -> {
                        newConfig.println(line)
                        executeTestCase(folder + "/" + setting[0])
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

            }finally{
newConfig.flush()
}
        }
        outSummary.close()
        }
    }

    fun operatorGraphToTable(node: OPBase): MemoryTable {
        val tmp = QueryResultToMemoryTable(node)
        if (tmp.size != 1) {
            throw Exception("multi-queries are not supported right now")
        }
        return tmp[0]
    }

    fun verifyEqual(expected: MemoryTable, actual: MemoryTable, mapping_live_to_target: Map<Int, Int>, dict: Map<String, Int>, dict2: Array<String>, allowOrderBy: Boolean, out: PrintWriter): Boolean {
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
            var idx = 0
            for (col in row) {
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
                            tmpRow[idx] = m
                        } else {
                            tmpRow[idx] = -2
                        }
                    }
                }
                idx++
            }
            actualRows.add(tmpRow)
        }
        for (row in expected.data) {
            val tmpRow = IntArray(columnCount) { -1 }
            var idx = 0
            for (col in row) {
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
                            tmpRow[idx] = m
                        } else {
                            tmpRow[idx] = -2
                        }
                    }
                }
                idx++
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
            if (tmp < 0) {
                out.println("missing row $allowOrderBy ${rowToString(expectedRows[idxExpected], dict2)}")
                flag = true
                idxExpected++
            } else if (tmp > 0) {
                out.println("additional row $allowOrderBy ${rowToString(actualRows[idxActual], dict2)}")
                flag = true
                idxActual++
            } else {
                idxExpected++
                idxActual++
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

    class IntArrayComparator : Comparator<IntArray> {
        override fun compare(p1: IntArray, p2: IntArray): Int {
            for (i in 0 until p1.size) {
if(p1[i]<p2[i]){
return -1
}else if(p1[i]>p2[i]){
return 1
}
            }
            return 0
        }
    }

    fun verifyEqual(expected: MemoryTable, actual: MemoryTable, mapping_live_to_target: Map<Int, Int>, dict: Map<String, Int>, dict2: Array<String>, allowOrderBy: Boolean, query_name: String, query_folder: String): Boolean {
        val buf = StringWriter()
        val out = PrintWriter(buf)
        val res = verifyEqual(expected, actual, mapping_live_to_target, dict, dict2, allowOrderBy, out)
        if (!res) {
            out.println("----------Failed")
            val x = buf.toString()
            println(x)
            outSummary.println("Test: $query_folder named: $query_name")
            outSummary.println(x)
        }
        return res
    }

    fun executeTestCase(query_folder: String): Boolean {
        val targetStat = java.io.DataInputStream(java.io.BufferedInputStream(java.io.FileInputStream(query_folder + "/query.stat")))
        val mode_id = targetStat.readInt()
        val mode = BinaryTestCaseOutputMode.values()[mode_id]
        val variables = mutableListOf<String>()
        var target_result_count = 0
        when (mode) {
            BinaryTestCaseOutputMode.SELECT_QUERY_RESULT, BinaryTestCaseOutputMode.SELECT_QUERY_RESULT_COUNT, BinaryTestCaseOutputMode.MODIFY_RESULT -> {
                val variablesSize = targetStat.readInt()
                for (i in 0 until variablesSize) {
                    val len = targetStat.readInt()
                    val buf = ByteArray(len)
                    val read = targetStat.read(buf, 0, len)
                    if (read < len) {
                        throw Exception("not enough data available")
                    }
                    variables.add(buf.decodeToString())
                }
                target_result_count = targetStat.readInt()
                if (target_result_count > MAX_TRIPLES_DURING_TEST && MAX_TRIPLES_DURING_TEST > 0) {
                    println("Test: $query_folder named: $query_folder")
                    println("----------Skipped")
                    targetStat.close()
                    return true
                }
            }
        }
        val len = targetStat.readInt()
        val buf = ByteArray(len) { 0 }
        val read = targetStat.read(buf, 0, len)
        if (read < len) {
            throw Exception("not enough data available")
        }
        val query_name = buf.decodeToString()
        println("Test: $query_folder named: $query_name")
        val dictionarySize = targetStat.readInt()
        val target_input_count = targetStat.readInt()
        targetStat.close()
        if (target_input_count > MAX_TRIPLES_DURING_TEST && MAX_TRIPLES_DURING_TEST > 0) {
            println("Test: $query_folder named: $query_folder")
            println("----------Skipped")
            return true
        }
        val targetDictionary = java.io.DataInputStream(java.io.BufferedInputStream(java.io.FileInputStream(query_folder + "/query.dictionary")))
        val targetDict = mutableMapOf<String, Int>()
        val targetDict2 = Array<String>(dictionarySize) { "" }
        val mapping_target_to_live = IntArray(dictionarySize) { 0 }
        val mapping_live_to_target = mutableMapOf<Int, Int>(ResultSetDictionary.undefValue to -1, ResultSetDictionary.errorValue to -1, ResultSetDictionary.nullValue to -1)
        for (i in 0 until dictionarySize) {
            val len = targetDictionary.readInt()
            val buf = ByteArray(len)
            val read = targetDictionary.read(buf, 0, len)
            if (read < len) {
                throw Exception("not enough data available")
            }
            val s = buf.decodeToString()
            targetDict[s] = i
            targetDict2[i] = s
            val tmp = nodeGlobalDictionary.createValue(s)
            mapping_target_to_live[i] = tmp
            mapping_live_to_target[tmp] = i
        }
        targetDictionary.close()
        val targetTriples = java.io.DataInputStream(java.io.BufferedInputStream(java.io.FileInputStream(query_folder + "/query.triples")))
        val query1 = Query()
        ServerCommunicationSend.graphClearAll(query1)
        query1.commit()
        val tableInput = MemoryTable(arrayOf("s", "p", "o"))
        val query2 = Query()
        var store = DistributedTripleStore.getDefaultGraph(query2)
        store.bulkImport { bulk ->
            for (i in 0 until target_input_count) {
                val s = mapping_target_to_live[targetTriples.readInt()]
                val p = mapping_target_to_live[targetTriples.readInt()]
                val o = mapping_target_to_live[targetTriples.readInt()]
                tableInput.data.add(intArrayOf(s, p, o))
                bulk.insert(s, p, o)
            }
        }
        val query3 = Query()
//TODO test other indices too ... 
        val tmpTable = operatorGraphToTable(DistributedTripleStore.getDefaultGraph(query3).getIterator(arrayOf(AOPVariable(query3, "s"), AOPVariable(query3, "p"), AOPVariable(query3, "o")), EIndexPattern.SPO, Partition()))
        if (!verifyEqual(tableInput, tmpTable, mapping_live_to_target, targetDict, targetDict2, true, query_name, query_folder)) {
            return false
        }
        val targetResult = java.io.DataInputStream(java.io.BufferedInputStream(java.io.FileInputStream(query_folder + "/query.result")))
        val tableOutput = MemoryTable(variables.toTypedArray())
        if (mode == BinaryTestCaseOutputMode.ASK_QUERY_RESULT) {
            tableOutput.booleanResult = targetResult.readInt() == 1
        } else {
            for (i in 0 until target_result_count) {
                val row = IntArray(variables.size) { -1 }
                for (j in 0 until variables.size) {
                    val tmp = targetResult.readInt()
                    if (tmp == -1) {
                        row[j] = ResultSetDictionary.undefValue
                    } else {
                        row[j] = mapping_target_to_live[tmp]
                    }
                }
                tableOutput.data.add(row)
            }
        }
        val toParse = File(query_folder + "/query.sparql").readAsString()
        val lcit = LexerCharIterator(toParse)
        val tit = TokenIteratorSPARQLParser(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        val parser = SPARQLParser(ltit)
        val ast_node = parser.expr()
        val query4 = Query()
        val lop_node = ast_node.visit(OperatorGraphVisitor(query4))
        val lop_node2 = LogicalOptimizer(query4).optimizeCall(lop_node)
        val pop_optimizer = PhysicalOptimizer(query4)
        val pop_node = pop_optimizer.optimizeCall(lop_node2)
        val pop_distributed_node = KeyDistributionOptimizer(query4).optimizeCall(pop_node)
        val allowOrderBy = !toParse.toLowerCase().contains("order")
        if (mode == BinaryTestCaseOutputMode.MODIFY_RESULT) {
            val resultBuf = StringWriter()
            val resultWriter = PrintWriter(resultBuf)
            QueryResultToXMLStream(pop_distributed_node, resultWriter)
            val actualResult = operatorGraphToTable(DistributedTripleStore.getDefaultGraph(query3).getIterator(arrayOf(AOPVariable(query3, "s"), AOPVariable(query3, "p"), AOPVariable(query3, "o")), EIndexPattern.SPO, Partition()))
            if (!verifyEqual(tableOutput, actualResult, mapping_live_to_target, targetDict, targetDict2, allowOrderBy, query_name, query_folder)) {
                return false
            }
        } else {
            val actualResult = operatorGraphToTable(pop_distributed_node)
            if (!verifyEqual(tableOutput, actualResult, mapping_live_to_target, targetDict, targetDict2, allowOrderBy, query_name, query_folder)) {
                return false
            }
        }
        targetTriples.close()
        targetResult.close()
        println("----------Success")
        return true
    }

    fun generateTestcase(query_input_file: String, query_file: String, query_output_file: String, output_folder: String, query_name: String, output_mode_tmp: BinaryTestCaseOutputMode): Boolean {
        println("generateTestcase.kts $query_input_file $query_file $query_output_file $output_folder $query_name $output_mode_tmp")
        try {
            var output_mode = output_mode_tmp
            java.io.File(output_folder).deleteRecursively()
            java.io.File(output_folder).mkdirs()
            var containsOrderBy = false
            java.io.File(output_folder + "/query.sparql").printWriter().use { out ->
                java.io.File(query_file).forEachLine { line ->
                    if (line.length > 0) {
                        if (line.toLowerCase().contains("order")) {
                            containsOrderBy = true
                        }
                        out.println(line)
                    }
                }
            }
            val dict = mutableMapOf<String, Int>()
            var dict_bnode_count = 0
            val outDictionary = java.io.DataOutputStream(java.io.BufferedOutputStream(java.io.FileOutputStream(output_folder + "/query.dictionary")))
            val outTriples = java.io.DataOutputStream(java.io.BufferedOutputStream(java.io.FileOutputStream(output_folder + "/query.triples")))
            val data = XMLElement.parseFromAny(java.io.File(query_input_file).readText(), query_input_file)!!
            var input_counter = 0
            for (node in data["results"]!!.childs) {
                input_counter++
                val row = IntArray(3) { -1 }
                for (v in node.childs) {
                    val name = v.attributes["name"]
                    val idx: Int
                    when (name) {
                        "s" -> idx = 0
                        "p" -> idx = 1
                        "o" -> idx = 2
                        else -> throw Exception("unknown name '$name'")
                    }
                    val child = v.childs.first()
                    val content = helper_clean_string(child.content)
                    val datatype = child.attributes["datatype"]
                    val lang = child.attributes["xml:lang"]
                    if ((datatype != null) && (lang != null)) {
                        throw Exception("overspecification")
                    }
                    val s: String
                    when {
                        child.tag == "uri" -> s = "<" + content + ">"
                        child.tag == "literal" && datatype != null -> s = "\"" + content + "\"^^<" + datatype + ">"
                        child.tag == "literal" && lang != null -> s = "\"" + content + "\"@" + lang
                        child.tag == "bnode" -> s = "_:" + content
                        else -> s = "\"" + content + "\""
                    }
                    val id = dict[s]
                    if (id != null) {
                        row[idx] = id
                    } else {
                        val id2 = dict.size
                        row[idx] = id2
                        dict[s] = id2
                        val tmp: ByteArray
                        if (s.startsWith("_:")) {
                            tmp = "_:b${dict_bnode_count++}".encodeToByteArray()
                        } else {
                            tmp = s.encodeToByteArray()
                        }
                        outDictionary.writeInt(tmp.size)
                        outDictionary.write(tmp)
                    }
                }
                for (i in 0 until 3) {
                    outTriples.writeInt(row[i])
                }
            }
            outTriples.close()
            var target = XMLElement.parseFromAny(java.io.File(query_output_file).readText(), query_output_file)!!
            if (target["results"] == null && target["boolean"] != null) {
                output_mode = BinaryTestCaseOutputMode.ASK_QUERY_RESULT
            }
            val outStat = java.io.DataOutputStream(java.io.BufferedOutputStream(java.io.FileOutputStream(output_folder + "/query.stat")))
            val outResult = java.io.DataOutputStream(java.io.BufferedOutputStream(java.io.FileOutputStream(output_folder + "/query.result")))
            var resultCounter = 0
            when (output_mode) {
                BinaryTestCaseOutputMode.SELECT_QUERY_RESULT, BinaryTestCaseOutputMode.SELECT_QUERY_RESULT_COUNT, BinaryTestCaseOutputMode.MODIFY_RESULT -> {
                    val variablesSet = mutableSetOf<String>()
                    for (node in target["results"]!!.childs) {
                        for (v in node.childs) {
                            variablesSet.add(v.attributes["name"]!!)
                        }
                    }
                    val variables = variablesSet.toTypedArray()
                    if (variables.size == 0) {
                        output_mode = BinaryTestCaseOutputMode.SELECT_QUERY_RESULT_COUNT
                    }
                    outStat.writeInt(output_mode.ordinal)
                    outStat.writeInt(variables.size)
                    for (i in 0 until variables.size) {
                        val tmp = variables[i].encodeToByteArray()
                        outStat.writeInt(tmp.size)
                        outStat.write(tmp)
                    }
                    var allRows = mutableListOf<IntArray>()
                    for (node in target["results"]!!.childs) {
                        val row_out = IntArray(variables.size) { -1 }
                        resultCounter++
                        for (v in node.childs) {
                            val name = v.attributes["name"]
                            val idx = variables.indexOf(name)
                            if (idx < 0) {
                                throw Exception("unknown name '$name'")
                            }
                            if (v.childs.size > 0) {
                                val child = v.childs.first()
                                val content = helper_clean_string(child.content)
                                val datatype = child.attributes["datatype"]
                                val lang = child.attributes["xml:lang"]
                                if ((datatype != null) && (lang != null)) {
                                    throw Exception("overspecification")
                                }
                                val s: String
                                when {
                                    child.tag == "uri" -> s = "<" + content + ">"
                                    child.tag == "literal" && datatype != null -> s = "\"" + content + "\"^^<" + datatype + ">"
                                    child.tag == "literal" && lang != null -> s = "\"" + content + "\"@" + lang
                                    child.tag == "bnode" -> s = "_:" + content
                                    else -> s = "\"" + content + "\""
                                }
                                val id = dict[s]
                                if (id != null) {
                                    row_out[idx] = id
                                } else {
                                    val id2 = dict.size
                                    row_out[idx] = id2
                                    dict[s] = id2
                                    val tmp: ByteArray
                                    if (s.startsWith("_:")) {
                                        tmp = "_:b${dict_bnode_count++}".encodeToByteArray()
                                    } else {
                                        tmp = "$s".encodeToByteArray()
                                    }
                                    outDictionary.writeInt(tmp.size)
                                    outDictionary.write(tmp)
                                }
                            }
                        }
                        if (containsOrderBy) {
                            for (i in 0 until variables.size) {
                                outResult.writeInt(row_out[i])
                            }
                        } else {
                            allRows.add(row_out)
                        }
                    }
                    outStat.writeInt(resultCounter)
                    if (!containsOrderBy) {
                        allRows.sortWith(IntArrayComparator())
                        for (row2 in allRows) {
                            for (i in 0 until variables.size) {
                                outResult.writeInt(row2[i])
                            }
                        }
                    }
                }
                BinaryTestCaseOutputMode.ASK_QUERY_RESULT -> {
                    outStat.writeInt(output_mode.ordinal)
                    if (target["boolean"]!!.content.toLowerCase() == "true") {
                        outResult.writeInt(1)
                    } else {
                        outResult.writeInt(0)
                    }
                }
                else -> {
                    throw Exception("not implemented")
                }
            }
            val tmp2 = query_name.encodeToByteArray()
            outStat.writeInt(tmp2.size)
            outStat.write(tmp2)
            outStat.writeInt(dict.size)
            outStat.writeInt(input_counter)
            outResult.close()
            outStat.close()
            outDictionary.close()
            return true
        } catch (e: UnknownDataFile) {
            java.io.File(output_folder).deleteRecursively()
            return false
        }
    }
}
