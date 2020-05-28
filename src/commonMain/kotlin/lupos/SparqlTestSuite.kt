package lupos
import kotlin.jvm.JvmField
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.File
import lupos.s00misc.GlobalLogger
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.ParseError
import lupos.s02buildSyntaxTree.rdf.BlankNode
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s02buildSyntaxTree.rdf.ID_Triple
import lupos.s02buildSyntaxTree.rdf.IRI
import lupos.s02buildSyntaxTree.rdf.SimpleLiteral
import lupos.s02buildSyntaxTree.sparql1_1.parseSPARQL
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithDictionary
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPValuesImportXML
import lupos.s09physicalOperators.POPBase
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXMLElement
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s14endpoint.convertToOPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.s16network.*
class SparqlTestSuite() {
    @JvmField
    val errorBoundForDecimalsDigits = 6
    fun testMain() {
Coverage.funStart(13217)
        repeat(1) {
Coverage.statementStart(13218)
            GlobalLogger.log(ELoggerType.RELEASE, { "Starting tests..." })
Coverage.statementStart(13219)
            val (nr_t, nr_e) = parseManifestFile("resources/sparql11-test-suite/", "manifest-all.ttl")
Coverage.statementStart(13220)
            GlobalLogger.log(ELoggerType.RELEASE, { "Number of tests: " + nr_t })
Coverage.statementStart(13221)
            GlobalLogger.log(ELoggerType.RELEASE, { "Number of errors: " + nr_e })
Coverage.statementStart(13222)
            var prefix = "resources/sp2b/"
Coverage.statementStart(13223)
            var lastinput: String? = null
Coverage.statementStart(13224)
            File(prefix + "config.csv").forEachLine {
Coverage.statementStart(13225)
                val line = it.split(",")
Coverage.statementStart(13226)
                if (line.size > 3) {
Coverage.ifStart(13227)
                    val triplesCount = line[0]
Coverage.statementStart(13228)
                    val queryFile = prefix + line[1]
Coverage.statementStart(13229)
                    var inputFile = prefix + line[2]
Coverage.statementStart(13230)
                    val outputFile = prefix + line[3]
Coverage.statementStart(13231)
                    if (lastinput == inputFile) {
Coverage.ifStart(13232)
                        inputFile = "#keep-data#"
Coverage.statementStart(13233)
                    } else {
Coverage.ifStart(13234)
                        lastinput = inputFile
Coverage.statementStart(13235)
                    }
Coverage.statementStart(13236)
                    if (!File(outputFile).exists()) {
Coverage.ifStart(13237)
                        try {
Coverage.statementStart(13238)
                            JenaWrapper.loadFromFile("/src/luposdate3000/" + inputFile)
Coverage.statementStart(13239)
                            val jenaResult = JenaWrapper.execQuery(File(queryFile).readAsString())
Coverage.statementStart(13240)
                            val jenaXML = XMLElement.parseFromXml(jenaResult)!!
Coverage.statementStart(13241)
                            File(outputFile).printWriter {
Coverage.statementStart(13242)
                                it.println(jenaXML.toPrettyString())
Coverage.statementStart(13243)
                            }
Coverage.statementStart(13244)
                        } catch (e: Throwable) {
Coverage.statementStart(13245)
                            e.printStackTrace()
Coverage.statementStart(13246)
                        } finally {
Coverage.statementStart(13247)
                            JenaWrapper.dropAll()
Coverage.statementStart(13248)
                        }
Coverage.statementStart(13249)
                    }
Coverage.statementStart(13250)
                    CoroutinesHelper.runBlock {
Coverage.statementStart(13251)
                        GlobalLogger.log(ELoggerType.RELEASE, { "  Test: " + queryFile + "-" + triplesCount })
Coverage.statementStart(13252)
                        parseSPARQLAndEvaluate(queryFile, true, queryFile, inputFile, outputFile, null, mutableListOf<MutableMap<String, String>>(), mutableListOf<MutableMap<String, String>>())
Coverage.statementStart(13253)
                    }
Coverage.statementStart(13254)
                }
Coverage.statementStart(13255)
            }
Coverage.statementStart(13256)
        }
Coverage.statementStart(13257)
ResultSetDictionary.debug()
Coverage.statementStart(13258)
}
    private fun listMembers(data: SevenIndices, start: Long, f: (Long) -> Unit) {
Coverage.funStart(13259)
        val rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
Coverage.statementStart(13260)
        val nil = rdf + "nil"
Coverage.statementStart(13261)
        val first = rdf + "first"
Coverage.statementStart(13262)
        val rest = rdf + "rest"
Coverage.statementStart(13263)
        val nil_iri = Dictionary.IRI(nil)
Coverage.statementStart(13264)
        val first_iri = Dictionary.IRI(first)
Coverage.statementStart(13265)
        val rest_iri = Dictionary.IRI(rest)
Coverage.statementStart(13266)
        fun recursiveListMembers(current: Long) {
Coverage.funStart(13267)
            data.sp(current, first_iri).forEach { f(it) }
Coverage.statementStart(13268)
            data.sp(current, rest_iri).forEach {
Coverage.forEachLoopStart(13269)
                if (it != nil_iri) {
Coverage.ifStart(13270)
                    listMembers(data, it, f)
Coverage.statementStart(13271)
                }
Coverage.statementStart(13272)
            }
Coverage.statementStart(13273)
        }
        recursiveListMembers(start)
    }
    private fun readTurtleData(filename: String, consume_triple: (Long, Long, Long) -> Unit) {
Coverage.funStart(13274)
        val ltit = LookAheadTokenIterator(lupos.s02buildSyntaxTree.turtle.TurtleScanner(LexerCharIterator(File(filename).readAsString())), 3)
Coverage.statementStart(13275)
        try {
Coverage.statementStart(13276)
            TurtleParserWithDictionary(consume_triple, ltit).turtleDoc()
Coverage.statementStart(13277)
        } catch (e: ParseError) {
Coverage.statementStart(13278)
            GlobalLogger.stacktrace(ELoggerType.DEBUG, e)
Coverage.statementStart(13279)
            GlobalLogger.log(ELoggerType.DEBUG, { "Error in the following line:" })
Coverage.statementStart(13280)
            GlobalLogger.log(ELoggerType.DEBUG, { e.lineNumber })
Coverage.statementStart(13281)
        }
Coverage.statementStart(13282)
    }
    private fun createSevenIndices(filename: String): SevenIndices {
Coverage.funStart(13283)
        val data = SevenIndices()
Coverage.statementStart(13284)
        readTurtleData(filename, data::add)
Coverage.statementStart(13285)
        data.distinct()
Coverage.statementStart(13286)
        return data
    }
    private fun parseManifestFile(prefix: String, filename: String): Pair<Int, Int> {
Coverage.funStart(13287)
        var numberOfErrors = 0
Coverage.statementStart(13288)
        var numberOfTests = 0
Coverage.statementStart(13289)
        GlobalLogger.log(ELoggerType.DEBUG, { "Reading file " + filename + "..." })
Coverage.statementStart(13290)
        val data = createSevenIndices(prefix + filename)
Coverage.statementStart(13291)
        val newprefix = prefix + filename.substringBeforeLast("/") + "/"
Coverage.statementStart(13292)
        val manifestEntries = data.po(Dictionary.IRI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"), Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#Manifest"))
Coverage.statementStart(13293)
        manifestEntries.forEach {
Coverage.forEachLoopStart(13294)
            // Are other manifest files included?
Coverage.statementStart(13295)
            val included = data.sp(it, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#include"))
Coverage.statementStart(13296)
            included.forEach {
Coverage.forEachLoopStart(13297)
                // follow list of included manifest files:
Coverage.statementStart(13298)
                listMembers(data, it) {
Coverage.statementStart(13299)
                    val includedfile = Dictionary[it]
Coverage.statementStart(13300)
                    if (includedfile != null) {
Coverage.ifStart(13301)
                        includedfile as IRI
Coverage.statementStart(13302)
                        val (nr_t, nr_e) = parseManifestFile(prefix, includedfile.iri)
Coverage.statementStart(13303)
                        numberOfTests += nr_t
Coverage.statementStart(13304)
                        numberOfErrors += nr_e
Coverage.statementStart(13305)
                    }
Coverage.statementStart(13306)
                }
Coverage.statementStart(13307)
            }
Coverage.statementStart(13308)
            // Now look for_ the tests:
Coverage.statementStart(13309)
            val tests = data.sp(it, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#entries"))
Coverage.statementStart(13310)
            tests.forEach {
Coverage.forEachLoopStart(13311)
                // follow list of entries:
Coverage.statementStart(13312)
                listMembers(data, it) {
Coverage.statementStart(13313)
                    // for_ printing out the name:
Coverage.statementStart(13314)
                    val name = data.sp(it, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#name"))
Coverage.statementStart(13315)
                    name.forEach {
Coverage.forEachLoopStart(13316)
                        GlobalLogger.log(ELoggerType.RELEASE, { "  Test: " + (Dictionary[it] as SimpleLiteral).content })
Coverage.statementStart(13317)
                    }
Coverage.statementStart(13318)
                    numberOfTests++
Coverage.statementStart(13319)
                    if (!testOneEntry(data, it, newprefix)) {
Coverage.ifStart(13320)
                        numberOfErrors++
Coverage.statementStart(13321)
                    }
Coverage.statementStart(13322)
                }
Coverage.statementStart(13323)
            }
Coverage.statementStart(13324)
        }
Coverage.statementStart(13325)
        return Pair(numberOfTests, numberOfErrors)
    }
    private fun readFileOrNull(name: String?): String? {
Coverage.funStart(13326)
        if (name == null) {
Coverage.ifStart(13327)
            return null
        }
Coverage.statementStart(13328)
        return File(name).readAsString()
    }
    private fun testOneEntry(data: SevenIndices, node: Long, prefix: String): Boolean {
Coverage.funStart(13329)
        var testType: String? = null
Coverage.statementStart(13330)
        var comment: String? = null
Coverage.statementStart(13331)
        var features = mutableListOf<String>()
Coverage.statementStart(13332)
        var description: String? = null
Coverage.statementStart(13333)
        var names = mutableListOf<String>()
Coverage.statementStart(13334)
        var expectedResult = true
Coverage.statementStart(13335)
        var queryFile: String? = null
Coverage.statementStart(13336)
        var inputDataFile: String? = null
Coverage.statementStart(13337)
        var resultFile: String? = null
Coverage.statementStart(13338)
        var services = mutableListOf<MutableMap<String, String>>()
Coverage.statementStart(13339)
        var inputDataGraph = mutableListOf<MutableMap<String, String>>()
Coverage.statementStart(13340)
        var outputDataGraph = mutableListOf<MutableMap<String, String>>()
Coverage.statementStart(13341)
        data.s(node).forEach {
Coverage.forEachLoopStart(13342)
            val iri = (Dictionary[it.first] as IRI).iri
Coverage.statementStart(13343)
            when (iri) {
                "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#result" -> {
Coverage.whenCaseStart(13345)
                    when {
                        Dictionary[it.second] is IRI -> {
Coverage.whenCaseStart(13347)
                            SanityCheck.checkNULL({ resultFile })
Coverage.statementStart(13348)
                            resultFile = prefix + (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13349)
                        }
                        Dictionary[it.second] is BlankNode -> {
Coverage.whenCaseStart(13350)
                            data.s(it.second).forEach {
Coverage.forEachLoopStart(13351)
                                val iri2 = (Dictionary[it.first] as IRI).iri
Coverage.statementStart(13352)
                                when (iri2) {
                                    "http://www.w3.org/2009/sparql/tests/test-update#data" -> {
Coverage.whenCaseStart(13354)
                                        val graph = mutableMapOf<String, String>()
Coverage.statementStart(13355)
                                        graph["name"] = ""
Coverage.statementStart(13356)
                                        graph["filename"] = prefix + (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13357)
                                        outputDataGraph.add(graph)
Coverage.statementStart(13358)
                                    }
                                    "http://www.w3.org/2009/sparql/tests/test-update#graphData" -> {
Coverage.whenCaseStart(13359)
                                        val graph = mutableMapOf<String, String>()
Coverage.statementStart(13360)
                                        data.s(it.second).forEach {
Coverage.forEachLoopStart(13361)
                                            val iri3 = (Dictionary[it.first] as IRI).iri
Coverage.statementStart(13362)
                                            when (iri3) {
                                                "http://www.w3.org/2009/sparql/tests/test-update#graph" -> {
Coverage.whenCaseStart(13364)
                                                    graph["filename"] = prefix + (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13365)
                                                }
                                                "http://www.w3.org/2000/01/rdf-schema#label" -> {
Coverage.whenCaseStart(13366)
                                                    graph["name"] = (Dictionary[it.second] as SimpleLiteral).content
Coverage.statementStart(13367)
                                                }
                                                else -> {
Coverage.whenCaseStart(13368)
                                                    throw Exception("unknown manifest resultGraphDataBlankNode : " + (Dictionary[it.first] as IRI).iri + " # " + Dictionary[it.second])
                                                }
                                            }
Coverage.statementStart(13369)
                                        }
Coverage.statementStart(13370)
                                        outputDataGraph.add(graph)
Coverage.statementStart(13371)
                                    }
                                    "http://www.w3.org/2009/sparql/tests/test-update#result" -> {
Coverage.whenCaseStart(13372)
                                        GlobalLogger.log(ELoggerType.DEBUG, { "unknown-manifest::http://www.w3.org/2009/sparql/tests/test-update#result : " + (Dictionary[it.second] as IRI).iri })
Coverage.statementStart(13373)
                                    }
                                    else -> {
Coverage.whenCaseStart(13374)
                                        throw Exception("unknown manifest resultBlankNode : " + (Dictionary[it.first] as IRI).iri + " # " + Dictionary[it.second])
                                    }
                                }
Coverage.statementStart(13375)
                            }
Coverage.statementStart(13376)
                        }
                        else -> {
Coverage.whenCaseStart(13377)
                            throw Exception("unknown manifest result : " + (Dictionary[it.first] as IRI).iri + " # " + Dictionary[it.second])
                        }
                    }
Coverage.statementStart(13378)
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#action" -> {
Coverage.whenCaseStart(13379)
                    when {
                        Dictionary[it.second] is IRI -> {
Coverage.whenCaseStart(13381)
                            queryFile = prefix + (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13382)
                        }
                        Dictionary[it.second] is BlankNode -> {
Coverage.whenCaseStart(13383)
                            data.s(it.second).forEach {
Coverage.forEachLoopStart(13384)
                                val iri2 = (Dictionary[it.first] as IRI).iri
Coverage.statementStart(13385)
                                when (iri2) {
                                    "http://www.w3.org/2001/sw/DataAccess/tests/test-query#data" -> {
Coverage.whenCaseStart(13387)
                                        SanityCheck.checkNULL({ inputDataFile })
Coverage.statementStart(13388)
                                        inputDataFile = prefix + (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13389)
                                    }
                                    "http://www.w3.org/2001/sw/DataAccess/tests/test-query#query" -> {
Coverage.whenCaseStart(13390)
                                        SanityCheck.checkNULL({ queryFile })
Coverage.statementStart(13391)
                                        queryFile = prefix + (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13392)
                                    }
                                    "http://www.w3.org/ns/sparql-service-description#entailmentRegime" -> {
Coverage.whenCaseStart(13393)
                                        GlobalLogger.log(ELoggerType.DEBUG, { "unknown-manifest::http://www.w3.org/ns/sparql-service-description#entailmentRegime " + Dictionary[it.second] })
Coverage.statementStart(13394)
                                    }
                                    "http://www.w3.org/ns/sparql-service-description#EntailmentProfile" -> {
Coverage.whenCaseStart(13395)
                                        GlobalLogger.log(ELoggerType.DEBUG, { "unknown-manifest::http://www.w3.org/ns/sparql-service-description#EntailmentProfile " + Dictionary[it.second] })
Coverage.statementStart(13396)
                                    }
                                    "http://www.w3.org/2001/sw/DataAccess/tests/test-query#graphData" -> {
Coverage.whenCaseStart(13397)
                                        val graph = mutableMapOf<String, String>()
Coverage.statementStart(13398)
                                        graph["name"] = (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13399)
                                        graph["filename"] = prefix + (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13400)
                                        inputDataGraph.add(graph)
Coverage.statementStart(13401)
                                    }
                                    "http://www.w3.org/2001/sw/DataAccess/tests/test-query#serviceData" -> {
Coverage.whenCaseStart(13402)
                                        val service = mutableMapOf<String, String>()
Coverage.statementStart(13403)
                                        data.s(it.second).forEach {
Coverage.forEachLoopStart(13404)
                                            val iri3 = (Dictionary[it.first] as IRI).iri
Coverage.statementStart(13405)
                                            when (iri3) {
                                                "http://www.w3.org/2001/sw/DataAccess/tests/test-query#endpoint" -> {
Coverage.whenCaseStart(13407)
                                                    service["name"] = (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13408)
                                                }
                                                "http://www.w3.org/2001/sw/DataAccess/tests/test-query#data" -> {
Coverage.whenCaseStart(13409)
                                                    service["filename"] = prefix + (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13410)
                                                }
                                                else -> {
Coverage.whenCaseStart(13411)
                                                    throw Exception("unknown manifest serviceData : " + (Dictionary[it.first] as IRI).iri + " # " + Dictionary[it.second])
                                                }
                                            }
Coverage.statementStart(13412)
                                        }
Coverage.statementStart(13413)
                                        if (service["filename"] != null) {
Coverage.ifStart(13414)
                                            services.add(service)
Coverage.statementStart(13415)
                                        }
Coverage.statementStart(13416)
                                    }
                                    "http://www.w3.org/2009/sparql/tests/test-update#request" -> {
Coverage.whenCaseStart(13417)
                                        SanityCheck.checkNULL({ queryFile })
Coverage.statementStart(13418)
                                        queryFile = prefix + (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13419)
                                    }
                                    "http://www.w3.org/2009/sparql/tests/test-update#data" -> {
Coverage.whenCaseStart(13420)
                                        SanityCheck.checkNULL({ inputDataFile })
Coverage.statementStart(13421)
                                        inputDataFile = prefix + (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13422)
                                    }
                                    "http://www.w3.org/2009/sparql/tests/test-update#graphData" -> {
Coverage.whenCaseStart(13423)
                                        val graph = mutableMapOf<String, String>()
Coverage.statementStart(13424)
                                        data.s(it.second).forEach {
Coverage.forEachLoopStart(13425)
                                            val iri3 = (Dictionary[it.first] as IRI).iri
Coverage.statementStart(13426)
                                            when (iri3) {
                                                "http://www.w3.org/2009/sparql/tests/test-update#graph" -> {
Coverage.whenCaseStart(13428)
                                                    graph["filename"] = prefix + (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13429)
                                                }
                                                "http://www.w3.org/2000/01/rdf-schema#label" -> {
Coverage.whenCaseStart(13430)
                                                    graph["name"] = (Dictionary[it.second] as SimpleLiteral).content
Coverage.statementStart(13431)
                                                }
                                                else -> {
Coverage.whenCaseStart(13432)
                                                    throw Exception("unknown manifest graphData : " + (Dictionary[it.first] as IRI).iri + " # " + Dictionary[it.second])
                                                }
                                            }
Coverage.statementStart(13433)
                                        }
Coverage.statementStart(13434)
                                        inputDataGraph.add(graph)
Coverage.statementStart(13435)
                                    }
                                    else -> {
Coverage.whenCaseStart(13436)
                                        throw Exception("unknown manifest action : " + (Dictionary[it.first] as IRI).iri + " # " + Dictionary[it.second])
                                    }
                                }
Coverage.statementStart(13437)
                            }
Coverage.statementStart(13438)
                        }
                        else -> {
Coverage.whenCaseStart(13439)
                            throw Exception("unknown manifest actionType : " + Dictionary[it.first])
                        }
                    }
Coverage.statementStart(13440)
                }
                "http://www.w3.org/1999/02/22-rdf-syntax-ns#type" -> {
Coverage.whenCaseStart(13441)
                    SanityCheck.checkNULL({ testType })
Coverage.statementStart(13442)
                    testType = (Dictionary[it.second] as IRI).iri
Coverage.statementStart(13443)
                    when ((Dictionary[it.second] as IRI).iri) {
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#CSVResultFormatTest" -> {
Coverage.whenCaseStart(13445)
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#NegativeUpdateSyntaxTest11" -> {
Coverage.whenCaseStart(13446)
                            expectedResult = false
Coverage.statementStart(13447)
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveSyntaxTest11" -> {
Coverage.whenCaseStart(13448)
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveUpdateSyntaxTest11" -> {
Coverage.whenCaseStart(13449)
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#ProtocolTest" -> {
Coverage.whenCaseStart(13450)
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#QueryEvaluationTest" -> {
Coverage.whenCaseStart(13451)
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#ServiceDescriptionTest" -> {
Coverage.whenCaseStart(13452)
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#UpdateEvaluationTest" -> {
Coverage.whenCaseStart(13453)
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#NegativeSyntaxTest11" -> {
Coverage.whenCaseStart(13454)
                            expectedResult = false
Coverage.statementStart(13455)
                        }
                        else -> {
Coverage.whenCaseStart(13456)
                            throw Exception("unknown manifest type : " + (Dictionary[it.second] as IRI).iri + " # " + (Dictionary[it.second] as IRI).iri)
                        }
                    }
Coverage.statementStart(13457)
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#name" -> {
Coverage.whenCaseStart(13458)
                    names.add((Dictionary[it.second] as SimpleLiteral).content)
Coverage.statementStart(13459)
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#feature" -> {
Coverage.whenCaseStart(13460)
                    features.add((Dictionary[it.second] as IRI).iri)
Coverage.statementStart(13461)
                }
                "http://www.w3.org/2000/01/rdf-schema#comment" -> {
Coverage.whenCaseStart(13462)
                    SanityCheck.checkNULL({ comment })
Coverage.statementStart(13463)
                    comment = (Dictionary[it.second] as SimpleLiteral).content
Coverage.statementStart(13464)
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approval" -> {
Coverage.whenCaseStart(13465)
                    GlobalLogger.log(ELoggerType.DEBUG, { "unknown-manifest::http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approval " + Dictionary[it.second] })
Coverage.statementStart(13466)
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approvedBy" -> {
Coverage.whenCaseStart(13467)
                    GlobalLogger.log(ELoggerType.DEBUG, { "unknown-manifest::http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approvedBy " + Dictionary[it.second] })
Coverage.statementStart(13468)
                }
                "http://www.w3.org/2000/01/rdf-schema#seeAlso" -> {
Coverage.whenCaseStart(13469)
                    GlobalLogger.log(ELoggerType.DEBUG, { "unknown-manifest::http://www.w3.org/2000/01/rdf-schema#seeAlso " + (Dictionary[it.second] as IRI).iri })
Coverage.statementStart(13470)
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-query#queryForm" -> {
Coverage.whenCaseStart(13471)
                    GlobalLogger.log(ELoggerType.DEBUG, { "unknown-manifest::http://www.w3.org/2001/sw/DataAccess/tests/test-query#queryForm " + (Dictionary[it.second] as IRI).iri })
Coverage.statementStart(13472)
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#description" -> {
Coverage.whenCaseStart(13473)
                    SanityCheck.checkNULL({ description })
Coverage.statementStart(13474)
                    description = (Dictionary[it.second] as SimpleLiteral).content
Coverage.statementStart(13475)
                }
                else -> {
Coverage.whenCaseStart(13476)
                    throw Exception("unknown manifest entry : " + (Dictionary[it.first] as IRI).iri + " # " + Dictionary[it.second])
                }
            }
Coverage.statementStart(13477)
        }
Coverage.statementStart(13478)
        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "testType : $testType" })
Coverage.statementStart(13479)
        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "names : $names" })
