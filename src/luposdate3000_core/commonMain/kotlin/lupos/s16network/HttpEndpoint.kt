package lupos.s16network

import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.Coverage
import lupos.s00misc.DateHelper
import lupos.s00misc.DateHelperRelative
import lupos.s00misc.EBenchmark
import lupos.s00misc.EModifyType
import lupos.s00misc.ETripleComponentType
import lupos.s00misc.File
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyPrintWriter
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.Turtle2Parser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithStringTriples
import lupos.s02buildSyntaxTree.turtle.TurtleScanner
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPValuesImportXML
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToStream
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s14endpoint.convertToOPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore

/*this object transforms the text input to the response-body*/
@UseExperimental(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
object HttpEndpoint {
    fun helper_clean_string(s: String): String {
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

    fun helper_import_turtle_files(dict: MyMapStringIntPatriciaTrie, usePredefinedDict: Boolean, v: String): Value {
        try {
            val v2 = helper_clean_string(v)
            BenchmarkUtils.start(EBenchmark.IMPORT_DICT)
            var res: Value
            if (v2.startsWith("_:")) {
                res = dict.getOrCreate(v2, {
//                    SanityCheck.check { !usePredefinedDict }
                    /*return*/ nodeGlobalDictionary.createNewBNode()
                })
            } else {
                res = nodeGlobalDictionary.createValue(v2)
            }
            return res
        } finally {
            BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_DICT)
        }
/*Coverage Unreachable*/
    }

    suspend fun cleanup_turtle_files_old(fileNames: String, bnodeDict: MyMapStringIntPatriciaTrie): String {
        var res = StringBuilder()
        try {
            for (fileName in fileNames.split(";")) {
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
                        suspend override fun consume_triple(s: String, p: String, o: String) {
//                            res.append("$s $p $o .\n")
                        }
                    }
                    x.ltit = ltit
                    x.turtleDoc()
                } catch (e: lupos.s02buildSyntaxTree.ParseError) {
                    println("error in file '$fileName'")
                    throw e
                }
            }
        } catch (e: Throwable) {
            SanityCheck.println({ "TODO exception 15" })
            e.printStackTrace()
            throw e
        }
        return res.toString()
    }

    suspend fun import_turtle_files_old(fileNames: String, bnodeDict: MyMapStringIntPatriciaTrie): String {
        try {
            val usePredefinedDict = bnodeDict.size > 0
            val query = Query()
            var counter = 0
            var store = DistributedTripleStore.getDefaultGraph(query)
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
                            suspend override fun consume_triple(s: String, p: String, o: String) {
                                counter++
                                bulk.insert(helper_import_turtle_files(bnodeDict, usePredefinedDict, s), helper_import_turtle_files(bnodeDict, usePredefinedDict, p), helper_import_turtle_files(bnodeDict, usePredefinedDict, o))
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

    suspend fun cleanup_turtle_files(fileNames: String, bnodeDict: MyMapStringIntPatriciaTrie): String {
        var res = StringBuilder()
        try {
            for (fileName in fileNames.split(";")) {
                val f = File(fileName)
                val iter = f.readAsInputStream()
                try {
                    val x = object : Turtle2Parser(iter) {
                        override fun onTriple(triple: Array<String>, tripleType: Array<ETripleComponentType>) {
//                            res.append("${triple[0]} ${triple[1]} ${triple[2]} .\n")
                        }
                    }
                    x.turtleDoc()
                } catch (e: Exception) {
                    println("fast_parser :: error in file '$fileName'")
                    e.printStackTrace()
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return res.toString()
    }

    suspend fun import_turtle_files(fileNames: String, bnodeDict: MyMapStringIntPatriciaTrie): String {
        try {
            val usePredefinedDict = bnodeDict.size > 0
            val query = Query()
            var counter = 0
            var store = DistributedTripleStore.getDefaultGraph(query)
            store.bulkImport { bulk ->
                for (fileName in fileNames.split(";")) {
                    println("importing file '$fileName'")
                    val f = File(fileName)
                    val iter = f.readAsInputStream()
                    try {
                        val x = object : Turtle2Parser(iter) {
                            override fun onTriple(triple: Array<String>, tripleType: Array<ETripleComponentType>) {
                                counter++
                                bulk.insert(helper_import_turtle_files(bnodeDict, usePredefinedDict, triple[0]), helper_import_turtle_files(bnodeDict, usePredefinedDict, triple[1]), helper_import_turtle_files(bnodeDict, usePredefinedDict, triple[2]))
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

    suspend fun import_intermediate_files(fileNames: String): String {
        try {
            val query = Query()
            var counter = 0L
            var store = DistributedTripleStore.getDefaultGraph(query)
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

    suspend fun import_xml_data(data: String): String {
        val query = Query()
        val import = POPValuesImportXML(query, listOf("s", "p", "o"), XMLElement.parseFromXml(data)!!).evaluate(Partition())
        val dataLocal = arrayOf(import.columns["s"]!!, import.columns["p"]!!, import.columns["o"]!!)
        DistributedTripleStore.getDefaultGraph(query).modify(dataLocal, EModifyType.INSERT)
DistributedTripleStore.commit(query)
query.commited=true
        return XMLElement("success").toString()
    }

    suspend fun evaluate_sparql_query_string_part1(query: String, logOperatorGraph: Boolean = false): OPBase {
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
        SanityCheck.println { "----------Distributed Operator Graph" }
        val pop_distributed_node = KeyDistributionOptimizer(q).optimizeCall(pop_node)
//println("timer #405 ${DateHelperRelative.elapsedSeconds(timer)}")
//        timer = DateHelperRelative.markNow()
        SanityCheck.println { pop_distributed_node }
        if (logOperatorGraph) {
            println("----------")
            println(query)
            println(">>>>>>>>>>")
            println(pop_distributed_node.toXMLElement().toString())
            println("<<<<<<<<<<")
            println(OperatorGraphToLatex(pop_distributed_node.toXMLElement().toString(), ""))
        }
//println("timer #406 ${DateHelperRelative.elapsedSeconds(timer)}")
        return pop_distributed_node
    }

    suspend fun evaluate_sparql_query_string_part2(node: OPBase, output: MyPrintWriter) {
//var timer = DateHelperRelative.markNow()
        output.println("HTTP/1.1 200 OK")
        output.println("Content-Type: text/plain")
        output.println();
        node.query.reset()
        QueryResultToStream(node, output)
DistributedTripleStore.commit(node.query)
node.query.commited=true
//println("timer #407 ${DateHelperRelative.elapsedSeconds(timer)}")
    }

    suspend fun evaluate_sparql_query_string(query: String, logOperatorGraph: Boolean = false): String {
        val node = evaluate_sparql_query_string_part1(query, logOperatorGraph)
        val buf = MyPrintWriter()
        evaluate_sparql_query_string_part2(node, buf)
        return buf.toString()
    }

    suspend fun evaluate_sparql_query_string(query: String, output: MyPrintWriter, logOperatorGraph: Boolean = false) {
//var timer = DateHelperRelative.markNow()
        val node = evaluate_sparql_query_string_part1(query, logOperatorGraph)
        evaluate_sparql_query_string_part2(node, output)
//println("timer #408 ${DateHelperRelative.elapsedSeconds(timer)}")
    }

    suspend fun evaluate_sparql_query_operator_xml(query: String, logOperatorGraph: Boolean = false): String {
        val q = Query()
        val pop_node = XMLElement.convertToOPBase(q, XMLElement.parseFromXml(query)!!)
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
        val res = QueryResultToStream(pop_node, buf)
DistributedTripleStore.commit(q)
q.commited=true
        return buf.toString()
    }
}
