package lupos

import lupos.s00misc.kotlinStacktrace
import lupos.s00misc.readFileContents
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.ParseError
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s02buildSyntaxTree.rdf.ID_Triple
import lupos.s02buildSyntaxTree.rdf.IRI
import lupos.s02buildSyntaxTree.rdf.BlankNode
import lupos.s02buildSyntaxTree.rdf.SimpleLiteral
import lupos.s02buildSyntaxTree.sparql1_1.parseSPARQL
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithDictionary
import lupos.s03buildOperatorGraph.OperatorGraphVisitor
import lupos.s05logicalOptimisation.LogicalOptimizer
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.*
import lupos.s07physicalOperators.POPBase
import lupos.s07physicalOperators.POPBaseNullableIterator
import lupos.s08tripleStore.TripleStore
import lupos.s09physicalOptimisation.PhysicalOptimizer
import lupos.s10outputResult.QueryResultToXML
import lupos.s11p2p.*
import lupos.s12keyDistributionOptimizer.*
import lupos.s13endpoint.convertToOPBase


val errorBoundForDecimalsDigits = 6

fun main(args: Array<String>) {
    repeat(1) {
        println("Starting tests...")
        val (nr_t, nr_e) = parseManifestFile("resources/sparql11-test-suite/", "manifest-all.ttl")
        println("Number of tests: " + nr_t)
        println("Number of errors: " + nr_e)
        val sp2bFiles = listOf("q3a", "q9", "q4", "q5b", "q10", "q2", "q6", "q12a", "q3b", "q8", "q1", "q5a", "q11", "q12b", "q12c", "q3c", "q7")
        val inputDataFile = "resources/sp2b/sp2b.n3"
        for (f in sp2bFiles) {
            println("  Test: sp2b/$f")
            val queryFile = "resources/sp2b/$f.sparql"
            val resultFile = "resources/sp2b/$f.srj"
            parseSPARQLAndEvaluate(queryFile, inputDataFile, resultFile,null)
        }
    }
    Trace.print()
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
    val nil_iri = Dictionary.IRI(nil)
    val first_iri = Dictionary.IRI(first)
    val rest_iri = Dictionary.IRI(rest)

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
    val ltit = lupos.s02buildSyntaxTree.LookAheadTokenIterator(lupos.s02buildSyntaxTree.turtle.TurtleScanner(lupos.s02buildSyntaxTree.LexerCharIterator(readFileContents(filename))), 3)
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
                if (queryEvaluationTest||syntaxTest) {
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
            }
        }
    }
    return Pair<Int, Int>(numberOfTests, numberOfErrors)
}

private fun readFileOrNull(name: String?): String? {
    if (name == null)
        return null
    return readFileContents(name)
}

private fun testOneEntry(data: SevenIndices, node: Long, queryIdentifier: String, inputDataIdentifier: String, prefix: String): Boolean {
    var queryFile: String? = null
    var inputDataFile: String? = null
    var resultFile: String? = null
    var services=mutableListOf<MutableMap<String,String>>()
    var action: Long? = null
    data.s(node).forEach {
        val iri = (Dictionary[it.first] as IRI).iri
        when (iri) {
            "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#result" -> {
                require(resultFile == null)
                resultFile = prefix+(Dictionary[it.second] as IRI).iri
            }
            "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#action" -> {
                require(action == null)
                action = it.second
            }
            "http://www.w3.org/1999/02/22-rdf-syntax-ns#type" -> {
                println("http://www.w3.org/1999/02/22-rdf-syntax-ns#type : " + (Dictionary[it.second] as IRI).iri)
            }
            "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#name" -> {
                println("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#name " + Dictionary[it.second])
            }
            "http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#feature" -> {
                println("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#feature " + (Dictionary[it.second] as IRI).iri)
            }
            "http://www.w3.org/2000/01/rdf-schema#comment" -> {
                println("http://www.w3.org/2000/01/rdf-schema#comment " + Dictionary[it.second])
            }
            "http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approval" -> {
                println("http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approval " + Dictionary[it.second])
            }
            "http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approvedBy" -> {
                println("http://www.w3.org/2001/sw/DataAccess/tests/test-dawg#approvedBy " + Dictionary[it.second])
            }
            "http://www.w3.org/2000/01/rdf-schema#seeAlso" -> {
                println("http://www.w3.org/2000/01/rdf-schema#seeAlso " + (Dictionary[it.second] as IRI).iri)
            }
            "http://www.w3.org/2001/sw/DataAccess/tests/test-query#queryForm" -> {
                println("http://www.w3.org/2001/sw/DataAccess/tests/test-query#queryForm " + (Dictionary[it.second] as IRI).iri)
            }
            else -> throw Exception("unkonown manifest entry : " + (Dictionary[it.first] as IRI).iri + " # " + Dictionary[it.second])
        }
    }
    println("action :: "+Dictionary[action!!])
when{
Dictionary[action!!] is IRI-> queryFile = prefix+(Dictionary[action!!] as IRI).iri
Dictionary[action!!] is BlankNode ->{
    data.s(action!!).forEach {
        val iri = (Dictionary[it.first] as IRI).iri
        when (iri) {
            "http://www.w3.org/2001/sw/DataAccess/tests/test-query#data" -> {
                require(inputDataFile == null)
                inputDataFile = prefix+(Dictionary[it.second] as IRI).iri
            }
            "http://www.w3.org/2001/sw/DataAccess/tests/test-query#query" -> {
                require(queryFile == null)
                queryFile = prefix+(Dictionary[it.second] as IRI).iri
            }
            "http://www.w3.org/ns/sparql-service-description#entailmentRegime" -> {
                println("http://www.w3.org/ns/sparql-service-description#entailmentRegime " + Dictionary[it.second])
            }
            "http://www.w3.org/ns/sparql-service-description#EntailmentProfile" -> {
                println("http://www.w3.org/ns/sparql-service-description#EntailmentProfile " + Dictionary[it.second])
            }
            "http://www.w3.org/2001/sw/DataAccess/tests/test-query#graphData" -> {
                println("http://www.w3.org/2001/sw/DataAccess/tests/test-query#graphData " + (Dictionary[it.second] as IRI).iri)
            }
            "http://www.w3.org/2001/sw/DataAccess/tests/test-query#serviceData" -> {
		val service=mutableMapOf<String,String>()
		data.s(it.second).forEach{
			val iri = (Dictionary[it.first] as IRI).iri
		        when (iri) {
				"http://www.w3.org/2001/sw/DataAccess/tests/test-query#endpoint" -> {
					service["name"]=(Dictionary[it.second] as IRI).iri
				}
				"http://www.w3.org/2001/sw/DataAccess/tests/test-query#data" ->{
					service["filename"]=prefix+(Dictionary[it.second] as IRI).iri
				}
				else -> throw Exception("unkonown manifest serviceData : " + (Dictionary[it.first] as IRI).iri + " # " + Dictionary[it.second])
			}
		}
			if(service["filename"]!=null)
			services.add(service)
            }
            else -> throw Exception("unkonown manifest action : " + (Dictionary[it.first] as IRI).iri + " # " + Dictionary[it.second])
        }
    }
}
else-> throw Exception("unkonown manifest actionType : " + Dictionary[action!!])
}
	if(queryFile==null)
	return true
    return parseSPARQLAndEvaluate(queryFile!!, inputDataFile, resultFile, services)
}