Coverage.statementStart(13480)
        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "comment : $comment" })
Coverage.statementStart(13481)
        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "description : $description" })
Coverage.statementStart(13482)
        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "features : $features" })
Coverage.statementStart(13483)
        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "inputDataGraph : $inputDataGraph" })
Coverage.statementStart(13484)
        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "outputDataGraph : $outputDataGraph" })
Coverage.statementStart(13485)
        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "expectedResult : $expectedResult" })
Coverage.statementStart(13486)
        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "queryFile : $queryFile" })
Coverage.statementStart(13487)
        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "inputDataFile : $inputDataFile" })
Coverage.statementStart(13488)
        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "resultFile : $resultFile" })
Coverage.statementStart(13489)
        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "services : $services" })
Coverage.statementStart(13490)
        if (queryFile == null) {
Coverage.ifStart(13491)
            return true
        }
Coverage.statementStart(13492)
        var success = false
Coverage.statementStart(13493)
        CoroutinesHelper.runBlock {
Coverage.statementStart(13494)
            success = parseSPARQLAndEvaluate(names.first(), expectedResult, queryFile!!, inputDataFile, resultFile, services, inputDataGraph, outputDataGraph)
Coverage.statementStart(13495)
        }
Coverage.statementStart(13496)
        return success == expectedResult
    }
    @JvmField
    var i = 0
    suspend fun parseSPARQLAndEvaluate(testName: String, expectedResult: Boolean, queryFile: String, inputDataFileName: String?, resultDataFileName: String?, services: List<Map<String, String>>?, inputDataGraph: MutableList<MutableMap<String, String>>, outputDataGraph: MutableList<MutableMap<String, String>>): Boolean {
Coverage.funStart(13497)
        var ignoreJena = false
Coverage.statementStart(13498)
        try {
Coverage.statementStart(13499)
            try {
Coverage.statementStart(13500)
                val toParse = readFileOrNull(queryFile)!!
Coverage.statementStart(13501)
                if (toParse.contains("service", true)) {
Coverage.ifStart(13502)
                    GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Failed(Service)" })
Coverage.statementStart(13503)
                    return false
                }
Coverage.statementStart(13504)
                val resultData = readFileOrNull(resultDataFileName)
Coverage.statementStart(13505)
                if (inputDataFileName != "#keep-data#") {
Coverage.ifStart(13506)
                    val query3 = Query()
Coverage.statementStart(13507)
                    DistributedTripleStore.clearGraph(query3, PersistentStoreLocal.defaultGraphName)
Coverage.statementStart(13508)
                    query3.commit()
Coverage.statementStart(13509)
                    JenaWrapper.dropAll()
Coverage.statementStart(13510)
                    val inputData = readFileOrNull(inputDataFileName)
Coverage.statementStart(13511)
                    val query2 = Query()
Coverage.statementStart(13512)
                    ServerCommunicationSend.graphClearAll(query2)
Coverage.statementStart(13513)
                    query2.commit()
Coverage.statementStart(13514)
                    if (inputData != null && inputDataFileName != null) {
Coverage.ifStart(13515)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "InputData Graph[] Original" })
Coverage.statementStart(13516)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { inputData })
Coverage.statementStart(13517)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Input Data Graph[]" })
Coverage.statementStart(13518)
                        var xmlQueryInput = XMLElement.parseFromAny(inputData, inputDataFileName)!!
Coverage.statementStart(13519)
                        if (inputDataFileName.endsWith(".ttl") || inputDataFileName.endsWith(".n3")) {
Coverage.ifStart(13520)
                            var xmlGraphBulk: XMLElement? = null
Coverage.statementStart(13521)
                            CoroutinesHelper.runBlock {
Coverage.statementStart(13522)
                                val query = Query()
Coverage.statementStart(13523)
                                HttpEndpoint.import_turtle_files(inputDataFileName, MyMapStringIntPatriciaTrie())
Coverage.statementStart(13524)
                                val bulkSelect = DistributedTripleStore.getDefaultGraph(query).getIterator(arrayOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), EIndexPattern.SPO)
Coverage.statementStart(13525)
                                xmlGraphBulk = QueryResultToXMLElement.toXML(bulkSelect)
Coverage.statementStart(13526)
                            }
Coverage.statementStart(13527)
                            if (xmlGraphBulk == null || !xmlGraphBulk!!.myEqualsUnclean(xmlQueryInput, true, true, true)) {
Coverage.ifStart(13528)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "test xmlQueryInput :: " + xmlQueryInput.toPrettyString() })
Coverage.statementStart(13529)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "test xmlGraphBulk :: " + xmlGraphBulk?.toPrettyString() })
Coverage.statementStart(13530)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Failed(BulkImport)" })
Coverage.statementStart(13531)
                                return false
                            }
