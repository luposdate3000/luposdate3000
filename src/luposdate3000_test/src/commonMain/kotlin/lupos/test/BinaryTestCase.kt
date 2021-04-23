/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.test

import lupos.dictionary.DictionaryFactory
import lupos.dictionary.EDictionaryTypeExt
import lupos.dictionary.IDictionary
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.iterator.ColumnIterator
import lupos.operator.base.iterator.ColumnIterator

MultiValue
import lupos.operator.logical.Query
import lupos.operator.physical.POPBase
import lupos.operator.physical.partition.POPMergePartition
import lupos.operator.physical.partition.POPSplitPartitionFromStore
import lupos.optimizer.ast.OperatorGraphVisitor
import lupos.optimizer.logical.LogicalOptimizer
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.parser.LexerCharIterator
import lupos.parser.LookAheadTokenIterator
import lupos.parser.sparql1_1.SPARQLParser
import lupos.parser.sparql1_1.TokenIteratorSPARQLParser
import lupos.result_format.QueryResultToMemoryTable
import lupos.result_format.QueryResultToXMLStream
import lupos.shared.ByteArrayWrapper
import lupos.shared.EIndexPatternExt
import lupos.shared.EModifyTypeExt
import lupos.shared.EPartitionModeExt
import lupos.shared.IMyOutputStream
import lupos.shared.MAX_TRIPLES_DURING_TEST
import lupos.shared.MemoryTable
import lupos.shared.NotImplementedException
import lupos.shared.Partition
import lupos.shared.UnknownDataFileException
import lupos.shared.XMLElement
import lupos.shared.communicationHandler
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.dictionary.nodeGlobalDictionary
import lupos.shared.operator.IAOPBase
import lupos.shared.operator.IOPBase
import lupos.shared.parseFromAny
import lupos.shared_inline.DictionaryHelper
import lupos.shared_inline.File
import lupos.shared_inline.MyPrintWriter
import lupos.shared.TripleStoreManager
import lupos.shared.tripleStoreManager
import kotlin.jvm.JvmField

public object BinaryTestCase {
    @JvmField
    internal var outSummary: IMyOutputStream = MyPrintWriter(false)

    @JvmField
    internal var lastInput = MemoryTable(Array(0) { "" })

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

