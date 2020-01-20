package lupos

import kotlin.math.*
import lupos.misc.*
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
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.*
import lupos.s5physicalOperators.singleinput.*
import lupos.s5physicalOperators.POPBaseNullableIterator
import lupos.s6tripleStore.TripleStore
import lupos.s7physicalOptimisation.PhysicalOptimizer
import lupos.s8outputResult.*

expect fun readFileContents(filename: String): String

val errorBoundForDecimalsDigits = 6

fun main(args: Array<String>) {
    println("Starting tests...")
    val (nr_t, nr_e) = parseManifestFile("common/src/main/resources/sparql11-test-suite/", "manifest-all.ttl")
    println("Number of tests: " + nr_t)
    println("Number of errors: " + nr_e)
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
                    queryEvaluationTest = (it == Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#QueryEvaluationTest"))
                    updateEvaluationTest = (it == Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#UpdateEvaluationTest"))
                    syntaxTest = (it == Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveSyntaxTest11") ||
                            it == Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveUpdateSyntaxTest11"))
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
    val action = data.sp(node, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#action"))
    var resultFileTmp = data.sp(node, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#result"))
    var result = true
    resultFileTmp.forEach {
        var resultFile = (Dictionary[it] as IRI).iri
        var targetResult = readFileContents(prefix + resultFile)
        action.forEach {
            val inputData = data.sp(it, Dictionary.IRI(inputDataIdentifier))
            val query = data.sp(it, Dictionary.IRI(queryIdentifier))
            query.forEach {
                val queryfile = (Dictionary[it] as IRI).iri
                inputData.forEach {
                    val inputDataFile = (Dictionary[it] as IRI).iri
                    val inputDataSevenIndices = createSevenIndices(prefix + inputDataFile)
                    println("    Query: " + queryfile)
                    val querycontents = readFileContents(prefix + queryfile)
                    result = result && parseSPARQLAndEvaluate(querycontents, inputDataSevenIndices, targetResult, prefix + resultFile)
                }
            }
        }
    }
    return result
}

private fun updateTestOneEntry(data: SevenIndices, node: Long, queryIdentifier: String, prefix: String): Boolean {
//see for example : common/src/main/resources/sparql11-test-suite/add/manifest.ttl
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
        result = result && parseSPARQLAndPrintOut(querycontents)
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
    var result: ResultRow?

    private val resultSet = ResultSet()

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

    constructor(triple: ID_Triple) {
        result = resultSet.createResultRow()
        result!![resultSet.createVariable("s")] = resultSet.createValue(cleanString(Dictionary[triple.s]!!.toN3String()))
        result!![resultSet.createVariable("p")] = resultSet.createValue(cleanString(Dictionary[triple.p]!!.toN3String()))
        result!![resultSet.createVariable("o")] = resultSet.createValue(cleanString(Dictionary[triple.o]!!.toN3String()))
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun nnext(): ResultRow? {
        var res = result
        result = null
        return res
    }

}

class QueryResult() {
    val variables = mutableListOf<String>()
    val rows = mutableListOf<MutableMap<String, String>>()
    var tmpBinding: String = ""
    override fun toString(): String {
        return "variables:" + variables + "\nrows:" + rows
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || !(other is QueryResult))
            return false
        if (variables.size != (other as QueryResult).variables.size)
            return false
        if (variables != (other as QueryResult).variables)
//		if(!variables .containsAll( (other as QueryResult).variables))
            return false
        if (rows != (other as QueryResult).rows)
            return false
        return true
    }

    fun equalsUnordered(other: Any?): Boolean {
        if (other == null || !(other is QueryResult))
            return false
        if (variables.size != (other as QueryResult).variables.size)
            return false
        for (a in variables) {
            var found = false
            for (b in (other as QueryResult).variables) {
                if (a == b) {
                    found = true
                    break
                }
            }
            if (!found)
                return false
        }
        if (rows.size != (other as QueryResult).rows.size)
            return false
        for (a in rows) {
            var found = false
            for (b in (other as QueryResult).rows) {
                if (a == b) {
                    found = true
                    break
                }
            }
            if (!found)
                return false
        }
        return true
    }
}

fun extractAttribute(xml: String, attr: String): String {
    val start = xml.indexOf(attr)
    if (start >= 0) {
        val s = start + attr.length + 2
        val stop = xml.indexOf("\"", s)
        if (stop > s)
            return xml.substring(s, stop)
        val stop2 = xml.indexOf("'", s)
        if (stop2 > s)
            return xml.substring(s, stop2)
    }
    return ""
}

fun nextToken(xml: String, indention: String, solution: QueryResult) {
    "((<([a-zA-Z]+).*?\\3>)|(<([a-zA-Z]+).*?>)|(<\\?.*?\\?>)|(<!--.*?-->))?".toRegex().findAll(xml).forEach() { child ->
        var value = child.value
        if (value.length > 0 && !value.startsWith("<?") && !value.startsWith("<!--")) {
            var nodeName = "^(<[a-zA-Z]+)".toRegex().find(value)!!.value
            nodeName = nodeName.substring(1, nodeName.length)
            var nodeAttributes = "[^>]*>".toRegex().find(value)!!.value
            value = value.substring(nodeAttributes.length, value.length)
            if (value.endsWith("</${nodeName}>"))
                nodeAttributes = nodeAttributes.substring(nodeName.length + 1, nodeAttributes.length - 1)
            else
                nodeAttributes = nodeAttributes.substring(nodeName.length + 1, nodeAttributes.length - 2)
            if (nodeName == "result") {
                solution.rows.add(mutableMapOf<String, String>())
            }
            if (nodeName == "binding") {
                solution.tmpBinding = extractAttribute(nodeAttributes, "name")
            }
            if (nodeName == "variable") {
                solution.variables.add(extractAttribute(nodeAttributes, "name"))
            }
            if (value.endsWith("</${nodeName}>")) {
                value = value.substring(0, value.length - nodeName.length - 3)
                if (nodeName == "literal") {
                    val dataType = extractAttribute(nodeAttributes, "datatype")
                    val lang = extractAttribute(nodeAttributes, "xml:lang")
                    val latestRow = solution.rows.last()!!
                    if (dataType != "") {
                        when (dataType) {
                            "http://www.w3.org/2001/XMLSchema#double" -> latestRow[solution.tmpBinding] = "\"" + value.toDouble() + "\"^^<" + dataType + ">"
                            "http://www.w3.org/2001/XMLSchema#decimal" -> {
                                value = value.toDouble().toString()
                                if (value.contains('.') && !value.contains('e') && !value.contains('E')) {
                                    val last = value.indexOf(".") + errorBoundForDecimalsDigits + 1
                                    val len = value.length
                                    if (len < last)
                                        value = value.substring(0, len)
                                    else
                                        value = value.substring(0, last)
                                }
                                latestRow[solution.tmpBinding] = "\"" + value.toDouble() + "\"^^<" + dataType + ">"
                            }
                            else -> latestRow[solution.tmpBinding] = "\"" + value + "\"^^<" + dataType + ">"
                        }
                    } else if (lang != "")
                        latestRow[solution.tmpBinding] = "\"" + value + "\"@" + lang
                    else
                        latestRow[solution.tmpBinding] = "\"" + value + "\""
                }
                if (nodeName == "uri") {
                    val latestRow = solution.rows.last()!!
                    latestRow[solution.tmpBinding] = "<" + value + ">"
                }
                if (nodeName == "bnode") {
                    val latestRow = solution.rows.last()!!
                    latestRow[solution.tmpBinding] = "_:" + value
                }
                nextToken(value, indention + "\t", solution)
            }
        }
    }
}

fun parseXMLTarget(xmlFile: String): QueryResult {
    val solution = QueryResult()
    try {
        nextToken(xmlFile.replace("\n", "").replace("\r", ""), "", solution)
    } catch (e: Throwable) {
        e.kotlinStacktrace()
    }
    return solution
}

fun parseSPARQLAndEvaluate(toParse: String, inputData: SevenIndices, resultData: String, resultDataFileName: String): Boolean {
    try {
        val store = TripleStore()
        println("----------Input Data")
        for (triple in inputData.spo) {
            //sehr inperformant ...
            store.addData(TripleInsertIterator(triple))
        }
        printResult(store.getIterator())
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
        println(lop_node)
        println("----------Logical Operator Graph optimized")
        val lop_node2 = LogicalOptimizer().optimize(lop_node)
        println(lop_node2)
        println("----------Physical Operator Graph")
        val pop_optimizer = PhysicalOptimizer()
        pop_optimizer.store = store
        val pop_node = pop_optimizer.optimize(lop_node2) as POPBase
        println(pop_node)
        println("----------Query Result")
        val xmlQueryResult = QueryResultToXML.toXML(pop_node)
        println(xmlQueryResult.first()?.toPrettyString())
        println("----------Target Result")

        var xmlQueryTarget: List<XMLElement>? = null
        when {
            resultDataFileName.endsWith(".srx") -> xmlQueryTarget = XMLElement.parseFromXml(resultData)
            else -> {
                throw Exception("query result file type '${resultDataFileName}' unknown")
            }
        }

        println(xmlQueryTarget?.first()?.toPrettyString())
        val res = xmlQueryResult?.first()?.myEquals(xmlQueryTarget?.first())
        if (res) {
            println("----------Success")
        } else {
            if (xmlQueryResult?.first()?.myEqualsUnclean(xmlQueryTarget?.first())) {
                println("----------Success(Unordered)")
            } else {
                println("----------Failed")
            }
        }
        return res
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