Coverage.statementStart(13532)
                        } else {
Coverage.ifStart(13533)
                            val query = Query()
Coverage.statementStart(13534)
                            CoroutinesHelper.runBlock {
Coverage.statementStart(13535)
                                val tmp = POPValuesImportXML(query, listOf("s", "p", "o"), xmlQueryInput).evaluate()
Coverage.statementStart(13536)
                                DistributedTripleStore.getDefaultGraph(query).modify(arrayOf(tmp.columns["s"]!!, tmp.columns["p"]!!, tmp.columns["o"]!!), EModifyType.INSERT)
Coverage.statementStart(13537)
                            }
Coverage.statementStart(13538)
                            query.commit()
Coverage.statementStart(13539)
                        }
Coverage.statementStart(13540)
                        val query = Query()
Coverage.statementStart(13541)
                        File("log/storetest").mkdirs()
Coverage.statementStart(13542)
                        DistributedTripleStore.localStore.getDefaultGraph(query).safeToFolder("log/storetest")
Coverage.statementStart(13543)
/*
Coverage.statementStart(13544)
                    DistributedTripleStore.localStore.getDefaultGraph(query).loadFromFolder("log/storetest")
Coverage.statementStart(13545)
                    var xmlGraphLoad: XMLElement? = null
Coverage.statementStart(13546)
                    CoroutinesHelper.runBlock {
Coverage.statementStart(13547)
                        val loadSelect = DistributedTripleStore.getDefaultGraph(query).getIterator(arrayOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), EIndexPattern.SPO)
Coverage.statementStart(13548)
                        query.commit()
Coverage.statementStart(13549)
                        xmlGraphLoad = QueryResultToXMLElement.toXML(loadSelect)
Coverage.statementStart(13550)
                    }
Coverage.statementStart(13551)
                    if (xmlGraphLoad == null || !xmlGraphLoad!!.myEqualsUnclean(xmlQueryInput,true,true,true)) {
Coverage.ifStart(13552)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "test xmlQueryInput :: " + xmlQueryInput.toPrettyString() })
Coverage.statementStart(13553)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "test xmlGraphLoad :: " + xmlGraphLoad?.toPrettyString() })
Coverage.statementStart(13554)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Failed(LoadImport)" })
Coverage.statementStart(13555)
                        return false
                    }
Coverage.statementStart(13556)
*/
Coverage.statementStart(13557)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "test InputData Graph[] ::" + xmlQueryInput.toPrettyString() })
Coverage.statementStart(13558)
                        try {
Coverage.statementStart(13559)
                            JenaWrapper.loadFromFile("/src/luposdate3000/" + inputDataFileName)
Coverage.statementStart(13560)
                        } catch (e: Throwable) {
Coverage.statementStart(13561)
                            ignoreJena = true
Coverage.statementStart(13562)
                        }
