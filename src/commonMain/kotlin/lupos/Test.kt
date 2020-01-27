package lupos
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.Variable
import lupos.s06resultRepresentation.ResultSet

import lupos.s8outputResult.QueryResultToXML
import lupos.s5physicalOperators.POPBase
import lupos.misc.kotlinStacktrace
import lupos.misc.XMLElement

import lupos.s1buildSyntaxTree.ParseError
import lupos.s1buildSyntaxTree.rdf.Dictionary
import lupos.s1buildSyntaxTree.rdf.ID_Triple
import lupos.s1buildSyntaxTree.rdf.IRI
import lupos.s1buildSyntaxTree.rdf.SimpleLiteral
import lupos.s1buildSyntaxTree.sparql1_1.parseSPARQL
import lupos.s1buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s1buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s1buildSyntaxTree.LexerCharIterator
import lupos.s1buildSyntaxTree.LookAheadTokenIterator
import lupos.s1buildSyntaxTree.turtle.TurtleParserWithDictionary
import lupos.s2buildOperatorGraph.OperatorGraphVisitor
import lupos.s3logicalOptimisation.LogicalOptimizer
import lupos.s5physicalOperators.POPBaseNullableIterator
import lupos.s6tripleStore.TripleStore
import lupos.s7physicalOptimisation.PhysicalOptimizer

val errorBoundForDecimalsDigits = 6

fun main(args: Array<String>) {
    println("Starting tests...")
    val (nr_t, nr_e) = parseManifestFile("resources/sparql11-test-suite/", "manifest-all.ttl")
    println("Number of tests: " + nr_t)
    println("Number of errors: " + nr_e)
    val sp2bFiles = listOf("q3a", "q9", "q4", "q5b", "q10", "q2", "q6", "q12a", "q3b", "q8", "q1", "q5a", "q11", "q12b", "q12c", "q3c", "q7")
    val inputDataFile = "resources/sp2b/sp2b.n3"
    val inputData = readFileContents(inputDataFile)
    for (f in sp2bFiles) {
        println("  Test: sp2b/$f")
        val queryFile = "resources/sp2b/$f.sparql"
        val query = readFileContents(queryFile)
        val resultFile = "resources/sp2b/$f.srj"
        val result = readFileContents(resultFile)
        parseSPARQLAndEvaluate(query, inputData, inputDataFile, result, resultFile)
    }
}

class SevenIndices {
    private val s = mutableMapOf<Long, Array<Pair<Long, Long>>>()
    private val p = mutableMapOf<Long, Array<Pair<Long, Long>>>()
    private val o = mutableMapOf<Long, Array<Pair<Long, Long>>>()
    private val sp = mutableMapOf<Pair<Long, Long>, LongArray>()
    private val so = mutableMapOf<Pair<Long, Long>, LongArray>()
    private val po = mutableMapOf<Pair<Long, Long>, LongArray>()
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
        distinctOneKeyMap(this.s)
        distinctOneKeyMap(this.p)
        distinctOneKeyMap(this.o)
        distinctTwoKeysMap(this.sp)
        distinctTwoKeysMap(this.so)
        distinctTwoKeysMap(this.po)
        // duplicates are already eliminated in this.spo!
    }

    fun add(triple_s: Long, triple_p: Long, triple_o: Long) {
        addToOneKeyMap(this.s, triple_s, triple_p, triple_o)
        addToOneKeyMap(this.p, triple_p, triple_s, triple_o)
        addToOneKeyMap(this.o, triple_o, triple_s, triple_p)
        addToTwoKeysMap(this.sp, triple_s, triple_p, triple_o)
        addToTwoKeysMap(this.so, triple_s, triple_o, triple_p)
        addToTwoKeysMap(this.po, triple_p, triple_o, triple_s)
        this.spo += ID_Triple(triple_s, triple_p, triple_o)
    }

    private inline fun addToOneKeyMap(onekeymap: MutableMap<Long, Array<Pair<Long, Long>>>, key: Long, value1: Long, value2: Long) {
        val values = onekeymap[key]
        val value = Pair(value1, value2)
        if (values == null) {
            onekeymap[key] = arrayOf(value)
        } else {
            onekeymap[key] = values + value
        }
    }

    private inline fun addToTwoKeysMap(twokeysmap: MutableMap<Pair<Long, Long>, LongArray>, key1: Long, key2: Long, value: Long) {
        val key = Pair(key1, key2)
        val values = twokeysmap[key]
        if (values == null) {
            twokeysmap[key] = longArrayOf(value)
        } else {
            twokeysmap[key] = values + value
        }
    }

    private inline fun distinctOneKeyMap(onekeymap: MutableMap<Long, Array<Pair<Long, Long>>>) {
        for (entry in onekeymap) {
            entry.setValue(entry.value.toMutableSet().toTypedArray())
        }
    }

    private inline fun distinctTwoKeysMap(twokeysmap: MutableMap<Pair<Long, Long>, LongArray>) {
        for (entry in twokeysmap) {
            entry.setValue(entry.value.toMutableSet().toLongArray())
        }
    }
}

