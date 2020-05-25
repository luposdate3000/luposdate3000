package lupos.s14endpoint

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
import lupos.s11outputResult.*
import lupos.s12p2p.P2P
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s14endpoint.Endpoint
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.s16network.*

@UseExperimental(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
abstract class EndpointServer(@JvmField val hostname: String = "localhost", @JvmField val port: Int = 80) {
    /*
    incoming requests from outside the database. Distribution algorithms must calculate the intended target nodes.
    */
    @JvmField
    val fullname = hostname + ":" + port

    abstract suspend fun start()
    /*
    any incoming request - further selection, what actually is the request
    */
    suspend fun receive(path: String, isPost: Boolean, data: String, params: Map<String, String>): ByteArray {
        when (path) {
            "/stacktrace" -> {
            }
            "/sparql/query" -> {
                if (isPost) {
                    return HttpEndpoint.evaluate_sparql_query_string(data, true).encodeToByteArray()
                } else {
                    return HttpEndpoint.evaluate_sparql_query_string(params["query"]!!, true).encodeToByteArray()
                }
            }
            "/sparql/operator" -> {
                if (isPost) {
                    return HttpEndpoint.evaluate_sparql_query_operator_xml(data, true).encodeToByteArray()
                } else {
                    return HttpEndpoint.evaluate_sparql_query_operator_xml(params["query"]!!, true).encodeToByteArray()
                }
            }
            "/import/turtle" -> {
                if (isPost) {
                    return HttpEndpoint.import_turtle_files(data).encodeToByteArray()
                } else {
                    return HttpEndpoint.import_turtle_files(params["query"]!!).encodeToByteArray()
                }
            }
            "/import/xml" -> {
                if (isPost) {
                    return HttpEndpoint.import_xml_data(data).encodeToByteArray()
                } else {
                    return HttpEndpoint.import_xml_data(params["query"]!!).encodeToByteArray()
                }
            }
            "/persistence/store" -> {
                return process_persistence_store(data).encodeToByteArray()
            }
            "/persistence/load" -> {
                return process_persistence_load(data).encodeToByteArray()
            }
        }
        TODO("unreachable")
    }

    fun process_persistence_store(foldername: String): String {
        println("process_persistence_store $foldername")
        File(foldername).deleteRecursively()
        File(foldername).mkdirs()
        BenchmarkUtils.start(EBenchmark.SAVE_DICTIONARY)
        nodeGlobalDictionary.safeToFolder(foldername)
        var timeDict = BenchmarkUtils.elapsedSeconds(EBenchmark.SAVE_DICTIONARY)
        BenchmarkUtils.start(EBenchmark.SAVE_TRIPLE_STORE)
        val stores = DistributedTripleStore.localStore.stores
        var idx = 0
        File(foldername + "/stores.txt").printWriter { out ->
            stores.keys.forEach { name ->
                val store = stores[name]!!
                File(foldername + "/$idx").mkdirs()
                store.safeToFolder(foldername + "/$idx")
                out.println(name)
                idx++
            }
        }
        var timeStore = BenchmarkUtils.elapsedSeconds(EBenchmark.SAVE_TRIPLE_STORE)
        return XMLElement("success $timeDict $timeStore").toString()
    }

    fun process_persistence_load(foldername: String): String {
        MemoryStatistics.print("before load")
        BenchmarkUtils.start(EBenchmark.LOAD_DICTIONARY)
        nodeGlobalDictionary.loadFromFolder(foldername)
        var timeDict = BenchmarkUtils.elapsedSeconds(EBenchmark.LOAD_DICTIONARY)
        MemoryStatistics.print("after load dictionary")
        BenchmarkUtils.start(EBenchmark.LOAD_TRIPLE_STORE)
        val stores = DistributedTripleStore.localStore.stores
        var idx = 0
        File(foldername + "/stores.txt").forEachLine { name ->
            val store = stores[name]!!
            store.loadFromFolder(foldername + "/$idx")
            idx++
        }
        var timeStore = BenchmarkUtils.elapsedSeconds(EBenchmark.LOAD_TRIPLE_STORE)
        MemoryStatistics.print("after load triplestore")
        return XMLElement("success $timeDict $timeStore").toString()
    }
}

var endpointServer: EndpointServer? = null