Coverage.statementStart(13563)
                    }
Coverage.statementStart(13564)
                    inputDataGraph.forEach {
Coverage.forEachLoopStart(13565)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "InputData Graph[${it["name"]}] Original" })
Coverage.statementStart(13566)
                        val inputData2 = readFileOrNull(it["filename"])
Coverage.statementStart(13567)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { inputData2 })
Coverage.statementStart(13568)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Input Data Graph[${it["name"]}]" })
Coverage.statementStart(13569)
                        var xmlQueryInput = XMLElement.parseFromAny(inputData2!!, it["filename"]!!)!!
Coverage.statementStart(13570)
                        val query = Query()
Coverage.statementStart(13571)
                        CoroutinesHelper.runBlock {
Coverage.statementStart(13572)
                            val tmp = POPValuesImportXML(query, listOf("s", "p", "o"), xmlQueryInput).evaluate()
Coverage.statementStart(13573)
                            DistributedTripleStore.getNamedGraph(query, it["name"]!!).modify(arrayOf(tmp.columns["s"]!!, tmp.columns["p"]!!, tmp.columns["o"]!!), EModifyType.INSERT)
Coverage.statementStart(13574)
                        }
Coverage.statementStart(13575)
                        query.commit()
Coverage.statementStart(13576)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "test Input Graph[${it["name"]!!}] :: " + xmlQueryInput.toPrettyString() })
Coverage.statementStart(13577)
                        try {
Coverage.statementStart(13578)
                            JenaWrapper.loadFromFile("/src/luposdate3000/" + it["filename"]!!, it["name"]!!)
Coverage.statementStart(13579)
                        } catch (e: Throwable) {
Coverage.statementStart(13580)
                            ignoreJena = true
Coverage.statementStart(13581)
                        }