private fun listMembers(data: SevenIndices, start: Long, f: (Long) -> Unit) {
    val rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
    val nil = rdf + "nil"
    val first = rdf + "first"
    val rest = rdf + "rest"
    val nil_iri = lupos.s1buildSyntaxTree.rdf.Dictionary.IRI(nil)
    val first_iri = lupos.s1buildSyntaxTree.rdf.Dictionary.IRI(first)
    val rest_iri = lupos.s1buildSyntaxTree.rdf.Dictionary.IRI(rest)

    fun recursiveListMembers(current: Long) {
        data.sp(current, first_iri).forEach { f(it) }
        data.sp(current, rest_iri).forEach {
            if (it != nil_iri) {
                listMembers(data, it, f)
            }
        }
    }

    recursiveListMembers(start)
}

private fun readTurtleData(filename: String, consume_triple: (Long, Long, Long) -> Unit) {
    val ltit = lupos.s1buildSyntaxTree.LookAheadTokenIterator(lupos.s1buildSyntaxTree.turtle.TurtleScanner(lupos.s1buildSyntaxTree.LexerCharIterator(readFileContents(filename))), 3)
    try {
        TurtleParserWithDictionary(consume_triple, ltit).turtleDoc()
    } catch (e: ParseError) {
        e.kotlinStacktrace()
        println("Error in the following line:")
        println(e.lineNumber)
    }
}

private fun createSevenIndices(filename: String): SevenIndices {
    val data = SevenIndices()
    readTurtleData(filename, data::add)
    data.distinct()
    return data
}

