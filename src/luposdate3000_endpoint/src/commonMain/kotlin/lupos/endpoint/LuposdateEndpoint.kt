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

import lupos.buffer_manager.BufferManager
import lupos.dictionary.DictionaryFactory
import lupos.operator.base.Query
import lupos.operator.base.iterator.ColumnIteratorMultiValue3
import lupos.operator.factory.XMLElementToOPBase
import lupos.operator.physical.noinput.POPValuesImportXML
import lupos.optimizer.ast.OperatorGraphVisitor
import lupos.optimizer.distributed.query.DistributedOptimizerQuery
import lupos.optimizer.logical.LogicalOptimizer
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.parser.InputToIntermediate
import lupos.parser.LexerCharIterator
import lupos.parser.LookAheadTokenIterator
import lupos.parser.sparql1_1.SPARQLParser
import lupos.parser.sparql1_1.TokenIteratorSPARQLParser
import lupos.result_format.EQueryResultToStream
import lupos.result_format.EQueryResultToStreamExt
import lupos.result_format.QueryResultToEmptyStream
import lupos.result_format.QueryResultToEmptyWithDictionaryStream
import lupos.result_format.QueryResultToMemoryTable
import lupos.result_format.QueryResultToTurtleStream
import lupos.result_format.QueryResultToXMLElement
import lupos.result_format.QueryResultToXMLStream
import lupos.shared.DateHelperRelative
import lupos.shared.EIndexPatternExt
import lupos.shared.EModifyTypeExt
import lupos.shared.EPartitionModeExt
import lupos.shared.IMyOutputStream
import lupos.shared.Luposdate3000Instance
import lupos.shared.MemoryTable
import lupos.shared.MyLock
import lupos.shared.OperatorGraphToLatex
import lupos.shared.SanityCheck
import lupos.shared.TripleStoreManager
import lupos.shared.UnreachableException
import lupos.shared.XMLElement
import lupos.shared.XMLElementFromXML
import lupos.shared.fileformat.DictionaryIntermediate
import lupos.shared.fileformat.TriplesIntermediateReader
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared_inline.File
import lupos.shared_inline.FileExt
import lupos.shared_inline.MyPrintWriter
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
    internal val initializerLock = MyLock()

    @JvmField
    internal val instances = mutableListOf<Luposdate3000Instance>()

    @JsName("import_turtle_string")
    /*suspend*/ public fun importTurtleString(instance: Luposdate3000Instance, data: String): String {
        val dir = FileExt.createTempDirectory()
        val fileName = dir + "data.n3"
        File(fileName).withOutputStream { out ->
            out.println(data)
        }
        val res = importTurtleFile(instance, fileName)
        File(dir).deleteRecursively()
        return res
    }

    public fun setEstimatedPartitionsFromFile(instance: Luposdate3000Instance, filename: String) {
        val filePartitions = File(filename)
        if (filePartitions.exists()) {
            instance.tripleStoreManager!!.updateDefaultTripleStoreLayout { layout ->
                try {
                    filePartitions.forEachLine { it2 ->
                        val t = it2.split(",")
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

    @JsName("import_turtle_file")
    /*suspend*/ public fun importTurtleFile(instance: Luposdate3000Instance, fileName: String): String {
        if (!DictionaryIntermediate.fileExists(fileName)) {
            InputToIntermediate.process(fileName, instance)
        }
        return importIntermediateFile(instance, fileName)
    }

    /*suspend*/ private fun importIntermediateFile(instance: Luposdate3000Instance, fileName: String): String {
        val query = Query(instance)
        val key = "${query.getTransactionID()}"
        try {
            if (instance.tripleStoreManager!!.getPartitionMode() == EPartitionModeExt.Process) {
                instance.communicationHandler!!.sendData(instance.tripleStoreManager!!.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to key))
                query.setDictionaryUrl("${instance.tripleStoreManager!!.getLocalhost()}/distributed/query/dictionary?key=$key")
            }
            instance.tripleStoreManager!!.resetDefaultTripleStoreLayout()
            instance.tripleStoreManager!!.resetGraph(query, TripleStoreManager.DEFAULT_GRAPH_NAME)
            var counter = 0L
            val store = instance.tripleStoreManager!!.getDefaultGraph()
            val bufS = IntArray(instance.LUPOS_BUFFER_SIZE)
            val bufP = IntArray(instance.LUPOS_BUFFER_SIZE)
            val bufO = IntArray(instance.LUPOS_BUFFER_SIZE)
            var bufPos = 0
            println("importing intermediate file '$fileName'")
            val startTime = DateHelperRelative.markNow()
            setEstimatedPartitionsFromFile(instance, "$fileName.partitions")
            instance.tripleStoreManager!!.resetGraph(query, TripleStoreManager.DEFAULT_GRAPH_NAME)
            val (mapping, mappingLength) = instance.nodeGlobalDictionary!!.importFromDictionaryFile(fileName)
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
                fileTriples.readAll {
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
                println("imported $counter triples without sorting")
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
                    bufPos = 0
                    fileTriples.readAll {
                        if (bufPos == bufS.size) {
                            for (i in 0 until 3) {
                                arr[i].reset(bufPos)
                            }
                            store.modify_cache_sorted(query, arr2, EModifyTypeExt.INSERT, cache, sortedBy, false)
                            bufPos = 0
                        }
                        bufS[bufPos] = mapping[it[order[0]]]
                        bufP[bufPos] = mapping[it[order[1]]]
                        bufO[bufPos] = mapping[it[order[2]]]
                        bufPos++
                        counter++
                        if (counter % 10000 == 0L) {
                            println("imported $counter triples for index $orderName")
                        }
                    }
                    println("imported $counter triples for index $orderName")
                    for (i in 0 until 3) {
                        arr[i].reset(bufPos)
                    }
                    store.modify_cache_sorted(query, arr2, EModifyTypeExt.INSERT, cache, sortedBy, true)
                }
            }
            val totalTime = DateHelperRelative.elapsedSeconds(startTime)
            val storeTime = totalTime - dictTime
            println("imported file $fileName,$counter,$totalTime,$dictTime,$storeTime")
            instance.tripleStoreManager!!.commit(query)
            if (instance.tripleStoreManager!!.getPartitionMode() == EPartitionModeExt.Process) {
                instance.communicationHandler!!.sendData(instance.tripleStoreManager!!.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to key))
            }
            return "successfully imported $counter Triples"
        } catch (e: Throwable) {
            e.printStackTrace()
            if (instance.tripleStoreManager!!.getPartitionMode() == EPartitionModeExt.Process) {
                instance.communicationHandler!!.sendData(instance.tripleStoreManager!!.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to key))
            }
            throw e
        }
/*Coverage Unreachable*/
    }

    @JsName("import_xml_data")
    /*suspend*/ public fun importXmlData(instance: Luposdate3000Instance, data: String): String {
        val query = Query(instance)
        val import2 = POPValuesImportXML(query, listOf("s", "p", "o"), XMLElementFromXML()(data)!!)
        val key = "${query.getTransactionID()}"
        if (instance.tripleStoreManager!!.getPartitionMode() == EPartitionModeExt.Process) {
            instance.communicationHandler!!.sendData(instance.tripleStoreManager!!.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to key))
            query.setDictionaryUrl("${instance.tripleStoreManager!!.getLocalhost()}/distributed/query/dictionary?key=$key")
        }
        val import = import2.evaluateRoot()
        val dataLocal = arrayOf(import.columns["s"]!!, import.columns["p"]!!, import.columns["o"]!!)
        val store = instance.tripleStoreManager!!.getDefaultGraph()
        val cache = store.modify_create_cache(EModifyTypeExt.INSERT)
        store.modify_cache(query, dataLocal, EModifyTypeExt.INSERT, cache, true)
        instance.tripleStoreManager!!.commit(query)
        query.commited = true
        if (instance.tripleStoreManager!!.getPartitionMode() == EPartitionModeExt.Process) {
            instance.communicationHandler!!.sendData(instance.tripleStoreManager!!.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to key))
        }
        return XMLElement("success").toString()
    }

    @JsName("evaluate_sparql_to_operatorgraph_a")
    /*suspend*/ public fun evaluateSparqlToOperatorgraphA(instance: Luposdate3000Instance, query: String): IOPBase {
        return evaluateSparqlToOperatorgraphB(instance, query, false)
    }

    @JsName("evaluate_sparql_to_operatorgraph_b")
    /*suspend*/ public fun evaluateSparqlToOperatorgraphB(instance: Luposdate3000Instance, query: String, logOperatorGraph: Boolean): IOPBase {
        val q = Query(instance)
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
    /*suspend*/ public fun evaluateOperatorgraphToResult(instance: Luposdate3000Instance, node: IOPBase, output: IMyOutputStream) {
        evaluateOperatorgraphToResultA(instance, node, output, EQueryResultToStreamExt.DEFAULT_STREAM)
    }

    @JsName("evaluate_operatorgraph_to_result_a")
    /*suspend*/ public fun evaluateOperatorgraphToResultA(instance: Luposdate3000Instance, node: IOPBase, output: IMyOutputStream, evaluator: EQueryResultToStream): Any {
        val res = when (evaluator) {
            EQueryResultToStreamExt.DEFAULT_STREAM -> QueryResultToStream(node, output)
            EQueryResultToStreamExt.XML_STREAM -> QueryResultToXMLStream(node, output)
            EQueryResultToStreamExt.TURTLE_STREAM -> QueryResultToTurtleStream(node, output)
            EQueryResultToStreamExt.EMPTY_STREAM -> QueryResultToEmptyStream(node, output)
            EQueryResultToStreamExt.EMPTYDICTIONARY_STREAM -> QueryResultToEmptyWithDictionaryStream(node, output)
            EQueryResultToStreamExt.MEMORY_TABLE -> QueryResultToMemoryTable(node)
            EQueryResultToStreamExt.XML_ELEMENT -> QueryResultToXMLElement.toXML(node)
            else -> throw UnreachableException()
        }
        instance.tripleStoreManager!!.commit(node.getQuery())
        node.getQuery().setCommited()
        return res
    }

    @JsName("evaluate_sparql_to_result_b")
    /*suspend*/ public fun evaluateSparqlToResultB(instance: Luposdate3000Instance, query: String): String {
        return evaluateSparqlToResultC(instance, query, false)
    }

    @JsName("evaluate_sparql_to_result_c")
    /*suspend*/ public fun evaluateSparqlToResultC(instance: Luposdate3000Instance, query: String, logOperatorGraph: Boolean): String {
        val node = evaluateSparqlToOperatorgraphB(instance, query, logOperatorGraph)
        val buf = MyPrintWriter(true)
        evaluateOperatorgraphToResult(instance, node, buf)
        return buf.toString()
    }

    @JsName("evaluate_sparql_to_result_a")
    /*suspend*/ public fun evaluateSparqlToResultA(instance: Luposdate3000Instance, query: String, output: IMyOutputStream) {
        evaluateSparqlToResultD(instance, query, output, false)
    }

    @JsName("evaluate_sparql_to_result_d")
    /*suspend*/ public fun evaluateSparqlToResultD(instance: Luposdate3000Instance, query: String, output: IMyOutputStream, logOperatorGraph: Boolean) {
        val node = evaluateSparqlToOperatorgraphB(instance, query, logOperatorGraph)
        evaluateOperatorgraphToResult(instance, node, output)
    }

    @JsName("evaluate_operatorgraphXML_to_result_a")
    /*suspend*/ public fun evaluateOperatorgraphxmlToResultA(instance: Luposdate3000Instance, query: String): String {
        return evaluateOperatorgraphxmlToResultB(instance, query, false)
    }

    @JsName("evaluate_operatorgraphXML_to_result_b")
    /*suspend*/ public fun evaluateOperatorgraphxmlToResultB(instance: Luposdate3000Instance, query: String, logOperatorGraph: Boolean): String {
        val q = Query(instance)
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
        evaluateOperatorgraphToResult(instance, popNode, buf)
        return buf.toString()
    }

    public fun close(instance: Luposdate3000Instance) {
        try {
            if (instance.initialized) {
                instance.initialized = false
                instance.nodeGlobalDictionary!!.close()
                instance.nodeGlobalDictionary = null
                instance.tripleStoreManager!!.close()
                instance.tripleStoreManager = null
                instance.bufferManager!!.close()
                instance.bufferManager = null
            }
        } finally {
            initializerLock.withLock {
                instances.remove(instance)
            }
        }
    }

    @JsName("close")
    public fun close() {
        var l = mutableListOf<Luposdate3000Instance>()
        initializerLock.withLock {
            l.addAll(instances)
        }
        for (i in l) {
            close(i)
        }
    }

    @JsName("initialize")
    public fun initialize(): Luposdate3000Instance {
        return initializeB(Luposdate3000Instance())
    }

    @JsName("initializeB")
    public fun initializeB(instance: Luposdate3000Instance): Luposdate3000Instance {
        initializerLock.withLock {
            instances.add(instance)
            instance.bufferManager = BufferManager(instance)
            instance.nodeGlobalDictionary = DictionaryFactory.createGlobalDictionary(instance)
            instance.tripleStoreManager = TripleStoreManagerImpl(instance.LUPOS_PROCESS_URLS, instance.LUPOS_PROCESS_URLS[instance.LUPOS_PROCESS_ID], instance)
            instance.tripleStoreManager!!.initialize()
            instance.distributedOptimizerQueryFactory = { DistributedOptimizerQuery() }
            instance.initialized = true
        }
        return instance!!
    }

    init {
        MemoryTable.parseFromAnyRegistered["n3"] = MemoryTableFromN3()
        MemoryTable.parseFromAnyRegistered["ttl"] = MemoryTableFromN3()
        MemoryTable.parseFromAnyRegistered["srx"] = MemoryTableFromXML()
        MemoryTable.parseFromAnyRegistered["csv"] = MemoryTableFromCsv()
        MemoryTable.parseFromAnyRegistered["tsv"] = MemoryTableFromTsv()
        Platform.setShutdownHock {
            close()
        }
    }
}