Coverage.statementStart(13582)
                    }
Coverage.statementStart(13583)
                    if (services != null) {
Coverage.ifStart(13584)
                        for (s in services) {
Coverage.forLoopStart(13585)
                            val n = s["name"]!!
Coverage.statementStart(13586)
                            val fn = s["filename"]!!
Coverage.statementStart(13587)
                            val fc = readFileOrNull(fn)!!
Coverage.statementStart(13588)
//                        ServerCommunicationSend.insertOnNamedNode(n, XMLElement.parseFromAny(fc, fn)!!)
Coverage.statementStart(13589)
                        }
Coverage.statementStart(13590)
                    }
Coverage.statementStart(13591)
                }
Coverage.statementStart(13592)
                val testName2 = "[^a-zA-Z0-9]".toRegex().replace(testName, "-")
Coverage.statementStart(13593)
                val query = Query()
Coverage.statementStart(13594)
                var res: Boolean
Coverage.statementStart(13595)
                GlobalLogger.log(ELoggerType.TEST_DETAIL, { "----------String Query" })
Coverage.statementStart(13596)
                GlobalLogger.log(ELoggerType.TEST_RESULT, { toParse })
Coverage.statementStart(13597)
                GlobalLogger.log(ELoggerType.TEST_DETAIL, { "----------Abstract Syntax Tree" })
Coverage.statementStart(13598)
                val lcit = LexerCharIterator(toParse)
Coverage.statementStart(13599)
                val tit = TokenIteratorSPARQLParser(lcit)
Coverage.statementStart(13600)
                val ltit = LookAheadTokenIterator(tit, 3)
Coverage.statementStart(13601)
                val parser = SPARQLParser(ltit)
Coverage.statementStart(13602)
                val ast_node = parser.expr()
Coverage.statementStart(13603)
                GlobalLogger.log(ELoggerType.TEST_DETAIL, { ast_node })
