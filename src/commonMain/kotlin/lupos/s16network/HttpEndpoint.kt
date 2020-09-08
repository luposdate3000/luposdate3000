package lupos.s16network

import java.io.PrintWriter
import java.io.StringWriter
import kotlin.time.DurationUnit
import kotlinx.coroutines.runBlocking
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.Coverage
import lupos.s00misc.DateHelper
import lupos.s00misc.EBenchmark
import lupos.s00misc.EModifyType
import lupos.s00misc.File
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
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
                    SanityCheck.check { !usePredefinedDict }
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

    suspend fun import_intermediate_files(fileNames: String): String {
        try {
            val query = Query()
            var counter = 0L
            var store = DistributedTripleStore.getDefaultGraph(query)
            store.bulkImport { bulk ->
                for (fileName in fileNames.split(";")) {
                    println("importing file '$fileName'")
                    val startTime = DateHelper.markNow()
                    val fileTriples = File(fileName + ".triples")
                    val fileDictionary = File(fileName + ".dictionary")
                    val fileDictionaryStat = File(fileName + ".stat")
                    val size = fileDictionaryStat.readAsString().toInt()
                    val mapping = IntArray(size)
                    var idx = 0
                    fileDictionary.forEachLine {
                        val v = helper_clean_string(it)
                        try {
                            mapping[idx++] = nodeGlobalDictionary.createValue(v)
                        } catch (e: Throwable) {
                            println("dictionary $idx $it $v")
                            throw e
                        }
                        if (idx % 100000 == 0) {
                            println("dictionary $idx / $size")
                        }
                    }
                    val dictTime = DateHelper.elapsedSeconds(startTime)
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
                    val totalTime = DateHelper.elapsedSeconds(startTime)
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
        query.commit()
        return XMLElement("success").toString()
    }

    suspend fun evaluate_sparql_query_string_part1(query: String, logOperatorGraph: Boolean = false): OPBase {
        val q = Query()
        var timer = DateHelper.markNow()
        SanityCheck.println { "----------String Query" }
        SanityCheck.println { query }
        SanityCheck.println { "----------Abstract Syntax Tree" }
        val lcit = LexerCharIterator(query)
        val tit = TokenIteratorSPARQLParser(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        val parser = SPARQLParser(ltit)
        val ast_node = parser.expr()
println("timer #401 ${DateHelper.elapsedSeconds(timer)}")
        timer = DateHelper.markNow()
        SanityCheck.println { ast_node }
        SanityCheck.println { "----------Logical Operator Graph" }
        val lop_node = ast_node.visit(OperatorGraphVisitor(q))
println("timer #402 ${DateHelper.elapsedSeconds(timer)}")
        timer = DateHelper.markNow()
        SanityCheck.println { lop_node }
        SanityCheck.println { "----------Logical Operator Graph optimized" }
        val lop_node2 = LogicalOptimizer(q).optimizeCall(lop_node)
println("timer #403 ${DateHelper.elapsedSeconds(timer)}")
        timer = DateHelper.markNow()
        SanityCheck.println { lop_node2 }
        SanityCheck.println { "----------Physical Operator Graph" }
        val pop_optimizer = PhysicalOptimizer(q)
        val pop_node = pop_optimizer.optimizeCall(lop_node2)
println("timer #404 ${DateHelper.elapsedSeconds(timer)}")
        timer = DateHelper.markNow()
        SanityCheck.println { pop_node }
        SanityCheck.println { "----------Distributed Operator Graph" }
        val pop_distributed_node = KeyDistributionOptimizer(q).optimizeCall(pop_node)
println("timer #405 ${DateHelper.elapsedSeconds(timer)}")
        timer = DateHelper.markNow()
        SanityCheck.println { pop_distributed_node }
        if (logOperatorGraph) {
            println("----------")
            println(query)
            println(">>>>>>>>>>")
            println(pop_distributed_node.toXMLElement().toString())
            println("<<<<<<<<<<")
            println(OperatorGraphToLatex(pop_distributed_node.toXMLElement().toString(), ""))
        }
println("timer #406 ${DateHelper.elapsedSeconds(timer)}")
        return pop_distributed_node
    }

    suspend fun evaluate_sparql_query_string_part2(node: OPBase, output: PrintWriter) {
var timer = DateHelper.markNow()
        output.println("HTTP/1.1 200 OK")
        output.println("Content-Type: text/plain")
        output.println();
        node.query.reset()
        QueryResultToStream(node, output)
        node.query.commit()
println("timer #407 ${DateHelper.elapsedSeconds(timer)}")
    }

    suspend fun evaluate_sparql_query_string(query: String, logOperatorGraph: Boolean = false): String {
        val node = evaluate_sparql_query_string_part1(query, logOperatorGraph)
        var buf = StringWriter()
        evaluate_sparql_query_string_part2(node, PrintWriter(buf))
        return buf.toString()
    }

    suspend fun evaluate_sparql_query_string(query: String, output: PrintWriter, logOperatorGraph: Boolean = false) {
var timer = DateHelper.markNow()
        val node = evaluate_sparql_query_string_part1(query, logOperatorGraph)
        evaluate_sparql_query_string_part2(node, output)
println("timer #408 ${DateHelper.elapsedSeconds(timer)}")
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
        var buf = StringWriter()
        val res = QueryResultToStream(pop_node, PrintWriter(buf))
        q.commit()
        return buf.toString()
    }
}
