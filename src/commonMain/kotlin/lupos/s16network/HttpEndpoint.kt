package lupos.s16network

import kotlin.time.DurationUnit
import kotlinx.coroutines.runBlocking
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.File
import lupos.s00misc.GlobalLogger
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
import lupos.s11outputResult.QueryResultToString
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
val x=                        object:TurtleParserWithStringTriples(){
suspend override fun consume_triple(s:String,p: String,o: String){ 
                            counter++
                                bulk.insert(helper_import_turtle_files(bnodeDict, usePredefinedDict, s), helper_import_turtle_files(bnodeDict, usePredefinedDict, p), helper_import_turtle_files(bnodeDict, usePredefinedDict, o))
               }
         }
x.ltit=ltit
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
                    val startTime = BenchmarkUtils.timesHelperMark()
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
                    val dictTime = startTime.elapsedNow().toDouble(DurationUnit.SECONDS)
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
                    val totalTime = startTime.elapsedNow().toDouble(DurationUnit.SECONDS)
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

    suspend fun evaluate_sparql_query_string(query: String, logOperatorGraph: Boolean = false): String {
        val q = Query()
        var timer = BenchmarkUtils.timesHelperMark()
        GlobalLogger.log(ELoggerType.DEBUG, { "----------String Query" })
        GlobalLogger.log(ELoggerType.DEBUG, { query })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Abstract Syntax Tree" })
        val lcit = LexerCharIterator(query)
        val tit = TokenIteratorSPARQLParser(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        val parser = SPARQLParser(ltit)
        val ast_node = parser.expr()
        BenchmarkUtils.timesHelperDuration(0, timer)
        timer = BenchmarkUtils.timesHelperMark()
        GlobalLogger.log(ELoggerType.DEBUG, { ast_node })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Logical Operator Graph" })
        val lop_node = ast_node.visit(OperatorGraphVisitor(q))
        BenchmarkUtils.timesHelperDuration(1, timer)
        timer = BenchmarkUtils.timesHelperMark()
        GlobalLogger.log(ELoggerType.DEBUG, { lop_node })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Logical Operator Graph optimized" })
        val lop_node2 = LogicalOptimizer(q).optimizeCall(lop_node)
        BenchmarkUtils.timesHelperDuration(2, timer)
        timer = BenchmarkUtils.timesHelperMark()
        GlobalLogger.log(ELoggerType.DEBUG, { lop_node2 })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Physical Operator Graph" })
        val pop_optimizer = PhysicalOptimizer(q)
        val pop_node = pop_optimizer.optimizeCall(lop_node2)
        BenchmarkUtils.timesHelperDuration(3, timer)
        timer = BenchmarkUtils.timesHelperMark()
        GlobalLogger.log(ELoggerType.DEBUG, { pop_node })
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Distributed Operator Graph" })
        val pop_distributed_node = KeyDistributionOptimizer(q).optimizeCall(pop_node)
        BenchmarkUtils.timesHelperDuration(4, timer)
        timer = BenchmarkUtils.timesHelperMark()
        GlobalLogger.log(ELoggerType.DEBUG, { pop_distributed_node })
        if (logOperatorGraph) {
            println("----------")
            println(query)
            println(">>>>>>>>>>")
            println(pop_distributed_node.toXMLElement().toString())
            println("<<<<<<<<<<")
            println(OperatorGraphToLatex(pop_distributed_node.toXMLElement().toString(), ""))
        }
        BenchmarkUtils.timesHelperDuration(5, timer)
        timer = BenchmarkUtils.timesHelperMark()
        val res = QueryResultToString(pop_distributed_node)
        BenchmarkUtils.timesHelperDuration(6, timer)
        timer = BenchmarkUtils.timesHelperMark()
        q.commit()
        BenchmarkUtils.timesHelperDuration(7, timer)
        return res
    }

    suspend fun evaluate_sparql_query_operator_xml(query: String, logOperatorGraph: Boolean = false): String {
        val q = Query()
        val pop_node = XMLElement.convertToOPBase(q, XMLElement.parseFromXml(query)!!)
        GlobalLogger.log(ELoggerType.DEBUG, { pop_node })
        if (logOperatorGraph) {
            SanityCheck.println({ "----------" })
            SanityCheck.println({ query })
            SanityCheck.println({ ">>>>>>>>>>" })
            SanityCheck.println({ pop_node.toXMLElement().toString() })
            SanityCheck.println({ "<<<<<<<<<<" })
            SanityCheck.println({ OperatorGraphToLatex(pop_node.toXMLElement().toString(), "") })
        }
        val res = QueryResultToString(pop_node)
        q.commit()
        return res
    }
}