Coverage.statementStart(13604)
                GlobalLogger.log(ELoggerType.TEST_DETAIL, { "----------Logical Operator Graph" })
Coverage.statementStart(13605)
                val lop_node = ast_node.visit(OperatorGraphVisitor(query))
Coverage.statementStart(13606)
                File("log/${testName2}-Logical-Operator-Graph.tex").printWriter {
Coverage.statementStart(13607)
                    it.println(OperatorGraphToLatex(lop_node.toXMLElement().toString(), testName2))
Coverage.statementStart(13608)
                }
Coverage.statementStart(13609)
                SanityCheck.check({ lop_node == lop_node.cloneOP() }, { lop_node.toString() + " - " + lop_node.cloneOP().toString() })
Coverage.statementStart(13610)
                GlobalLogger.log(ELoggerType.TEST_DETAIL, { lop_node.toXMLElement().toPrettyString() })
Coverage.statementStart(13611)
                GlobalLogger.log(ELoggerType.TEST_DETAIL, { "----------Logical Operator Graph optimized" })
Coverage.statementStart(13612)
                val lop_node2 = LogicalOptimizer(query).optimizeCall(lop_node)
Coverage.statementStart(13613)
                SanityCheck.check { lop_node2 == lop_node2.cloneOP() }
Coverage.statementStart(13614)
                File("log/${testName2}-Logical-Operator-Graph-Optimized.tex").printWriter {
Coverage.statementStart(13615)
                    it.println(OperatorGraphToLatex(lop_node2.toXMLElement().toString(), testName2))
Coverage.statementStart(13616)
                }
Coverage.statementStart(13617)
                GlobalLogger.log(ELoggerType.TEST_DETAIL, { lop_node2.toXMLElement().toPrettyString() })
Coverage.statementStart(13618)
                GlobalLogger.log(ELoggerType.TEST_DETAIL, { "----------Physical Operator Graph" })
Coverage.statementStart(13619)
                val pop_optimizer = PhysicalOptimizer(query)
Coverage.statementStart(13620)
                val pop_node = pop_optimizer.optimizeCall(lop_node2)
Coverage.statementStart(13621)
                SanityCheck.check({ pop_node == pop_node.cloneOP() }, { pop_node.toString() + " - " + pop_node.cloneOP().toString() })
Coverage.statementStart(13622)
                SanityCheck { pop_node.toSparqlQuery() }
Coverage.statementStart(13623)
                File("log/${testName2}-Physical-Operator-Graph.tex").printWriter {
Coverage.statementStart(13624)
                    it.println(OperatorGraphToLatex(pop_node.toXMLElement().toString(), testName2))
Coverage.statementStart(13625)
                }
Coverage.statementStart(13626)
                GlobalLogger.log(ELoggerType.TEST_DETAIL, { pop_node.toXMLElement().toPrettyString() })
Coverage.statementStart(13627)
                GlobalLogger.log(ELoggerType.TEST_DETAIL, { "----------Distributed Operator Graph" })
Coverage.statementStart(13628)
                val pop_distributed_node = KeyDistributionOptimizer(query).optimizeCall(pop_node)
Coverage.statementStart(13629)
                SanityCheck.check { pop_distributed_node == pop_distributed_node.cloneOP() }
Coverage.statementStart(13630)
                SanityCheck { pop_distributed_node.toSparqlQuery() }
Coverage.statementStart(13631)
                File("log/${testName2}-Distributed-Operator-Graph.tex").printWriter {
Coverage.statementStart(13632)
                    it.println(OperatorGraphToLatex(pop_distributed_node.toXMLElement().toString(), testName2))
Coverage.statementStart(13633)
                }
Coverage.statementStart(13634)
                GlobalLogger.log(ELoggerType.TEST_DETAIL, { pop_distributed_node })
Coverage.statementStart(13635)
                var xmlQueryResult: XMLElement? = null
Coverage.statementStart(13636)
                if (!outputDataGraph.isEmpty() || (resultData != null && resultDataFileName != null)) {
Coverage.ifStart(13637)
                    GlobalLogger.log(ELoggerType.TEST_DETAIL, { "----------Query Result" })
Coverage.statementStart(13638)
                    xmlQueryResult = QueryResultToXMLElement.toXML(pop_distributed_node)
Coverage.statementStart(13639)
                    GlobalLogger.log(ELoggerType.TEST_DETAIL, { "test xmlQueryResult :: " + xmlQueryResult.toPrettyString() })
Coverage.statementStart(13640)
                    query.commit()
Coverage.statementStart(13641)
                }
Coverage.statementStart(13642)
                var verifiedOutput = false
Coverage.statementStart(13643)
                outputDataGraph.forEach {
Coverage.forEachLoopStart(13644)
                    val outputData = readFileOrNull(it["filename"])
Coverage.statementStart(13645)
                    var xmlGraphTarget = XMLElement.parseFromAny(outputData!!, it["filename"]!!)!!
Coverage.statementStart(13646)
                    val tmp = DistributedTripleStore.getNamedGraph(query, it["name"]!!).getIterator(arrayOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), EIndexPattern.SPO)
Coverage.statementStart(13647)
                    var xmlGraphActual = QueryResultToXMLElement.toXML(tmp)
Coverage.statementStart(13648)
                    if (!xmlGraphTarget.myEqualsUnclean(xmlGraphActual, true, true, true)) {
Coverage.ifStart(13649)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "OutputData Graph[${it["name"]}] Original" })
Coverage.statementStart(13650)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { outputData })
Coverage.statementStart(13651)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Verify Output Data Graph[${it["name"]}] ... target,actual" })
Coverage.statementStart(13652)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "test xmlGraphTarget :: " + xmlGraphTarget.toPrettyString() })
Coverage.statementStart(13653)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "test xmlGraphActual :: " + xmlGraphActual.toPrettyString() })
Coverage.statementStart(13654)
                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Failed(PersistentStore Graph)" })
Coverage.statementStart(13655)
                        return false
                    } else {
Coverage.statementStart(13656)
                        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "OutputData Graph[${it["name"]}] Original" })
Coverage.statementStart(13657)
                        GlobalLogger.log(ELoggerType.TEST_DETAIL, { outputData })
Coverage.statementStart(13658)
                        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "----------Verify Output Data Graph[${it["name"]}] ... target,actual" })
Coverage.statementStart(13659)
                        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "test xmlGraphTarget :: " + xmlGraphTarget.toPrettyString() })
Coverage.statementStart(13660)
                        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "test xmlGraphActual :: " + xmlGraphActual.toPrettyString() })
Coverage.statementStart(13661)
                    }
Coverage.statementStart(13662)
                    verifiedOutput = true
Coverage.statementStart(13663)
                }
