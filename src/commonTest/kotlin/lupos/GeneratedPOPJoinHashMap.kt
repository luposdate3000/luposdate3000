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
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "S",
                                "#p2396",
                                "O1"
                            ), listOf(
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2396" to "<http://www.example.org/p>",
                                    "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2396" to "<http://www.example.org/p>",
                                    "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2396" to "<http://www.example.org/p>",
                                    "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "#p2397",
                                "O2"
                            ), listOf(
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2397" to "<http://www.example.org/q>",
                                    "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2397" to "<http://www.example.org/q>",
                                    "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2397" to "<http://www.example.org/q>",
                                    "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p2396",
                            "O1",
                            "#p2397",
                            "O2",
                            "S"
                        ), listOf(
                            mutableMapOf(
                                "#p2396" to "<http://www.example.org/p>",
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to "<http://www.example.org/q>",
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to "<http://www.example.org/p>",
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to "<http://www.example.org/q>",
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to "<http://www.example.org/p>",
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to "<http://www.example.org/q>",
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to "<http://www.example.org/p>",
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to "<http://www.example.org/q>",
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to "<http://www.example.org/p>",
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to "<http://www.example.org/q>",
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to "<http://www.example.org/p>",
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to "<http://www.example.org/q>",
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to "<http://www.example.org/p>",
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to "<http://www.example.org/q>",
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to "<http://www.example.org/p>",
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to "<http://www.example.org/q>",
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to "<http://www.example.org/p>",
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to "<http://www.example.org/q>",
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "S",
                                "#p2658",
                                "O1"
                            ), listOf(
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2658" to "<http://www.example.org/p>",
                                    "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2658" to "<http://www.example.org/p>",
                                    "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2658" to "<http://www.example.org/p>",
                                    "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "#p2659",
                                "O2"
                            ), listOf(
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2659" to "<http://www.example.org/q>",
                                    "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2659" to "<http://www.example.org/q>",
                                    "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2659" to "<http://www.example.org/q>",
                                    "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p2658",
                            "O1",
                            "#p2659",
                            "O2",
                            "S"
                        ), listOf(
                            mutableMapOf(
                                "#p2658" to "<http://www.example.org/p>",
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to "<http://www.example.org/q>",
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to "<http://www.example.org/p>",
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to "<http://www.example.org/q>",
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to "<http://www.example.org/p>",
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to "<http://www.example.org/q>",
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to "<http://www.example.org/p>",
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to "<http://www.example.org/q>",
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to "<http://www.example.org/p>",
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to "<http://www.example.org/q>",
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to "<http://www.example.org/p>",
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to "<http://www.example.org/q>",
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to "<http://www.example.org/p>",
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to "<http://www.example.org/q>",
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to "<http://www.example.org/p>",
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to "<http://www.example.org/q>",
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to "<http://www.example.org/p>",
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to "<http://www.example.org/q>",
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                        false                    ),
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
            }() /* resources/sparql11-test-suite/bind/bind03.rq */ ,
            {
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
                        false                    ),
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
            }() /* resources/sparql11-test-suite/bind/bind07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "book",
                                "#p8341",
                                "title"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8341" to "<http://purl.org/dc/elements/1.1/title>",
                                    "title" to "\"SPARQL Tutorial\""
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8341" to "<http://purl.org/dc/elements/1.1/title>",
                                    "title" to "\"The Semantic Web\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "#p8342",
                                "price"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8342" to "<http://example.org/ns#price>",
                                    "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8342" to "<http://example.org/ns#price>",
                                    "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p8341",
                            "title",
                            "#p8342",
                            "price",
                            "book"
                        ), listOf(
                            mutableMapOf(
                                "#p8341" to "<http://purl.org/dc/elements/1.1/title>",
                                "title" to "\"SPARQL Tutorial\"",
                                "#p8342" to "<http://example.org/ns#price>",
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "book" to "<http://example.org/book/book1>"
                            ),
                            mutableMapOf(
                                "#p8341" to "<http://purl.org/dc/elements/1.1/title>",
                                "title" to "\"The Semantic Web\"",
                                "#p8342" to "<http://example.org/ns#price>",
                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "book" to "<http://example.org/book/book2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                        false                    ),
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
            }() /* resources/sparql11-test-suite/bindings/values01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                        false                    ),
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
            }() /* resources/sparql11-test-suite/bindings/values02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                        false                    ),
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
            }() /* resources/sparql11-test-suite/bindings/values03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                        false                    ),
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
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                        false                    ),
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
            }() /* resources/sparql11-test-suite/bindings/values04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                        false                    ),
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
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                        false                    ),
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
            }() /* resources/sparql11-test-suite/bindings/values06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                        false                    ),
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
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "#p8804",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/a>",
                                    "#p8804" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/b>",
                                    "#p8804" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "o2" to "<http://example.org/c>"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "p1",
                            "o1",
                            "#p8804",
                            "o2",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o1" to "<http://example.org/b>",
                                "#p8804" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o2" to "<http://example.org/b>",
                                "s" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"alan@example.org\"",
                                "#p8804" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o2" to "<http://example.org/b>",
                                "s" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Alan\"",
                                "#p8804" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o2" to "<http://example.org/b>",
                                "s" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o1" to "<http://example.org/c>",
                                "#p8804" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o2" to "<http://example.org/c>",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"bob@example.org\"",
                                "#p8804" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o2" to "<http://example.org/c>",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Bob\"",
                                "#p8804" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o2" to "<http://example.org/c>",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"alice@example.org\"",
                                "#p8804" to null,
                                "o2" to null,
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Alice\"",
                                "#p8804" to null,
                                "o2" to null,
                                "s" to "<http://example.org/c>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "#p8814",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/a>",
                                    "#p8814" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/b>",
                                    "#p8814" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "o2" to "<http://example.org/c>"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "p1",
                            "o1",
                            "#p8814",
                            "o2",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o1" to "<http://example.org/b>",
                                "#p8814" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o2" to "<http://example.org/b>",
                                "s" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"alan@example.org\"",
                                "#p8814" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o2" to "<http://example.org/b>",
                                "s" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Alan\"",
                                "#p8814" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o2" to "<http://example.org/b>",
                                "s" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o1" to "<http://example.org/c>",
                                "#p8814" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o2" to "<http://example.org/c>",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"bob@example.org\"",
                                "#p8814" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o2" to "<http://example.org/c>",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Bob\"",
                                "#p8814" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o2" to "<http://example.org/c>",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"alice@example.org\"",
                                "#p8814" to null,
                                "o2" to null,
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Alice\"",
                                "#p8814" to null,
                                "o2" to null,
                                "s" to "<http://example.org/c>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                        false                    ),
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
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "book",
                                "#p8905",
                                "title"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8905" to "<http://purl.org/dc/elements/1.1/title>",
                                    "title" to "\"SPARQL Tutorial\""
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8905" to "<http://purl.org/dc/elements/1.1/title>",
                                    "title" to "\"The Semantic Web\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "#p8906",
                                "price"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8906" to "<http://example.org/ns#price>",
                                    "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8906" to "<http://example.org/ns#price>",
                                    "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p8905",
                            "title",
                            "#p8906",
                            "price",
                            "book"
                        ), listOf(
                            mutableMapOf(
                                "#p8905" to "<http://purl.org/dc/elements/1.1/title>",
                                "title" to "\"SPARQL Tutorial\"",
                                "#p8906" to "<http://example.org/ns#price>",
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "book" to "<http://example.org/book/book1>"
                            ),
                            mutableMapOf(
                                "#p8905" to "<http://purl.org/dc/elements/1.1/title>",
                                "title" to "\"The Semantic Web\"",
                                "#p8906" to "<http://example.org/ns#price>",
                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "book" to "<http://example.org/book/book2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "#p8980",
                                "title"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8980" to "<http://purl.org/dc/elements/1.1/title>",
                                    "title" to "\"SPARQL Tutorial\""
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8980" to "<http://purl.org/dc/elements/1.1/title>",
                                    "title" to "\"The Semantic Web\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p8980",
                            "title",
                            "book"
                        ), listOf(
                            mutableMapOf(
                                "#p8980" to "<http://purl.org/dc/elements/1.1/title>",
                                "title" to "\"SPARQL Tutorial\"",
                                "book" to "<http://example.org/book/book1>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p8980",
                                "title",
                                "book"
                            ), listOf(
                                mutableMapOf(
                                    "#p8980" to "<http://purl.org/dc/elements/1.1/title>",
                                    "title" to "\"SPARQL Tutorial\"",
                                    "book" to "<http://example.org/book/book1>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "#p8982",
                                "price"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8982" to "<http://example.org/ns#price>",
                                    "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8982" to "<http://example.org/ns#price>",
                                    "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p8980",
                            "title",
                            "#p8982",
                            "price",
                            "book"
                        ), listOf(
                            mutableMapOf(
                                "#p8980" to "<http://purl.org/dc/elements/1.1/title>",
                                "title" to "\"SPARQL Tutorial\"",
                                "#p8982" to "<http://example.org/ns#price>",
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "book" to "<http://example.org/book/book1>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "#p8990",
                                "title"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8990" to "<http://purl.org/dc/elements/1.1/title>",
                                    "title" to "\"SPARQL Tutorial\""
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8990" to "<http://purl.org/dc/elements/1.1/title>",
                                    "title" to "\"The Semantic Web\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p8990",
                            "title",
                            "book"
                        ), listOf(
                            mutableMapOf(
                                "#p8990" to "<http://purl.org/dc/elements/1.1/title>",
                                "title" to "\"SPARQL Tutorial\"",
                                "book" to "<http://example.org/book/book1>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p8990",
                                "title",
                                "book"
                            ), listOf(
                                mutableMapOf(
                                    "#p8990" to "<http://purl.org/dc/elements/1.1/title>",
                                    "title" to "\"SPARQL Tutorial\"",
                                    "book" to "<http://example.org/book/book1>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "#p8992",
                                "price"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8992" to "<http://example.org/ns#price>",
                                    "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8992" to "<http://example.org/ns#price>",
                                    "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p8990",
                            "title",
                            "#p8992",
                            "price",
                            "book"
                        ), listOf(
                            mutableMapOf(
                                "#p8990" to "<http://purl.org/dc/elements/1.1/title>",
                                "title" to "\"SPARQL Tutorial\"",
                                "#p8992" to "<http://example.org/ns#price>",
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "book" to "<http://example.org/book/book1>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
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
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "#p11128",
                                "#o11128"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11128" to "<http://xmlns.com/foaf/0.1/name>",
                                    "#o11128" to "\"Alan\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "#p11129",
                                "b"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11129" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "b" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11129" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "b" to "<http://example.org/c>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/b>",
                                    "#p11129" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "b" to "<http://example.org/c>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p11128",
                            "#o11128",
                            "#p11129",
                            "b",
                            "a"
                        ), listOf(
                            mutableMapOf(
                                "#p11128" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o11128" to "\"Alan\"",
                                "#p11129" to "<http://xmlns.com/foaf/0.1/knows>",
                                "b" to "<http://example.org/b>",
                                "a" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "#p11128" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o11128" to "\"Alan\"",
                                "#p11129" to "<http://xmlns.com/foaf/0.1/knows>",
                                "b" to "<http://example.org/c>",
                                "a" to "<http://example.org/a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "#p11372",
                                "#o11372"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11372" to "<http://xmlns.com/foaf/0.1/name>",
                                    "#o11372" to "\"Alan\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "#p11373",
                                "Var_B"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11373" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "Var_B" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11373" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "Var_B" to "<http://example.org/c>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/b>",
                                    "#p11373" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "Var_B" to "<http://example.org/c>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p11372",
                            "#o11372",
                            "#p11373",
                            "Var_B",
                            "a"
                        ), listOf(
                            mutableMapOf(
                                "#p11372" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o11372" to "\"Alan\"",
                                "#p11373" to "<http://xmlns.com/foaf/0.1/knows>",
                                "Var_B" to "<http://example.org/b>",
                                "a" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "#p11372" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o11372" to "\"Alan\"",
                                "#p11373" to "<http://xmlns.com/foaf/0.1/knows>",
                                "Var_B" to "<http://example.org/c>",
                                "a" to "<http://example.org/a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-04b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "#p11510",
                                "#o11510"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11510" to "<http://xmlns.com/foaf/0.1/name>",
                                    "#o11510" to "\"Alan\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "#p11511",
                                "Var_B"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11511" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "Var_B" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11511" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "Var_B" to "<http://example.org/c>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/b>",
                                    "#p11511" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "Var_B" to "<http://example.org/c>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p11510",
                            "#o11510",
                            "#p11511",
                            "Var_B",
                            "a"
                        ), listOf(
                            mutableMapOf(
                                "#p11510" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o11510" to "\"Alan\"",
                                "#p11511" to "<http://xmlns.com/foaf/0.1/knows>",
                                "Var_B" to "<http://example.org/b>",
                                "a" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "#p11510" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o11510" to "\"Alan\"",
                                "#p11511" to "<http://xmlns.com/foaf/0.1/knows>",
                                "Var_B" to "<http://example.org/c>",
                                "a" to "<http://example.org/a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "#p11664",
                                "#o11664"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11664" to "<http://xmlns.com/foaf/0.1/name>",
                                    "#o11664" to "\"Alan\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "#p11665",
                                "Var_B"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/b>",
                                    "#p11665" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "Var_B" to "<http://example.org/c>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p11664",
                            "#o11664",
                            "#p11665",
                            "Var_B",
                            "a"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s12551",
                                "#p12551",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "#s12551" to "<http://example.org/a>",
                                    "#p12551" to "<http://xmlns.com/foaf/0.1/knows>",
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s12551",
                            "#p12551",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#s12551" to "<http://example.org/a>",
                                "#p12551" to "<http://xmlns.com/foaf/0.1/knows>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\"",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "#s12551" to "<http://example.org/a>",
                                "#p12551" to "<http://xmlns.com/foaf/0.1/knows>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\"",
                                "s" to "<http://example.org/b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s12648",
                                "#p12648",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "#s12648" to "<http://example.org/a>",
                                    "#p12648" to "<http://xmlns.com/foaf/0.1/knows>",
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s12648",
                            "#p12648",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#s12648" to "<http://example.org/a>",
                                "#p12648" to "<http://xmlns.com/foaf/0.1/knows>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\"",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "#s12648" to "<http://example.org/a>",
                                "#p12648" to "<http://xmlns.com/foaf/0.1/knows>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\"",
                                "s" to "<http://example.org/b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-02.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p12730",
                                "#o12730"
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p12730",
                            "#o12730",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-03.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p12809",
                                "#o12809"
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p12809",
                            "#o12809",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-04.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s12877",
                                "#p12877",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "#s12877" to "<http://example.org/a>",
                                    "#p12877" to "<http://xmlns.com/foaf/0.1/knows>",
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s12877",
                            "#p12877",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#s12877" to "<http://example.org/a>",
                                "#p12877" to "<http://xmlns.com/foaf/0.1/knows>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\"",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "#s12877" to "<http://example.org/a>",
                                "#p12877" to "<http://xmlns.com/foaf/0.1/knows>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\"",
                                "s" to "<http://example.org/b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p12982",
                                "#o12982"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "#p12982" to "<http://xmlns.com/foaf/0.1/name>",
                                    "#o12982" to "\"Chris\""
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p12982",
                            "#o12982",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p12982" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o12982" to "\"Chris\"",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"chris@example.org\"",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p12982" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o12982" to "\"Chris\"",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Chris\"",
                                "s" to "<http://example.org/c>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s13393",
                                "#p13393",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "#s13393" to "<http://example.org/a>",
                                    "#p13393" to "<http://xmlns.com/foaf/0.1/knows>",
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s13393",
                            "#p13393",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#s13393" to "<http://example.org/a>",
                                "#p13393" to "<http://xmlns.com/foaf/0.1/knows>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/c>",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "#s13393" to "<http://example.org/a>",
                                "#p13393" to "<http://xmlns.com/foaf/0.1/knows>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\"",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "#s13393" to "<http://example.org/a>",
                                "#p13393" to "<http://xmlns.com/foaf/0.1/knows>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\"",
                                "s" to "<http://example.org/b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s13508",
                                "#p13508",
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s13508",
                            "#p13508",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-02.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p13585",
                                "#o13585"
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p13585",
                            "#o13585",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-03.ru */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p13673",
                                "#o13673"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "#p13673" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "#o13673" to "<http://example.org/d>"
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
                            "#p13673",
                            "#o13673",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p13673" to "<http://xmlns.com/foaf/0.1/knows>",
                                "#o13673" to "<http://example.org/d>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/d>",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p13673" to "<http://xmlns.com/foaf/0.1/knows>",
                                "#o13673" to "<http://example.org/d>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"chris@example.org\"",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p13673" to "<http://xmlns.com/foaf/0.1/knows>",
                                "#o13673" to "<http://example.org/d>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Chris\"",
                                "s" to "<http://example.org/c>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/delete/delete-using-04.ru */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p13779",
                                "#o13779"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/a>",
                                    "#p13779" to "<http://xmlns.com/foaf/0.1/knows>",
                                    "#o13779" to "<http://example.org/b>"
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p13779",
                            "#o13779",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p13779" to "<http://xmlns.com/foaf/0.1/knows>",
                                "#o13779" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/b>",
                                "s" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "#p13779" to "<http://xmlns.com/foaf/0.1/knows>",
                                "#o13779" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"alan@example.org\"",
                                "s" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "#p13779" to "<http://xmlns.com/foaf/0.1/knows>",
                                "#o13779" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Alan\"",
                                "s" to "<http://example.org/a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p13900",
                                "#o13900"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "#p13900" to "<http://xmlns.com/foaf/0.1/name>",
                                    "#o13900" to "\"Chris\""
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
                            "#p13900",
                            "#o13900",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p13900" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o13900" to "\"Chris\"",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/d>",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p13900" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o13900" to "\"Chris\"",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"chris@example.org\"",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p13900" to "<http://xmlns.com/foaf/0.1/name>",
                                "#o13900" to "\"Chris\"",
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
                                "#p15650",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "#p15650" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/d>",
                                    "#p15650" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "#p15650" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p15650" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p15650" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/y>",
                                    "#p15650" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "_:rdfs05",
                                    "#p15650" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p15651",
                                "#o15651"
                            ), listOf(
                                mutableMapOf(
                                    "c" to "<http://example.org/x/c>",
                                    "#p15651" to "<http://www.w3.org/2000/01/rdf-schema#subClassOf>",
                                    "#o15651" to "<http://example.org/x/d>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p15650",
                            "#p15651",
                            "#o15651",
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p15650" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#p15651" to "<http://www.w3.org/2000/01/rdf-schema#subClassOf>",
                                "#o15651" to "<http://example.org/x/d>",
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
                                "#s15909",
                                "x",
                                "#o15909"
                            ), listOf(
                                mutableMapOf(
                                    "#s15909" to "<http://example.org/ns#a>",
                                    "x" to "<http://example.org/ns#b>",
                                    "#o15909" to "<http://example.org/ns#c>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p15910",
                                "#o15910"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/ns#b>",
                                    "#p15910" to "<http://www.w3.org/2000/01/rdf-schema#subPropertyOf>",
                                    "#o15910" to "<http://example.org/ns#p>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s15909",
                            "#o15909",
                            "#p15910",
                            "#o15910",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#s15909" to "<http://example.org/ns#a>",
                                "#o15909" to "<http://example.org/ns#c>",
                                "#p15910" to "<http://www.w3.org/2000/01/rdf-schema#subPropertyOf>",
                                "#o15910" to "<http://example.org/ns#p>",
                                "x" to "<http://example.org/ns#b>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/rdfs11.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16097",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p16097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p16097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/x/d>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p16097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p16097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "_:x"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "#p16097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/d>",
                                    "#p16097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "#p16097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:ont",
                                    "#p16097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:x",
                                    "#p16097" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p16098",
                                "#o16098"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16097",
                            "#p16098",
                            "#o16098",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16159",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p16159" to "<http://example.org/x/p>",
                                    "y" to "<http://example.org/x/y>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p16159" to "<http://example.org/x/p>",
                                    "y" to "_:y"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "y",
                                "#p16160",
                                "#o16160"
                            ), listOf(
                                mutableMapOf(
                                    "y" to "<http://example.org/x/y>",
                                    "#p16160" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16160" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "y" to "_:y",
                                    "#p16160" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16160" to "<http://example.org/x/c>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16159",
                            "#p16160",
                            "#o16160",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p16159" to "<http://example.org/x/p>",
                                "#p16160" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16160" to "<http://example.org/x/c>",
                                "y" to "<http://example.org/x/y>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p16159" to "<http://example.org/x/p>",
                                "#p16160" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16160" to "<http://example.org/x/c>",
                                "y" to "_:y"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16352",
                                "#c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Conference>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/ConferencePaper>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Employee>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/GraduateAssistant>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Student>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Workshop>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/hasPublication>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/name>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/publishedAt>",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:SPARQLDAWGTestOntology",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:_16248",
                                    "#p16352" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16269",
                                "#p16353",
                                "#o16353"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16352",
                            "#c",
                            "#_16269",
                            "#p16353",
                            "#o16353"
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
                                "#p16352",
                                "#p16353",
                                "#o16353",
                                "#p16355",
                                "#p16357",
                                "#o16357",
                                "#p16359",
                                "#o16359",
                                "#_16274",
                                "#p16361",
                                "#c",
                                "#_16269"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16363",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16363" to "<http://example.org/name>",
                                    "y" to "\"Johnnie\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p16352",
                            "#p16353",
                            "#o16353",
                            "#p16355",
                            "#p16357",
                            "#o16357",
                            "#p16359",
                            "#o16359",
                            "#_16274",
                            "#p16361",
                            "#c",
                            "#_16269",
                            "#p16363",
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
                                "#p16480",
                                "#b0"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16394",
                                "#p16481",
                                "#o16481"
                            ), listOf(
                                mutableMapOf(
                                    "#_16394" to "_:_16374",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16480",
                            "#b0",
                            "#_16394",
                            "#p16481",
                            "#o16481"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16394" to "_:_16374",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#_16394" to "_:_16374",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>"
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
                                "#p16480",
                                "#b0",
                                "#_16394",
                                "#p16481",
                                "#o16481"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16394" to "_:_16374",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16394" to "_:_16374",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16394",
                                "#p16483",
                                "#o16483"
                            ), listOf(
                                mutableMapOf(
                                    "#_16394" to "_:_16374",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16480",
                            "#b0",
                            "#p16481",
                            "#o16481",
                            "#p16483",
                            "#o16483",
                            "#_16394"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374"
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
                                "#p16480",
                                "#b0",
                                "#p16481",
                                "#o16481",
                                "#p16483",
                                "#o16483",
                                "#_16394"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16403",
                                "#p16485",
                                "#o16485"
                            ), listOf(
                                mutableMapOf(
                                    "#_16403" to "<http://example.org/Conference>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_16403" to "<http://example.org/ConferencePaper>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_16403" to "<http://example.org/Employee>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_16403" to "<http://example.org/GraduateAssistant>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_16403" to "<http://example.org/Student>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_16403" to "<http://example.org/Workshop>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16480",
                            "#b0",
                            "#p16481",
                            "#o16481",
                            "#p16483",
                            "#o16483",
                            "#_16394",
                            "#_16403",
                            "#p16485",
                            "#o16485"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374",
                                "#_16403" to "<http://example.org/Conference>",
                                "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374",
                                "#_16403" to "<http://example.org/Conference>",
                                "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374",
                                "#_16403" to "<http://example.org/ConferencePaper>",
                                "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374",
                                "#_16403" to "<http://example.org/ConferencePaper>",
                                "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374",
                                "#_16403" to "<http://example.org/Employee>",
                                "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374",
                                "#_16403" to "<http://example.org/Employee>",
                                "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374",
                                "#_16403" to "<http://example.org/GraduateAssistant>",
                                "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374",
                                "#_16403" to "<http://example.org/GraduateAssistant>",
                                "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374",
                                "#_16403" to "<http://example.org/Student>",
                                "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374",
                                "#_16403" to "<http://example.org/Student>",
                                "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374",
                                "#_16403" to "<http://example.org/Workshop>",
                                "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16480" to "<http://example.org/hasPublication>",
                                "#b0" to "<http://example.org/paper1>",
                                "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o16481" to "<http://example.org/publishedAt>",
                                "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#_16394" to "_:_16374",
                                "#_16403" to "<http://example.org/Workshop>",
                                "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
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
                                "#p16480",
                                "#b0",
                                "#p16481",
                                "#o16481",
                                "#p16483",
                                "#o16483",
                                "#_16394",
                                "#_16403",
                                "#p16485",
                                "#o16485"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374",
                                    "#_16403" to "<http://example.org/Conference>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374",
                                    "#_16403" to "<http://example.org/Conference>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374",
                                    "#_16403" to "<http://example.org/ConferencePaper>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374",
                                    "#_16403" to "<http://example.org/ConferencePaper>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374",
                                    "#_16403" to "<http://example.org/Employee>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374",
                                    "#_16403" to "<http://example.org/Employee>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374",
                                    "#_16403" to "<http://example.org/GraduateAssistant>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374",
                                    "#_16403" to "<http://example.org/GraduateAssistant>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374",
                                    "#_16403" to "<http://example.org/Student>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374",
                                    "#_16403" to "<http://example.org/Student>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374",
                                    "#_16403" to "<http://example.org/Workshop>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16480" to "<http://example.org/hasPublication>",
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16481" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o16481" to "<http://example.org/publishedAt>",
                                    "#p16483" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16483" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#_16394" to "_:_16374",
                                    "#_16403" to "<http://example.org/Workshop>",
                                    "#p16485" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16485" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16403",
                                "#p16487",
                                "#o16487"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16480",
                            "#b0",
                            "#p16481",
                            "#o16481",
                            "#p16483",
                            "#o16483",
                            "#_16394",
                            "#p16485",
                            "#o16485",
                            "#p16487",
                            "#o16487",
                            "#_16403"
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
                                "#p16480",
                                "#b0",
                                "#p16481",
                                "#o16481",
                                "#p16483",
                                "#o16483",
                                "#_16394",
                                "#p16485",
                                "#o16485",
                                "#p16487",
                                "#o16487",
                                "#_16403"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16394",
                                "#p16489",
                                "#_16403"
                            ), listOf(
                                mutableMapOf(
                                    "#_16394" to "_:_16374",
                                    "#p16489" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                    "#_16403" to "<http://example.org/Conference>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16480",
                            "#b0",
                            "#p16481",
                            "#o16481",
                            "#p16483",
                            "#o16483",
                            "#p16485",
                            "#o16485",
                            "#p16487",
                            "#o16487",
                            "#p16489",
                            "#_16394",
                            "#_16403"
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
                                "#p16480",
                                "#b0",
                                "#p16481",
                                "#o16481",
                                "#p16483",
                                "#o16483",
                                "#p16485",
                                "#o16485",
                                "#p16487",
                                "#o16487",
                                "#p16489",
                                "#_16394",
                                "#_16403"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b0",
                                "#p16491",
                                "#_16394"
                            ), listOf(
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Anite>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Anite>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Conference>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/ConferencePaper>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Employee>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/George>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/George>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/GraduateAssistant>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/John>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/John>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Student>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Workshop>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/hasPublication>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/name>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/person1>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/publishedAt>",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "_:SPARQLDAWGTestOntology",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "#b0" to "_:_16374",
                                    "#p16491" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_16394" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16480",
                            "#p16481",
                            "#o16481",
                            "#p16483",
                            "#o16483",
                            "#p16485",
                            "#o16485",
                            "#p16487",
                            "#o16487",
                            "#p16489",
                            "#_16403",
                            "#p16491",
                            "#b0",
                            "#_16394"
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
                                "#p16566",
                                "#o16566"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16566" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16566" to "<http://example.org/Student>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16567",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Conference>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/ConferencePaper>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Employee>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/GraduateAssistant>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Student>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Workshop>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/hasPublication>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/name>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/publishedAt>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:SPARQLDAWGTestOntology",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:_16502",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p16566",
                            "#o16566",
                            "#p16567",
                            "c",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p16566" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16566" to "<http://example.org/Student>",
                                "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "c" to "<http://example.org/Student>",
                                "x" to "<http://example.org/Anite>"
                            ),
                            mutableMapOf(
                                "#p16566" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16566" to "<http://example.org/Student>",
                                "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
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
                                "#p16566",
                                "#o16566",
                                "#p16567",
                                "c",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "#p16566" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16566" to "<http://example.org/Student>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/Student>",
                                    "x" to "<http://example.org/Anite>"
                                ),
                                mutableMapOf(
                                    "#p16566" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16566" to "<http://example.org/Student>",
                                    "#p16567" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>",
                                    "x" to "<http://example.org/Anite>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p16569",
                                "#o16569"
                            ), listOf(
                                mutableMapOf(
                                    "c" to "<http://example.org/GraduateAssistant>",
                                    "#p16569" to "<http://www.w3.org/2000/01/rdf-schema#subClassOf>",
                                    "#o16569" to "<http://example.org/Employee>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p16566",
                            "#o16566",
                            "#p16567",
                            "x",
                            "#p16569",
                            "#o16569",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "p",
                                "#p16632",
                                "#o16632"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/hasPublication>",
                                    "#p16632" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16632" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "p" to "<http://example.org/publishedAt>",
                                    "#p16632" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16632" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#s16633",
                                "p",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "#s16633" to "<http://example.org/John>",
                                    "p" to "<http://example.org/hasPublication>",
                                    "v" to "<http://example.org/paper1>"
                                ),
                                mutableMapOf(
                                    "#s16633" to "<http://example.org/John>",
                                    "p" to "<http://example.org/name>",
                                    "v" to "\"Johnnie\""
                                ),
                                mutableMapOf(
                                    "#s16633" to "<http://example.org/John>",
                                    "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "v" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "#s16633" to "<http://example.org/John>",
                                    "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "v" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p16632",
                            "#o16632",
                            "#s16633",
                            "v",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "#p16632" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16632" to "<http://www.w3.org/2002/07/owl#ObjectProperty>",
                                "#s16633" to "<http://example.org/John>",
                                "v" to "<http://example.org/paper1>",
                                "p" to "<http://example.org/hasPublication>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "#p16747",
                                "#o16747"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p16747" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16747" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "o",
                            "#p16747",
                            "#o16747",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16747" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16747" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16747" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16747" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16747" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16747" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16747" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16747" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "#p16877",
                                "#o16877"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p16877" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o16877" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "z2",
                            "z",
                            "s",
                            "o",
                            "#p16877",
                            "#o16877",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "z2" to "\"101\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16877" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16877" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z2" to "\"102\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16877" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16877" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z2" to "\"103\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16877" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16877" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z2" to "\"104\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16877" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o16877" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "#p17050",
                                "#o17050"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "o",
                            "#p17050",
                            "#o17050",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "o",
                                "#p17050",
                                "#o17050",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s3>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "#p17050",
                            "#o17050",
                            "p",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "o",
                                "#p17050",
                                "#o17050",
                                "p",
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "p" to "<http://example.org/p>",
                                    "s1" to "<http://example.org/s2>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "p" to "<http://example.org/p>",
                                    "s1" to "<http://example.org/s3>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "p" to "<http://example.org/p>",
                                    "s1" to "<http://example.org/s4>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p1",
                                "#p17054",
                                "#o17054"
                            ), listOf(
                                mutableMapOf(
                                    "p1" to "<http://example.org/p>",
                                    "#p17054" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17054" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "#p17050",
                            "#o17050",
                            "p",
                            "s1",
                            "z",
                            "#p17054",
                            "#o17054",
                            "p1"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "s1" to "<http://example.org/s2>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17054" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17054" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p1" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "s1" to "<http://example.org/s3>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17054" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17054" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p1" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17050" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17050" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "s1" to "<http://example.org/s4>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17054" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17054" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p1" to "<http://example.org/p>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind03.rq */ ,
            {
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
                                "#p17164",
                                "#o17164"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17164" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17164" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "#p17164",
                            "#o17164",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17164" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17164" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17164" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17164" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17164" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17164" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17164" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17164" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "#p17269",
                                "#o17269"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17269" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17269" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "o",
                            "#p17269",
                            "#o17269",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17269" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17269" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17269" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17269" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17269" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17269" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17269" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17269" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "#p17386",
                                "#o17386"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17386" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17386" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "o",
                            "#p17386",
                            "#o17386",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17386" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17386" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17386" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17386" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17386" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17386" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17386" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17386" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "#p17560",
                                "#o17560"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17560" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17560" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "o",
                            "#p17560",
                            "#o17560",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17560" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17560" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17560" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17560" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17560" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17560" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17560" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17560" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
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
                                "#p17701",
                                "#o17701"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "#p17701",
                            "#o17701",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "o",
                                "#p17701",
                                "#o17701",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p17701",
                            "#o17701",
                            "p",
                            "z",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "z" to null,
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "z" to null,
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "z" to null,
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "z" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "z" to null,
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "z" to null,
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "z" to null,
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p17701" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17701" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>",
                                "z" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "#p17820",
                                "#o17820"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17820" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17820" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "o",
                            "#p17820",
                            "#o17820",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17820" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17820" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17820" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17820" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17820" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17820" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17820" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17820" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "#p17937",
                                "#o17937"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17937" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17937" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "o",
                            "#p17937",
                            "#o17937",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17937" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17937" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17937" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17937" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17937" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17937" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17937" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o17937" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "p" to "<http://example.org/p>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p18132",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "#p18132" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "#p18132" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18132" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "_:ont",
                                    "#p18132" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p18133",
                                "#o18133"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p18132",
                            "#p18133",
                            "#o18133",
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
                                "#p18202",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p18202" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p18202" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "#p18202" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/d>",
                                    "#p18202" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "#p18202" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18202" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18202" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://example.org/x/d>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18202" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18202" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "_:x"
                                ),
                                mutableMapOf(
                                    "x" to "_:sparql-dl",
                                    "#p18202" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:x",
                                    "#p18202" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p18203",
                                "#o18203"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p18202",
                            "#p18203",
                            "#o18203",
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
                                "#p18202",
                                "#p18203",
                                "#o18203",
                                "c"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p18205",
                                "#y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18205" to "<http://example.org/x/p>",
                                    "#y" to "<http://example.org/x/a>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18202",
                            "#p18203",
                            "#o18203",
                            "c",
                            "#p18205",
                            "#y",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18274",
                                "#o18274"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "#p18274" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18274" to "<http://example.org/test#Person>"
                                ),
                                mutableMapOf(
                                    "X" to "<http://example.org/test#b>",
                                    "#p18274" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18274" to "<http://example.org/test#Person>"
                                ),
                                mutableMapOf(
                                    "X" to "<http://example.org/test#c>",
                                    "#p18274" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18274" to "<http://example.org/test#Person>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18275",
                                "Y1"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "#p18275" to "<http://example.org/test#name>",
                                    "Y1" to "\"A\""
                                ),
                                mutableMapOf(
                                    "X" to "<http://example.org/test#b>",
                                    "#p18275" to "<http://example.org/test#name>",
                                    "Y1" to "\"B\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18274",
                            "#o18274",
                            "#p18275",
                            "Y1",
                            "X"
                        ), listOf(
                            mutableMapOf(
                                "#p18274" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o18274" to "<http://example.org/test#Person>",
                                "#p18275" to "<http://example.org/test#name>",
                                "Y1" to "\"A\"",
                                "X" to "<http://example.org/test#a>"
                            ),
                            mutableMapOf(
                                "#p18274" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o18274" to "<http://example.org/test#Person>",
                                "#p18275" to "<http://example.org/test#name>",
                                "Y1" to "\"B\"",
                                "X" to "<http://example.org/test#b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p18274",
                                "#o18274",
                                "#p18275",
                                "Y1",
                                "X"
                            ), listOf(
                                mutableMapOf(
                                    "#p18274" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18274" to "<http://example.org/test#Person>",
                                    "#p18275" to "<http://example.org/test#name>",
                                    "Y1" to "\"A\"",
                                    "X" to "<http://example.org/test#a>"
                                ),
                                mutableMapOf(
                                    "#p18274" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18274" to "<http://example.org/test#Person>",
                                    "#p18275" to "<http://example.org/test#name>",
                                    "Y1" to "\"B\"",
                                    "X" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18277",
                                "Y2"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "#p18277" to "<http://example.org/test#nick>",
                                    "Y2" to "\"Anick\""
                                ),
                                mutableMapOf(
                                    "X" to "<http://example.org/test#b>",
                                    "#p18277" to "<http://example.org/test#nick>",
                                    "Y2" to "\"Bnick\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18274",
                            "#o18274",
                            "#p18275",
                            "Y1",
                            "#p18277",
                            "Y2",
                            "X"
                        ), listOf(
                            mutableMapOf(
                                "#p18274" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o18274" to "<http://example.org/test#Person>",
                                "#p18275" to "<http://example.org/test#name>",
                                "Y1" to "\"A\"",
                                "#p18277" to "<http://example.org/test#nick>",
                                "Y2" to "\"Anick\"",
                                "X" to "<http://example.org/test#a>"
                            ),
                            mutableMapOf(
                                "#p18274" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o18274" to "<http://example.org/test#Person>",
                                "#p18275" to "<http://example.org/test#name>",
                                "Y1" to "\"B\"",
                                "#p18277" to "<http://example.org/test#nick>",
                                "Y2" to "\"Bnick\"",
                                "X" to "<http://example.org/test#b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s18395",
                                "#p18395",
                                "#aa"
                            ), listOf(
                                mutableMapOf(
                                    "#s18395" to "<http://example.org/test#a>",
                                    "#p18395" to "<http://example.org/test#p>",
                                    "#aa" to "<http://example.org/test#aa>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#p18396",
                                "#dd"
                            ), listOf(
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#aa>",
                                    "#p18396" to "<http://example.org/test#r>",
                                    "#dd" to "<http://example.org/test#ee>"
                                ),
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#cc>",
                                    "#p18396" to "<http://example.org/test#r>",
                                    "#dd" to "<http://example.org/test#dd>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s18395",
                            "#p18395",
                            "#p18396",
                            "#dd",
                            "#aa"
                        ), listOf(
                            mutableMapOf(
                                "#s18395" to "<http://example.org/test#a>",
                                "#p18395" to "<http://example.org/test#p>",
                                "#p18396" to "<http://example.org/test#r>",
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
                                "#s18395",
                                "#p18395",
                                "#p18396",
                                "#dd",
                                "#aa"
                            ), listOf(
                                mutableMapOf(
                                    "#s18395" to "<http://example.org/test#a>",
                                    "#p18395" to "<http://example.org/test#p>",
                                    "#p18396" to "<http://example.org/test#r>",
                                    "#dd" to "<http://example.org/test#ee>",
                                    "#aa" to "<http://example.org/test#aa>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#dd",
                                "#p18398",
                                "#bb"
                            ), listOf(
                                mutableMapOf(
                                    "#dd" to "<http://example.org/test#dd>",
                                    "#p18398" to "<http://example.org/test#t>",
                                    "#bb" to "<http://example.org/test#bb>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s18395",
                            "#p18395",
                            "#p18396",
                            "#aa",
                            "#p18398",
                            "#bb",
                            "#dd"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s18481",
                                "#p18481",
                                "#aa"
                            ), listOf(
                                mutableMapOf(
                                    "#s18481" to "<http://example.org/test#a>",
                                    "#p18481" to "<http://example.org/test#p>",
                                    "#aa" to "<http://example.org/test#aa>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18482",
                                "Y"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#dd>",
                                    "#p18482" to "<http://example.org/test#t>",
                                    "Y" to "<http://example.org/test#bb>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s18481",
                            "#p18481",
                            "#aa",
                            "X",
                            "#p18482",
                            "Y"
                        ), listOf(
                            mutableMapOf(
                                "#s18481" to "<http://example.org/test#a>",
                                "#p18481" to "<http://example.org/test#p>",
                                "#aa" to "<http://example.org/test#aa>",
                                "X" to "<http://example.org/test#dd>",
                                "#p18482" to "<http://example.org/test#t>",
                                "Y" to "<http://example.org/test#bb>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s18481",
                                "#p18481",
                                "#aa",
                                "X",
                                "#p18482",
                                "Y"
                            ), listOf(
                                mutableMapOf(
                                    "#s18481" to "<http://example.org/test#a>",
                                    "#p18481" to "<http://example.org/test#p>",
                                    "#aa" to "<http://example.org/test#aa>",
                                    "X" to "<http://example.org/test#dd>",
                                    "#p18482" to "<http://example.org/test#t>",
                                    "Y" to "<http://example.org/test#bb>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "Y",
                                "#p18484",
                                "#aa"
                            ), listOf(
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#bb>",
                                    "#p18484" to "<http://example.org/test#s>",
                                    "#aa" to "<http://example.org/test#aa>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s18481",
                            "#p18481",
                            "X",
                            "#p18482",
                            "#p18484",
                            "#aa",
                            "Y"
                        ), listOf(
                            mutableMapOf(
                                "#s18481" to "<http://example.org/test#a>",
                                "#p18481" to "<http://example.org/test#p>",
                                "X" to "<http://example.org/test#dd>",
                                "#p18482" to "<http://example.org/test#t>",
                                "#p18484" to "<http://example.org/test#s>",
                                "#aa" to "<http://example.org/test#aa>",
                                "Y" to "<http://example.org/test#bb>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s18481",
                                "#p18481",
                                "X",
                                "#p18482",
                                "#p18484",
                                "#aa",
                                "Y"
                            ), listOf(
                                mutableMapOf(
                                    "#s18481" to "<http://example.org/test#a>",
                                    "#p18481" to "<http://example.org/test#p>",
                                    "X" to "<http://example.org/test#dd>",
                                    "#p18482" to "<http://example.org/test#t>",
                                    "#p18484" to "<http://example.org/test#s>",
                                    "#aa" to "<http://example.org/test#aa>",
                                    "Y" to "<http://example.org/test#bb>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#p18486",
                                "Z"
                            ), listOf(
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#aa>",
                                    "#p18486" to "<http://example.org/test#r>",
                                    "Z" to "<http://example.org/test#ee>"
                                ),
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#cc>",
                                    "#p18486" to "<http://example.org/test#r>",
                                    "Z" to "<http://example.org/test#dd>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s18481",
                            "#p18481",
                            "X",
                            "#p18482",
                            "#p18484",
                            "Y",
                            "#p18486",
                            "Z",
                            "#aa"
                        ), listOf(
                            mutableMapOf(
                                "#s18481" to "<http://example.org/test#a>",
                                "#p18481" to "<http://example.org/test#p>",
                                "X" to "<http://example.org/test#dd>",
                                "#p18482" to "<http://example.org/test#t>",
                                "#p18484" to "<http://example.org/test#s>",
                                "Y" to "<http://example.org/test#bb>",
                                "#p18486" to "<http://example.org/test#r>",
                                "Z" to "<http://example.org/test#ee>",
                                "#aa" to "<http://example.org/test#aa>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18541",
                                "#a"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "#p18541" to "<http://example.org/test#p>",
                                    "#a" to "<http://example.org/test#aa>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#a",
                                "#p18542",
                                "Y"
                            ), listOf(
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#aa>",
                                    "#p18542" to "<http://example.org/test#r>",
                                    "Y" to "<http://example.org/test#ee>"
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#cc>",
                                    "#p18542" to "<http://example.org/test#r>",
                                    "Y" to "<http://example.org/test#dd>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18541",
                            "#p18542",
                            "Y",
                            "#a"
                        ), listOf(
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18541" to "<http://example.org/test#p>",
                                "#p18542" to "<http://example.org/test#r>",
                                "Y" to "<http://example.org/test#ee>",
                                "#a" to "<http://example.org/test#aa>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18598",
                                "#a"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "#p18598" to "<http://example.org/test#p>",
                                    "#a" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#a",
                                "#p18599",
                                "Y"
                            ), listOf(
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#b>",
                                    "#p18599" to "<http://example.org/test#q>",
                                    "Y" to "<http://example.org/test#c>"
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#b>",
                                    "#p18599" to "<http://example.org/test#q>",
                                    "Y" to "<http://example.org/test#h>"
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#b>",
                                    "#p18599" to "<http://example.org/test#q>",
                                    "Y" to "<http://example.org/test#i>"
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#x>",
                                    "#p18599" to "<http://example.org/test#q>",
                                    "Y" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18598",
                            "#p18599",
                            "Y",
                            "#a"
                        ), listOf(
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18598" to "<http://example.org/test#p>",
                                "#p18599" to "<http://example.org/test#q>",
                                "Y" to "<http://example.org/test#c>",
                                "#a" to "<http://example.org/test#b>"
                            ),
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18598" to "<http://example.org/test#p>",
                                "#p18599" to "<http://example.org/test#q>",
                                "Y" to "<http://example.org/test#h>",
                                "#a" to "<http://example.org/test#b>"
                            ),
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18598" to "<http://example.org/test#p>",
                                "#p18599" to "<http://example.org/test#q>",
                                "Y" to "<http://example.org/test#i>",
                                "#a" to "<http://example.org/test#b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18679",
                                "a"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "#p18679" to "<http://example.org/test#p>",
                                    "a" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "#p18680",
                                "b"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18679",
                            "#p18680",
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
                                "#p18679",
                                "#p18680",
                                "a",
                                "#p18682",
                                "Y",
                                "b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "Y",
                                "#p18684",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#b>",
                                    "#p18684" to "<http://example.org/test#q>",
                                    "c" to "<http://example.org/test#c>"
                                ),
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#b>",
                                    "#p18684" to "<http://example.org/test#q>",
                                    "c" to "<http://example.org/test#h>"
                                ),
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#b>",
                                    "#p18684" to "<http://example.org/test#q>",
                                    "c" to "<http://example.org/test#i>"
                                ),
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#x>",
                                    "#p18684" to "<http://example.org/test#q>",
                                    "c" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18679",
                            "#p18680",
                            "a",
                            "#p18682",
                            "b",
                            "#p18684",
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
                                "#p18845",
                                "b"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/test#a>",
                                    "#p18845" to "<http://example.org/test#p>",
                                    "b" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "#p18846",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "b" to "<http://example.org/test#b>",
                                    "#p18846" to "<http://www.w3.org/2002/07/owl#sameAs>",
                                    "x" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p18845",
                            "#p18846",
                            "x",
                            "b"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/test#a>",
                                "#p18845" to "<http://example.org/test#p>",
                                "#p18846" to "<http://www.w3.org/2002/07/owl#sameAs>",
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
                                "#p18845",
                                "#p18846",
                                "x",
                                "b"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/test#a>",
                                    "#p18845" to "<http://example.org/test#p>",
                                    "#p18846" to "<http://www.w3.org/2002/07/owl#sameAs>",
                                    "x" to "<http://example.org/test#x>",
                                    "b" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p18848",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p18848" to "<http://example.org/test#q>",
                                    "x" to "<http://example.org/test#c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p18848" to "<http://example.org/test#q>",
                                    "x" to "<http://example.org/test#d>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#x>",
                                    "#p18848" to "<http://example.org/test#q>",
                                    "x" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p18845",
                            "#p18846",
                            "b",
                            "#p18848",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/test#a>",
                                "#p18845" to "<http://example.org/test#p>",
                                "#p18846" to "<http://www.w3.org/2002/07/owl#sameAs>",
                                "b" to "<http://example.org/test#b>",
                                "#p18848" to "<http://example.org/test#q>",
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
                                "#_18963",
                                "#p19016",
                                "#o19016"
                            ), listOf(
                                mutableMapOf(
                                    "#_18963" to "_:_18948",
                                    "#p19016" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19016" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_18963" to "_:_18949",
                                    "#p19016" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19016" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18963",
                                "#p19017",
                                "#o19017"
                            ), listOf(
                                mutableMapOf(
                                    "#_18963" to "_:_18948",
                                    "#p19017" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19017" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_18963" to "_:_18949",
                                    "#p19017" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19017" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19016",
                            "#o19016",
                            "#p19017",
                            "#o19017",
                            "#_18963"
                        ), listOf(
                            mutableMapOf(
                                "#p19016" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19016" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19017" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19017" to "<http://example.org/test#hasChild>",
                                "#_18963" to "_:_18948"
                            ),
                            mutableMapOf(
                                "#p19016" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19016" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19017" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19017" to "<http://example.org/test#hasChild>",
                                "#_18963" to "_:_18949"
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
                                "#p19016",
                                "#o19016",
                                "#p19017",
                                "#o19017",
                                "#_18963"
                            ), listOf(
                                mutableMapOf(
                                    "#p19016" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19016" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19017" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19017" to "<http://example.org/test#hasChild>",
                                    "#_18963" to "_:_18948"
                                ),
                                mutableMapOf(
                                    "#p19016" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19016" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19017" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19017" to "<http://example.org/test#hasChild>",
                                    "#_18963" to "_:_18949"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18963",
                                "#p19019",
                                "#o19019"
                            ), listOf(
                                mutableMapOf(
                                    "#_18963" to "_:_18948",
                                    "#p19019" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                    "#o19019" to "<http://www.w3.org/2002/07/owl#Thing>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19016",
                            "#o19016",
                            "#p19017",
                            "#o19017",
                            "#p19019",
                            "#o19019",
                            "#_18963"
                        ), listOf(
                            mutableMapOf(
                                "#p19016" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19016" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19017" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19017" to "<http://example.org/test#hasChild>",
                                "#p19019" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                "#o19019" to "<http://www.w3.org/2002/07/owl#Thing>",
                                "#_18963" to "_:_18948"
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
                                "#p19016",
                                "#o19016",
                                "#p19017",
                                "#o19017",
                                "#p19019",
                                "#o19019",
                                "#_18963"
                            ), listOf(
                                mutableMapOf(
                                    "#p19016" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19016" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19017" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19017" to "<http://example.org/test#hasChild>",
                                    "#p19019" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                    "#o19019" to "<http://www.w3.org/2002/07/owl#Thing>",
                                    "#_18963" to "_:_18948"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19021",
                                "#_18963"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "_:_18949"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18942",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18945",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18948",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18949",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18950",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19021" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_18963" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19016",
                            "#o19016",
                            "#p19017",
                            "#o19017",
                            "#p19019",
                            "#o19019",
                            "parent",
                            "#p19021",
                            "#_18963"
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
                                "#_19053",
                                "#p19111",
                                "#o19111"
                            ), listOf(
                                mutableMapOf(
                                    "#_19053" to "_:_19035",
                                    "#p19111" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19111" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_19053" to "_:_19036",
                                    "#p19111" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19111" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19053",
                                "#p19112",
                                "#o19112"
                            ), listOf(
                                mutableMapOf(
                                    "#_19053" to "_:_19035",
                                    "#p19112" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19112" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_19053" to "_:_19036",
                                    "#p19112" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19112" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19111",
                            "#o19111",
                            "#p19112",
                            "#o19112",
                            "#_19053"
                        ), listOf(
                            mutableMapOf(
                                "#p19111" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19111" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19112" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19112" to "<http://example.org/test#hasChild>",
                                "#_19053" to "_:_19035"
                            ),
                            mutableMapOf(
                                "#p19111" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19111" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19112" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19112" to "<http://example.org/test#hasChild>",
                                "#_19053" to "_:_19036"
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
                                "#p19111",
                                "#o19111",
                                "#p19112",
                                "#o19112",
                                "#_19053"
                            ), listOf(
                                mutableMapOf(
                                    "#p19111" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19111" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19112" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19112" to "<http://example.org/test#hasChild>",
                                    "#_19053" to "_:_19035"
                                ),
                                mutableMapOf(
                                    "#p19111" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19111" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19112" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19112" to "<http://example.org/test#hasChild>",
                                    "#_19053" to "_:_19036"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19053",
                                "#p19114",
                                "#o19114"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19111",
                            "#o19111",
                            "#p19112",
                            "#o19112",
                            "#p19114",
                            "#o19114",
                            "#_19053"
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
                                "#p19111",
                                "#o19111",
                                "#p19112",
                                "#o19112",
                                "#p19114",
                                "#o19114",
                                "#_19053"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19116",
                                "#_19053"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "_:_19036"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19029",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19032",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19035",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19036",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19037",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19116" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19053" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19111",
                            "#o19111",
                            "#p19112",
                            "#o19112",
                            "#p19114",
                            "#o19114",
                            "parent",
                            "#p19116",
                            "#_19053"
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
                                "#_19145",
                                "#p19198",
                                "#o19198"
                            ), listOf(
                                mutableMapOf(
                                    "#_19145" to "_:_19130",
                                    "#p19198" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19198" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_19145" to "_:_19131",
                                    "#p19198" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19198" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19145",
                                "#p19199",
                                "#o19199"
                            ), listOf(
                                mutableMapOf(
                                    "#_19145" to "_:_19130",
                                    "#p19199" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19199" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_19145" to "_:_19131",
                                    "#p19199" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19199" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19198",
                            "#o19198",
                            "#p19199",
                            "#o19199",
                            "#_19145"
                        ), listOf(
                            mutableMapOf(
                                "#p19198" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19198" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19199" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19199" to "<http://example.org/test#hasChild>",
                                "#_19145" to "_:_19130"
                            ),
                            mutableMapOf(
                                "#p19198" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19198" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19199" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19199" to "<http://example.org/test#hasChild>",
                                "#_19145" to "_:_19131"
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
                                "#p19198",
                                "#o19198",
                                "#p19199",
                                "#o19199",
                                "#_19145"
                            ), listOf(
                                mutableMapOf(
                                    "#p19198" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19198" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19199" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19199" to "<http://example.org/test#hasChild>",
                                    "#_19145" to "_:_19130"
                                ),
                                mutableMapOf(
                                    "#p19198" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19198" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19199" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19199" to "<http://example.org/test#hasChild>",
                                    "#_19145" to "_:_19131"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19145",
                                "#p19201",
                                "#o19201"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19198",
                            "#o19198",
                            "#p19199",
                            "#o19199",
                            "#p19201",
                            "#o19201",
                            "#_19145"
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
                                "#p19198",
                                "#o19198",
                                "#p19199",
                                "#o19199",
                                "#p19201",
                                "#o19201",
                                "#_19145"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19203",
                                "#_19145"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "_:_19131"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19124",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19127",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19130",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19131",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19132",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19203" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19145" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19198",
                            "#o19198",
                            "#p19199",
                            "#o19199",
                            "#p19201",
                            "#o19201",
                            "parent",
                            "#p19203",
                            "#_19145"
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
                                "#_19233",
                                "#p19299",
                                "#o19299"
                            ), listOf(
                                mutableMapOf(
                                    "#_19233" to "_:_19217",
                                    "#p19299" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19299" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_19233" to "_:_19218",
                                    "#p19299" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19299" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19233",
                                "#p19300",
                                "#o19300"
                            ), listOf(
                                mutableMapOf(
                                    "#_19233" to "_:_19217",
                                    "#p19300" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19300" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_19233" to "_:_19218",
                                    "#p19300" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19300" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19299",
                            "#o19299",
                            "#p19300",
                            "#o19300",
                            "#_19233"
                        ), listOf(
                            mutableMapOf(
                                "#p19299" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19299" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19300" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19300" to "<http://example.org/test#hasChild>",
                                "#_19233" to "_:_19217"
                            ),
                            mutableMapOf(
                                "#p19299" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19299" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19300" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19300" to "<http://example.org/test#hasChild>",
                                "#_19233" to "_:_19218"
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
                                "#p19299",
                                "#o19299",
                                "#p19300",
                                "#o19300",
                                "#_19233"
                            ), listOf(
                                mutableMapOf(
                                    "#p19299" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19299" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19300" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19300" to "<http://example.org/test#hasChild>",
                                    "#_19233" to "_:_19217"
                                ),
                                mutableMapOf(
                                    "#p19299" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19299" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19300" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19300" to "<http://example.org/test#hasChild>",
                                    "#_19233" to "_:_19218"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19233",
                                "#p19302",
                                "#o19302"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19299",
                            "#o19299",
                            "#p19300",
                            "#o19300",
                            "#p19302",
                            "#o19302",
                            "#_19233"
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
                                "#p19299",
                                "#o19299",
                                "#p19300",
                                "#o19300",
                                "#p19302",
                                "#o19302",
                                "#p19304",
                                "#o19304",
                                "#_19233"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19306",
                                "#_19233"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "_:_19218"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19211",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19214",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19217",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19218",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19219",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19306" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19233" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19299",
                            "#o19299",
                            "#p19300",
                            "#o19300",
                            "#p19302",
                            "#o19302",
                            "#p19304",
                            "#o19304",
                            "parent",
                            "#p19306",
                            "#_19233"
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
                                "#_19337",
                                "#p19403",
                                "#o19403"
                            ), listOf(
                                mutableMapOf(
                                    "#_19337" to "_:_19321",
                                    "#p19403" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19403" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_19337" to "_:_19322",
                                    "#p19403" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19403" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19337",
                                "#p19404",
                                "#o19404"
                            ), listOf(
                                mutableMapOf(
                                    "#_19337" to "_:_19321",
                                    "#p19404" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19404" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_19337" to "_:_19322",
                                    "#p19404" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19404" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19403",
                            "#o19403",
                            "#p19404",
                            "#o19404",
                            "#_19337"
                        ), listOf(
                            mutableMapOf(
                                "#p19403" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19403" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19404" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19404" to "<http://example.org/test#hasChild>",
                                "#_19337" to "_:_19321"
                            ),
                            mutableMapOf(
                                "#p19403" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19403" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19404" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19404" to "<http://example.org/test#hasChild>",
                                "#_19337" to "_:_19322"
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
                                "#p19403",
                                "#o19403",
                                "#p19404",
                                "#o19404",
                                "#_19337"
                            ), listOf(
                                mutableMapOf(
                                    "#p19403" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19403" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19404" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19404" to "<http://example.org/test#hasChild>",
                                    "#_19337" to "_:_19321"
                                ),
                                mutableMapOf(
                                    "#p19403" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19403" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19404" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19404" to "<http://example.org/test#hasChild>",
                                    "#_19337" to "_:_19322"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19337",
                                "#p19406",
                                "#o19406"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19403",
                            "#o19403",
                            "#p19404",
                            "#o19404",
                            "#p19406",
                            "#o19406",
                            "#_19337"
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
                                "#p19403",
                                "#o19403",
                                "#p19404",
                                "#o19404",
                                "#p19406",
                                "#o19406",
                                "#p19408",
                                "#o19408",
                                "#_19337"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19410",
                                "#_19337"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "_:_19322"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19315",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19318",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19321",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19322",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19323",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19410" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19337" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19403",
                            "#o19403",
                            "#p19404",
                            "#o19404",
                            "#p19406",
                            "#o19406",
                            "#p19408",
                            "#o19408",
                            "parent",
                            "#p19410",
                            "#_19337"
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
                                "#_19441",
                                "#p19507",
                                "#o19507"
                            ), listOf(
                                mutableMapOf(
                                    "#_19441" to "_:_19425",
                                    "#p19507" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19507" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_19441" to "_:_19426",
                                    "#p19507" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19507" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19441",
                                "#p19508",
                                "#o19508"
                            ), listOf(
                                mutableMapOf(
                                    "#_19441" to "_:_19425",
                                    "#p19508" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19508" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_19441" to "_:_19426",
                                    "#p19508" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19508" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19507",
                            "#o19507",
                            "#p19508",
                            "#o19508",
                            "#_19441"
                        ), listOf(
                            mutableMapOf(
                                "#p19507" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19507" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19508" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19508" to "<http://example.org/test#hasChild>",
                                "#_19441" to "_:_19425"
                            ),
                            mutableMapOf(
                                "#p19507" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19507" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19508" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19508" to "<http://example.org/test#hasChild>",
                                "#_19441" to "_:_19426"
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
                                "#p19507",
                                "#o19507",
                                "#p19508",
                                "#o19508",
                                "#_19441"
                            ), listOf(
                                mutableMapOf(
                                    "#p19507" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19507" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19508" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19508" to "<http://example.org/test#hasChild>",
                                    "#_19441" to "_:_19425"
                                ),
                                mutableMapOf(
                                    "#p19507" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19507" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19508" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19508" to "<http://example.org/test#hasChild>",
                                    "#_19441" to "_:_19426"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19441",
                                "#p19510",
                                "#o19510"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19507",
                            "#o19507",
                            "#p19508",
                            "#o19508",
                            "#p19510",
                            "#o19510",
                            "#_19441"
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
                                "#p19507",
                                "#o19507",
                                "#p19508",
                                "#o19508",
                                "#p19510",
                                "#o19510",
                                "#p19512",
                                "#o19512",
                                "#_19441"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19514",
                                "#_19441"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "_:_19426"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19419",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19422",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19425",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19426",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19427",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19514" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19441" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19507",
                            "#o19507",
                            "#p19508",
                            "#o19508",
                            "#p19510",
                            "#o19510",
                            "#p19512",
                            "#o19512",
                            "parent",
                            "#p19514",
                            "#_19441"
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
                                "#_19546",
                                "#p19601",
                                "#o19601"
                            ), listOf(
                                mutableMapOf(
                                    "#_19546" to "_:_19529",
                                    "#p19601" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19601" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#_19546" to "_:_19530",
                                    "#p19601" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19601" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19546",
                                "#p19602",
                                "#o19602"
                            ), listOf(
                                mutableMapOf(
                                    "#_19546" to "_:_19529",
                                    "#p19602" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19602" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#_19546" to "_:_19530",
                                    "#p19602" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19602" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19601",
                            "#o19601",
                            "#p19602",
                            "#o19602",
                            "#_19546"
                        ), listOf(
                            mutableMapOf(
                                "#p19601" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19601" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19602" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19602" to "<http://example.org/test#hasChild>",
                                "#_19546" to "_:_19529"
                            ),
                            mutableMapOf(
                                "#p19601" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19601" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19602" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19602" to "<http://example.org/test#hasChild>",
                                "#_19546" to "_:_19530"
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
                                "#p19601",
                                "#o19601",
                                "#p19602",
                                "#o19602",
                                "#_19546"
                            ), listOf(
                                mutableMapOf(
                                    "#p19601" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19601" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19602" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19602" to "<http://example.org/test#hasChild>",
                                    "#_19546" to "_:_19529"
                                ),
                                mutableMapOf(
                                    "#p19601" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19601" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19602" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19602" to "<http://example.org/test#hasChild>",
                                    "#_19546" to "_:_19530"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19546",
                                "#p19604",
                                "#o19604"
                            ), listOf(
                                mutableMapOf(
                                    "#_19546" to "_:_19529",
                                    "#p19604" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                    "#o19604" to "<http://www.w3.org/2002/07/owl#Thing>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19601",
                            "#o19601",
                            "#p19602",
                            "#o19602",
                            "#p19604",
                            "#o19604",
                            "#_19546"
                        ), listOf(
                            mutableMapOf(
                                "#p19601" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o19601" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                "#p19602" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                "#o19602" to "<http://example.org/test#hasChild>",
                                "#p19604" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                "#o19604" to "<http://www.w3.org/2002/07/owl#Thing>",
                                "#_19546" to "_:_19529"
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
                                "#p19601",
                                "#o19601",
                                "#p19602",
                                "#o19602",
                                "#p19604",
                                "#o19604",
                                "#_19546"
                            ), listOf(
                                mutableMapOf(
                                    "#p19601" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19601" to "<http://www.w3.org/2002/07/owl#Restriction>",
                                    "#p19602" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19602" to "<http://example.org/test#hasChild>",
                                    "#p19604" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                    "#o19604" to "<http://www.w3.org/2002/07/owl#Thing>",
                                    "#_19546" to "_:_19529"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#p19606",
                                "#_19546"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19601",
                            "#o19601",
                            "#p19602",
                            "#o19602",
                            "#p19604",
                            "#o19604",
                            "C",
                            "#p19606",
                            "#_19546"
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
                                "#p19703",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b",
                                "#p19704",
                                "#o19704"
                            ), listOf(
                                mutableMapOf(
                                    "#b" to "_:_19620",
                                    "#p19704" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19704" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "#b" to "_:_19621",
                                    "#p19704" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o19704" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#p19703",
                            "#p19704",
                            "#o19704",
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
                                "#p19703",
                                "#p19704",
                                "#o19704",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b",
                                "#p19706",
                                "#o19706"
                            ), listOf(
                                mutableMapOf(
                                    "#b" to "_:_19620",
                                    "#p19706" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19706" to "<http://example.org/test#hasChild>"
                                ),
                                mutableMapOf(
                                    "#b" to "_:_19621",
                                    "#p19706" to "<http://www.w3.org/2002/07/owl#onProperty>",
                                    "#o19706" to "<http://example.org/test#hasChild>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#p19703",
                            "#p19704",
                            "#o19704",
                            "#p19706",
                            "#o19706",
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
                                "#p19703",
                                "#p19704",
                                "#o19704",
                                "#p19706",
                                "#o19706",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b",
                                "#p19708",
                                "#o19708"
                            ), listOf(
                                mutableMapOf(
                                    "#b" to "_:_19620",
                                    "#p19708" to "<http://www.w3.org/2002/07/owl#someValuesFrom>",
                                    "#o19708" to "<http://www.w3.org/2002/07/owl#Thing>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#p19703",
                            "#p19704",
                            "#o19704",
                            "#p19706",
                            "#o19706",
                            "#p19708",
                            "#o19708",
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
                                "#p19807",
                                "#o19807",
                                "#p19808",
                                "#p19810",
                                "#o19810",
                                "#p19812",
                                "#o19812",
                                "#_19742",
                                "#_19734",
                                "#p19814",
                                "#_19737"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p19816",
                                "#_19734"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p19816" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19734" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p19816" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19734" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p19816" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19734" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19816" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19734" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19816" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19734" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p19816" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19734" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p19816" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19734" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19816" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19734" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19816" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19734" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19816" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19734" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19816" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19734" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19816" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19734" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p19816" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19734" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19807",
                            "#o19807",
                            "#p19808",
                            "#p19810",
                            "#o19810",
                            "#p19812",
                            "#o19812",
                            "#_19742",
                            "#p19814",
                            "#_19737",
                            "x",
                            "#p19816",
                            "#_19734"
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
                                "#p19942",
                                "#o19942",
                                "#p19943",
                                "#o19943",
                                "#p19945",
                                "#o19945",
                                "#p19947",
                                "#o19947",
                                "#p19949",
                                "#p19951",
                                "#_19846",
                                "#p19953",
                                "#o19953",
                                "#_19856",
                                "#_19839",
                                "#p19955",
                                "#_19842"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p19957",
                                "#_19839"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p19957" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19839" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p19957" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19839" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p19957" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19839" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19957" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19839" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19957" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19839" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p19957" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19839" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p19957" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19839" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19957" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19839" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19957" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19839" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19957" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19839" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19957" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19839" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19957" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19839" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p19957" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19839" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19942",
                            "#o19942",
                            "#p19943",
                            "#o19943",
                            "#p19945",
                            "#o19945",
                            "#p19947",
                            "#o19947",
                            "#p19949",
                            "#p19951",
                            "#_19846",
                            "#p19953",
                            "#o19953",
                            "#_19856",
                            "#p19955",
                            "#_19842",
                            "x",
                            "#p19957",
                            "#_19839"
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
                                "#p20086",
                                "#o20086",
                                "#p20087",
                                "#o20087",
                                "#p20089",
                                "#o20089",
                                "#p20091",
                                "#p20093",
                                "#o20093",
                                "#p20095",
                                "#o20095",
                                "#_19999",
                                "#p20097",
                                "#_19994",
                                "#p20099",
                                "#_19983",
                                "#_19991"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20101",
                                "#_19983"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20101" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19983" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20101" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19983" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20101" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19983" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20101" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19983" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20101" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19983" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20101" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19983" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20101" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19983" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20101" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19983" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20101" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19983" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20101" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19983" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20101" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19983" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20101" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19983" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20101" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_19983" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20086",
                            "#o20086",
                            "#p20087",
                            "#o20087",
                            "#p20089",
                            "#o20089",
                            "#p20091",
                            "#p20093",
                            "#o20093",
                            "#p20095",
                            "#o20095",
                            "#_19999",
                            "#p20097",
                            "#_19994",
                            "#p20099",
                            "#_19991",
                            "x",
                            "#p20101",
                            "#_19983"
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
                                "#_20127",
                                "#p20209",
                                "#o20209"
                            ), listOf(
                                mutableMapOf(
                                    "#_20127" to "<http://example.org/test#A>",
                                    "#p20209" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20209" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20127" to "<http://example.org/test#B>",
                                    "#p20209" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20209" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20127" to "<http://example.org/test#C>",
                                    "#p20209" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20209" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20132",
                                "#p20210",
                                "#o20210"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20127",
                            "#p20209",
                            "#o20209",
                            "#_20132",
                            "#p20210",
                            "#o20210"
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
                                "#p20209",
                                "#o20209",
                                "#p20210",
                                "#o20210",
                                "#p20212",
                                "#p20214",
                                "#o20214",
                                "#p20216",
                                "#o20216",
                                "#_20137",
                                "#p20218",
                                "#_20127",
                                "#_20132"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20220",
                                "#_20127"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20220" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20127" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20220" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20127" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20220" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20127" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20220" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20127" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20220" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20127" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20220" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20127" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20220" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20127" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20220" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20127" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20220" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20127" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20220" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20127" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20220" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20127" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20220" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20127" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20220" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20127" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20209",
                            "#o20209",
                            "#p20210",
                            "#o20210",
                            "#p20212",
                            "#p20214",
                            "#o20214",
                            "#p20216",
                            "#o20216",
                            "#_20137",
                            "#p20218",
                            "#_20132",
                            "x",
                            "#p20220",
                            "#_20127"
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
                                "#p20356",
                                "#o20356",
                                "#p20357",
                                "#o20357",
                                "#_20244"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20252",
                                "#p20359",
                                "#o20359"
                            ), listOf(
                                mutableMapOf(
                                    "#_20252" to "<http://example.org/test#A>",
                                    "#p20359" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20359" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20252" to "<http://example.org/test#B>",
                                    "#p20359" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20359" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20252" to "<http://example.org/test#C>",
                                    "#p20359" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20359" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20356",
                            "#o20356",
                            "#p20357",
                            "#o20357",
                            "#_20244",
                            "#_20252",
                            "#p20359",
                            "#o20359"
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
                                "#p20356",
                                "#o20356",
                                "#p20357",
                                "#o20357",
                                "#p20359",
                                "#o20359",
                                "#p20361",
                                "#o20361",
                                "#p20363",
                                "#p20365",
                                "#o20365",
                                "#p20367",
                                "#o20367",
                                "#_20262",
                                "#p20369",
                                "#_20257",
                                "#p20371",
                                "#_20244",
                                "#_20252"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20373",
                                "#_20244"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20373" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20244" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20373" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20244" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20373" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20244" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20373" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20244" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20373" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20244" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20373" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20244" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20373" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20244" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20373" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20244" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20373" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20244" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20373" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20244" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20373" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20244" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20373" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20244" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20373" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20244" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20356",
                            "#o20356",
                            "#p20357",
                            "#o20357",
                            "#p20359",
                            "#o20359",
                            "#p20361",
                            "#o20361",
                            "#p20363",
                            "#p20365",
                            "#o20365",
                            "#p20367",
                            "#o20367",
                            "#_20262",
                            "#p20369",
                            "#_20257",
                            "#p20371",
                            "#_20252",
                            "x",
                            "#p20373",
                            "#_20244"
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
                                "#p20531",
                                "#o20531",
                                "#p20532",
                                "#o20532",
                                "#_20400"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20408",
                                "#p20534",
                                "#o20534"
                            ), listOf(
                                mutableMapOf(
                                    "#_20408" to "<http://example.org/test#A>",
                                    "#p20534" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20534" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20408" to "<http://example.org/test#B>",
                                    "#p20534" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20534" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20408" to "<http://example.org/test#C>",
                                    "#p20534" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20534" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20531",
                            "#o20531",
                            "#p20532",
                            "#o20532",
                            "#_20400",
                            "#_20408",
                            "#p20534",
                            "#o20534"
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
                                "#p20531",
                                "#o20531",
                                "#p20532",
                                "#o20532",
                                "#p20534",
                                "#o20534",
                                "#p20536",
                                "#o20536",
                                "#p20538",
                                "#p20540",
                                "#o20540",
                                "#p20542",
                                "#_20418",
                                "#p20544",
                                "#o20544",
                                "#p20546",
                                "#o20546",
                                "#_20423",
                                "#p20548",
                                "#_20413",
                                "#p20550",
                                "#_20400",
                                "#_20408"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20552",
                                "#_20400"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20552" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20400" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20552" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20400" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20552" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20400" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20552" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20400" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20552" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20400" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20552" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20400" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20552" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20400" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20552" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20400" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20552" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20400" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20552" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20400" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20552" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20400" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20552" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20400" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20552" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20400" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20531",
                            "#o20531",
                            "#p20532",
                            "#o20532",
                            "#p20534",
                            "#o20534",
                            "#p20536",
                            "#o20536",
                            "#p20538",
                            "#p20540",
                            "#o20540",
                            "#p20542",
                            "#_20418",
                            "#p20544",
                            "#o20544",
                            "#p20546",
                            "#o20546",
                            "#_20423",
                            "#p20548",
                            "#_20413",
                            "#p20550",
                            "#_20408",
                            "x",
                            "#p20552",
                            "#_20400"
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
                                "#_20581",
                                "#p20720",
                                "#o20720"
                            ), listOf(
                                mutableMapOf(
                                    "#_20581" to "<http://example.org/test#A>",
                                    "#p20720" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20720" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20581" to "<http://example.org/test#B>",
                                    "#p20720" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20720" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20581" to "<http://example.org/test#C>",
                                    "#p20720" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20720" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20586",
                                "#p20721",
                                "#o20721"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20581",
                            "#p20720",
                            "#o20720",
                            "#_20586",
                            "#p20721",
                            "#o20721"
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
                                "#_20581",
                                "#p20720",
                                "#o20720",
                                "#_20586",
                                "#p20721",
                                "#o20721"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20590",
                                "#p20723",
                                "#o20723"
                            ), listOf(
                                mutableMapOf(
                                    "#_20590" to "<http://example.org/test#A>",
                                    "#p20723" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20723" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20590" to "<http://example.org/test#B>",
                                    "#p20723" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20723" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#_20590" to "<http://example.org/test#C>",
                                    "#p20723" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o20723" to "<http://www.w3.org/2002/07/owl#Class>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20581",
                            "#p20720",
                            "#o20720",
                            "#_20586",
                            "#p20721",
                            "#o20721",
                            "#_20590",
                            "#p20723",
                            "#o20723"
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
                                "#p20720",
                                "#o20720",
                                "#p20721",
                                "#o20721",
                                "#p20723",
                                "#o20723",
                                "#p20725",
                                "#o20725",
                                "#p20727",
                                "#p20729",
                                "#o20729",
                                "#p20731",
                                "#o20731",
                                "#_20600",
                                "#p20733",
                                "#_20595",
                                "#p20735",
                                "#p20737",
                                "#_20590",
                                "#p20739",
                                "#o20739",
                                "#_20606",
                                "#p20741",
                                "#_20581",
                                "#_20586"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20743",
                                "#_20581"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20743" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20581" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20743" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20581" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20743" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20581" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20743" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20581" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20743" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20581" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20743" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20581" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20743" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20581" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20743" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20581" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20743" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20581" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20743" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20581" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20743" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20581" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20743" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20581" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20743" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20581" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20720",
                            "#o20720",
                            "#p20721",
                            "#o20721",
                            "#p20723",
                            "#o20723",
                            "#p20725",
                            "#o20725",
                            "#p20727",
                            "#p20729",
                            "#o20729",
                            "#p20731",
                            "#o20731",
                            "#_20600",
                            "#p20733",
                            "#_20595",
                            "#p20735",
                            "#p20737",
                            "#_20590",
                            "#p20739",
                            "#o20739",
                            "#_20606",
                            "#p20741",
                            "#_20586",
                            "x",
                            "#p20743",
                            "#_20581"
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
                                "#p20858",
                                "#o20858",
                                "#p20859",
                                "#o20859",
                                "#p20861",
                                "#o20861",
                                "#p20863",
                                "#o20863",
                                "#p20865",
                                "#o20865",
                                "#p20867",
                                "#_20773",
                                "#_20781"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20869",
                                "#_20773"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20869" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20773" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20869" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20773" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20869" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20773" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20869" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20773" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20869" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20773" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20869" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20773" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20869" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20773" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20869" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20773" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20869" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20773" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20869" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20773" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20869" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20773" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20869" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20773" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20869" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#_20773" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20858",
                            "#o20858",
                            "#p20859",
                            "#o20859",
                            "#p20861",
                            "#o20861",
                            "#p20863",
                            "#o20863",
                            "#p20865",
                            "#o20865",
                            "#p20867",
                            "#_20781",
                            "x",
                            "#p20869",
                            "#_20773"
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple8.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s24014",
                                "#p24014",
                                "str1"
                            ), listOf(
                                mutableMapOf(
                                    "#s24014" to "<http://example.org/s6>",
                                    "#p24014" to "<http://example.org/str>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#s24015",
                                "#p24015",
                                "str2"
                            ), listOf(
                                mutableMapOf(
                                    "#s24015" to "<http://example.org/s7>",
                                    "#p24015" to "<http://example.org/str>",
                                    "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s24014",
                            "#p24014",
                            "str1",
                            "#s24015",
                            "#p24015",
                            "str2"
                        ), listOf(
                            mutableMapOf(
                                "#s24014" to "<http://example.org/s6>",
                                "#p24014" to "<http://example.org/str>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#s24015" to "<http://example.org/s7>",
                                "#p24015" to "<http://example.org/str>",
                                "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#s24042",
                                "#p24042",
                                "str1"
                            ), listOf(
                                mutableMapOf(
                                    "#s24042" to "<http://example.org/s6>",
                                    "#p24042" to "<http://example.org/str>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#s24043",
                                "#p24043",
                                "str2"
                            ), listOf(
                                mutableMapOf(
                                    "#s24043" to "<http://example.org/s7>",
                                    "#p24043" to "<http://example.org/str>",
                                    "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s24042",
                            "#p24042",
                            "str1",
                            "#s24043",
                            "#p24043",
                            "str2"
                        ), listOf(
                            mutableMapOf(
                                "#s24042" to "<http://example.org/s6>",
                                "#p24042" to "<http://example.org/str>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#s24043" to "<http://example.org/s7>",
                                "#p24043" to "<http://example.org/str>",
                                "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s1",
                                "#p24129",
                                "str1"
                            ), listOf(
                                mutableMapOf(
                                    "s1" to "<http://example.org/s1>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"123\""
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s2>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s3>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s4>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s5>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s6>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s7>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s2",
                                "#p24130",
                                "str2"
                            ), listOf(
                                mutableMapOf(
                                    "s2" to "<http://example.org/s1>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "s2" to "<http://example.org/s2>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "s2" to "<http://example.org/s3>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "s2" to "<http://example.org/s4>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "s2" to "<http://example.org/s5>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s2" to "<http://example.org/s6>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s2" to "<http://example.org/s7>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s1",
                            "#p24129",
                            "str1",
                            "s2",
                            "#p24130",
                            "str2"
                        ), listOf(
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s1>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s1>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s1>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s1>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s1>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s1>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s1>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s2>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s2>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s2>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s2>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s2>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s2>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s2>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s3>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s3>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s3>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s3>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s3>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s3>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s3>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s4>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s4>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s4>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s4>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s4>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s4>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s4>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s5>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s5>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s5>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s5>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s5>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s5>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s5>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s6>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s6>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s6>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s6>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s6>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s6>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s6>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s7>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s7>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s7>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s7>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s7>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s7>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24129" to "<http://example.org/str>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s7>",
                                "#p24130" to "<http://example.org/str>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p26417",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"a\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "_:b"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "<http://example/a>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p26418",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p26417",
                            "x",
                            "#p26418",
                            "y",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p26417" to "<http://example/p>",
                                "x" to "\"a\"",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x1>"
                            ),
                            mutableMapOf(
                                "#p26417" to "<http://example/p>",
                                "x" to "_:b",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x2>"
                            ),
                            mutableMapOf(
                                "#p26417" to "<http://example/p>",
                                "x" to "<http://example/a>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x3>"
                            ),
                            mutableMapOf(
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>"
                            ),
                            mutableMapOf(
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x5>"
                            ),
                            mutableMapOf(
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x6>"
                            ),
                            mutableMapOf(
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x7>"
                            ),
                            mutableMapOf(
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x8>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p26590",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"a\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "#p26590" to "<http://example/p>",
                                    "x" to "_:b"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "#p26590" to "<http://example/p>",
                                    "x" to "<http://example/a>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p26591",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p26590",
                            "x",
                            "#p26591",
                            "y",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p26590" to "<http://example/p>",
                                "x" to "\"a\"",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x1>"
                            ),
                            mutableMapOf(
                                "#p26590" to "<http://example/p>",
                                "x" to "_:b",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x2>"
                            ),
                            mutableMapOf(
                                "#p26590" to "<http://example/p>",
                                "x" to "<http://example/a>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x3>"
                            ),
                            mutableMapOf(
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>"
                            ),
                            mutableMapOf(
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x5>"
                            ),
                            mutableMapOf(
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x6>"
                            ),
                            mutableMapOf(
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x7>"
                            ),
                            mutableMapOf(
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x8>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "#p28459",
                                "s1"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/s1>",
                                    "#p28459" to "<http://example.org/str>",
                                    "s1" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s2>",
                                    "#p28459" to "<http://example.org/str>",
                                    "s1" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s3>",
                                    "#p28459" to "<http://example.org/str>",
                                    "s1" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s4>",
                                    "#p28459" to "<http://example.org/str>",
                                    "s1" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s5>",
                                    "#p28459" to "<http://example.org/str>",
                                    "s1" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s6>",
                                    "#p28459" to "<http://example.org/str>",
                                    "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s7>",
                                    "#p28459" to "<http://example.org/str>",
                                    "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "#p28460",
                                "s2"
                            ), listOf(
                                mutableMapOf(
                                    "b" to "<http://example.org/s1>",
                                    "#p28460" to "<http://example.org/str>",
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "b" to "<http://example.org/s2>",
                                    "#p28460" to "<http://example.org/str>",
                                    "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "b" to "<http://example.org/s3>",
                                    "#p28460" to "<http://example.org/str>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "b" to "<http://example.org/s4>",
                                    "#p28460" to "<http://example.org/str>",
                                    "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "b" to "<http://example.org/s5>",
                                    "#p28460" to "<http://example.org/str>",
                                    "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "b" to "<http://example.org/s6>",
                                    "#p28460" to "<http://example.org/str>",
                                    "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "b" to "<http://example.org/s7>",
                                    "#p28460" to "<http://example.org/str>",
                                    "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p28459",
                            "s1",
                            "b",
                            "#p28460",
                            "s2"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s1>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s1>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s1>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s1>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s1>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s1>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s1>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s2>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s2>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s2>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s2>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s2>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s2>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s2>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s3>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s3>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s3>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s3>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s3>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s3>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s3>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s4>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s4>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s4>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s4>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s4>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s4>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s4>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s5>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s5>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s5>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s5>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s5>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s5>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s5>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s6>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s6>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s6>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s6>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s6>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s6>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s6>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s7>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s7>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s7>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s7>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s7>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s7>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s7>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p32591",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32591" to "<http://example/p>",
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/s2>",
                                    "#p32591" to "<http://example/p>",
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p32592",
                                "w"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32592" to "<http://example/q>",
                                    "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "#p32591",
                            "v",
                            "#p32592",
                            "w",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p32591" to "<http://example/p>",
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32592" to "<http://example/q>",
                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/s1>"
                            ),
                            mutableMapOf(
                                "#p32591" to "<http://example/p>",
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32592" to null,
                                "w" to null,
                                "s" to "<http://example/s2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p32629",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32629" to "<http://example/p>",
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/s2>",
                                    "#p32629" to "<http://example/p>",
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p32630",
                                "w"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32630" to "<http://example/q>",
                                    "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "#p32629",
                            "v",
                            "#p32630",
                            "w",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p32629" to "<http://example/p>",
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32630" to "<http://example/q>",
                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/s1>"
                            ),
                            mutableMapOf(
                                "#p32629" to "<http://example/p>",
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32630" to null,
                                "w" to null,
                                "s" to "<http://example/s2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p32786",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32786" to "<http://example/p>",
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/s2>",
                                    "#p32786" to "<http://example/p>",
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/s3>",
                                    "#p32786" to "<http://example/p>",
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p32787",
                                "w"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32787" to "<http://example/q>",
                                    "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "#p32786",
                            "v",
                            "#p32787",
                            "w",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p32786" to "<http://example/p>",
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32787" to "<http://example/q>",
                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/s1>"
                            ),
                            mutableMapOf(
                                "#p32786" to "<http://example/p>",
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32787" to null,
                                "w" to null,
                                "s" to "<http://example/s2>"
                            ),
                            mutableMapOf(
                                "#p32786" to "<http://example/p>",
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32787" to null,
                                "w" to null,
                                "s" to "<http://example/s3>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "#p32797",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32797" to "<http://example/p>",
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/s2>",
                                    "#p32797" to "<http://example/p>",
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/s3>",
                                    "#p32797" to "<http://example/p>",
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p32798",
                                "w"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32798" to "<http://example/q>",
                                    "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "#p32797",
                            "v",
                            "#p32798",
                            "w",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p32797" to "<http://example/p>",
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32798" to "<http://example/q>",
                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/s1>"
                            ),
                            mutableMapOf(
                                "#p32797" to "<http://example/p>",
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32798" to null,
                                "w" to null,
                                "s" to "<http://example/s2>"
                            ),
                            mutableMapOf(
                                "#p32797" to "<http://example/p>",
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32798" to null,
                                "w" to null,
                                "s" to "<http://example/s3>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
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
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p34863",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34863" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p34864",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34864" to "<http://www.example.org/schema#q>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34864" to "<http://www.example.org/schema#q>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p34863",
                            "y",
                            "#p34864",
                            "z",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p34863" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34864" to "<http://www.example.org/schema#q>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            ),
                            mutableMapOf(
                                "#p34863" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34864" to "<http://www.example.org/schema#q>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p34902",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34902" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p34903",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34903" to "<http://www.example.org/schema#q>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34903" to "<http://www.example.org/schema#q>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p34902",
                            "y",
                            "#p34903",
                            "z",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p34902" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34903" to "<http://www.example.org/schema#q>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            ),
                            mutableMapOf(
                                "#p34902" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34903" to "<http://www.example.org/schema#q>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35012",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35012" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35013",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35013" to "<http://www.example.org/schema#q>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35013" to "<http://www.example.org/schema#q>",
                                    "z" to "\"foobar\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p35012",
                            "y",
                            "#p35013",
                            "z",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p35012" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35013" to "<http://www.example.org/schema#q>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            ),
                            mutableMapOf(
                                "#p35012" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35013" to "<http://www.example.org/schema#q>",
                                "z" to "\"foobar\"",
                                "x" to "<http://www.example.org/instance#a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35040",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35040" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35041",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35041" to "<http://www.example.org/schema#q>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35041" to "<http://www.example.org/schema#q>",
                                    "z" to "\"foobar\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p35040",
                            "y",
                            "#p35041",
                            "z",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p35040" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35041" to "<http://www.example.org/schema#q>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            ),
                            mutableMapOf(
                                "#p35040" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35041" to "<http://www.example.org/schema#q>",
                                "z" to "\"foobar\"",
                                "x" to "<http://www.example.org/instance#a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35152",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35152" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35153",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35153" to "<http://www.example.org/schema#q>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p35152",
                            "y",
                            "#p35153",
                            "z",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p35152" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35153" to "<http://www.example.org/schema#q>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35197",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35197" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35198",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35198" to "<http://www.example.org/schema#q>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p35197",
                            "y",
                            "#p35198",
                            "z",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p35197" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35198" to "<http://www.example.org/schema#q>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35656",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35656" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#b>",
                                    "#p35656" to "<http://www.example.org/schema#p>",
                                    "y" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35657",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35657" to "<http://www.example.org/schema#q>",
                                    "l" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "#p35656",
                            "y",
                            "#p35657",
                            "l",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p35656" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35657" to "<http://www.example.org/schema#q>",
                                "l" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            ),
                            mutableMapOf(
                                "#p35656" to "<http://www.example.org/schema#p>",
                                "y" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35657" to null,
                                "l" to null,
                                "x" to "<http://www.example.org/instance#b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_36993",
                                "#p37064",
                                "L"
                            ), listOf(
                                mutableMapOf(
                                    "#_36993" to "_:_36970",
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36971",
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36972",
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36973",
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36974",
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36975",
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36976",
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Sandwich\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36977",
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36978",
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Bagel\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36979",
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37065",
                                "#_36993"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36970"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36971"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36972"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36973"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36974"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36975"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36976"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36977"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36978"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36979"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37064",
                            "L",
                            "O",
                            "#p37065",
                            "#_36993"
                        ), listOf(
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36970"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36971"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36972"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36973"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36974"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36975"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Sandwich\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36976"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36977"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Bagel\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36978"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36979"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p37064",
                                "L",
                                "O",
                                "#p37065",
                                "#_36993"
                            ), listOf(
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36970"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36971"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36972"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36973"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36974"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36975"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Sandwich\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36976"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36977"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Bagel\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36978"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36979"
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37064",
                            "L",
                            "#p37065",
                            "#_36993",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36970",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36971",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36972",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36973",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36974",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36975",
                                "O" to "<http://www.example.orgorder2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_36993",
                                "#p37085",
                                "L"
                            ), listOf(
                                mutableMapOf(
                                    "#_36993" to "_:_36970",
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36971",
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36972",
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36973",
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36974",
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36975",
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36976",
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Sandwich\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36977",
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36978",
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Bagel\""
                                ),
                                mutableMapOf(
                                    "#_36993" to "_:_36979",
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37086",
                                "#_36993"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36970"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36971"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36972"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36973"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36974"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36975"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36976"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36977"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36978"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36979"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37085",
                            "L",
                            "O",
                            "#p37086",
                            "#_36993"
                        ), listOf(
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36970"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36971"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36972"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36973"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36974"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36975"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Sandwich\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36976"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36977"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Bagel\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36978"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36979"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p37085",
                                "L",
                                "O",
                                "#p37086",
                                "#_36993"
                            ), listOf(
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36970"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36971"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36972"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36973"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36974"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36975"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Sandwich\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36976"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36977"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Bagel\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36978"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36979"
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37085",
                            "L",
                            "#p37086",
                            "#_36993",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36970",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36971",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36972",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36973",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36974",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36975",
                                "O" to "<http://www.example.orgorder2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "P",
                                "#p37207",
                                "F"
                            ), listOf(
                                mutableMapOf(
                                    "P" to "<http://p1>",
                                    "#p37207" to "<http://xmlns.com/foaf/0.1/firstName>",
                                    "F" to "\"John\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "#p37208",
                                "L"
                            ), listOf(
                                mutableMapOf(
                                    "P" to "<http://p1>",
                                    "#p37208" to "<http://xmlns.com/foaf/0.1/lastName>",
                                    "L" to "\"Doe\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37207",
                            "F",
                            "#p37208",
                            "L",
                            "P"
                        ), listOf(
                            mutableMapOf(
                                "#p37207" to "<http://xmlns.com/foaf/0.1/firstName>",
                                "F" to "\"John\"",
                                "#p37208" to "<http://xmlns.com/foaf/0.1/lastName>",
                                "L" to "\"Doe\"",
                                "P" to "<http://p1>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "P",
                                "#p37262",
                                "F"
                            ), listOf(
                                mutableMapOf(
                                    "P" to "<http://p1>",
                                    "#p37262" to "<http://xmlns.com/foaf/0.1/firstName>",
                                    "F" to "\"John\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "#p37263",
                                "L"
                            ), listOf(
                                mutableMapOf(
                                    "P" to "<http://p1>",
                                    "#p37263" to "<http://xmlns.com/foaf/0.1/lastName>",
                                    "L" to "\"Doe\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37262",
                            "F",
                            "#p37263",
                            "L",
                            "P"
                        ), listOf(
                            mutableMapOf(
                                "#p37262" to "<http://xmlns.com/foaf/0.1/firstName>",
                                "F" to "\"John\"",
                                "#p37263" to "<http://xmlns.com/foaf/0.1/lastName>",
                                "L" to "\"Doe\"",
                                "P" to "<http://p1>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_37358",
                                "#p37429",
                                "L"
                            ), listOf(
                                mutableMapOf(
                                    "#_37358" to "_:_37335",
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37336",
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37337",
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37338",
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37339",
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37340",
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37341",
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Sandwich\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37342",
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37343",
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Bagel\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37344",
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37430",
                                "#_37358"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37335"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37336"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37337"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37338"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37339"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37340"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37341"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37342"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37343"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37344"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37429",
                            "L",
                            "O",
                            "#p37430",
                            "#_37358"
                        ), listOf(
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37335"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37336"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37337"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37338"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37339"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37340"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Sandwich\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37341"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37342"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Bagel\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37343"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37344"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p37429",
                                "L",
                                "O",
                                "#p37430",
                                "#_37358"
                            ), listOf(
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37335"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37336"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37337"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37338"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37339"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37340"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Sandwich\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37341"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37342"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Bagel\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37343"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37344"
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37429",
                            "L",
                            "#p37430",
                            "#_37358",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37335",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37336",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37337",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37338",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37339",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37340",
                                "O" to "<http://www.example.orgorder2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_37358",
                                "#p37450",
                                "L"
                            ), listOf(
                                mutableMapOf(
                                    "#_37358" to "_:_37335",
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37336",
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37337",
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37338",
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37339",
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37340",
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37341",
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Sandwich\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37342",
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37343",
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Bagel\""
                                ),
                                mutableMapOf(
                                    "#_37358" to "_:_37344",
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37451",
                                "#_37358"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37335"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37336"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37337"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37338"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37339"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37340"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37341"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37342"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37343"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37344"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37450",
                            "L",
                            "O",
                            "#p37451",
                            "#_37358"
                        ), listOf(
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37335"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37336"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37337"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37338"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37339"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37340"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Sandwich\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37341"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37342"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Bagel\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37343"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37344"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#p37450",
                                "L",
                                "O",
                                "#p37451",
                                "#_37358"
                            ), listOf(
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37335"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37336"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37337"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37338"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37339"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37340"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Sandwich\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37341"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37342"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Bagel\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37343"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37344"
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37450",
                            "L",
                            "#p37451",
                            "#_37358",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37335",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37336",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37337",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37338",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37339",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37340",
                                "O" to "<http://www.example.orgorder2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
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
                    if (!expected.myEquals(output)){
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
