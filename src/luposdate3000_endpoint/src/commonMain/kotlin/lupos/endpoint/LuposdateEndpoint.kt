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

import lupos.buffermanager.BufferManagerExt
import lupos.dictionary.DictionaryFactory
import lupos.dictionary.DictionaryHelper
import lupos.dictionary.nodeGlobalDictionary
import lupos.operator.factory.XMLElementToOPBase
import lupos.optimizer.ast.OperatorGraphVisitor
import lupos.optimizer.distributed.query.DistributedOptimizerQuery
import lupos.optimizer.logical.LogicalOptimizer
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.DateHelperRelative
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EModifyTypeExt
import lupos.s00misc.EPartitionModeExt
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.File
import lupos.s00misc.IMyOutputStream
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.MyStringStream
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.Platform
import lupos.s00misc.QueryResultToStream
import lupos.s00misc.SanityCheck
import lupos.s00misc.UnreachableException
import lupos.s00misc.XMLElement
import lupos.s00misc.XMLElementFromCsv
import lupos.s00misc.XMLElementFromJson
import lupos.s00misc.XMLElementFromN3
import lupos.s00misc.XMLElementFromTsv
import lupos.s00misc.XMLElementFromXML
import lupos.s00misc.communicationHandler
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.ParseError
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.Turtle2Parser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithStringTriples
import lupos.s02buildSyntaxTree.turtle.TurtleScanner
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s05tripleStore.TripleStoreManager
import lupos.s05tripleStore.TripleStoreManagerImpl
import lupos.s05tripleStore.tripleStoreManager
import lupos.s09physicalOperators.noinput.POPValuesImportXML
import lupos.s11outputResult.EQueryResultToStream
import lupos.s11outputResult.EQueryResultToStreamExt
import lupos.s11outputResult.QueryResultToEmptyStream
import lupos.s11outputResult.QueryResultToEmptyWithDictionaryStream
import lupos.s11outputResult.QueryResultToMemoryTable
import lupos.s11outputResult.QueryResultToTurtleStream
import lupos.s11outputResult.QueryResultToXMLElement
import lupos.s11outputResult.QueryResultToXMLStream
import lupos.shared.optimizer.distributedOptimizerQueryFactory
import kotlin.js.JsName

/*
 * This is the _interface_ of the database
 * Do not overload any function here - because that would yield bad function names in the exported headers.
 * Do not use default parameters - for the same reason - mangled function names in the _interface_ are bad
 */
