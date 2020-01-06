package lupos.sparql1_1.test

import lupos.s1parser.ParseError
import lupos.s1parser.rdf.Dictionary
import lupos.s1parser.rdf.ID_Triple
import lupos.s1parser.rdf.IRI
import lupos.s1parser.rdf.SimpleLiteral
import lupos.s1parser.sparql1_1.parseSPARQL
import lupos.s1parser.turtle.TurtleParserWithDictionary
import lupos.s2operatorgraph.OperatorGraphVisitor

expect fun readFileContents(filename: String): String

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
    private val spo = mutableSetOf<ID_Triple>()

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
    val nil_iri = lupos.s1parser.rdf.Dictionary.IRI(nil)
    val first_iri = lupos.s1parser.rdf.Dictionary.IRI(first)
    val rest_iri = lupos.s1parser.rdf.Dictionary.IRI(rest)

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
    val ltit = lupos.s1parser.LookAheadTokenIterator(lupos.s1parser.turtle.TurtleScanner(lupos.s1parser.LexerCharIterator(readFileContents(filename))), 3)
    try {
        TurtleParserWithDictionary(consume_triple, ltit).turtleDoc()
    } catch (e: ParseError) {
        println(e.message)
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
                    syntaxTest = (it == Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveSyntaxTest11")
                            || it == Dictionary.IRI("http://www.w3.org/2001/sw/DataAccess/tests/test-manifest#PositiveUpdateSyntaxTest11"))
                }
                if (queryEvaluationTest) {
                    numberOfTests++
                    if (!testOneEntry(data, it, "http://www.w3.org/2001/sw/DataAccess/tests/test-query#query", newprefix)) {
                        numberOfErrors++
                    }
                }
                if (updateEvaluationTest) {
                    numberOfTests++
                    if (!testOneEntry(data, it, "http://www.w3.org/2009/sparql/tests/test-update#request", newprefix)) {
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

private fun testOneEntry(data: SevenIndices, node: Long, queryIdentifier: String, prefix: String): Boolean {
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
    } catch (e: UnsupportedOperationException) {
        println(e.message)
        return false
    }
}
