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
import lupos.optimizer.logical.LogicalOptimizer
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.parser.InputToIntermediate
import lupos.parser.sparql.ASTSparqlDoc
import lupos.parser.sparql.SparqlParser
import lupos.parser.turtle.TurtleParser
import lupos.result_format.EQueryResultToStream
import lupos.result_format.EQueryResultToStreamExt
import lupos.result_format.ResultFormatManager
import lupos.shared.DateHelperRelative
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EIndexPatternExt
import lupos.shared.EIndexPatternHelper
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
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.File
import lupos.shared.inline.FileExt
import lupos.shared.inline.MyPrintWriter
import lupos.shared.inline.MyStringStream
import lupos.shared.inline.Platform
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.inline.fileformat.DictionaryIntermediate
import lupos.shared.inline.fileformat.TriplesIntermediateReader
import lupos.shared.myPrintStackTrace
import lupos.shared.myPrintStackTraceAndThrowAgain
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundleRoot
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
    public val instances: MutableList<Luposdate3000Instance> = mutableListOf<Luposdate3000Instance>()

    @JsName("load_shacl_ontology")
    /*suspend*/ public fun loadShaclOntology(instance: Luposdate3000Instance, data: String): String {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt:86"/*SOURCE_FILE_END*/ }, { instance.LUPOS_PROCESS_ID == 0 })
        val dict = instance.nodeGlobalDictionary!!
        val cache2 = instance.nodeGlobalOntologyCache
        val cache = if (cache2 == null) {
            val c = DictionaryCache(0)
            instance.nodeGlobalOntologyCache = c
            c
        } else {
            cache2
        }
        val buffer = ByteArrayWrapper()
        val dataStream = MyStringStream(data)
        val parserObject = TurtleParser(dataStream)
        fun dHelper(b: ByteArrayWrapper): DictionaryValueType {
            return if (DictionaryHelper.byteArrayToType(b) != ETripleComponentTypeExt.BLANK_NODE) {
                dict.createValue(b)
            } else {
                DictionaryValueHelper.booleanTrueValue
            }
        }
        parserObject.consumeTriple = { s, p, o ->
            DictionaryHelper.sparqlToByteArray(buffer, s)
            var id = dHelper(buffer)
            cache.insertValuePairExtend(buffer, id)

            DictionaryHelper.sparqlToByteArray(buffer, p)
            id = dHelper(buffer)
            cache.insertValuePairExtend(buffer, id)

            DictionaryHelper.sparqlToByteArray(buffer, o)
            id = dHelper(buffer)
            cache.insertValuePairExtend(buffer, id)
        }
        try {
            parserObject.parserDefinedParse()
        } catch (e: Throwable) {
            println(data)
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt:123"/*SOURCE_FILE_END*/)
        }
        var data2 = ByteArrayWrapper()
        cache.forEach { value, key ->
            var c1 = ByteArrayWrapperExt.getSize(data2)
            val c2 = c1 + DictionaryValueHelper.getSize() + 4
            ByteArrayWrapperExt.setSize(data2, c2, true)
            DictionaryValueHelper.toByteArray(data2, c1, key)
            c1 += DictionaryValueHelper.getSize()
            ByteArrayWrapperExt.writeInt4(data2, c1, ByteArrayWrapperExt.getSize(value))
            ByteArrayWrapperExt.appendTo(value, data2)
        }
        val c3 = ByteArrayWrapperExt.getSize(data2)
        ByteArrayWrapperExt.setSize(data2, c3 + DictionaryValueHelper.getSize(), true)
        DictionaryValueHelper.toByteArray(data2, c3, DictionaryValueHelper.nullValue)
        for (i in 1 until instance.LUPOS_PROCESS_URLS_ALL.size) {
            val (input, output) = instance.communicationHandler!!.openConnection(instance.LUPOS_PROCESS_URLS_ALL[i], "/shacl/ontology/load", mapOf(), -1)
            output.write(ByteArrayWrapperExt.getBuf(data2), ByteArrayWrapperExt.getSize(data2))
            output.close()
            input.close()
        }
        instance.tripleStoreManager!!.getDefaultGraph() // initialize the default graph
        return "successfully loaded ontology"
    }

    @JsName("import_turtle_string")
    /*suspend*/ public fun importTurtleString(instance: Luposdate3000Instance, data: String): String {
        return importTurtleString(instance, data, TripleStoreManager.DEFAULT_GRAPH_NAME)
    }

    @JsName("import_turtle_string_b")
    /*suspend*/ public fun importTurtleString(instance: Luposdate3000Instance, data: String, graphName: String): String {
        val dir = FileExt.createTempDirectory()
        val fileName = dir + "data.n3"
        File(fileName).withOutputStream { out ->
            out.println(data)
        }
        val res = importTripleFileB(instance, fileName, graphName)
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
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt:182"/*SOURCE_FILE_END*/)
                }
            }
        }
    }

    @JsName("import_triple_file")
    /*suspend*/ public fun importTripleFile(instance: Luposdate3000Instance, fileName: String): String {
        return importTripleFileB(instance, fileName, TripleStoreManager.DEFAULT_GRAPH_NAME)
    }

    @JsName("import_triple_file_b")
    /*suspend*/ public fun importTripleFileB(instance: Luposdate3000Instance, fileName: String, graphName: String): String {
        if (!DictionaryIntermediate.fileExists(fileName)) {
            InputToIntermediate.process(fileName, instance)
        }
        return importIntermediateFile(instance, fileName, graphName)
    }

    @JsName("import_triple_file_c")
    /*suspend*/ public fun importTripleFileC(instance: Luposdate3000Instance, fileName: String, fileType: String, graphName: String): String {
        if (!DictionaryIntermediate.fileExists(fileName)) {
            InputToIntermediate.process(fileName, fileType, instance)
        }
        return importIntermediateFile(instance, fileName, graphName)
    }

    /*suspend*/ private fun importIntermediateFile(instance: Luposdate3000Instance, fileName: String): String {
        return importIntermediateFile(instance, fileName, TripleStoreManager.DEFAULT_GRAPH_NAME)
    }

    /*suspend*/ private fun importIntermediateFile(instance: Luposdate3000Instance, fileName: String, graphName: String): String {
        val query = Query(instance)
        val key = "${query.getTransactionID()}"
        try {
            if (instance.LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
                instance.communicationHandler!!.sendData(instance.LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/register", mapOf("key" to key), query.getTransactionID().toInt())
                query.setDictionaryUrl("${instance.LUPOS_PROCESS_URLS_ALL[0]}/distributed/query/dictionary?key=$key")
            }
            instance.tripleStoreManager!!.resetDefaultTripleStoreLayout()
            var counter = 0L
            val startTime = DateHelperRelative.markNow()
            setEstimatedPartitionsFromFile(instance, "$fileName.partitions")
            if (instance.tripleStoreManager!!.getGraphNames().contains(graphName)) {
                instance.tripleStoreManager!!.dropGraph(query, graphName)
            }
            try {
                instance.tripleStoreManager!!.createGraph(query, graphName)
            } catch (e: Throwable) {
                e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt:231"/*SOURCE_FILE_END*/)
            }
            val store = instance.tripleStoreManager!!.getGraph(graphName)
            val (mapping, mappingLength) = instance.nodeGlobalDictionary!!.importFromDictionaryFile(fileName)
            val dictTime = DateHelperRelative.elapsedSeconds(startTime)
            var requireSorting = false
            for (i in 1 until mappingLength) {
                if (mapping[i] < mapping[i - 1]) {
                    requireSorting = true
                    break
                }
            }
            if (requireSorting) {
                val cache = store.modify_create_cache(query, EModifyTypeExt.INSERT, -1, false)
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
                    val orderName = orderNames[o]
                    val sortedBy = orderPatterns[o]
                    val cache = store.modify_create_cache(query, EModifyTypeExt.INSERT, sortedBy, true)
                    println("opening the file :: $fileName.$orderName")
                    val fileTriples = TriplesIntermediateReader("$fileName.$orderName")
                    var oldA = DictionaryValueHelper.NULL
                    var oldB = DictionaryValueHelper.NULL
                    var oldC = DictionaryValueHelper.NULL
                    var oldOriginalA = 0
                    var oldOriginalB = 0
                    var oldOriginalC = 0
                    fileTriples.readAll {
                        cache.writeRow(mapping[DictionaryValueHelper.toInt(it[0])], mapping[DictionaryValueHelper.toInt(it[1])], mapping[DictionaryValueHelper.toInt(it[2])], query)
                        SanityCheck(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt:281"/*SOURCE_FILE_END*/ },
                            {
                                val newOriginalA = DictionaryValueHelper.toInt(it[EIndexPatternHelper.tripleIndicees[sortedBy][0]])
                                val newOriginalB = DictionaryValueHelper.toInt(it[EIndexPatternHelper.tripleIndicees[sortedBy][1]])
                                val newOriginalC = DictionaryValueHelper.toInt(it[EIndexPatternHelper.tripleIndicees[sortedBy][2]])
                                val newA = mapping[newOriginalA]
                                val newB = mapping[newOriginalB]
                                val newC = mapping[newOriginalC]
                                SanityCheck.check(
                                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt:290"/*SOURCE_FILE_END*/ },
                                    { newA >= oldA },
                                    { "$oldA $oldB $oldC $newA $newB $newC .. ${newA >= oldA} ${newB >= oldB} ${newC > oldC} ${EIndexPatternHelper.tripleIndicees[sortedBy].map { it }} $oldOriginalA $oldOriginalB $oldOriginalC .. $newOriginalA $newOriginalB $newOriginalC ${EIndexPatternExt.names[sortedBy]} $orderName $fileName" }
                                )
                                SanityCheck.check(
                                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt:295"/*SOURCE_FILE_END*/ },
                                    { newB >= oldB || newA > oldA },
                                    { "$oldA $oldB $oldC $newA $newB $newC .. ${newA >= oldA} ${newB >= oldB} ${newC > oldC} ${EIndexPatternHelper.tripleIndicees[sortedBy].map { it }} $oldOriginalA $oldOriginalB $oldOriginalC .. $newOriginalA $newOriginalB $newOriginalC ${EIndexPatternExt.names[sortedBy]} $orderName $fileName" }
                                )
                                SanityCheck.check(
                                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt:300"/*SOURCE_FILE_END*/ },
                                    { newC > oldC || newA > oldA || newB > oldB },
                                    { "$oldA $oldB $oldC $newA $newB $newC .. ${newA >= oldA} ${newB >= oldB} ${newC > oldC} ${EIndexPatternHelper.tripleIndicees[sortedBy].map { it }} $oldOriginalA $oldOriginalB $oldOriginalC .. $newOriginalA $newOriginalB $newOriginalC ${EIndexPatternExt.names[sortedBy]} $orderName $fileName" }
                                )
                                oldA = newA
                                oldB = newB
                                oldC = newC
                                oldOriginalA = newOriginalA
                                oldOriginalB = newOriginalB
                                oldOriginalC = newOriginalC
                            }
                        )
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
                instance.communicationHandler!!.sendData(instance.LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
            }
            return "successfully imported $counter Triples"
        } catch (e: Throwable) {
            if (instance.LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
                instance.communicationHandler!!.sendData(instance.LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
            }
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt:333"/*SOURCE_FILE_END*/)
        }
/*Coverage Unreachable*/
    }

    @JsName("evaluate_sparql_to_iteratorbundle_a")
    /*suspend*/ public fun evaluateSparqlToIteratorBundleA(instance: Luposdate3000Instance, query: String): IteratorBundleRoot {
        return evaluateSparqlToOperatorgraphA(instance, query).evaluateRootBundle()
    }

    @JsName("evaluate_sparql_to_operatorgraph_a")
    /*suspend*/ public fun evaluateSparqlToOperatorgraphA(instance: Luposdate3000Instance, query: String): IOPBase {
        return evaluateSparqlToOperatorgraphA(instance, Query(instance), query)
    }

    @JsName("evaluate_sparql_to_operatorgraph_a")
    /*suspend*/ public fun evaluateSparqlToOperatorgraphA(instance: Luposdate3000Instance, q: Query, query: String): IOPBase {
        return evaluateSparqlToOperatorgraphB(instance, q, query, false)
    }

    @JsName("evaluate_sparql_to_operatorgraph_b")
    /*suspend*/ public fun evaluateSparqlToOperatorgraphB(instance: Luposdate3000Instance, query: String, logOperatorGraph: Boolean): IOPBase {
        return evaluateSparqlToOperatorgraphB(instance, Query(instance), query, logOperatorGraph)
    }

    @JsName("evaluate_sparql_to_operatorgraph_b")
    /*suspend*/ public fun evaluateSparqlToOperatorgraphB(instance: Luposdate3000Instance, q: Query, query: String, logOperatorGraph: Boolean): IOPBase {
        try {
println("evaluateSparqlToOperatorgraphB")
            SanityCheck.println { "----------String Query" }
            SanityCheck.println { query }
            SanityCheck.println { "----------Abstract Syntax Tree" }
            val stream = MyStringStream(query)
            val parser: SparqlParser = SparqlParser(stream)
            parser.parserDefinedParse()
            val astNode = parser.getResult() as ASTSparqlDoc
            parser.close()
            stream.close()
            SanityCheck.println { astNode }
            SanityCheck.println { "----------Logical Operator Graph" }
            val visitor = OperatorGraphVisitor(q)
            val lopNode: IOPBase = visitor.visit(astNode)
println()
println("----------Logical Operator Graph")
println(lopNode)
            SanityCheck.println { lopNode }
            SanityCheck.println { "----------Logical Operator Graph optimized" }
            val lopNode2 = LogicalOptimizer(q).optimizeCall(lopNode)
            SanityCheck.println { lopNode2 }
println()
println("----------Logical Operator Graph")
println(lopNode2)
            SanityCheck.println { "----------Physical Operator Graph" }
            val popOptimizer = PhysicalOptimizer(q)
            val popNode = popOptimizer.optimizeCall(lopNode2)
            SanityCheck.println { popNode }
println()
println("----------Physical Operator Graph")
println(popNode)
            if (logOperatorGraph) {
                println("----------")
                println(query)
                println(">>>>>>>>>>")
                println(popNode.toString())
                println("<<<<<<<<<<")
                println(OperatorGraphToLatex(popNode.toString(), ""))
            }
// println(query)
// println(popNode)
            return popNode
        } catch (e: Throwable) {
            println(query)
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt:405"/*SOURCE_FILE_END*/)
        }
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

    @JsName("evaluate_sparql_to_logical_operatorgraph_b")
    /*suspend*/ public fun evaluateSparqlToLogicalOperatorgraphB(instance: Luposdate3000Instance, query: String): IOPBase {
        val q = Query(instance)
        SanityCheck.println { "----------String Query" }
        SanityCheck.println { query }
        SanityCheck.println { "----------Abstract Syntax Tree" }
        val stream = MyStringStream(query)
        val parser: SparqlParser = SparqlParser(stream)
        parser.parserDefinedParse()
        val astNode = parser.getResult() as ASTSparqlDoc
        parser.close()
        stream.close()
        SanityCheck.println { astNode }
        SanityCheck.println { "----------Logical Operator Graph" }
        val visitor = OperatorGraphVisitor(q)
        val lopNode: IOPBase = visitor.visit(astNode)
        SanityCheck.println { lopNode }
        SanityCheck.println { "----------Logical Operator Graph optimized" }
        val lopNode2 = LogicalOptimizer(q).optimizeCall(lopNode)
        return lopNode2
    }

    @JsName("evaluate_operatorgraph_to_result")
    /*suspend*/ public fun evaluateOperatorgraphToResultB(instance: Luposdate3000Instance, node: IOPBase, output: IMyOutputStream): Any {
        return evaluateOperatorgraphToResultInternal(instance, node, output, EQueryResultToStreamExt.DEFAULT_STREAM, -1, true)
    }

    @JsName("evaluate_operatorgraph_to_result_a")
    /*suspend*/ public fun evaluateOperatorgraphToResultA(instance: Luposdate3000Instance, node: IOPBase, output: IMyOutputStream, evaluator: EQueryResultToStream): Any {
        return evaluateOperatorgraphToResultInternal(instance, node, output, evaluator, -1, true)
    }

    @JsName("evaluate_iterator_bundle_to_result_a")
    /*suspend*/ public fun evaluateIteratorBundleToResultA(instance: Luposdate3000Instance, node: IteratorBundleRoot, output: IMyOutputStream, evaluator: EQueryResultToStream): Any {
        return evaluateIteratorBundleToResultInternal(instance, node, output, evaluator, -1)
    }

    @JsName("evaluate_operatorgraph_to_result_e")
    /*suspend*/ public fun evaluateOperatorgraphToResultE(instance: Luposdate3000Instance, node: IOPBase, output: IMyOutputStream, evaluator: EQueryResultToStream, asRoot: Boolean): Any {
        return evaluateOperatorgraphToResultInternal(instance, node, output, evaluator, -1, asRoot)
    }

    @JsName("evaluate_iterator_bundle_to_result_e")
    /*suspend*/ public fun evaluateIteratorBundleToResultE(instance: Luposdate3000Instance, node: IteratorBundleRoot, output: IMyOutputStream, evaluator: EQueryResultToStream): Any {
        return evaluateIteratorBundleToResultInternal(instance, node, output, evaluator, -1)
    }

    @JsName("evaluate_operatorgraph_to_result_c")
    /*suspend*/ public fun evaluateOperatorgraphToResultC(instance: Luposdate3000Instance, node: IOPBase, output: IMyOutputStream, evaluator: EQueryResultToStream, timeoutInMs: Long): Any {
        return evaluateOperatorgraphToResultInternal(instance, node, output, evaluator, timeoutInMs, true)
    }

    @JsName("evaluate_operatorgraph_to_result_d")
    /*suspend*/ public fun evaluateOperatorgraphToResultD(instance: Luposdate3000Instance, node: IOPBase, output: IMyOutputStream, evaluator: EQueryResultToStream, timeoutInMs: Long, asRoot: Boolean): Any {
        return evaluateOperatorgraphToResultInternal(instance, node, output, evaluator, timeoutInMs, asRoot)
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ private inline fun evaluateOperatorgraphToResultInternal(instance: Luposdate3000Instance, node: IOPBase, output: IMyOutputStream, evaluator: EQueryResultToStream, timeoutInMs: Long, asRoot: Boolean): Any {
        try {
            val bundle = if (asRoot) {
                node.evaluateRootBundle()
            } else {
                node.evaluateBundle()
            }
            return evaluateIteratorBundleToResultInternal(
                instance,
                bundle,
                output,
                evaluator,
                timeoutInMs
            )
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt:499"/*SOURCE_FILE_END*/)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ private inline fun evaluateIteratorBundleToResultInternal(instance: Luposdate3000Instance, node: IteratorBundleRoot, output: IMyOutputStream, evaluator: EQueryResultToStream, timeoutInMs: Long): Any {
        val evaluatorInstance = ResultFormatManager[EQueryResultToStreamExt.names[evaluator]]
        if (evaluatorInstance == null) {
            TODO(EQueryResultToStreamExt.names[evaluator])
        }
        val res = evaluatorInstance(node, output, timeoutInMs)
        instance.tripleStoreManager!!.commit(node.query)
        node.query.setCommited()
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
            instance.tripleStoreManager = TripleStoreManagerImpl(instance)
            instance.tripleStoreManager!!.initialize()
            instance.initialized = true
        }
        return instance
    }

    init {
        MemoryTable.parseFromAnyRegistered["n3"] = MemoryTableFromN3()
        MemoryTable.parseFromAnyRegistered["ttl"] = MemoryTableFromN3()
        MemoryTable.parseFromAnyRegistered["nt"] = MemoryTableFromNTriple()
        MemoryTable.parseFromAnyRegistered["srx"] = MemoryTableFromXML()
        MemoryTable.parseFromAnyRegistered["srj"] = MemoryTableFromJson()
        MemoryTable.parseFromAnyRegistered["csv"] = MemoryTableFromCsv()
        MemoryTable.parseFromAnyRegistered["tsv"] = MemoryTableFromTsv()
        Platform.setShutdownHock {
            close()
        }
    }
}
