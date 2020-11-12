package lupos.s16network

import kotlin.js.JsName
import lupos.s00misc.DateHelperRelative
import lupos.s00misc.EModifyType
import lupos.s00misc.ETripleComponentType
import lupos.s00misc.File
import lupos.s00misc.IMyPrintWriter
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s00misc.QueryResultToStream
import lupos.s00misc.SanityCheck
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
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPValuesImportXML
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s14endpoint.convertToOPBase
import lupos.s15tripleStoreDistributed.distributedTripleStore
import lupos.s15tripleStoreDistributed.DistributedTripleStore

/*
 * This is the interface of the database
 * Dont overload any function here - because that would yield bad function names in the exported headers.
 * Dont use default parameters - for the same reason - mangled function names in the interface are bad
 */
@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
object LuposdateEndpoint {
    var initialized = false
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

    internal fun helper_import_turtle_files(dict: MutableMap<String, Int>, v: String): Int {
        val v2 = helper_clean_string(v)
        var res: Int
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
    suspend fun import_turtle_files_old(fileNames: String, bnodeDict: MutableMap<String, Int>): String {
        try {
            val query = Query()
            var counter = 0
            var store = distributedTripleStore.getDefaultGraph(query)
            store.bulkImport { bulk ->
                for (fileName in fileNames.split(";")) {
                    println("importing file '$fileName'")
                    val f = File(fileName)
                    val lcit: LexerCharIterator
                    if (f.length() < Int.MAX_VALUE) {
                        val data = f.readAsString()
                        lcit = LexerCharIterator(data)
                    } else {
                        val data = f.readAsCharIterator()
                        lcit = LexerCharIterator(data)
                    }
                    val tit = TurtleScanner(lcit)
                    val ltit = LookAheadTokenIterator(tit, 3)
                    try {
                        val x = object : TurtleParserWithStringTriples() {
                            override suspend fun consume_triple(s: String, p: String, o: String) {
                                counter++
                                bulk.insert(helper_import_turtle_files(bnodeDict, s), helper_import_turtle_files(bnodeDict, p), helper_import_turtle_files(bnodeDict, o))
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
            SanityCheck.println({ "TODO exception 15" })
            e.printStackTrace()
            throw e
        }
/*Coverage Unreachable*/
    }

    @JsName("import_turtle_files")
    suspend fun import_turtle_files(fileNames: String, bnodeDict: MutableMap<String, Int>): String {
        try {
            val query = Query()
            var counter = 0
            var store = distributedTripleStore.getDefaultGraph(query)
            store.bulkImport { bulk ->
                for (fileName in fileNames.split(";")) {
                    println("importing file '$fileName'")
                    val f = File(fileName)
                    val iter = f.readAsInputStream()
                    try {
                        val x = object : Turtle2Parser(iter) {
                            override fun onTriple(triple: Array<String>, tripleType: Array<ETripleComponentType>) {
                                counter++
                                bulk.insert(helper_import_turtle_files(bnodeDict, triple[0]), helper_import_turtle_files(bnodeDict, triple[1]), helper_import_turtle_files(bnodeDict, triple[2]))
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
            return import_turtle_files_old(fileNames, bnodeDict)
        }
/*Coverage Unreachable*/
    }

    @JsName("import_intermediate_files")
    suspend fun import_intermediate_files(fileNames: String): String {
        try {
            val query = Query()
            var counter = 0L
            var store = distributedTripleStore.getDefaultGraph(query)
            store.bulkImport { bulk ->
                for (fileName in fileNames.split(";")) {
                    println("importing file '$fileName'")
                    val startTime = DateHelperRelative.markNow()
                    val fileTriples = File(fileName + ".triples")
                    val fileDictionary = File(fileName + ".dictionary")
                    val fileDictionaryOffset = File(fileName + ".dictionaryoffset")
                    val fileDictionaryStat = File(fileName + ".stat")
                    var dictTotal = 0
                    var dictTyped = IntArray(ETripleComponentType.values().size)
                    fileDictionaryStat.forEachLine {
                        val p = it.split("=")
                        if (p[0] == "total") {
                            dictTotal = p[1].toInt()
                        } else {
                            dictTyped[ETripleComponentType.valueOf(p[0]).ordinal] = p[1].toInt()
                        }
                    }
                    nodeGlobalDictionary.prepareBulk(dictTotal, dictTyped)
                    val mapping = IntArray(dictTotal)
                    var mappingIdx = 0
                    var buffer = ByteArray(0)
                    fileDictionaryOffset.dataInputStream { offsetStream ->
                        fileDictionary.dataInputStream { dictStream ->
                            var lastOffset = 0
                            for (i in 0 until dictTotal) {
                                val nextOffset = offsetStream.readInt()
                                var type = ETripleComponentType.values()[dictStream.readByte().toInt()]
                                var length = nextOffset - lastOffset - 1
                                if (buffer.size < length) {
                                    buffer = ByteArray(length)
                                }
                                val read = dictStream.read(buffer, 0, length)
                                if (read < length) {
                                    throw Exception("invalid read")
                                }
                                val s = buffer.decodeToString(0, length)
                                mapping[mappingIdx++] = nodeGlobalDictionary.createByType(s, type)
                                lastOffset = nextOffset
                            }
                        }
                    }
                    val dictTime = DateHelperRelative.elapsedSeconds(startTime)
                    var cnt = fileTriples.length() / 12L
                    counter += cnt
                    fileTriples.dataInputStreamSuspended {
                        for (i in 0 until cnt) {
                            var s = it.readInt()
                            var p = it.readInt()
                            var o = it.readInt()
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
            SanityCheck.println({ "TODO exception 15" })
            e.printStackTrace()
            throw e
        }
/*Coverage Unreachable*/
    }

    @JsName("import_xml_data")
    suspend fun import_xml_data(data: String): String {
        val query = Query()
        val import = POPValuesImportXML(query, listOf("s", "p", "o"), XMLElementFromXML()(data)!!).evaluate(Partition())
        val dataLocal = arrayOf(import.columns["s"]!!, import.columns["p"]!!, import.columns["o"]!!)
        distributedTripleStore.getDefaultGraph(query).modify(dataLocal, EModifyType.INSERT)
        distributedTripleStore.commit(query)
        query.commited = true
        return XMLElement("success").toString()
    }

    @JsName("evaluate_sparql_to_operatorgraph_a")
    suspend fun evaluate_sparql_to_operatorgraph_a(query: String): IOPBase {
        return evaluate_sparql_to_operatorgraph_b(query, false)
    }

    @JsName("evaluate_sparql_to_operatorgraph_b")
    suspend fun evaluate_sparql_to_operatorgraph_b(query: String, logOperatorGraph: Boolean): IOPBase {
        val q = Query()
//        var timer = DateHelperRelative.markNow()
        SanityCheck.println { "----------String Query" }
        SanityCheck.println { query }
        SanityCheck.println { "----------Abstract Syntax Tree" }
        val lcit = LexerCharIterator(query)
        val tit = TokenIteratorSPARQLParser(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        val parser = SPARQLParser(ltit)
        val ast_node = parser.expr()
//println("timer #401 ${DateHelperRelative.elapsedSeconds(timer)}")
//        timer = DateHelperRelative.markNow()
        SanityCheck.println { ast_node }
        SanityCheck.println { "----------Logical Operator Graph" }
        val lop_node = ast_node.visit(OperatorGraphVisitor(q))
//println("timer #402 ${DateHelperRelative.elapsedSeconds(timer)}")
//        timer = DateHelperRelative.markNow()
        SanityCheck.println { lop_node }
        SanityCheck.println { "----------Logical Operator Graph optimized" }
        val lop_node2 = LogicalOptimizer(q).optimizeCall(lop_node)
//println("timer #403 ${DateHelperRelative.elapsedSeconds(timer)}")
//        timer = DateHelperRelative.markNow()
        SanityCheck.println { lop_node2 }
        SanityCheck.println { "----------Physical Operator Graph" }
        val pop_optimizer = PhysicalOptimizer(q)
        val pop_node = pop_optimizer.optimizeCall(lop_node2)
//println("timer #404 ${DateHelperRelative.elapsedSeconds(timer)}")
//        timer = DateHelperRelative.markNow()
        SanityCheck.println { pop_node }
        if (logOperatorGraph) {
            println("----------")
            println(query)
            println(">>>>>>>>>>")
            println(pop_node.toXMLElement().toString())
            println("<<<<<<<<<<")
            println(OperatorGraphToLatex(pop_node.toXMLElement().toString(), ""))
        }
//println("timer #406 ${DateHelperRelative.elapsedSeconds(timer)}")
        return pop_node
    }

    @JsName("evaluate_operatorgraph_to_result")
    suspend fun evaluate_operatorgraph_to_result(node: IOPBase, output: IMyPrintWriter) {
//var timer = DateHelperRelative.markNow()
        output.println("HTTP/1.1 200 OK")
        output.println("Content-Type: text/plain")
        output.println()
        node.getQuery().reset()
        QueryResultToStream(node, output)
        distributedTripleStore.commit(node.getQuery())
        node.getQuery().setCommited()
//println("timer #407 ${DateHelperRelative.elapsedSeconds(timer)}")
    }

    @JsName("evaluate_sparql_to_result_b")
    suspend fun evaluate_sparql_to_result_b(query: String): String {
        return evaluate_sparql_to_result_c(query, false)
    }

    @JsName("evaluate_sparql_to_result_c")
    suspend fun evaluate_sparql_to_result_c(query: String, logOperatorGraph: Boolean): String {
        val node = evaluate_sparql_to_operatorgraph_b(query, logOperatorGraph)
        val buf = MyPrintWriter()
        evaluate_operatorgraph_to_result(node, buf)
        return buf.toString()
    }

    @JsName("evaluate_sparql_to_result_a")
    suspend fun evaluate_sparql_to_result_a(query: String, output: IMyPrintWriter) {
        evaluate_sparql_to_result_d(query, output, false)
    }

    @JsName("evaluate_sparql_to_result_d")
    suspend fun evaluate_sparql_to_result_d(query: String, output: IMyPrintWriter, logOperatorGraph: Boolean) {
//var timer = DateHelperRelative.markNow()
        val node = evaluate_sparql_to_operatorgraph_b(query, logOperatorGraph)
        evaluate_operatorgraph_to_result(node, output)
//println("timer #408 ${DateHelperRelative.elapsedSeconds(timer)}")
    }

    @JsName("evaluate_operatorgraphXML_to_result_a")
    suspend fun evaluate_operatorgraphXML_to_result_a(query: String): String {
        return evaluate_operatorgraphXML_to_result_b(query, false)
    }

    @JsName("evaluate_operatorgraphXML_to_result_b")
    suspend fun evaluate_operatorgraphXML_to_result_b(query: String, logOperatorGraph: Boolean): String {
        val q = Query()
        val pop_node = XMLElement.convertToOPBase(q, XMLElementFromXML()(query)!!)
        SanityCheck.println { pop_node }
        if (logOperatorGraph) {
            SanityCheck.suspended {
                SanityCheck.println({ "----------" })
                SanityCheck.println({ query })
                SanityCheck.println({ ">>>>>>>>>>" })
                val a = pop_node.toXMLElement().toString()
                SanityCheck.println({ a })
                SanityCheck.println({ "<<<<<<<<<<" })
                val b = OperatorGraphToLatex(pop_node.toXMLElement().toString(), "")
                SanityCheck.println({ b })
            }
        }
        var buf = MyPrintWriter()
        evaluate_operatorgraph_to_result(pop_node, buf)
        return buf.toString()
    }

    @JsName("initialize")
    fun initialize() {
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