private fun updateTestOneEntry(data: SevenIndices, node: Long, queryIdentifier: String, prefix: String): Boolean {
    val action = data.sp(node, Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#action"))
    var result = true
    action.forEach {
        val query = data.sp(it, Dictionary.IRI(queryIdentifier))
        query.forEach {
            val queryFile = (Dictionary[it] as IRI).iri
            println("    Query: " + queryFile)
            val querycontents = readFileContents(prefix + queryFile)
            result = result && parseSPARQLAndPrintOut(querycontents)
        }
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

fun parseSPARQLAndEvaluate(queryFile: String, inputDataFileName: String?, resultDataFileName: String?,services:List<Map<String,String>>?): Boolean {
	val toParse=readFileOrNull(queryFile)!!
	val inputData=readFileOrNull(inputDataFileName)
	val resultData=readFileOrNull(resultDataFileName)
    try {
        val store = TripleStore()
        if (inputData != null && inputDataFileName != null) {
            println("InputData Original")
            println(inputData)
            println("----------Input Data")
            var xmlQueryInput = XMLElement.parseFromAny(inputData, inputDataFileName)
            store.addData(POPImportFromXml(xmlQueryInput!!.first()))
            println(xmlQueryInput.first().toPrettyString())
        }
	P2P.execTruncate()
if(services!=null)
	for(s in services){
val n=s["name"]!!
val fn=s["filename"]!!
val fc=readFileOrNull(fn)!!
		P2P.insertOnNamedNode(n,XMLElement.parseFromAny(fc,fn)!!.first())
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
        val pop_node = pop_optimizer.optimize(lop_node2)
        println(pop_node.toXMLElement().toPrettyString())
        println("----------Distributed Operator Graph")
        val pop_distributed_node = KeyDistributionOptimizer().optimize(pop_node) as POPBase
        println(pop_distributed_node)
        if (resultData != null && resultDataFileName != null) {
            println("----------Query Result")
            val xmlQueryResult = QueryResultToXML.toXML(pop_distributed_node)
            println(xmlQueryResult.first().toPrettyString())
            println("----------Target Result")
            var xmlQueryTarget = XMLElement.parseFromAny(resultData, resultDataFileName)
            println(xmlQueryTarget?.first()?.toPrettyString())
            println(resultData)
            var res = xmlQueryResult.first().myEquals(xmlQueryTarget?.first())
            if (res) {
                val xmlPOP = pop_distributed_node.toXMLElement()
                val popNodeRecovered = XMLElement.convertToOPBase(xmlPOP, store) as POPBase
                println(xmlPOP.toPrettyString())
                println(popNodeRecovered.toXMLElement().toPrettyString())
                val xmlQueryResultRecovered = QueryResultToXML.toXML(popNodeRecovered)
                if (xmlQueryResultRecovered.first().myEquals(xmlQueryResult.first()))
                    println("----------Success")
                else {
                    println("----------Failed(recoverFromXMLOperatorGraph)")
                    res = false
                }
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