private fun parseManifestFile(prefix: String, filename: String): Pair<Int, Int> {
    var numberOfErrors = 0
    var numberOfTests = 0
    println("Reading file " + filename + "...")
    val data = createSevenIndices(prefix + filename)

    val newprefix = prefix + filename.substringBeforeLast("/") + "/"

    val manifestEntries = data.po(Dictionary.IRI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"), Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#Manifest"))

    manifestEntries.forEach {
        // Are other manifest files included?
        val included = data.sp(it, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#include"))
        included.forEach {
            // follow list of included manifest files:
            listMembers(data, it) {
                val includedfile = Dictionary[it]
                if (includedfile != null) {
                    includedfile as IRI
                    val (nr_t, nr_e) = parseManifestFile(prefix, includedfile.iri)
                    numberOfTests += nr_t
                    numberOfErrors += nr_e
                }
            }
        }
        // Now look for the tests:
        val tests = data.sp(it, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#entries"))
        tests.forEach {
            // follow list of entries:
            listMembers(data, it) {
                // for printing out the name:
                val name = data.sp(it, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#name"))
                name.forEach {
                    println("  Test: " + (Dictionary[it] as SimpleLiteral).content)
                    if ((Dictionary[it] as SimpleLiteral).content.compareTo("(pp28a) Diamond, with loop -- (:p/:p)?") == 0) {
                        println("found")
                    }
                }
                // print out the type of test:
                val type = data.sp(it, Dictionary.IRI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"))
                var queryEvaluationTest = false
                var updateEvaluationTest = false
                var syntaxTest = false
                type.forEach {
                    println("    Type: " + Dictionary[it]?.toN3String())
                    queryEvaluationTest = false
                    updateEvaluationTest = false
                    syntaxTest = false
                    when (Dictionary[it]?.toN3String()) {
                        "<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#QueryEvaluationTest>" -> queryEvaluationTest = true
                        "<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#UpdateEvaluationTest>" -> updateEvaluationTest = true
                        "<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveSyntaxTest11>" -> syntaxTest = true
                        "<http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveUpdateSyntaxTest11>" -> syntaxTest = true
                        else -> println(Exception("Unknown Test Type: ${Dictionary[it]?.toN3String()}").toString())
                    }
                }
                if (queryEvaluationTest) {
                    numberOfTests++
                    if (!testOneEntry(data, it, "http://www.w3.org/2001/sw/DataAccess/tests/test-query#query", "http://www.w3.org/2001/sw/DataAccess/tests/test-query#data", newprefix)) {
                        numberOfErrors++
                    }
                }
                if (updateEvaluationTest) {
                    numberOfTests++
                    if (!updateTestOneEntry(data, it, "http://www.w3.org/2009/sparql/tests/test-update#request", newprefix)) {
                        numberOfErrors++
                    }
                }
                if (syntaxTest) {
                    numberOfTests++
                    if (!syntaxTestOneEntry(data, it, newprefix)) {
                        numberOfErrors++
                    }
                }
            }
        }
    }
    return Pair<Int, Int>(numberOfTests, numberOfErrors)
}

private fun testOneEntry(data: SevenIndices, node: Long, queryIdentifier: String, inputDataIdentifier: String, prefix: String): Boolean {
/*
:rdf01 rdf:type mf:QueryEvaluationTest ;
    mf:name    "RDF inference test" ;
    dawgt:approval dawgt:NotClassified ;
    mf:action
         [ qt:query  <rdf01.rq> ;
           qt:data   <rdf01.ttl> ;
           sd:entailmentRegime ent:RDF ] ;
    mf:result  <rdf01.srx> .
*/
    val action = data.sp(node, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#action"))
    var resultFileTmp = data.sp(node, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#result"))
    var result = true
    resultFileTmp.forEach {
        var resultFile: String = (Dictionary[it] as IRI).iri
        var targetResult: String = readFileContents(prefix + resultFile)
        action.forEach {
            val inputData = data.sp(it, Dictionary.IRI(inputDataIdentifier))
            val query = data.sp(it, Dictionary.IRI(queryIdentifier))
            query.forEach {
                val queryfile: String = (Dictionary[it] as IRI).iri
                inputData.forEach {
                    val inputDataFile: String = (Dictionary[it] as IRI).iri
                    val querycontents: String = readFileContents(prefix + queryfile)
                    val inputdatacontent: String = readFileContents(prefix + inputDataFile)
                    result = result && parseSPARQLAndEvaluate(querycontents, inputdatacontent, prefix + inputDataFile, targetResult, prefix + resultFile)
                }
            }
        }
    }
    return result
}

private fun updateTestOneEntry(data: SevenIndices, node: Long, queryIdentifier: String, prefix: String): Boolean {
/*
    mf:name "MOVE 1" ;
    rdfs:comment "Move the default graph to an existing graph" ;
    dawgt:approval dawgt:Approved ;
    dawgt:approvedBy <http://www.w3.org/2009/sparql/meeting/2012-05-22#resolution_2> ;
    mf:action [ ut:request <move-01.ru> ;
                ut:data <move-default.ttl> ;
                ut:graphData [ ut:graph <move-01.ttl> ;
                               rdfs:label "http://example.org/g1" ]
              ] ;
    mf:result [ ut:graphData [ ut:graph <move-default.ttl> ;
                               rdfs:label "http://example.org/g1" ]
              ] .
*/
    val action = data.sp(node, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#action"))
    var result = true
    action.forEach {
        val query = data.sp(it, Dictionary.IRI(queryIdentifier))
        query.forEach {
            val queryfile = (Dictionary[it] as IRI).iri
            println("    Query: " + queryfile)
            val querycontents = readFileContents(prefix + queryfile)
            result = result && parseSPARQLAndPrintOut(querycontents)
        }
    }
    return result
}

private fun syntaxTestOneEntry(data: SevenIndices, node: Long, prefix: String): Boolean {
    val action = data.sp(node, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#action"))
    var result = true
    action.forEach {
        val queryfile = (Dictionary[it] as IRI).iri
        println("    Query: " + queryfile)
        val querycontents = readFileContents(prefix + queryfile)
        result = result && parseSPARQLAndEvaluate(querycontents, null, null, null, null)
    }
    return result
}

fun parseSPARQLAndPrintOut(toParse: String): Boolean {
    try {
        println("File Contents:>>>>>>>>>>")
        println(toParse)
        println("<<<<<<<<<<")
        val node = parseSPARQL(toParse)
        val operatorGraph = node.visit(OperatorGraphVisitor())
        println("Abstract Syntax Tree:>>>>>>>>>>")
        println(node)
        println("<<<<<<<<<<")
        println("Operator Graph:>>>>>>>>>>")
        println(operatorGraph)
        println("<<<<<<<<<<")
        return true
    } catch (e: ParseError) {
        println(e.message)
        println("Error in the following line:")
        println(e.lineNumber)
        return false
    } catch (e: Throwable) {
        e.kotlinStacktrace()
        return false
    }
}

class TripleInsertIterator : POPBaseNullableIterator {
    private val resultSet = ResultSet()
    val data: XMLElement
    var iterator: Iterator<XMLElement>? = null
    val variables = mutableMapOf<String, Variable>()
    override fun toXMLElement(): XMLElement {
        return XMLElement("TripleInsertIterator")
    }

    constructor(data: XMLElement) {
        this.data = data
        if (data.tag != "sparql")
            throw Exception("can only parse sparql xml into an iterator")
        for (r in data.childs) {
            if (r.tag == "results") {
                iterator = r.childs.iterator()
            }
            if (r.tag == "head") {
                if (r.childs.size != 3)
                    throw Exception("can only parse sparql xml containing only triples into an iterator")
                var i = 0
                for (v in r.childs) {
                    when (i) {
                        0 -> variables[v.attributes["name"]!!] = resultSet.createVariable("s")
                        1 -> variables[v.attributes["name"]!!] = resultSet.createVariable("p")
                        2 -> variables[v.attributes["name"]!!] = resultSet.createVariable("o")
                    }
                    i++
                }
            }
        }
        if (iterator == null)
            throw Exception("can only parse sparql xml into an iterator")
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>("s", "p", "o")
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    fun cleanString(s: String): String {
        var res = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
            if (match == null)
                return res
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
    }

    override fun nnext(): ResultRow? {
        if (!iterator!!.hasNext())
            return null
        val node = iterator!!.next()
        val result = resultSet.createResultRow()
        for (v in node.childs) {
            val name = v.attributes["name"]
            val child = v.childs.first()
            val content = cleanString(child.content)

            val value = when {
                child.tag == "uri" -> "<" + content + ">"
                child.tag == "literal" && child.attributes["datatype"] != null -> "\"" + content + "\"^^<" + child.attributes["datatype"] + ">"
                child.tag == "literal" && child.attributes["xml:lang"] != null -> "\"" + content + "\"@" + child.attributes["xml:lang"]
                child.tag == "bnode" -> "_:" + content
                else -> "\"" + content + "\""
            }
            result[variables[name]!!] = resultSet.createValue(value)
        }
        return result
    }
}

fun parseSPARQLAndEvaluate(toParse: String, inputData: String?, inputDataFileName: String?, resultData: String?, resultDataFileName: String?): Boolean {
    try {
        val store = TripleStore()
        if (inputData != null && inputDataFileName != null) {
            println("InputData Original")
            println(inputData)
            println("----------Input Data")
            var xmlQueryInput = XMLElement.parseFromAny(inputData, inputDataFileName)
            store.addData(TripleInsertIterator(xmlQueryInput!!.first()))
            println(xmlQueryInput.first().toPrettyString())
        }
        println("----------String Query")
        println(toParse)
        println("----------Abstract Syntax Tree")
        val lcit = LexerCharIterator(toParse)
        val tit = TokenIteratorSPARQLParser(lcit)
        val ltit = LookAheadTokenIterator(tit, 3)
        val parser = SPARQLParser(ltit)
        val ast_node = parser.expr()
        println(ast_node)
        println("----------Logical Operator Graph")
        val lop_node = ast_node.visit(OperatorGraphVisitor())
        println(lop_node.toXMLElement().toPrettyString())
        println("----------Logical Operator Graph optimized")
        val lop_node2 = LogicalOptimizer().optimize(lop_node)
        println(lop_node2.toXMLElement().toPrettyString())
        println("----------Physical Operator Graph")
        val pop_optimizer = PhysicalOptimizer()
        pop_optimizer.store = store
        val pop_node = pop_optimizer.optimize(lop_node2) as POPBase
        println(pop_node.toXMLElement().toPrettyString())
        if (resultData != null && resultDataFileName != null) {
            println("----------Query Result")
            val xmlQueryResult = QueryResultToXML.toXML(pop_node)
            println(xmlQueryResult.first().toPrettyString())
            println("----------Target Result")
            var xmlQueryTarget = XMLElement.parseFromAny(resultData, resultDataFileName)
            println(xmlQueryTarget?.first()?.toPrettyString())
            println(resultData)
            val res = xmlQueryResult.first().myEquals(xmlQueryTarget?.first())
            if (res) {
                println("----------Success")
            } else {
                if (xmlQueryResult.first().myEqualsUnclean(xmlQueryTarget?.first())) {
                    println("----------Success(Unordered)")
                } else {
                    println("----------Failed")
                }
            }
            return res
        } else {
            println("----------Success(NoResult)")
            return true
        }
    } catch (e: ParseError) {
        println(e.message)
        println("Error in the following line:")
        println(e.lineNumber)
        println("----------Failed(ParseError)")
        return false
    } catch (e: Throwable) {
        e.kotlinStacktrace()
        println("----------Failed(Throwable)")
        return false
    }
}