Coverage.statementStart(13664)
                if (resultData != null && resultDataFileName != null) {
Coverage.ifStart(13665)
                    GlobalLogger.log(ELoggerType.TEST_DETAIL, { "----------Target Result" })
Coverage.statementStart(13666)
                    var xmlQueryTarget = XMLElement.parseFromAny(resultData, resultDataFileName)!!
Coverage.statementStart(13667)
                    GlobalLogger.log(ELoggerType.TEST_DETAIL, { "test xmlQueryTarget :: " + xmlQueryTarget.toPrettyString() })
Coverage.statementStart(13668)
                    GlobalLogger.log(ELoggerType.TEST_DETAIL, { resultData })
Coverage.statementStart(13669)
                    if (!ignoreJena) {
Coverage.ifStart(13670)
                        try {
Coverage.statementStart(13671)
                            val jenaResult = JenaWrapper.execQuery(toParse)
Coverage.statementStart(13672)
                            val jenaXML = XMLElement.parseFromXml(jenaResult)
Coverage.statementStart(13673)
//println("test xmlJena >>>>>"+jenaResult+"<<<<<")
Coverage.statementStart(13674)
                            if (jenaXML != null && !jenaXML.myEqualsUnclean(xmlQueryResult, true, true, true)) {
Coverage.ifStart(13675)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Verify Output Jena jena,actual" })
Coverage.statementStart(13676)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "test jenaOriginal :: " + jenaResult })
Coverage.statementStart(13677)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "test xmlJena :: " + jenaXML.toPrettyString() })
Coverage.statementStart(13678)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "test xmlActual :: " + xmlQueryResult!!.toPrettyString() })
Coverage.statementStart(13679)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "test xmlTarget :: " + xmlQueryTarget.toPrettyString() })
Coverage.statementStart(13680)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Failed(Jena)" })
Coverage.statementStart(13681)
                                return false
                            }
Coverage.statementStart(13682)
                        } catch (e: Throwable) {
Coverage.statementStart(13683)
                            ignoreJena = true
Coverage.statementStart(13684)
                        }
Coverage.statementStart(13685)
                    }
Coverage.statementStart(13686)
                    res = xmlQueryResult!!.myEquals(xmlQueryTarget)
Coverage.statementStart(13687)
                    if (res) {
Coverage.ifStart(13688)
                        val xmlPOP = pop_distributed_node.toXMLElement()
Coverage.statementStart(13689)
                        val query4 = Query()
Coverage.statementStart(13690)
                        val popNodeRecovered = XMLElement.convertToOPBase(query4, xmlPOP)
Coverage.statementStart(13691)
                        GlobalLogger.log(ELoggerType.TEST_DETAIL, { xmlPOP.toPrettyString() })
Coverage.statementStart(13692)
                        GlobalLogger.log(ELoggerType.TEST_DETAIL, { popNodeRecovered.toXMLElement().toPrettyString() })
Coverage.statementStart(13693)
                        val xmlQueryResultRecovered = QueryResultToXMLElement.toXML(popNodeRecovered)
Coverage.statementStart(13694)
                        query4.commit()
Coverage.statementStart(13695)
                        GlobalLogger.log(ELoggerType.TEST_DETAIL, { "test xmlQueryResultRecovered :: " + xmlQueryResultRecovered.toPrettyString() })
Coverage.statementStart(13696)
                        if (xmlQueryResultRecovered.myEquals(xmlQueryResult)) {
Coverage.ifStart(13697)
                            if (expectedResult) {
Coverage.ifStart(13698)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Success" })
Coverage.statementStart(13699)
                            } else {
Coverage.ifStart(13700)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Failed(expectFalse)" })
Coverage.statementStart(13701)
                            }
Coverage.statementStart(13702)
                        } else {
Coverage.ifStart(13703)
                            GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Failed(RecoverFromXMLOperatorGraph)" })
Coverage.statementStart(13704)
                            res = false
Coverage.statementStart(13705)
                        }
Coverage.statementStart(13706)
                    } else {
Coverage.ifStart(13707)
                        val containsOrderBy = toParse.contains("ORDER", true)
Coverage.statementStart(13708)
                        val correctIfIgnoreOrderBy = xmlQueryResult.myEqualsUnclean(xmlQueryTarget, false, false, true)
Coverage.statementStart(13709)
                        val correctIfIgnoreString = xmlQueryResult.myEqualsUnclean(xmlQueryTarget, true, false, true)
Coverage.statementStart(13710)
                        val correctIfIgnoreNumber = xmlQueryResult.myEqualsUnclean(xmlQueryTarget, true, true, true)
Coverage.statementStart(13711)
                        val correctIfIgnoreAllExceptOrder = xmlQueryResult.myEqualsUnclean(xmlQueryTarget, true, true, false)
Coverage.statementStart(13712)
                        if (correctIfIgnoreNumber) {
Coverage.ifStart(13713)
                            if (expectedResult) {
Coverage.ifStart(13714)
                                if (containsOrderBy) {
Coverage.ifStart(13715)
                                    if (correctIfIgnoreAllExceptOrder) {
Coverage.ifStart(13716)
                                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Success" })
Coverage.statementStart(13717)
                                    } else {
Coverage.ifStart(13718)
                                        GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Success(Unordered)" })
Coverage.statementStart(13719)
                                    }
Coverage.statementStart(13720)
                                } else if (correctIfIgnoreOrderBy) {
Coverage.ifStart(13721)
                                    GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Success" })
Coverage.statementStart(13722)
                                } else if (correctIfIgnoreString) {
Coverage.ifStart(13723)
                                    GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Success(String)" })
Coverage.statementStart(13724)
                                } else if (correctIfIgnoreNumber) {
Coverage.ifStart(13725)
                                    GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Success(Number & String)" })
Coverage.statementStart(13726)
                                } else {
Coverage.ifStart(13727)
                                    throw Exception("unreachable")
                                }
Coverage.statementStart(13728)
                            } else {
Coverage.ifStart(13729)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Failed(expectFalse,Simplified)" })
Coverage.statementStart(13730)
                            }
Coverage.statementStart(13731)
                        } else {
Coverage.ifStart(13732)
                            if (expectedResult) {
Coverage.ifStart(13733)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "test xmlQueryTarget :: " + xmlQueryTarget.toPrettyString() })
Coverage.statementStart(13734)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "test xmlQueryResult :: " + xmlQueryResult.toPrettyString() })
Coverage.statementStart(13735)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Failed(Incorrect)" })
Coverage.statementStart(13736)
                            } else {
Coverage.ifStart(13737)
                                GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Success(ExpectFalse)" })
Coverage.statementStart(13738)
                            }
Coverage.statementStart(13739)
                        }
Coverage.statementStart(13740)
                    }
Coverage.statementStart(13741)
                    return res
                } else {
Coverage.statementStart(13742)
                    if (verifiedOutput) {
Coverage.ifStart(13743)
                        if (expectedResult) {
Coverage.ifStart(13744)
                            GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Success(Graph)" })
Coverage.statementStart(13745)
                        } else {
Coverage.ifStart(13746)
                            GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Failed(ExpectFalse,Graph)" })
Coverage.statementStart(13747)
                        }
Coverage.statementStart(13748)
                    } else {
Coverage.ifStart(13749)
                        if (expectedResult) {
Coverage.ifStart(13750)
                            GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Success(Syntax)" })
Coverage.statementStart(13751)
                        } else {
Coverage.ifStart(13752)
                            GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Failed(ExpectFalse,Syntax)" })
Coverage.statementStart(13753)
                        }
Coverage.statementStart(13754)
                    }
Coverage.statementStart(13755)
                    return expectedResult
                }
