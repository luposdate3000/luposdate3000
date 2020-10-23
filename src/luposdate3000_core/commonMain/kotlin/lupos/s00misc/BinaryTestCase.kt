package lupos.s00misc

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
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.NotImplementedException
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromAny
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
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
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
import lupos.s15tripleStoreDistributed.distributedTripleStore
import lupos.s16network.HttpEndpoint
import lupos.s16network.ServerCommunicationSend
import lupos.SparqlTestSuite

object BinaryTestCase {
    internal var outSummary = MyPrintWriter(false)
    internal var lastInput = MemoryTable(Array<String>(0) { "" })
    internal fun rowToString(row: IntArray, dict: Array<String>): String {
        var res = "${row.map { it }}::"
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

    internal fun helper_clean_string(s: String): String {
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
        outSummary = File("log/error").myPrintWriter()
        File(folder + "/config2").printWriter { newConfig ->
            File(folder + "/config").forEachLine { line ->
                val setting = line.split("=")
                try {
                    when (setting[1]) {
                        "disabled", "missingFeatures" -> {
                            newConfig.println(line)
                        }
                        "hadSuccess" -> {
                            executeTestCase(folder + "/" + setting[0])
                            newConfig.println(line)
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

    internal fun operatorGraphToTable(node: IOPBase): MemoryTable {
        val tmp = QueryResultToMemoryTable(node)
        if (tmp.size != 1) {
            throw Exception("multi-queries are not supported right now")
        }
        return tmp[0]
    }

    internal fun verifyEqual(expected: MemoryTable, actual: MemoryTable, mapping_live_to_target: Map<Int, Int>, dict: Map<String, Int>, dict2: Array<String>, allowOrderBy: Boolean, out: MyPrintWriter): Boolean {
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

    internal class IntArrayComparator : Comparator<IntArray> {
        override fun compare(p1: IntArray, p2: IntArray): Int {
            for (i in 0 until p1.size) {
                if (p1[i] < p2[i]) {
                    return -1
                } else if (p1[i] > p2[i]) {
                    return 1
                }
            }
            return 0
        }
    }

    internal fun verifyEqual(expected: MemoryTable, actual: MemoryTable, mapping_live_to_target: Map<Int, Int>, dict: Map<String, Int>, dict2: Array<String>, allowOrderBy: Boolean, query_name: String, query_folder: String, tag: String): Boolean {
        val out = MyPrintWriter()
        val res = verifyEqual(expected, actual, mapping_live_to_target, dict, dict2, allowOrderBy, out)
        if (!res && tag != "this is no error") {
            out.println("----------Failed($tag)")
            val x = out.toString()
            println(x)
            outSummary.println("Test: $query_folder named: $query_name")
            outSummary.println(x)
        }
        return res
    }

    internal var notImplementedFeaturesList = mutableSetOf(//
            "rdfs:subPropertyOf",//
            "rdfs:subClassOf",//
            "rdfs:domain",//
            // "rdfs:label",//
            "rdfs:range",//
            // "owl:Class",//
            "owl:allValuesFrom",//
            "owl:complementOf",//
            "owl:DatatypeProperty",//
            "owl:intersectionOf",//
            "owl:maxQualifiedCardinality",//
            "owl:minCardinality",//
            "owl:minQualifiedCardinality",//
            "owl:Nothing",//
            "owl:ObjectProperty",//
            "owl:onClass",//
            "owl:onProperty",//
            "owl:qualifiedCardinality",//
            "owl:Restriction",//
            "owl:sameAs",//
            "owl:someValuesFrom",//
            "owl:Thing",//
            "owl:unionOf",//
            "<http://www.w3.org/2000/01/rdf-schema#domain>",//
            "<http://www.w3.org/2000/01/rdf-schema#range>",//
            // "<http://www.w3.org/2000/01/rdf-schema#label>",//
            "<http://www.w3.org/2000/01/rdf-schema#seeAlso>",//
            "<http://www.w3.org/2000/01/rdf-schema#subClassOf>",//
            "<http://www.w3.org/2000/01/rdf-schema#subPropertyOf>",//
            "<http://www.w3.org/2002/07/owl#allValuesFrom>",//
            // "<http://www.w3.org/2002/07/owl#Class>",//
            "<http://www.w3.org/2002/07/owl#DatatypeProperty>",//
            "<http://www.w3.org/2002/07/owl#disjointWith>",//
            "<http://www.w3.org/2002/07/owl#equivalentClass>",//
            "<http://www.w3.org/2002/07/owl#FunctionalProperty>",//
            "<http://www.w3.org/2002/07/owl#intersectionOf>",//
            "<http://www.w3.org/2002/07/owl#inverseOf>",//
            "<http://www.w3.org/2002/07/owl#minCardinality>",//
            "<http://www.w3.org/2002/07/owl#NamedIndividual>",//
            "<http://www.w3.org/2002/07/owl#Nothing>",//
            "<http://www.w3.org/2002/07/owl#ObjectProperty>",//
            "<http://www.w3.org/2002/07/owl#oneOf>",//
            "<http://www.w3.org/2002/07/owl#onProperty>",//
            "<http://www.w3.org/2002/07/owl#Ontology>",//
            "<http://www.w3.org/2002/07/owl#Restriction>",//
            "<http://www.w3.org/2002/07/owl#sameAs>",//
            "<http://www.w3.org/2002/07/owl#someValuesFrom>",//
            "<http://www.w3.org/2002/07/owl#Thing>",//
    )

    fun executeTestCase(query_folder: String): Boolean {
        var return_value = true
        File(query_folder + "/query.stat").dataInputStream { targetStat ->
            File(query_folder + "/query.dictionary").dataInputStream { targetDictionary ->
                File(query_folder + "/query.triples").dataInputStream { targetTriples ->
                    File(query_folder + "/query.result").dataInputStream { targetResult ->
                        func@ while (true) {
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
                                        return_value = true
                                        break@func
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
                            if (target_input_count > MAX_TRIPLES_DURING_TEST && MAX_TRIPLES_DURING_TEST > 0) {
                                println("Test: $query_folder named: $query_folder")
                                println("----------Skipped")
                                return_value = true
                                break@func
                            }
                            val targetDict = mutableMapOf<String, Int>()
                            val targetDict2 = Array<String>(dictionarySize) { "" }
                            val mapping_target_to_live = IntArray(dictionarySize) { 0 }
                            val mapping_live_to_target = mutableMapOf<Int, Int>(ResultSetDictionaryExt.undefValue to -1, ResultSetDictionaryExt.errorValue to -1, ResultSetDictionaryExt.nullValue to -1)
                            for (i in 0 until dictionarySize) {
                                val len = targetDictionary.readInt()
                                val buf = ByteArray(len)
                                val read = targetDictionary.read(buf, 0, len)
                                if (read < len) {
                                    throw Exception("not enough data available")
                                }
                                val s = buf.decodeToString()
                                if (notImplementedFeaturesList.contains(s)) {
                                    throw object : NotImplementedException("NotImplementedException", "Inference not implemented '$s'") {}
                                }
                                if (s.startsWith("<http://www.w3.org/2000/01/rdf-schema") || s.startsWith("<http://www.w3.org/2002/07/owl")) {
                                    outSummary.println(s)
                                }
                                targetDict[s] = i
                                targetDict2[i] = s
                                println("XXX added value:$s")
                                val tmp = nodeGlobalDictionary.createValue(s)
                                mapping_target_to_live[i] = tmp
                                mapping_live_to_target[tmp] = i
                            }
                            val tableInput = MemoryTable(arrayOf("s", "p", "o"))
                            println("----------Triple-Store-Target")
                            for (i in 0 until target_input_count) {
                                val s1 = targetTriples.readInt()
                                val p1 = targetTriples.readInt()
                                val o1 = targetTriples.readInt()
                                println("[$s1, $p1, $o1] :: [${targetDict2[s1]}, ${targetDict2[p1]}, ${targetDict2[o1]}]")
                                val s = mapping_target_to_live[s1]
                                val p = mapping_target_to_live[p1]
                                val o = mapping_target_to_live[o1]
                                tableInput.data.add(intArrayOf(s, p, o))
                            }
                            if (!verifyEqual(lastInput, tableInput, mapping_live_to_target, targetDict, targetDict2, true, query_name, query_folder, "this is no error")) {
                                val query1 = Query()
                                ServerCommunicationSend.graphClearAll(query1)
                                distributedTripleStore.commit(query1)
                                query1.commited = true
                                val query2 = Query()
                                var store = distributedTripleStore.getDefaultGraph(query2)
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
                                    } else {
//TODO
/*
                                        for (value in 0 until p.partitionCount) {
                                            val partition = Partition()
                                            val key = idx.toString().substring(p.column, p.column + 1)
                                            partition.limit[key] = p.partitionCount
                                            partition.data[key] = value
                                            println("using Partitioning on ${key} (${value}/${p.partitionCount})")
                                            val node = distributedTripleStore.getDefaultGraph(query3).getIterator(queryParam, idx, partition)
val                                        table = operatorGraphToTable(OPBaseCompound(query3,arrayOf(node),listOf(listOf("s","p","o"))))
                                            if (tmpTable != null) {
                                                tmpTable = MemoryTable(tmpTable!!, table)
                                            } else {
                                                tmpTable = table
                                            }
                                        }
*/
                                    }
                                    if (tmpTable != null) {
                                        success = verifyEqual(tableInput, tmpTable!!, mapping_live_to_target, targetDict, targetDict2, true, query_name, query_folder, "import ($idx ${p.column} ${p.partitionCount})") && success
                                        println("success after idx $idx $success")
                                    }
                                }
                                if (!success) {
                                    return_value = false
                                    println("----------Failed(import)")
                                    break@func
                                }
                            }
                            val tableOutput = MemoryTable(variables.toTypedArray())
                            if (mode == BinaryTestCaseOutputMode.ASK_QUERY_RESULT) {
                                tableOutput.booleanResult = targetResult.readInt() == 1
                            } else {
                                for (i in 0 until target_result_count) {
                                    val row = IntArray(variables.size) { -1 }
                                    for (j in 0 until variables.size) {
                                        val tmp = targetResult.readInt()
                                        if (tmp == -1) {
                                            row[j] = ResultSetDictionaryExt.undefValue
                                        } else {
                                            row[j] = mapping_target_to_live[tmp]
                                        }
                                    }
                                    tableOutput.data.add(row)
                                }
                            }
                            println("----------String query")
                            val toParse = File(query_folder + "/query.sparql").readAsString()
                            println(toParse)
                            for (f in notImplementedFeaturesList) {
                                if (toParse.contains(f)) {
                                    throw object : NotImplementedException("NotImplementedException", "Inference not implemented '$f'") {}
                                }
                            }
                            println("----------AST")
                            val lcit = LexerCharIterator(toParse)
                            val tit = TokenIteratorSPARQLParser(lcit)
                            val ltit = LookAheadTokenIterator(tit, 3)
                            val parser = SPARQLParser(ltit)
                            val ast_node = parser.expr()
                            println(ast_node)
                            println("----------Logical Operatorgraph")
                            val query4 = Query()
                            val lop_node = ast_node.visit(OperatorGraphVisitor(query4))
                            println(lop_node.toXMLElement().toPrettyString())
                            println("----------Logical Operatorgraph optimized")
                            val lop_node2 = LogicalOptimizer(query4).optimizeCall(lop_node)
                            println(lop_node2.toXMLElement().toPrettyString())
                            println("----------Physical Operatorgraph optimized")
                            val pop_optimizer = PhysicalOptimizer(query4)
                            val pop_node = pop_optimizer.optimizeCall(lop_node2)
                            println(pop_node.toXMLElement().toPrettyString())
                            println("----------Distributed Operatorgraph optimized")
                            val pop_distributed_node = KeyDistributionOptimizer(query4).optimizeCall(pop_node)
                            println(pop_distributed_node.toXMLElement().toPrettyString())
                            val allowOrderBy = !toParse.toLowerCase().contains("order")
                            if (mode == BinaryTestCaseOutputMode.MODIFY_RESULT) {
                                val resultWriter = MyPrintWriter(false)
                                QueryResultToXMLStream(pop_distributed_node, resultWriter)
                                val query4 = Query()
                                val actualResult = operatorGraphToTable(distributedTripleStore.getDefaultGraph(query4).getIterator(arrayOf(AOPVariable(query4, "s"), AOPVariable(query4, "p"), AOPVariable(query4, "o")), EIndexPattern.SPO, Partition()))
                                if (!verifyEqual(tableOutput, actualResult, mapping_live_to_target, targetDict, targetDict2, allowOrderBy, query_name, query_folder, "result in store (SPO) is wrong")) {
                                    return_value = false
                                    break@func
                                }
                                distributedTripleStore.commit(query4)
                                query4.commited = true
                            } else {
                                val actualResult = operatorGraphToTable(pop_distributed_node)
                                if (!verifyEqual(tableOutput, actualResult, mapping_live_to_target, targetDict, targetDict2, allowOrderBy, query_name, query_folder, "query result is wrong")) {
                                    return_value = false
                                    break@func
                                }
                            }
                            println("----------Success")
                            return_value = true
                            break@func
                        }
                    }
                }
            }
        }
        return return_value
    }

    fun generateTestcase(query_input_file: String, query_file: String, query_output_file: String, output_folder: String, query_name: String, output_mode_tmp: BinaryTestCaseOutputMode): Boolean {
        println("generateTestcase.kts $query_input_file $query_file $query_output_file $output_folder $query_name $output_mode_tmp")
        try {
            var output_mode = output_mode_tmp
            File(output_folder).deleteRecursively()
            File(output_folder).mkdirs()
            var containsOrderBy = false
            File(output_folder + "/query.sparql").printWriter { out ->
                File(query_file).forEachLine { line ->
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
            File(output_folder + "/query.dictionary").dataOutputStream { outDictionary ->
                File(output_folder + "/query.triples").dataOutputStream { outTriples ->
                    val data = XMLElement.parseFromAny(File(query_input_file).readAsString(), query_input_file)!!
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
                    var target = XMLElement.parseFromAny(File(query_output_file).readAsString(), query_output_file)!!
                    if (target["results"] == null && target["boolean"] != null) {
                        output_mode = BinaryTestCaseOutputMode.ASK_QUERY_RESULT
                    }
                    File(output_folder + "/query.stat").dataOutputStream { outStat ->
                        File(output_folder + "/query.result").dataOutputStream { outResult ->
                            var resultCounter = 0
                            when (output_mode) {
                                BinaryTestCaseOutputMode.SELECT_QUERY_RESULT, BinaryTestCaseOutputMode.SELECT_QUERY_RESULT_COUNT, BinaryTestCaseOutputMode.MODIFY_RESULT -> {
                                    val variablesTmp = mutableListOf<String>()
                                    for (node in target["head"]!!.childs) {
                                        variablesTmp.add(node.attributes["name"]!!)
                                    }
                                    val variables = variablesTmp.toTypedArray()
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
                            println("added Testcase $output_folder $output_mode ($output_mode_tmp) $query_name $query_input_file $query_output_file $query_file")
                        }
                    }
                }
            }
            return true
        } catch (e: UnknownDataFile) {
            File(output_folder).deleteRecursively()
            return false
        }
    }
}
