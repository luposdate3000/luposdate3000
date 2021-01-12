package lupos
import lupos.s00misc.DateHelperRelative
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EModifyTypeExt
import lupos.s00misc.File
import lupos.s00misc.JenaBugException
import lupos.s00misc.JenaWrapper
import lupos.s00misc.Luposdate3000Exception
import lupos.s00misc.MAX_TRIPLES_DURING_TEST
import lupos.s00misc.NotImplementedException
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.UnknownManifestException
import lupos.s00misc.XMLElement
import lupos.s00misc.XMLElementFromXML
import lupos.s00misc.parseFromAny
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.ParseError
import lupos.s02buildSyntaxTree.rdf.BlankNode
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s02buildSyntaxTree.rdf.ID_Triple
import lupos.s02buildSyntaxTree.rdf.IRI
import lupos.s02buildSyntaxTree.rdf.SimpleLiteral
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithDictionary
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.Query
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPValuesImportXML
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXMLElement
import lupos.s14endpoint.convertToOPBase
import lupos.s15tripleStoreDistributed.distributedTripleStore
import lupos.s16network.LuposdateEndpoint
import kotlin.jvm.JvmField
public open class SparqlTestSuite {
    public companion object {
        public const val testPersistence: Boolean = false
        @JvmField public val filterList: MutableList<String> = mutableListOf()
        @JvmField public var prefixDirectory: String = "."
        @JvmField public val enabledTestCases: List<String> = listOf("resources/myqueries/", "resources/bsbm/", "resources/btc/", "resources/sp2b/")
    }
    public /*suspend*/ fun testMain() {
        repeat(1) {
            println("Starting tests...")
            val (nr_t, nr_e) = parseManifestFile("$prefixDirectory/resources/sparql11-test-suite/", "manifest-all.ttl")
            println("Number of tests: $nr_t")
            println("Number of errors: $nr_e")
            val prefixes = enabledTestCases
            for (prefix in prefixes) {
                var lastinput: String? = null
                File(prefixDirectory + prefix + "config.csv").forEachLineSuspended { it2 ->
                    val line = it2.split(",")
                    if (line.size > 3) {
                        val triplesCount = line[0]
                        val queryFile = prefixDirectory + "/" + prefix + line[1]
                        var inputFile = prefixDirectory + "/" + prefix + line[2]
                        val outputFile = prefixDirectory + "/" + prefix + line[3]
                        if (!File(outputFile).exists()) {
                            try {
                                JenaWrapper.loadFromFile("/src/luposdate3000/$inputFile")
                                val jenaResult = JenaWrapper.execQuery(File(queryFile).readAsString())
                                val jenaXML = XMLElementFromXML()(jenaResult)!!
                                File(outputFile).printWriterSuspended { it3 ->
                                    it3.println(jenaXML.toPrettyString())
                                }
                            } catch (e: Throwable) {
                                println({ "TODO exception 39" })
                                e.printStackTrace()
                            } finally {
                                JenaWrapper.dropAll()
                            }
                        }
                        if (lastinput == inputFile) {
                            inputFile = "#keep-data#"
                        } else {
                            lastinput = inputFile
                        }
                        parseSPARQLAndEvaluate(false, "$queryFile-$triplesCount", true, queryFile, inputFile, outputFile, null, mutableListOf(), mutableListOf())
                    }
                }
            }
        }
    }
    private /*suspend*/ fun listMembers(data: SevenIndices, start: Long, f: /*suspend*/ (Long) -> Unit) {
        val rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
        val nil = rdf + "nil"
        val first = rdf + "first"
        val rest = rdf + "rest"
        val nilIri = Dictionary.IRI(nil)
        val firstIri = Dictionary.IRI(first)
        val restIri = Dictionary.IRI(rest)
        /*suspend*/ fun recursiveListMembers(current: Long) {
            data.sp(current, firstIri).forEach { f(it) }
            data.sp(current, restIri).forEach {
                if (it != nilIri) {
                    listMembers(data, it, f)
                }
            }
        }
        recursiveListMembers(start)
    }
    private fun readTurtleData(filename: String, consume_triple: (Long, Long, Long) -> Unit) {
        val ltit = LookAheadTokenIterator(lupos.s02buildSyntaxTree.turtle.TurtleScanner(LexerCharIterator(File(filename).readAsString())), 3)
        try {
            TurtleParserWithDictionary(consume_triple, ltit).turtleDoc()
        } catch (e: ParseError) {
            SanityCheck {
                e.printStackTrace()
            }
            SanityCheck.println { "Error in the following line:" }
            SanityCheck.println { e.lineNumber }
        }
    }
    private fun createSevenIndices(filename: String): SevenIndices {
        val data = SevenIndices()
        readTurtleData(filename, data::add)
        data.distinct()
        return data
    }
    private /*suspend*/ fun parseManifestFile(prefix: String, filename: String): Pair<Int, Int> {
        var numberOfErrors = 0
        var numberOfTests = 0
        SanityCheck.println { "Reading file $filename..." }
        val data = createSevenIndices(prefix + filename)
        val newprefix = prefix + filename.substringBeforeLast("/") + "/"
        val manifestEntries = data.po(Dictionary.IRI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"), Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#Manifest"))
        manifestEntries.forEach {
            // Are other manifest files included?
            val included = data.sp(it, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#include"))
            included.forEach { it2 ->
                // follow list of included manifest files:
                listMembers(data, it2) { it3 ->
                    val includedfile = Dictionary[it3]
                    if (includedfile != null) {
                        includedfile as IRI
                        val (nr_t, nr_e) = parseManifestFile(prefix, includedfile.iri)
                        numberOfTests += nr_t
                        numberOfErrors += nr_e
                    }
                }
            }
            // Now look for_ the tests:
            val tests = data.sp(it, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#entries"))
            tests.forEach { it2 ->
                // follow list of entries:
                listMembers(data, it2) { it3 ->
                    // for_ printing out the name:
                    val name = data.sp(it3, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#name"))
                    numberOfTests++
                    if (!testOneEntry(data, it3, newprefix)) {
                        numberOfErrors++
                    }
                }
            }
        }
        return Pair(numberOfTests, numberOfErrors)
    }
    private fun readFileOrNull(name: String?): String? {
        if (name == null) {
            return null
        }
        return File(name).readAsString()
    }
    private /*suspend*/ fun testOneEntry(data: SevenIndices, node: Long, prefix: String): Boolean {
        var testType: String? = null
        var comment: String? = null
        val features = mutableListOf<String>()
        var description: String? = null
        val names = mutableListOf<String>()
        var expectedResult = true
        var queryFile: String? = null
        var inputDataFile: String? = null
        var resultFile: String? = null
        val services = mutableListOf<MutableMap<String, String>>()
        val inputDataGraph = mutableListOf<MutableMap<String, String>>()
        val outputDataGraph = mutableListOf<MutableMap<String, String>>()
        data.s(node).forEach { (first, second) ->
            when ((Dictionary[first] as IRI).iri) {
                "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#result" -> {
                    when {
                        Dictionary[second] is IRI -> {
                            SanityCheck.check { resultFile == null }
                            resultFile = prefix + (Dictionary[second] as IRI).iri
                        }
                        Dictionary[second] is BlankNode -> {
                            data.s(second).forEach { (first, second) ->
                                when ((Dictionary[first] as IRI).iri) {
                                    "http://www.w3.org/2009/sparql/tests/test-update#data" -> {
                                        val graph = mutableMapOf<String, String>()
                                        graph["name"] = ""
                                        graph["filename"] = prefix + (Dictionary[second] as IRI).iri
                                        outputDataGraph.add(graph)
                                    }
                                    "http://www.w3.org/2009/sparql/tests/test-update#graphData" -> {
                                        val graph = mutableMapOf<String, String>()
                                        data.s(second).forEach { (first, second) ->
                                            when ((Dictionary[first] as IRI).iri) {
                                                "http://www.w3.org/2009/sparql/tests/test-update#graph" -> {
                                                    graph["filename"] = prefix + (Dictionary[second] as IRI).iri
                                                }
                                                "http://www.w3.org/2000/01/rdf-schema#label" -> {
                                                    graph["name"] = (Dictionary[second] as SimpleLiteral).content
                                                }
                                                else -> {
                                                    throw UnknownManifestException("SparqlTestSuite", (Dictionary[first] as IRI).iri + " # " + Dictionary[second])
                                                }
                                            }
                                        }
                                        outputDataGraph.add(graph)
                                    }
                                    "http://www.w3.org/2009/sparql/tests/test-update#result" -> {
                                        SanityCheck.println { "unknown-manifest::http://www.w3.org/2009/sparql/tests/test-update#result : " + (Dictionary[second] as IRI).iri }
                                    }
                                    else -> {
                                        throw UnknownManifestException("SparqlTestSuite", (Dictionary[first] as IRI).iri + " # " + Dictionary[second])
                                    }
                                }
                            }
                        }
                        else -> {
                            throw UnknownManifestException("SparqlTestSuite", (Dictionary[first] as IRI).iri + " # " + Dictionary[second])
                        }
                    }
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#action" -> {
                    when {
                        Dictionary[second] is IRI -> {
                            queryFile = prefix + (Dictionary[second] as IRI).iri
                        }
                        Dictionary[second] is BlankNode -> {
                            data.s(second).forEach { (first, second) ->
                                when ((Dictionary[first] as IRI).iri) {
                                    "http://www.w3.org/2001/sw/DataAccess/tests/test-query#data" -> {
                                        SanityCheck.check { inputDataFile == null }
                                        inputDataFile = prefix + (Dictionary[second] as IRI).iri
                                    }
                                    "http://www.w3.org/2001/sw/DataAccess/tests/test-query#query" -> {
                                        SanityCheck.check { queryFile == null }
                                        queryFile = prefix + (Dictionary[second] as IRI).iri
                                    }
                                    "http://www.w3.org/ns/sparql-service-description#entailmentRegime" -> {
                                        SanityCheck.println { "unknown-manifest::http://www.w3.org/ns/sparql-service-description#entailmentRegime " + Dictionary[second] }
                                    }
                                    "http://www.w3.org/ns/sparql-service-description#EntailmentProfile" -> {
                                        SanityCheck.println { "unknown-manifest::http://www.w3.org/ns/sparql-service-description#EntailmentProfile " + Dictionary[second] }
                                    }
                                    "http://www.w3.org/2001/sw/DataAccess/tests/test-query#graphData" -> {
                                        val graph = mutableMapOf<String, String>()
                                        graph["name"] = (Dictionary[second] as IRI).iri
                                        graph["filename"] = prefix + (Dictionary[second] as IRI).iri
                                        inputDataGraph.add(graph)
                                    }
                                    "http://www.w3.org/2001/sw/DataAccess/tests/test-query#serviceData" -> {
                                        val service = mutableMapOf<String, String>()
                                        data.s(second).forEach {
                                            when ((Dictionary[it.first] as IRI).iri) {
                                                "http://www.w3.org/2001/sw/DataAccess/tests/test-query#endpoint" -> {
                                                    service["name"] = (Dictionary[it.second] as IRI).iri
                                                }
                                                "http://www.w3.org/2001/sw/DataAccess/tests/test-query#data" -> {
                                                    service["filename"] = prefix + (Dictionary[it.second] as IRI).iri
                                                }
                                                else -> {
                                                    throw UnknownManifestException("SparqlTestSuite", (Dictionary[it.first] as IRI).iri + " # " + Dictionary[it.second])
                                                }
                                            }
                                        }
                                        if (service["filename"] != null) {
                                            services.add(service)
                                        }
                                    }
                                    "http://www.w3.org/2009/sparql/tests/test-update#request" -> {
                                        SanityCheck.check { queryFile == null }
                                        queryFile = prefix + (Dictionary[second] as IRI).iri
                                    }
                                    "http://www.w3.org/2009/sparql/tests/test-update#data" -> {
                                        SanityCheck.check { inputDataFile == null }
                                        inputDataFile = prefix + (Dictionary[second] as IRI).iri
                                    }
                                    "http://www.w3.org/2009/sparql/tests/test-update#graphData" -> {
                                        val graph = mutableMapOf<String, String>()
                                        data.s(second).forEach {
                                            when ((Dictionary[it.first] as IRI).iri) {
                                                "http://www.w3.org/2009/sparql/tests/test-update#graph" -> {
                                                    graph["filename"] = prefix + (Dictionary[it.second] as IRI).iri
                                                }
                                                "http://www.w3.org/2000/01/rdf-schema#label" -> {
                                                    graph["name"] = (Dictionary[it.second] as SimpleLiteral).content
                                                }
                                                else -> {
                                                    throw UnknownManifestException("SparqlTestSuite", (Dictionary[it.first] as IRI).iri + " # " + Dictionary[it.second])
                                                }
                                            }
                                        }
                                        inputDataGraph.add(graph)
                                    }
                                    else -> {
                                        throw UnknownManifestException("SparqlTestSuite", (Dictionary[first] as IRI).iri + " # " + Dictionary[second])
                                    }
                                }
                            }
                        }
                        else -> {
                            throw UnknownManifestException("SparqlTestSuite", (Dictionary[first] as IRI).iri + " # " + Dictionary[second])
                        }
                    }
                }
                "http://www.w3.org/1999/02/22-rdf-syntax-ns#type" -> {
                    SanityCheck.check { testType == null }
                    testType = (Dictionary[second] as IRI).iri
                    when ((Dictionary[second] as IRI).iri) {
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#CSVResultFormatTest" -> {
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#NegativeUpdateSyntaxTest11" -> {
                            expectedResult = false
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveSyntaxTest11" -> {
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveUpdateSyntaxTest11" -> {
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#ProtocolTest" -> {
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#QueryEvaluationTest" -> {
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#ServiceDescriptionTest" -> {
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#UpdateEvaluationTest" -> {
                        }
                        "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#NegativeSyntaxTest11" -> {
                            expectedResult = false
                        }
                        else -> {
                            throw UnknownManifestException("SparqlTestSuite", (Dictionary[first] as IRI).iri + " # " + (Dictionary[second] as IRI).iri)
                        }
                    }
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#name" -> {
                    names.add((Dictionary[second] as SimpleLiteral).content)
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#feature" -> {
                    features.add((Dictionary[second] as IRI).iri)
                }
                "http://www.w3.org/2000/01/rdf-schema#comment" -> {
                    SanityCheck.check { comment == null }
                    comment = (Dictionary[second] as SimpleLiteral).content
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approval" -> {
                    SanityCheck.println { "unknown-manifest::http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approval " + Dictionary[second] }
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approvedBy" -> {
                    SanityCheck.println { "unknown-manifest::http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approvedBy " + Dictionary[second] }
                }
                "http://www.w3.org/2000/01/rdf-schema#seeAlso" -> {
                    SanityCheck.println { "unknown-manifest::http://www.w3.org/2000/01/rdf-schema#seeAlso " + (Dictionary[second] as IRI).iri }
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-query#queryForm" -> {
                    SanityCheck.println { "unknown-manifest::http://www.w3.org/2001/sw/DataAccess/tests/test-query#queryForm " + (Dictionary[second] as IRI).iri }
                }
                "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#description" -> {
                    SanityCheck.check { description == null }
                    description = (Dictionary[second] as SimpleLiteral).content
                }
                else -> {
                    throw UnknownManifestException("SparqlTestSuite", (Dictionary[first] as IRI).iri + " # " + Dictionary[second])
                }
            }
        }
        SanityCheck.println { "testType : $testType" }
        SanityCheck.println { "names : $names" }
        SanityCheck.println { "comment : $comment" }
        SanityCheck.println { "description : $description" }
        SanityCheck.println { "features : $features" }
        SanityCheck.println { "inputDataGraph : $inputDataGraph" }
        SanityCheck.println { "outputDataGraph : $outputDataGraph" }
        SanityCheck.println { "expectedResult : $expectedResult" }
        SanityCheck.println { "queryFile : $queryFile" }
        SanityCheck.println { "inputDataFile : $inputDataFile" }
        SanityCheck.println { "resultFile : $resultFile" }
        SanityCheck.println { "services : $services" }
        if (queryFile == null) {
            return true
        }
        lastTripleCount = 0 // dont apply during w3c-tests
        val success = parseSPARQLAndEvaluate(true, names.first(), expectedResult, queryFile!!, inputDataFile, resultFile, services, inputDataGraph, outputDataGraph)
        return success == expectedResult
    }
    @JvmField
    public var lastTripleCount: Int = 0
    @OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
    public open /*suspend*/ fun parseSPARQLAndEvaluate(executeJena: Boolean, testName: String, expectedResult: Boolean, queryFile: String, inputDataFileName: String?, resultDataFileName: String?, services: List<Map<String, String>>?, inputDataGraph: MutableList<MutableMap<String, String>>, outputDataGraph: MutableList<MutableMap<String, String>>): Boolean {
//        if (!testName.contains("resources")) {
//            return true
//        }
        if (filterList.size > 0 && !filterList.contains(testName)) {
            println({ "'$testName' not in WhiteList of Unit-Tests" })
            return true
        } else {
            println({ "'$testName' is in WhiteList of Unit-Tests" })
        }
        File("log/storetest").mkdirs()
        var ignoreJena = !executeJena
        val timer = DateHelperRelative.markNow()
        try {
            val toParse = readFileOrNull(queryFile)!!
            if (toParse.contains("service", true)) {
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Failed(Service)")
                return false
            }
            val resultData = readFileOrNull(resultDataFileName)
            if (inputDataFileName != "#keep-data#") {
                val query2 = Query()
                query2.setWorkingDirectory(queryFile.substring(0, queryFile.lastIndexOf("/")))
                distributedTripleStore.getLocalStore().getDefaultGraph(query2).clear()
                for (g in distributedTripleStore.getGraphNames()) {
                    distributedTripleStore.dropGraph(query2, g)
                }
                distributedTripleStore.commit(query2)
                query2.commited = true
                nodeGlobalDictionary.clear()
                JenaWrapper.dropAll()
                val inputData = readFileOrNull(inputDataFileName)
                if (inputData != null && inputDataFileName != null) {
                    lastTripleCount = inputData.split("\n").size
                    if (MAX_TRIPLES_DURING_TEST in 1 until lastTripleCount) {
                        println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                        println("----------Success(Skipped)")
                        return true
                    }
                    println("InputData Graph[] Original")
                    println(inputData)
                    println("----------Input Data Graph[]")
                    val xmlQueryInput = XMLElement.parseFromAny(inputData, inputDataFileName)!!
                    if (inputDataFileName.endsWith(".ttl") || inputDataFileName.endsWith(".n3")) {
                        val query = Query()
                        query.setWorkingDirectory(queryFile.substring(0, queryFile.lastIndexOf("/")))
                        LuposdateEndpoint.importTurtleFiles(inputDataFileName, mutableMapOf())
                        val bulkSelect = distributedTripleStore.getDefaultGraph(query).getIterator(arrayOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), EIndexPatternExt.SPO, Partition())
                        val xmlGraphBulk = QueryResultToXMLElement.toXML(bulkSelect)
                        if (!xmlGraphBulk.myEqualsUnclean(xmlQueryInput, true, true, true)) {
                            println("test xmlQueryInput :: " + xmlQueryInput.toPrettyString())
                            println("test xmlGraphBulk :: " + xmlGraphBulk.toPrettyString())
                            println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                            println("----------Failed(BulkImport)")
                            return false
                        }
                    } else {
                        val query = Query()
                        query.setWorkingDirectory(queryFile.substring(0, queryFile.lastIndexOf("/")))
                        val tmp = POPValuesImportXML(query, listOf("s", "p", "o"), xmlQueryInput).evaluate(Partition())
                        distributedTripleStore.getDefaultGraph(query).modify(arrayOf(tmp.columns["s"]!!, tmp.columns["p"]!!, tmp.columns["o"]!!), EModifyTypeExt.INSERT)
                        distributedTripleStore.commit(query)
                        query.commited = true
                    }
                    println("test InputData Graph[] ::" + xmlQueryInput.toPrettyString())
                    try {
                        if (!ignoreJena) {
                            JenaWrapper.loadFromFile("/src/luposdate3000/$inputDataFileName")
                        }
                    } catch (e: JenaBugException) {
                        println({ e.message })
                        ignoreJena = true
                    } catch (e: Throwable) {
                        println({ "TODO exception 41" })
                        e.printStackTrace()
                        ignoreJena = true
                    }
                }
                inputDataGraph.forEach {
                    println("InputData Graph[${it["name"]}] Original")
                    val inputData2 = readFileOrNull(it["filename"])
                    println(inputData2)
                    println("----------Input Data Graph[${it["name"]}]")
                    val xmlQueryInput = XMLElement.parseFromAny(inputData2!!, it["filename"]!!)!!
                    val query = Query()
                    query.setWorkingDirectory(queryFile.substring(0, queryFile.lastIndexOf("/")))
                    val tmp = POPValuesImportXML(query, listOf("s", "p", "o"), xmlQueryInput).evaluate(Partition())
                    distributedTripleStore.getNamedGraph(query, it["name"]!!).modify(arrayOf(tmp.columns["s"]!!, tmp.columns["p"]!!, tmp.columns["o"]!!), EModifyTypeExt.INSERT)
                    distributedTripleStore.commit(query)
                    query.commited = true
                    println("test Input Graph[${it["name"]!!}] :: " + xmlQueryInput.toPrettyString())
                    try {
                        if (!ignoreJena) {
                            JenaWrapper.loadFromFile("/src/luposdate3000/" + it["filename"]!!, it["name"]!!)
                        }
                    } catch (e: JenaBugException) {
                        println({ e.message })
                        ignoreJena = true
                    } catch (e: Throwable) {
                        println({ "TODO exception 42" })
                        e.printStackTrace()
                        ignoreJena = true
                    }
                }
                if (services != null) {
                    for (s in services) {
                        val n = s["name"]!!
                        val fn = s["filename"]!!
                        val fc = readFileOrNull(fn)!!
// TODO
                    }
                }
            } else {
                if (MAX_TRIPLES_DURING_TEST in 1 until lastTripleCount) {
                    println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                    println("----------Success(Skipped)")
                    return true
                }
            }
            val testName2 = "[^a-zA-Z0-9]".toRegex().replace(testName, "-")
            val query = Query()
            query.setWorkingDirectory(queryFile.substring(0, queryFile.lastIndexOf("/")))
            var res: Boolean
            SanityCheck.println { "----------String Query" }
            println(toParse)
            SanityCheck.println { "----------Abstract Syntax Tree" }
            val lcit = LexerCharIterator(toParse)
            val tit = TokenIteratorSPARQLParser(lcit)
            val ltit = LookAheadTokenIterator(tit, 3)
            val parser = SPARQLParser(ltit)
            val astNode = parser.expr()
            SanityCheck.println { astNode }
            SanityCheck.println { "----------Logical Operator Graph" }
            val lopNode = astNode.visit(OperatorGraphVisitor(query))
            File("log/$testName2-Logical-Operator-Graph.tex").printWriterSuspended {
                it.println(OperatorGraphToLatex(lopNode.toXMLElement().toString(), testName2))
            }
            SanityCheck.check({ lopNode == lopNode.cloneOP() }, { lopNode.toString() + " - " + lopNode.cloneOP().toString() })
            SanityCheck.suspended {
                val x = lopNode.toXMLElement().toPrettyString()
                SanityCheck.println { x }
            }
            SanityCheck.println { "----------Logical Operator Graph optimized" }
            val lopNode2 = LogicalOptimizer(query).optimizeCall(lopNode)
            SanityCheck.check { lopNode2 == lopNode2.cloneOP() }
            File("log/$testName2-Logical-Operator-Graph-Optimized.tex").printWriterSuspended {
                it.println(OperatorGraphToLatex(lopNode2.toXMLElement().toString(), testName2))
            }
            SanityCheck.suspended {
                val x = lopNode2.toXMLElement().toPrettyString()
                SanityCheck.println { x }
            }
            SanityCheck.println { "----------Physical Operator Graph" }
            val popOptimizer = PhysicalOptimizer(query)
            val popNode = popOptimizer.optimizeCall(lopNode2)
            SanityCheck.check({ popNode == popNode.cloneOP() }, { popNode.toString() + " - " + popNode.cloneOP().toString() })
            SanityCheck { popNode.toSparqlQuery() }
            File("log/$testName2-Physical-Operator-Graph.tex").printWriterSuspended {
                it.println(OperatorGraphToLatex(popNode.toXMLElement().toString(), testName2))
            }
            SanityCheck.suspended {
                val x = popNode.toXMLElement().toPrettyString()
                SanityCheck.println { x }
            }
            var xmlQueryResult: XMLElement? = null
            if (outputDataGraph.isNotEmpty() || (resultData != null && resultDataFileName != null)) {
                SanityCheck.println { "----------Query Result" }
                xmlQueryResult = QueryResultToXMLElement.toXML(popNode)
                SanityCheck.println { "test xmlQueryResult :: " + xmlQueryResult.toPrettyString() }
                distributedTripleStore.commit(query)
                query.commited = true
            }
            var verifiedOutput = false
            outputDataGraph.forEach {
                val outputData = readFileOrNull(it["filename"])
                val xmlGraphTarget = XMLElement.parseFromAny(outputData!!, it["filename"]!!)!!
                val tmp = distributedTripleStore.getNamedGraph(query, it["name"]!!).getIterator(arrayOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), EIndexPatternExt.SPO, Partition())
                val xmlGraphActual = QueryResultToXMLElement.toXML(tmp)
                if (!xmlGraphTarget.myEqualsUnclean(xmlGraphActual, true, true, true)) {
                    println("OutputData Graph[${it["name"]}] Original")
                    println(outputData)
                    println("----------Verify Output Data Graph[${it["name"]}] ... target,actual")
                    println("test xmlGraphTarget :: " + xmlGraphTarget.toPrettyString())
                    println("test xmlGraphActual :: " + xmlGraphActual.toPrettyString())
                    println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                    println("----------Failed(PersistentStore Graph)")
                    return false
                } else {
                    SanityCheck.println { "OutputData Graph[${it["name"]}] Original" }
                    SanityCheck.println { outputData }
                    SanityCheck.println { "----------Verify Output Data Graph[${it["name"]}] ... target,actual" }
                    SanityCheck.println { "test xmlGraphTarget :: " + xmlGraphTarget.toPrettyString() }
                    SanityCheck.println { "test xmlGraphActual :: " + xmlGraphActual.toPrettyString() }
                }
                verifiedOutput = true
            }
            if (resultData != null && resultDataFileName != null) {
                SanityCheck.println { "----------Target Result" }
                val xmlQueryTarget = XMLElement.parseFromAny(resultData, resultDataFileName)!!
                SanityCheck.println { "test xmlQueryTarget :: " + xmlQueryTarget.toPrettyString() }
                SanityCheck.println { resultData }
                if (!ignoreJena) {
                    try {
                        val jenaResult = JenaWrapper.execQuery(toParse)
                        val jenaXML = XMLElementFromXML()(jenaResult)
// println("test xmlJena >>>>>"+jenaResult+"<<<<<")
                        if (jenaXML != null && !jenaXML.myEqualsUnclean(xmlQueryResult, true, true, true)) {
                            println("----------Verify Output Jena jena,actual")
                            println("test jenaOriginal :: $jenaResult")
                            println("test xmlJena :: " + jenaXML.toPrettyString())
                            println("test xmlActual :: " + xmlQueryResult!!.toPrettyString())
                            println("test xmlTarget :: " + xmlQueryTarget.toPrettyString())
                            println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                            println("----------Failed(Jena)")
                            return false
                        }
                    } catch (e: JenaBugException) {
                        println({ e.message })
                        ignoreJena = true
                    } catch (e: Throwable) {
                        println({ "TODO exception 43" })
                        e.printStackTrace()
                        ignoreJena = true
                    }
                }
                res = xmlQueryResult!!.myEquals(xmlQueryTarget)
                if (res) {
                    val xmlPOP = popNode.toXMLElement()
                    val query4 = Query()
                    query4.setWorkingDirectory(queryFile.substring(0, queryFile.lastIndexOf("/")))
                    val popNodeRecovered = XMLElement.convertToOPBase(query4, xmlPOP)
                    SanityCheck.println { xmlPOP.toPrettyString() }
                    SanityCheck.suspended {
                        val x = popNodeRecovered.toXMLElement().toPrettyString()
                        SanityCheck.println { x }
                    }
                    val xmlQueryResultRecovered = QueryResultToXMLElement.toXML(popNodeRecovered)
                    distributedTripleStore.commit(query4)
                    query4.commited = true
                    SanityCheck.println { "test xmlQueryResultRecovered :: " + xmlQueryResultRecovered.toPrettyString() }
                    if (xmlQueryResultRecovered.myEqualsUnclean(xmlQueryResult, true, true, true)) {
                        if (expectedResult) {
                            println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                            println("----------Success")
                        } else {
                            println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                            println("----------Failed(expectFalse)")
                        }
                    } else {
                        println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                        println("----------Failed(RecoverFromXMLOperatorGraph)")
                        res = false
                    }
                } else {
                    val containsOrderBy = toParse.contains("ORDER", true)
                    val correctIfIgnoreOrderBy = xmlQueryResult.myEqualsUnclean(xmlQueryTarget, false, false, true)
                    val correctIfIgnoreString = xmlQueryResult.myEqualsUnclean(xmlQueryTarget, true, false, true)
                    val correctIfIgnoreNumber = xmlQueryResult.myEqualsUnclean(xmlQueryTarget, true, true, true)
                    val correctIfIgnoreAllExceptOrder = xmlQueryResult.myEqualsUnclean(xmlQueryTarget, true, true, false)
                    if (correctIfIgnoreNumber) {
                        if (expectedResult) {
                            if (containsOrderBy) {
                                if (correctIfIgnoreAllExceptOrder) {
                                    println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                                    println("----------Success")
                                } else {
                                    println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                                    println("----------Success(Unordered)")
                                }
                            } else if (correctIfIgnoreOrderBy) {
                                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                                println("----------Success")
                            } else if (correctIfIgnoreString) {
                                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                                println("----------Success(String)")
                            } else if (correctIfIgnoreNumber) {
                                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                                println("----------Success(Number & String)")
                            } else {
                                SanityCheck.checkUnreachable()
                            }
                        } else {
                            println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                            println("----------Failed(expectFalse,Simplified)")
                        }
                    } else {
                        if (expectedResult) {
                            println("test xmlQueryTarget :: " + xmlQueryTarget.toPrettyString())
                            println("test xmlQueryResult :: " + xmlQueryResult.toPrettyString())
                            println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                            println("----------Failed(Incorrect)")
                        } else {
                            println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                            println("----------Success(ExpectFalse)")
                        }
                    }
                }
                return res
            } else {
                if (verifiedOutput) {
                    if (expectedResult) {
                        println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                        println("----------Success(Graph)")
                    } else {
                        println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                        println("----------Failed(ExpectFalse,Graph)")
                    }
                } else {
                    if (expectedResult) {
                        println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                        println("----------Success(Syntax)")
                    } else {
                        println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                        println("----------Failed(ExpectFalse,Syntax)")
                    }
                }
                return expectedResult
            }
        } catch (e: ParseError) {
            if (expectedResult) {
                e.printStackTrace()
                SanityCheck.println { e }
                SanityCheck.println { "Error in the following line:" }
                SanityCheck.println { e.lineNumber }
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Failed(ParseError)")
            } else {
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Success(ExpectFalse,ParseError)")
            }
            return false
        } catch (e: NotImplementedException) {
            println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
            println("----------Failed(NotImplemented)")
            e.printStackTrace()
            return false
        } catch (e: Luposdate3000Exception) {
            if (expectedResult) {
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Failed(${e.classname})")
                e.printStackTrace()
            } else {
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Success(ExpectFalse,${e.classname})")
            }
            return false
        } catch (e: Throwable) {
            println({ "TODO exception 44" })
            e.printStackTrace()
            if (expectedResult) {
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Failed(Throwable)")
                e.printStackTrace()
                throw e
            } else {
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Success(ExpectFalse,Throwable)")
                e.printStackTrace()
            }
            return false
        }
    }
}
public class SevenIndices {
    private val s = mutableMapOf<Long, Array<Pair<Long, Long>>>()
    private val p = mutableMapOf<Long, Array<Pair<Long, Long>>>()
    private val o = mutableMapOf<Long, Array<Pair<Long, Long>>>()
    private val sp = mutableMapOf<Pair<Long, Long>, LongArray>()
    private val so = mutableMapOf<Pair<Long, Long>, LongArray>()
    private val po = mutableMapOf<Pair<Long, Long>, LongArray>()
    @JvmField
    public val spo: MutableSet<ID_Triple> = mutableSetOf()
    public fun s(key: Long): Array<Pair<Long, Long>> = this.s[key] ?: arrayOf()
    public fun o(key: Long): Array<Pair<Long, Long>> = this.o[key] ?: arrayOf()
    public fun sp(key1: Long, key2: Long): LongArray = this.sp[Pair(key1, key2)] ?: longArrayOf()
    public fun po(key1: Long, key2: Long): LongArray = this.po[Pair(key1, key2)] ?: longArrayOf()
    public fun distinct() {
        distinctOneKeyMap(this.s)
        distinctOneKeyMap(this.p)
        distinctOneKeyMap(this.o)
        distinctTwoKeysMap(this.sp)
        distinctTwoKeysMap(this.so)
        distinctTwoKeysMap(this.po)
        // duplicates are already eliminated in this.spo!
    }
    public fun add(triple_s: Long, triple_p: Long, triple_o: Long) {
        addToOneKeyMap(this.s, triple_s, triple_p, triple_o)
        addToOneKeyMap(this.p, triple_p, triple_s, triple_o)
        addToOneKeyMap(this.o, triple_o, triple_s, triple_p)
        addToTwoKeysMap(this.sp, triple_s, triple_p, triple_o)
        addToTwoKeysMap(this.so, triple_s, triple_o, triple_p)
        addToTwoKeysMap(this.po, triple_p, triple_o, triple_s)
        this.spo += ID_Triple(triple_s, triple_p, triple_o)
    }
    private fun addToOneKeyMap(onekeymap: MutableMap<Long, Array<Pair<Long, Long>>>, key: Long, value1: Long, value2: Long) {
        val values = onekeymap[key]
        val value = Pair(value1, value2)
        if (values == null) {
            onekeymap[key] = arrayOf(value)
        } else {
            onekeymap[key] = values + value
        }
    }
    private fun addToTwoKeysMap(twokeysmap: MutableMap<Pair<Long, Long>, LongArray>, key1: Long, key2: Long, value: Long) {
        val key = Pair(key1, key2)
        val values = twokeysmap[key]
        if (values == null) {
            twokeysmap[key] = longArrayOf(value)
        } else {
            twokeysmap[key] = values + value
        }
    }
    private fun distinctOneKeyMap(onekeymap: MutableMap<Long, Array<Pair<Long, Long>>>) {
        for (entry in onekeymap) {
            entry.setValue(entry.value.toMutableSet().toTypedArray())
        }
    }
    private fun distinctTwoKeysMap(twokeysmap: MutableMap<Pair<Long, Long>, LongArray>) {
        for (entry in twokeysmap) {
            entry.setValue(entry.value.toMutableSet().toLongArray())
        }
    }
}
