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
package lupos.endpoint

import lupos.buffer_manager.BufferManagerExt
import lupos.dictionary.DictionaryFactory
import lupos.operator.base.Query
import lupos.operator.base.iterator.ColumnIteratorMultiValue3
import lupos.operator.factory.XMLElementToOPBase
import lupos.operator.physical.noinput.POPValuesImportXML
import lupos.optimizer.ast.OperatorGraphVisitor
import lupos.optimizer.distributed.query.DistributedOptimizerQuery
import lupos.optimizer.logical.LogicalOptimizer
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.parser.LexerCharIterator
import lupos.parser.LookAheadTokenIterator
import lupos.parser.ParseError
import lupos.parser.XMLElementFromN3
import lupos.parser.sparql1_1.SPARQLParser
import lupos.parser.sparql1_1.TokenIteratorSPARQLParser
import lupos.parser.turtle.Turtle2Parser
import lupos.parser.turtle.TurtleParserWithStringTriples
import lupos.parser.turtle.TurtleScanner
import lupos.result_format.EQueryResultToStream
import lupos.result_format.EQueryResultToStreamExt
import lupos.result_format.QueryResultToEmptyStream
import lupos.result_format.QueryResultToEmptyWithDictionaryStream
import lupos.result_format.QueryResultToMemoryTable
import lupos.result_format.QueryResultToTurtleStream
import lupos.result_format.QueryResultToXMLElement
import lupos.result_format.QueryResultToXMLStream
import lupos.shared.ByteArrayWrapper
import lupos.shared.DateHelperRelative
import lupos.shared.EIndexPatternExt
import lupos.shared.EModifyTypeExt
import lupos.shared.EPartitionModeExt
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.IMyOutputStream
import lupos.shared.INTERNAL_BUFFER_SIZE
import lupos.shared.MyLock
import lupos.shared.OperatorGraphToLatex
import lupos.shared.QueryResultToStream
import lupos.shared.SanityCheck
import lupos.shared.TripleStoreManager
import lupos.shared.UnreachableException
import lupos.shared.XMLElement
import lupos.shared.XMLElementFromCsv
import lupos.shared.XMLElementFromJson
import lupos.shared.XMLElementFromTsv
import lupos.shared.XMLElementFromXML
import lupos.shared.communicationHandler
import lupos.shared.dictionary.nodeGlobalDictionary
import lupos.shared.fileformat.TriplesIntermediateReader
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.optimizer.distributedOptimizerQueryFactory
import lupos.shared.tripleStoreManager
import lupos.shared_inline.DictionaryHelper
import lupos.shared_inline.File
import lupos.shared_inline.MyPrintWriter
import lupos.shared_inline.MyStringStream
import lupos.shared_inline.Platform
import lupos.triple_store_manager.TripleStoreManagerImpl
import kotlin.js.JsName
import kotlin.jvm.JvmField

/*
 * This is the _interface_ of the database
 * Do not overload any function here - because that would yield bad function names in the exported headers.
 * Do not use default parameters - for the same reason - mangled function names in the _interface_ are bad
 */
@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
public object LuposdateEndpoint {
    @JvmField
    internal var initialized = false

    @JvmField
    internal val initializerLock = MyLock()
    private fun helperCleanString(s: String): String {
        var res: String = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res) ?: break
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
        return res
    }

    internal fun helperImportRaw(dict: MutableMap<String, Int>, v: String): Int {
        val v2 = helperCleanString(v)
        val res: Int
        if (v2.startsWith("_:")) {
            val tmp = dict[v2]
            if (tmp != null) {
                res = tmp
            } else {
                res = nodeGlobalDictionary.createNewBNode()
                dict[v2] = res
            }
        } else {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.sparqlToByteArray(buffer, v2)
            res = nodeGlobalDictionary.createValue(buffer)
        }
        return res
/*Coverage Unreachable*/
    }

    internal fun helperImportRaw(dict: MutableMap<String, Int>, v: ByteArrayWrapper): Int {
        val type = DictionaryHelper.byteArrayToType(v)
        if (type == ETripleComponentTypeExt.BLANK_NODE) {
            val tmp = DictionaryHelper.byteArrayToBnode_S(v)
            var res = dict[tmp]
            if (res != null) {
                return res
            }
            res = nodeGlobalDictionary.createNewBNode()
            dict[tmp] = res
            return res
        } else {
            return nodeGlobalDictionary.createValue(v)
        }
    }

    @JsName("import_turtle_files_old")