@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
public object LuposdateEndpoint {
    private var initialized = false
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
            DictionaryHelper.valueToByteArray(buffer, v2)
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
            val bufS = IntArray(1048576)
            val bufP = IntArray(1048576)
            val bufO = IntArray(1048576)
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
                    val x = object : TurtleParserWithStringTriples() {
                        /*suspend*/ override fun consume_triple(s: String, p: String, o: String) {
                            counter++
                            if (bufPos == bufS.size) {
                                store.modify(query, arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufP, bufPos), ColumnIteratorMultiValue(bufO, bufPos)), EModifyTypeExt.INSERT)
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
                    if (bufPos > 0) {
                        store.modify(query, arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufP, bufPos), ColumnIteratorMultiValue(bufO, bufPos)), EModifyTypeExt.INSERT)
                        bufPos = 0
                    }
                } catch (e: lupos.s02buildSyntaxTree.ParseError) {
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
            val bufS = IntArray(1048576)
            val bufP = IntArray(1048576)
            val bufO = IntArray(1048576)
            var bufPos = 0
            for (fileName in fileNames.split(";")) {
                println("importing file '$fileName'")
                val f = File(fileName)
                val iter = f.openInputStream()
                try {
                    val x = object : Turtle2Parser(iter) {
                        override fun onTriple() {
                            counter++
                            if (bufPos == bufS.size) {
                                store.modify(query, arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufP, bufPos), ColumnIteratorMultiValue(bufO, bufPos)), EModifyTypeExt.INSERT)
                                bufPos = 0
                            }
                            bufS[bufPos] = helperImportRaw(bnodeDict, triple[0])
                            bufP[bufPos] = helperImportRaw(bnodeDict, triple[1])
                            bufO[bufPos] = helperImportRaw(bnodeDict, triple[2])
                            bufPos++
                        }
                    }
                    x.parse()
                    if (bufPos > 0) {
                        store.modify(query, arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufP, bufPos), ColumnIteratorMultiValue(bufO, bufPos)), EModifyTypeExt.INSERT)
                        bufPos = 0
                    }
                } catch (e: Exception) {
                    println("fast_parser :: error in file '$fileName'")
                    e.printStackTrace()
                    throw e
                }
            }
            tripleStoreManager.commit(query)
            if (tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
            }
            return "successfully imported $counter Triples"
        } catch (e: Throwable) {
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
            val bufS = IntArray(1048576)
            val bufP = IntArray(1048576)
            val bufO = IntArray(1048576)
            var bufPos = 0
            val iter = MyStringStream(data)
            try {
                val x = object : Turtle2Parser(iter) {
                    override fun onTriple() {
                        counter++
                        if (bufPos == bufS.size) {
                            store.modify(query, arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufP, bufPos), ColumnIteratorMultiValue(bufO, bufPos)), EModifyTypeExt.INSERT)
                            bufPos = 0
                        }
                        bufS[bufPos] = helperImportRaw(bnodeDict, triple[0])
                        bufP[bufPos] = helperImportRaw(bnodeDict, triple[1])
                        bufO[bufPos] = helperImportRaw(bnodeDict, triple[2])
                        bufPos++
                    }
                }
                x.parse()
                if (bufPos > 0) {
                    store.modify(query, arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufP, bufPos), ColumnIteratorMultiValue(bufO, bufPos)), EModifyTypeExt.INSERT)
                    bufPos = 0
                }
            } catch (e: Exception) {
                println("fast_parser :: error in turtle-string")
                e.printStackTrace()
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
            val bufS = IntArray(1048576)
            val bufP = IntArray(1048576)
            val bufO = IntArray(1048576)
            var bufPos = 0
            val fileNamesS = fileNames.split(";")
            for (fileName in fileNamesS) {
                println("importing intermediate file '$fileName'")
                val startTime = DateHelperRelative.markNow()
                if (fileNamesS.size == 1) {
                    setEstimatedPartitionsFromFile("$fileName.partitions")
                    tripleStoreManager.resetGraph(query, TripleStoreManager.DEFAULT_GRAPH_NAME)
                }
                val fileTriples = File("$fileName.triples")
                var dictTotal = 0
                val mapping = nodeGlobalDictionary.importFromDictionaryFile("$fileName")
                val dictTime = DateHelperRelative.elapsedSeconds(startTime)
                val cnt = fileTriples.length() / 12L
                counter += cnt
                fileTriples.withInputStream {
                    for (i in 0 until cnt) {
                        val s = it.readInt()
                        val p = it.readInt()
                        val o = it.readInt()
                        if (bufPos == bufS.size) {
                            store.modify(query, arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufP, bufPos), ColumnIteratorMultiValue(bufO, bufPos)), EModifyTypeExt.INSERT)
                            bufPos = 0
                        }
                        bufS[bufPos] = mapping[s]
                        bufP[bufPos] = mapping[p]
                        bufO[bufPos] = mapping[o]
                        bufPos++
                    }
                }
                if (bufPos > 0) {
                    store.modify(query, arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufP, bufPos), ColumnIteratorMultiValue(bufO, bufPos)), EModifyTypeExt.INSERT)
                    bufPos = 0
                }
                val totalTime = DateHelperRelative.elapsedSeconds(startTime)
                val storeTime = totalTime - dictTime
                println("imported file $fileName,$cnt,$totalTime,$dictTime,$storeTime")
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
        tripleStoreManager.getDefaultGraph().modify(query, dataLocal, EModifyTypeExt.INSERT)
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
        if (initialized) {
            initialized = false
            nodeGlobalDictionary.close()
            tripleStoreManager.close()
            BufferManagerExt.close()
        }
    }

    @JsName("initialize")
    public fun initialize() {
        if (!initialized) {
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

    init {
        initialize()
    }
}
