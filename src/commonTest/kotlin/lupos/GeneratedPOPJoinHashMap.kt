package lupos

import lupos.s12p2p.P2P
import lupos.s14endpoint.EndpointImpl
import lupos.s00misc.*
import lupos.s15tripleStoreDistributed.*
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
    constructor(){
        P2P.knownClients.add(EndpointImpl.fullname)
    }
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
                                    "#p2396" to null,
                                    "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2396" to null,
                                    "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2396" to null,
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
                                    "#p2397" to null,
                                    "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2397" to null,
                                    "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2397" to null,
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
                                "#p2396" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2396" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
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
                                    "#p2658" to null,
                                    "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2658" to null,
                                    "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2658" to null,
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
                                    "#p2659" to null,
                                    "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2659" to null,
                                    "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "#p2659" to null,
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
                                "#p2658" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>"
                            ),
                            mutableMapOf(
                                "#p2658" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
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
                                "o",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "o" to null,
                                    "z" to null
                                ),
                                mutableMapOf(
                                    "o" to null,
                                    "z" to null
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
                                "book",
                                "#p8341",
                                "title"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8341" to null,
                                    "title" to "\"SPARQL Tutorial\""
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8341" to null,
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
                                    "#p8342" to null,
                                    "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8342" to null,
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
                                "#p8341" to null,
                                "title" to "\"SPARQL Tutorial\"",
                                "#p8342" to null,
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "book" to "<http://example.org/book/book1>"
                            ),
                            mutableMapOf(
                                "#p8341" to null,
                                "title" to "\"The Semantic Web\"",
                                "#p8342" to null,
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
                                "book",
                                "#p8351",
                                "title"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8351" to null,
                                    "title" to "\"SPARQL Tutorial\""
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8351" to null,
                                    "title" to "\"The Semantic Web\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "#p8352",
                                "price"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8352" to null,
                                    "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8352" to null,
                                    "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p8351",
                            "title",
                            "#p8352",
                            "price",
                            "book"
                        ), listOf(
                            mutableMapOf(
                                "#p8351" to null,
                                "title" to "\"SPARQL Tutorial\"",
                                "#p8352" to null,
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "book" to "<http://example.org/book/book1>"
                            ),
                            mutableMapOf(
                                "#p8351" to null,
                                "title" to "\"The Semantic Web\"",
                                "#p8352" to null,
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
                                "#p8782",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/a>",
                                    "#p8782" to null,
                                    "o2" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/b>",
                                    "#p8782" to null,
                                    "o2" to "<http://example.org/c>"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "p1",
                            "o1",
                            "#p8782",
                            "o2",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o1" to "<http://example.org/b>",
                                "#p8782" to null,
                                "o2" to "<http://example.org/b>",
                                "s" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"alan@example.org\"",
                                "#p8782" to null,
                                "o2" to "<http://example.org/b>",
                                "s" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Alan\"",
                                "#p8782" to null,
                                "o2" to "<http://example.org/b>",
                                "s" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o1" to "<http://example.org/c>",
                                "#p8782" to null,
                                "o2" to "<http://example.org/c>",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"bob@example.org\"",
                                "#p8782" to null,
                                "o2" to "<http://example.org/c>",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Bob\"",
                                "#p8782" to null,
                                "o2" to "<http://example.org/c>",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"alice@example.org\"",
                                "#p8782" to null,
                                "o2" to null,
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Alice\"",
                                "#p8782" to null,
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
                                "#p8870",
                                "title"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8870" to null,
                                    "title" to "\"SPARQL Tutorial\""
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8870" to null,
                                    "title" to "\"The Semantic Web\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "#p8871",
                                "price"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8871" to null,
                                    "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8871" to null,
                                    "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p8870",
                            "title",
                            "#p8871",
                            "price",
                            "book"
                        ), listOf(
                            mutableMapOf(
                                "#p8870" to null,
                                "title" to "\"SPARQL Tutorial\"",
                                "#p8871" to null,
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "book" to "<http://example.org/book/book1>"
                            ),
                            mutableMapOf(
                                "#p8870" to null,
                                "title" to "\"The Semantic Web\"",
                                "#p8871" to null,
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
                                "book",
                                "#p8880",
                                "title"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8880" to null,
                                    "title" to "\"SPARQL Tutorial\""
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8880" to null,
                                    "title" to "\"The Semantic Web\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "#p8881",
                                "price"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8881" to null,
                                    "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8881" to null,
                                    "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p8880",
                            "title",
                            "#p8881",
                            "price",
                            "book"
                        ), listOf(
                            mutableMapOf(
                                "#p8880" to null,
                                "title" to "\"SPARQL Tutorial\"",
                                "#p8881" to null,
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "book" to "<http://example.org/book/book1>"
                            ),
                            mutableMapOf(
                                "#p8880" to null,
                                "title" to "\"The Semantic Web\"",
                                "#p8881" to null,
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
                                "#p8958",
                                "title"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8958" to null,
                                    "title" to "\"SPARQL Tutorial\""
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8958" to null,
                                    "title" to "\"The Semantic Web\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p8958",
                            "title",
                            "book"
                        ), listOf(
                            mutableMapOf(
                                "#p8958" to null,
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
                                "#p8958",
                                "title",
                                "book"
                            ), listOf(
                                mutableMapOf(
                                    "#p8958" to null,
                                    "title" to "\"SPARQL Tutorial\"",
                                    "book" to "<http://example.org/book/book1>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "#p8960",
                                "price"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8960" to null,
                                    "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8960" to null,
                                    "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p8958",
                            "title",
                            "#p8960",
                            "price",
                            "book"
                        ), listOf(
                            mutableMapOf(
                                "#p8958" to null,
                                "title" to "\"SPARQL Tutorial\"",
                                "#p8960" to null,
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
                                "#p8968",
                                "title"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8968" to null,
                                    "title" to "\"SPARQL Tutorial\""
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8968" to null,
                                    "title" to "\"The Semantic Web\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p8968",
                            "title",
                            "book"
                        ), listOf(
                            mutableMapOf(
                                "#p8968" to null,
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
                                "#p8968",
                                "title",
                                "book"
                            ), listOf(
                                mutableMapOf(
                                    "#p8968" to null,
                                    "title" to "\"SPARQL Tutorial\"",
                                    "book" to "<http://example.org/book/book1>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "#p8970",
                                "price"
                            ), listOf(
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book1>",
                                    "#p8970" to null,
                                    "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "book" to "<http://example.org/book/book2>",
                                    "#p8970" to null,
                                    "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p8968",
                            "title",
                            "#p8970",
                            "price",
                            "book"
                        ), listOf(
                            mutableMapOf(
                                "#p8968" to null,
                                "title" to "\"SPARQL Tutorial\"",
                                "#p8970" to null,
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
                                "#p11103",
                                "#o11103"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11103" to null,
                                    "#o11103" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "#p11104",
                                "b"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11104" to null,
                                    "b" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11104" to null,
                                    "b" to "<http://example.org/c>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/b>",
                                    "#p11104" to null,
                                    "b" to "<http://example.org/c>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p11103",
                            "#o11103",
                            "#p11104",
                            "b",
                            "a"
                        ), listOf(
                            mutableMapOf(
                                "#p11103" to null,
                                "#o11103" to null,
                                "#p11104" to null,
                                "b" to "<http://example.org/b>",
                                "a" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "#p11103" to null,
                                "#o11103" to null,
                                "#p11104" to null,
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
                                "#p11347",
                                "#o11347"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11347" to null,
                                    "#o11347" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "#p11348",
                                "Var_B"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11348" to null,
                                    "Var_B" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11348" to null,
                                    "Var_B" to "<http://example.org/c>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/b>",
                                    "#p11348" to null,
                                    "Var_B" to "<http://example.org/c>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p11347",
                            "#o11347",
                            "#p11348",
                            "Var_B",
                            "a"
                        ), listOf(
                            mutableMapOf(
                                "#p11347" to null,
                                "#o11347" to null,
                                "#p11348" to null,
                                "Var_B" to "<http://example.org/b>",
                                "a" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "#p11347" to null,
                                "#o11347" to null,
                                "#p11348" to null,
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
                                "#p11485",
                                "#o11485"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11485" to null,
                                    "#o11485" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "#p11486",
                                "Var_B"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11486" to null,
                                    "Var_B" to "<http://example.org/b>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11486" to null,
                                    "Var_B" to "<http://example.org/c>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/b>",
                                    "#p11486" to null,
                                    "Var_B" to "<http://example.org/c>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p11485",
                            "#o11485",
                            "#p11486",
                            "Var_B",
                            "a"
                        ), listOf(
                            mutableMapOf(
                                "#p11485" to null,
                                "#o11485" to null,
                                "#p11486" to null,
                                "Var_B" to "<http://example.org/b>",
                                "a" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "#p11485" to null,
                                "#o11485" to null,
                                "#p11486" to null,
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
                                "#p11639",
                                "#o11639"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/a>",
                                    "#p11639" to null,
                                    "#o11639" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "#p11640",
                                "Var_B"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/b>",
                                    "#p11640" to null,
                                    "Var_B" to "<http://example.org/c>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p11639",
                            "#o11639",
                            "#p11640",
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
                                "#s12526",
                                "#p12526",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "#s12526" to null,
                                    "#p12526" to null,
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
                            "#s12526",
                            "#p12526",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#s12526" to null,
                                "#p12526" to null,
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\"",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "#s12526" to null,
                                "#p12526" to null,
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
                                "#s12623",
                                "#p12623",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "#s12623" to null,
                                    "#p12623" to null,
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
                            "#s12623",
                            "#p12623",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#s12623" to null,
                                "#p12623" to null,
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\"",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "#s12623" to null,
                                "#p12623" to null,
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
                                "#p12705",
                                "#o12705"
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
                            "#p12705",
                            "#o12705",
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
                                "#p12784",
                                "#o12784"
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
                            "#p12784",
                            "#o12784",
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
                                "#s12852",
                                "#p12852",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "#s12852" to null,
                                    "#p12852" to null,
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
                            "#s12852",
                            "#p12852",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#s12852" to null,
                                "#p12852" to null,
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\"",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "#s12852" to null,
                                "#p12852" to null,
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
                                "#p12957",
                                "#o12957"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "#p12957" to null,
                                    "#o12957" to null
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
                            "#p12957",
                            "#o12957",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p12957" to null,
                                "#o12957" to null,
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"chris@example.org\"",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p12957" to null,
                                "#o12957" to null,
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
                                "#s13368",
                                "#p13368",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "#s13368" to null,
                                    "#p13368" to null,
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
                            "#s13368",
                            "#p13368",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#s13368" to null,
                                "#p13368" to null,
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/c>",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "#s13368" to null,
                                "#p13368" to null,
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\"",
                                "s" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "#s13368" to null,
                                "#p13368" to null,
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
                                "#s13483",
                                "#p13483",
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
                            "#s13483",
                            "#p13483",
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
                                "#p13560",
                                "#o13560"
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
                            "#p13560",
                            "#o13560",
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
                                "#p13648",
                                "#o13648"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "#p13648" to null,
                                    "#o13648" to null
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
                            "#p13648",
                            "#o13648",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p13648" to null,
                                "#o13648" to null,
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/d>",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p13648" to null,
                                "#o13648" to null,
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"chris@example.org\"",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p13648" to null,
                                "#o13648" to null,
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
                                "#p13754",
                                "#o13754"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/a>",
                                    "#p13754" to null,
                                    "#o13754" to null
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
                            "#p13754",
                            "#o13754",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p13754" to null,
                                "#o13754" to null,
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/b>",
                                "s" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "#p13754" to null,
                                "#o13754" to null,
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"alan@example.org\"",
                                "s" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "#p13754" to null,
                                "#o13754" to null,
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
                                "#p13875",
                                "#o13875"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/c>",
                                    "#p13875" to null,
                                    "#o13875" to null
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
                            "#p13875",
                            "#o13875",
                            "p",
                            "o",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p13875" to null,
                                "#o13875" to null,
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/d>",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p13875" to null,
                                "#o13875" to null,
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"chris@example.org\"",
                                "s" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "#p13875" to null,
                                "#o13875" to null,
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
                                "#p15625",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "#p15625" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/d>",
                                    "#p15625" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "#p15625" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p15625" to null,
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p15625" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/y>",
                                    "#p15625" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "_:rdfs05",
                                    "#p15625" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p15626",
                                "#o15626"
                            ), listOf(
                                mutableMapOf(
                                    "c" to "<http://example.org/x/c>",
                                    "#p15626" to null,
                                    "#o15626" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p15625",
                            "#p15626",
                            "#o15626",
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p15625" to null,
                                "#p15626" to null,
                                "#o15626" to null,
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
                                "#s15884",
                                "x",
                                "#o15884"
                            ), listOf(
                                mutableMapOf(
                                    "#s15884" to null,
                                    "x" to "<http://example.org/ns#b>",
                                    "#o15884" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p15885",
                                "#o15885"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/ns#b>",
                                    "#p15885" to null,
                                    "#o15885" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s15884",
                            "#o15884",
                            "#p15885",
                            "#o15885",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#s15884" to null,
                                "#o15884" to null,
                                "#p15885" to null,
                                "#o15885" to null,
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
                                "#p16072",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p16072" to null,
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p16072" to null,
                                    "c" to "<http://example.org/x/d>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p16072" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p16072" to null,
                                    "c" to "_:x"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "#p16072" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/d>",
                                    "#p16072" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "#p16072" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:ont",
                                    "#p16072" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:x",
                                    "#p16072" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p16073",
                                "#o16073"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16072",
                            "#p16073",
                            "#o16073",
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
                                "#p16134",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p16134" to null,
                                    "y" to "<http://example.org/x/y>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p16134" to null,
                                    "y" to "_:y"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "y",
                                "#p16135",
                                "#o16135"
                            ), listOf(
                                mutableMapOf(
                                    "y" to "<http://example.org/x/y>",
                                    "#p16135" to null,
                                    "#o16135" to null
                                ),
                                mutableMapOf(
                                    "y" to "_:y",
                                    "#p16135" to null,
                                    "#o16135" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16134",
                            "#p16135",
                            "#o16135",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p16134" to null,
                                "#p16135" to null,
                                "#o16135" to null,
                                "y" to "<http://example.org/x/y>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p16134" to null,
                                "#p16135" to null,
                                "#o16135" to null,
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
                                "#p16327",
                                "#c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16327" to null,
                                    "#c" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Conference>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/ConferencePaper>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Employee>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#p16327" to null,
                                    "#c" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/GraduateAssistant>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16327" to null,
                                    "#c" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Student>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Workshop>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/hasPublication>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/name>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#p16327" to null,
                                    "#c" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/publishedAt>",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:SPARQLDAWGTestOntology",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:_16223",
                                    "#p16327" to null,
                                    "#c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16244",
                                "#p16328",
                                "#o16328"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16327",
                            "#c",
                            "#_16244",
                            "#p16328",
                            "#o16328"
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
                                "#p16327",
                                "#p16328",
                                "#o16328",
                                "#p16330",
                                "#_16249",
                                "#p16332",
                                "#o16332",
                                "#p16334",
                                "#o16334",
                                "#p16336",
                                "#c",
                                "#_16244"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16338",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16338" to null,
                                    "y" to "\"Johnnie\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p16327",
                            "#c",
                            "#_16244",
                            "#p16328",
                            "#o16328",
                            "#p16330",
                            "#_16249",
                            "#p16332",
                            "#o16332",
                            "#p16334",
                            "#o16334",
                            "#p16336",
                            "#p16338",
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
                                "#p16455",
                                "#b0"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16369",
                                "#p16456",
                                "#o16456"
                            ), listOf(
                                mutableMapOf(
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16455",
                            "#b0",
                            "#_16369",
                            "#p16456",
                            "#o16456"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null
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
                                "#p16455",
                                "#b0",
                                "#_16369",
                                "#p16456",
                                "#o16456"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16369",
                                "#p16458",
                                "#o16458"
                            ), listOf(
                                mutableMapOf(
                                    "#_16369" to "_:_16349",
                                    "#p16458" to null,
                                    "#o16458" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16455",
                            "#b0",
                            "#p16456",
                            "#o16456",
                            "#p16458",
                            "#o16458",
                            "#_16369"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16369" to "_:_16349"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16369" to "_:_16349"
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
                                "#p16455",
                                "#b0",
                                "#p16456",
                                "#o16456",
                                "#p16458",
                                "#o16458",
                                "#_16369"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16369" to "_:_16349"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16369" to "_:_16349"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16378",
                                "#p16460",
                                "#o16460"
                            ), listOf(
                                mutableMapOf(
                                    "#_16378" to "<http://example.org/Conference>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "#_16378" to "<http://example.org/ConferencePaper>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "#_16378" to "<http://example.org/Employee>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "#_16378" to "<http://example.org/GraduateAssistant>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "#_16378" to "<http://example.org/Student>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "#_16378" to "<http://example.org/Workshop>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16455",
                            "#b0",
                            "#_16369",
                            "#p16456",
                            "#o16456",
                            "#p16458",
                            "#o16458",
                            "#_16378",
                            "#p16460",
                            "#o16460"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16378" to "<http://example.org/Conference>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16378" to "<http://example.org/Conference>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16378" to "<http://example.org/ConferencePaper>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16378" to "<http://example.org/ConferencePaper>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16378" to "<http://example.org/Employee>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16378" to "<http://example.org/Employee>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16378" to "<http://example.org/GraduateAssistant>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16378" to "<http://example.org/GraduateAssistant>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16378" to "<http://example.org/Student>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16378" to "<http://example.org/Student>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16378" to "<http://example.org/Workshop>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>",
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null,
                                "#p16458" to null,
                                "#o16458" to null,
                                "#_16378" to "<http://example.org/Workshop>",
                                "#p16460" to null,
                                "#o16460" to null
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
                                "#p16455",
                                "#b0",
                                "#_16369",
                                "#p16456",
                                "#o16456",
                                "#p16458",
                                "#o16458",
                                "#_16378",
                                "#p16460",
                                "#o16460"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16378" to "<http://example.org/Conference>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16378" to "<http://example.org/Conference>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16378" to "<http://example.org/ConferencePaper>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16378" to "<http://example.org/ConferencePaper>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16378" to "<http://example.org/Employee>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16378" to "<http://example.org/Employee>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16378" to "<http://example.org/GraduateAssistant>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16378" to "<http://example.org/GraduateAssistant>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16378" to "<http://example.org/Student>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16378" to "<http://example.org/Student>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16378" to "<http://example.org/Workshop>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16455" to null,
                                    "#b0" to "<http://example.org/paper1>",
                                    "#_16369" to "_:_16349",
                                    "#p16456" to null,
                                    "#o16456" to null,
                                    "#p16458" to null,
                                    "#o16458" to null,
                                    "#_16378" to "<http://example.org/Workshop>",
                                    "#p16460" to null,
                                    "#o16460" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16378",
                                "#p16462",
                                "#o16462"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16455",
                            "#b0",
                            "#_16369",
                            "#p16456",
                            "#o16456",
                            "#p16458",
                            "#o16458",
                            "#p16460",
                            "#o16460",
                            "#p16462",
                            "#o16462",
                            "#_16378"
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
                                "#p16455",
                                "#b0",
                                "#_16369",
                                "#p16456",
                                "#o16456",
                                "#p16458",
                                "#o16458",
                                "#p16460",
                                "#o16460",
                                "#p16462",
                                "#o16462",
                                "#_16378"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16369",
                                "#p16464",
                                "#_16378"
                            ), listOf(
                                mutableMapOf(
                                    "#_16369" to "_:_16349",
                                    "#p16464" to null,
                                    "#_16378" to "<http://example.org/Conference>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16455",
                            "#b0",
                            "#p16456",
                            "#o16456",
                            "#p16458",
                            "#o16458",
                            "#p16460",
                            "#o16460",
                            "#p16462",
                            "#o16462",
                            "#p16464",
                            "#_16369",
                            "#_16378"
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
                                "#p16455",
                                "#b0",
                                "#p16456",
                                "#o16456",
                                "#p16458",
                                "#o16458",
                                "#p16460",
                                "#o16460",
                                "#p16462",
                                "#o16462",
                                "#p16464",
                                "#_16369",
                                "#_16378"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b0",
                                "#p16466",
                                "#_16369"
                            ), listOf(
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Anite>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Anite>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Conference>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/ConferencePaper>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Employee>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/George>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/George>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/GraduateAssistant>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/John>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/John>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Student>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/Workshop>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/hasPublication>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/name>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/paper1>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/person1>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "#b0" to "<http://example.org/publishedAt>",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "#b0" to "_:SPARQLDAWGTestOntology",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "#b0" to "_:_16349",
                                    "#p16466" to null,
                                    "#_16369" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16455",
                            "#p16456",
                            "#o16456",
                            "#p16458",
                            "#o16458",
                            "#_16378",
                            "#p16460",
                            "#o16460",
                            "#p16462",
                            "#o16462",
                            "#p16464",
                            "#p16466",
                            "#b0",
                            "#_16369"
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
                                "#p16541",
                                "#o16541"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16541" to null,
                                    "#o16541" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16542",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16542" to null,
                                    "c" to "<http://example.org/Student>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Anite>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Conference>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/ConferencePaper>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Employee>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#p16542" to null,
                                    "c" to "<http://example.org/Employee>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/George>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/GraduateAssistant>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16542" to null,
                                    "c" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/John>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Student>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/Workshop>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/hasPublication>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/name>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#p16542" to null,
                                    "c" to "<http://example.org/ConferencePaper>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/paper1>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/person1>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/publishedAt>",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:SPARQLDAWGTestOntology",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:_16477",
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p16541",
                            "#o16541",
                            "#p16542",
                            "c",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p16541" to null,
                                "#o16541" to null,
                                "#p16542" to null,
                                "c" to "<http://example.org/Student>",
                                "x" to "<http://example.org/Anite>"
                            ),
                            mutableMapOf(
                                "#p16541" to null,
                                "#o16541" to null,
                                "#p16542" to null,
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
                                "#p16541",
                                "#o16541",
                                "#p16542",
                                "c",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "#p16541" to null,
                                    "#o16541" to null,
                                    "#p16542" to null,
                                    "c" to "<http://example.org/Student>",
                                    "x" to "<http://example.org/Anite>"
                                ),
                                mutableMapOf(
                                    "#p16541" to null,
                                    "#o16541" to null,
                                    "#p16542" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>",
                                    "x" to "<http://example.org/Anite>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p16544",
                                "#o16544"
                            ), listOf(
                                mutableMapOf(
                                    "c" to "<http://example.org/GraduateAssistant>",
                                    "#p16544" to null,
                                    "#o16544" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16541",
                            "#o16541",
                            "#p16542",
                            "#p16544",
                            "#o16544",
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
                                "#p16607",
                                "#o16607"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/hasPublication>",
                                    "#p16607" to null,
                                    "#o16607" to null
                                ),
                                mutableMapOf(
                                    "p" to "<http://example.org/publishedAt>",
                                    "#p16607" to null,
                                    "#o16607" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#s16608",
                                "p",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "#s16608" to null,
                                    "p" to "<http://example.org/hasPublication>",
                                    "v" to "<http://example.org/paper1>"
                                ),
                                mutableMapOf(
                                    "#s16608" to null,
                                    "p" to "<http://example.org/name>",
                                    "v" to "\"Johnnie\""
                                ),
                                mutableMapOf(
                                    "#s16608" to null,
                                    "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "v" to "<http://example.org/GraduateAssistant>"
                                ),
                                mutableMapOf(
                                    "#s16608" to null,
                                    "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "v" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p16607",
                            "#o16607",
                            "#s16608",
                            "v",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "#p16607" to null,
                                "#o16607" to null,
                                "#s16608" to null,
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
                                "#p16722",
                                "#o16722"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p16722" to null,
                                    "#o16722" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "z",
                            "#p16722",
                            "#o16722",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16722" to null,
                                "#o16722" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16722" to null,
                                "#o16722" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16722" to null,
                                "#o16722" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16722" to null,
                                "#o16722" to null,
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
                                "s",
                                "p",
                                "o",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "z2" to null,
                                    "s" to "<http://example.org/p>",
                                    "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "z" to null
                                ),
                                mutableMapOf(
                                    "z2" to "\"101\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z2" to "\"102\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z2" to "\"103\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z2" to "\"104\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z2" to null,
                                    "s" to "_:1",
                                    "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "o" to "<http://www.w3.org/2002/07/owl#Ontology>",
                                    "z" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p",
                                "#p16852",
                                "#o16852"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p16852" to null,
                                    "#o16852" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "z",
                            "z2",
                            "#p16852",
                            "#o16852",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z2" to "\"101\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16852" to null,
                                "#o16852" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z2" to "\"102\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16852" to null,
                                "#o16852" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z2" to "\"103\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16852" to null,
                                "#o16852" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z2" to "\"104\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p16852" to null,
                                "#o16852" to null,
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
                                "#p17025",
                                "#o17025"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17025" to null,
                                    "#o17025" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "z",
                            "#p17025",
                            "#o17025",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17025" to null,
                                "#o17025" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17025" to null,
                                "#o17025" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17025" to null,
                                "#o17025" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17025" to null,
                                "#o17025" to null,
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
                                "s",
                                "o",
                                "z",
                                "#p17025",
                                "#o17025",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17025" to null,
                                    "#o17025" to null,
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17025" to null,
                                    "#o17025" to null,
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17025" to null,
                                    "#o17025" to null,
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17025" to null,
                                    "#o17025" to null,
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
                            "p",
                            "o",
                            "#p17025",
                            "#o17025",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17025" to null,
                                "#o17025" to null,
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17025" to null,
                                "#o17025" to null,
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17025" to null,
                                "#o17025" to null,
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
                                "p",
                                "o",
                                "#p17025",
                                "#o17025",
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17025" to null,
                                    "#o17025" to null,
                                    "s1" to "<http://example.org/s2>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17025" to null,
                                    "#o17025" to null,
                                    "s1" to "<http://example.org/s3>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17025" to null,
                                    "#o17025" to null,
                                    "s1" to "<http://example.org/s4>",
                                    "p1" to "<http://example.org/p>",
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p1",
                                "#p17029",
                                "#o17029"
                            ), listOf(
                                mutableMapOf(
                                    "p1" to "<http://example.org/p>",
                                    "#p17029" to null,
                                    "#o17029" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z",
                            "#p17025",
                            "#o17025",
                            "s1",
                            "#p17029",
                            "#o17029",
                            "p1"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17025" to null,
                                "#o17025" to null,
                                "s1" to "<http://example.org/s2>",
                                "#p17029" to null,
                                "#o17029" to null,
                                "p1" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17025" to null,
                                "#o17025" to null,
                                "s1" to "<http://example.org/s3>",
                                "#p17029" to null,
                                "#o17029" to null,
                                "p1" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17025" to null,
                                "#o17025" to null,
                                "s1" to "<http://example.org/s4>",
                                "#p17029" to null,
                                "#o17029" to null,
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
                                "#p17138",
                                "#o17138"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17138" to null,
                                    "#o17138" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "#p17138",
                            "#o17138",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17138" to null,
                                "#o17138" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17138" to null,
                                "#o17138" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17138" to null,
                                "#o17138" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17138" to null,
                                "#o17138" to null,
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
                                "#p17243",
                                "#o17243"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17243" to null,
                                    "#o17243" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "z",
                            "#p17243",
                            "#o17243",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17243" to null,
                                "#o17243" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17243" to null,
                                "#o17243" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17243" to null,
                                "#o17243" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17243" to null,
                                "#o17243" to null,
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
                                "#p17360",
                                "#o17360"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17360" to null,
                                    "#o17360" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "z",
                            "#p17360",
                            "#o17360",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17360" to null,
                                "#o17360" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17360" to null,
                                "#o17360" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17360" to null,
                                "#o17360" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17360" to null,
                                "#o17360" to null,
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
                                "#p17533",
                                "#o17533"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17533" to null,
                                    "#o17533" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "z",
                            "#p17533",
                            "#o17533",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17533" to null,
                                "#o17533" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17533" to null,
                                "#o17533" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17533" to null,
                                "#o17533" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17533" to null,
                                "#o17533" to null,
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
                                "#p17674",
                                "#o17674"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17674" to null,
                                    "#o17674" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "#p17674",
                            "#o17674",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17674" to null,
                                "#o17674" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17674" to null,
                                "#o17674" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17674" to null,
                                "#o17674" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17674" to null,
                                "#o17674" to null,
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
                                "#p17674",
                                "#o17674",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17674" to null,
                                    "#o17674" to null,
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17674" to null,
                                    "#o17674" to null,
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17674" to null,
                                    "#o17674" to null,
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17674" to null,
                                    "#o17674" to null,
                                    "p" to "<http://example.org/p>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "o" to null,
                                    "z" to null
                                ),
                                mutableMapOf(
                                    "o" to null,
                                    "z" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "#p17674",
                            "#o17674",
                            "z",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "#p17674" to null,
                                "#o17674" to null,
                                "z" to null,
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "#p17674" to null,
                                "#o17674" to null,
                                "z" to null,
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "#p17674" to null,
                                "#o17674" to null,
                                "z" to null,
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "#p17674" to null,
                                "#o17674" to null,
                                "z" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "#p17674" to null,
                                "#o17674" to null,
                                "z" to null,
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "#p17674" to null,
                                "#o17674" to null,
                                "z" to null,
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "#p17674" to null,
                                "#o17674" to null,
                                "z" to null,
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "#p17674" to null,
                                "#o17674" to null,
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
                                "#p17793",
                                "#o17793"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17793" to null,
                                    "#o17793" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "z",
                            "#p17793",
                            "#o17793",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17793" to null,
                                "#o17793" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17793" to null,
                                "#o17793" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17793" to null,
                                "#o17793" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17793" to null,
                                "#o17793" to null,
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
                                "#p17910",
                                "#o17910"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://example.org/p>",
                                    "#p17910" to null,
                                    "#o17910" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "o",
                            "z",
                            "#p17910",
                            "#o17910",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17910" to null,
                                "#o17910" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17910" to null,
                                "#o17910" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17910" to null,
                                "#o17910" to null,
                                "p" to "<http://example.org/p>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17910" to null,
                                "#o17910" to null,
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
                                "#p18105",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "#p18105" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "#p18105" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18105" to null,
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "_:ont",
                                    "#p18105" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p18106",
                                "#o18106"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p18105",
                            "#p18106",
                            "#o18106",
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
                                "#p18175",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p18175" to null,
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/a>",
                                    "#p18175" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/c>",
                                    "#p18175" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/d>",
                                    "#p18175" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/p>",
                                    "#p18175" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18175" to null,
                                    "c" to "<http://example.org/x/c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18175" to null,
                                    "c" to "<http://example.org/x/d>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18175" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18175" to null,
                                    "c" to "_:x"
                                ),
                                mutableMapOf(
                                    "x" to "_:sparql-dl",
                                    "#p18175" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                ),
                                mutableMapOf(
                                    "x" to "_:x",
                                    "#p18175" to null,
                                    "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p18176",
                                "#o18176"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p18175",
                            "#p18176",
                            "#o18176",
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
                                "#p18175",
                                "#p18176",
                                "#o18176",
                                "c"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p18178",
                                "#y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18178" to null,
                                    "#y" to "<http://example.org/x/a>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18175",
                            "c",
                            "#p18176",
                            "#o18176",
                            "#p18178",
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
                                "#p18247",
                                "#o18247"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "#p18247" to null,
                                    "#o18247" to null
                                ),
                                mutableMapOf(
                                    "X" to "<http://example.org/test#b>",
                                    "#p18247" to null,
                                    "#o18247" to null
                                ),
                                mutableMapOf(
                                    "X" to "<http://example.org/test#c>",
                                    "#p18247" to null,
                                    "#o18247" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18248",
                                "Y1"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "#p18248" to null,
                                    "Y1" to "\"A\""
                                ),
                                mutableMapOf(
                                    "X" to "<http://example.org/test#b>",
                                    "#p18248" to null,
                                    "Y1" to "\"B\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18247",
                            "#o18247",
                            "#p18248",
                            "Y1",
                            "X"
                        ), listOf(
                            mutableMapOf(
                                "#p18247" to null,
                                "#o18247" to null,
                                "#p18248" to null,
                                "Y1" to "\"A\"",
                                "X" to "<http://example.org/test#a>"
                            ),
                            mutableMapOf(
                                "#p18247" to null,
                                "#o18247" to null,
                                "#p18248" to null,
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
                                "#p18247",
                                "#o18247",
                                "#p18248",
                                "Y1",
                                "X"
                            ), listOf(
                                mutableMapOf(
                                    "#p18247" to null,
                                    "#o18247" to null,
                                    "#p18248" to null,
                                    "Y1" to "\"A\"",
                                    "X" to "<http://example.org/test#a>"
                                ),
                                mutableMapOf(
                                    "#p18247" to null,
                                    "#o18247" to null,
                                    "#p18248" to null,
                                    "Y1" to "\"B\"",
                                    "X" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18250",
                                "Y2"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "#p18250" to null,
                                    "Y2" to "\"Anick\""
                                ),
                                mutableMapOf(
                                    "X" to "<http://example.org/test#b>",
                                    "#p18250" to null,
                                    "Y2" to "\"Bnick\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18247",
                            "#o18247",
                            "#p18248",
                            "Y1",
                            "#p18250",
                            "Y2",
                            "X"
                        ), listOf(
                            mutableMapOf(
                                "#p18247" to null,
                                "#o18247" to null,
                                "#p18248" to null,
                                "Y1" to "\"A\"",
                                "#p18250" to null,
                                "Y2" to "\"Anick\"",
                                "X" to "<http://example.org/test#a>"
                            ),
                            mutableMapOf(
                                "#p18247" to null,
                                "#o18247" to null,
                                "#p18248" to null,
                                "Y1" to "\"B\"",
                                "#p18250" to null,
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
                                "#s18368",
                                "#p18368",
                                "#aa"
                            ), listOf(
                                mutableMapOf(
                                    "#s18368" to null,
                                    "#p18368" to null,
                                    "#aa" to "<http://example.org/test#aa>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#p18369",
                                "#dd"
                            ), listOf(
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#aa>",
                                    "#p18369" to null,
                                    "#dd" to "<http://example.org/test#ee>"
                                ),
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#cc>",
                                    "#p18369" to null,
                                    "#dd" to "<http://example.org/test#dd>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s18368",
                            "#p18368",
                            "#p18369",
                            "#dd",
                            "#aa"
                        ), listOf(
                            mutableMapOf(
                                "#s18368" to null,
                                "#p18368" to null,
                                "#p18369" to null,
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
                                "#s18368",
                                "#p18368",
                                "#p18369",
                                "#dd",
                                "#aa"
                            ), listOf(
                                mutableMapOf(
                                    "#s18368" to null,
                                    "#p18368" to null,
                                    "#p18369" to null,
                                    "#dd" to "<http://example.org/test#ee>",
                                    "#aa" to "<http://example.org/test#aa>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#dd",
                                "#p18371",
                                "#bb"
                            ), listOf(
                                mutableMapOf(
                                    "#dd" to "<http://example.org/test#dd>",
                                    "#p18371" to null,
                                    "#bb" to "<http://example.org/test#bb>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s18368",
                            "#p18368",
                            "#aa",
                            "#p18369",
                            "#p18371",
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
                                "#s18453",
                                "#p18453",
                                "#aa"
                            ), listOf(
                                mutableMapOf(
                                    "#s18453" to null,
                                    "#p18453" to null,
                                    "#aa" to "<http://example.org/test#aa>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "#p18454",
                                "Y"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#dd>",
                                    "#p18454" to null,
                                    "Y" to "<http://example.org/test#bb>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s18453",
                            "#p18453",
                            "#aa",
                            "X",
                            "#p18454",
                            "Y"
                        ), listOf(
                            mutableMapOf(
                                "#s18453" to null,
                                "#p18453" to null,
                                "#aa" to "<http://example.org/test#aa>",
                                "X" to "<http://example.org/test#dd>",
                                "#p18454" to null,
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
                                "#s18453",
                                "#p18453",
                                "#aa",
                                "X",
                                "#p18454",
                                "Y"
                            ), listOf(
                                mutableMapOf(
                                    "#s18453" to null,
                                    "#p18453" to null,
                                    "#aa" to "<http://example.org/test#aa>",
                                    "X" to "<http://example.org/test#dd>",
                                    "#p18454" to null,
                                    "Y" to "<http://example.org/test#bb>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "Y",
                                "#p18456",
                                "#aa"
                            ), listOf(
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#bb>",
                                    "#p18456" to null,
                                    "#aa" to "<http://example.org/test#aa>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s18453",
                            "#p18453",
                            "X",
                            "#p18454",
                            "#p18456",
                            "#aa",
                            "Y"
                        ), listOf(
                            mutableMapOf(
                                "#s18453" to null,
                                "#p18453" to null,
                                "X" to "<http://example.org/test#dd>",
                                "#p18454" to null,
                                "#p18456" to null,
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
                                "#s18453",
                                "#p18453",
                                "X",
                                "#p18454",
                                "#p18456",
                                "#aa",
                                "Y"
                            ), listOf(
                                mutableMapOf(
                                    "#s18453" to null,
                                    "#p18453" to null,
                                    "X" to "<http://example.org/test#dd>",
                                    "#p18454" to null,
                                    "#p18456" to null,
                                    "#aa" to "<http://example.org/test#aa>",
                                    "Y" to "<http://example.org/test#bb>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#p18458",
                                "Z"
                            ), listOf(
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#aa>",
                                    "#p18458" to null,
                                    "Z" to "<http://example.org/test#ee>"
                                ),
                                mutableMapOf(
                                    "#aa" to "<http://example.org/test#cc>",
                                    "#p18458" to null,
                                    "Z" to "<http://example.org/test#dd>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s18453",
                            "#p18453",
                            "X",
                            "#p18454",
                            "Y",
                            "#p18456",
                            "#p18458",
                            "Z",
                            "#aa"
                        ), listOf(
                            mutableMapOf(
                                "#s18453" to null,
                                "#p18453" to null,
                                "X" to "<http://example.org/test#dd>",
                                "#p18454" to null,
                                "Y" to "<http://example.org/test#bb>",
                                "#p18456" to null,
                                "#p18458" to null,
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
                                "#p18513",
                                "#a"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "#p18513" to null,
                                    "#a" to "<http://example.org/test#aa>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#a",
                                "#p18514",
                                "Y"
                            ), listOf(
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#aa>",
                                    "#p18514" to null,
                                    "Y" to "<http://example.org/test#ee>"
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#cc>",
                                    "#p18514" to null,
                                    "Y" to "<http://example.org/test#dd>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18513",
                            "#p18514",
                            "Y",
                            "#a"
                        ), listOf(
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18513" to null,
                                "#p18514" to null,
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
                                "#p18570",
                                "#a"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "#p18570" to null,
                                    "#a" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#a",
                                "#p18571",
                                "Y"
                            ), listOf(
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#b>",
                                    "#p18571" to null,
                                    "Y" to "<http://example.org/test#c>"
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#b>",
                                    "#p18571" to null,
                                    "Y" to "<http://example.org/test#h>"
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#b>",
                                    "#p18571" to null,
                                    "Y" to "<http://example.org/test#i>"
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#x>",
                                    "#p18571" to null,
                                    "Y" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18570",
                            "#p18571",
                            "Y",
                            "#a"
                        ), listOf(
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18570" to null,
                                "#p18571" to null,
                                "Y" to "<http://example.org/test#c>",
                                "#a" to "<http://example.org/test#b>"
                            ),
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18570" to null,
                                "#p18571" to null,
                                "Y" to "<http://example.org/test#h>",
                                "#a" to "<http://example.org/test#b>"
                            ),
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18570" to null,
                                "#p18571" to null,
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
                                "#p18651",
                                "a"
                            ), listOf(
                                mutableMapOf(
                                    "X" to "<http://example.org/test#a>",
                                    "#p18651" to null,
                                    "a" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "#p18652",
                                "b"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18651",
                            "#p18652",
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
                                "#p18651",
                                "a",
                                "#p18652",
                                "#p18654",
                                "Y",
                                "b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "Y",
                                "#p18656",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#b>",
                                    "#p18656" to null,
                                    "c" to "<http://example.org/test#c>"
                                ),
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#b>",
                                    "#p18656" to null,
                                    "c" to "<http://example.org/test#h>"
                                ),
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#b>",
                                    "#p18656" to null,
                                    "c" to "<http://example.org/test#i>"
                                ),
                                mutableMapOf(
                                    "Y" to "<http://example.org/test#x>",
                                    "#p18656" to null,
                                    "c" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18651",
                            "a",
                            "#p18652",
                            "b",
                            "#p18654",
                            "#p18656",
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
                                "#p18817",
                                "b"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/test#a>",
                                    "#p18817" to null,
                                    "b" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "#p18818",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "b" to "<http://example.org/test#b>",
                                    "#p18818" to null,
                                    "x" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p18817",
                            "#p18818",
                            "x",
                            "b"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/test#a>",
                                "#p18817" to null,
                                "#p18818" to null,
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
                                "#p18817",
                                "#p18818",
                                "x",
                                "b"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/test#a>",
                                    "#p18817" to null,
                                    "#p18818" to null,
                                    "x" to "<http://example.org/test#x>",
                                    "b" to "<http://example.org/test#b>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p18820",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p18820" to null,
                                    "x" to "<http://example.org/test#c>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p18820" to null,
                                    "x" to "<http://example.org/test#d>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#x>",
                                    "#p18820" to null,
                                    "x" to "<http://example.org/test#x>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p18817",
                            "b",
                            "#p18818",
                            "#p18820",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/test#a>",
                                "#p18817" to null,
                                "b" to "<http://example.org/test#b>",
                                "#p18818" to null,
                                "#p18820" to null,
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
                                "#_18935",
                                "#p18988",
                                "#o18988"
                            ), listOf(
                                mutableMapOf(
                                    "#_18935" to "_:_18920",
                                    "#p18988" to null,
                                    "#o18988" to null
                                ),
                                mutableMapOf(
                                    "#_18935" to "_:_18921",
                                    "#p18988" to null,
                                    "#o18988" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18935",
                                "#p18989",
                                "#o18989"
                            ), listOf(
                                mutableMapOf(
                                    "#_18935" to "_:_18920",
                                    "#p18989" to null,
                                    "#o18989" to null
                                ),
                                mutableMapOf(
                                    "#_18935" to "_:_18921",
                                    "#p18989" to null,
                                    "#o18989" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18988",
                            "#o18988",
                            "#p18989",
                            "#o18989",
                            "#_18935"
                        ), listOf(
                            mutableMapOf(
                                "#p18988" to null,
                                "#o18988" to null,
                                "#p18989" to null,
                                "#o18989" to null,
                                "#_18935" to "_:_18920"
                            ),
                            mutableMapOf(
                                "#p18988" to null,
                                "#o18988" to null,
                                "#p18989" to null,
                                "#o18989" to null,
                                "#_18935" to "_:_18921"
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
                                "#p18988",
                                "#o18988",
                                "#p18989",
                                "#o18989",
                                "#_18935"
                            ), listOf(
                                mutableMapOf(
                                    "#p18988" to null,
                                    "#o18988" to null,
                                    "#p18989" to null,
                                    "#o18989" to null,
                                    "#_18935" to "_:_18920"
                                ),
                                mutableMapOf(
                                    "#p18988" to null,
                                    "#o18988" to null,
                                    "#p18989" to null,
                                    "#o18989" to null,
                                    "#_18935" to "_:_18921"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_18935",
                                "#p18991",
                                "#o18991"
                            ), listOf(
                                mutableMapOf(
                                    "#_18935" to "_:_18920",
                                    "#p18991" to null,
                                    "#o18991" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18988",
                            "#o18988",
                            "#p18989",
                            "#o18989",
                            "#p18991",
                            "#o18991",
                            "#_18935"
                        ), listOf(
                            mutableMapOf(
                                "#p18988" to null,
                                "#o18988" to null,
                                "#p18989" to null,
                                "#o18989" to null,
                                "#p18991" to null,
                                "#o18991" to null,
                                "#_18935" to "_:_18920"
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
                                "#p18988",
                                "#o18988",
                                "#p18989",
                                "#o18989",
                                "#p18991",
                                "#o18991",
                                "#_18935"
                            ), listOf(
                                mutableMapOf(
                                    "#p18988" to null,
                                    "#o18988" to null,
                                    "#p18989" to null,
                                    "#o18989" to null,
                                    "#p18991" to null,
                                    "#o18991" to null,
                                    "#_18935" to "_:_18920"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p18993",
                                "#_18935"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p18993" to null,
                                    "#_18935" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p18993" to null,
                                    "#_18935" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p18993" to null,
                                    "#_18935" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p18993" to null,
                                    "#_18935" to "_:_18921"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18914",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18917",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18920",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18921",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_18922",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p18993" to null,
                                    "#_18935" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p18988",
                            "#o18988",
                            "#p18989",
                            "#o18989",
                            "#p18991",
                            "#o18991",
                            "parent",
                            "#p18993",
                            "#_18935"
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
                                "#_19025",
                                "#p19083",
                                "#o19083"
                            ), listOf(
                                mutableMapOf(
                                    "#_19025" to "_:_19007",
                                    "#p19083" to null,
                                    "#o19083" to null
                                ),
                                mutableMapOf(
                                    "#_19025" to "_:_19008",
                                    "#p19083" to null,
                                    "#o19083" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19025",
                                "#p19084",
                                "#o19084"
                            ), listOf(
                                mutableMapOf(
                                    "#_19025" to "_:_19007",
                                    "#p19084" to null,
                                    "#o19084" to null
                                ),
                                mutableMapOf(
                                    "#_19025" to "_:_19008",
                                    "#p19084" to null,
                                    "#o19084" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19083",
                            "#o19083",
                            "#p19084",
                            "#o19084",
                            "#_19025"
                        ), listOf(
                            mutableMapOf(
                                "#p19083" to null,
                                "#o19083" to null,
                                "#p19084" to null,
                                "#o19084" to null,
                                "#_19025" to "_:_19007"
                            ),
                            mutableMapOf(
                                "#p19083" to null,
                                "#o19083" to null,
                                "#p19084" to null,
                                "#o19084" to null,
                                "#_19025" to "_:_19008"
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
                                "#p19083",
                                "#o19083",
                                "#p19084",
                                "#o19084",
                                "#_19025"
                            ), listOf(
                                mutableMapOf(
                                    "#p19083" to null,
                                    "#o19083" to null,
                                    "#p19084" to null,
                                    "#o19084" to null,
                                    "#_19025" to "_:_19007"
                                ),
                                mutableMapOf(
                                    "#p19083" to null,
                                    "#o19083" to null,
                                    "#p19084" to null,
                                    "#o19084" to null,
                                    "#_19025" to "_:_19008"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19025",
                                "#p19086",
                                "#o19086"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19083",
                            "#o19083",
                            "#p19084",
                            "#o19084",
                            "#p19086",
                            "#o19086",
                            "#_19025"
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
                                "#p19083",
                                "#o19083",
                                "#p19084",
                                "#o19084",
                                "#p19086",
                                "#o19086",
                                "#_19025"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19088",
                                "#_19025"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19088" to null,
                                    "#_19025" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19088" to null,
                                    "#_19025" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19088" to null,
                                    "#_19025" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19088" to null,
                                    "#_19025" to "_:_19008"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19001",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19004",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19007",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19008",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19009",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19088" to null,
                                    "#_19025" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19083",
                            "#o19083",
                            "#p19084",
                            "#o19084",
                            "#p19086",
                            "#o19086",
                            "parent",
                            "#p19088",
                            "#_19025"
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
                                "#_19117",
                                "#p19170",
                                "#o19170"
                            ), listOf(
                                mutableMapOf(
                                    "#_19117" to "_:_19102",
                                    "#p19170" to null,
                                    "#o19170" to null
                                ),
                                mutableMapOf(
                                    "#_19117" to "_:_19103",
                                    "#p19170" to null,
                                    "#o19170" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19117",
                                "#p19171",
                                "#o19171"
                            ), listOf(
                                mutableMapOf(
                                    "#_19117" to "_:_19102",
                                    "#p19171" to null,
                                    "#o19171" to null
                                ),
                                mutableMapOf(
                                    "#_19117" to "_:_19103",
                                    "#p19171" to null,
                                    "#o19171" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19170",
                            "#o19170",
                            "#p19171",
                            "#o19171",
                            "#_19117"
                        ), listOf(
                            mutableMapOf(
                                "#p19170" to null,
                                "#o19170" to null,
                                "#p19171" to null,
                                "#o19171" to null,
                                "#_19117" to "_:_19102"
                            ),
                            mutableMapOf(
                                "#p19170" to null,
                                "#o19170" to null,
                                "#p19171" to null,
                                "#o19171" to null,
                                "#_19117" to "_:_19103"
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
                                "#p19170",
                                "#o19170",
                                "#p19171",
                                "#o19171",
                                "#_19117"
                            ), listOf(
                                mutableMapOf(
                                    "#p19170" to null,
                                    "#o19170" to null,
                                    "#p19171" to null,
                                    "#o19171" to null,
                                    "#_19117" to "_:_19102"
                                ),
                                mutableMapOf(
                                    "#p19170" to null,
                                    "#o19170" to null,
                                    "#p19171" to null,
                                    "#o19171" to null,
                                    "#_19117" to "_:_19103"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19117",
                                "#p19173",
                                "#o19173"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19170",
                            "#o19170",
                            "#p19171",
                            "#o19171",
                            "#p19173",
                            "#o19173",
                            "#_19117"
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
                                "#p19170",
                                "#o19170",
                                "#p19171",
                                "#o19171",
                                "#p19173",
                                "#o19173",
                                "#_19117"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19175",
                                "#_19117"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19175" to null,
                                    "#_19117" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19175" to null,
                                    "#_19117" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19175" to null,
                                    "#_19117" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19175" to null,
                                    "#_19117" to "_:_19103"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19096",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19099",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19102",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19103",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19104",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19175" to null,
                                    "#_19117" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19170",
                            "#o19170",
                            "#p19171",
                            "#o19171",
                            "#p19173",
                            "#o19173",
                            "parent",
                            "#p19175",
                            "#_19117"
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
                                "#_19205",
                                "#p19271",
                                "#o19271"
                            ), listOf(
                                mutableMapOf(
                                    "#_19205" to "_:_19189",
                                    "#p19271" to null,
                                    "#o19271" to null
                                ),
                                mutableMapOf(
                                    "#_19205" to "_:_19190",
                                    "#p19271" to null,
                                    "#o19271" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19205",
                                "#p19272",
                                "#o19272"
                            ), listOf(
                                mutableMapOf(
                                    "#_19205" to "_:_19189",
                                    "#p19272" to null,
                                    "#o19272" to null
                                ),
                                mutableMapOf(
                                    "#_19205" to "_:_19190",
                                    "#p19272" to null,
                                    "#o19272" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19271",
                            "#o19271",
                            "#p19272",
                            "#o19272",
                            "#_19205"
                        ), listOf(
                            mutableMapOf(
                                "#p19271" to null,
                                "#o19271" to null,
                                "#p19272" to null,
                                "#o19272" to null,
                                "#_19205" to "_:_19189"
                            ),
                            mutableMapOf(
                                "#p19271" to null,
                                "#o19271" to null,
                                "#p19272" to null,
                                "#o19272" to null,
                                "#_19205" to "_:_19190"
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
                                "#p19271",
                                "#o19271",
                                "#p19272",
                                "#o19272",
                                "#_19205"
                            ), listOf(
                                mutableMapOf(
                                    "#p19271" to null,
                                    "#o19271" to null,
                                    "#p19272" to null,
                                    "#o19272" to null,
                                    "#_19205" to "_:_19189"
                                ),
                                mutableMapOf(
                                    "#p19271" to null,
                                    "#o19271" to null,
                                    "#p19272" to null,
                                    "#o19272" to null,
                                    "#_19205" to "_:_19190"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19205",
                                "#p19274",
                                "#o19274"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19271",
                            "#o19271",
                            "#p19272",
                            "#o19272",
                            "#p19274",
                            "#o19274",
                            "#_19205"
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
                                "#p19271",
                                "#o19271",
                                "#p19272",
                                "#o19272",
                                "#p19274",
                                "#o19274",
                                "#p19276",
                                "#o19276",
                                "#_19205"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19278",
                                "#_19205"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19278" to null,
                                    "#_19205" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19278" to null,
                                    "#_19205" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19278" to null,
                                    "#_19205" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19278" to null,
                                    "#_19205" to "_:_19190"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19183",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19186",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19189",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19190",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19191",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19278" to null,
                                    "#_19205" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19271",
                            "#o19271",
                            "#p19272",
                            "#o19272",
                            "#p19274",
                            "#o19274",
                            "#p19276",
                            "#o19276",
                            "parent",
                            "#p19278",
                            "#_19205"
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
                                "#_19309",
                                "#p19375",
                                "#o19375"
                            ), listOf(
                                mutableMapOf(
                                    "#_19309" to "_:_19293",
                                    "#p19375" to null,
                                    "#o19375" to null
                                ),
                                mutableMapOf(
                                    "#_19309" to "_:_19294",
                                    "#p19375" to null,
                                    "#o19375" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19309",
                                "#p19376",
                                "#o19376"
                            ), listOf(
                                mutableMapOf(
                                    "#_19309" to "_:_19293",
                                    "#p19376" to null,
                                    "#o19376" to null
                                ),
                                mutableMapOf(
                                    "#_19309" to "_:_19294",
                                    "#p19376" to null,
                                    "#o19376" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19375",
                            "#o19375",
                            "#p19376",
                            "#o19376",
                            "#_19309"
                        ), listOf(
                            mutableMapOf(
                                "#p19375" to null,
                                "#o19375" to null,
                                "#p19376" to null,
                                "#o19376" to null,
                                "#_19309" to "_:_19293"
                            ),
                            mutableMapOf(
                                "#p19375" to null,
                                "#o19375" to null,
                                "#p19376" to null,
                                "#o19376" to null,
                                "#_19309" to "_:_19294"
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
                                "#p19375",
                                "#o19375",
                                "#p19376",
                                "#o19376",
                                "#_19309"
                            ), listOf(
                                mutableMapOf(
                                    "#p19375" to null,
                                    "#o19375" to null,
                                    "#p19376" to null,
                                    "#o19376" to null,
                                    "#_19309" to "_:_19293"
                                ),
                                mutableMapOf(
                                    "#p19375" to null,
                                    "#o19375" to null,
                                    "#p19376" to null,
                                    "#o19376" to null,
                                    "#_19309" to "_:_19294"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19309",
                                "#p19378",
                                "#o19378"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19375",
                            "#o19375",
                            "#p19376",
                            "#o19376",
                            "#p19378",
                            "#o19378",
                            "#_19309"
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
                                "#p19375",
                                "#o19375",
                                "#p19376",
                                "#o19376",
                                "#p19378",
                                "#o19378",
                                "#p19380",
                                "#o19380",
                                "#_19309"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19382",
                                "#_19309"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19382" to null,
                                    "#_19309" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19382" to null,
                                    "#_19309" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19382" to null,
                                    "#_19309" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19382" to null,
                                    "#_19309" to "_:_19294"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19287",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19290",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19293",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19294",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19295",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19382" to null,
                                    "#_19309" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19375",
                            "#o19375",
                            "#p19376",
                            "#o19376",
                            "#p19378",
                            "#o19378",
                            "#p19380",
                            "#o19380",
                            "parent",
                            "#p19382",
                            "#_19309"
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
                                "#_19413",
                                "#p19479",
                                "#o19479"
                            ), listOf(
                                mutableMapOf(
                                    "#_19413" to "_:_19397",
                                    "#p19479" to null,
                                    "#o19479" to null
                                ),
                                mutableMapOf(
                                    "#_19413" to "_:_19398",
                                    "#p19479" to null,
                                    "#o19479" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19413",
                                "#p19480",
                                "#o19480"
                            ), listOf(
                                mutableMapOf(
                                    "#_19413" to "_:_19397",
                                    "#p19480" to null,
                                    "#o19480" to null
                                ),
                                mutableMapOf(
                                    "#_19413" to "_:_19398",
                                    "#p19480" to null,
                                    "#o19480" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19479",
                            "#o19479",
                            "#p19480",
                            "#o19480",
                            "#_19413"
                        ), listOf(
                            mutableMapOf(
                                "#p19479" to null,
                                "#o19479" to null,
                                "#p19480" to null,
                                "#o19480" to null,
                                "#_19413" to "_:_19397"
                            ),
                            mutableMapOf(
                                "#p19479" to null,
                                "#o19479" to null,
                                "#p19480" to null,
                                "#o19480" to null,
                                "#_19413" to "_:_19398"
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
                                "#p19479",
                                "#o19479",
                                "#p19480",
                                "#o19480",
                                "#_19413"
                            ), listOf(
                                mutableMapOf(
                                    "#p19479" to null,
                                    "#o19479" to null,
                                    "#p19480" to null,
                                    "#o19480" to null,
                                    "#_19413" to "_:_19397"
                                ),
                                mutableMapOf(
                                    "#p19479" to null,
                                    "#o19479" to null,
                                    "#p19480" to null,
                                    "#o19480" to null,
                                    "#_19413" to "_:_19398"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19413",
                                "#p19482",
                                "#o19482"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19479",
                            "#o19479",
                            "#p19480",
                            "#o19480",
                            "#p19482",
                            "#o19482",
                            "#_19413"
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
                                "#p19479",
                                "#o19479",
                                "#p19480",
                                "#o19480",
                                "#p19482",
                                "#o19482",
                                "#p19484",
                                "#o19484",
                                "#_19413"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p19486",
                                "#_19413"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19486" to null,
                                    "#_19413" to "<http://example.org/test#Female>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19486" to null,
                                    "#_19413" to "<http://example.org/test#Parent>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Alice>",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19486" to null,
                                    "#_19413" to "<http://example.org/test#Male>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Charlie>",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p19486" to null,
                                    "#_19413" to "_:_19398"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Father>",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Female>",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Male>",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Mother>",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Parent>",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#hasChild>",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19391",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19394",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19397",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19398",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#Restriction>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:_19399",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "parent" to "_:ont",
                                    "#p19486" to null,
                                    "#_19413" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19479",
                            "#o19479",
                            "#p19480",
                            "#o19480",
                            "#p19482",
                            "#o19482",
                            "#p19484",
                            "#o19484",
                            "parent",
                            "#p19486",
                            "#_19413"
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
                                "#_19518",
                                "#p19573",
                                "#o19573"
                            ), listOf(
                                mutableMapOf(
                                    "#_19518" to "_:_19501",
                                    "#p19573" to null,
                                    "#o19573" to null
                                ),
                                mutableMapOf(
                                    "#_19518" to "_:_19502",
                                    "#p19573" to null,
                                    "#o19573" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19518",
                                "#p19574",
                                "#o19574"
                            ), listOf(
                                mutableMapOf(
                                    "#_19518" to "_:_19501",
                                    "#p19574" to null,
                                    "#o19574" to null
                                ),
                                mutableMapOf(
                                    "#_19518" to "_:_19502",
                                    "#p19574" to null,
                                    "#o19574" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19573",
                            "#o19573",
                            "#p19574",
                            "#o19574",
                            "#_19518"
                        ), listOf(
                            mutableMapOf(
                                "#p19573" to null,
                                "#o19573" to null,
                                "#p19574" to null,
                                "#o19574" to null,
                                "#_19518" to "_:_19501"
                            ),
                            mutableMapOf(
                                "#p19573" to null,
                                "#o19573" to null,
                                "#p19574" to null,
                                "#o19574" to null,
                                "#_19518" to "_:_19502"
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
                                "#p19573",
                                "#o19573",
                                "#p19574",
                                "#o19574",
                                "#_19518"
                            ), listOf(
                                mutableMapOf(
                                    "#p19573" to null,
                                    "#o19573" to null,
                                    "#p19574" to null,
                                    "#o19574" to null,
                                    "#_19518" to "_:_19501"
                                ),
                                mutableMapOf(
                                    "#p19573" to null,
                                    "#o19573" to null,
                                    "#p19574" to null,
                                    "#o19574" to null,
                                    "#_19518" to "_:_19502"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19518",
                                "#p19576",
                                "#o19576"
                            ), listOf(
                                mutableMapOf(
                                    "#_19518" to "_:_19501",
                                    "#p19576" to null,
                                    "#o19576" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19573",
                            "#o19573",
                            "#p19574",
                            "#o19574",
                            "#p19576",
                            "#o19576",
                            "#_19518"
                        ), listOf(
                            mutableMapOf(
                                "#p19573" to null,
                                "#o19573" to null,
                                "#p19574" to null,
                                "#o19574" to null,
                                "#p19576" to null,
                                "#o19576" to null,
                                "#_19518" to "_:_19501"
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
                                "#p19573",
                                "#o19573",
                                "#p19574",
                                "#o19574",
                                "#p19576",
                                "#o19576",
                                "#_19518"
                            ), listOf(
                                mutableMapOf(
                                    "#p19573" to null,
                                    "#o19573" to null,
                                    "#p19574" to null,
                                    "#o19574" to null,
                                    "#p19576" to null,
                                    "#o19576" to null,
                                    "#_19518" to "_:_19501"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#p19578",
                                "#_19518"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p19573",
                            "#o19573",
                            "#p19574",
                            "#o19574",
                            "#p19576",
                            "#o19576",
                            "C",
                            "#p19578",
                            "#_19518"
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
                                "#p19675",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b",
                                "#p19676",
                                "#o19676"
                            ), listOf(
                                mutableMapOf(
                                    "#b" to "_:_19592",
                                    "#p19676" to null,
                                    "#o19676" to null
                                ),
                                mutableMapOf(
                                    "#b" to "_:_19593",
                                    "#p19676" to null,
                                    "#o19676" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#p19675",
                            "#p19676",
                            "#o19676",
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
                                "#p19675",
                                "#p19676",
                                "#o19676",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b",
                                "#p19678",
                                "#o19678"
                            ), listOf(
                                mutableMapOf(
                                    "#b" to "_:_19592",
                                    "#p19678" to null,
                                    "#o19678" to null
                                ),
                                mutableMapOf(
                                    "#b" to "_:_19593",
                                    "#p19678" to null,
                                    "#o19678" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#p19675",
                            "#p19676",
                            "#o19676",
                            "#p19678",
                            "#o19678",
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
                                "#p19675",
                                "#p19676",
                                "#o19676",
                                "#p19678",
                                "#o19678",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b",
                                "#p19680",
                                "#o19680"
                            ), listOf(
                                mutableMapOf(
                                    "#b" to "_:_19592",
                                    "#p19680" to null,
                                    "#o19680" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#p19675",
                            "#p19676",
                            "#o19676",
                            "#p19678",
                            "#o19678",
                            "#p19680",
                            "#o19680",
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
                                "#p19779",
                                "#o19779",
                                "#p19780",
                                "#_19714",
                                "#p19782",
                                "#o19782",
                                "#p19784",
                                "#o19784",
                                "#_19706",
                                "#p19786",
                                "#_19709"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p19788",
                                "#_19706"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p19788" to null,
                                    "#_19706" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p19788" to null,
                                    "#_19706" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p19788" to null,
                                    "#_19706" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19788" to null,
                                    "#_19706" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19788" to null,
                                    "#_19706" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p19788" to null,
                                    "#_19706" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p19788" to null,
                                    "#_19706" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19788" to null,
                                    "#_19706" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19788" to null,
                                    "#_19706" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19788" to null,
                                    "#_19706" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19788" to null,
                                    "#_19706" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19788" to null,
                                    "#_19706" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p19788" to null,
                                    "#_19706" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19709",
                            "#p19779",
                            "#o19779",
                            "#p19780",
                            "#_19714",
                            "#p19782",
                            "#o19782",
                            "#p19784",
                            "#o19784",
                            "#p19786",
                            "x",
                            "#p19788",
                            "#_19706"
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
                                "#p19914",
                                "#o19914",
                                "#_19818",
                                "#p19915",
                                "#o19915",
                                "#p19917",
                                "#o19917",
                                "#p19919",
                                "#o19919",
                                "#p19921",
                                "#_19828",
                                "#p19923",
                                "#p19925",
                                "#o19925",
                                "#_19811",
                                "#p19927",
                                "#_19814"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p19929",
                                "#_19811"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p19929" to null,
                                    "#_19811" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p19929" to null,
                                    "#_19811" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p19929" to null,
                                    "#_19811" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19929" to null,
                                    "#_19811" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p19929" to null,
                                    "#_19811" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p19929" to null,
                                    "#_19811" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p19929" to null,
                                    "#_19811" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19929" to null,
                                    "#_19811" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19929" to null,
                                    "#_19811" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p19929" to null,
                                    "#_19811" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19929" to null,
                                    "#_19811" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p19929" to null,
                                    "#_19811" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p19929" to null,
                                    "#_19811" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19814",
                            "#p19914",
                            "#o19914",
                            "#_19818",
                            "#p19915",
                            "#o19915",
                            "#p19917",
                            "#o19917",
                            "#p19919",
                            "#o19919",
                            "#p19921",
                            "#_19828",
                            "#p19923",
                            "#p19925",
                            "#o19925",
                            "#p19927",
                            "x",
                            "#p19929",
                            "#_19811"
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
                                "#p20058",
                                "#o20058",
                                "#p20059",
                                "#o20059",
                                "#_19966",
                                "#p20061",
                                "#o20061",
                                "#p20063",
                                "#_19971",
                                "#p20065",
                                "#o20065",
                                "#p20067",
                                "#o20067",
                                "#p20069",
                                "#p20071",
                                "#_19955",
                                "#_19963"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20073",
                                "#_19955"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20073" to null,
                                    "#_19955" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20073" to null,
                                    "#_19955" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20073" to null,
                                    "#_19955" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20073" to null,
                                    "#_19955" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20073" to null,
                                    "#_19955" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20073" to null,
                                    "#_19955" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20073" to null,
                                    "#_19955" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20073" to null,
                                    "#_19955" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20073" to null,
                                    "#_19955" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20073" to null,
                                    "#_19955" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20073" to null,
                                    "#_19955" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20073" to null,
                                    "#_19955" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20073" to null,
                                    "#_19955" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20058",
                            "#o20058",
                            "#p20059",
                            "#o20059",
                            "#_19966",
                            "#p20061",
                            "#o20061",
                            "#p20063",
                            "#_19971",
                            "#p20065",
                            "#o20065",
                            "#p20067",
                            "#o20067",
                            "#_19963",
                            "#p20069",
                            "#p20071",
                            "x",
                            "#p20073",
                            "#_19955"
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
                                "#_20099",
                                "#p20181",
                                "#o20181"
                            ), listOf(
                                mutableMapOf(
                                    "#_20099" to "<http://example.org/test#A>",
                                    "#p20181" to null,
                                    "#o20181" to null
                                ),
                                mutableMapOf(
                                    "#_20099" to "<http://example.org/test#B>",
                                    "#p20181" to null,
                                    "#o20181" to null
                                ),
                                mutableMapOf(
                                    "#_20099" to "<http://example.org/test#C>",
                                    "#p20181" to null,
                                    "#o20181" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20104",
                                "#p20182",
                                "#o20182"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20099",
                            "#p20181",
                            "#o20181",
                            "#_20104",
                            "#p20182",
                            "#o20182"
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
                                "#p20181",
                                "#o20181",
                                "#p20182",
                                "#o20182",
                                "#p20184",
                                "#_20109",
                                "#p20186",
                                "#o20186",
                                "#p20188",
                                "#o20188",
                                "#p20190",
                                "#_20099",
                                "#_20104"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20192",
                                "#_20099"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20192" to null,
                                    "#_20099" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20192" to null,
                                    "#_20099" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20192" to null,
                                    "#_20099" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20192" to null,
                                    "#_20099" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20192" to null,
                                    "#_20099" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20192" to null,
                                    "#_20099" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20192" to null,
                                    "#_20099" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20192" to null,
                                    "#_20099" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20192" to null,
                                    "#_20099" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20192" to null,
                                    "#_20099" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20192" to null,
                                    "#_20099" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20192" to null,
                                    "#_20099" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20192" to null,
                                    "#_20099" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20181",
                            "#o20181",
                            "#_20104",
                            "#p20182",
                            "#o20182",
                            "#p20184",
                            "#_20109",
                            "#p20186",
                            "#o20186",
                            "#p20188",
                            "#o20188",
                            "#p20190",
                            "x",
                            "#p20192",
                            "#_20099"
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
                                "#p20328",
                                "#o20328",
                                "#p20329",
                                "#o20329",
                                "#_20216"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20224",
                                "#p20331",
                                "#o20331"
                            ), listOf(
                                mutableMapOf(
                                    "#_20224" to "<http://example.org/test#A>",
                                    "#p20331" to null,
                                    "#o20331" to null
                                ),
                                mutableMapOf(
                                    "#_20224" to "<http://example.org/test#B>",
                                    "#p20331" to null,
                                    "#o20331" to null
                                ),
                                mutableMapOf(
                                    "#_20224" to "<http://example.org/test#C>",
                                    "#p20331" to null,
                                    "#o20331" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20216",
                            "#p20328",
                            "#o20328",
                            "#p20329",
                            "#o20329",
                            "#_20224",
                            "#p20331",
                            "#o20331"
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
                                "#p20328",
                                "#o20328",
                                "#p20329",
                                "#o20329",
                                "#p20331",
                                "#o20331",
                                "#_20229",
                                "#p20333",
                                "#o20333",
                                "#p20335",
                                "#_20234",
                                "#p20337",
                                "#o20337",
                                "#p20339",
                                "#o20339",
                                "#p20341",
                                "#p20343",
                                "#_20216",
                                "#_20224"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20345",
                                "#_20216"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20345" to null,
                                    "#_20216" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20345" to null,
                                    "#_20216" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20345" to null,
                                    "#_20216" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20345" to null,
                                    "#_20216" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20345" to null,
                                    "#_20216" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20345" to null,
                                    "#_20216" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20345" to null,
                                    "#_20216" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20345" to null,
                                    "#_20216" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20345" to null,
                                    "#_20216" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20345" to null,
                                    "#_20216" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20345" to null,
                                    "#_20216" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20345" to null,
                                    "#_20216" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20345" to null,
                                    "#_20216" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20328",
                            "#o20328",
                            "#p20329",
                            "#o20329",
                            "#_20224",
                            "#p20331",
                            "#o20331",
                            "#_20229",
                            "#p20333",
                            "#o20333",
                            "#p20335",
                            "#_20234",
                            "#p20337",
                            "#o20337",
                            "#p20339",
                            "#o20339",
                            "#p20341",
                            "#p20343",
                            "x",
                            "#p20345",
                            "#_20216"
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
                                "#p20503",
                                "#o20503",
                                "#p20504",
                                "#o20504",
                                "#_20372"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20380",
                                "#p20506",
                                "#o20506"
                            ), listOf(
                                mutableMapOf(
                                    "#_20380" to "<http://example.org/test#A>",
                                    "#p20506" to null,
                                    "#o20506" to null
                                ),
                                mutableMapOf(
                                    "#_20380" to "<http://example.org/test#B>",
                                    "#p20506" to null,
                                    "#o20506" to null
                                ),
                                mutableMapOf(
                                    "#_20380" to "<http://example.org/test#C>",
                                    "#p20506" to null,
                                    "#o20506" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20372",
                            "#p20503",
                            "#o20503",
                            "#p20504",
                            "#o20504",
                            "#_20380",
                            "#p20506",
                            "#o20506"
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
                                "#p20503",
                                "#o20503",
                                "#p20504",
                                "#o20504",
                                "#p20506",
                                "#o20506",
                                "#_20385",
                                "#p20508",
                                "#o20508",
                                "#p20510",
                                "#_20390",
                                "#p20512",
                                "#o20512",
                                "#p20514",
                                "#_20395",
                                "#p20516",
                                "#o20516",
                                "#p20518",
                                "#o20518",
                                "#p20520",
                                "#p20522",
                                "#_20372",
                                "#_20380"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20524",
                                "#_20372"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20524" to null,
                                    "#_20372" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20524" to null,
                                    "#_20372" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20524" to null,
                                    "#_20372" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20524" to null,
                                    "#_20372" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20524" to null,
                                    "#_20372" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20524" to null,
                                    "#_20372" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20524" to null,
                                    "#_20372" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20524" to null,
                                    "#_20372" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20524" to null,
                                    "#_20372" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20524" to null,
                                    "#_20372" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20524" to null,
                                    "#_20372" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20524" to null,
                                    "#_20372" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20524" to null,
                                    "#_20372" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20503",
                            "#o20503",
                            "#p20504",
                            "#o20504",
                            "#_20380",
                            "#p20506",
                            "#o20506",
                            "#_20385",
                            "#p20508",
                            "#o20508",
                            "#p20510",
                            "#_20390",
                            "#p20512",
                            "#o20512",
                            "#p20514",
                            "#_20395",
                            "#p20516",
                            "#o20516",
                            "#p20518",
                            "#o20518",
                            "#p20520",
                            "#p20522",
                            "x",
                            "#p20524",
                            "#_20372"
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
                                "#_20553",
                                "#p20692",
                                "#o20692"
                            ), listOf(
                                mutableMapOf(
                                    "#_20553" to "<http://example.org/test#A>",
                                    "#p20692" to null,
                                    "#o20692" to null
                                ),
                                mutableMapOf(
                                    "#_20553" to "<http://example.org/test#B>",
                                    "#p20692" to null,
                                    "#o20692" to null
                                ),
                                mutableMapOf(
                                    "#_20553" to "<http://example.org/test#C>",
                                    "#p20692" to null,
                                    "#o20692" to null
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20558",
                                "#p20693",
                                "#o20693"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20553",
                            "#p20692",
                            "#o20692",
                            "#_20558",
                            "#p20693",
                            "#o20693"
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
                                "#_20553",
                                "#p20692",
                                "#o20692",
                                "#_20558",
                                "#p20693",
                                "#o20693"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20562",
                                "#p20695",
                                "#o20695"
                            ), listOf(
                                mutableMapOf(
                                    "#_20562" to "<http://example.org/test#A>",
                                    "#p20695" to null,
                                    "#o20695" to null
                                ),
                                mutableMapOf(
                                    "#_20562" to "<http://example.org/test#B>",
                                    "#p20695" to null,
                                    "#o20695" to null
                                ),
                                mutableMapOf(
                                    "#_20562" to "<http://example.org/test#C>",
                                    "#p20695" to null,
                                    "#o20695" to null
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20553",
                            "#p20692",
                            "#o20692",
                            "#_20558",
                            "#p20693",
                            "#o20693",
                            "#_20562",
                            "#p20695",
                            "#o20695"
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
                                "#p20692",
                                "#o20692",
                                "#p20693",
                                "#o20693",
                                "#_20562",
                                "#p20695",
                                "#o20695",
                                "#_20567",
                                "#p20697",
                                "#o20697",
                                "#p20699",
                                "#_20572",
                                "#p20701",
                                "#o20701",
                                "#p20703",
                                "#o20703",
                                "#p20705",
                                "#p20707",
                                "#_20578",
                                "#p20709",
                                "#p20711",
                                "#o20711",
                                "#p20713",
                                "#_20553",
                                "#_20558"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20715",
                                "#_20553"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20715" to null,
                                    "#_20553" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20715" to null,
                                    "#_20553" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20715" to null,
                                    "#_20553" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20715" to null,
                                    "#_20553" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20715" to null,
                                    "#_20553" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20715" to null,
                                    "#_20553" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20715" to null,
                                    "#_20553" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20715" to null,
                                    "#_20553" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20715" to null,
                                    "#_20553" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20715" to null,
                                    "#_20553" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20715" to null,
                                    "#_20553" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20715" to null,
                                    "#_20553" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20715" to null,
                                    "#_20553" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20692",
                            "#o20692",
                            "#_20558",
                            "#p20693",
                            "#o20693",
                            "#_20562",
                            "#p20695",
                            "#o20695",
                            "#_20567",
                            "#p20697",
                            "#o20697",
                            "#p20699",
                            "#_20572",
                            "#p20701",
                            "#o20701",
                            "#p20703",
                            "#o20703",
                            "#p20705",
                            "#p20707",
                            "#_20578",
                            "#p20709",
                            "#p20711",
                            "#o20711",
                            "#p20713",
                            "x",
                            "#p20715",
                            "#_20553"
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
                                "#p20830",
                                "#o20830",
                                "#p20831",
                                "#o20831",
                                "#p20833",
                                "#o20833",
                                "#p20835",
                                "#o20835",
                                "#p20837",
                                "#o20837",
                                "#p20839",
                                "#_20745",
                                "#_20753"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p20841",
                                "#_20745"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#A>",
                                    "#p20841" to null,
                                    "#_20745" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#B>",
                                    "#p20841" to null,
                                    "#_20745" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#C>",
                                    "#p20841" to null,
                                    "#_20745" to "<http://www.w3.org/2002/07/owl#Class>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20841" to null,
                                    "#_20745" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#a>",
                                    "#p20841" to null,
                                    "#_20745" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p20841" to null,
                                    "#_20745" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#c>",
                                    "#p20841" to null,
                                    "#_20745" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20841" to null,
                                    "#_20745" to "<http://example.org/test#A>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20841" to null,
                                    "#_20745" to "<http://example.org/test#B>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#d>",
                                    "#p20841" to null,
                                    "#_20745" to "<http://example.org/test#C>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20841" to null,
                                    "#_20745" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/test#p>",
                                    "#p20841" to null,
                                    "#_20745" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                                ),
                                mutableMapOf(
                                    "x" to "_:simple",
                                    "#p20841" to null,
                                    "#_20745" to "<http://www.w3.org/2002/07/owl#Ontology>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p20830",
                            "#o20830",
                            "#p20831",
                            "#o20831",
                            "#_20753",
                            "#p20833",
                            "#o20833",
                            "#p20835",
                            "#o20835",
                            "#p20837",
                            "#o20837",
                            "#p20839",
                            "x",
                            "#p20841",
                            "#_20745"
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
                                "#s23986",
                                "#p23986",
                                "str1"
                            ), listOf(
                                mutableMapOf(
                                    "#s23986" to null,
                                    "#p23986" to null,
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#s23987",
                                "#p23987",
                                "str2"
                            ), listOf(
                                mutableMapOf(
                                    "#s23987" to null,
                                    "#p23987" to null,
                                    "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#s23986",
                            "#p23986",
                            "str1",
                            "#s23987",
                            "#p23987",
                            "str2"
                        ), listOf(
                            mutableMapOf(
                                "#s23986" to null,
                                "#p23986" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#s23987" to null,
                                "#p23987" to null,
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
                                "#s24014",
                                "#p24014",
                                "str1"
                            ), listOf(
                                mutableMapOf(
                                    "#s24014" to null,
                                    "#p24014" to null,
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
                                    "#s24015" to null,
                                    "#p24015" to null,
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
                                "#s24014" to null,
                                "#p24014" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#s24015" to null,
                                "#p24015" to null,
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
                                "#p24101",
                                "str1"
                            ), listOf(
                                mutableMapOf(
                                    "s1" to "<http://example.org/s1>",
                                    "#p24101" to null,
                                    "str1" to "\"123\""
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s2>",
                                    "#p24101" to null,
                                    "str1" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s3>",
                                    "#p24101" to null,
                                    "str1" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s4>",
                                    "#p24101" to null,
                                    "str1" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s5>",
                                    "#p24101" to null,
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s6>",
                                    "#p24101" to null,
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s7>",
                                    "#p24101" to null,
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s2",
                                "#p24102",
                                "str2"
                            ), listOf(
                                mutableMapOf(
                                    "s2" to "<http://example.org/s1>",
                                    "#p24102" to null,
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "s2" to "<http://example.org/s2>",
                                    "#p24102" to null,
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "s2" to "<http://example.org/s3>",
                                    "#p24102" to null,
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "s2" to "<http://example.org/s4>",
                                    "#p24102" to null,
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "s2" to "<http://example.org/s5>",
                                    "#p24102" to null,
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s2" to "<http://example.org/s6>",
                                    "#p24102" to null,
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s2" to "<http://example.org/s7>",
                                    "#p24102" to null,
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s1",
                            "#p24101",
                            "str1",
                            "s2",
                            "#p24102",
                            "str2"
                        ), listOf(
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
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
                                "#p26389",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "#p26389" to null,
                                    "x" to "\"a\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "#p26389" to null,
                                    "x" to "_:b"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "#p26389" to null,
                                    "x" to "<http://example/a>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "#p26389" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "#p26389" to null,
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "#p26389" to null,
                                    "x" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "#p26389" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "#p26389" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p26390",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "#p26390" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "#p26390" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "#p26390" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "#p26390" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "#p26390" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "#p26390" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "#p26390" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "#p26390" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p26389",
                            "x",
                            "#p26390",
                            "y",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p26389" to null,
                                "x" to "\"a\"",
                                "#p26390" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x1>"
                            ),
                            mutableMapOf(
                                "#p26389" to null,
                                "x" to "_:b",
                                "#p26390" to null,
                                "y" to "\"1\"",
                                "s" to "<http://example/x2>"
                            ),
                            mutableMapOf(
                                "#p26389" to null,
                                "x" to "<http://example/a>",
                                "#p26390" to null,
                                "y" to "\"1\"",
                                "s" to "<http://example/x3>"
                            ),
                            mutableMapOf(
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>"
                            ),
                            mutableMapOf(
                                "#p26389" to null,
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x5>"
                            ),
                            mutableMapOf(
                                "#p26389" to null,
                                "x" to "\"1\"",
                                "#p26390" to null,
                                "y" to "\"2\"",
                                "s" to "<http://example/x6>"
                            ),
                            mutableMapOf(
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26390" to null,
                                "y" to "\"2\"",
                                "s" to "<http://example/x7>"
                            ),
                            mutableMapOf(
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26390" to null,
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
                                "#p26562",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "#p26562" to null,
                                    "x" to "\"a\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "#p26562" to null,
                                    "x" to "_:b"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "#p26562" to null,
                                    "x" to "<http://example/a>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "#p26562" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "#p26562" to null,
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "#p26562" to null,
                                    "x" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "#p26562" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "#p26562" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p26563",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "#p26563" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "#p26563" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "#p26563" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "#p26563" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "#p26563" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "#p26563" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "#p26563" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "#p26563" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p26562",
                            "x",
                            "#p26563",
                            "y",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p26562" to null,
                                "x" to "\"a\"",
                                "#p26563" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x1>"
                            ),
                            mutableMapOf(
                                "#p26562" to null,
                                "x" to "_:b",
                                "#p26563" to null,
                                "y" to "\"1\"",
                                "s" to "<http://example/x2>"
                            ),
                            mutableMapOf(
                                "#p26562" to null,
                                "x" to "<http://example/a>",
                                "#p26563" to null,
                                "y" to "\"1\"",
                                "s" to "<http://example/x3>"
                            ),
                            mutableMapOf(
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>"
                            ),
                            mutableMapOf(
                                "#p26562" to null,
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x5>"
                            ),
                            mutableMapOf(
                                "#p26562" to null,
                                "x" to "\"1\"",
                                "#p26563" to null,
                                "y" to "\"2\"",
                                "s" to "<http://example/x6>"
                            ),
                            mutableMapOf(
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26563" to null,
                                "y" to "\"2\"",
                                "s" to "<http://example/x7>"
                            ),
                            mutableMapOf(
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26563" to null,
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
                                "#p28431",
                                "s1"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/s1>",
                                    "#p28431" to null,
                                    "s1" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s2>",
                                    "#p28431" to null,
                                    "s1" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s3>",
                                    "#p28431" to null,
                                    "s1" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s4>",
                                    "#p28431" to null,
                                    "s1" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s5>",
                                    "#p28431" to null,
                                    "s1" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s6>",
                                    "#p28431" to null,
                                    "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s7>",
                                    "#p28431" to null,
                                    "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "#p28432",
                                "s2"
                            ), listOf(
                                mutableMapOf(
                                    "b" to "<http://example.org/s1>",
                                    "#p28432" to null,
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "b" to "<http://example.org/s2>",
                                    "#p28432" to null,
                                    "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "b" to "<http://example.org/s3>",
                                    "#p28432" to null,
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "b" to "<http://example.org/s4>",
                                    "#p28432" to null,
                                    "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "b" to "<http://example.org/s5>",
                                    "#p28432" to null,
                                    "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "b" to "<http://example.org/s6>",
                                    "#p28432" to null,
                                    "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "b" to "<http://example.org/s7>",
                                    "#p28432" to null,
                                    "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p28431",
                            "s1",
                            "b",
                            "#p28432",
                            "s2"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28431" to null,
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s1>",
                                "#p28432" to null,
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28431" to null,
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s1>",
                                "#p28432" to null,
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28431" to null,
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s1>",
                                "#p28432" to null,
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28431" to null,
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s1>",
                                "#p28432" to null,
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28431" to null,
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s1>",
                                "#p28432" to null,
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28431" to null,
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s1>",
                                "#p28432" to null,
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28431" to null,
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s1>",
                                "#p28432" to null,
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28431" to null,
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s2>",
                                "#p28432" to null,
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28431" to null,
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s2>",
                                "#p28432" to null,
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28431" to null,
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s2>",
                                "#p28432" to null,
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28431" to null,
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s2>",
                                "#p28432" to null,
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28431" to null,
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s2>",
                                "#p28432" to null,
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28431" to null,
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s2>",
                                "#p28432" to null,
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28431" to null,
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s2>",
                                "#p28432" to null,
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28431" to null,
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s3>",
                                "#p28432" to null,
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28431" to null,
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s3>",
                                "#p28432" to null,
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28431" to null,
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s3>",
                                "#p28432" to null,
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28431" to null,
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s3>",
                                "#p28432" to null,
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28431" to null,
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s3>",
                                "#p28432" to null,
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28431" to null,
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s3>",
                                "#p28432" to null,
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28431" to null,
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s3>",
                                "#p28432" to null,
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28431" to null,
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s4>",
                                "#p28432" to null,
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28431" to null,
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s4>",
                                "#p28432" to null,
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28431" to null,
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s4>",
                                "#p28432" to null,
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28431" to null,
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s4>",
                                "#p28432" to null,
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28431" to null,
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s4>",
                                "#p28432" to null,
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28431" to null,
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s4>",
                                "#p28432" to null,
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28431" to null,
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s4>",
                                "#p28432" to null,
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28431" to null,
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s5>",
                                "#p28432" to null,
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28431" to null,
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s5>",
                                "#p28432" to null,
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28431" to null,
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s5>",
                                "#p28432" to null,
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28431" to null,
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s5>",
                                "#p28432" to null,
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28431" to null,
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s5>",
                                "#p28432" to null,
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28431" to null,
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s5>",
                                "#p28432" to null,
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28431" to null,
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s5>",
                                "#p28432" to null,
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28431" to null,
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s6>",
                                "#p28432" to null,
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28431" to null,
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s6>",
                                "#p28432" to null,
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28431" to null,
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s6>",
                                "#p28432" to null,
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28431" to null,
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s6>",
                                "#p28432" to null,
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28431" to null,
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s6>",
                                "#p28432" to null,
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28431" to null,
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s6>",
                                "#p28432" to null,
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28431" to null,
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s6>",
                                "#p28432" to null,
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28431" to null,
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s7>",
                                "#p28432" to null,
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28431" to null,
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s7>",
                                "#p28432" to null,
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28431" to null,
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s7>",
                                "#p28432" to null,
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28431" to null,
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s7>",
                                "#p28432" to null,
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28431" to null,
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s7>",
                                "#p28432" to null,
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28431" to null,
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s7>",
                                "#p28432" to null,
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28431" to null,
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s7>",
                                "#p28432" to null,
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
                                "#p32563",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32563" to null,
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/s2>",
                                    "#p32563" to null,
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p32564",
                                "w"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32564" to null,
                                    "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "#p32563",
                            "v",
                            "#p32564",
                            "w",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p32563" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32564" to null,
                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/s1>"
                            ),
                            mutableMapOf(
                                "#p32563" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32564" to null,
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
                                "#p32601",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32601" to null,
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/s2>",
                                    "#p32601" to null,
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p32602",
                                "w"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32602" to null,
                                    "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "#p32601",
                            "v",
                            "#p32602",
                            "w",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p32601" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32602" to null,
                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/s1>"
                            ),
                            mutableMapOf(
                                "#p32601" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32602" to null,
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
                                "#p32758",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32758" to null,
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/s2>",
                                    "#p32758" to null,
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/s3>",
                                    "#p32758" to null,
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p32759",
                                "w"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32759" to null,
                                    "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "#p32758",
                            "v",
                            "#p32759",
                            "w",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p32758" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32759" to null,
                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/s1>"
                            ),
                            mutableMapOf(
                                "#p32758" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32759" to null,
                                "w" to null,
                                "s" to "<http://example/s2>"
                            ),
                            mutableMapOf(
                                "#p32758" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32759" to null,
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
                                "#p32769",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32769" to null,
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/s2>",
                                    "#p32769" to null,
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/s3>",
                                    "#p32769" to null,
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p32770",
                                "w"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/s1>",
                                    "#p32770" to null,
                                    "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "#p32769",
                            "v",
                            "#p32770",
                            "w",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#p32769" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32770" to null,
                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/s1>"
                            ),
                            mutableMapOf(
                                "#p32769" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32770" to null,
                                "w" to null,
                                "s" to "<http://example/s2>"
                            ),
                            mutableMapOf(
                                "#p32769" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p32770" to null,
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
                                "#p34833",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34833" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p34834",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34834" to null,
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34834" to null,
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p34833",
                            "y",
                            "#p34834",
                            "z",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p34833" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34834" to null,
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            ),
                            mutableMapOf(
                                "#p34833" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34834" to null,
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
                                "#p34872",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34872" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p34873",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34873" to null,
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34873" to null,
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p34872",
                            "y",
                            "#p34873",
                            "z",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p34872" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34873" to null,
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            ),
                            mutableMapOf(
                                "#p34872" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34873" to null,
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
                                "#p34982",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34982" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p34983",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34983" to null,
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p34983" to null,
                                    "z" to "\"foobar\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p34982",
                            "y",
                            "#p34983",
                            "z",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p34982" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34983" to null,
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            ),
                            mutableMapOf(
                                "#p34982" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34983" to null,
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
                                "#p35010",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35010" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35011",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35011" to null,
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35011" to null,
                                    "z" to "\"foobar\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p35010",
                            "y",
                            "#p35011",
                            "z",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p35010" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35011" to null,
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            ),
                            mutableMapOf(
                                "#p35010" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35011" to null,
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
                                "#p35122",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35122" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35123",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35123" to null,
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p35122",
                            "y",
                            "#p35123",
                            "z",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p35122" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35123" to null,
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
                                "#p35167",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35167" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35168",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35168" to null,
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p35167",
                            "y",
                            "#p35168",
                            "z",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p35167" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35168" to null,
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
                                "#p35626",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35626" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#b>",
                                    "#p35626" to null,
                                    "y" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35627",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35627" to null,
                                    "l" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "#p35626",
                            "y",
                            "#p35627",
                            "l",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#p35626" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35627" to null,
                                "l" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>"
                            ),
                            mutableMapOf(
                                "#p35626" to null,
                                "y" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35627" to null,
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
                                "#_36963",
                                "#p37034",
                                "L"
                            ), listOf(
                                mutableMapOf(
                                    "#_36963" to "_:_36940",
                                    "#p37034" to null,
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36941",
                                    "#p37034" to null,
                                    "L" to "\"Pizza\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36942",
                                    "#p37034" to null,
                                    "L" to "\"Wine\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36943",
                                    "#p37034" to null,
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36944",
                                    "#p37034" to null,
                                    "L" to "\"Pasta\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36945",
                                    "#p37034" to null,
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36946",
                                    "#p37034" to null,
                                    "L" to "\"Sandwich\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36947",
                                    "#p37034" to null,
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36948",
                                    "#p37034" to null,
                                    "L" to "\"Bagel\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36949",
                                    "#p37034" to null,
                                    "L" to "\"Soft Drink\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37035",
                                "#_36963"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36940"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36941"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36942"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36943"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36944"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36945"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36946"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36947"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36948"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36949"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37034",
                            "L",
                            "O",
                            "#p37035",
                            "#_36963"
                        ), listOf(
                            mutableMapOf(
                                "#p37034" to null,
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37035" to null,
                                "#_36963" to "_:_36940"
                            ),
                            mutableMapOf(
                                "#p37034" to null,
                                "L" to "\"Pizza\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37035" to null,
                                "#_36963" to "_:_36941"
                            ),
                            mutableMapOf(
                                "#p37034" to null,
                                "L" to "\"Wine\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37035" to null,
                                "#_36963" to "_:_36942"
                            ),
                            mutableMapOf(
                                "#p37034" to null,
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37035" to null,
                                "#_36963" to "_:_36943"
                            ),
                            mutableMapOf(
                                "#p37034" to null,
                                "L" to "\"Pasta\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37035" to null,
                                "#_36963" to "_:_36944"
                            ),
                            mutableMapOf(
                                "#p37034" to null,
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37035" to null,
                                "#_36963" to "_:_36945"
                            ),
                            mutableMapOf(
                                "#p37034" to null,
                                "L" to "\"Sandwich\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37035" to null,
                                "#_36963" to "_:_36946"
                            ),
                            mutableMapOf(
                                "#p37034" to null,
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37035" to null,
                                "#_36963" to "_:_36947"
                            ),
                            mutableMapOf(
                                "#p37034" to null,
                                "L" to "\"Bagel\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37035" to null,
                                "#_36963" to "_:_36948"
                            ),
                            mutableMapOf(
                                "#p37034" to null,
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37035" to null,
                                "#_36963" to "_:_36949"
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
                                "#p37034",
                                "L",
                                "O",
                                "#p37035",
                                "#_36963"
                            ), listOf(
                                mutableMapOf(
                                    "#p37034" to null,
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36940"
                                ),
                                mutableMapOf(
                                    "#p37034" to null,
                                    "L" to "\"Pizza\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36941"
                                ),
                                mutableMapOf(
                                    "#p37034" to null,
                                    "L" to "\"Wine\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36942"
                                ),
                                mutableMapOf(
                                    "#p37034" to null,
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36943"
                                ),
                                mutableMapOf(
                                    "#p37034" to null,
                                    "L" to "\"Pasta\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36944"
                                ),
                                mutableMapOf(
                                    "#p37034" to null,
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36945"
                                ),
                                mutableMapOf(
                                    "#p37034" to null,
                                    "L" to "\"Sandwich\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36946"
                                ),
                                mutableMapOf(
                                    "#p37034" to null,
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36947"
                                ),
                                mutableMapOf(
                                    "#p37034" to null,
                                    "L" to "\"Bagel\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36948"
                                ),
                                mutableMapOf(
                                    "#p37034" to null,
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37035" to null,
                                    "#_36963" to "_:_36949"
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
                            "#_36963",
                            "#p37034",
                            "L",
                            "#p37035",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#_36963" to "_:_36940",
                                "#p37034" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37035" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36941",
                                "#p37034" to null,
                                "L" to "\"Pizza\"",
                                "#p37035" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36942",
                                "#p37034" to null,
                                "L" to "\"Wine\"",
                                "#p37035" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36943",
                                "#p37034" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37035" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36944",
                                "#p37034" to null,
                                "L" to "\"Pasta\"",
                                "#p37035" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36945",
                                "#p37034" to null,
                                "L" to "\"Soft Drink\"",
                                "#p37035" to null,
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
                                "#_36963",
                                "#p37055",
                                "L"
                            ), listOf(
                                mutableMapOf(
                                    "#_36963" to "_:_36940",
                                    "#p37055" to null,
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36941",
                                    "#p37055" to null,
                                    "L" to "\"Pizza\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36942",
                                    "#p37055" to null,
                                    "L" to "\"Wine\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36943",
                                    "#p37055" to null,
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36944",
                                    "#p37055" to null,
                                    "L" to "\"Pasta\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36945",
                                    "#p37055" to null,
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36946",
                                    "#p37055" to null,
                                    "L" to "\"Sandwich\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36947",
                                    "#p37055" to null,
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36948",
                                    "#p37055" to null,
                                    "L" to "\"Bagel\""
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36949",
                                    "#p37055" to null,
                                    "L" to "\"Soft Drink\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37056",
                                "#_36963"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36940"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36941"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36942"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36943"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36944"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36945"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36946"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36947"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36948"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36949"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37055",
                            "L",
                            "O",
                            "#p37056",
                            "#_36963"
                        ), listOf(
                            mutableMapOf(
                                "#p37055" to null,
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37056" to null,
                                "#_36963" to "_:_36940"
                            ),
                            mutableMapOf(
                                "#p37055" to null,
                                "L" to "\"Pizza\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37056" to null,
                                "#_36963" to "_:_36941"
                            ),
                            mutableMapOf(
                                "#p37055" to null,
                                "L" to "\"Wine\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37056" to null,
                                "#_36963" to "_:_36942"
                            ),
                            mutableMapOf(
                                "#p37055" to null,
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37056" to null,
                                "#_36963" to "_:_36943"
                            ),
                            mutableMapOf(
                                "#p37055" to null,
                                "L" to "\"Pasta\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37056" to null,
                                "#_36963" to "_:_36944"
                            ),
                            mutableMapOf(
                                "#p37055" to null,
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37056" to null,
                                "#_36963" to "_:_36945"
                            ),
                            mutableMapOf(
                                "#p37055" to null,
                                "L" to "\"Sandwich\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37056" to null,
                                "#_36963" to "_:_36946"
                            ),
                            mutableMapOf(
                                "#p37055" to null,
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37056" to null,
                                "#_36963" to "_:_36947"
                            ),
                            mutableMapOf(
                                "#p37055" to null,
                                "L" to "\"Bagel\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37056" to null,
                                "#_36963" to "_:_36948"
                            ),
                            mutableMapOf(
                                "#p37055" to null,
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37056" to null,
                                "#_36963" to "_:_36949"
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
                                "#p37055",
                                "L",
                                "O",
                                "#p37056",
                                "#_36963"
                            ), listOf(
                                mutableMapOf(
                                    "#p37055" to null,
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36940"
                                ),
                                mutableMapOf(
                                    "#p37055" to null,
                                    "L" to "\"Pizza\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36941"
                                ),
                                mutableMapOf(
                                    "#p37055" to null,
                                    "L" to "\"Wine\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36942"
                                ),
                                mutableMapOf(
                                    "#p37055" to null,
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36943"
                                ),
                                mutableMapOf(
                                    "#p37055" to null,
                                    "L" to "\"Pasta\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36944"
                                ),
                                mutableMapOf(
                                    "#p37055" to null,
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36945"
                                ),
                                mutableMapOf(
                                    "#p37055" to null,
                                    "L" to "\"Sandwich\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36946"
                                ),
                                mutableMapOf(
                                    "#p37055" to null,
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36947"
                                ),
                                mutableMapOf(
                                    "#p37055" to null,
                                    "L" to "\"Bagel\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36948"
                                ),
                                mutableMapOf(
                                    "#p37055" to null,
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37056" to null,
                                    "#_36963" to "_:_36949"
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
                            "#_36963",
                            "#p37055",
                            "L",
                            "#p37056",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#_36963" to "_:_36940",
                                "#p37055" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37056" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36941",
                                "#p37055" to null,
                                "L" to "\"Pizza\"",
                                "#p37056" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36942",
                                "#p37055" to null,
                                "L" to "\"Wine\"",
                                "#p37056" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36943",
                                "#p37055" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37056" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36944",
                                "#p37055" to null,
                                "L" to "\"Pasta\"",
                                "#p37056" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36945",
                                "#p37055" to null,
                                "L" to "\"Soft Drink\"",
                                "#p37056" to null,
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
                                "#p37177",
                                "F"
                            ), listOf(
                                mutableMapOf(
                                    "P" to "<http://p1>",
                                    "#p37177" to null,
                                    "F" to "\"John\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "#p37178",
                                "L"
                            ), listOf(
                                mutableMapOf(
                                    "P" to "<http://p1>",
                                    "#p37178" to null,
                                    "L" to "\"Doe\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37177",
                            "F",
                            "#p37178",
                            "L",
                            "P"
                        ), listOf(
                            mutableMapOf(
                                "#p37177" to null,
                                "F" to "\"John\"",
                                "#p37178" to null,
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
                                "#p37232",
                                "F"
                            ), listOf(
                                mutableMapOf(
                                    "P" to "<http://p1>",
                                    "#p37232" to null,
                                    "F" to "\"John\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "#p37233",
                                "L"
                            ), listOf(
                                mutableMapOf(
                                    "P" to "<http://p1>",
                                    "#p37233" to null,
                                    "L" to "\"Doe\""
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37232",
                            "F",
                            "#p37233",
                            "L",
                            "P"
                        ), listOf(
                            mutableMapOf(
                                "#p37232" to null,
                                "F" to "\"John\"",
                                "#p37233" to null,
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
                                "#_37328",
                                "#p37399",
                                "L"
                            ), listOf(
                                mutableMapOf(
                                    "#_37328" to "_:_37305",
                                    "#p37399" to null,
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37306",
                                    "#p37399" to null,
                                    "L" to "\"Pizza\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37307",
                                    "#p37399" to null,
                                    "L" to "\"Wine\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37308",
                                    "#p37399" to null,
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37309",
                                    "#p37399" to null,
                                    "L" to "\"Pasta\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37310",
                                    "#p37399" to null,
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37311",
                                    "#p37399" to null,
                                    "L" to "\"Sandwich\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37312",
                                    "#p37399" to null,
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37313",
                                    "#p37399" to null,
                                    "L" to "\"Bagel\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37314",
                                    "#p37399" to null,
                                    "L" to "\"Soft Drink\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37400",
                                "#_37328"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37305"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37306"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37307"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37308"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37309"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37310"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37311"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37312"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37313"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37314"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37399",
                            "L",
                            "O",
                            "#p37400",
                            "#_37328"
                        ), listOf(
                            mutableMapOf(
                                "#p37399" to null,
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37400" to null,
                                "#_37328" to "_:_37305"
                            ),
                            mutableMapOf(
                                "#p37399" to null,
                                "L" to "\"Pizza\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37400" to null,
                                "#_37328" to "_:_37306"
                            ),
                            mutableMapOf(
                                "#p37399" to null,
                                "L" to "\"Wine\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37400" to null,
                                "#_37328" to "_:_37307"
                            ),
                            mutableMapOf(
                                "#p37399" to null,
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37400" to null,
                                "#_37328" to "_:_37308"
                            ),
                            mutableMapOf(
                                "#p37399" to null,
                                "L" to "\"Pasta\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37400" to null,
                                "#_37328" to "_:_37309"
                            ),
                            mutableMapOf(
                                "#p37399" to null,
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37400" to null,
                                "#_37328" to "_:_37310"
                            ),
                            mutableMapOf(
                                "#p37399" to null,
                                "L" to "\"Sandwich\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37400" to null,
                                "#_37328" to "_:_37311"
                            ),
                            mutableMapOf(
                                "#p37399" to null,
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37400" to null,
                                "#_37328" to "_:_37312"
                            ),
                            mutableMapOf(
                                "#p37399" to null,
                                "L" to "\"Bagel\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37400" to null,
                                "#_37328" to "_:_37313"
                            ),
                            mutableMapOf(
                                "#p37399" to null,
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37400" to null,
                                "#_37328" to "_:_37314"
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
                                "#p37399",
                                "L",
                                "O",
                                "#p37400",
                                "#_37328"
                            ), listOf(
                                mutableMapOf(
                                    "#p37399" to null,
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37305"
                                ),
                                mutableMapOf(
                                    "#p37399" to null,
                                    "L" to "\"Pizza\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37306"
                                ),
                                mutableMapOf(
                                    "#p37399" to null,
                                    "L" to "\"Wine\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37307"
                                ),
                                mutableMapOf(
                                    "#p37399" to null,
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37308"
                                ),
                                mutableMapOf(
                                    "#p37399" to null,
                                    "L" to "\"Pasta\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37309"
                                ),
                                mutableMapOf(
                                    "#p37399" to null,
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37310"
                                ),
                                mutableMapOf(
                                    "#p37399" to null,
                                    "L" to "\"Sandwich\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37311"
                                ),
                                mutableMapOf(
                                    "#p37399" to null,
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37312"
                                ),
                                mutableMapOf(
                                    "#p37399" to null,
                                    "L" to "\"Bagel\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37313"
                                ),
                                mutableMapOf(
                                    "#p37399" to null,
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37400" to null,
                                    "#_37328" to "_:_37314"
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
                            "#_37328",
                            "#p37399",
                            "L",
                            "#p37400",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#_37328" to "_:_37305",
                                "#p37399" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37400" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37306",
                                "#p37399" to null,
                                "L" to "\"Pizza\"",
                                "#p37400" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37307",
                                "#p37399" to null,
                                "L" to "\"Wine\"",
                                "#p37400" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37308",
                                "#p37399" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37400" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37309",
                                "#p37399" to null,
                                "L" to "\"Pasta\"",
                                "#p37400" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37310",
                                "#p37399" to null,
                                "L" to "\"Soft Drink\"",
                                "#p37400" to null,
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
                                "#_37328",
                                "#p37420",
                                "L"
                            ), listOf(
                                mutableMapOf(
                                    "#_37328" to "_:_37305",
                                    "#p37420" to null,
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37306",
                                    "#p37420" to null,
                                    "L" to "\"Pizza\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37307",
                                    "#p37420" to null,
                                    "L" to "\"Wine\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37308",
                                    "#p37420" to null,
                                    "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37309",
                                    "#p37420" to null,
                                    "L" to "\"Pasta\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37310",
                                    "#p37420" to null,
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37311",
                                    "#p37420" to null,
                                    "L" to "\"Sandwich\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37312",
                                    "#p37420" to null,
                                    "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37313",
                                    "#p37420" to null,
                                    "L" to "\"Bagel\""
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37314",
                                    "#p37420" to null,
                                    "L" to "\"Soft Drink\""
                                )
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37421",
                                "#_37328"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37305"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37306"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37307"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37308"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37309"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37310"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37311"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37312"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37313"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37314"
                                )
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#p37420",
                            "L",
                            "O",
                            "#p37421",
                            "#_37328"
                        ), listOf(
                            mutableMapOf(
                                "#p37420" to null,
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37421" to null,
                                "#_37328" to "_:_37305"
                            ),
                            mutableMapOf(
                                "#p37420" to null,
                                "L" to "\"Pizza\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37421" to null,
                                "#_37328" to "_:_37306"
                            ),
                            mutableMapOf(
                                "#p37420" to null,
                                "L" to "\"Wine\"",
                                "O" to "<http://www.example.orgorder1>",
                                "#p37421" to null,
                                "#_37328" to "_:_37307"
                            ),
                            mutableMapOf(
                                "#p37420" to null,
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37421" to null,
                                "#_37328" to "_:_37308"
                            ),
                            mutableMapOf(
                                "#p37420" to null,
                                "L" to "\"Pasta\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37421" to null,
                                "#_37328" to "_:_37309"
                            ),
                            mutableMapOf(
                                "#p37420" to null,
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder2>",
                                "#p37421" to null,
                                "#_37328" to "_:_37310"
                            ),
                            mutableMapOf(
                                "#p37420" to null,
                                "L" to "\"Sandwich\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37421" to null,
                                "#_37328" to "_:_37311"
                            ),
                            mutableMapOf(
                                "#p37420" to null,
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder3>",
                                "#p37421" to null,
                                "#_37328" to "_:_37312"
                            ),
                            mutableMapOf(
                                "#p37420" to null,
                                "L" to "\"Bagel\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37421" to null,
                                "#_37328" to "_:_37313"
                            ),
                            mutableMapOf(
                                "#p37420" to null,
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder4>",
                                "#p37421" to null,
                                "#_37328" to "_:_37314"
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
                                "#p37420",
                                "L",
                                "O",
                                "#p37421",
                                "#_37328"
                            ), listOf(
                                mutableMapOf(
                                    "#p37420" to null,
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37305"
                                ),
                                mutableMapOf(
                                    "#p37420" to null,
                                    "L" to "\"Pizza\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37306"
                                ),
                                mutableMapOf(
                                    "#p37420" to null,
                                    "L" to "\"Wine\"",
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37307"
                                ),
                                mutableMapOf(
                                    "#p37420" to null,
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37308"
                                ),
                                mutableMapOf(
                                    "#p37420" to null,
                                    "L" to "\"Pasta\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37309"
                                ),
                                mutableMapOf(
                                    "#p37420" to null,
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37310"
                                ),
                                mutableMapOf(
                                    "#p37420" to null,
                                    "L" to "\"Sandwich\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37311"
                                ),
                                mutableMapOf(
                                    "#p37420" to null,
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37312"
                                ),
                                mutableMapOf(
                                    "#p37420" to null,
                                    "L" to "\"Bagel\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37313"
                                ),
                                mutableMapOf(
                                    "#p37420" to null,
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37421" to null,
                                    "#_37328" to "_:_37314"
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
                            "#_37328",
                            "#p37420",
                            "L",
                            "#p37421",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#_37328" to "_:_37305",
                                "#p37420" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37421" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37306",
                                "#p37420" to null,
                                "L" to "\"Pizza\"",
                                "#p37421" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37307",
                                "#p37420" to null,
                                "L" to "\"Wine\"",
                                "#p37421" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37308",
                                "#p37420" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37421" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37309",
                                "#p37420" to null,
                                "L" to "\"Pasta\"",
                                "#p37421" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37310",
                                "#p37420" to null,
                                "L" to "\"Soft Drink\"",
                                "#p37421" to null,
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
                e.printStackTrace()
                assertTrue(data.expected is Throwable)
            }
        }
    }
}