/*suspend*/ public fun importTurtleFilesOld(fileNames: String, bnodeDict: MutableMap<String, Int>): String {
        val query = Query()
        val key = "${query.getTransactionID()}"
        try {
            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to "$key"))
                query.setDictionaryUrl("${tripleStoreManager.getLocalhost()}/distributed/query/dictionary?key=$key")
            }
            var counter = 0
            val store = tripleStoreManager.getDefaultGraph()
            val bufS = IntArray(INTERNAL_BUFFER_SIZE)
            val bufP = IntArray(INTERNAL_BUFFER_SIZE)
            val bufO = IntArray(INTERNAL_BUFFER_SIZE)
            var bufPos = 0
            for (fileName in fileNames.split(";")) {
                println("importing file '$fileName'")
                val f = File(fileName)
                val lcit: LexerCharIterator = if (f.length() < Int.MAX_VALUE) {
                    val data = f.readAsString()
                    LexerCharIterator(data)
                } else {
                    val data = f.readAsCharIterator()
                    LexerCharIterator(data)
                }
                val tit = TurtleScanner(lcit)
                val ltit = LookAheadTokenIterator(tit, 3)
                try {
                    val arr = arrayOf(ColumnIteratorMultiValue3(bufS, bufPos), ColumnIteratorMultiValue3(bufP, bufPos), ColumnIteratorMultiValue3(bufO, bufPos))
                    val arr2 = arrayOf(arr[0] as ColumnIterator, arr[1] as ColumnIterator, arr[2] as ColumnIterator)
                    val cache = store.modify_create_cache(EModifyTypeExt.INSERT)
                    val x = object : TurtleParserWithStringTriples() {
                        /*suspend*/ override fun consume_triple(s: String, p: String, o: String) {
                            counter++
                            if (bufPos == bufS.size) {
                                for (i in 0 until 3) {
                                    arr[i].reset(bufPos)
                                }
                                store.modify_cache(query, arr2, EModifyTypeExt.INSERT, cache, false)
                                bufPos = 0
                            }
                            bufS[bufPos] = helperImportRaw(bnodeDict, s)
                            bufP[bufPos] = helperImportRaw(bnodeDict, p)
                            bufO[bufPos] = helperImportRaw(bnodeDict, o)
                            bufPos++
                        }
                    }
                    x.ltit = ltit
                    x.parse()
                    for (i in 0 until 3) {
                        arr[i].reset(bufPos)
                    }
                    store.modify_cache(query, arr2, EModifyTypeExt.INSERT, cache, true)
                } catch (e: lupos.parser.ParseError) {
                    e.printStackTrace()
                    println("error in file '$fileName'")
                    throw e
                }
            }
            tripleStoreManager.commit(query)
            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
            }
            return "successfully imported $counter Triples"
        } catch (e: Throwable) {
            e.printStackTrace()
            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
            }
            throw e
        }
