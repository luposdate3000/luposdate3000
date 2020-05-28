package lupos.s16network
import kotlin.jvm.JvmField
import kotlin.time.DurationUnit
import kotlin.time.TimeMark
import kotlin.time.TimeSource.Monotonic
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.File
import lupos.s00misc.GlobalLogger
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MemoryStatistics
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyListGeneric
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.MySetInt
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.rdf.*
import lupos.s02buildSyntaxTree.rdf.ID_Triple
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.*
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithDictionary
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPValuesImportBase
import lupos.s09physicalOperators.noinput.POPValuesImportXML
import lupos.s09physicalOperators.POPBase
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToString
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s14endpoint.convertToOPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore
/*this object transforms the text input to the response-body*/
object HttpEndpoint {
    fun helper_clean_string(s: String): String {
Coverage.funStart(13074)
        var res: String = s
Coverage.statementStart(13075)
        while (true) {
Coverage.whileLoopStart(13076)
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
Coverage.statementStart(13077)
            if (match == null) {
Coverage.ifStart(13078)
                break
            }
Coverage.statementStart(13079)
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
Coverage.statementStart(13080)
            res = res.replace(match.value, replacement)
Coverage.statementStart(13081)
        }
Coverage.statementStart(13082)
        return res
    }
    fun helper_import_turtle_files(dict: MyMapStringIntPatriciaTrie, usePredefinedDict: Boolean, v: String): Value {
Coverage.funStart(13083)
        try {
Coverage.statementStart(13084)
            val v2 = helper_clean_string(v)
Coverage.statementStart(13085)
            BenchmarkUtils.start(EBenchmark.IMPORT_DICT)
Coverage.statementStart(13086)
            if (v2.startsWith("_:")) {
Coverage.ifStart(13087)
                return dict.getOrCreate(v2, {
Coverage.statementStart(13088)
                    SanityCheck.check { !usePredefinedDict }
Coverage.statementStart(13089)
/*return*/ nodeGlobalDictionary.createNewBNode()
                })
Coverage.statementStart(13090)
            } else {
Coverage.ifStart(13091)
                return nodeGlobalDictionary.createValue(v2)
            }
Coverage.statementStart(13092)
        } finally {
Coverage.statementStart(13093)
            BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_DICT)
Coverage.statementStart(13094)
        }
Coverage.statementStart(13095)
    }
    suspend fun import_turtle_files(fileNames: String, bnodeDict: MyMapStringIntPatriciaTrie): String {
Coverage.funStart(13096)
        try {
Coverage.statementStart(13097)
            val usePredefinedDict = bnodeDict.size > 0
Coverage.statementStart(13098)
            val query = Query()
Coverage.statementStart(13099)
            var bulk = TripleStoreBulkImport()
Coverage.statementStart(13100)
            var counter = 0
Coverage.statementStart(13101)
            var store = DistributedTripleStore.getDefaultGraph(query)
Coverage.statementStart(13102)
            for (fileName in fileNames.split(";")) {
Coverage.forLoopStart(13103)
                val data = File(fileName).readAsString()
Coverage.statementStart(13104)
                val lcit = LexerCharIterator(data)
Coverage.statementStart(13105)
                val tit = TurtleScanner(lcit)
Coverage.statementStart(13106)
                val ltit = LookAheadTokenIterator(tit, 3)
Coverage.statementStart(13107)
                TurtleParserWithStringTriples({ s, p, o ->
Coverage.statementStart(13108)
                    counter++
Coverage.statementStart(13109)
                    bulk.insert(                            helper_import_turtle_files(bnodeDict, usePredefinedDict, s),                            helper_import_turtle_files(bnodeDict, usePredefinedDict, p),                            helper_import_turtle_files(bnodeDict, usePredefinedDict, o))
Coverage.statementStart(13110)
                    if (bulk.full()) {
Coverage.ifStart(13111)
                        CoroutinesHelper.runBlock {
Coverage.statementStart(13112)
                            bulk.sort()
Coverage.statementStart(13113)
                            for (idx in TripleStoreLocalBase.distinctIndices) {
Coverage.forLoopStart(13114)
                                store.bulkImport(bulk, idx)
Coverage.statementStart(13115)
                            }
Coverage.statementStart(13116)
                            bulk.reset()
Coverage.statementStart(13117)
                        }
Coverage.statementStart(13118)
                    }
Coverage.statementStart(13119)
                }, ltit).turtleDoc()
Coverage.statementStart(13120)
            }
Coverage.statementStart(13121)
            bulk.sort()
Coverage.statementStart(13122)
            for (idx in TripleStoreLocalBase.distinctIndices) {
Coverage.forLoopStart(13123)
                store.bulkImport(bulk, idx)
Coverage.statementStart(13124)
            }
Coverage.statementStart(13125)
            return "successfully imported $counter Triples"
        } catch (e: Throwable) {
Coverage.statementStart(13126)
            e.printStackTrace()
Coverage.statementStart(13127)
            throw e
        }
Coverage.statementStart(13128)
    }
    suspend fun import_xml_data(data: String): String {
Coverage.funStart(13129)
        val query = Query()
Coverage.statementStart(13130)
        val import = POPValuesImportXML(query, listOf("s", "p", "o"), XMLElement.parseFromXml(data)!!).evaluate()
Coverage.statementStart(13131)
        val dataLocal = arrayOf(import.columns["s"]!!, import.columns["p"]!!, import.columns["o"]!!)
Coverage.statementStart(13132)
        DistributedTripleStore.getDefaultGraph(query).modify(dataLocal, EModifyType.INSERT)
Coverage.statementStart(13133)
        query.commit()
Coverage.statementStart(13134)
        return XMLElement("success").toString()
    }
    suspend fun evaluate_sparql_query_string(query: String, logOperatorGraph: Boolean = false): String {
Coverage.funStart(13135)
        val q = Query()
Coverage.statementStart(13136)
        GlobalLogger.log(ELoggerType.DEBUG, { "----------String Query" })
Coverage.statementStart(13137)
        GlobalLogger.log(ELoggerType.DEBUG, { query })
Coverage.statementStart(13138)
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Abstract Syntax Tree" })
Coverage.statementStart(13139)
        val lcit = LexerCharIterator(query)
Coverage.statementStart(13140)
        val tit = TokenIteratorSPARQLParser(lcit)
Coverage.statementStart(13141)
        val ltit = LookAheadTokenIterator(tit, 3)
Coverage.statementStart(13142)
        val parser = SPARQLParser(ltit)
Coverage.statementStart(13143)
        val ast_node = parser.expr()
Coverage.statementStart(13144)
        GlobalLogger.log(ELoggerType.DEBUG, { ast_node })
Coverage.statementStart(13145)
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Logical Operator Graph" })
Coverage.statementStart(13146)
        val lop_node = ast_node.visit(OperatorGraphVisitor(q))
Coverage.statementStart(13147)
        GlobalLogger.log(ELoggerType.DEBUG, { lop_node })
Coverage.statementStart(13148)
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Logical Operator Graph optimized" })
Coverage.statementStart(13149)
        val lop_node2 = LogicalOptimizer(q).optimizeCall(lop_node)
Coverage.statementStart(13150)
        GlobalLogger.log(ELoggerType.DEBUG, { lop_node2 })
Coverage.statementStart(13151)
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Physical Operator Graph" })
Coverage.statementStart(13152)
        val pop_optimizer = PhysicalOptimizer(q)
Coverage.statementStart(13153)
        val pop_node = pop_optimizer.optimizeCall(lop_node2)
Coverage.statementStart(13154)
        GlobalLogger.log(ELoggerType.DEBUG, { pop_node })
Coverage.statementStart(13155)
        GlobalLogger.log(ELoggerType.DEBUG, { "----------Distributed Operator Graph" })
Coverage.statementStart(13156)
        val pop_distributed_node = KeyDistributionOptimizer(q).optimizeCall(pop_node)
Coverage.statementStart(13157)
        GlobalLogger.log(ELoggerType.DEBUG, { pop_distributed_node })
Coverage.statementStart(13158)
        if (logOperatorGraph) {
Coverage.ifStart(13159)
            println("----------")
Coverage.statementStart(13160)
            println(query)
Coverage.statementStart(13161)
            println(">>>>>>>>>>")
Coverage.statementStart(13162)
            println(pop_distributed_node.toXMLElement().toString())
Coverage.statementStart(13163)
            println("<<<<<<<<<<")
Coverage.statementStart(13164)
            println(OperatorGraphToLatex(pop_distributed_node.toXMLElement().toString(), ""))
Coverage.statementStart(13165)
        }
Coverage.statementStart(13166)
        val res = QueryResultToString(pop_distributed_node)
Coverage.statementStart(13167)
        q.commit()
Coverage.statementStart(13168)
        return res
    }
    suspend fun evaluate_sparql_query_operator_xml(query: String, logOperatorGraph: Boolean = false): String {
Coverage.funStart(13169)
        val q = Query()
Coverage.statementStart(13170)
        val pop_node = XMLElement.convertToOPBase(q, XMLElement.parseFromXml(query)!!)
Coverage.statementStart(13171)
        GlobalLogger.log(ELoggerType.DEBUG, { pop_node })
Coverage.statementStart(13172)
        if (logOperatorGraph) {
Coverage.ifStart(13173)
            println("----------")
Coverage.statementStart(13174)
            println(query)
Coverage.statementStart(13175)
            println(">>>>>>>>>>")
Coverage.statementStart(13176)
            println(pop_node.toXMLElement().toString())
Coverage.statementStart(13177)
            println("<<<<<<<<<<")
Coverage.statementStart(13178)
            println(OperatorGraphToLatex(pop_node.toXMLElement().toString(), ""))
Coverage.statementStart(13179)
        }
Coverage.statementStart(13180)
        val res = QueryResultToString(pop_node)
Coverage.statementStart(13181)
        q.commit()
Coverage.statementStart(13182)
        return res
    }
    fun persistence_store(foldername: String): String {
Coverage.funStart(13183)
        File(foldername).deleteRecursively()
Coverage.statementStart(13184)
        File(foldername).mkdirs()
Coverage.statementStart(13185)
        BenchmarkUtils.start(EBenchmark.SAVE_DICTIONARY)
Coverage.statementStart(13186)
        nodeGlobalDictionary.safeToFolder(foldername)
Coverage.statementStart(13187)
        var timeDict = BenchmarkUtils.elapsedSeconds(EBenchmark.SAVE_DICTIONARY)
Coverage.statementStart(13188)
        BenchmarkUtils.start(EBenchmark.SAVE_TRIPLE_STORE)
Coverage.statementStart(13189)
        val stores = DistributedTripleStore.localStore.stores
Coverage.statementStart(13190)
        var idx = 0
Coverage.statementStart(13191)
        File(foldername + "/stores.txt").printWriter { out ->
Coverage.statementStart(13192)
            stores.keys.forEach { name ->
Coverage.statementStart(13193)
                val store = stores[name]!!
Coverage.statementStart(13194)
                File(foldername + "/$idx").mkdirs()
Coverage.statementStart(13195)
                store.safeToFolder(foldername + "/$idx")
Coverage.statementStart(13196)
                idx++
Coverage.statementStart(13197)
            }
Coverage.statementStart(13198)
        }
Coverage.statementStart(13199)
        var timeStore = BenchmarkUtils.elapsedSeconds(EBenchmark.SAVE_TRIPLE_STORE)
Coverage.statementStart(13200)
        return "success $timeDict $timeStore"
    }
    fun persistence_load(foldername: String): String {
Coverage.funStart(13201)
        MemoryStatistics.print("before load")
Coverage.statementStart(13202)
        BenchmarkUtils.start(EBenchmark.LOAD_DICTIONARY)
Coverage.statementStart(13203)
        nodeGlobalDictionary.loadFromFolder(foldername)
Coverage.statementStart(13204)
        var timeDict = BenchmarkUtils.elapsedSeconds(EBenchmark.LOAD_DICTIONARY)
Coverage.statementStart(13205)
        MemoryStatistics.print("after load dictionary")
Coverage.statementStart(13206)
        BenchmarkUtils.start(EBenchmark.LOAD_TRIPLE_STORE)
Coverage.statementStart(13207)
        val stores = DistributedTripleStore.localStore.stores
Coverage.statementStart(13208)
        var idx = 0
Coverage.statementStart(13209)
        File(foldername + "/stores.txt").forEachLine { name ->
Coverage.statementStart(13210)
            val store = stores[name]!!
Coverage.statementStart(13211)
            store.loadFromFolder(foldername + "/$idx")
Coverage.statementStart(13212)
            idx++
Coverage.statementStart(13213)
        }
Coverage.statementStart(13214)
        var timeStore = BenchmarkUtils.elapsedSeconds(EBenchmark.LOAD_TRIPLE_STORE)
Coverage.statementStart(13215)
        MemoryStatistics.print("after load triplestore")
Coverage.statementStart(13216)
        return "success $timeDict $timeStore"
    }
}
