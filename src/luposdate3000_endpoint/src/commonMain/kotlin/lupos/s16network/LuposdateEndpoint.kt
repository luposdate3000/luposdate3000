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
package lupos.s16network

import lupos.s00misc.DateHelperRelative
import lupos.s00misc.EModifyTypeExt
import lupos.s00misc.ETripleComponentType
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.File
import lupos.s00misc.IMyPrintWriter
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.MyStringStream
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.Partition
import lupos.s00misc.QueryResultToStream
import lupos.s00misc.SanityCheck
import lupos.s00misc.UnreachableException
import lupos.s00misc.XMLElement
import lupos.s00misc.XMLElementFromCsv
import lupos.s00misc.XMLElementFromJson
import lupos.s00misc.XMLElementFromN3
import lupos.s00misc.XMLElementFromTsv
import lupos.s00misc.XMLElementFromXML
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.Turtle2Parser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithStringTriples
import lupos.s02buildSyntaxTree.turtle.TurtleScanner
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPValuesImportXML
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.EQueryResultToStream
import lupos.s11outputResult.EQueryResultToStreamExt
import lupos.s11outputResult.QueryResultToEmptyStream
import lupos.s11outputResult.QueryResultToEmptyWithDictionaryStream
import lupos.s11outputResult.QueryResultToMemoryTable
import lupos.s11outputResult.QueryResultToXMLElement
import lupos.s11outputResult.QueryResultToXMLStream
import lupos.s14endpoint.convertToOPBase
import lupos.s15tripleStoreDistributed.distributedTripleStore
import kotlin.js.JsName

/*
 * This is the interface of the database
 * Do not overload any function here - because that would yield bad function names in the exported headers.
 * Do not use default parameters - for the same reason - mangled function names in the interface are bad
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
            res = nodeGlobalDictionary.createValue(v2)
        }
        return res
/*Coverage Unreachable*/
    }

    @JsName("import_turtle_files_old")
    /*suspend*/ public fun importTurtleFilesOld(fileNames: String, bnodeDict: MutableMap<String, Int>): String {
        try {
            val query = Query()
            var counter = 0
            val store = distributedTripleStore.getDefaultGraph(query)
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
                                store.modify(arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos)), EModifyTypeExt.INSERT)
                                bufPos = 0
                            }
                            bufS[bufPos] = helperImportRaw(bnodeDict, s)
                            bufP[bufPos] = helperImportRaw(bnodeDict, p)
                            bufO[bufPos] = helperImportRaw(bnodeDict, o)
                            bufPos++
                        }
                    }
                    x.ltit = ltit
                    x.turtleDoc()
                    if (bufPos > 0) {
                        store.modify(arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos)), EModifyTypeExt.INSERT)
                        bufPos = 0
                    }
                } catch (e: lupos.s02buildSyntaxTree.ParseError) {
                    println("error in file '$fileName'")
                    throw e
                }
            }
            distributedTripleStore.commit(query)
            return "successfully imported $counter Triples"
        } catch (e: Throwable) {
            SanityCheck.println { "TODO exception 15" }
            e.printStackTrace()
            throw e
        }
