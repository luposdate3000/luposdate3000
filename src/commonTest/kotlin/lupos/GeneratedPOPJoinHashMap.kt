package lupos

import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.*
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.multiinput.*
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.*
import lupos.s11outputResult.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


class GeneratedPOPJoinHashMapTest {
    fun setAggregationMode(node: OPBase, mode: Boolean, count: Int) {
        for (n in node.children)
            setAggregationMode(n, mode, count)
        if (node is AOPAggregation) {
            node.count = count
            node.collectMode = mode
            if (node.collectMode)
                node.a = null
        }
    }

    @TestFactory
    fun test() = listOf(
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "S",
                                        "#p2379",
                                        "O1"
                                ), listOf(
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "#p2379" to "<http://www.example.org/p>",
                                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "#p2379" to "<http://www.example.org/p>",
                                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "#p2379" to "<http://www.example.org/p>",
                                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "S",
                                        "#p2380",
                                        "O2"
                                ), listOf(
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "#p2380" to "<http://www.example.org/q>",
                                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "#p2380" to "<http://www.example.org/q>",
                                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "#p2380" to "<http://www.example.org/q>",
                                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p2379",
                                "O1",
                                "#p2380",
                                "O2",
                                "S"
                        ), listOf(
                                mutableMapOf(
                                        "#p2379" to "<http://www.example.org/p>",
                                        "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2380" to "<http://www.example.org/q>",
                                        "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2379" to "<http://www.example.org/p>",
                                        "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2380" to "<http://www.example.org/q>",
                                        "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2379" to "<http://www.example.org/p>",
                                        "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2380" to "<http://www.example.org/q>",
                                        "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2379" to "<http://www.example.org/p>",
                                        "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2380" to "<http://www.example.org/q>",
                                        "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2379" to "<http://www.example.org/p>",
                                        "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2380" to "<http://www.example.org/q>",
                                        "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2379" to "<http://www.example.org/p>",
                                        "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2380" to "<http://www.example.org/q>",
                                        "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2379" to "<http://www.example.org/p>",
                                        "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2380" to "<http://www.example.org/q>",
                                        "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2379" to "<http://www.example.org/p>",
                                        "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2380" to "<http://www.example.org/q>",
                                        "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2379" to "<http://www.example.org/p>",
                                        "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2380" to "<http://www.example.org/q>",
                                        "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "S",
                                        "#p2641",
                                        "O1"
                                ), listOf(
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "#p2641" to "<http://www.example.org/p>",
                                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "#p2641" to "<http://www.example.org/p>",
                                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "#p2641" to "<http://www.example.org/p>",
                                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "S",
                                        "#p2642",
                                        "O2"
                                ), listOf(
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "#p2642" to "<http://www.example.org/q>",
                                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "#p2642" to "<http://www.example.org/q>",
                                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "S" to "<http://www.example.org/s>",
                                                "#p2642" to "<http://www.example.org/q>",
                                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p2641",
                                "O1",
                                "#p2642",
                                "O2",
                                "S"
                        ), listOf(
                                mutableMapOf(
                                        "#p2641" to "<http://www.example.org/p>",
                                        "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2642" to "<http://www.example.org/q>",
                                        "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2641" to "<http://www.example.org/p>",
                                        "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2642" to "<http://www.example.org/q>",
                                        "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2641" to "<http://www.example.org/p>",
                                        "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2642" to "<http://www.example.org/q>",
                                        "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2641" to "<http://www.example.org/p>",
                                        "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2642" to "<http://www.example.org/q>",
                                        "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2641" to "<http://www.example.org/p>",
                                        "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2642" to "<http://www.example.org/q>",
                                        "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2641" to "<http://www.example.org/p>",
                                        "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2642" to "<http://www.example.org/q>",
                                        "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2641" to "<http://www.example.org/p>",
                                        "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2642" to "<http://www.example.org/q>",
                                        "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2641" to "<http://www.example.org/p>",
                                        "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2642" to "<http://www.example.org/q>",
                                        "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                ),
                                mutableMapOf(
                                        "#p2641" to "<http://www.example.org/p>",
                                        "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p2642" to "<http://www.example.org/q>",
                                        "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "<http://www.example.org/s>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "z",
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s1",
                                        "p1",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s1>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s2>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s3>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s4>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "s1",
                                "p1",
                                "z"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s2>",
                                        "p1" to "<http://example.org/p>",
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s3>",
                                        "p1" to "<http://example.org/p>",
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s4>",
                                        "p1" to "<http://example.org/p>",
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "z",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "z" to null,
                                                "o" to null
                                        ),
                                        mutableMapOf(
                                                "z" to null,
                                                "o" to null
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "z",
                                "o"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book",
                                        "#p8281",
                                        "title"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "#p8281" to "<http://purl.org/dc/elements/1.1/title>",
                                                "title" to "\"SPARQL Tutorial\""
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "#p8281" to "<http://purl.org/dc/elements/1.1/title>",
                                                "title" to "\"The Semantic Web\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "#p8282",
                                        "price"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "#p8282" to "<http://example.org/ns#price>",
                                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "#p8282" to "<http://example.org/ns#price>",
                                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p8281",
                                "title",
                                "#p8282",
                                "price",
                                "book"
                        ), listOf(
                                mutableMapOf(
                                        "#p8281" to "<http://purl.org/dc/elements/1.1/title>",
                                        "title" to "\"SPARQL Tutorial\"",
                                        "#p8282" to "<http://example.org/ns#price>",
                                        "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "book" to "<http://example.org/book/book1>"
                                ),
                                mutableMapOf(
                                        "#p8281" to "<http://purl.org/dc/elements/1.1/title>",
                                        "title" to "\"The Semantic Web\"",
                                        "#p8282" to "<http://example.org/ns#price>",
                                        "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "book" to "<http://example.org/book/book2>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title",
                                        "price"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "title" to "\"SPARQL Tutorial\"",
                                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "title" to "\"The Semantic Web\"",
                                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "title",
                                "price",
                                "book"
                        ), listOf(
                                mutableMapOf(
                                        "title" to "\"SPARQL Tutorial\"",
                                        "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "book" to "<http://example.org/book/book1>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "o" to "<http://example.org/b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "o"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o" to "<http://example.org/b>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"alan@example.org\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "\"Alan\"",
                                        "o2" to "\"alan@example.org\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Bob\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p2" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o2" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "p1",
                                "o1",
                                "p2",
                                "o2",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o1" to "<http://example.org/b>",
                                        "p2" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"alan@example.org\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Alan\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o1" to "<http://example.org/b>",
                                        "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o2" to "\"alan@example.org\"",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"alan@example.org\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o2" to "\"alan@example.org\"",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Alan\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o2" to "\"alan@example.org\"",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o1" to "<http://example.org/b>",
                                        "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o2" to "\"Alan\"",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"alan@example.org\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o2" to "\"Alan\"",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Alan\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o2" to "\"Alan\"",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"bob@example.org\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o2" to "\"bob@example.org\"",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Bob\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o2" to "\"bob@example.org\"",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"bob@example.org\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o2" to "\"Bob\"",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Bob\"",
                                        "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o2" to "\"Bob\"",
                                        "s" to "<http://example.org/b>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "o1" to "\"Alan\"",
                                                "o2" to null
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "\"Alan\"",
                                        "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "\"Alan\"",
                                        "o2" to "\"alan@example.org\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "\"Alan\"",
                                        "o2" to "\"Alan\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "o1" to null,
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "o1" to "<http://example.org/b>",
                                                "o2" to null
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "<http://example.org/b>",
                                        "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "<http://example.org/b>",
                                        "o2" to "\"alan@example.org\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "<http://example.org/b>",
                                        "o2" to "\"Alan\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "<http://example.org/b>",
                                        "o2" to "\"Alan\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "\"alan@example.org\"",
                                        "o2" to "\"Alan\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "\"Alan\"",
                                        "o2" to "\"Alan\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "p1"
                                ), listOf(
                                        mutableMapOf(
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "p1"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "<http://example.org/b>",
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "o2" to "<http://example.org/b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "<http://example.org/c>",
                                                "o2" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "o1" to "\"alice@example.org\"",
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "o1" to "\"Alice\"",
                                                "o2" to null
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "<http://example.org/b>",
                                        "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "\"alan@example.org\"",
                                        "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/a>",
                                        "o1" to "\"Alan\"",
                                        "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/c>",
                                        "o1" to "\"alice@example.org\"",
                                        "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/c>",
                                        "o1" to "\"Alice\"",
                                        "o2" to "<http://example.org/b>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alice@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alice\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p8738",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "#p8738" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "#p8738" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o2" to "<http://example.org/c>"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "p1",
                                "o1",
                                "#p8738",
                                "o2",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o1" to "<http://example.org/b>",
                                        "#p8738" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"alan@example.org\"",
                                        "#p8738" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Alan\"",
                                        "#p8738" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o1" to "<http://example.org/c>",
                                        "#p8738" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/c>",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"bob@example.org\"",
                                        "#p8738" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/c>",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Bob\"",
                                        "#p8738" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/c>",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"alice@example.org\"",
                                        "#p8738" to null,
                                        "o2" to null,
                                        "s" to "<http://example.org/c>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Alice\"",
                                        "#p8738" to null,
                                        "o2" to null,
                                        "s" to "<http://example.org/c>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alice@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alice\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p8748",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "#p8748" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "#p8748" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o2" to "<http://example.org/c>"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "p1",
                                "o1",
                                "#p8748",
                                "o2",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o1" to "<http://example.org/b>",
                                        "#p8748" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"alan@example.org\"",
                                        "#p8748" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Alan\"",
                                        "#p8748" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o1" to "<http://example.org/c>",
                                        "#p8748" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/c>",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"bob@example.org\"",
                                        "#p8748" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/c>",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Bob\"",
                                        "#p8748" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o2" to "<http://example.org/c>",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o1" to "\"alice@example.org\"",
                                        "#p8748" to null,
                                        "o2" to null,
                                        "s" to "<http://example.org/c>"
                                ),
                                mutableMapOf(
                                        "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o1" to "\"Alice\"",
                                        "#p8748" to null,
                                        "o2" to null,
                                        "s" to "<http://example.org/c>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to null,
                                                "title" to "\"SPARQL Tutorial\""
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "title" to null
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title",
                                        "price"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "title" to "\"SPARQL Tutorial\"",
                                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "title" to "\"The Semantic Web\"",
                                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "price",
                                "book",
                                "title"
                        ), listOf(
                                mutableMapOf(
                                        "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "book" to "<http://example.org/book/book1>",
                                        "title" to "\"SPARQL Tutorial\""
                                ),
                                mutableMapOf(
                                        "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "book" to "<http://example.org/book/book2>",
                                        "title" to "\"The Semantic Web\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book",
                                        "#p8838",
                                        "title"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "#p8838" to "<http://purl.org/dc/elements/1.1/title>",
                                                "title" to "\"SPARQL Tutorial\""
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "#p8838" to "<http://purl.org/dc/elements/1.1/title>",
                                                "title" to "\"The Semantic Web\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "#p8839",
                                        "price"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "#p8839" to "<http://example.org/ns#price>",
                                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "#p8839" to "<http://example.org/ns#price>",
                                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p8838",
                                "title",
                                "#p8839",
                                "price",
                                "book"
                        ), listOf(
                                mutableMapOf(
                                        "#p8838" to "<http://purl.org/dc/elements/1.1/title>",
                                        "title" to "\"SPARQL Tutorial\"",
                                        "#p8839" to "<http://example.org/ns#price>",
                                        "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "book" to "<http://example.org/book/book1>"
                                ),
                                mutableMapOf(
                                        "#p8838" to "<http://purl.org/dc/elements/1.1/title>",
                                        "title" to "\"The Semantic Web\"",
                                        "#p8839" to "<http://example.org/ns#price>",
                                        "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "book" to "<http://example.org/book/book2>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "#p8912",
                                        "title"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "#p8912" to "<http://purl.org/dc/elements/1.1/title>",
                                                "title" to "\"SPARQL Tutorial\""
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "#p8912" to "<http://purl.org/dc/elements/1.1/title>",
                                                "title" to "\"The Semantic Web\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p8912",
                                "title",
                                "book"
                        ), listOf(
                                mutableMapOf(
                                        "#p8912" to "<http://purl.org/dc/elements/1.1/title>",
                                        "title" to "\"SPARQL Tutorial\"",
                                        "book" to "<http://example.org/book/book1>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#p8912",
                                        "title",
                                        "book"
                                ), listOf(
                                        mutableMapOf(
                                                "#p8912" to "<http://purl.org/dc/elements/1.1/title>",
                                                "title" to "\"SPARQL Tutorial\"",
                                                "book" to "<http://example.org/book/book1>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "#p8914",
                                        "price"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "#p8914" to "<http://example.org/ns#price>",
                                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "#p8914" to "<http://example.org/ns#price>",
                                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p8912",
                                "title",
                                "#p8914",
                                "price",
                                "book"
                        ), listOf(
                                mutableMapOf(
                                        "#p8912" to "<http://purl.org/dc/elements/1.1/title>",
                                        "title" to "\"SPARQL Tutorial\"",
                                        "#p8914" to "<http://example.org/ns#price>",
                                        "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "book" to "<http://example.org/book/book1>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "#p8922",
                                        "title"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "#p8922" to "<http://purl.org/dc/elements/1.1/title>",
                                                "title" to "\"SPARQL Tutorial\""
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "#p8922" to "<http://purl.org/dc/elements/1.1/title>",
                                                "title" to "\"The Semantic Web\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p8922",
                                "title",
                                "book"
                        ), listOf(
                                mutableMapOf(
                                        "#p8922" to "<http://purl.org/dc/elements/1.1/title>",
                                        "title" to "\"SPARQL Tutorial\"",
                                        "book" to "<http://example.org/book/book1>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#p8922",
                                        "title",
                                        "book"
                                ), listOf(
                                        mutableMapOf(
                                                "#p8922" to "<http://purl.org/dc/elements/1.1/title>",
                                                "title" to "\"SPARQL Tutorial\"",
                                                "book" to "<http://example.org/book/book1>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "#p8924",
                                        "price"
                                ), listOf(
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book1>",
                                                "#p8924" to "<http://example.org/ns#price>",
                                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "book" to "<http://example.org/book/book2>",
                                                "#p8924" to "<http://example.org/ns#price>",
                                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p8922",
                                "title",
                                "#p8924",
                                "price",
                                "book"
                        ), listOf(
                                mutableMapOf(
                                        "#p8922" to "<http://purl.org/dc/elements/1.1/title>",
                                        "title" to "\"SPARQL Tutorial\"",
                                        "#p8924" to "<http://example.org/ns#price>",
                                        "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "book" to "<http://example.org/book/book1>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "o" to "<http://example.org/s1>",
                                    "p2" to "<http://example.org/p1>",
                                    "o2" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "o" to "<http://example.org/s3>",
                                    "p2" to "<http://example.org/p3>",
                                    "o2" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "o" to "<http://example.org/s4>",
                                    "p2" to "<http://example.org/p4>",
                                    "o2" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "o" to "<http://example.org/s5>",
                                    "p2" to "<http://example.org/p5>",
                                    "o2" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "o" to "<http://example.org/s6>",
                                    "p2" to "<http://example.org/p6>",
                                    "o2" to "_:o6"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p3>",
                                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p4>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s5>",
                                                "p" to "<http://example.org/p5>",
                                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s6>",
                                                "p" to "<http://example.org/p6>",
                                                "o" to "_:o6"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "o" to "<http://example.org/s1>",
                                                "p2" to "<http://example.org/p1>",
                                                "o2" to "<http://example.org/s2>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s2>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s3>",
                                                "p2" to "<http://example.org/p3>",
                                                "o2" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s4>",
                                                "p2" to "<http://example.org/p4>",
                                                "o2" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s5>",
                                                "p2" to "<http://example.org/p5>",
                                                "o2" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s6>",
                                                "p2" to "<http://example.org/p6>",
                                                "o2" to "_:o6"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "p2" to "<http://example.org/p2>",
                                        "o2" to "\"foo\"",
                                        "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "p2" to null,
                                        "o2" to null,
                                        "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p3>",
                                        "p2" to null,
                                        "o2" to null,
                                        "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p4>",
                                        "p2" to null,
                                        "o2" to null,
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s5>",
                                        "p" to "<http://example.org/p5>",
                                        "p2" to null,
                                        "o2" to null,
                                        "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "p" to "<http://example.org/p6>",
                                        "p2" to null,
                                        "o2" to null,
                                        "o" to "_:o6"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "a",
                                        "#p11029",
                                        "#o11029"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "#p11029" to "<http://xmlns.com/foaf/0.1/name>",
                                                "#o11029" to "\"Alan\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "#p11030",
                                        "b"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "#p11030" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "b" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "#p11030" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "b" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/b>",
                                                "#p11030" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "b" to "<http://example.org/c>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p11029",
                                "#o11029",
                                "#p11030",
                                "b",
                                "a"
                        ), listOf(
                                mutableMapOf(
                                        "#p11029" to "<http://xmlns.com/foaf/0.1/name>",
                                        "#o11029" to "\"Alan\"",
                                        "#p11030" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "b" to "<http://example.org/b>",
                                        "a" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "#p11029" to "<http://xmlns.com/foaf/0.1/name>",
                                        "#o11029" to "\"Alan\"",
                                        "#p11030" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "b" to "<http://example.org/c>",
                                        "a" to "<http://example.org/a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "a",
                                        "#p11269",
                                        "#o11269"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "#p11269" to "<http://xmlns.com/foaf/0.1/name>",
                                                "#o11269" to "\"Alan\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "#p11270",
                                        "Var_B"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "#p11270" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "Var_B" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "#p11270" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "Var_B" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/b>",
                                                "#p11270" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "Var_B" to "<http://example.org/c>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p11269",
                                "#o11269",
                                "#p11270",
                                "Var_B",
                                "a"
                        ), listOf(
                                mutableMapOf(
                                        "#p11269" to "<http://xmlns.com/foaf/0.1/name>",
                                        "#o11269" to "\"Alan\"",
                                        "#p11270" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "Var_B" to "<http://example.org/b>",
                                        "a" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "#p11269" to "<http://xmlns.com/foaf/0.1/name>",
                                        "#o11269" to "\"Alan\"",
                                        "#p11270" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "Var_B" to "<http://example.org/c>",
                                        "a" to "<http://example.org/a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-04b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "a",
                                        "#p11405",
                                        "#o11405"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "#p11405" to "<http://xmlns.com/foaf/0.1/name>",
                                                "#o11405" to "\"Alan\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "#p11406",
                                        "Var_B"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "#p11406" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "Var_B" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "#p11406" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "Var_B" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/b>",
                                                "#p11406" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "Var_B" to "<http://example.org/c>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p11405",
                                "#o11405",
                                "#p11406",
                                "Var_B",
                                "a"
                        ), listOf(
                                mutableMapOf(
                                        "#p11405" to "<http://xmlns.com/foaf/0.1/name>",
                                        "#o11405" to "\"Alan\"",
                                        "#p11406" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "Var_B" to "<http://example.org/b>",
                                        "a" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "#p11405" to "<http://xmlns.com/foaf/0.1/name>",
                                        "#o11405" to "\"Alan\"",
                                        "#p11406" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "Var_B" to "<http://example.org/c>",
                                        "a" to "<http://example.org/a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "a",
                                        "#p11557",
                                        "#o11557"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/a>",
                                                "#p11557" to "<http://xmlns.com/foaf/0.1/name>",
                                                "#o11557" to "\"Alan\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "#p11558",
                                        "Var_B"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/b>",
                                                "#p11558" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "Var_B" to "<http://example.org/c>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p11557",
                                "#o11557",
                                "#p11558",
                                "Var_B",
                                "a"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#s12433",
                                        "#p12433",
                                        "s"
                                ), listOf(
                                        mutableMapOf(
                                                "#s12433" to "<http://example.org/a>",
                                                "#p12433" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "s" to "<http://example.org/b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#s12433",
                                "#p12433",
                                "p",
                                "o",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "#s12433" to "<http://example.org/a>",
                                        "#p12433" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o" to "\"bob@example.org\"",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "#s12433" to "<http://example.org/a>",
                                        "#p12433" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "p" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o" to "\"Bob\"",
                                        "s" to "<http://example.org/b>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#s12529",
                                        "#p12529",
                                        "s"
                                ), listOf(
                                        mutableMapOf(
                                                "#s12529" to "<http://example.org/a>",
                                                "#p12529" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "s" to "<http://example.org/b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#s12529",
                                "#p12529",
                                "p",
                                "o",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "#s12529" to "<http://example.org/a>",
                                        "#p12529" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o" to "\"bob@example.org\"",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "#s12529" to "<http://example.org/a>",
                                        "#p12529" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "p" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o" to "\"Bob\"",
                                        "s" to "<http://example.org/b>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p12610",
                                        "#o12610"
                                ), listOf(
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p12610",
                                "#o12610",
                                "p",
                                "o",
                                "s"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-03.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p12688",
                                        "#o12688"
                                ), listOf(
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p12688",
                                "#o12688",
                                "p",
                                "o",
                                "s"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-04.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#s12755",
                                        "#p12755",
                                        "s"
                                ), listOf(
                                        mutableMapOf(
                                                "#s12755" to "<http://example.org/a>",
                                                "#p12755" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "s" to "<http://example.org/b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#s12755",
                                "#p12755",
                                "p",
                                "o",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "#s12755" to "<http://example.org/a>",
                                        "#p12755" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o" to "\"bob@example.org\"",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "#s12755" to "<http://example.org/a>",
                                        "#p12755" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "p" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o" to "\"Bob\"",
                                        "s" to "<http://example.org/b>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p12859",
                                        "#o12859"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "#p12859" to "<http://xmlns.com/foaf/0.1/name>",
                                                "#o12859" to "\"Chris\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"chris@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Chris\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p12859",
                                "#o12859",
                                "p",
                                "o",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "#p12859" to "<http://xmlns.com/foaf/0.1/name>",
                                        "#o12859" to "\"Chris\"",
                                        "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o" to "\"chris@example.org\"",
                                        "s" to "<http://example.org/c>"
                                ),
                                mutableMapOf(
                                        "#p12859" to "<http://xmlns.com/foaf/0.1/name>",
                                        "#o12859" to "\"Chris\"",
                                        "p" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o" to "\"Chris\"",
                                        "s" to "<http://example.org/c>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#s13262",
                                        "#p13262",
                                        "s"
                                ), listOf(
                                        mutableMapOf(
                                                "#s13262" to "<http://example.org/a>",
                                                "#p13262" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "s" to "<http://example.org/b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"chris@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Chris\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#s13262",
                                "#p13262",
                                "p",
                                "o",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "#s13262" to "<http://example.org/a>",
                                        "#p13262" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o" to "<http://example.org/c>",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "#s13262" to "<http://example.org/a>",
                                        "#p13262" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o" to "\"bob@example.org\"",
                                        "s" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                        "#s13262" to "<http://example.org/a>",
                                        "#p13262" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "p" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o" to "\"Bob\"",
                                        "s" to "<http://example.org/b>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#s13376",
                                        "#p13376",
                                        "s"
                                ), listOf(
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/d>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"chris@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Chris\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/d>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"dan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/d>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Dan\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#s13376",
                                "#p13376",
                                "p",
                                "o",
                                "s"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p13452",
                                        "#o13452"
                                ), listOf(
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"chris@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Chris\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p13452",
                                "#o13452",
                                "p",
                                "o",
                                "s"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-03.ru */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p13539",
                                "#o13539"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "#p13539" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "#o13539" to "<http://example.org/d>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "o" to "<http://example.org/d>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                    "o" to "\"chris@example.org\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "p" to "<http://xmlns.com/foaf/0.1/name>",
                                    "o" to "\"Chris\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d>",
                                    "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                    "o" to "\"dan@example.org\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d>",
                                    "p" to "<http://xmlns.com/foaf/0.1/name>",
                                    "o" to "\"Dan\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p13539",
                            "#o13539",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p13539" to "<http://xmlns.com/foaf/0.1/knows>",
                                "#o13539" to "<http://example.org/d>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/d>",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p13539" to "<http://xmlns.com/foaf/0.1/knows>",
                                "#o13539" to "<http://example.org/d>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"chris@example.org\"",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p13539" to "<http://xmlns.com/foaf/0.1/knows>",
                                "#o13539" to "<http://example.org/d>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Chris\"",
                                "s" to "<http://example.org/c>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/delete/delete-using-04.ru */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p13644",
                                        "#o13644"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "#p13644" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "#o13644" to "<http://example.org/b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"Bob\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p13644",
                                "#o13644",
                                "p",
                                "o",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "#p13644" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "#o13644" to "<http://example.org/b>",
                                        "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "o" to "<http://example.org/b>",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "#p13644" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "#o13644" to "<http://example.org/b>",
                                        "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                        "o" to "\"alan@example.org\"",
                                        "s" to "<http://example.org/a>"
                                ),
                                mutableMapOf(
                                        "#p13644" to "<http://xmlns.com/foaf/0.1/knows>",
                                        "#o13644" to "<http://example.org/b>",
                                        "p" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o" to "\"Alan\"",
                                        "s" to "<http://example.org/a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p13764",
                                "#o13764"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "#p13764" to "<http://xmlns.com/foaf/0.1/name>",
                                    "#o13764" to "\"Chris\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "o" to "<http://example.org/d>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                    "o" to "\"chris@example.org\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "p" to "<http://xmlns.com/foaf/0.1/name>",
                                    "o" to "\"Chris\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d>",
                                    "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                    "o" to "\"dan@example.org\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d>",
                                    "p" to "<http://xmlns.com/foaf/0.1/name>",
                                    "o" to "\"Dan\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p13764",
                            "#o13764",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p13764" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o13764" to "\"Chris\"",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/d>",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p13764" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o13764" to "\"Chris\"",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"chris@example.org\"",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p13764" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o13764" to "\"Chris\"",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Chris\"",
                                "s" to "<http://example.org/c>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/delete/delete-using-06.ru */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p15501",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "#p15501" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/d>",
                                    "#p15501" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "#p15501" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p15501" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p15501" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/y>",
                                    "#p15501" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "_:rdfs05",
                                    "#p15501" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p15502",
                                "#o15502"
                            ), listOf(
                                mutableMapOf(
                                    "c" to "<http://example.org/x/c>",
                                    "#p15502" to "<http://www.w3.org/2000/01/rdf-schema#subClassOf>",
                                    "#o15502" to "<http://example.org/x/d>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p15501",
                            "#p15502",
                            "#o15502",
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p15501" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#p15502" to "<http://www.w3.org/2000/01/rdf-schema#subClassOf>",
                                "#o15502" to "<http://example.org/x/d>",
                                "c" to "<http://example.org/x/c>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/rdfs05.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s15754",
                                "x",
                                "#o15754"
                            ), listOf(
                                mutableMapOf(
                                    "#s15754" to "<http://example.org/ns#a>",
                                    "x" to "<http://example.org/ns#b>",
                                    "#o15754" to "<http://example.org/ns#c>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p15755",
                                "#o15755"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/ns#b>",
                                    "#p15755" to "<http://www.w3.org/2000/01/rdf-schema#subPropertyOf>",
                                    "#o15755" to "<http://example.org/ns#p>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s15754",
                            "#o15754",
                            "#p15755",
                            "#o15755",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#s15754" to "<http://example.org/ns#a>",
                                "#o15754" to "<http://example.org/ns#c>",
                                "#p15755" to "<http://www.w3.org/2000/01/rdf-schema#subPropertyOf>",
                                "#o15755" to "<http://example.org/ns#p>",
                                "x" to "<http://example.org/ns#b>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/rdfs11.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p15938",
                                        "c"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/a>",
                                                "#p15938" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "c" to "<http://example.org/x/c>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/a>",
                                                "#p15938" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "c" to "<http://example.org/x/d>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/a>",
                                                "#p15938" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/a>",
                                                "#p15938" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "c" to "_:x"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/c>",
                                                "#p15938" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/d>",
                                                "#p15938" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/p>",
                                                "#p15938" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                        ),
                                        mutableMapOf(
                                                "x" to "_:ont",
                                                "#p15938" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        ),
                                        mutableMapOf(
                                                "x" to "_:x",
                                                "#p15938" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "c",
                                        "#p15939",
                                        "#o15939"
                                ), listOf(
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p15938",
                                "#p15939",
                                "#o15939",
                                "c"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p15999",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/x>",
                                                "#p15999" to "<http://example.org/x/p>",
                                                "y" to "<http://example.org/x/y>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/x>",
                                                "#p15999" to "<http://example.org/x/p>",
                                                "y" to "_:y"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "y",
                                        "#p16000",
                                        "#o16000"
                                ), listOf(
                                        mutableMapOf(
                                                "y" to "<http://example.org/x/y>",
                                                "#p16000" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16000" to "<http://example.org/x/c>"
                                        ),
                                        mutableMapOf(
                                                "y" to "_:y",
                                                "#p16000" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16000" to "<http://example.org/x/c>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p15999",
                                "#p16000",
                                "#o16000",
                                "y"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://example.org/x/x>",
                                        "#p15999" to "<http://example.org/x/p>",
                                        "#p16000" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16000" to "<http://example.org/x/c>",
                                        "y" to "<http://example.org/x/y>"
                                ),
                                mutableMapOf(
                                        "x" to "<http://example.org/x/x>",
                                        "#p15999" to "<http://example.org/x/p>",
                                        "#p16000" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16000" to "<http://example.org/x/c>",
                                        "y" to "_:y"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16189",
                                "#c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Conference>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/ConferencePaper>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Employee>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/GraduateAssistant>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Student>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Workshop>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/hasPublication>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/name>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/publishedAt>",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:SPARQLDAWGTestOntology",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:_16086",
                                    "#p16189" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16107",
                                "#p16190",
                                "#o16190"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16189",
                            "#c",
                            "#_16107",
                            "#p16190",
                            "#o16190"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16189",
                                "#p16190",
                                "#o16190",
                                "#p16192",
                                "#p16194",
                                "#o16194",
                                "#p16196",
                                "#o16196",
                                "#_16112",
                                "#p16198",
                                "#c",
                                "#_16107"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16200",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16200" to "<http://example.org/name>",
                                    "y" to "\"Johnnie\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p16189",
                            "#p16190",
                            "#o16190",
                            "#p16192",
                            "#p16194",
                            "#o16194",
                            "#p16196",
                            "#o16196",
                            "#_16112",
                            "#p16198",
                            "#c",
                            "#_16107",
                            "#p16200",
                            "y",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16316",
                                "#b0"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16231",
                                "#p16317",
                                "#o16317"
                            ), listOf(
                                mutableMapOf(
                                    "#_16231" to "_:_16211",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16316",
                            "#b0",
                            "#_16231",
                            "#p16317",
                            "#o16317"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16231" to "_:_16211",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16231" to "_:_16211",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16316",
                                "#b0",
                                "#_16231",
                                "#p16317",
                                "#o16317"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16231" to "_:_16211",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16231" to "_:_16211",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16231",
                                "#p16319",
                                "#o16319"
                            ), listOf(
                                mutableMapOf(
                                    "#_16231" to "_:_16211",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16316",
                            "#b0",
                            "#p16317",
                            "#o16317",
                            "#p16319",
                            "#o16319",
                            "#_16231"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16316",
                                "#b0",
                                "#p16317",
                                "#o16317",
                                "#p16319",
                                "#o16319",
                                "#_16231"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16240",
                                "#p16321",
                                "#o16321"
                            ), listOf(
                                mutableMapOf(
                                    "#_16240" to "<http://example.org/Conference>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_16240" to "<http://example.org/ConferencePaper>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_16240" to "<http://example.org/Employee>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_16240" to "<http://example.org/GraduateAssistant>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_16240" to "<http://example.org/Student>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_16240" to "<http://example.org/Workshop>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16316",
                            "#b0",
                            "#p16317",
                            "#o16317",
                            "#p16319",
                            "#o16319",
                            "#_16231",
                            "#_16240",
                            "#p16321",
                            "#o16321"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211",
                                "#_16240" to "<http://example.org/Conference>",
                                "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211",
                                "#_16240" to "<http://example.org/Conference>",
                                "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211",
                                "#_16240" to "<http://example.org/ConferencePaper>",
                                "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211",
                                "#_16240" to "<http://example.org/ConferencePaper>",
                                "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211",
                                "#_16240" to "<http://example.org/Employee>",
                                "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211",
                                "#_16240" to "<http://example.org/Employee>",
                                "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211",
                                "#_16240" to "<http://example.org/GraduateAssistant>",
                                "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211",
                                "#_16240" to "<http://example.org/GraduateAssistant>",
                                "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211",
                                "#_16240" to "<http://example.org/Student>",
                                "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211",
                                "#_16240" to "<http://example.org/Student>",
                                "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211",
                                "#_16240" to "<http://example.org/Workshop>",
                                "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16316" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16317" to "<http://example.org/publishedAt>",
                                "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16231" to "_:_16211",
                                "#_16240" to "<http://example.org/Workshop>",
                                "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16316",
                                "#b0",
                                "#p16317",
                                "#o16317",
                                "#p16319",
                                "#o16319",
                                "#_16231",
                                "#_16240",
                                "#p16321",
                                "#o16321"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211",
                                    "#_16240" to "<http://example.org/Conference>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211",
                                    "#_16240" to "<http://example.org/Conference>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211",
                                    "#_16240" to "<http://example.org/ConferencePaper>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211",
                                    "#_16240" to "<http://example.org/ConferencePaper>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211",
                                    "#_16240" to "<http://example.org/Employee>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211",
                                    "#_16240" to "<http://example.org/Employee>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211",
                                    "#_16240" to "<http://example.org/GraduateAssistant>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211",
                                    "#_16240" to "<http://example.org/GraduateAssistant>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211",
                                    "#_16240" to "<http://example.org/Student>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211",
                                    "#_16240" to "<http://example.org/Student>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211",
                                    "#_16240" to "<http://example.org/Workshop>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16316" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16317" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16317" to "<http://example.org/publishedAt>",
                                    "#p16319" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16319" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16231" to "_:_16211",
                                    "#_16240" to "<http://example.org/Workshop>",
                                    "#p16321" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16321" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16240",
                                "#p16323",
                                "#o16323"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16316",
                            "#b0",
                            "#p16317",
                            "#o16317",
                            "#p16319",
                            "#o16319",
                            "#_16231",
                            "#p16321",
                            "#o16321",
                            "#p16323",
                            "#o16323",
                            "#_16240"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16316",
                                "#b0",
                                "#p16317",
                                "#o16317",
                                "#p16319",
                                "#o16319",
                                "#_16231",
                                "#p16321",
                                "#o16321",
                                "#p16323",
                                "#o16323",
                                "#_16240"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16231",
                                "#p16325",
                                "#_16240"
                            ), listOf(
                                mutableMapOf(
                                    "#_16231" to "_:_16211",
                                    "#p16325" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                    "#_16240" to "<http://example.org/Conference>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16316",
                            "#b0",
                            "#p16317",
                            "#o16317",
                            "#p16319",
                            "#o16319",
                            "#p16321",
                            "#o16321",
                            "#p16323",
                            "#o16323",
                            "#p16325",
                            "#_16231",
                            "#_16240"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16316",
                                "#b0",
                                "#p16317",
                                "#o16317",
                                "#p16319",
                                "#o16319",
                                "#p16321",
                                "#o16321",
                                "#p16323",
                                "#o16323",
                                "#p16325",
                                "#_16231",
                                "#_16240"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b0",
                                "#p16327",
                                "#_16231"
                            ), listOf(
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Anite>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Anite>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Conference>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/ConferencePaper>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Employee>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/George>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/George>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/GraduateAssistant>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/John>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/John>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Student>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Workshop>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/hasPublication>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/name>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/person1>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/publishedAt>",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "_:SPARQLDAWGTestOntology",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "#b0" to "_:_16211",
                                    "#p16327" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16231" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16316",
                            "#p16317",
                            "#o16317",
                            "#p16319",
                            "#o16319",
                            "#p16321",
                            "#o16321",
                            "#p16323",
                            "#o16323",
                            "#p16325",
                            "#_16240",
                            "#p16327",
                            "#b0",
                            "#_16231"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16401",
                                "#o16401"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16401" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16401" to "<http://example.org/Student>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16402",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Conference>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/ConferencePaper>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Employee>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/GraduateAssistant>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Student>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Workshop>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/hasPublication>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/name>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/publishedAt>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:SPARQLDAWGTestOntology",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:_16338",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p16401",
                            "#o16401",
                            "#p16402",
                            "c",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p16401" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16401" to "<http://example.org/Student>",
                                "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "c" to "<http://example.org/Student>",
                                "x" to "<http://example.org/Anite>"
                            ),
                            mutableMapOf(
                                "#p16401" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16401" to "<http://example.org/Student>",
                                "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>",
                                "x" to "<http://example.org/Anite>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p16401",
                                "#o16401",
                                "#p16402",
                                "c",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "#p16401" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16401" to "<http://example.org/Student>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/Student>",
                                    "x" to "<http://example.org/Anite>"
                                ),
                                mutableMapOf(
                                    "#p16401" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16401" to "<http://example.org/Student>",
                                    "#p16402" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>",
                                    "x" to "<http://example.org/Anite>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p16404",
                                "#o16404"
                            ), listOf(
                                mutableMapOf(
                                    "c" to "<http://example.org/GraduateAssistant>",
                                    "#p16404" to "<http://www.w3.org/2000/01/rdf-schema#subClassOf>",
                                    "#o16404" to "<http://example.org/Employee>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p16401",
                            "#o16401",
                            "#p16402",
                            "x",
                            "#p16404",
                            "#o16404",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "p",
                                        "#p16466",
                                        "#o16466"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/hasPublication>",
                                                "#p16466" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16466" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                        ),
                                        mutableMapOf(
                                                "p" to "<http://example.org/publishedAt>",
                                                "#p16466" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16466" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#s16467",
                                        "p",
                                        "v"
                                ), listOf(
                                        mutableMapOf(
                                                "#s16467" to "<http://example.org/John>",
                                                "p" to "<http://example.org/hasPublication>",
                                                "v" to "<http://example.org/paper1>"
                                        ),
                                        mutableMapOf(
                                                "#s16467" to "<http://example.org/John>",
                                                "p" to "<http://example.org/name>",
                                                "v" to "\"Johnnie\""
                                        ),
                                        mutableMapOf(
                                                "#s16467" to "<http://example.org/John>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "v" to "<http://example.org/GraduateAssistant>"
                                        ),
                                        mutableMapOf(
                                                "#s16467" to "<http://example.org/John>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "v" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p16466",
                                "#o16466",
                                "#s16467",
                                "v",
                                "p"
                        ), listOf(
                                mutableMapOf(
                                        "#p16466" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16466" to "<http://www.w3.org/2002/07/owl#ObjectProperty>",
                                        "#s16467" to "<http://example.org/John>",
                                        "v" to "<http://example.org/paper1>",
                                        "p" to "<http://example.org/hasPublication>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "z",
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "#p16579",
                                        "#o16579"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>",
                                                "#p16579" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16579" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "o",
                                "#p16579",
                                "#o16579",
                                "p"
                        ), listOf(
                                mutableMapOf(
                                        "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16579" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16579" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s2>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16579" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16579" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s3>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16579" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16579" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16579" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16579" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "z2",
                                        "z",
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "z2" to null,
                                                "z" to null,
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        ),
                                        mutableMapOf(
                                                "z2" to "\"101\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z2" to "\"102\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z2" to "\"103\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z2" to "\"104\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z2" to null,
                                                "z" to null,
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "#p16708",
                                        "#o16708"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>",
                                                "#p16708" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16708" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "z2",
                                "z",
                                "s",
                                "o",
                                "#p16708",
                                "#o16708",
                                "p"
                        ), listOf(
                                mutableMapOf(
                                        "z2" to "\"101\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16708" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16708" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z2" to "\"102\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s2>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16708" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16708" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z2" to "\"103\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s3>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16708" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16708" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z2" to "\"104\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16708" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16708" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "z",
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "#p16880",
                                        "#o16880"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>",
                                                "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "o",
                                "#p16880",
                                "#o16880",
                                "p"
                        ), listOf(
                                mutableMapOf(
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s2>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s3>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "z",
                                        "s",
                                        "o",
                                        "#p16880",
                                        "#o16880",
                                        "p"
                                ), listOf(
                                        mutableMapOf(
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s1>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "p" to "<http://example.org/p>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s2>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "p" to "<http://example.org/p>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s3>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "p" to "<http://example.org/p>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s4>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "p" to "<http://example.org/p>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s1",
                                        "p1",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "s1" to "<http://example.org/p>",
                                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "z" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s1>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s2>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s3>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s4>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "_:1",
                                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "z" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "o",
                                "#p16880",
                                "#o16880",
                                "p",
                                "s1",
                                "p1",
                                "z"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "s1" to "<http://example.org/s2>",
                                        "p1" to "<http://example.org/p>",
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "s1" to "<http://example.org/s3>",
                                        "p1" to "<http://example.org/p>",
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "s1" to "<http://example.org/s4>",
                                        "p1" to "<http://example.org/p>",
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o",
                                        "#p16880",
                                        "#o16880",
                                        "p",
                                        "s1",
                                        "p1",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "p" to "<http://example.org/p>",
                                                "s1" to "<http://example.org/s2>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "p" to "<http://example.org/p>",
                                                "s1" to "<http://example.org/s3>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "p" to "<http://example.org/p>",
                                                "s1" to "<http://example.org/s4>",
                                                "p1" to "<http://example.org/p>",
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p1",
                                        "#p16884",
                                        "#o16884"
                                ), listOf(
                                        mutableMapOf(
                                                "p1" to "<http://example.org/p>",
                                                "#p16884" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16884" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "o",
                                "#p16880",
                                "#o16880",
                                "p",
                                "s1",
                                "z",
                                "#p16884",
                                "#o16884",
                                "p1"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "s1" to "<http://example.org/s2>",
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16884" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16884" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p1" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "s1" to "<http://example.org/s3>",
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16884" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16884" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p1" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16880" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16880" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "s1" to "<http://example.org/s4>",
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16884" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16884" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p1" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "#p16993",
                                        "#o16993"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>",
                                                "#p16993" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o16993" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "o",
                                "#p16993",
                                "#o16993",
                                "p"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16993" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16993" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16993" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16993" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16993" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16993" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p16993" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o16993" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "z",
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "#p17097",
                                        "#o17097"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>",
                                                "#p17097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o17097" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "o",
                                "#p17097",
                                "#o17097",
                                "p"
                        ), listOf(
                                mutableMapOf(
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17097" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s2>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17097" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s3>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17097" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17097" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "z",
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "#p17214",
                                        "#o17214"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>",
                                                "#p17214" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o17214" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "o",
                                "#p17214",
                                "#o17214",
                                "p"
                        ), listOf(
                                mutableMapOf(
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17214" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17214" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s2>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17214" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17214" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s3>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17214" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17214" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17214" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17214" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "z",
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "#p17387",
                                        "#o17387"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>",
                                                "#p17387" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o17387" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "o",
                                "#p17387",
                                "#o17387",
                                "p"
                        ), listOf(
                                mutableMapOf(
                                        "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17387" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17387" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s2>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17387" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17387" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s3>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17387" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17387" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17387" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17387" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "#p17527",
                                        "#o17527"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>",
                                                "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "o",
                                "#p17527",
                                "#o17527",
                                "p"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o",
                                        "#p17527",
                                        "#o17527",
                                        "p"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "p" to "<http://example.org/p>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "p" to "<http://example.org/p>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "p" to "<http://example.org/p>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                                "p" to "<http://example.org/p>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "z",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "z" to null,
                                                "o" to null
                                        ),
                                        mutableMapOf(
                                                "z" to null,
                                                "o" to null
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p17527",
                                "#o17527",
                                "p",
                                "z",
                                "o"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "#p17527" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17527" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>",
                                        "z" to null,
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "z",
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "#p17645",
                                        "#o17645"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>",
                                                "#p17645" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o17645" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "o",
                                "#p17645",
                                "#o17645",
                                "p"
                        ), listOf(
                                mutableMapOf(
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17645" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17645" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s2>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17645" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17645" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s3>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17645" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17645" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17645" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17645" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "z",
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "<http://example.org/p>",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "z" to null,
                                                "s" to "_:1",
                                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "#p17762",
                                        "#o17762"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/p>",
                                                "#p17762" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o17762" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "o",
                                "#p17762",
                                "#o17762",
                                "p"
                        ), listOf(
                                mutableMapOf(
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17762" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17762" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s2>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17762" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17762" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s3>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17762" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17762" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                        "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example.org/s4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p17762" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o17762" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                        "p" to "<http://example.org/p>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p17955",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "#p17955" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "#p17955" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p17955" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "_:ont",
                                    "#p17955" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p17956",
                                "#o17956"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p17955",
                            "#p17956",
                            "#o17956",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-02.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p18024",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p18024" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p18024" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "#p18024" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/d>",
                                    "#p18024" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "#p18024" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18024" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18024" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/x/d>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18024" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18024" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "_:x"
                                ),
                                mutableMapOf(
                                    "x" to "_:sparql-dl",
                                    "#p18024" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:x",
                                    "#p18024" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p18025",
                                "#o18025"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p18024",
                            "#p18025",
                            "#o18025",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p18024",
                                "#p18025",
                                "#o18025",
                                "c"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p18027",
                                "#y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18027" to "<http://example.org/x/p>",
                                    "#y" to "<http://example.org/x/a>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18024",
                            "#p18025",
                            "#o18025",
                            "c",
                            "#p18027",
                            "#y",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#p18095",
                                        "#o18095"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "#p18095" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o18095" to "<http://example.org/test#Person>"
                                        ),
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#b>",
                                                "#p18095" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o18095" to "<http://example.org/test#Person>"
                                        ),
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#c>",
                                                "#p18095" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o18095" to "<http://example.org/test#Person>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#p18096",
                                        "Y1"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "#p18096" to "<http://example.org/test#name>",
                                                "Y1" to "\"A\""
                                        ),
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#b>",
                                                "#p18096" to "<http://example.org/test#name>",
                                                "Y1" to "\"B\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p18095",
                                "#o18095",
                                "#p18096",
                                "Y1",
                                "X"
                        ), listOf(
                                mutableMapOf(
                                        "#p18095" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o18095" to "<http://example.org/test#Person>",
                                        "#p18096" to "<http://example.org/test#name>",
                                        "Y1" to "\"A\"",
                                        "X" to "<http://example.org/test#a>"
                                ),
                                mutableMapOf(
                                        "#p18095" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o18095" to "<http://example.org/test#Person>",
                                        "#p18096" to "<http://example.org/test#name>",
                                        "Y1" to "\"B\"",
                                        "X" to "<http://example.org/test#b>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#p18095",
                                        "#o18095",
                                        "#p18096",
                                        "Y1",
                                        "X"
                                ), listOf(
                                        mutableMapOf(
                                                "#p18095" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o18095" to "<http://example.org/test#Person>",
                                                "#p18096" to "<http://example.org/test#name>",
                                                "Y1" to "\"A\"",
                                                "X" to "<http://example.org/test#a>"
                                        ),
                                        mutableMapOf(
                                                "#p18095" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                                "#o18095" to "<http://example.org/test#Person>",
                                                "#p18096" to "<http://example.org/test#name>",
                                                "Y1" to "\"B\"",
                                                "X" to "<http://example.org/test#b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#p18098",
                                        "Y2"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "#p18098" to "<http://example.org/test#nick>",
                                                "Y2" to "\"Anick\""
                                        ),
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#b>",
                                                "#p18098" to "<http://example.org/test#nick>",
                                                "Y2" to "\"Bnick\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p18095",
                                "#o18095",
                                "#p18096",
                                "Y1",
                                "#p18098",
                                "Y2",
                                "X"
                        ), listOf(
                                mutableMapOf(
                                        "#p18095" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o18095" to "<http://example.org/test#Person>",
                                        "#p18096" to "<http://example.org/test#name>",
                                        "Y1" to "\"A\"",
                                        "#p18098" to "<http://example.org/test#nick>",
                                        "Y2" to "\"Anick\"",
                                        "X" to "<http://example.org/test#a>"
                                ),
                                mutableMapOf(
                                        "#p18095" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                        "#o18095" to "<http://example.org/test#Person>",
                                        "#p18096" to "<http://example.org/test#name>",
                                        "Y1" to "\"B\"",
                                        "#p18098" to "<http://example.org/test#nick>",
                                        "Y2" to "\"Bnick\"",
                                        "X" to "<http://example.org/test#b>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s18214",
                                "#p18214",
                                "#aa"
                            ), listOf(
                                mutableMapOf(
                                    "#s18214" to "<http://example.org/test#a>",
                                    "#p18214" to "<http://example.org/test#p>",
                                    "#aa" to "<http://example.org/test#aa>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#p18215",
                                "#dd"
                            ), listOf(
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#aa>",
                                    "#p18215" to "<http://example.org/test#r>",
                                    "#dd" to "<http://example.org/test#ee>"
                                ),
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#cc>",
                                    "#p18215" to "<http://example.org/test#r>",
                                    "#dd" to "<http://example.org/test#dd>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s18214",
                            "#p18214",
                            "#p18215",
                            "#dd",
                            "#aa"
                        ), listOf(
                            mutableMapOf(
                                "#s18214" to "<http://example.org/test#a>",
                                "#p18214" to "<http://example.org/test#p>",
                                "#p18215" to "<http://example.org/test#r>",
                                "#dd" to "<http://example.org/test#ee>",
                                "#aa" to "<http://example.org/test#aa>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s18214",
                                "#p18214",
                                "#p18215",
                                "#dd",
                                "#aa"
                            ), listOf(
                                mutableMapOf(
                                    "#s18214" to "<http://example.org/test#a>",
                                    "#p18214" to "<http://example.org/test#p>",
                                    "#p18215" to "<http://example.org/test#r>",
                                    "#dd" to "<http://example.org/test#ee>",
                                    "#aa" to "<http://example.org/test#aa>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#dd",
                                "#p18217",
                                "#bb"
                            ), listOf(
                                mutableMapOf(
                                    "#dd" to "<http://example.org/test#dd>",
                                    "#p18217" to "<http://example.org/test#t>",
                                    "#bb" to "<http://example.org/test#bb>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s18214",
                            "#p18214",
                            "#p18215",
                            "#aa",
                            "#p18217",
                            "#bb",
                            "#dd"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#s18299",
                                        "#p18299",
                                        "#aa"
                                ), listOf(
                                        mutableMapOf(
                                                "#s18299" to "<http://example.org/test#a>",
                                                "#p18299" to "<http://example.org/test#p>",
                                                "#aa" to "<http://example.org/test#aa>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#p18300",
                                        "Y"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#dd>",
                                                "#p18300" to "<http://example.org/test#t>",
                                                "Y" to "<http://example.org/test#bb>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#s18299",
                                "#p18299",
                                "#aa",
                                "X",
                                "#p18300",
                                "Y"
                        ), listOf(
                                mutableMapOf(
                                        "#s18299" to "<http://example.org/test#a>",
                                        "#p18299" to "<http://example.org/test#p>",
                                        "#aa" to "<http://example.org/test#aa>",
                                        "X" to "<http://example.org/test#dd>",
                                        "#p18300" to "<http://example.org/test#t>",
                                        "Y" to "<http://example.org/test#bb>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#s18299",
                                        "#p18299",
                                        "#aa",
                                        "X",
                                        "#p18300",
                                        "Y"
                                ), listOf(
                                        mutableMapOf(
                                                "#s18299" to "<http://example.org/test#a>",
                                                "#p18299" to "<http://example.org/test#p>",
                                                "#aa" to "<http://example.org/test#aa>",
                                                "X" to "<http://example.org/test#dd>",
                                                "#p18300" to "<http://example.org/test#t>",
                                                "Y" to "<http://example.org/test#bb>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "Y",
                                        "#p18302",
                                        "#aa"
                                ), listOf(
                                        mutableMapOf(
                                                "Y" to "<http://example.org/test#bb>",
                                                "#p18302" to "<http://example.org/test#s>",
                                                "#aa" to "<http://example.org/test#aa>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#s18299",
                                "#p18299",
                                "X",
                                "#p18300",
                                "#p18302",
                                "#aa",
                                "Y"
                        ), listOf(
                                mutableMapOf(
                                        "#s18299" to "<http://example.org/test#a>",
                                        "#p18299" to "<http://example.org/test#p>",
                                        "X" to "<http://example.org/test#dd>",
                                        "#p18300" to "<http://example.org/test#t>",
                                        "#p18302" to "<http://example.org/test#s>",
                                        "#aa" to "<http://example.org/test#aa>",
                                        "Y" to "<http://example.org/test#bb>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#s18299",
                                        "#p18299",
                                        "X",
                                        "#p18300",
                                        "#p18302",
                                        "#aa",
                                        "Y"
                                ), listOf(
                                        mutableMapOf(
                                                "#s18299" to "<http://example.org/test#a>",
                                                "#p18299" to "<http://example.org/test#p>",
                                                "X" to "<http://example.org/test#dd>",
                                                "#p18300" to "<http://example.org/test#t>",
                                                "#p18302" to "<http://example.org/test#s>",
                                                "#aa" to "<http://example.org/test#aa>",
                                                "Y" to "<http://example.org/test#bb>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "#p18304",
                                        "Z"
                                ), listOf(
                                        mutableMapOf(
                                                "#aa" to "<http://example.org/test#aa>",
                                                "#p18304" to "<http://example.org/test#r>",
                                                "Z" to "<http://example.org/test#ee>"
                                        ),
                                        mutableMapOf(
                                                "#aa" to "<http://example.org/test#cc>",
                                                "#p18304" to "<http://example.org/test#r>",
                                                "Z" to "<http://example.org/test#dd>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#s18299",
                                "#p18299",
                                "X",
                                "#p18300",
                                "#p18302",
                                "Y",
                                "#p18304",
                                "Z",
                                "#aa"
                        ), listOf(
                                mutableMapOf(
                                        "#s18299" to "<http://example.org/test#a>",
                                        "#p18299" to "<http://example.org/test#p>",
                                        "X" to "<http://example.org/test#dd>",
                                        "#p18300" to "<http://example.org/test#t>",
                                        "#p18302" to "<http://example.org/test#s>",
                                        "Y" to "<http://example.org/test#bb>",
                                        "#p18304" to "<http://example.org/test#r>",
                                        "Z" to "<http://example.org/test#ee>",
                                        "#aa" to "<http://example.org/test#aa>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#p18358",
                                        "#a"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "#p18358" to "<http://example.org/test#p>",
                                                "#a" to "<http://example.org/test#aa>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#a",
                                        "#p18359",
                                        "Y"
                                ), listOf(
                                        mutableMapOf(
                                                "#a" to "<http://example.org/test#aa>",
                                                "#p18359" to "<http://example.org/test#r>",
                                                "Y" to "<http://example.org/test#ee>"
                                        ),
                                        mutableMapOf(
                                                "#a" to "<http://example.org/test#cc>",
                                                "#p18359" to "<http://example.org/test#r>",
                                                "Y" to "<http://example.org/test#dd>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18358",
                                "#p18359",
                                "Y",
                                "#a"
                        ), listOf(
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "#p18358" to "<http://example.org/test#p>",
                                        "#p18359" to "<http://example.org/test#r>",
                                        "Y" to "<http://example.org/test#ee>",
                                        "#a" to "<http://example.org/test#aa>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#p18414",
                                        "#a"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "#p18414" to "<http://example.org/test#p>",
                                                "#a" to "<http://example.org/test#b>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#a",
                                        "#p18415",
                                        "Y"
                                ), listOf(
                                        mutableMapOf(
                                                "#a" to "<http://example.org/test#b>",
                                                "#p18415" to "<http://example.org/test#q>",
                                                "Y" to "<http://example.org/test#c>"
                                        ),
                                        mutableMapOf(
                                                "#a" to "<http://example.org/test#b>",
                                                "#p18415" to "<http://example.org/test#q>",
                                                "Y" to "<http://example.org/test#h>"
                                        ),
                                        mutableMapOf(
                                                "#a" to "<http://example.org/test#b>",
                                                "#p18415" to "<http://example.org/test#q>",
                                                "Y" to "<http://example.org/test#i>"
                                        ),
                                        mutableMapOf(
                                                "#a" to "<http://example.org/test#x>",
                                                "#p18415" to "<http://example.org/test#q>",
                                                "Y" to "<http://example.org/test#x>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18414",
                                "#p18415",
                                "Y",
                                "#a"
                        ), listOf(
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "#p18414" to "<http://example.org/test#p>",
                                        "#p18415" to "<http://example.org/test#q>",
                                        "Y" to "<http://example.org/test#c>",
                                        "#a" to "<http://example.org/test#b>"
                                ),
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "#p18414" to "<http://example.org/test#p>",
                                        "#p18415" to "<http://example.org/test#q>",
                                        "Y" to "<http://example.org/test#h>",
                                        "#a" to "<http://example.org/test#b>"
                                ),
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "#p18414" to "<http://example.org/test#p>",
                                        "#p18415" to "<http://example.org/test#q>",
                                        "Y" to "<http://example.org/test#i>",
                                        "#a" to "<http://example.org/test#b>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18494",
                                "a"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "#p18494" to "<http://example.org/test#p>",
                                    "a" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "#p18495",
                                "b"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18494",
                            "#p18495",
                            "b",
                            "a"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18494",
                                "#p18495",
                                "a",
                                "#p18497",
                                "Y",
                                "b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "Y",
                                "#p18499",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#b>",
                                    "#p18499" to "<http://example.org/test#q>",
                                    "c" to "<http://example.org/test#c>"
                                ),
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#b>",
                                    "#p18499" to "<http://example.org/test#q>",
                                    "c" to "<http://example.org/test#h>"
                                ),
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#b>",
                                    "#p18499" to "<http://example.org/test#q>",
                                    "c" to "<http://example.org/test#i>"
                                ),
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#x>",
                                    "#p18499" to "<http://example.org/test#q>",
                                    "c" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18494",
                            "#p18495",
                            "a",
                            "#p18497",
                            "b",
                            "#p18499",
                            "c",
                            "Y"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "#p18657",
                                "b"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/test#a>",
                                    "#p18657" to "<http://example.org/test#p>",
                                    "b" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "#p18658",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "b" to "<http://example.org/test#b>",
                                    "#p18658" to "<http://www.w3.org/2002/07/owl#sameAs>",
                                    "x" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p18657",
                            "#p18658",
                            "x",
                            "b"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/test#a>",
                                "#p18657" to "<http://example.org/test#p>",
                                "#p18658" to "<http://www.w3.org/2002/07/owl#sameAs>",
                                "x" to "<http://example.org/test#x>",
                                "b" to "<http://example.org/test#b>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "#p18657",
                                "#p18658",
                                "x",
                                "b"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/test#a>",
                                    "#p18657" to "<http://example.org/test#p>",
                                    "#p18658" to "<http://www.w3.org/2002/07/owl#sameAs>",
                                    "x" to "<http://example.org/test#x>",
                                    "b" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p18660",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p18660" to "<http://example.org/test#q>",
                                    "x" to "<http://example.org/test#c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p18660" to "<http://example.org/test#q>",
                                    "x" to "<http://example.org/test#d>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#x>",
                                    "#p18660" to "<http://example.org/test#q>",
                                    "x" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p18657",
                            "#p18658",
                            "b",
                            "#p18660",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/test#a>",
                                "#p18657" to "<http://example.org/test#p>",
                                "#p18658" to "<http://www.w3.org/2002/07/owl#sameAs>",
                                "b" to "<http://example.org/test#b>",
                                "#p18660" to "<http://example.org/test#q>",
                                "x" to "<http://example.org/test#x>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18773",
                                "#p18825",
                                "#o18825"
                            ), listOf(
                                mutableMapOf(
                                    "#_18773" to "_:_18758",
                                    "#p18825" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18825" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_18773" to "_:_18759",
                                    "#p18825" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18825" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18773",
                                "#p18826",
                                "#o18826"
                            ), listOf(
                                mutableMapOf(
                                    "#_18773" to "_:_18758",
                                    "#p18826" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o18826" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_18773" to "_:_18759",
                                    "#p18826" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o18826" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18825",
                            "#o18825",
                            "#p18826",
                            "#o18826",
                            "#_18773"
                        ), listOf(
                            mutableMapOf(
                                "#p18825" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o18825" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p18826" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o18826" to "<http://example.org/test#hasChild>",
                                "#_18773" to "_:_18758"
                            ),
                            mutableMapOf(
                                "#p18825" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o18825" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p18826" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o18826" to "<http://example.org/test#hasChild>",
                                "#_18773" to "_:_18759"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p18825",
                                "#o18825",
                                "#p18826",
                                "#o18826",
                                "#_18773"
                            ), listOf(
                                mutableMapOf(
                                    "#p18825" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18825" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p18826" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o18826" to "<http://example.org/test#hasChild>",
                                    "#_18773" to "_:_18758"
                                ),
                                mutableMapOf(
                                    "#p18825" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18825" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p18826" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o18826" to "<http://example.org/test#hasChild>",
                                    "#_18773" to "_:_18759"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18773",
                                "#p18828",
                                "#o18828"
                            ), listOf(
                                mutableMapOf(
                                    "#_18773" to "_:_18758",
                                    "#p18828" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                    "#o18828" to "<http://www.w3.org/2002/07/owl#Thing>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18825",
                            "#o18825",
                            "#p18826",
                            "#o18826",
                            "#p18828",
                            "#o18828",
                            "#_18773"
                        ), listOf(
                            mutableMapOf(
                                "#p18825" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o18825" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p18826" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o18826" to "<http://example.org/test#hasChild>",
                                "#p18828" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                "#o18828" to "<http://www.w3.org/2002/07/owl#Thing>",
                                "#_18773" to "_:_18758"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p18825",
                                "#o18825",
                                "#p18826",
                                "#o18826",
                                "#p18828",
                                "#o18828",
                                "#_18773"
                            ), listOf(
                                mutableMapOf(
                                    "#p18825" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18825" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p18826" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o18826" to "<http://example.org/test#hasChild>",
                                    "#p18828" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                    "#o18828" to "<http://www.w3.org/2002/07/owl#Thing>",
                                    "#_18773" to "_:_18758"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p18830",
                                "#_18773"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "_:_18759"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18752",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18755",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18758",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18759",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18760",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p18830" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18773" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18825",
                            "#o18825",
                            "#p18826",
                            "#o18826",
                            "#p18828",
                            "#o18828",
                            "parent",
                            "#p18830",
                            "#_18773"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18862",
                                "#p18919",
                                "#o18919"
                            ), listOf(
                                mutableMapOf(
                                    "#_18862" to "_:_18844",
                                    "#p18919" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18919" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_18862" to "_:_18845",
                                    "#p18919" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18919" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18862",
                                "#p18920",
                                "#o18920"
                            ), listOf(
                                mutableMapOf(
                                    "#_18862" to "_:_18844",
                                    "#p18920" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o18920" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_18862" to "_:_18845",
                                    "#p18920" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o18920" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18919",
                            "#o18919",
                            "#p18920",
                            "#o18920",
                            "#_18862"
                        ), listOf(
                            mutableMapOf(
                                "#p18919" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o18919" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p18920" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o18920" to "<http://example.org/test#hasChild>",
                                "#_18862" to "_:_18844"
                            ),
                            mutableMapOf(
                                "#p18919" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o18919" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p18920" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o18920" to "<http://example.org/test#hasChild>",
                                "#_18862" to "_:_18845"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p18919",
                                "#o18919",
                                "#p18920",
                                "#o18920",
                                "#_18862"
                            ), listOf(
                                mutableMapOf(
                                    "#p18919" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18919" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p18920" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o18920" to "<http://example.org/test#hasChild>",
                                    "#_18862" to "_:_18844"
                                ),
                                mutableMapOf(
                                    "#p18919" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18919" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p18920" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o18920" to "<http://example.org/test#hasChild>",
                                    "#_18862" to "_:_18845"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18862",
                                "#p18922",
                                "#o18922"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18919",
                            "#o18919",
                            "#p18920",
                            "#o18920",
                            "#p18922",
                            "#o18922",
                            "#_18862"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p18919",
                                "#o18919",
                                "#p18920",
                                "#o18920",
                                "#p18922",
                                "#o18922",
                                "#_18862"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p18924",
                                "#_18862"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "_:_18845"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18838",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18841",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18844",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18845",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18846",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p18924" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18862" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18919",
                            "#o18919",
                            "#p18920",
                            "#o18920",
                            "#p18922",
                            "#o18922",
                            "parent",
                            "#p18924",
                            "#_18862"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_18953",
                                "#p19005",
                                "#o19005"
                            ), listOf(
                                mutableMapOf(
                                    "#_18953" to "_:_18938",
                                    "#p19005" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19005" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_18953" to "_:_18939",
                                    "#p19005" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19005" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18953",
                                "#p19006",
                                "#o19006"
                            ), listOf(
                                mutableMapOf(
                                    "#_18953" to "_:_18938",
                                    "#p19006" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19006" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_18953" to "_:_18939",
                                    "#p19006" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19006" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19005",
                            "#o19005",
                            "#p19006",
                            "#o19006",
                            "#_18953"
                        ), listOf(
                            mutableMapOf(
                                "#p19005" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19005" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19006" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19006" to "<http://example.org/test#hasChild>",
                                "#_18953" to "_:_18938"
                            ),
                            mutableMapOf(
                                "#p19005" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19005" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19006" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19006" to "<http://example.org/test#hasChild>",
                                "#_18953" to "_:_18939"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p19005",
                                "#o19005",
                                "#p19006",
                                "#o19006",
                                "#_18953"
                            ), listOf(
                                mutableMapOf(
                                    "#p19005" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19005" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19006" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19006" to "<http://example.org/test#hasChild>",
                                    "#_18953" to "_:_18938"
                                ),
                                mutableMapOf(
                                    "#p19005" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19005" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19006" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19006" to "<http://example.org/test#hasChild>",
                                    "#_18953" to "_:_18939"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18953",
                                "#p19008",
                                "#o19008"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19005",
                            "#o19005",
                            "#p19006",
                            "#o19006",
                            "#p19008",
                            "#o19008",
                            "#_18953"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p19005",
                                "#o19005",
                                "#p19006",
                                "#o19006",
                                "#p19008",
                                "#o19008",
                                "#_18953"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19010",
                                "#_18953"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "_:_18939"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18932",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18935",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18938",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18939",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18940",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19010" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18953" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19005",
                            "#o19005",
                            "#p19006",
                            "#o19006",
                            "#p19008",
                            "#o19008",
                            "parent",
                            "#p19010",
                            "#_18953"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19040",
                                "#p19105",
                                "#o19105"
                            ), listOf(
                                mutableMapOf(
                                    "#_19040" to "_:_19024",
                                    "#p19105" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19105" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_19040" to "_:_19025",
                                    "#p19105" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19105" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19040",
                                "#p19106",
                                "#o19106"
                            ), listOf(
                                mutableMapOf(
                                    "#_19040" to "_:_19024",
                                    "#p19106" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19106" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_19040" to "_:_19025",
                                    "#p19106" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19106" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19105",
                            "#o19105",
                            "#p19106",
                            "#o19106",
                            "#_19040"
                        ), listOf(
                            mutableMapOf(
                                "#p19105" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19105" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19106" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19106" to "<http://example.org/test#hasChild>",
                                "#_19040" to "_:_19024"
                            ),
                            mutableMapOf(
                                "#p19105" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19105" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19106" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19106" to "<http://example.org/test#hasChild>",
                                "#_19040" to "_:_19025"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p19105",
                                "#o19105",
                                "#p19106",
                                "#o19106",
                                "#_19040"
                            ), listOf(
                                mutableMapOf(
                                    "#p19105" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19105" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19106" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19106" to "<http://example.org/test#hasChild>",
                                    "#_19040" to "_:_19024"
                                ),
                                mutableMapOf(
                                    "#p19105" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19105" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19106" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19106" to "<http://example.org/test#hasChild>",
                                    "#_19040" to "_:_19025"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19040",
                                "#p19108",
                                "#o19108"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19105",
                            "#o19105",
                            "#p19106",
                            "#o19106",
                            "#p19108",
                            "#o19108",
                            "#_19040"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p19105",
                                "#o19105",
                                "#p19106",
                                "#o19106",
                                "#p19108",
                                "#o19108",
                                "#p19110",
                                "#o19110",
                                "#_19040"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19112",
                                "#_19040"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "_:_19025"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19018",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19021",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19024",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19025",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19026",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19112" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19040" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19105",
                            "#o19105",
                            "#p19106",
                            "#o19106",
                            "#p19108",
                            "#o19108",
                            "#p19110",
                            "#o19110",
                            "parent",
                            "#p19112",
                            "#_19040"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19143",
                                "#p19208",
                                "#o19208"
                            ), listOf(
                                mutableMapOf(
                                    "#_19143" to "_:_19127",
                                    "#p19208" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19208" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_19143" to "_:_19128",
                                    "#p19208" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19208" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19143",
                                "#p19209",
                                "#o19209"
                            ), listOf(
                                mutableMapOf(
                                    "#_19143" to "_:_19127",
                                    "#p19209" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19209" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_19143" to "_:_19128",
                                    "#p19209" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19209" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19208",
                            "#o19208",
                            "#p19209",
                            "#o19209",
                            "#_19143"
                        ), listOf(
                            mutableMapOf(
                                "#p19208" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19208" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19209" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19209" to "<http://example.org/test#hasChild>",
                                "#_19143" to "_:_19127"
                            ),
                            mutableMapOf(
                                "#p19208" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19208" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19209" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19209" to "<http://example.org/test#hasChild>",
                                "#_19143" to "_:_19128"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p19208",
                                "#o19208",
                                "#p19209",
                                "#o19209",
                                "#_19143"
                            ), listOf(
                                mutableMapOf(
                                    "#p19208" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19208" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19209" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19209" to "<http://example.org/test#hasChild>",
                                    "#_19143" to "_:_19127"
                                ),
                                mutableMapOf(
                                    "#p19208" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19208" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19209" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19209" to "<http://example.org/test#hasChild>",
                                    "#_19143" to "_:_19128"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19143",
                                "#p19211",
                                "#o19211"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19208",
                            "#o19208",
                            "#p19209",
                            "#o19209",
                            "#p19211",
                            "#o19211",
                            "#_19143"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p19208",
                                "#o19208",
                                "#p19209",
                                "#o19209",
                                "#p19211",
                                "#o19211",
                                "#p19213",
                                "#o19213",
                                "#_19143"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19215",
                                "#_19143"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "_:_19128"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19121",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19124",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19127",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19128",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19129",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19215" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19143" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19208",
                            "#o19208",
                            "#p19209",
                            "#o19209",
                            "#p19211",
                            "#o19211",
                            "#p19213",
                            "#o19213",
                            "parent",
                            "#p19215",
                            "#_19143"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19246",
                                "#p19311",
                                "#o19311"
                            ), listOf(
                                mutableMapOf(
                                    "#_19246" to "_:_19230",
                                    "#p19311" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19311" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_19246" to "_:_19231",
                                    "#p19311" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19311" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19246",
                                "#p19312",
                                "#o19312"
                            ), listOf(
                                mutableMapOf(
                                    "#_19246" to "_:_19230",
                                    "#p19312" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19312" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_19246" to "_:_19231",
                                    "#p19312" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19312" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19311",
                            "#o19311",
                            "#p19312",
                            "#o19312",
                            "#_19246"
                        ), listOf(
                            mutableMapOf(
                                "#p19311" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19311" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19312" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19312" to "<http://example.org/test#hasChild>",
                                "#_19246" to "_:_19230"
                            ),
                            mutableMapOf(
                                "#p19311" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19311" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19312" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19312" to "<http://example.org/test#hasChild>",
                                "#_19246" to "_:_19231"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p19311",
                                "#o19311",
                                "#p19312",
                                "#o19312",
                                "#_19246"
                            ), listOf(
                                mutableMapOf(
                                    "#p19311" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19311" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19312" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19312" to "<http://example.org/test#hasChild>",
                                    "#_19246" to "_:_19230"
                                ),
                                mutableMapOf(
                                    "#p19311" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19311" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19312" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19312" to "<http://example.org/test#hasChild>",
                                    "#_19246" to "_:_19231"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19246",
                                "#p19314",
                                "#o19314"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19311",
                            "#o19311",
                            "#p19312",
                            "#o19312",
                            "#p19314",
                            "#o19314",
                            "#_19246"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p19311",
                                "#o19311",
                                "#p19312",
                                "#o19312",
                                "#p19314",
                                "#o19314",
                                "#p19316",
                                "#o19316",
                                "#_19246"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19318",
                                "#_19246"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "_:_19231"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19224",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19227",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19230",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19231",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19232",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19318" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19246" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19311",
                            "#o19311",
                            "#p19312",
                            "#o19312",
                            "#p19314",
                            "#o19314",
                            "#p19316",
                            "#o19316",
                            "parent",
                            "#p19318",
                            "#_19246"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19350",
                                "#p19404",
                                "#o19404"
                            ), listOf(
                                mutableMapOf(
                                    "#_19350" to "_:_19333",
                                    "#p19404" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19404" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_19350" to "_:_19334",
                                    "#p19404" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19404" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19350",
                                "#p19405",
                                "#o19405"
                            ), listOf(
                                mutableMapOf(
                                    "#_19350" to "_:_19333",
                                    "#p19405" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19405" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_19350" to "_:_19334",
                                    "#p19405" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19405" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19404",
                            "#o19404",
                            "#p19405",
                            "#o19405",
                            "#_19350"
                        ), listOf(
                            mutableMapOf(
                                "#p19404" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19404" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19405" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19405" to "<http://example.org/test#hasChild>",
                                "#_19350" to "_:_19333"
                            ),
                            mutableMapOf(
                                "#p19404" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19404" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19405" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19405" to "<http://example.org/test#hasChild>",
                                "#_19350" to "_:_19334"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p19404",
                                "#o19404",
                                "#p19405",
                                "#o19405",
                                "#_19350"
                            ), listOf(
                                mutableMapOf(
                                    "#p19404" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19404" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19405" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19405" to "<http://example.org/test#hasChild>",
                                    "#_19350" to "_:_19333"
                                ),
                                mutableMapOf(
                                    "#p19404" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19404" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19405" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19405" to "<http://example.org/test#hasChild>",
                                    "#_19350" to "_:_19334"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19350",
                                "#p19407",
                                "#o19407"
                            ), listOf(
                                mutableMapOf(
                                    "#_19350" to "_:_19333",
                                    "#p19407" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                    "#o19407" to "<http://www.w3.org/2002/07/owl#Thing>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19404",
                            "#o19404",
                            "#p19405",
                            "#o19405",
                            "#p19407",
                            "#o19407",
                            "#_19350"
                        ), listOf(
                            mutableMapOf(
                                "#p19404" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19404" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19405" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19405" to "<http://example.org/test#hasChild>",
                                "#p19407" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                "#o19407" to "<http://www.w3.org/2002/07/owl#Thing>",
                                "#_19350" to "_:_19333"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p19404",
                                "#o19404",
                                "#p19405",
                                "#o19405",
                                "#p19407",
                                "#o19407",
                                "#_19350"
                            ), listOf(
                                mutableMapOf(
                                    "#p19404" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19404" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19405" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19405" to "<http://example.org/test#hasChild>",
                                    "#p19407" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                    "#o19407" to "<http://www.w3.org/2002/07/owl#Thing>",
                                    "#_19350" to "_:_19333"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#p19409",
                                "#_19350"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19404",
                            "#o19404",
                            "#p19405",
                            "#o19405",
                            "#p19407",
                            "#o19407",
                            "C",
                            "#p19409",
                            "#_19350"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "C",
                                "#p19505",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b",
                                "#p19506",
                                "#o19506"
                            ), listOf(
                                mutableMapOf(
                                    "#b" to "_:_19423",
                                    "#p19506" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19506" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#b" to "_:_19424",
                                    "#p19506" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19506" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#p19505",
                            "#p19506",
                            "#o19506",
                            "#b"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "C",
                                "#p19505",
                                "#p19506",
                                "#o19506",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b",
                                "#p19508",
                                "#o19508"
                            ), listOf(
                                mutableMapOf(
                                    "#b" to "_:_19423",
                                    "#p19508" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19508" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#b" to "_:_19424",
                                    "#p19508" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19508" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#p19505",
                            "#p19506",
                            "#o19506",
                            "#p19508",
                            "#o19508",
                            "#b"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "C",
                                "#p19505",
                                "#p19506",
                                "#o19506",
                                "#p19508",
                                "#o19508",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b",
                                "#p19510",
                                "#o19510"
                            ), listOf(
                                mutableMapOf(
                                    "#b" to "_:_19423",
                                    "#p19510" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                    "#o19510" to "<http://www.w3.org/2002/07/owl#Thing>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#p19505",
                            "#p19506",
                            "#o19506",
                            "#p19508",
                            "#o19508",
                            "#p19510",
                            "#o19510",
                            "#b"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p19608",
                                "#o19608",
                                "#p19609",
                                "#p19611",
                                "#o19611",
                                "#p19613",
                                "#o19613",
                                "#_19544",
                                "#_19536",
                                "#p19615",
                                "#_19539"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p19617",
                                "#_19536"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p19617" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19536" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p19617" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19536" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p19617" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19536" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19617" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19536" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19617" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19536" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p19617" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19536" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p19617" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19536" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19617" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19536" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19617" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19536" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19617" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19536" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19617" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19536" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19617" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19536" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p19617" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19536" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19608",
                            "#o19608",
                            "#p19609",
                            "#p19611",
                            "#o19611",
                            "#p19613",
                            "#o19613",
                            "#_19544",
                            "#p19615",
                            "#_19539",
                            "x",
                            "#p19617",
                            "#_19536"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple1.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p19742",
                                "#o19742",
                                "#p19743",
                                "#o19743",
                                "#p19745",
                                "#o19745",
                                "#p19747",
                                "#o19747",
                                "#p19749",
                                "#p19751",
                                "#_19647",
                                "#p19753",
                                "#o19753",
                                "#_19657",
                                "#_19640",
                                "#p19755",
                                "#_19643"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p19757",
                                "#_19640"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p19757" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19640" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p19757" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19640" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p19757" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19640" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19757" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19640" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19757" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19640" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p19757" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19640" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p19757" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19640" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19757" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19640" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19757" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19640" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19757" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19640" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19757" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19640" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19757" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19640" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p19757" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19640" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19742",
                            "#o19742",
                            "#p19743",
                            "#o19743",
                            "#p19745",
                            "#o19745",
                            "#p19747",
                            "#o19747",
                            "#p19749",
                            "#p19751",
                            "#_19647",
                            "#p19753",
                            "#o19753",
                            "#_19657",
                            "#p19755",
                            "#_19643",
                            "x",
                            "#p19757",
                            "#_19640"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple2.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p19885",
                                "#o19885",
                                "#p19886",
                                "#o19886",
                                "#p19888",
                                "#o19888",
                                "#p19890",
                                "#p19892",
                                "#o19892",
                                "#p19894",
                                "#o19894",
                                "#_19799",
                                "#p19896",
                                "#_19794",
                                "#p19898",
                                "#_19783",
                                "#_19791"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p19900",
                                "#_19783"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p19900" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19783" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p19900" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19783" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p19900" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19783" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19900" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19783" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19900" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19783" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p19900" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19783" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p19900" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19783" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19900" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19783" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19900" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19783" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19900" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19783" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19900" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19783" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19900" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19783" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p19900" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19783" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19885",
                            "#o19885",
                            "#p19886",
                            "#o19886",
                            "#p19888",
                            "#o19888",
                            "#p19890",
                            "#p19892",
                            "#o19892",
                            "#p19894",
                            "#o19894",
                            "#_19799",
                            "#p19896",
                            "#_19794",
                            "#p19898",
                            "#_19791",
                            "x",
                            "#p19900",
                            "#_19783"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19926",
                                "#p20007",
                                "#o20007"
                            ), listOf(
                                mutableMapOf(
                                    "#_19926" to "<http://example.org/test#A>",
                                    "#p20007" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20007" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_19926" to "<http://example.org/test#B>",
                                    "#p20007" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20007" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_19926" to "<http://example.org/test#C>",
                                    "#p20007" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20007" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19931",
                                "#p20008",
                                "#o20008"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19926",
                            "#p20007",
                            "#o20007",
                            "#_19931",
                            "#p20008",
                            "#o20008"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p20007",
                                "#o20007",
                                "#p20008",
                                "#o20008",
                                "#p20010",
                                "#p20012",
                                "#o20012",
                                "#p20014",
                                "#o20014",
                                "#_19936",
                                "#p20016",
                                "#_19926",
                                "#_19931"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20018",
                                "#_19926"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20018" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19926" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20018" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19926" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20018" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19926" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20018" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19926" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20018" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19926" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20018" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19926" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20018" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19926" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20018" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19926" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20018" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19926" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20018" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19926" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20018" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19926" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20018" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19926" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20018" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19926" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20007",
                            "#o20007",
                            "#p20008",
                            "#o20008",
                            "#p20010",
                            "#p20012",
                            "#o20012",
                            "#p20014",
                            "#o20014",
                            "#_19936",
                            "#p20016",
                            "#_19931",
                            "x",
                            "#p20018",
                            "#_19926"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p20153",
                                "#o20153",
                                "#p20154",
                                "#o20154",
                                "#_20042"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20050",
                                "#p20156",
                                "#o20156"
                            ), listOf(
                                mutableMapOf(
                                    "#_20050" to "<http://example.org/test#A>",
                                    "#p20156" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20156" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20050" to "<http://example.org/test#B>",
                                    "#p20156" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20156" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20050" to "<http://example.org/test#C>",
                                    "#p20156" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20156" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20153",
                            "#o20153",
                            "#p20154",
                            "#o20154",
                            "#_20042",
                            "#_20050",
                            "#p20156",
                            "#o20156"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p20153",
                                "#o20153",
                                "#p20154",
                                "#o20154",
                                "#p20156",
                                "#o20156",
                                "#p20158",
                                "#o20158",
                                "#p20160",
                                "#p20162",
                                "#o20162",
                                "#p20164",
                                "#o20164",
                                "#_20060",
                                "#p20166",
                                "#_20055",
                                "#p20168",
                                "#_20042",
                                "#_20050"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20170",
                                "#_20042"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20170" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20042" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20170" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20042" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20170" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20042" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20170" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20042" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20170" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20042" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20170" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20042" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20170" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20042" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20170" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20042" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20170" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20042" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20170" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20042" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20170" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20042" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20170" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20042" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20170" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20042" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20153",
                            "#o20153",
                            "#p20154",
                            "#o20154",
                            "#p20156",
                            "#o20156",
                            "#p20158",
                            "#o20158",
                            "#p20160",
                            "#p20162",
                            "#o20162",
                            "#p20164",
                            "#o20164",
                            "#_20060",
                            "#p20166",
                            "#_20055",
                            "#p20168",
                            "#_20050",
                            "x",
                            "#p20170",
                            "#_20042"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p20327",
                                "#o20327",
                                "#p20328",
                                "#o20328",
                                "#_20197"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20205",
                                "#p20330",
                                "#o20330"
                            ), listOf(
                                mutableMapOf(
                                    "#_20205" to "<http://example.org/test#A>",
                                    "#p20330" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20330" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20205" to "<http://example.org/test#B>",
                                    "#p20330" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20330" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20205" to "<http://example.org/test#C>",
                                    "#p20330" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20330" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20327",
                            "#o20327",
                            "#p20328",
                            "#o20328",
                            "#_20197",
                            "#_20205",
                            "#p20330",
                            "#o20330"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p20327",
                                "#o20327",
                                "#p20328",
                                "#o20328",
                                "#p20330",
                                "#o20330",
                                "#p20332",
                                "#o20332",
                                "#p20334",
                                "#p20336",
                                "#o20336",
                                "#p20338",
                                "#_20215",
                                "#p20340",
                                "#o20340",
                                "#p20342",
                                "#o20342",
                                "#_20220",
                                "#p20344",
                                "#_20210",
                                "#p20346",
                                "#_20197",
                                "#_20205"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20348",
                                "#_20197"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20348" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20197" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20348" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20197" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20348" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20197" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20348" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20197" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20348" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20197" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20348" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20197" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20348" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20197" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20348" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20197" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20348" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20197" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20348" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20197" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20348" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20197" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20348" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20197" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20348" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20197" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20327",
                            "#o20327",
                            "#p20328",
                            "#o20328",
                            "#p20330",
                            "#o20330",
                            "#p20332",
                            "#o20332",
                            "#p20334",
                            "#p20336",
                            "#o20336",
                            "#p20338",
                            "#_20215",
                            "#p20340",
                            "#o20340",
                            "#p20342",
                            "#o20342",
                            "#_20220",
                            "#p20344",
                            "#_20210",
                            "#p20346",
                            "#_20205",
                            "x",
                            "#p20348",
                            "#_20197"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20377",
                                "#p20515",
                                "#o20515"
                            ), listOf(
                                mutableMapOf(
                                    "#_20377" to "<http://example.org/test#A>",
                                    "#p20515" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20515" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20377" to "<http://example.org/test#B>",
                                    "#p20515" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20515" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20377" to "<http://example.org/test#C>",
                                    "#p20515" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20515" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20382",
                                "#p20516",
                                "#o20516"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20377",
                            "#p20515",
                            "#o20515",
                            "#_20382",
                            "#p20516",
                            "#o20516"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20377",
                                "#p20515",
                                "#o20515",
                                "#_20382",
                                "#p20516",
                                "#o20516"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20386",
                                "#p20518",
                                "#o20518"
                            ), listOf(
                                mutableMapOf(
                                    "#_20386" to "<http://example.org/test#A>",
                                    "#p20518" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20518" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20386" to "<http://example.org/test#B>",
                                    "#p20518" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20518" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20386" to "<http://example.org/test#C>",
                                    "#p20518" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20518" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20377",
                            "#p20515",
                            "#o20515",
                            "#_20382",
                            "#p20516",
                            "#o20516",
                            "#_20386",
                            "#p20518",
                            "#o20518"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p20515",
                                "#o20515",
                                "#p20516",
                                "#o20516",
                                "#p20518",
                                "#o20518",
                                "#p20520",
                                "#o20520",
                                "#p20522",
                                "#p20524",
                                "#o20524",
                                "#p20526",
                                "#o20526",
                                "#_20396",
                                "#p20528",
                                "#_20391",
                                "#p20530",
                                "#p20532",
                                "#_20386",
                                "#p20534",
                                "#o20534",
                                "#_20402",
                                "#p20536",
                                "#_20377",
                                "#_20382"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20538",
                                "#_20377"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20538" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20377" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20538" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20377" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20538" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20377" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20538" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20377" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20538" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20377" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20538" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20377" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20538" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20377" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20538" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20377" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20538" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20377" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20538" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20377" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20538" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20377" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20538" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20377" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20538" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20377" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20515",
                            "#o20515",
                            "#p20516",
                            "#o20516",
                            "#p20518",
                            "#o20518",
                            "#p20520",
                            "#o20520",
                            "#p20522",
                            "#p20524",
                            "#o20524",
                            "#p20526",
                            "#o20526",
                            "#_20396",
                            "#p20528",
                            "#_20391",
                            "#p20530",
                            "#p20532",
                            "#_20386",
                            "#p20534",
                            "#o20534",
                            "#_20402",
                            "#p20536",
                            "#_20382",
                            "x",
                            "#p20538",
                            "#_20377"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p20652",
                                "#o20652",
                                "#p20653",
                                "#o20653",
                                "#p20655",
                                "#o20655",
                                "#p20657",
                                "#o20657",
                                "#p20659",
                                "#o20659",
                                "#p20661",
                                "#_20568",
                                "#_20576"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20663",
                                "#_20568"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20663" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20568" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20663" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20568" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20663" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20568" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20663" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20568" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20663" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20568" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20663" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20568" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20663" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20568" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20663" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20568" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20663" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20568" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20663" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20568" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20663" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20568" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20663" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20568" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20663" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20568" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20652",
                            "#o20652",
                            "#p20653",
                            "#o20653",
                            "#p20655",
                            "#o20655",
                            "#p20657",
                            "#o20657",
                            "#p20659",
                            "#o20659",
                            "#p20661",
                            "#_20576",
                            "x",
                            "#p20663",
                            "#_20568"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple8.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#s23791",
                                        "#p23791",
                                        "str1"
                                ), listOf(
                                        mutableMapOf(
                                                "#s23791" to "<http://example.org/s6>",
                                                "#p23791" to "<http://example.org/str>",
                                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#s23792",
                                        "#p23792",
                                        "str2"
                                ), listOf(
                                        mutableMapOf(
                                                "#s23792" to "<http://example.org/s7>",
                                                "#p23792" to "<http://example.org/str>",
                                                "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#s23791",
                                "#p23791",
                                "str1",
                                "#s23792",
                                "#p23792",
                                "str2"
                        ), listOf(
                                mutableMapOf(
                                        "#s23791" to "<http://example.org/s6>",
                                        "#p23791" to "<http://example.org/str>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "#s23792" to "<http://example.org/s7>",
                                        "#p23792" to "<http://example.org/str>",
                                        "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#s23819",
                                        "#p23819",
                                        "str1"
                                ), listOf(
                                        mutableMapOf(
                                                "#s23819" to "<http://example.org/s6>",
                                                "#p23819" to "<http://example.org/str>",
                                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#s23820",
                                        "#p23820",
                                        "str2"
                                ), listOf(
                                        mutableMapOf(
                                                "#s23820" to "<http://example.org/s7>",
                                                "#p23820" to "<http://example.org/str>",
                                                "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#s23819",
                                "#p23819",
                                "str1",
                                "#s23820",
                                "#p23820",
                                "str2"
                        ), listOf(
                                mutableMapOf(
                                        "#s23819" to "<http://example.org/s6>",
                                        "#p23819" to "<http://example.org/str>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "#s23820" to "<http://example.org/s7>",
                                        "#p23820" to "<http://example.org/str>",
                                        "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s1",
                                        "#p23905",
                                        "str1"
                                ), listOf(
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s1>",
                                                "#p23905" to "<http://example.org/str>",
                                                "str1" to "\"123\""
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s2>",
                                                "#p23905" to "<http://example.org/str>",
                                                "str1" to "\"日本語\"@ja"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s3>",
                                                "#p23905" to "<http://example.org/str>",
                                                "str1" to "\"english\"@en"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s4>",
                                                "#p23905" to "<http://example.org/str>",
                                                "str1" to "\"français\"@fr"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s5>",
                                                "#p23905" to "<http://example.org/str>",
                                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s6>",
                                                "#p23905" to "<http://example.org/str>",
                                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s1" to "<http://example.org/s7>",
                                                "#p23905" to "<http://example.org/str>",
                                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s2",
                                        "#p23906",
                                        "str2"
                                ), listOf(
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s1>",
                                                "#p23906" to "<http://example.org/str>",
                                                "str2" to "\"123\""
                                        ),
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s2>",
                                                "#p23906" to "<http://example.org/str>",
                                                "str2" to "\"日本語\"@ja"
                                        ),
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s3>",
                                                "#p23906" to "<http://example.org/str>",
                                                "str2" to "\"english\"@en"
                                        ),
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s4>",
                                                "#p23906" to "<http://example.org/str>",
                                                "str2" to "\"français\"@fr"
                                        ),
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s5>",
                                                "#p23906" to "<http://example.org/str>",
                                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s6>",
                                                "#p23906" to "<http://example.org/str>",
                                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s2" to "<http://example.org/s7>",
                                                "#p23906" to "<http://example.org/str>",
                                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s1",
                                "#p23905",
                                "str1",
                                "s2",
                                "#p23906",
                                "str2"
                        ), listOf(
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s1>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s1>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s1>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s1>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s1>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s1>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s1>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s2>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s2>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s2>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s2>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s2>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s2>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s2>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s3>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s3>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s3>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s3>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s3>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s3>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s3>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s4>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s4>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s4>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s4>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s4>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s4>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s4>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s5>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s5>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s5>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s5>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s5>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s5>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s5>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s6>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s6>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s6>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s6>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s6>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s6>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s6>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s1>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"123\"",
                                        "s2" to "<http://example.org/s7>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s2>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"日本語\"@ja",
                                        "s2" to "<http://example.org/s7>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s3>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"english\"@en",
                                        "s2" to "<http://example.org/s7>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s4>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"français\"@fr",
                                        "s2" to "<http://example.org/s7>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s5>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s7>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s6>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "s2" to "<http://example.org/s7>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s1" to "<http://example.org/s7>",
                                        "#p23905" to "<http://example.org/str>",
                                        "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s2" to "<http://example.org/s7>",
                                        "#p23906" to "<http://example.org/str>",
                                        "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p26183",
                                        "x"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/x1>",
                                                "#p26183" to "<http://example/p>",
                                                "x" to "\"a\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x2>",
                                                "#p26183" to "<http://example/p>",
                                                "x" to "_:b"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x3>",
                                                "#p26183" to "<http://example/p>",
                                                "x" to "<http://example/a>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x4>",
                                                "#p26183" to "<http://example/p>",
                                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x5>",
                                                "#p26183" to "<http://example/p>",
                                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x6>",
                                                "#p26183" to "<http://example/p>",
                                                "x" to "\"1\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x7>",
                                                "#p26183" to "<http://example/p>",
                                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x8>",
                                                "#p26183" to "<http://example/p>",
                                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p26184",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/x1>",
                                                "#p26184" to "<http://example/q>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x2>",
                                                "#p26184" to "<http://example/q>",
                                                "y" to "\"1\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x3>",
                                                "#p26184" to "<http://example/q>",
                                                "y" to "\"1\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x4>",
                                                "#p26184" to "<http://example/q>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x5>",
                                                "#p26184" to "<http://example/q>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x6>",
                                                "#p26184" to "<http://example/q>",
                                                "y" to "\"2\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x7>",
                                                "#p26184" to "<http://example/q>",
                                                "y" to "\"2\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x8>",
                                                "#p26184" to "<http://example/q>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p26183",
                                "x",
                                "#p26184",
                                "y",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "#p26183" to "<http://example/p>",
                                        "x" to "\"a\"",
                                        "#p26184" to "<http://example/q>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example/x1>"
                                ),
                                mutableMapOf(
                                        "#p26183" to "<http://example/p>",
                                        "x" to "_:b",
                                        "#p26184" to "<http://example/q>",
                                        "y" to "\"1\"",
                                        "s" to "<http://example/x2>"
                                ),
                                mutableMapOf(
                                        "#p26183" to "<http://example/p>",
                                        "x" to "<http://example/a>",
                                        "#p26184" to "<http://example/q>",
                                        "y" to "\"1\"",
                                        "s" to "<http://example/x3>"
                                ),
                                mutableMapOf(
                                        "#p26183" to "<http://example/p>",
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p26184" to "<http://example/q>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example/x4>"
                                ),
                                mutableMapOf(
                                        "#p26183" to "<http://example/p>",
                                        "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                        "#p26184" to "<http://example/q>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example/x5>"
                                ),
                                mutableMapOf(
                                        "#p26183" to "<http://example/p>",
                                        "x" to "\"1\"",
                                        "#p26184" to "<http://example/q>",
                                        "y" to "\"2\"",
                                        "s" to "<http://example/x6>"
                                ),
                                mutableMapOf(
                                        "#p26183" to "<http://example/p>",
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "#p26184" to "<http://example/q>",
                                        "y" to "\"2\"",
                                        "s" to "<http://example/x7>"
                                ),
                                mutableMapOf(
                                        "#p26183" to "<http://example/p>",
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "#p26184" to "<http://example/q>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example/x8>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p26355",
                                        "x"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/x1>",
                                                "#p26355" to "<http://example/p>",
                                                "x" to "\"a\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x2>",
                                                "#p26355" to "<http://example/p>",
                                                "x" to "_:b"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x3>",
                                                "#p26355" to "<http://example/p>",
                                                "x" to "<http://example/a>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x4>",
                                                "#p26355" to "<http://example/p>",
                                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x5>",
                                                "#p26355" to "<http://example/p>",
                                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x6>",
                                                "#p26355" to "<http://example/p>",
                                                "x" to "\"1\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x7>",
                                                "#p26355" to "<http://example/p>",
                                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x8>",
                                                "#p26355" to "<http://example/p>",
                                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p26356",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/x1>",
                                                "#p26356" to "<http://example/q>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x2>",
                                                "#p26356" to "<http://example/q>",
                                                "y" to "\"1\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x3>",
                                                "#p26356" to "<http://example/q>",
                                                "y" to "\"1\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x4>",
                                                "#p26356" to "<http://example/q>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x5>",
                                                "#p26356" to "<http://example/q>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x6>",
                                                "#p26356" to "<http://example/q>",
                                                "y" to "\"2\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x7>",
                                                "#p26356" to "<http://example/q>",
                                                "y" to "\"2\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x8>",
                                                "#p26356" to "<http://example/q>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p26355",
                                "x",
                                "#p26356",
                                "y",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "#p26355" to "<http://example/p>",
                                        "x" to "\"a\"",
                                        "#p26356" to "<http://example/q>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example/x1>"
                                ),
                                mutableMapOf(
                                        "#p26355" to "<http://example/p>",
                                        "x" to "_:b",
                                        "#p26356" to "<http://example/q>",
                                        "y" to "\"1\"",
                                        "s" to "<http://example/x2>"
                                ),
                                mutableMapOf(
                                        "#p26355" to "<http://example/p>",
                                        "x" to "<http://example/a>",
                                        "#p26356" to "<http://example/q>",
                                        "y" to "\"1\"",
                                        "s" to "<http://example/x3>"
                                ),
                                mutableMapOf(
                                        "#p26355" to "<http://example/p>",
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p26356" to "<http://example/q>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example/x4>"
                                ),
                                mutableMapOf(
                                        "#p26355" to "<http://example/p>",
                                        "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                        "#p26356" to "<http://example/q>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example/x5>"
                                ),
                                mutableMapOf(
                                        "#p26355" to "<http://example/p>",
                                        "x" to "\"1\"",
                                        "#p26356" to "<http://example/q>",
                                        "y" to "\"2\"",
                                        "s" to "<http://example/x6>"
                                ),
                                mutableMapOf(
                                        "#p26355" to "<http://example/p>",
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "#p26356" to "<http://example/q>",
                                        "y" to "\"2\"",
                                        "s" to "<http://example/x7>"
                                ),
                                mutableMapOf(
                                        "#p26355" to "<http://example/p>",
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "#p26356" to "<http://example/q>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example/x8>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "a",
                                        "#p28207",
                                        "s1"
                                ), listOf(
                                        mutableMapOf(
                                                "a" to "<http://example.org/s1>",
                                                "#p28207" to "<http://example.org/str>",
                                                "s1" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s2>",
                                                "#p28207" to "<http://example.org/str>",
                                                "s1" to "\"bar\"@en"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s3>",
                                                "#p28207" to "<http://example.org/str>",
                                                "s1" to "\"BAZ\""
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s4>",
                                                "#p28207" to "<http://example.org/str>",
                                                "s1" to "\"食べ物\""
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s5>",
                                                "#p28207" to "<http://example.org/str>",
                                                "s1" to "\"100%\""
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s6>",
                                                "#p28207" to "<http://example.org/str>",
                                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s7>",
                                                "#p28207" to "<http://example.org/str>",
                                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "b",
                                        "#p28208",
                                        "s2"
                                ), listOf(
                                        mutableMapOf(
                                                "b" to "<http://example.org/s1>",
                                                "#p28208" to "<http://example.org/str>",
                                                "s2" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "b" to "<http://example.org/s2>",
                                                "#p28208" to "<http://example.org/str>",
                                                "s2" to "\"bar\"@en"
                                        ),
                                        mutableMapOf(
                                                "b" to "<http://example.org/s3>",
                                                "#p28208" to "<http://example.org/str>",
                                                "s2" to "\"BAZ\""
                                        ),
                                        mutableMapOf(
                                                "b" to "<http://example.org/s4>",
                                                "#p28208" to "<http://example.org/str>",
                                                "s2" to "\"食べ物\""
                                        ),
                                        mutableMapOf(
                                                "b" to "<http://example.org/s5>",
                                                "#p28208" to "<http://example.org/str>",
                                                "s2" to "\"100%\""
                                        ),
                                        mutableMapOf(
                                                "b" to "<http://example.org/s6>",
                                                "#p28208" to "<http://example.org/str>",
                                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "b" to "<http://example.org/s7>",
                                                "#p28208" to "<http://example.org/str>",
                                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "#p28207",
                                "s1",
                                "b",
                                "#p28208",
                                "s2"
                        ), listOf(
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s1>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s1>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s1>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s1>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s1>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s1>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s1>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s2>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s2>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s2>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s2>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s2>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s2>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s2>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s3>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s3>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s3>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s3>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s3>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s3>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s3>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s4>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s4>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s4>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s4>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s4>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s4>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s4>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s5>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s5>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s5>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s5>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s5>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s5>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s5>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s6>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s6>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s6>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s6>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s6>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s6>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s6>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s1>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"foo\"",
                                        "b" to "<http://example.org/s7>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s2>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"bar\"@en",
                                        "b" to "<http://example.org/s7>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s3>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"BAZ\"",
                                        "b" to "<http://example.org/s7>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s4>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"食べ物\"",
                                        "b" to "<http://example.org/s7>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s5>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"100%\"",
                                        "b" to "<http://example.org/s7>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s6>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s7>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "a" to "<http://example.org/s7>",
                                        "#p28207" to "<http://example.org/str>",
                                        "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "b" to "<http://example.org/s7>",
                                        "#p28208" to "<http://example.org/str>",
                                        "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p32320",
                                        "v"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>",
                                                "#p32320" to "<http://example/p>",
                                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/s2>",
                                                "#p32320" to "<http://example/p>",
                                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p32321",
                                        "w"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>",
                                                "#p32321" to "<http://example/q>",
                                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "#p32320",
                                "v",
                                "#p32321",
                                "w",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "#p32320" to "<http://example/p>",
                                        "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p32321" to "<http://example/q>",
                                        "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example/s1>"
                                ),
                                mutableMapOf(
                                        "#p32320" to "<http://example/p>",
                                        "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p32321" to null,
                                        "w" to null,
                                        "s" to "<http://example/s2>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p32358",
                                        "v"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>",
                                                "#p32358" to "<http://example/p>",
                                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/s2>",
                                                "#p32358" to "<http://example/p>",
                                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p32359",
                                        "w"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>",
                                                "#p32359" to "<http://example/q>",
                                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "#p32358",
                                "v",
                                "#p32359",
                                "w",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "#p32358" to "<http://example/p>",
                                        "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p32359" to "<http://example/q>",
                                        "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example/s1>"
                                ),
                                mutableMapOf(
                                        "#p32358" to "<http://example/p>",
                                        "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p32359" to null,
                                        "w" to null,
                                        "s" to "<http://example/s2>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p32513",
                                        "v"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>",
                                                "#p32513" to "<http://example/p>",
                                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/s2>",
                                                "#p32513" to "<http://example/p>",
                                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/s3>",
                                                "#p32513" to "<http://example/p>",
                                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p32514",
                                        "w"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>",
                                                "#p32514" to "<http://example/q>",
                                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "#p32513",
                                "v",
                                "#p32514",
                                "w",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "#p32513" to "<http://example/p>",
                                        "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p32514" to "<http://example/q>",
                                        "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example/s1>"
                                ),
                                mutableMapOf(
                                        "#p32513" to "<http://example/p>",
                                        "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p32514" to null,
                                        "w" to null,
                                        "s" to "<http://example/s2>"
                                ),
                                mutableMapOf(
                                        "#p32513" to "<http://example/p>",
                                        "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p32514" to null,
                                        "w" to null,
                                        "s" to "<http://example/s3>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p32524",
                                        "v"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>",
                                                "#p32524" to "<http://example/p>",
                                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/s2>",
                                                "#p32524" to "<http://example/p>",
                                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/s3>",
                                                "#p32524" to "<http://example/p>",
                                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#p32525",
                                        "w"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>",
                                                "#p32525" to "<http://example/q>",
                                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "#p32524",
                                "v",
                                "#p32525",
                                "w",
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "#p32524" to "<http://example/p>",
                                        "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p32525" to "<http://example/q>",
                                        "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s" to "<http://example/s1>"
                                ),
                                mutableMapOf(
                                        "#p32524" to "<http://example/p>",
                                        "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p32525" to null,
                                        "w" to null,
                                        "s" to "<http://example/s2>"
                                ),
                                mutableMapOf(
                                        "#p32524" to "<http://example/p>",
                                        "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p32525" to null,
                                        "w" to null,
                                        "s" to "<http://example/s3>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p4>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s5>",
                                                "p" to "<http://example.org/p5>",
                                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s6>",
                                                "p" to "<http://example.org/p6>",
                                                "o" to "_:o6"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "o" to "<http://example.org/s1>",
                                                "p2" to "<http://example.org/p1>",
                                                "o2" to "<http://example.org/s2>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s2>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s3>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s4>",
                                                "p2" to "<http://example.org/p4>",
                                                "o2" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s5>",
                                                "p2" to "<http://example.org/p5>",
                                                "o2" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "o" to "<http://example.org/s6>",
                                                "p2" to "<http://example.org/p6>",
                                                "o2" to "_:o6"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "p2" to "<http://example.org/p2>",
                                        "o2" to "\"foo\"",
                                        "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "p2" to null,
                                        "o2" to null,
                                        "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p2>",
                                        "p2" to null,
                                        "o2" to null,
                                        "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p4>",
                                        "p2" to null,
                                        "o2" to null,
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s5>",
                                        "p" to "<http://example.org/p5>",
                                        "p2" to null,
                                        "o2" to null,
                                        "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "p" to "<http://example.org/p6>",
                                        "p2" to null,
                                        "o2" to null,
                                        "o" to "_:o6"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p34566",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34566" to "<http://www.example.org/schema#p>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p34567",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34567" to "<http://www.example.org/schema#q>",
                                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34567" to "<http://www.example.org/schema#q>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p34566",
                                "y",
                                "#p34567",
                                "z",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "#p34566" to "<http://www.example.org/schema#p>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p34567" to "<http://www.example.org/schema#q>",
                                        "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "x" to "<http://www.example.org/instance#a>"
                                ),
                                mutableMapOf(
                                        "#p34566" to "<http://www.example.org/schema#p>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p34567" to "<http://www.example.org/schema#q>",
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "x" to "<http://www.example.org/instance#a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p34605",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34605" to "<http://www.example.org/schema#p>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p34606",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34606" to "<http://www.example.org/schema#q>",
                                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34606" to "<http://www.example.org/schema#q>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p34605",
                                "y",
                                "#p34606",
                                "z",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "#p34605" to "<http://www.example.org/schema#p>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p34606" to "<http://www.example.org/schema#q>",
                                        "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "x" to "<http://www.example.org/instance#a>"
                                ),
                                mutableMapOf(
                                        "#p34605" to "<http://www.example.org/schema#p>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p34606" to "<http://www.example.org/schema#q>",
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "x" to "<http://www.example.org/instance#a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p34714",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34714" to "<http://www.example.org/schema#p>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p34715",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34715" to "<http://www.example.org/schema#q>",
                                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34715" to "<http://www.example.org/schema#q>",
                                                "z" to "\"foobar\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p34714",
                                "y",
                                "#p34715",
                                "z",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "#p34714" to "<http://www.example.org/schema#p>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p34715" to "<http://www.example.org/schema#q>",
                                        "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "x" to "<http://www.example.org/instance#a>"
                                ),
                                mutableMapOf(
                                        "#p34714" to "<http://www.example.org/schema#p>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p34715" to "<http://www.example.org/schema#q>",
                                        "z" to "\"foobar\"",
                                        "x" to "<http://www.example.org/instance#a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p34742",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34742" to "<http://www.example.org/schema#p>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p34743",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34743" to "<http://www.example.org/schema#q>",
                                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34743" to "<http://www.example.org/schema#q>",
                                                "z" to "\"foobar\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p34742",
                                "y",
                                "#p34743",
                                "z",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "#p34742" to "<http://www.example.org/schema#p>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p34743" to "<http://www.example.org/schema#q>",
                                        "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "x" to "<http://www.example.org/instance#a>"
                                ),
                                mutableMapOf(
                                        "#p34742" to "<http://www.example.org/schema#p>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p34743" to "<http://www.example.org/schema#q>",
                                        "z" to "\"foobar\"",
                                        "x" to "<http://www.example.org/instance#a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p34853",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34853" to "<http://www.example.org/schema#p>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p34854",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34854" to "<http://www.example.org/schema#q>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p34853",
                                "y",
                                "#p34854",
                                "z",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "#p34853" to "<http://www.example.org/schema#p>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p34854" to "<http://www.example.org/schema#q>",
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "x" to "<http://www.example.org/instance#a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p34898",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34898" to "<http://www.example.org/schema#p>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p34899",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p34899" to "<http://www.example.org/schema#q>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p34898",
                                "y",
                                "#p34899",
                                "z",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "#p34898" to "<http://www.example.org/schema#p>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p34899" to "<http://www.example.org/schema#q>",
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "x" to "<http://www.example.org/instance#a>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p35353",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p35353" to "<http://www.example.org/schema#p>",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#b>",
                                                "#p35353" to "<http://www.example.org/schema#p>",
                                                "y" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#p35354",
                                        "l"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.example.org/instance#a>",
                                                "#p35354" to "<http://www.example.org/schema#q>",
                                                "l" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "#p35353",
                                "y",
                                "#p35354",
                                "l",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "#p35353" to "<http://www.example.org/schema#p>",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p35354" to "<http://www.example.org/schema#q>",
                                        "l" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "x" to "<http://www.example.org/instance#a>"
                                ),
                                mutableMapOf(
                                        "#p35353" to "<http://www.example.org/schema#p>",
                                        "y" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "#p35354" to null,
                                        "l" to null,
                                        "x" to "<http://www.example.org/instance#b>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#_36666",
                                        "#p36736",
                                        "L"
                                ), listOf(
                                        mutableMapOf(
                                                "#_36666" to "_:_36643",
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36644",
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pizza\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36645",
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Wine\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36646",
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36647",
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pasta\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36648",
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36649",
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Sandwich\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36650",
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36651",
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Bagel\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36652",
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#p36737",
                                        "#_36666"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36643"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36644"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36645"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36646"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36647"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36648"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36649"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36650"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36651"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36652"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p36736",
                                "L",
                                "O",
                                "#p36737",
                                "#_36666"
                        ), listOf(
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36643"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36644"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36645"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36646"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36647"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36648"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Sandwich\"",
                                        "O" to "<http://www.example.orgorder3>",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36649"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder3>",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36650"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Bagel\"",
                                        "O" to "<http://www.example.orgorder4>",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36651"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder4>",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36652"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#p36736",
                                        "L",
                                        "O",
                                        "#p36737",
                                        "#_36666"
                                ), listOf(
                                        mutableMapOf(
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36643"
                                        ),
                                        mutableMapOf(
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pizza\"",
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36644"
                                        ),
                                        mutableMapOf(
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Wine\"",
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36645"
                                        ),
                                        mutableMapOf(
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36646"
                                        ),
                                        mutableMapOf(
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pasta\"",
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36647"
                                        ),
                                        mutableMapOf(
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36648"
                                        ),
                                        mutableMapOf(
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Sandwich\"",
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36649"
                                        ),
                                        mutableMapOf(
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36650"
                                        ),
                                        mutableMapOf(
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Bagel\"",
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36651"
                                        ),
                                        mutableMapOf(
                                                "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p36737" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36652"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p36736",
                                "L",
                                "#p36737",
                                "#_36666",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36643",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pizza\"",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36644",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Wine\"",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36645",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36646",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pasta\"",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36647",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#p36736" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "#p36737" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36648",
                                        "O" to "<http://www.example.orgorder2>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#_36666",
                                        "#p36757",
                                        "L"
                                ), listOf(
                                        mutableMapOf(
                                                "#_36666" to "_:_36643",
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36644",
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pizza\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36645",
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Wine\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36646",
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36647",
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pasta\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36648",
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36649",
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Sandwich\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36650",
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36651",
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Bagel\""
                                        ),
                                        mutableMapOf(
                                                "#_36666" to "_:_36652",
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#p36758",
                                        "#_36666"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36643"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36644"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36645"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36646"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36647"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36648"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36649"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36650"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36651"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36652"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p36757",
                                "L",
                                "O",
                                "#p36758",
                                "#_36666"
                        ), listOf(
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36643"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36644"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36645"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36646"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36647"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36648"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Sandwich\"",
                                        "O" to "<http://www.example.orgorder3>",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36649"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder3>",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36650"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Bagel\"",
                                        "O" to "<http://www.example.orgorder4>",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36651"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder4>",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36652"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#p36757",
                                        "L",
                                        "O",
                                        "#p36758",
                                        "#_36666"
                                ), listOf(
                                        mutableMapOf(
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36643"
                                        ),
                                        mutableMapOf(
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pizza\"",
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36644"
                                        ),
                                        mutableMapOf(
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Wine\"",
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36645"
                                        ),
                                        mutableMapOf(
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36646"
                                        ),
                                        mutableMapOf(
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pasta\"",
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36647"
                                        ),
                                        mutableMapOf(
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36648"
                                        ),
                                        mutableMapOf(
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Sandwich\"",
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36649"
                                        ),
                                        mutableMapOf(
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36650"
                                        ),
                                        mutableMapOf(
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Bagel\"",
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36651"
                                        ),
                                        mutableMapOf(
                                                "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p36758" to "<http://www.example.orghasItem>",
                                                "#_36666" to "_:_36652"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p36757",
                                "L",
                                "#p36758",
                                "#_36666",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36643",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pizza\"",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36644",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Wine\"",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36645",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36646",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pasta\"",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36647",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#p36757" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "#p36758" to "<http://www.example.orghasItem>",
                                        "#_36666" to "_:_36648",
                                        "O" to "<http://www.example.orgorder2>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "P",
                                        "#p36878",
                                        "F"
                                ), listOf(
                                        mutableMapOf(
                                                "P" to "<http://p1>",
                                                "#p36878" to "<http://xmlns.com/foaf/0.1/firstName>",
                                                "F" to "\"John\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "#p36879",
                                        "L"
                                ), listOf(
                                        mutableMapOf(
                                                "P" to "<http://p1>",
                                                "#p36879" to "<http://xmlns.com/foaf/0.1/lastName>",
                                                "L" to "\"Doe\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p36878",
                                "F",
                                "#p36879",
                                "L",
                                "P"
                        ), listOf(
                                mutableMapOf(
                                        "#p36878" to "<http://xmlns.com/foaf/0.1/firstName>",
                                        "F" to "\"John\"",
                                        "#p36879" to "<http://xmlns.com/foaf/0.1/lastName>",
                                        "L" to "\"Doe\"",
                                        "P" to "<http://p1>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "P",
                                        "#p36933",
                                        "F"
                                ), listOf(
                                        mutableMapOf(
                                                "P" to "<http://p1>",
                                                "#p36933" to "<http://xmlns.com/foaf/0.1/firstName>",
                                                "F" to "\"John\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "#p36934",
                                        "L"
                                ), listOf(
                                        mutableMapOf(
                                                "P" to "<http://p1>",
                                                "#p36934" to "<http://xmlns.com/foaf/0.1/lastName>",
                                                "L" to "\"Doe\""
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p36933",
                                "F",
                                "#p36934",
                                "L",
                                "P"
                        ), listOf(
                                mutableMapOf(
                                        "#p36933" to "<http://xmlns.com/foaf/0.1/firstName>",
                                        "F" to "\"John\"",
                                        "#p36934" to "<http://xmlns.com/foaf/0.1/lastName>",
                                        "L" to "\"Doe\"",
                                        "P" to "<http://p1>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#_37029",
                                        "#p37099",
                                        "L"
                                ), listOf(
                                        mutableMapOf(
                                                "#_37029" to "_:_37006",
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37007",
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pizza\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37008",
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Wine\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37009",
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37010",
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pasta\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37011",
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37012",
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Sandwich\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37013",
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37014",
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Bagel\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37015",
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#p37100",
                                        "#_37029"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37006"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37007"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37008"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37009"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37010"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37011"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37012"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37013"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37014"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37015"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p37099",
                                "L",
                                "O",
                                "#p37100",
                                "#_37029"
                        ), listOf(
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37006"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37007"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37008"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37009"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37010"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37011"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Sandwich\"",
                                        "O" to "<http://www.example.orgorder3>",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37012"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder3>",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37013"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Bagel\"",
                                        "O" to "<http://www.example.orgorder4>",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37014"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder4>",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37015"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#p37099",
                                        "L",
                                        "O",
                                        "#p37100",
                                        "#_37029"
                                ), listOf(
                                        mutableMapOf(
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37006"
                                        ),
                                        mutableMapOf(
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pizza\"",
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37007"
                                        ),
                                        mutableMapOf(
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Wine\"",
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37008"
                                        ),
                                        mutableMapOf(
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37009"
                                        ),
                                        mutableMapOf(
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pasta\"",
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37010"
                                        ),
                                        mutableMapOf(
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37011"
                                        ),
                                        mutableMapOf(
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Sandwich\"",
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37012"
                                        ),
                                        mutableMapOf(
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37013"
                                        ),
                                        mutableMapOf(
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Bagel\"",
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37014"
                                        ),
                                        mutableMapOf(
                                                "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p37100" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37015"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p37099",
                                "L",
                                "#p37100",
                                "#_37029",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37006",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pizza\"",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37007",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Wine\"",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37008",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37009",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pasta\"",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37010",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#p37099" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "#p37100" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37011",
                                        "O" to "<http://www.example.orgorder2>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#_37029",
                                        "#p37120",
                                        "L"
                                ), listOf(
                                        mutableMapOf(
                                                "#_37029" to "_:_37006",
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37007",
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pizza\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37008",
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Wine\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37009",
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37010",
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pasta\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37011",
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37012",
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Sandwich\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37013",
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37014",
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Bagel\""
                                        ),
                                        mutableMapOf(
                                                "#_37029" to "_:_37015",
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\""
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#p37121",
                                        "#_37029"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37006"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37007"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37008"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37009"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37010"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37011"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37012"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37013"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37014"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37015"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p37120",
                                "L",
                                "O",
                                "#p37121",
                                "#_37029"
                        ), listOf(
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37006"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37007"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37008"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37009"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37010"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37011"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Sandwich\"",
                                        "O" to "<http://www.example.orgorder3>",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37012"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder3>",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37013"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Bagel\"",
                                        "O" to "<http://www.example.orgorder4>",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37014"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder4>",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37015"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#p37120",
                                        "L",
                                        "O",
                                        "#p37121",
                                        "#_37029"
                                ), listOf(
                                        mutableMapOf(
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37006"
                                        ),
                                        mutableMapOf(
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pizza\"",
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37007"
                                        ),
                                        mutableMapOf(
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Wine\"",
                                                "O" to "<http://www.example.orgorder1>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37008"
                                        ),
                                        mutableMapOf(
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37009"
                                        ),
                                        mutableMapOf(
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Pasta\"",
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37010"
                                        ),
                                        mutableMapOf(
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder2>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37011"
                                        ),
                                        mutableMapOf(
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Sandwich\"",
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37012"
                                        ),
                                        mutableMapOf(
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder3>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37013"
                                        ),
                                        mutableMapOf(
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Bagel\"",
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37014"
                                        ),
                                        mutableMapOf(
                                                "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder4>",
                                                "#p37121" to "<http://www.example.orghasItem>",
                                                "#_37029" to "_:_37015"
                                        )
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>"
                                        )
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#p37120",
                                "L",
                                "#p37121",
                                "#_37029",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37006",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pizza\"",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37007",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Wine\"",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37008",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Ice Cream\"",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37009",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Pasta\"",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37010",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#p37120" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                        "L" to "\"Soft Drink\"",
                                        "#p37121" to "<http://www.example.orghasItem>",
                                        "#_37029" to "_:_37011",
                                        "O" to "<http://www.example.orgorder2>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                MicroTest0(AOPUndef(), AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("$index") {
            try {
                if (data.input is AOPBase) {
                    val input = data.input as AOPBase
                    val output: AOPConstant
                    if (data is MicroTestA1) {
                        output = input.calculate(data.resultSet, data.resultRow)
                    } else if (data is MicroTestAN) {
                        setAggregationMode(input, true, data.resultRows.count())
                        for (resultRow in data.resultRows)
                            input.calculate(data.resultSet, resultRow)
                        setAggregationMode(input, false, data.resultRows.count())
                        output = input.calculate(data.resultSet, data.resultSet.createResultRow())
                    } else {
                        val resultSet = ResultSet(ResultSetDictionary())
                        output = input.calculate(resultSet, resultSet.createResultRow())
                    }
                    assertTrue(data.expected is AOPConstant)
                    if (!data.expected.equals(output)) {
                        if (data is MicroTestA1)
                            println(data.resultRow)
                        println(output.valueToString())
                        println((data.expected as AOPConstant).valueToString())
                    }
                    assertTrue(data.expected.equals(output))
                } else if (data.input is POPBase) {
                    val input = data.input as POPBase
                    assertTrue(data.expected is POPValues)
                    val output = QueryResultToXML.toXML(input).first()
                    val expected = QueryResultToXML.toXML(data.expected as POPValues).first()
                    if (!expected.myEquals(output)) {
                        println(output.toPrettyString())
                        println(expected.toPrettyString())
                    }
                    assertTrue(expected.myEquals(output))
                }
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }
}
