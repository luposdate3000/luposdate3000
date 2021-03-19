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
import kotlin.js.JsName
import lupos.s00misc.*
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
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPValuesImportXML
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.*
import lupos.s14endpoint.convertToOPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.s15tripleStoreDistributed.distributedTripleStore
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
            store.bulkImport { bulk ->
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
                                bulk.insert(helperImportRaw(bnodeDict, s), helperImportRaw(bnodeDict, p), helperImportRaw(bnodeDict, o))
                            }
                        }
                        x.ltit = ltit
                        x.turtleDoc()
                    } catch (e: lupos.s02buildSyntaxTree.ParseError) {
                        println("error in file '$fileName'")
                        throw e
                    }
                }
            }
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
            store.bulkImport { bulk ->
                for (fileName in fileNames.split(";")) {
                    println("importing file '$fileName'")
                    val f = File(fileName)
                    val iter = f.readAsInputStream()
                    try {
                        val x = object : Turtle2Parser(iter) {
                            override fun onTriple(triple: Array<String>, tripleType: Array<ETripleComponentType>) {
                                counter++
                                bulk.insert(helperImportRaw(bnodeDict, triple[0]), helperImportRaw(bnodeDict, triple[1]), helperImportRaw(bnodeDict, triple[2]))
                            }
                        }
                        x.turtleDoc()
                    } catch (e: Exception) {
                        println("fast_parser :: error in file '$fileName'")
                        e.printStackTrace()
                        throw e
                    }
                }
            }
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
            store.bulkImport { bulk ->
                val iter = MyStringStream(data)
                try {
                    val x = object : Turtle2Parser(iter) {
                        override fun onTriple(triple: Array<String>, tripleType: Array<ETripleComponentType>) {
                            counter++
                            bulk.insert(helperImportRaw(bnodeDict, triple[0]), helperImportRaw(bnodeDict, triple[1]), helperImportRaw(bnodeDict, triple[2]))
                        }
                    }
                    x.turtleDoc()
                } catch (e: Exception) {
                    println("fast_parser :: error in turtle-string")
                    e.printStackTrace()
                    throw e
                }
            }
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
    @JsName("import_intermediate_files_a")
    /*suspend*/ public fun importIntermediateFiles(fileNames: String, convert_to_bnodes: Boolean): String {
        try {
            Partition.estimatedPartitions1.clear()
            Partition.estimatedPartitions2.clear()
            Partition.estimatedPartitionsValid = true
            val query = Query()
            var counter = 0L
            val store = distributedTripleStore.getDefaultGraph(query)
            store.bulkImport { bulk ->
                val fileNamesS = fileNames.split(";")
                for (fileName in fileNamesS) {
                    println("importing intermediate file '$fileName'")
                    val startTime = DateHelperRelative.markNow()
                    if (fileNamesS.size == 1) {
                        val filePartitions = File("$fileName.partitions")
                        try {
                            filePartitions.forEachLine {
                                val t = it.split(",")
                                if (t[1] == "1") {
                                    var t2 = Partition.estimatedPartitions1[t[0]]
                                    if (t2 == null) {
                                        t2 = mutableSetOf()
                                        Partition.estimatedPartitions1[t[0]] = t2
                                    }
                                    if (t[2].toInt() > 1) {
                                        t2.add(t[2].toInt())
                                    }
                                }
                                if (t[1] == "2") {
                                    var t2 = Partition.estimatedPartitions2[t[0]]
                                    if (t2 == null) {
                                        t2 = mutableSetOf()
                                        Partition.estimatedPartitions2[t[0]] = t2
                                    }
                                    if (t[2].toInt() > 0) {
                                        t2.add(t[2].toInt())
                                    }
                                }
                            }
                            distributedTripleStore.reloadPartitioningScheme()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
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
                            bulk.insert(mapping[s], mapping[p], mapping[o])
                        }
                    }
                    val totalTime = DateHelperRelative.elapsedSeconds(startTime)
                    val storeTime = totalTime - dictTime
                    println("imported file $fileName,$cnt,$totalTime,$dictTime,$storeTime")
                }
            }
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
        val import = POPValuesImportXML(query, listOf("s", "p", "o"), XMLElementFromXML()(data)!!).evaluate(Partition())
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

    //Added the traverseNetwork call for changing the UUIDs for visualiziation
    @JsName("evaluate_sparql_to_operatorgraph_b")
    /*suspend*/ public fun evaluateSparqlToOperatorgraphB(query: String, logOperatorGraph: Boolean): IOPBase {
        val q = Query()
        //        var timer = DateHelperRelative.markNow()
        SanityCheck.println { "----------String Query" }
        SanityCheck.println { query }
        SanityCheck.println { "----------Abstract Syntax Tree" }
        val lcit = LexerCharIterator(query)
        val tit = TokenIteratorSPARQLParser(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        val parser = SPARQLParser(ltit)
        val astNode = parser.expr()
        // println("timer #401 ${DateHelperRelative.elapsedSeconds(timer)}")
        //        timer = DateHelperRelative.markNow()
        SanityCheck.println { astNode }
        SanityCheck.println { "----------Logical Operator Graph" }
        val lopNode = astNode.visit(OperatorGraphVisitor(q))
        // println("timer #402 ${DateHelperRelative.elapsedSeconds(timer)}")
        //        timer = DateHelperRelative.markNow()
        SanityCheck.println { lopNode }
        SanityCheck.println { "----------Logical Operator Graph optimized" }
        val lopNode2 = LogicalOptimizer(q).optimizeCall(lopNode)
        // println("timer #403 ${DateHelperRelative.elapsedSeconds(timer)}")
        //        timer = DateHelperRelative.markNow()
        SanityCheck.println { lopNode2 }
        SanityCheck.println { "----------Physical Operator Graph" }
        val popOptimizer = PhysicalOptimizer(q)
        val popNode = popOptimizer.optimizeCall(lopNode2)

        //Calling traverseNetwork function to change the UUIDs via DFS
        //TODO rausnehmen
        EndpointExtendedVisualize().traverseNetwork(popNode, mutableMapOf<IOPBase, Int>())

        // println("timer #404 ${DateHelperRelative.elapsedSeconds(timer)}")
        //        timer = DateHelperRelative.markNow()
        SanityCheck.println { popNode }
        if (logOperatorGraph) {
            println("----------")
            println(query)
            println(">>>>>>>>>>")
            println(popNode.toXMLElement().toString())
            println("<<<<<<<<<<")
            println(OperatorGraphToLatex(popNode.toXMLElement().toString(), ""))
        }
        // println("timer #406 ${DateHelperRelative.elapsedSeconds(timer)}")
        return popNode
    }
    @JsName("evaluate_operatorgraph_to_result")
    /*suspend*/ public fun evaluateOperatorgraphToResult(node: IOPBase, output: IMyPrintWriter) {
        evaluateOperatorgraphToResultA(node, output, EQueryResultToStreamExt.DEFAULT_STREAM)
    }

    //Changed by Rico:
    //Added the traverseNetwork call for changing the UUIDs for visualiziation
    @JsName("evaluate_operatorgraph_to_result_a")
    /*suspend*/ public fun evaluateOperatorgraphToResultA(node: IOPBase, output: IMyPrintWriter, evaluator: EQueryResultToStream): Any? {
    // var timer = DateHelperRelative.markNow()
        output.println("HTTP/1.1 200 OK")
        output.println("Content-Type: text/plain")
        output.println()
        node.getQuery().reset()
        //TODO Auslagern
        EndpointExtendedVisualize().traverseNetwork(node, mutableMapOf<IOPBase, Int>())
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
        // println("timer #407 ${DateHelperRelative.elapsedSeconds(timer)}")
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
    // var timer = DateHelperRelative.markNow()
        val node = evaluateSparqlToOperatorgraphB(query, logOperatorGraph)
        evaluateOperatorgraphToResult(node, output)
    // println("timer #408 ${DateHelperRelative.elapsedSeconds(timer)}")
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
                val a = popNode.toXMLElement().toString()
                SanityCheck.println { a }
                SanityCheck.println { "<<<<<<<<<<" }
                val b = OperatorGraphToLatex(popNode.toXMLElement().toString(), "")
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
            distributedTripleStore = DistributedTripleStore()
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