/*Coverage Unreachable*/
    }

    @JsName("import_turtle_files")
    /*suspend*/ public fun importTurtleFiles(fileNames: String, bnodeDict: MutableMap<String, Int>): String {
        try {
            val query = Query()
            var counter = 0
            val store = distributedTripleStore.getDefaultGraph(query)
            val bufS = IntArray(1048576)
            val bufP = IntArray(1048576)
            val bufO = IntArray(1048576)
            var bufPos = 0
            for (fileName in fileNames.split(";")) {
                println("importing file '$fileName'")
                val f = File(fileName)
                val iter = f.readAsInputStream()
                try {
                    val x = object : Turtle2Parser(iter) {
                        override fun onTriple(triple: Array<String>, tripleType: Array<ETripleComponentType>) {
                            counter++
                            if (bufPos == bufS.size) {
                                store.modify(arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos)), EModifyTypeExt.INSERT)
                                bufPos = 0
                            }
                            bufS[bufPos] = helperImportRaw(bnodeDict, triple[0])
                            bufP[bufPos] = helperImportRaw(bnodeDict, triple[1])
                            bufO[bufPos] = helperImportRaw(bnodeDict, triple[2])
                            bufPos++
                        }
                    }
                    x.turtleDoc()
                    if (bufPos > 0) {
                        store.modify(arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos)), EModifyTypeExt.INSERT)
                        bufPos = 0
                    }
                } catch (e: Exception) {
                    println("fast_parser :: error in file '$fileName'")
                    e.printStackTrace()
                    throw e
                }
            }
            distributedTripleStore.commit(query)
            return "successfully imported $counter Triples"
        } catch (e: Throwable) {
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
        try {
            val query = Query()
            var counter = 0
            val store = distributedTripleStore.getDefaultGraph(query)
            val bufS = IntArray(1048576)
            val bufP = IntArray(1048576)
            val bufO = IntArray(1048576)
            var bufPos = 0
            val iter = MyStringStream(data)
            try {
                val x = object : Turtle2Parser(iter) {
                    override fun onTriple(triple: Array<String>, tripleType: Array<ETripleComponentType>) {
                        counter++
                        if (bufPos == bufS.size) {
                            store.modify(arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos)), EModifyTypeExt.INSERT)
                            bufPos = 0
                        }
                        bufS[bufPos] = helperImportRaw(bnodeDict, triple[0])
                        bufP[bufPos] = helperImportRaw(bnodeDict, triple[1])
                        bufO[bufPos] = helperImportRaw(bnodeDict, triple[2])
                        bufPos++
                    }
                }
                x.turtleDoc()
                if (bufPos > 0) {
                    store.modify(arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos)), EModifyTypeExt.INSERT)
                    bufPos = 0
                }
            } catch (e: Exception) {
                println("fast_parser :: error in turtle-string")
                e.printStackTrace()
                throw e
            }
            distributedTripleStore.commit(query)
            return "successfully imported $counter Triples"
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    @JsName("import_intermediate_files")
    /*suspend*/ public fun importIntermediateFiles(fileNames: String): String {
        return importIntermediateFiles(fileNames, false)
    }

    public fun setEstimatedPartitionsFromFile(filename: String) {
        Partition.estimatedPartitions0.clear()
        Partition.estimatedPartitions1.clear()
        Partition.estimatedPartitions2.clear()
        Partition.estimatedPartitionsValid = true
        val filePartitions = File("$filename")
        try {
            filePartitions.forEachLine {
                val t = it.split(",")
                if (t[1] == "-1") {
                    Partition.estimatedPartitions0.add(t[0])
                } else if (t[1] == "1") {
                    var t2 = Partition.estimatedPartitions1[t[0]]
                    if (t2 == null) {
                        t2 = mutableSetOf()
                        Partition.estimatedPartitions1[t[0]] = t2
                    }
                    if (t[2].toInt() > 1) {
                        t2.add(t[2].toInt())
                        if (t[2].toInt() > Partition.default_k) {
                            Partition.default_k = t[2].toInt()
                        }
                    }
                } else if (t[1] == "2") {
                    var t2 = Partition.estimatedPartitions2[t[0]]
                    if (t2 == null) {
                        t2 = mutableSetOf()
                        Partition.estimatedPartitions2[t[0]] = t2
                    }
                    if (t[2].toInt() > 0) {
                        t2.add(t[2].toInt())
                        if (t[2].toInt() > Partition.default_k) {
                            Partition.default_k = t[2].toInt()
                        }
                    }
                }
            }
            distributedTripleStore.reloadPartitioningScheme()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JsName("import_intermediate_files_a")
    /*suspend*/ public fun importIntermediateFiles(fileNames: String, convert_to_bnodes: Boolean): String {
        try {
            Partition.estimatedPartitions0.clear()
            Partition.estimatedPartitions1.clear()
            Partition.estimatedPartitions2.clear()
            Partition.estimatedPartitionsValid = true
            val query = Query()
            var counter = 0L
            val store = distributedTripleStore.getDefaultGraph(query)
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
                }
                val fileTriples = File("$fileName.triples")
                val fileDictionaryStat = File("$fileName.stat")
                var dictTotal = 0
                val dictTyped = IntArray(ETripleComponentTypeExt.values_size)
                dictTyped[ETripleComponentTypeExt.BLANK_NODE] = 0
                fileDictionaryStat.forEachLine {
                    val p = it.split("=")
                    if (p[0] == "total") {
                        dictTotal = p[1].toInt()
                    } else {
                        if (convert_to_bnodes) {
                            dictTyped[ETripleComponentTypeExt.BLANK_NODE] = dictTyped[ETripleComponentTypeExt.BLANK_NODE] + p[1].toInt()
                        } else {
                            dictTyped[ETripleComponentTypeExt.names.indexOf(p[0])] = p[1].toInt()
                        }
                    }
                }
                nodeGlobalDictionary.prepareBulk(dictTotal, dictTyped)
                val mapping = IntArray(dictTotal)
                nodeGlobalDictionary.importFromDictionaryFile("$fileName.dictionary", mapping)
                val dictTime = DateHelperRelative.elapsedSeconds(startTime)
                val cnt = fileTriples.length() / 12L
                counter += cnt
                fileTriples.dataInputStreamSuspended {
                    for (i in 0 until cnt) {
                        val s = it.readInt()
                        val p = it.readInt()
                        val o = it.readInt()
                        if (bufPos == bufS.size) {
                            store.modify(arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos)), EModifyTypeExt.INSERT)
                            bufPos = 0
                        }
                        bufS[bufPos] = mapping[s]
                        bufP[bufPos] = mapping[p]
                        bufO[bufPos] = mapping[o]
                        bufPos++
                    }
                }
                if (bufPos > 0) {
                    store.modify(arrayOf(ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos), ColumnIteratorMultiValue(bufS, bufPos)), EModifyTypeExt.INSERT)
                    bufPos = 0
                }
                val totalTime = DateHelperRelative.elapsedSeconds(startTime)
                val storeTime = totalTime - dictTime
                println("imported file $fileName,$cnt,$totalTime,$dictTime,$storeTime")
            }
            distributedTripleStore.commit(query)
            return "successfully imported $counter Triples"
        } catch (e: Throwable) {
            SanityCheck.println { "TODO exception 15" }
            e.printStackTrace()
            throw e
        }
