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
package lupos.test
import lupos.endpoint.LuposdateEndpoint
import lupos.endpoint.MemoryTableFromXML
import lupos.jena_wrapper.JenaWrapper
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.operator.factory.XMLElementToOPBase
import lupos.operator.physical.noinput.POPValues2
import lupos.optimizer.ast.OperatorGraphVisitor
import lupos.optimizer.logical.LogicalOptimizer
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.parser.sparql.ASTSparqlDoc
import lupos.parser.sparql.SparqlParser
import lupos.parser.turtle.TurtleParser
import lupos.result_format.QueryResultToMemoryTable
import lupos.shared.*
import lupos.shared.PartitionHelper
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import lupos.shared.inline.MyStringStream
import lupos.shared.myPrintStackTrace
import lupos.shared.myPrintStackTraceAndThrowAgain
import kotlin.jvm.JvmField

public open class SparqlTestSuite {
    public companion object {
        @JvmField
        public val filterList: MutableList<String> = mutableListOf()

        @JvmField
        public var prefixDirectory: String = "."

        @JvmField
        public val enabledTestCases: List<String> = listOf("resources/myqueries/", "resources/bsbm/", "resources/btc/", "resources/sp2b/")
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
                File(prefixDirectory + prefix + "config.csv").forEachLine { it2 ->
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
                                File(outputFile).withOutputStream { it3 ->
                                    it3.println(jenaXML.toPrettyString())
                                }
                            } catch (e: Throwable) {
                                e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:77"/*SOURCE_FILE_END*/)
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

    private /*suspend*/ fun listMembers(data: SevenIndices, start: String, f: /*suspend*/ (String) -> Unit) {
        /*suspend*/ fun recursiveListMembers(current: String) {
            data.sp(current, ("<http://www.w3.org/1999/02/22-rdf-syntax-ns#first>")).forEach { f(it) }
            data.sp(current, ("<http://www.w3.org/1999/02/22-rdf-syntax-ns#rest>")).forEach {
                if (it != ("<http://www.w3.org/1999/02/22-rdf-syntax-ns#nil>")) {
                    listMembers(data, it, f)
                }
            }
        }
        recursiveListMembers(start)
    }

    private fun readTurtleData(filename: String, consume_triple: (String, String, String) -> Unit) {
        try {
            val parserObject = TurtleParser(MyStringStream(File(filename).readAsString()))
            parserObject.consumeTriple = { s, p, o ->
                consume_triple((s), (p), (o))
            }
            parserObject.parserDefinedParse()
        } catch (e: Throwable) {
            try {
                throw Exception(filename, e)
            } catch (e2: Throwable) {
                e2.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:117"/*SOURCE_FILE_END*/)
            }
        }
    }

    private fun createSevenIndices(filename: String): SevenIndices {
        val data = SevenIndices()
        readTurtleData(filename, data::add)
        data.distinct()
        return data
    }

    private /*suspend*/ fun parseManifestFile(prefix: String, filename: String): Pair<Int, Int> {
        try {
            var numberOfErrors = 0
            var numberOfTests = 0
            SanityCheck.println { "Reading file $filename..." }
            val data = createSevenIndices(prefix + filename)
            val newprefix = prefix + filename.substringBeforeLast("/") + "/"
            val manifestEntries = data.po(("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#Manifest>"))
            manifestEntries.forEach {
                // Are other manifest files included?
                val included = data.sp(it, ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#include>"))
                included.forEach { it2 ->
                    // follow list of included manifest files:
                    listMembers(data, it2) { it3 ->
                        val includedfile = it3
                        if (includedfile != null) {
                            val (nr_t, nr_e) = parseManifestFile(prefix, extractValueFromIriOrString(includedfile))
                            numberOfTests += nr_t
                            numberOfErrors += nr_e
                        }
                    }
                }
                // Now look for_ the tests:
                val tests = data.sp(it, ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#entries>"))
                tests.forEach { it2 ->
                    // follow list of entries:
                    listMembers(data, it2) { it3 ->
                        // for_ printing out the name:
//                    val name = data.sp(it3, ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#name>"))
                        numberOfTests++
                        if (!testOneEntry(data, it3, newprefix)) {
                            numberOfErrors++
                        }
                    }
                }
            }
            return Pair(numberOfTests, numberOfErrors)
        } catch (e: Throwable) {
            throw Exception(prefix + filename, e)
        }
    }

    private fun readFileOrNull(name: String?): String? {
        if (name == null) {
            return null
        }
        return File(name).readAsString()
    }

    private fun extractValueFromIriOrString(s: String): String {
        return if (s.startsWith("\"") && s.endsWith("\"")) {
            s.drop(1).dropLast(1)
        } else if (s.startsWith("\"") && s.endsWith("\"^^<http://www.w3.org/2001/XMLSchema#string>")) {
            s.drop(1).dropLast("\"^^<http://www.w3.org/2001/XMLSchema#string>".length)
        } else if (s.startsWith("<") && s.endsWith(">") && !s.contains("#")) {
            s.drop(1).dropLast(1)
        } else {
            TODO(s)
        }
    }
    private fun extractValueFromIriOrString2(s: String): String {
        return if (s.startsWith("\"") && s.endsWith("\"")) {
            s.drop(1).dropLast(1)
        } else if (s.startsWith("\"") && s.endsWith("\"^^<http://www.w3.org/2001/XMLSchema#string>")) {
            s.drop(1).dropLast("\"^^<http://www.w3.org/2001/XMLSchema#string>".length)
        } else if (s.startsWith("<") && s.endsWith(">") && !s.contains("#")) {
            s.drop(1).dropLast(1)
        } else {
            s.drop(s.indexOf("#") + 1).dropLast(1)
        }
    }

    private /*suspend*/ fun testOneEntry(data: SevenIndices, node: String, prefix: String): Boolean {
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
            when (first) {
                ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#result>") -> {
                    when {
                        second.startsWith("<") && second.endsWith(">") -> {
                            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:219"/*SOURCE_FILE_END*/ }, { resultFile == null })
                            resultFile = prefix + extractValueFromIriOrString2(second)
                        }
                        second.startsWith("_:") -> {
                            data.s(second).forEach { (first, second) ->
                                when (first) {
                                    ("<http://www.w3.org/2009/sparql/tests/test-update#data>") -> {
                                        val graph = mutableMapOf<String, String>()
                                        graph["name"] = ""
                                        graph["filename"] = prefix + extractValueFromIriOrString2(second)
                                        outputDataGraph.add(graph)
                                    }
                                    ("<http://www.w3.org/2009/sparql/tests/test-update#graphData>") -> {
                                        val graph = mutableMapOf<String, String>()
                                        data.s(second).forEach { (first, second) ->
                                            when (first) {
                                                ("<http://www.w3.org/2009/sparql/tests/test-update#graph>") -> {
                                                    graph["filename"] = prefix + extractValueFromIriOrString2(second)
                                                }
                                                ("<http://www.w3.org/2000/01/rdf-schema#label>") -> {
                                                    graph["name"] = extractValueFromIriOrString2(second)
                                                }
                                                else -> {
                                                    TODO("SparqlTestSuite" + first + " # " + second)
                                                }
                                            }
                                        }
                                        outputDataGraph.add(graph)
                                    }
                                    ("<http://www.w3.org/2009/sparql/tests/test-update#result>") -> {
                                        SanityCheck.println { "unknown-manifest::http://www.w3.org/2009/sparql/tests/test-update#result : " + second }
                                    }
                                    else -> {
                                        TODO("SparqlTestSuite" + first + " # " + second)
                                    }
                                }
                            }
                        }
                        else -> {
                            TODO("SparqlTestSuite" + first + " # " + second)
                        }
                    }
                }
                ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#action>") -> {
                    when {
                        second.startsWith("<") && second.endsWith(">") -> {
                            queryFile = prefix + extractValueFromIriOrString2(second)
                        }
                        second.startsWith("_:") -> {
                            data.s(second).forEach { (first, second) ->
                                when (first) {
                                    ("<http://www.w3.org/2001/sw/DataAccess/tests/test-query#data>") -> {
                                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:271"/*SOURCE_FILE_END*/ }, { inputDataFile == null })
                                        inputDataFile = prefix + extractValueFromIriOrString2(second)
                                    }
                                    ("<http://www.w3.org/2001/sw/DataAccess/tests/test-query#query>") -> {
                                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:275"/*SOURCE_FILE_END*/ }, { queryFile == null })
                                        queryFile = prefix + extractValueFromIriOrString2(second)
                                    }
                                    ("<http://www.w3.org/ns/sparql-service-description#entailmentRegime>") -> {
                                        SanityCheck.println { "unknown-manifest::http://www.w3.org/ns/sparql-service-description#entailmentRegime " + second }
                                    }
                                    ("<http://www.w3.org/ns/sparql-service-description#EntailmentProfile>") -> {
                                        SanityCheck.println { "unknown-manifest::http://www.w3.org/ns/sparql-service-description#EntailmentProfile " + second }
                                    }
                                    ("<http://www.w3.org/2001/sw/DataAccess/tests/test-query#graphData>") -> {
                                        val graph = mutableMapOf<String, String>()
                                        graph["name"] = extractValueFromIriOrString2(second)
                                        graph["filename"] = prefix + extractValueFromIriOrString2(second)
                                        inputDataGraph.add(graph)
                                    }
                                    ("<http://www.w3.org/2001/sw/DataAccess/tests/test-query#serviceData>") -> {
                                        val service = mutableMapOf<String, String>()
                                        data.s(second).forEach {
                                            when (it.first) {
                                                ("<http://www.w3.org/2001/sw/DataAccess/tests/test-query#endpoint>") -> {
                                                    service["name"] = extractValueFromIriOrString2(it.second)
                                                }
                                                ("<http://www.w3.org/2001/sw/DataAccess/tests/test-query#data>") -> {
                                                    service["filename"] = prefix + extractValueFromIriOrString2(it.second)
                                                }
                                                else -> {
                                                    TODO("SparqlTestSuite" + it.first + " # " + it.second)
                                                }
                                            }
                                        }
                                        if (service["filename"] != null) {
                                            services.add(service)
                                        }
                                    }
                                    ("<http://www.w3.org/2009/sparql/tests/test-update#request>") -> {
                                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:310"/*SOURCE_FILE_END*/ }, { queryFile == null })
                                        queryFile = prefix + extractValueFromIriOrString2(second)
                                    }
                                    ("<http://www.w3.org/2009/sparql/tests/test-update#data>") -> {
                                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:314"/*SOURCE_FILE_END*/ }, { inputDataFile == null })
                                        inputDataFile = prefix + extractValueFromIriOrString2(second)
                                    }
                                    ("<http://www.w3.org/2009/sparql/tests/test-update#graphData>") -> {
                                        val graph = mutableMapOf<String, String>()
                                        data.s(second).forEach {
                                            when (it.first) {
                                                ("<http://www.w3.org/2009/sparql/tests/test-update#graph>") -> {
                                                    graph["filename"] = prefix + extractValueFromIriOrString2(it.second)
                                                }
                                                ("<http://www.w3.org/2000/01/rdf-schema#label>") -> {
                                                    graph["name"] = extractValueFromIriOrString2(it.second)
                                                }
                                                else -> {
                                                    TODO("SparqlTestSuite" + it.first + " # " + it.second)
                                                }
                                            }
                                        }
                                        inputDataGraph.add(graph)
                                    }
                                    else -> {
                                        TODO("SparqlTestSuite" + first + " # " + second)
                                    }
                                }
                            }
                        }
                        else -> {
                            TODO("SparqlTestSuite" + first + " # " + second)
                        }
                    }
                }
                ("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>") -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:346"/*SOURCE_FILE_END*/ }, { testType == null })
                    testType = second
                    when (second) {
                        ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#CSVResultFormatTest>") -> {
                        }
                        ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#NegativeUpdateSyntaxTest11>") -> {
                            expectedResult = false
                        }
                        ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveSyntaxTest11>") -> {
                        }
                        ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveUpdateSyntaxTest11>") -> {
                        }
                        ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#ProtocolTest>") -> {
                        }
                        ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#QueryEvaluationTest>") -> {
                        }
                        ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#ServiceDescriptionTest>") -> {
                        }
                        ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#UpdateEvaluationTest>") -> {
                        }
                        ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#NegativeSyntaxTest11>") -> {
                            expectedResult = false
                        }
                        else -> {
                            TODO("SparqlTestSuite" + first + " # " + second)
                        }
                    }
                }
                ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#name>") -> {
                    names.add(extractValueFromIriOrString(second))
                }
                ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#feature>") -> {
                    features.add(second)
                }
                ("<http://www.w3.org/2000/01/rdf-schema#comment>") -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:381"/*SOURCE_FILE_END*/ }, { comment == null })
                    comment = extractValueFromIriOrString(second)
                }
                ("<http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approval>") -> {
                    SanityCheck.println { "unknown-manifest::http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approval " + second }
                }
                ("<http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approvedBy>") -> {
                    SanityCheck.println { "unknown-manifest::http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approvedBy " + second }
                }
                ("<http://www.w3.org/2000/01/rdf-schema#seeAlso>") -> {
                    SanityCheck.println { "unknown-manifest::http://www.w3.org/2000/01/rdf-schema#seeAlso " + second }
                }
                ("<http://www.w3.org/2001/sw/DataAccess/tests/test-query#queryForm>") -> {
                    SanityCheck.println { "unknown-manifest::http://www.w3.org/2001/sw/DataAccess/tests/test-query#queryForm " + second }
                }
                ("<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#description>") -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:397"/*SOURCE_FILE_END*/ }, { description == null })
                    description = extractValueFromIriOrString(second)
                }
                else -> {
                    TODO("SparqlTestSuite" + first + " # " + second)
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

    /*suspend*/ @OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
    public open fun parseSPARQLAndEvaluate(executeJena: Boolean, testName: String, expectedResult: Boolean, queryFile: String, inputDataFileName: String?, resultDataFileName: String?, services: List<Map<String, String>>?, inputDataGraph: MutableList<MutableMap<String, String>>, outputDataGraph: MutableList<MutableMap<String, String>>): Boolean {
//        if (!testName.contains("resources")) {
//            return true
//        }
        if (filterList.size > 0 && !filterList.contains(testName)) {
            println({ "'$testName' not in WhiteList of Unit-Tests" })
            return true
        } else {
            println({ "'$testName' is in WhiteList of Unit-Tests" })
        }
        var ignoreJena = !executeJena
        val timer = DateHelperRelative.markNow()
        val instance = LuposdateEndpoint.initialize()

        try {
            val toParse = readFileOrNull(queryFile)!!
            if (toParse.contains("service", true)) {
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Failed(Service)")
                LuposdateEndpoint.close(instance)
                return false
            }
            val resultData = readFileOrNull(resultDataFileName)
            if (inputDataFileName != "#keep-data#") {
                val query2 = Query(instance)
                query2.setWorkingDirectory(queryFile.substring(0, queryFile.lastIndexOf("/")))
                instance.tripleStoreManager!!.clearGraph(query2, TripleStoreManager.DEFAULT_GRAPH_NAME)
                for (g in instance.tripleStoreManager!!.getGraphNames()) {
                    instance.tripleStoreManager!!.dropGraph(query2, g)
                }
                instance.tripleStoreManager!!.commit(query2)
                query2.commited = true
                JenaWrapper.dropAll()
                val inputData = readFileOrNull(inputDataFileName)
                if (inputData != null && inputDataFileName != null) {
                    lastTripleCount = inputData.split("\n").size
                    if (MAX_TRIPLES_DURING_TEST in 1 until lastTripleCount) {
                        println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                        println("----------Success(Skipped)")
                        LuposdateEndpoint.close(instance)
                        return true
                    }
                    println("InputData Graph[] Original")
                    println(inputData)
                    println("----------Input Data Graph[]")
                    if (inputDataFileName.endsWith(".ttl") || inputDataFileName.endsWith(".n3")) {
                        val query = Query(instance)
                        query.setWorkingDirectory(queryFile.substring(0, queryFile.lastIndexOf("/")))
                        val xmlQueryInput = MemoryTable.parseFromAny(inputData, inputDataFileName, query)!!
                        LuposdateEndpoint.importTripleFile(instance, inputDataFileName)
                        val bulkSelect = instance.tripleStoreManager!!.getDefaultGraph().getIterator(query, arrayOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), EIndexPatternExt.SPO)
                        val xmlGraphBulk = QueryResultToMemoryTable()(bulkSelect.evaluateRootBundle())
                        if (xmlGraphBulk.first() != xmlQueryInput) {
                            println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                            println("----------Failed(BulkImport)")
                            LuposdateEndpoint.close(instance)
                            return false
                        }
                    } else {
                        val query = Query(instance)
                        query.setWorkingDirectory(queryFile.substring(0, queryFile.lastIndexOf("/")))
                        val xmlQueryInput = MemoryTable.parseFromAny(inputData, inputDataFileName, query)!!
                        val tmp2 = POPValues2(query, xmlQueryInput)
                        val key = "${query.getTransactionID()}"
                        if (instance.LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
                            instance.communicationHandler!!.sendData(instance.LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/register", mapOf("key" to key), query.getTransactionID().toInt())
                            query.setDictionaryUrl("${instance.LUPOS_PROCESS_URLS_ALL[0]}/distributed/query/dictionary?key=$key")
                        }
                        val tmp = tmp2.evaluateRoot()
                        val sstore = instance.tripleStoreManager!!.getDefaultGraph()
                        val cache = sstore.modify_create_cache(query, EModifyTypeExt.INSERT, -1, false)
                        val iterator = arrayOf(tmp.columns["s"]!!, tmp.columns["p"]!!, tmp.columns["o"]!!)
                        while (true) {
                            val s = iterator[0].next()
                            val p = iterator[1].next()
                            val o = iterator[2].next()
                            if (s == DictionaryValueHelper.nullValue) {
                                break
                            }
                            cache.writeRow(s, p, o, query)
                        }
                        cache.close()
                        instance.tripleStoreManager!!.commit(query)
                        query.commited = true
                        if (instance.LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
                            instance.communicationHandler!!.sendData(instance.LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
                        }
                    }
                    try {
                        if (!ignoreJena) {
                            JenaWrapper.loadFromFile("/src/luposdate3000/$inputDataFileName")
                        }
                    } catch (e: JenaBugException) {
                        e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:522"/*SOURCE_FILE_END*/)
                        ignoreJena = true
                    } catch (e: Throwable) {
                        e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:525"/*SOURCE_FILE_END*/)
                        ignoreJena = true
                    }
                }
                inputDataGraph.forEach {
                    println("InputData Graph[${it["name"]}] Original")
                    val inputData2 = readFileOrNull(it["filename"])
                    println(inputData2)
                    println("----------Input Data Graph[${it["name"]}]")
                    val query = Query(instance)
                    query.setWorkingDirectory(queryFile.substring(0, queryFile.lastIndexOf("/")))
                    val xmlQueryInput = MemoryTable.parseFromAny(inputData2!!, it["filename"]!!, query)!!
                    val tmp2 = POPValues2(query, xmlQueryInput)
                    val key = "${query.getTransactionID()}"
                    if (instance.LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
                        instance.communicationHandler!!.sendData(instance.LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/register", mapOf("key" to key), query.getTransactionID().toInt())
                        query.setDictionaryUrl("${instance.LUPOS_PROCESS_URLS_ALL[0]}/distributed/query/dictionary?key=$key")
                    }
                    val tmp = tmp2.evaluateRoot()
                    val sstore = instance.tripleStoreManager!!.getGraph(it["name"]!!)
                    val cache = sstore.modify_create_cache(query, EModifyTypeExt.INSERT, -1, false)
                    val iterator = arrayOf(tmp.columns["s"]!!, tmp.columns["p"]!!, tmp.columns["o"]!!)
                    while (true) {
                        val s = iterator[0].next()
                        val p = iterator[1].next()
                        val o = iterator[2].next()
                        if (s == DictionaryValueHelper.nullValue) {
                            break
                        }
                        cache.writeRow(s, p, o, query)
                    }
                    cache.close()
                    instance.tripleStoreManager!!.commit(query)
                    if (instance.LUPOS_PARTITION_MODE == EPartitionModeExt.Process) {
                        instance.communicationHandler!!.sendData(instance.LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
                    }
                    query.commited = true

                    try {
                        if (!ignoreJena) {
                            JenaWrapper.loadFromFile("/src/luposdate3000/" + it["filename"]!!, it["name"]!!)
                        }
                    } catch (e: JenaBugException) {
                        e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:568"/*SOURCE_FILE_END*/)
                        ignoreJena = true
                    } catch (e: Throwable) {
                        e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:571"/*SOURCE_FILE_END*/)
                        ignoreJena = true
                    }
                }
/*
                if (services != null) {
                    for (s in services) {
                        val n = s["name"]!!
                        val fn = s["filename"]!!
                        val fc = readFileOrNull(fn)!!
// TODO
                    }
                }
*/
            } else {
                if (MAX_TRIPLES_DURING_TEST in 1 until lastTripleCount) {
                    println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                    println("----------Success(Skipped)")
                    LuposdateEndpoint.close(instance)
                    return true
                }
            }
            val query = Query(instance)
            query.setWorkingDirectory(queryFile.substring(0, queryFile.lastIndexOf("/")))
            var res: Boolean
            SanityCheck.println { "----------String Query" }
            println(toParse)
            SanityCheck.println { "----------Abstract Syntax Tree" }
            val stream = MyStringStream(toParse)
            val parser: SparqlParser = SparqlParser(stream)
            parser.parserDefinedParse()
            val astNode = parser.getResult() as ASTSparqlDoc
            parser.close()
            stream.close()
            SanityCheck.println { astNode }
            SanityCheck.println { "----------Logical Operator Graph" }
            val visitor = OperatorGraphVisitor(query)
            val lopNode = visitor.visit(astNode)
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:609"/*SOURCE_FILE_END*/ }, { lopNode == lopNode.cloneOP() }, { lopNode.toString() + " - " + lopNode.cloneOP().toString() })
            SanityCheck.suspended {
                val x = lopNode.toString()
                SanityCheck.println { x }
            }
            SanityCheck.println { "----------Logical Operator Graph optimized" }
            val lopNode2 = LogicalOptimizer(query).optimizeCall(lopNode)
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:616"/*SOURCE_FILE_END*/ }, { lopNode2 == lopNode2.cloneOP() })
            SanityCheck.suspended {
                val x = lopNode2.toString()
                SanityCheck.println { x }
            }
            SanityCheck.println { "----------Physical Operator Graph" }
            val popOptimizer = PhysicalOptimizer(query)
            val popNode = popOptimizer.optimizeCall(lopNode2)
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:624"/*SOURCE_FILE_END*/ }, { popNode == popNode.cloneOP() }, { popNode.toString() + " - " + popNode.cloneOP().toString() })
            SanityCheck({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:625"/*SOURCE_FILE_END*/ }, { popNode.toSparqlQuery() })
            SanityCheck.suspended {
                val x = popNode.toString()
                SanityCheck.println { x }
            }
            val xmlQueryResult: MemoryTable? = null
            if (outputDataGraph.isNotEmpty() || (resultData != null && resultDataFileName != null)) {
                SanityCheck.println { "----------Query Result" }
                instance.tripleStoreManager!!.commit(query)
                query.commited = true
            }
            var verifiedOutput = false
            outputDataGraph.forEach {
                val outputData = readFileOrNull(it["filename"])
                val xmlGraphTarget = MemoryTable.parseFromAny(outputData!!, it["filename"]!!, query)!!
                val tmp = instance.tripleStoreManager!!.getGraph(it["name"]!!).getIterator(query, arrayOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), EIndexPatternExt.SPO)
                val xmlGraphActual = QueryResultToMemoryTable()(tmp.evaluateRootBundle())
                if (xmlGraphTarget != xmlGraphActual.first()) {
                    println("OutputData Graph[${it["name"]}] Original")
                    println(outputData)
                    println("----------Verify Output Data Graph[${it["name"]}] ... target,actual")

                    println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                    println("----------Failed(PersistentStore Graph)")
                    LuposdateEndpoint.close(instance)
                    return false
                } else {
                    SanityCheck.println { "OutputData Graph[${it["name"]}] Original" }
                    SanityCheck.println { outputData }
                    SanityCheck.println { "----------Verify Output Data Graph[${it["name"]}] ... target,actual" }
                }
                verifiedOutput = true
            }
            if (resultData != null && resultDataFileName != null) {
                SanityCheck.println { "----------Target Result" }
                val xmlQueryTarget = MemoryTable.parseFromAny(resultData, resultDataFileName, query)!!

                SanityCheck.println { resultData }
                if (!ignoreJena) {
                    try {
                        val jenaResult = JenaWrapper.execQuery(toParse)
                        val jenaXML = MemoryTableFromXML()(jenaResult, xmlQueryResult!!.query!!)
                        val buf = MyPrintWriter(true)
                        if (jenaXML != null && !jenaXML.equalsVerbose(xmlQueryResult, false, true, true, buf)) {
                            println(buf.toString())
                            println("----------Verify Output Jena jena,actual")
                            println("test jenaOriginal :: $jenaResult")

                            println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                            println("----------Failed(Jena)")
                            LuposdateEndpoint.close(instance)
                            return false
                        }
                    } catch (e: JenaBugException) {
                        e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:679"/*SOURCE_FILE_END*/)
                        ignoreJena = true
                    } catch (e: Throwable) {
                        e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:682"/*SOURCE_FILE_END*/)
                        ignoreJena = true
                    }
                }
                val buf2 = MyPrintWriter(true)
                res = xmlQueryResult!!.equalsVerbose(xmlQueryTarget, toParse.lowercase().contains("order", true), true, true, buf2)
                if (res) {
                    val xmlPOP = popNode.toXMLElementRoot(false, PartitionHelper())
                    val query4 = Query(instance)
                    query4.setWorkingDirectory(queryFile.substring(0, queryFile.lastIndexOf("/")))
                    val popNodeRecovered = XMLElementToOPBase(query4, xmlPOP)
                    SanityCheck.println { xmlPOP.toPrettyString() }
                    SanityCheck.suspended {
                        val x = popNodeRecovered.toString()
                        SanityCheck.println { x }
                    }
                    val xmlQueryResultRecovered = QueryResultToMemoryTable()(popNodeRecovered.evaluateRootBundle())
                    instance.tripleStoreManager!!.commit(query4)
                    query4.commited = true
                    val buf3 = MyPrintWriter(true)
                    if (xmlQueryResultRecovered.first().equalsVerbose(xmlQueryResult, false, true, true, buf3)) {
                        if (expectedResult) {
                            println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                            println("----------Success")
                        } else {
                            println(buf3.toString())
                            println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                            println("----------Failed(expectFalse)")
                        }
                    } else {
                        println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                        println("----------Failed(RecoverFromXMLOperatorGraph)")
                        res = false
                    }
                } else {
                    if (expectedResult) {
                        println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                        println("----------Failed(Incorrect)")
                        println(buf2.toString())
                    } else {
                        println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                        println("----------Success(ExpectFalse)")
                    }
                }
                LuposdateEndpoint.close(instance)
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
                LuposdateEndpoint.close(instance)
                return expectedResult
            }
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:750"/*SOURCE_FILE_END*/)
            if (expectedResult) {
                SanityCheck.println { e }
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Failed(ParseError)")
            } else {
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Success(ExpectFalse,ParseError)")
            }
            LuposdateEndpoint.close(instance)
            return false
        } catch (e: Luposdate3000Exception) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:762"/*SOURCE_FILE_END*/)
            if (expectedResult) {
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Failed(${e.classname})")
            } else {
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Success(ExpectFalse,${e.classname})")
            }
            LuposdateEndpoint.close(instance)
            return false
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:773"/*SOURCE_FILE_END*/)
            if (expectedResult) {
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Failed(Throwable)")
                e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:777"/*SOURCE_FILE_END*/)
            } else {
                println("----------Time(${DateHelperRelative.elapsedSeconds(timer)})")
                println("----------Success(ExpectFalse,Throwable)")
                e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuite.kt:781"/*SOURCE_FILE_END*/)
            }
            LuposdateEndpoint.close(instance)
            return false
        }
    }
}