    public fun executeAllTestCase(folder: String) {
        outSummary = File("log/error").openOutputStream(false)
        File("$folder/config2").withOutputStream { newConfig ->
            File("$folder/config").forEachLine { line ->
                val setting = line.split("=")
                if (setting.size == 2) {
                    var s = setting[0] + "="
                    try {
                        when (setting[1]) {
                            "disabled" -> {
                                s += "disabled"
                            }
                            "missingFeatures" -> {
                                s += "missingFeatures"
                            }
                            "hadSuccess" -> {
                                val res = executeTestCase(folder + "/" + setting[0])
                                if (res) {
                                    s += "hadSuccess"
                                } else {
                                    s += "hadSuccessButFailedNow"
                                }
                            }
                            else -> {
                                val res = executeTestCase(folder + "/" + setting[0])
                                if (res) {
                                    s += "hadSuccess"
                                } else {
                                    s += "enabled"
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        e.printStackTrace()
                        s += "missingFeaturesException"
                    }
                    newConfig.println(s)
                    newConfig.flush()
                }
            }
            outSummary.close()
        }
    }

    private fun operatorGraphToTable(node: IOPBase, partition: Partition = Partition()): MemoryTable {
        val tmp = QueryResultToMemoryTable(node, partition)
        if (tmp.size != 1) {
            throw Exception("multi-queries are not supported right now")
        }
        return tmp[0]
    }

    private fun verifyEqual(expected: MemoryTable, actual: MemoryTable, mapping_live_to_target: Map<Int, Int>, dict: Map<String, Int>, dict2: Array<String>, allowOrderBy: Boolean, out: MyPrintWriter): Boolean {
        val buffer = ByteArrayWrapper()
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
        val actualDict: IDictionary
        if (actual.query != null) {
            val q = actual.query!!
            actualDict = q.getDictionary()
        } else {
            actualDict = DictionaryFactory.createDictionary(EDictionaryTypeExt.InMemory, true)
        }
        val expectedDict: IDictionary
        if (expected.query != null) {
            val q = expected.query!!
            expectedDict = q.getDictionary()
        } else {
            expectedDict = DictionaryFactory.createDictionary(EDictionaryTypeExt.InMemory, true)
        }
        for (row in actual.data) {
            val tmpRow = IntArray(columnCount) { -1 }
            for ((i, col) in row.withIndex()) {
                val m = mapping_live_to_target[col]
                actualDict.getValue(buffer, col)
                val value = DictionaryHelper.byteArrayToValueDefinition(buffer).valueToString()
                if (m == null) {
                    if (value != null && !value.startsWith("_:")) {
                        out.println("found wrong $value")
                        out.println(
                            "row :: ${row.map {
                                actualDict.getValue(buffer, it)
                                DictionaryHelper.byteArrayToValueDefinition(buffer).valueToString()
                            }}"
                        )
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
                expectedDict.getValue(buffer, col)
                val value = DictionaryHelper.byteArrayToValueDefinition(buffer).valueToString()
                if (m == null) {
                    if (value != null && !value.startsWith("_:")) {
                        out.println("found wrong $value")
                        out.println(
                            "row :: ${row.map {
                                expectedDict.getValue(buffer, it)
                                DictionaryHelper.byteArrayToValueDefinition(buffer).valueToString()
                            }}"
                        )
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

    private fun verifyEqual(expected: MemoryTable, actual: MemoryTable, mapping_live_to_target: Map<Int, Int>, dict: Map<String, Int>, dict2: Array<String>, allowOrderBy: Boolean, query_name: String, query_folder: String, tag: String): Boolean {
        val out = MyPrintWriter(true)
        val res = verifyEqual(expected, actual, mapping_live_to_target, dict, dict2, allowOrderBy, out)
        if (!res && tag != "this is no error") {
            out.println("----------Failed($tag)")
            val x = out.toString()
            outSummary.println("Test: $query_folder named: $query_name")
            outSummary.println(x)
        }
        return res
    }

    @JvmField
    internal var notImplementedFeaturesList = mutableSetOf( //
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
        val buffer = ByteArrayWrapper()
        println("executeTestCase $query_folder")
        var returnValue = true
        File("$query_folder/query.stat").withInputStream { targetStat ->
            File("$query_folder/query.dictionary").withInputStream { targetDictionary ->
                File("$query_folder/query.triples").withInputStream { targetTriples ->
                    File("$query_folder/query.result").withInputStream { targetResult ->
                        func@ while (true) {
                            val mode = targetStat.readInt()
                            val variables = mutableListOf<String>()
                            var targetResultCount = 0
                            when (mode) {
                                BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT, BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT_COUNT, BinaryTestCaseOutputModeExt.MODIFY_RESULT -> {
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
                                    targetResultCount = targetStat.readInt()
                                    if (MAX_TRIPLES_DURING_TEST in 1 until targetResultCount) {
                                        println("Test: $query_folder named: $query_folder")
                                        println("----------Skipped")
                                        returnValue = true
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
                            val queryName = buf.decodeToString()
                            println("Test: $query_folder named: $queryName")
                            val dictionarySize = targetStat.readInt()
                            val targetInputCount = targetStat.readInt()
                            if (MAX_TRIPLES_DURING_TEST in 1 until targetInputCount) {
                                println("Test: $query_folder named: $query_folder")
                                println("----------Skipped")
                                returnValue = true
                                break@func
                            }
                            val targetDict = mutableMapOf<String, Int>()
                            val targetDict2 = Array<String>(dictionarySize) { "" }
                            val mappingTargetToLive = IntArray(dictionarySize) { 0 }
                            val mappingLiveToTarget = mutableMapOf(DictionaryExt.undefValue to -1, DictionaryExt.errorValue to -1, DictionaryExt.nullValue to -1)
                            for (i in 0 until dictionarySize) {
                                val len2 = targetDictionary.readInt()
                                val buf2 = ByteArray(len2)
                                val read2 = targetDictionary.read(buf2, 0, len2)
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
                                DictionaryHelper.sparqlToByteArray(buffer, s)
                                val tmp = nodeGlobalDictionary.createValue(buffer)
                                mappingTargetToLive[i] = tmp
                                mappingLiveToTarget[tmp] = i
                            }
                            val tableInput = MemoryTable(arrayOf("s", "p", "o"))
                            println("----------Triple-Store-Target")
                            for (i in 0 until targetInputCount) {
                                val s1 = targetTriples.readInt()
                                val p1 = targetTriples.readInt()
                                val o1 = targetTriples.readInt()
                                val s = mappingTargetToLive[s1]
                                val p = mappingTargetToLive[p1]
                                val o = mappingTargetToLive[o1]
                                tableInput.data.add(intArrayOf(s, p, o))
                            }
                            if (!verifyEqual(lastInput, tableInput, mappingLiveToTarget, targetDict, targetDict2, true, queryName, query_folder, "this is no error")) {
                                val query1 = Query()
                                tripleStoreManager.clearGraph(query1, TripleStoreManager.DEFAULT_GRAPH_NAME)
                                for (g in tripleStoreManager.getGraphNames()) {
                                    tripleStoreManager.dropGraph(query1, g)
                                }
                                tripleStoreManager.commit(query1)
                                query1.commited = true
                                val query2 = Query()
                                val key = "${query2.getTransactionID()}"
                                if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                                    communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to "$key"))
                                    query2.setDictionaryUrl("${tripleStoreManager.getLocalhost()}/distributed/query/dictionary?key=$key")
                                }
                                val store = tripleStoreManager.getDefaultGraph()
                                val bufS = IntArray(1048576)
                                val bufP = IntArray(1048576)
                                val bufO = IntArray(1048576)
                                var bufPos = 0
                                val arr = arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufP, bufPos), ColumnIteratorMultiValue(bufO, bufPos))
                                val arr2 = arrayOf(arr[0] as ColumnIterator, arr[1] as ColumnIterator, arr[2] as ColumnIterator)
                                val cache = store.modify_create_cache(EModifyTypeExt.INSERT)
                                for (row in tableInput.data) {
                                    if (bufPos == bufS.size) {
                                        for (i in 0 until 3) {
                                            arr[i].reset(bufPos)
                                        }
                                        store.modify_cache(query2, arr2, EModifyTypeExt.INSERT, cache, false)
                                        bufPos = 0
                                    }
                                    bufS[bufPos] = row[0]
                                    bufP[bufPos] = row[1]
                                    bufO[bufPos] = row[2]
                                    bufPos++
                                }
                                for (i in 0 until 3) {
                                    arr[i].reset(bufPos)
                                }
                                store.modify_cache(query2, arr2, EModifyTypeExt.INSERT, cache, true)
                                tripleStoreManager.commit(query2)
                                if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                                    communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
                                }
                                val graph = tripleStoreManager.getDefaultGraph()
                                var success = true
                                for (
                                idx in listOf(
                                    EIndexPatternExt.SPO,
                                    EIndexPatternExt.SOP,
                                    EIndexPatternExt.PSO,
                                    EIndexPatternExt.POS,
                                    EIndexPatternExt.OSP,
                                    EIndexPatternExt.OPS,
                                )
                                ) {
                                    val query3 = Query()
                                    val queryParam = arrayOf<IAOPBase>(AOPVariable(query3, "s"), AOPVariable(query3, "p"), AOPVariable(query3, "o"))
                                    val key2 = "${query3.getTransactionID()}"
                                    if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                                        communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to "$key2"))
                                        query3.setDictionaryUrl("${tripleStoreManager.getLocalhost()}/distributed/query/dictionary?key=$key2")
                                    }
                                    val iterator = graph.getIterator(query3, queryParam, idx)
                                    var tmpTable: MemoryTable?
                                    var partitionCount = 1
                                    var partitionVariable = ""
                                    for (variable in listOf("s", "p", "o")) {
                                        val tmp = iterator.getPartitionCount(variable)
                                        if (tmp > partitionCount) {
                                            partitionCount = tmp
                                            partitionVariable = variable
                                        }
                                    }
                                    val node: POPBase
                                    if (partitionCount == 1) {
                                        node = iterator as POPBase
                                    } else {
                                        node = POPMergePartition(query3, listOf("s", "p", "o"), partitionVariable, partitionCount, -1, POPSplitPartitionFromStore(query3, listOf("s", "p", "o"), partitionVariable, partitionCount, -1, iterator))
                                    }
                                    tmpTable = operatorGraphToTable(OPBaseCompound(query3, arrayOf(node), listOf(listOf("s", "p", "o"))))
                                    success = verifyEqual(tableInput, tmpTable, mappingLiveToTarget, targetDict, targetDict2, true, queryName, query_folder, "import (${EIndexPatternExt.names[idx]} $partitionCount)") && success
                                    println("success $success $idx")
                                    if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                                        communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key2"))
                                    }
                                }
                                if (!success) {
                                    returnValue = false
                                    println("----------Failed(import)")
                                    break@func
                                }
                            }
                            val tableOutput = MemoryTable(variables.toTypedArray())
                            if (mode == BinaryTestCaseOutputModeExt.ASK_QUERY_RESULT) {
                                tableOutput.booleanResult = targetResult.readInt() == 1
                            } else {
                                for (i in 0 until targetResultCount) {
                                    val row = IntArray(variables.size) { -1 }
                                    for (j in 0 until variables.size) {
                                        val tmp = targetResult.readInt()
                                        if (tmp == -1) {
                                            row[j] = DictionaryExt.undefValue
                                        } else {
                                            row[j] = mappingTargetToLive[tmp]
                                        }
                                    }
                                    tableOutput.data.add(row)
                                }
                            }
                            println("----------String query")
                            val toParse = File("$query_folder/query.sparql").readAsString()
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
                            val astNode = parser.expr()
                            println(astNode)
                            println("----------Logical Operatorgraph")
                            val query4 = Query()
                            val key4 = "${query4.getTransactionID()}"
                            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to "$key4"))
                                query4.setDictionaryUrl("${tripleStoreManager.getLocalhost()}/distributed/query/dictionary?key=$key4")
                            }
                            val lopNode = astNode.visit(OperatorGraphVisitor(query4))
                            println(lopNode.toString())
                            println("----------Logical Operatorgraph optimized")
                            val lopNode2 = LogicalOptimizer(query4).optimizeCall(lopNode)
                            println(lopNode2.toString())
                            println("----------Physical Operatorgraph optimized")
                            val popOptimizer = PhysicalOptimizer(query4)
                            val popNode = popOptimizer.optimizeCall(lopNode2)
                            println(popNode.toString())
                            val allowOrderBy = !toParse.toLowerCase().contains("order")
                            if (mode == BinaryTestCaseOutputModeExt.MODIFY_RESULT) {
                                val resultWriter = MyPrintWriter(false)
                                QueryResultToXMLStream(popNode, resultWriter)
                                val query5 = Query()
                                val key5 = "${query5.getTransactionID()}"
                                if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                                    communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to "$key5"))
                                    query5.setDictionaryUrl("${tripleStoreManager.getLocalhost()}/distributed/query/dictionary?key=$key5")
                                }
                                val popOptimizer2 = PhysicalOptimizer(query5)
                                val actualResult = operatorGraphToTable(popOptimizer2.optimizeCall(tripleStoreManager.getDefaultGraph().getIterator(query5, arrayOf(AOPVariable(query5, "s"), AOPVariable(query5, "p"), AOPVariable(query5, "o")), EIndexPatternExt.SPO)))
                                if (!verifyEqual(tableOutput, actualResult, mappingLiveToTarget, targetDict, targetDict2, allowOrderBy, queryName, query_folder, "result in store (SPO) is wrong")) {
                                    returnValue = false
                                    if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                                        communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key5"))
                                        communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key4"))
                                    }
                                    break@func
                                }
                                tripleStoreManager.commit(query5)
                                query5.commited = true
                                if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                                    communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key5"))
                                }
                            } else {
                                val actualResult = operatorGraphToTable(popNode)
                                if (!verifyEqual(tableOutput, actualResult, mappingLiveToTarget, targetDict, targetDict2, allowOrderBy, queryName, query_folder, "query result is wrong")) {
                                    returnValue = false
                                    if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                                        communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key4"))
                                    }
                                    break@func
                                }
                            }
                            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key4"))
                            }
                            println("----------Success")
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
            File("$output_folder/query.sparql").withOutputStream { out ->
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
            File("$output_folder/query.dictionary").withOutputStream { outDictionary ->
                File("$output_folder/query.triples").withOutputStream { outTriples ->
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
                                outDictionary.write(tmp)
                            }
                        }
                        for (i in 0 until 3) {
                            outTriples.writeInt(row[i])
                        }
                    }
                    val target = XMLElement.parseFromAny(File(query_output_file).readAsString(), query_output_file)!!
                    if (target["results"] == null && target["boolean"] != null) {
                        outputMode = BinaryTestCaseOutputModeExt.ASK_QUERY_RESULT
                    }
                    File("$output_folder/query.stat").withOutputStream { outStat ->
                        File("$output_folder/query.result").withOutputStream { outResult ->
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
                                    outStat.writeInt(variables.size)
                                    for (element in variables) {
                                        val tmp = element.encodeToByteArray()
                                        outStat.writeInt(tmp.size)
                                        outStat.write(tmp)
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
                                                    outDictionary.write(tmp)
                                                }
                                            }
                                        }
                                        if (containsOrderBy) {
                                            for (i in 0 until variables.size) {
                                                outResult.writeInt(rowOut[i])
                                            }
                                        } else {
                                            allRows.add(rowOut)
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
                                BinaryTestCaseOutputModeExt.ASK_QUERY_RESULT -> {
                                    outStat.writeInt(outputMode)
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
                            outStat.writeInt(inputCounter)
                            println("added Testcase $output_folder ${BinaryTestCaseOutputModeExt.names[outputMode]} (${BinaryTestCaseOutputModeExt.names[output_mode_tmp]}) $query_name $query_input_file $query_output_file $query_file")
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