/*Coverage Unreachable*/
    }

    @JsName("import_xml_data")
    /*suspend*/ public fun importXmlData(data: String): String {
        val query = Query()
        val import2 = POPValuesImportXML(query, listOf("s", "p", "o"), XMLElementFromXML()(data)!!)
        val import = import2.evaluateRoot()
        val dataLocal = arrayOf(import.columns["s"]!!, import.columns["p"]!!, import.columns["o"]!!)
        distributedTripleStore.getDefaultGraph(query).modify(dataLocal, EModifyTypeExt.INSERT)
        distributedTripleStore.commit(query)
        query.commited = true
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
    /*suspend*/ public fun evaluateOperatorgraphToResult(node: IOPBase, output: IMyPrintWriter) {
        evaluateOperatorgraphToResultA(node, output, EQueryResultToStreamExt.DEFAULT_STREAM)
    }

    @JsName("evaluate_operatorgraph_to_result_a")
    /*suspend*/ public fun evaluateOperatorgraphToResultA(node: IOPBase, output: IMyPrintWriter, evaluator: EQueryResultToStream): Any? {
        output.println("HTTP/1.1 200 OK")
        output.println("Content-Type: text/plain")
        output.println()
        var res: Any? = null
        res = when (evaluator) {
            EQueryResultToStreamExt.DEFAULT_STREAM -> QueryResultToStream(node, output)
            EQueryResultToStreamExt.XML_STREAM -> QueryResultToXMLStream(node, output)
            EQueryResultToStreamExt.EMPTY_STREAM -> QueryResultToEmptyStream(node, output)
            EQueryResultToStreamExt.EMPTYDICTIONARY_STREAM -> QueryResultToEmptyWithDictionaryStream(node, output)
            EQueryResultToStreamExt.MEMORY_TABLE -> QueryResultToMemoryTable(node)
            EQueryResultToStreamExt.XML_ELEMENT -> QueryResultToXMLElement.toXML(node)
            else -> throw UnreachableException()
        }
        distributedTripleStore.commit(node.getQuery())
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
    /*suspend*/ public fun evaluateSparqlToResultA(query: String, output: IMyPrintWriter) {
        evaluateSparqlToResultD(query, output, false)
    }

    @JsName("evaluate_sparql_to_result_d")
    /*suspend*/ public fun evaluateSparqlToResultD(query: String, output: IMyPrintWriter, logOperatorGraph: Boolean) {
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
        val popNode = XMLElement.convertToOPBase(q, XMLElementFromXML()(query)!!)
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

    @JsName("initialize")
    public fun initialize() {
        if (!initialized) {
            initialized = true
            tripleStoreManager = TripleStoreManagerImpl()
            XMLElement.parseFromAnyRegistered["n3"] = XMLElementFromN3()
            XMLElement.parseFromAnyRegistered["ttl"] = XMLElementFromN3()
            XMLElement.parseFromAnyRegistered["srx"] = XMLElementFromXML()
            XMLElement.parseFromAnyRegistered["srj"] = XMLElementFromJson()
            XMLElement.parseFromAnyRegistered["csv"] = XMLElementFromCsv()
            XMLElement.parseFromAnyRegistered["tsv"] = XMLElementFromTsv()
        }
    }

    init {
        initialize()
    }
}
