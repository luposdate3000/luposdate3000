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
import lupos.dictionary.DictionaryCache
import lupos.dictionary.DictionaryFactory
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.operator.factory.XMLElementToOPBase
import lupos.optimizer.ast.OperatorGraphVisitor
import lupos.optimizer.distributed.query.DistributedOptimizerQuery
import lupos.optimizer.logical.LogicalOptimizer
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.parser.InputToIntermediate
import lupos.parser.LexerCharIterator
import lupos.parser.LookAheadTokenIterator
import lupos.parser.sparql1_1.SPARQLParser
import lupos.parser.sparql1_1.TokenIteratorSPARQLParser
import lupos.parser.turtle.TurtleParserWithStringTriples
import lupos.parser.turtle.TurtleScanner
import lupos.result_format.EQueryResultToStream
import lupos.result_format.EQueryResultToStreamExt
import lupos.result_format.ResultFormatManager
import lupos.shared.DateHelperRelative
import lupos.shared.DictionaryValueHelper
import lupos.shared.EIndexPatternExt
import lupos.shared.EModifyTypeExt
import lupos.shared.EPartitionModeExt
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.IMyOutputStream
import lupos.shared.Luposdate3000Instance
import lupos.shared.MemoryTable
import lupos.shared.MyLock
import lupos.shared.OPVisualEdge
import lupos.shared.OPVisualGraph
import lupos.shared.OPVisualNode
import lupos.shared.OperatorGraphToLatex
import lupos.shared.SanityCheck
import lupos.shared.TripleStoreManager
import lupos.shared.XMLElementFromXML
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.ByteArrayHelper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.File
import lupos.shared.inline.FileExt
import lupos.shared.inline.MyPrintWriter
import lupos.shared.inline.Platform
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.inline.fileformat.DictionaryIntermediate
import lupos.shared.inline.fileformat.TriplesIntermediateReader
import lupos.shared.operator.IOPBase
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

    @JsName("load_shacl_ontology")
    /*suspend*/ public fun loadShaclOntology(instance: Luposdate3000Instance, data: String): String {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt:85"/*SOURCE_FILE_END*/ }, { instance.LUPOS_PROCESS_ID == 0 })
        val lcit = LexerCharIterator(data)
        val tit = TurtleScanner(lcit)
        val dict = instance.nodeGlobalDictionary!!
        val cache2 = instance.nodeGlobalOntologyCache
        val cache = if (cache2 == null) {
            val c = DictionaryCache(0)
            instance.nodeGlobalOntologyCache = c
            c
        } else {
            cache2
        }
        val ltit = LookAheadTokenIterator(tit, 3)
        val x = object : TurtleParserWithStringTriples() {
            /*suspend*/ override fun consume_triple(s: String, p: String, o: String) {
                val buffer = ByteArrayWrapper()
                for (v in listOf(s, p, o)) {
                    DictionaryHelper.sparqlToByteArray(buffer, InputToIntermediate.helperCleanString(v))
                    if (DictionaryHelper.byteArrayToType(buffer) != ETripleComponentTypeExt.BLANK_NODE) {
                        cache.insertValuePairExtend(buffer, dict.createValue(buffer))
                    }
                }
            }
        }
        x.ltit = ltit
        x.parse()
        var data = ByteArrayWrapper()
        cache.forEach { value, key ->
            var c1 = ByteArrayWrapperExt.getSize(data)
            val c2 = c1 + DictionaryValueHelper.getSize() + 4
            ByteArrayWrapperExt.setSize(data, c2, true)
            DictionaryValueHelper.toByteArray(data, c1, key)
            c1 += DictionaryValueHelper.getSize()
            ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(data), c1, ByteArrayWrapperExt.getSize(value))
            ByteArrayWrapperExt.appendTo(value, data)
        }
        val c3 = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, c3 + DictionaryValueHelper.getSize(), true)
        DictionaryValueHelper.toByteArray(ByteArrayWrapperExt.getBuf(data), c3, DictionaryValueHelper.nullValue)
        for (i in 1 until instance.LUPOS_PROCESS_URLS.size) {
            val (input, output) = instance.communicationHandler!!.openConnection(instance.LUPOS_PROCESS_URLS[i], "/shacl/ontology/load", mapOf(), -1)
            output.write(ByteArrayWrapperExt.getBuf(data), ByteArrayWrapperExt.getSize(data))
            output.close()
            input.close()
        }
        return "successfully loaded ontology"
    }

    @JsName("import_turtle_string")
    /*suspend*/ public fun importTurtleString(instance: Luposdate3000Instance, data: String): String {
        return importTurtleString(instance, data, TripleStoreManager.DEFAULT_GRAPH_NAME)
    }

    @JsName("import_turtle_string_b")
    /*suspend*/ public fun importTurtleString(instance: Luposdate3000Instance, data: String, graphName: String): String {
        println("importTurtleString >$graphName<")
        val dir = FileExt.createTempDirectory()
        val fileName = dir + "data.n3"
        File(fileName).withOutputStream { out ->
            out.println(data)
        }
        val res = importTurtleFile(instance, fileName, graphName)
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
                        when (t[1]) {
                            "Simple" -> layout.addIndex { it.simple(idx) }
                            "ID0" -> layout.addIndex { it.partitionedByID(idx, t[2].toInt(), 0) }
                            "ID1" -> layout.addIndex { it.partitionedByID(idx, t[2].toInt(), 1) }
                            "ID2" -> layout.addIndex { it.partitionedByID(idx, t[2].toInt(), 2) }
                            "KEY" -> layout.addIndex { it.partitionedByKey(idx, t[2].toInt()) }
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
        return importTurtleFile(instance, fileName, TripleStoreManager.DEFAULT_GRAPH_NAME)
    }

    @JsName("import_turtle_file_b")
    /*suspend*/ public fun importTurtleFile(instance: Luposdate3000Instance, fileName: String, graphName: String): String {
        println("importTurtleFile >$graphName<")
        if (!DictionaryIntermediate.fileExists(fileName)) {
            InputToIntermediate.process(fileName, instance)
        }
        return importIntermediateFile(instance, fileName, graphName)
    }

    /*suspend*/ private fun importIntermediateFile(instance: Luposdate3000Instance, fileName: String): String {
        return importIntermediateFile(instance, fileName, TripleStoreManager.DEFAULT_GRAPH_NAME)
    }

    /*suspend*/ private fun importIntermediateFile(instance: Luposdate3000Instance, fileName: String, graphName: String): String {
        println("importIntermediateFile >$graphName<")
        val query = Query(instance)
        val key = "${query.getTransactionID()}"
        try {
            if (instance.LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
                instance.communicationHandler!!.sendData(instance.LUPOS_PROCESS_URLS[0], "/distributed/query/dictionary/register", mapOf("key" to key), query.getTransactionID().toInt())
                query.setDictionaryUrl("${instance.LUPOS_PROCESS_URLS[0]}/distributed/query/dictionary?key=$key")
            }
            instance.tripleStoreManager!!.resetDefaultTripleStoreLayout()
            var counter = 0L
            println("importing intermediate file '$fileName'")
            val startTime = DateHelperRelative.markNow()
            setEstimatedPartitionsFromFile(instance, "$fileName.partitions")
            if (instance.tripleStoreManager!!.getGraphNames().contains(graphName)) {
                instance.tripleStoreManager!!.dropGraph(query, graphName)
            }
            try {
                instance.tripleStoreManager!!.createGraph(query, graphName)
            } catch (e: Throwable) {
            }
            val store = instance.tripleStoreManager!!.getGraph(graphName)
            val (mapping, mappingLength) = instance.nodeGlobalDictionary!!.importFromDictionaryFile(fileName)
            val dictTime = DateHelperRelative.elapsedSeconds(startTime)
            var requireSorting = false
            for (i in 1 until mappingLength) {
                if (mapping[i] < mapping[i - 1]) {
                    println("${mapping[i]} < ${mapping[i - 1]} -> requireSorting")
                    requireSorting = true
                    break
                }
            }
            if (requireSorting) {
                val cache = store.modify_create_cache(query, EModifyTypeExt.INSERT)
                val fileTriples = TriplesIntermediateReader("$fileName.spo")
                fileTriples.readAll {
                    cache.writeRow(mapping[DictionaryValueHelper.toInt(it[0])], mapping[DictionaryValueHelper.toInt(it[1])], mapping[DictionaryValueHelper.toInt(it[2])], query)
                    counter++
                    if (counter % 10000 == 0L) {
                        println("imported $counter triples without sorting")
                    }
                }
                println("imported $counter triples without sorting")
                cache.close()
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
                    val cache = store.modify_create_cache_sorted(query, EModifyTypeExt.INSERT, sortedBy)
                    val fileTriples = TriplesIntermediateReader("$fileName.$orderName")
                    fileTriples.readAll {
                        cache.writeRow(mapping[DictionaryValueHelper.toInt(it[0])], mapping[DictionaryValueHelper.toInt(it[1])], mapping[DictionaryValueHelper.toInt(it[2])], query)
                        counter++
                        if (counter % 10000 == 0L) {
                            println("imported $counter triples for index $orderName")
                        }
                    }
                    println("imported $counter triples for index $orderName")
                    cache.close()
                }
            }
            val totalTime = DateHelperRelative.elapsedSeconds(startTime)
            val storeTime = totalTime - dictTime
            println("imported file $fileName,$counter,$totalTime,$dictTime,$storeTime")
            instance.tripleStoreManager!!.commit(query)
            if (instance.LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
                instance.communicationHandler!!.sendData(instance.LUPOS_PROCESS_URLS[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
            }
            return "successfully imported $counter Triples"
        } catch (e: Throwable) {
            e.printStackTrace()
            if (instance.LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
                instance.communicationHandler!!.sendData(instance.LUPOS_PROCESS_URLS[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
            }
            throw e
        }
/*Coverage Unreachable*/
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

    public fun evaluateOperatorgraphToVisual(instance: Luposdate3000Instance, node: IOPBase, output: OPVisualGraph): Int {
        val id = output.maxID++
        node.setVisualUUID(id.toLong())
        var label = node.getClassname() + " " + node.getUUID()
        label += when (node) {
            is AOPVariable -> "\n?" + node.getName()
            is AOPConstant -> "\n" + node.toSparql()
            else -> "\n" + node.getProvidedVariableNames()
        }
        output.nodes.add(OPVisualNode(id, label))
        for (c in node.getChildren()) {
            val childId = evaluateOperatorgraphToVisual(instance, c, output)
            output.edges.add(OPVisualEdge(id, childId, 1))
        }
        return id
    }

    @JsName("evaluate_operatorgraph_to_result")
    /*suspend*/ public fun evaluateOperatorgraphToResultB(instance: Luposdate3000Instance, node: IOPBase, output: IMyOutputStream): Any {
        return evaluateOperatorgraphToResultInternal(instance, node, output, EQueryResultToStreamExt.DEFAULT_STREAM, -1)
    }

    @JsName("evaluate_operatorgraph_to_result_a")
    /*suspend*/ public fun evaluateOperatorgraphToResultA(instance: Luposdate3000Instance, node: IOPBase, output: IMyOutputStream, evaluator: EQueryResultToStream): Any {
        return evaluateOperatorgraphToResultInternal(instance, node, output, evaluator, -1)
    }

    @JsName("evaluate_operatorgraph_to_result_c")
    /*suspend*/ public fun evaluateOperatorgraphToResultC(instance: Luposdate3000Instance, node: IOPBase, output: IMyOutputStream, evaluator: EQueryResultToStream, timeoutInMs: Long): Any {
        return evaluateOperatorgraphToResultInternal(instance, node, output, evaluator, timeoutInMs)
    }

    /*suspend*/ private inline fun evaluateOperatorgraphToResultInternal(instance: Luposdate3000Instance, node: IOPBase, output: IMyOutputStream, evaluator: EQueryResultToStream, timeoutInMs: Long): Any {
        val evaluatorInstance = ResultFormatManager[EQueryResultToStreamExt.names[evaluator]]
        if (evaluatorInstance == null) {
            TODO(EQueryResultToStreamExt.names[evaluator])
        }
        val res = evaluatorInstance(node, output, timeoutInMs)
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
        evaluateOperatorgraphToResultB(instance, node, buf)
        return buf.toString()
    }

    @JsName("evaluate_sparql_to_result_a")
    /*suspend*/ public fun evaluateSparqlToResultA(instance: Luposdate3000Instance, query: String, output: IMyOutputStream) {
        evaluateSparqlToResultD(instance, query, output, false)
    }

    @JsName("evaluate_sparql_to_result_d")
    /*suspend*/ public fun evaluateSparqlToResultD(instance: Luposdate3000Instance, query: String, output: IMyOutputStream, logOperatorGraph: Boolean) {
        val node = evaluateSparqlToOperatorgraphB(instance, query, logOperatorGraph)
        evaluateOperatorgraphToResultB(instance, node, output)
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
        evaluateOperatorgraphToResultB(instance, popNode, buf)
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
        val l = mutableListOf<Luposdate3000Instance>()
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
        return instance
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