/*Coverage Unreachable*/
    }

    @JsName("import_turtle_files")
    /*suspend*/ public fun importTurtleFiles(fileNames: String, bnodeDict: MutableMap<String, Int>): String {
        val query = Query()
        val key = "${query.getTransactionID()}"
        try {
            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to "$key"))
                query.setDictionaryUrl("${tripleStoreManager.getLocalhost()}/distributed/query/dictionary?key=$key")
            }
            var counter = 0
            val store = tripleStoreManager.getDefaultGraph()
            val bufS = IntArray(INTERNAL_BUFFER_SIZE)
            val bufP = IntArray(INTERNAL_BUFFER_SIZE)
            val bufO = IntArray(INTERNAL_BUFFER_SIZE)
            var bufPos = 0
            for (fileName in fileNames.split(";")) {
                println("importing file '$fileName'")
                val f = File(fileName)
                val iter = f.openInputStream()
                try {
                    val arr = arrayOf(ColumnIteratorMultiValue3(bufS, bufPos), ColumnIteratorMultiValue3(bufP, bufPos), ColumnIteratorMultiValue3(bufO, bufPos))
                    val arr2 = arrayOf(arr[0] as ColumnIterator, arr[1] as ColumnIterator, arr[2] as ColumnIterator)
                    val cache = store.modify_create_cache(EModifyTypeExt.INSERT)
                    val x = object : Turtle2Parser(iter) {
                        override fun onTriple() {
                            counter++
                            if (bufPos == bufS.size) {
                                for (i in 0 until 3) {
                                    arr[i].reset(bufPos)
                                }
                                store.modify_cache(query, arr2, EModifyTypeExt.INSERT, cache, false)
                                bufPos = 0
                            }
                            bufS[bufPos] = helperImportRaw(bnodeDict, triple[0])
                            bufP[bufPos] = helperImportRaw(bnodeDict, triple[1])
                            bufO[bufPos] = helperImportRaw(bnodeDict, triple[2])
                            bufPos++
                        }
                    }
                    x.parse()
                    for (i in 0 until 3) {
                        arr[i].reset(bufPos)
                    }
                    store.modify_cache(query, arr2, EModifyTypeExt.INSERT, cache, true)
                } catch (e: Exception) {
                    e.printStackTrace()
                    println("fast_parser :: error in file '$fileName'")
                    throw e
                }
            }
            tripleStoreManager.commit(query)
            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
            }
            return "successfully imported $counter Triples"
        } catch (e: Throwable) {
            e.printStackTrace()
            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
            }
            return importTurtleFilesOld(fileNames, bnodeDict)
        }