Coverage.statementStart(13756)
            } catch (e: ParseError) {
Coverage.statementStart(13757)
                if (expectedResult) {
Coverage.ifStart(13758)
                    e.printStackTrace()
Coverage.statementStart(13759)
                    GlobalLogger.log(ELoggerType.DEBUG, { e })
Coverage.statementStart(13760)
                    GlobalLogger.log(ELoggerType.DEBUG, { "Error in the following line:" })
Coverage.statementStart(13761)
                    GlobalLogger.log(ELoggerType.DEBUG, { e.lineNumber })
Coverage.statementStart(13762)
                    GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Failed(ParseError)" })
Coverage.statementStart(13763)
                } else {
Coverage.ifStart(13764)
                    GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Success(ExpectFalse,ParseError)" })
Coverage.statementStart(13765)
                }
Coverage.statementStart(13766)
                return false
            } catch (e: Throwable) {
Coverage.statementStart(13767)
                println("lastStatement :: ${Coverage.CoverageMapGenerated[Coverage.lastcounter]}")
Coverage.statementStart(13768)
                if (expectedResult) {
Coverage.ifStart(13769)
                    GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Failed(Throwable)" })
Coverage.statementStart(13770)
                    GlobalLogger.stacktrace(ELoggerType.TEST_RESULT, e)
Coverage.statementStart(13771)
                } else {
Coverage.ifStart(13772)
                    GlobalLogger.log(ELoggerType.TEST_RESULT, { "----------Success(ExpectFalse,Throwable)" })
Coverage.statementStart(13773)
                }
Coverage.statementStart(13774)
                return false
            }
Coverage.statementStart(13775)
        } finally {
Coverage.statementStart(13776)
            ColumnIteratorDebug.debug()
Coverage.statementStart(13777)
        }
Coverage.statementStart(13778)
    }
}
class SevenIndices {
    private val s = mutableMapOf<Long, Array<Pair<Long, Long>>>()
    private val p = mutableMapOf<Long, Array<Pair<Long, Long>>>()
    private val o = mutableMapOf<Long, Array<Pair<Long, Long>>>()
    private val sp = mutableMapOf<Pair<Long, Long>, LongArray>()
    private val so = mutableMapOf<Pair<Long, Long>, LongArray>()
    private val po = mutableMapOf<Pair<Long, Long>, LongArray>()
    @JvmField
    val spo = mutableSetOf<ID_Triple>()
    fun s(key: Long): Array<Pair<Long, Long>> = this.s[key] ?: arrayOf()
    fun p(key: Long): Array<Pair<Long, Long>> = this.p[key] ?: arrayOf()
    fun o(key: Long): Array<Pair<Long, Long>> = this.o[key] ?: arrayOf()
    fun sp(key1: Long, key2: Long): LongArray = this.sp[Pair(key1, key2)] ?: longArrayOf()
    fun so(key1: Long, key2: Long): LongArray = this.so[Pair(key1, key2)] ?: longArrayOf()
    fun po(key1: Long, key2: Long): LongArray = this.po[Pair(key1, key2)] ?: longArrayOf()
    fun spo(key1: Long, key2: Long, key3: Long): Boolean = this.spo(ID_Triple(key1, key2, key3))
    fun spo(key: ID_Triple): Boolean = this.spo.contains(key)
    fun distinct() {
Coverage.funStart(13779)
        distinctOneKeyMap(this.s)
Coverage.statementStart(13780)
        distinctOneKeyMap(this.p)
Coverage.statementStart(13781)
        distinctOneKeyMap(this.o)
Coverage.statementStart(13782)
        distinctTwoKeysMap(this.sp)
Coverage.statementStart(13783)
        distinctTwoKeysMap(this.so)
Coverage.statementStart(13784)
        distinctTwoKeysMap(this.po)
Coverage.statementStart(13785)
        // duplicates are already eliminated in this.spo!
Coverage.statementStart(13786)
    }
    fun add(triple_s: Long, triple_p: Long, triple_o: Long) {
Coverage.funStart(13787)
        addToOneKeyMap(this.s, triple_s, triple_p, triple_o)
Coverage.statementStart(13788)
        addToOneKeyMap(this.p, triple_p, triple_s, triple_o)
Coverage.statementStart(13789)
        addToOneKeyMap(this.o, triple_o, triple_s, triple_p)
Coverage.statementStart(13790)
        addToTwoKeysMap(this.sp, triple_s, triple_p, triple_o)
Coverage.statementStart(13791)
        addToTwoKeysMap(this.so, triple_s, triple_o, triple_p)
Coverage.statementStart(13792)
        addToTwoKeysMap(this.po, triple_p, triple_o, triple_s)
Coverage.statementStart(13793)
        this.spo += ID_Triple(triple_s, triple_p, triple_o)
Coverage.statementStart(13794)
    }
    private fun addToOneKeyMap(onekeymap: MutableMap<Long, Array<Pair<Long, Long>>>, key: Long, value1: Long, value2: Long) {
Coverage.funStart(13795)
        val values = onekeymap[key]
Coverage.statementStart(13796)
        val value = Pair(value1, value2)
Coverage.statementStart(13797)
        if (values == null) {
Coverage.ifStart(13798)
            onekeymap[key] = arrayOf(value)
Coverage.statementStart(13799)
        } else {
Coverage.ifStart(13800)
            onekeymap[key] = values + value
Coverage.statementStart(13801)
        }
Coverage.statementStart(13802)
    }
    private fun addToTwoKeysMap(twokeysmap: MutableMap<Pair<Long, Long>, LongArray>, key1: Long, key2: Long, value: Long) {
Coverage.funStart(13803)
        val key = Pair(key1, key2)
Coverage.statementStart(13804)
        val values = twokeysmap[key]
Coverage.statementStart(13805)
        if (values == null) {
Coverage.ifStart(13806)
            twokeysmap[key] = longArrayOf(value)
Coverage.statementStart(13807)
        } else {
Coverage.ifStart(13808)
            twokeysmap[key] = values + value
Coverage.statementStart(13809)
        }
Coverage.statementStart(13810)
    }
    private fun distinctOneKeyMap(onekeymap: MutableMap<Long, Array<Pair<Long, Long>>>) {
Coverage.funStart(13811)
        for (entry in onekeymap) {
Coverage.forLoopStart(13812)
            entry.setValue(entry.value.toMutableSet().toTypedArray())
Coverage.statementStart(13813)
        }
Coverage.statementStart(13814)
    }
    private fun distinctTwoKeysMap(twokeysmap: MutableMap<Pair<Long, Long>, LongArray>) {
Coverage.funStart(13815)
        for (entry in twokeysmap) {
Coverage.forLoopStart(13816)
            entry.setValue(entry.value.toMutableSet().toLongArray())
Coverage.statementStart(13817)
        }
Coverage.statementStart(13818)
    }
}