/*Coverage Unreachable*/
    }

    @JsName("import_turtle_string_a")
    /*suspend*/ public fun importTurtleString_a(data: String): String {
        return importTurtleString(data, mutableMapOf())
    }

    @JsName("import_turtle_string")
    /*suspend*/ public fun importTurtleString(data: String, bnodeDict: MutableMap<String, Int>): String {
        val query = Query()
        val key = "${query.getTransactionID()}"
        try {
            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to "$key"))
                query.setDictionaryUrl("${tripleStoreManager.getLocalhost()}/distributed/query/dictionary?key=$key")
            }
            var counter = 0
            val store = tripleStoreManager.getDefaultGraph()
            val bufS = IntArray(INTERNAL_BUFFER_SIZE)
            val bufP = IntArray(INTERNAL_BUFFER_SIZE)
            val bufO = IntArray(INTERNAL_BUFFER_SIZE)
            var bufPos = 0
            val iter = MyStringStream(data)
            try {
                val arr = arrayOf(ColumnIteratorMultiValue3(bufS, bufPos), ColumnIteratorMultiValue3(bufP, bufPos), ColumnIteratorMultiValue3(bufO, bufPos))
                val arr2 = arrayOf(arr[0] as ColumnIterator, arr[1] as ColumnIterator, arr[2] as ColumnIterator)
                val cache = store.modify_create_cache(EModifyTypeExt.INSERT)
                val x = object : Turtle2Parser(iter) {
                    override fun onTriple() {
                        counter++
                        if (bufPos == bufS.size) {
                            for (i in 0 until 3) {
                                arr[i].reset(bufPos)
                            }
                            store.modify_cache(query, arr2, EModifyTypeExt.INSERT, cache, false)
                            bufPos = 0
                        }
                        bufS[bufPos] = helperImportRaw(bnodeDict, triple[0])
                        bufP[bufPos] = helperImportRaw(bnodeDict, triple[1])
                        bufO[bufPos] = helperImportRaw(bnodeDict, triple[2])
                        bufPos++
                    }
                }
                x.parse()
                for (i in 0 until 3) {
                    arr[i].reset(bufPos)
                }
                store.modify_cache(query, arr2, EModifyTypeExt.INSERT, cache, true)
            } catch (e: Exception) {
                e.printStackTrace()
                println("fast_parser :: error in turtle-string")
                throw e
            }
            tripleStoreManager.commit(query)
            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
            }
            return "successfully imported $counter Triples"
        } catch (e: Exception) {
            e.printStackTrace()
            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
            }
            throw e
        }
    }

    @JsName("import_intermediate_files")
    /*suspend*/ public fun importIntermediateFiles(fileNames: String): String {
        return importIntermediateFiles(fileNames, false)
    }

    public fun setEstimatedPartitionsFromFile(filename: String) {
        val filePartitions = File("$filename")
        if (filePartitions.exists()) {
            tripleStoreManager.updateDefaultTripleStoreLayout { layout ->
                try {
                    filePartitions.forEachLine {
                        val t = it.split(",")
                        val idx = EIndexPatternExt.names.indexOf(t[0])
                        if (t[1] == "-1") {
                            layout.addIndex { it.simple(idx) }
                        } else if (t[1] == "1") {
                            layout.addIndex { it.partitionedByID(idx, t[2].toInt(), 1) }
                        } else if (t[1] == "2") {
                            layout.addIndex { it.partitionedByID(idx, t[2].toInt(), 2) }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    @JsName("import_intermediate_files_a")
    /*suspend*/ public fun importIntermediateFiles(fileNames: String, convert_to_bnodes: Boolean): String {
        val query = Query()
        val key = "${query.getTransactionID()}"
        try {
            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to "$key"))
                query.setDictionaryUrl("${tripleStoreManager.getLocalhost()}/distributed/query/dictionary?key=$key")
            }
            tripleStoreManager.resetDefaultTripleStoreLayout()
            tripleStoreManager.resetGraph(query, TripleStoreManager.DEFAULT_GRAPH_NAME)
            var counter = 0L
            val store = tripleStoreManager.getDefaultGraph()
            val bufS = IntArray(INTERNAL_BUFFER_SIZE)
            val bufP = IntArray(INTERNAL_BUFFER_SIZE)
            val bufO = IntArray(INTERNAL_BUFFER_SIZE)
            var bufPos = 0
            val fileNamesS = fileNames.split(";")
            for (fileName in fileNamesS) {
                println("importing intermediate file '$fileName'")
                val startTime = DateHelperRelative.markNow()
                if (fileNamesS.size == 1) {
                    setEstimatedPartitionsFromFile("$fileName.partitions")
                    tripleStoreManager.resetGraph(query, TripleStoreManager.DEFAULT_GRAPH_NAME)
                }
                val (mapping, mappingLength) = nodeGlobalDictionary.importFromDictionaryFile(fileName)
                val dictTime = DateHelperRelative.elapsedSeconds(startTime)
                val arr = arrayOf(ColumnIteratorMultiValue3(bufS, bufPos), ColumnIteratorMultiValue3(bufP, bufPos), ColumnIteratorMultiValue3(bufO, bufPos))
                val arr2 = arrayOf(arr[0] as ColumnIterator, arr[1] as ColumnIterator, arr[2] as ColumnIterator)
                var requireSorting = false
                for (i in 1 until mappingLength) {
                    if (mapping[i] < mapping[i - 1]) {
                        println("${mapping[i]} < ${mapping[i - 1]} -> requireSorting")
                        requireSorting = true
                        break
                    }
                }
                if (requireSorting) {
                    val cache = store.modify_create_cache(EModifyTypeExt.INSERT)
                    val fileTriples = TriplesIntermediateReader("$fileName.spo")
                    bufPos = 0
                    fileTriples.readAll { it ->
                        if (bufPos == bufS.size) {
                            for (i in 0 until 3) {
                                arr[i].reset(bufPos)
                            }
                            store.modify_cache(query, arr2, EModifyTypeExt.INSERT, cache, false)
                            bufPos = 0
                        }
                        bufS[bufPos] = mapping[it[0]]
                        bufP[bufPos] = mapping[it[1]]
                        bufO[bufPos] = mapping[it[2]]
                        bufPos++
                        counter++
                        if (counter % 10000 == 0L) {
                            println("imported $counter triples without sorting")
                        }
                    }
                    for (i in 0 until 3) {
                        arr[i].reset(bufPos)
                    }
                    store.modify_cache(query, arr2, EModifyTypeExt.INSERT, cache, true)
                } else {
                    val orders = arrayOf(
                        intArrayOf(0, 1, 2), // "spo" -> "spo" -> "spo"
                        intArrayOf(0, 2, 1), // "spo" -> "sop" -> "spo"
                        intArrayOf(1, 0, 2), // "spo" -> "pso" -> "spo"
                        intArrayOf(1, 2, 0), // "spo" -> "pos" -> "osp" !!!
                        intArrayOf(2, 0, 1), // "spo" -> "osp" -> "pos" !!!
                        intArrayOf(2, 1, 0), // "spo" -> "ops" -> "spo"
                    )
                    val ordersReverse = arrayOf(
                        orders[0],
                        orders[1],
                        orders[2],
                        orders[4], // swapped here !!!! intentionally
                        orders[3],
                        orders[5]
                    )
                    val orderNames = arrayOf("spo", "sop", "pso", "pos", "osp", "ops")
                    val orderPatterns = arrayOf(
                        EIndexPatternExt.SPO,
                        EIndexPatternExt.SOP,
                        EIndexPatternExt.PSO,
                        EIndexPatternExt.POS,
                        EIndexPatternExt.OSP,
                        EIndexPatternExt.OPS,
                    )
                    for (o in 0 until 6) {
                        counter = 0
                        val order = ordersReverse[o]
                        val orderName = orderNames[o]
                        val sortedBy = orderPatterns[o]
                        val cache = store.modify_create_cache_sorted(EModifyTypeExt.INSERT, sortedBy)
                        val fileTriples = TriplesIntermediateReader("$fileName.$orderName")
//                        val debugFile = File("debug-input-$orderName").openOutputStream(false)
                        bufPos = 0
                        fileTriples.readAll { it ->
                            if (bufPos == bufS.size) {
                                for (i in 0 until 3) {
                                    arr[i].reset(bufPos)
                                }
                                store.modify_cache_sorted(query, arr2, EModifyTypeExt.INSERT, cache, sortedBy, false)
                                bufPos = 0
                            }
                            // debugFile.println("${mapping[it[0]]} ${mapping[it[1]]} ${mapping[it[2]]}")
                            bufS[bufPos] = mapping[it[order[0]]]
                            bufP[bufPos] = mapping[it[order[1]]]
                            bufO[bufPos] = mapping[it[order[2]]]
                            bufPos++
                            counter++
                            if (counter % 10000 == 0L) {
                                println("imported $counter triples for index $orderName")
                            }
                        }
                        // debugFile.close()
                        for (i in 0 until 3) {
                            arr[i].reset(bufPos)
                        }
                        store.modify_cache_sorted(query, arr2, EModifyTypeExt.INSERT, cache, sortedBy, true)
                    }
                }
                val totalTime = DateHelperRelative.elapsedSeconds(startTime)
                val storeTime = totalTime - dictTime
                println("imported file $fileName,$counter,$totalTime,$dictTime,$storeTime")
            }
            tripleStoreManager.commit(query)
            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
            }
            return "successfully imported $counter Triples"
        } catch (e: Throwable) {
            e.printStackTrace()
            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
            }
            throw e
        }
/*Coverage Unreachable*/
    }

    @JsName("import_xml_data")
    /*suspend*/ public fun importXmlData(data: String): String {
        val query = Query()
        val import2 = POPValuesImportXML(query, listOf("s", "p", "o"), XMLElementFromXML()(data)!!)
        val key = "${query.getTransactionID()}"
        if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
            communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to "$key"))
            query.setDictionaryUrl("${tripleStoreManager.getLocalhost()}/distributed/query/dictionary?key=$key")
        }
        val import = import2.evaluateRoot()
        val dataLocal = arrayOf(import.columns["s"]!!, import.columns["p"]!!, import.columns["o"]!!)
        val store = tripleStoreManager.getDefaultGraph()
        val cache = store.modify_create_cache(EModifyTypeExt.INSERT)
        store.modify_cache(query, dataLocal, EModifyTypeExt.INSERT, cache, true)
        tripleStoreManager.commit(query)
        query.commited = true
        if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
            communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
        }
        return XMLElement("success").toString()
    }

    @JsName("evaluate_sparql_to_operatorgraph_a")
    /*suspend*/ public fun evaluateSparqlToOperatorgraphA(query: String): IOPBase {
        return evaluateSparqlToOperatorgraphB(query, false)
    }

    @JsName("evaluate_sparql_to_operatorgraph_b")
    /*suspend*/ public fun evaluateSparqlToOperatorgraphB(query: String, logOperatorGraph: Boolean): IOPBase {
        val q = Query()
        SanityCheck.println { "----------String Query" }
        SanityCheck.println { query }
        SanityCheck.println { "----------Abstract Syntax Tree" }
        val lcit = LexerCharIterator(query)
        val tit = TokenIteratorSPARQLParser(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        val parser = SPARQLParser(ltit)
        val astNode = parser.expr()
        SanityCheck.println { astNode }
        SanityCheck.println { "----------Logical Operator Graph" }
        val lopNode = astNode.visit(OperatorGraphVisitor(q))
        SanityCheck.println { lopNode }
        SanityCheck.println { "----------Logical Operator Graph optimized" }
        val lopNode2 = LogicalOptimizer(q).optimizeCall(lopNode)
        SanityCheck.println { lopNode2 }
        SanityCheck.println { "----------Physical Operator Graph" }
        val popOptimizer = PhysicalOptimizer(q)
        val popNode = popOptimizer.optimizeCall(lopNode2)
        SanityCheck.println { popNode }
        if (logOperatorGraph) {
            println("----------")
            println(query)
            println(">>>>>>>>>>")
            println(popNode.toString())
            println("<<<<<<<<<<")
            println(OperatorGraphToLatex(popNode.toString(), ""))
        }
        return popNode
    }

    @JsName("evaluate_operatorgraph_to_result")
    /*suspend*/ public fun evaluateOperatorgraphToResult(node: IOPBase, output: IMyOutputStream) {
        evaluateOperatorgraphToResultA(node, output, EQueryResultToStreamExt.DEFAULT_STREAM)
    }

    @JsName("evaluate_operatorgraph_to_result_a")
    /*suspend*/ public fun evaluateOperatorgraphToResultA(node: IOPBase, output: IMyOutputStream, evaluator: EQueryResultToStream): Any? {
        var res = when (evaluator) {
            EQueryResultToStreamExt.DEFAULT_STREAM -> QueryResultToStream(node, output)
            EQueryResultToStreamExt.XML_STREAM -> QueryResultToXMLStream(node, output)
            EQueryResultToStreamExt.TURTLE_STREAM -> QueryResultToTurtleStream(node, output)
            EQueryResultToStreamExt.EMPTY_STREAM -> QueryResultToEmptyStream(node, output)
            EQueryResultToStreamExt.EMPTYDICTIONARY_STREAM -> QueryResultToEmptyWithDictionaryStream(node, output)
            EQueryResultToStreamExt.MEMORY_TABLE -> QueryResultToMemoryTable(node)
            EQueryResultToStreamExt.XML_ELEMENT -> QueryResultToXMLElement.toXML(node)
            else -> throw UnreachableException()
        }
        tripleStoreManager.commit(node.getQuery())
        node.getQuery().setCommited()
        return res
    }

    @JsName("evaluate_sparql_to_result_b")
    /*suspend*/ public fun evaluateSparqlToResultB(query: String): String {
        return evaluateSparqlToResultC(query, false)
    }

    @JsName("evaluate_sparql_to_result_c")
    /*suspend*/ public fun evaluateSparqlToResultC(query: String, logOperatorGraph: Boolean): String {
        val node = evaluateSparqlToOperatorgraphB(query, logOperatorGraph)
        val buf = MyPrintWriter(true)
        evaluateOperatorgraphToResult(node, buf)
        return buf.toString()
    }

    @JsName("evaluate_sparql_to_result_a")
    /*suspend*/ public fun evaluateSparqlToResultA(query: String, output: IMyOutputStream) {
        evaluateSparqlToResultD(query, output, false)
    }

    @JsName("evaluate_sparql_to_result_d")
    /*suspend*/ public fun evaluateSparqlToResultD(query: String, output: IMyOutputStream, logOperatorGraph: Boolean) {
        val node = evaluateSparqlToOperatorgraphB(query, logOperatorGraph)
        evaluateOperatorgraphToResult(node, output)
    }

    @JsName("evaluate_operatorgraphXML_to_result_a")
    /*suspend*/ public fun evaluateOperatorgraphxmlToResultA(query: String): String {
        return evaluateOperatorgraphxmlToResultB(query, false)
    }

    @JsName("evaluate_operatorgraphXML_to_result_b")
    /*suspend*/ public fun evaluateOperatorgraphxmlToResultB(query: String, logOperatorGraph: Boolean): String {
        val q = Query()
        val popNode = XMLElementToOPBase(q, XMLElementFromXML()(query)!!)
        SanityCheck.println { popNode }
        if (logOperatorGraph) {
            SanityCheck.suspended {
                SanityCheck.println { "----------" }
                SanityCheck.println { query }
                SanityCheck.println { ">>>>>>>>>>" }
                val a = popNode.toString()
                SanityCheck.println { a }
                SanityCheck.println { "<<<<<<<<<<" }
                val b = OperatorGraphToLatex(popNode.toString(), "")
                SanityCheck.println { b }
            }
        }
        val buf = MyPrintWriter(true)
        evaluateOperatorgraphToResult(popNode, buf)
        return buf.toString()
    }

    @JsName("close")
    public fun close() {
        initializerLock.withLock {
            if (initialized) {
                println("LuposdateEndpoint.close")
                initialized = false
                nodeGlobalDictionary.close()
                tripleStoreManager.close()
                BufferManagerExt.close()
            }
        }
    }

    @JsName("initialize")
    public fun initialize() {
        initializerLock.withLock {
            if (!initialized) {
                println("LuposdateEndpoint.initialize")
                initialized = true
                nodeGlobalDictionary = DictionaryFactory.createGlobalDictionary()
                val hostnames = Platform.getEnv("LUPOS_PROCESS_URLS", "localhost:80")!!.split(",").toTypedArray()
                val localhost = hostnames[Platform.getEnv("LUPOS_PROCESS_ID", "0")!!.toInt()]
                tripleStoreManager = TripleStoreManagerImpl(hostnames, localhost)
                tripleStoreManager.initialize()
                distributedOptimizerQueryFactory = { DistributedOptimizerQuery() }
                XMLElement.parseFromAnyRegistered["n3"] = XMLElementFromN3()
                XMLElement.parseFromAnyRegistered["ttl"] = XMLElementFromN3()
                XMLElement.parseFromAnyRegistered["srx"] = XMLElementFromXML()
                XMLElement.parseFromAnyRegistered["srj"] = XMLElementFromJson()
                XMLElement.parseFromAnyRegistered["csv"] = XMLElementFromCsv()
                XMLElement.parseFromAnyRegistered["tsv"] = XMLElementFromTsv()
                Platform.setShutdownHock {
                    close()
                }
            }
        }
    }

    init {
        initialize()
    }
}
