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


class GeneratedPOPBindTest {
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
                    POPBind(
                        dictionary,
                        AOPVariable("O12"),
                        POPExpression(dictionary, AOPAddition(AOPVariable("O2"), AOPVariable("O1"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "O12",
                            "S",
                            "#p2396",
                            "O1",
                            "#p2397",
                            "O2"
                        ), listOf(
                            mutableMapOf(
                                "O12" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2396" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2396" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2396" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2396" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2396" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2396" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2396" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2396" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2396" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2397" to null,
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("O12"),
                        POPExpression(dictionary, AOPAddition(AOPVariable("O2"), AOPVariable("O1"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "O12",
                            "S",
                            "#p2658",
                            "O1",
                            "#p2659",
                            "O2"
                        ), listOf(
                            mutableMapOf(
                                "O12" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2658" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2658" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2658" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2658" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2658" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2658" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2658" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2658" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "S" to "<http://www.example.org/s>",
                                "#p2658" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p2659" to null,
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z"),
                        POPExpression(dictionary, AOPAddition(AOPInteger(10), AOPVariable("o"))),
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
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "p",
                            "o"
                        ), listOf(
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
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z2"),
                        POPExpression(dictionary, AOPAddition(AOPInteger(100), AOPVariable("o"))),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "p",
                                "o"
                            ), listOf(
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
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "z2",
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
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
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z"),
                        POPExpression(dictionary, AOPAddition(AOPInteger(1), AOPVariable("o"))),
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
                        )
                    ),
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
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("nova"),
                        POPExpression(dictionary, AOPUndef()),
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
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "nova",
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "nova" to null,
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "nova" to null,
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "nova" to null,
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "nova" to null,
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z"),
                        POPExpression(dictionary, AOPVariable("nova")),
                        POPValues(dictionary, listOf(
                                "nova",
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "nova" to null,
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "nova" to null,
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "nova" to null,
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "nova" to null,
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "p",
                            "o",
                            "nova"
                        ), listOf(
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "nova" to null
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "nova" to null
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "nova" to null
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "nova" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("o"),
                        POPExpression(dictionary, AOPUndef()),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "o" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z"),
                        POPExpression(dictionary, AOPAddition(AOPInteger(2), AOPVariable("o"))),
                        POPValues(dictionary, listOf(
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "o" to null
                                )
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
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z"),
                        POPExpression(dictionary, AOPAddition(AOPInteger(1), AOPVariable("o"))),
                        POPValues(dictionary, listOf(
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "o" to null
                                )
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
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z"),
                        POPExpression(dictionary, AOPUndef()),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p7865",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p7865" to null,
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p7865" to null,
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p7865" to null,
                                    "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p7865" to null,
                                    "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "#p7865",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s1>",
                                "#p7865" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s2>",
                                "#p7865" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s3>",
                                "#p7865" to null,
                                "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s4>",
                                "#p7865" to null,
                                "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z"),
                        POPExpression(dictionary, AOPUndef()),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p7934",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p7934" to null,
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p7934" to null,
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p7934" to null,
                                    "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p7934" to null,
                                    "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "#p7934",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s1>",
                                "#p7934" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s2>",
                                "#p7934" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s3>",
                                "#p7934" to null,
                                "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s4>",
                                "#p7934" to null,
                                "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z"),
                        POPExpression(dictionary, AOPInteger(4)),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p8059",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p8059" to null,
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p8059" to null,
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p8059" to null,
                                    "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p8059" to null,
                                    "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "#p8059",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s1>",
                                "#p8059" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s2>",
                                "#p8059" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s3>",
                                "#p8059" to null,
                                "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s4>",
                                "#p8059" to null,
                                "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z"),
                        POPExpression(dictionary, AOPInteger(4)),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p8123",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p8123" to null,
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p8123" to null,
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p8123" to null,
                                    "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p8123" to null,
                                    "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "#p8123",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s1>",
                                "#p8123" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s2>",
                                "#p8123" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s3>",
                                "#p8123" to null,
                                "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s4>",
                                "#p8123" to null,
                                "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z"),
                        POPExpression(dictionary, AOPAddition(AOPInteger(10), AOPVariable("o"))),
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
                        )
                    ),
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
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z2"),
                        POPExpression(dictionary, AOPAddition(AOPInteger(100), AOPVariable("o"))),
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
                        )
                    ),
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
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z"),
                        POPExpression(dictionary, AOPAddition(AOPInteger(1), AOPVariable("o"))),
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
                        )
                    ),
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
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("nova"),
                        POPExpression(dictionary, AOPUndef()),
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
                    ),
                    POPValues(dictionary, listOf(
                            "nova",
                            "s",
                            "p",
                            "o",
                            "#p17138",
                            "#o17138"
                        ), listOf(
                            mutableMapOf(
                                "nova" to null,
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17138" to null,
                                "#o17138" to null
                            ),
                            mutableMapOf(
                                "nova" to null,
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17138" to null,
                                "#o17138" to null
                            ),
                            mutableMapOf(
                                "nova" to null,
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17138" to null,
                                "#o17138" to null
                            ),
                            mutableMapOf(
                                "nova" to null,
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17138" to null,
                                "#o17138" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("z"),
                        POPExpression(dictionary, AOPVariable("nova")),
                        POPValues(dictionary, listOf(
                                "nova",
                                "s",
                                "p",
                                "o",
                                "#p17138",
                                "#o17138"
                            ), listOf(
                                mutableMapOf(
                                    "nova" to null,
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17138" to null,
                                    "#o17138" to null
                                ),
                                mutableMapOf(
                                    "nova" to null,
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17138" to null,
                                    "#o17138" to null
                                ),
                                mutableMapOf(
                                    "nova" to null,
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17138" to null,
                                    "#o17138" to null
                                ),
                                mutableMapOf(
                                    "nova" to null,
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17138" to null,
                                    "#o17138" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s",
                            "p",
                            "o",
                            "#p17138",
                            "#o17138",
                            "nova"
                        ), listOf(
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17138" to null,
                                "#o17138" to null,
                                "nova" to null
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17138" to null,
                                "#o17138" to null,
                                "nova" to null
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17138" to null,
                                "#o17138" to null,
                                "nova" to null
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p17138" to null,
                                "#o17138" to null,
                                "nova" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("str1"),
                        POPExpression(dictionary, AOPBuildInCallSTRDT(AOPVariable("str"), AOPIri("http://www.w3.org/2001/XMLSchema#string"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p21717",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p21717" to null,
                                    "str" to "\"bar\"@en"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "str1",
                            "s",
                            "#p21717",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/s2>",
                                "#p21717" to null,
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("str1"),
                        POPExpression(dictionary, AOPBuildInCallSTRDT(AOPVariable("str"), AOPIri("http://www.w3.org/2001/XMLSchema#string"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p21850",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p21850" to null,
                                    "str" to "\"bar\"@en"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "str1",
                            "s",
                            "#p21850",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/s2>",
                                "#p21850" to null,
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("str1"),
                        POPExpression(dictionary, AOPBuildInCallSTRDT(AOPBuildInCallSTR(AOPVariable("str")), AOPIri("http://www.w3.org/2001/XMLSchema#string"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p22044",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p22044" to null,
                                    "str" to "\"bar\"@en"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "str1",
                            "s",
                            "#p22044",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "str1" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s" to "<http://example.org/s2>",
                                "#p22044" to null,
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("str1"),
                        POPExpression(dictionary, AOPBuildInCallSTRDT(AOPBuildInCallSTR(AOPVariable("str")), AOPIri("http://www.w3.org/2001/XMLSchema#string"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p22185",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p22185" to null,
                                    "str" to "\"bar\"@en"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "str1",
                            "s",
                            "#p22185",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "str1" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s" to "<http://example.org/s2>",
                                "#p22185" to null,
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("str1"),
                        POPExpression(dictionary, AOPBuildInCallSTRDT(AOPVariable("o"), AOPIri("http://www.w3.org/2001/XMLSchema#string"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "p" to "<http://example.org/date>",
                                    "o" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "p" to "<http://example.org/date>",
                                    "o" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "p" to "<http://example.org/date>",
                                    "o" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "p" to "<http://example.org/date>",
                                    "o" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n1>",
                                    "p" to "<http://example.org/num>",
                                    "o" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n2>",
                                    "p" to "<http://example.org/num>",
                                    "o" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n3>",
                                    "p" to "<http://example.org/num>",
                                    "o" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n4>",
                                    "p" to "<http://example.org/num>",
                                    "o" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n5>",
                                    "p" to "<http://example.org/num>",
                                    "o" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "str1",
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/d1>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/d2>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/d3>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/d4>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/n1>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/n2>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/n3>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/n4>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/n5>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "str1" to "\"foo\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "str1" to "\"BAZ\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "str1" to "\"食べ物\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "str1" to "\"100%\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"100%\""
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("s2"),
                        POPExpression(dictionary, AOPBuildInCallSTRLANG(AOPVariable("str"), AOPSimpleLiteral("\"", "en-US"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p22557",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p22557" to null,
                                    "str" to "\"bar\"@en"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s2",
                            "s",
                            "#p22557",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s2" to null,
                                "s" to "<http://example.org/s2>",
                                "#p22557" to null,
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("s2"),
                        POPExpression(dictionary, AOPBuildInCallSTRLANG(AOPVariable("str"), AOPSimpleLiteral("\"", "en-US"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p22690",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p22690" to null,
                                    "str" to "\"bar\"@en"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s2",
                            "s",
                            "#p22690",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s2" to null,
                                "s" to "<http://example.org/s2>",
                                "#p22690" to null,
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("s2"),
                        POPExpression(dictionary, AOPBuildInCallSTRLANG(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "en-US"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p22881",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p22881" to null,
                                    "str" to "\"bar\"@en"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s2",
                            "s",
                            "#p22881",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s2" to "\"bar\"@en-us",
                                "s" to "<http://example.org/s2>",
                                "#p22881" to null,
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("s2"),
                        POPExpression(dictionary, AOPBuildInCallSTRLANG(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "en-US"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p23022",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p23022" to null,
                                    "str" to "\"bar\"@en"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s2",
                            "s",
                            "#p23022",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s2" to "\"bar\"@en-us",
                                "s" to "<http://example.org/s2>",
                                "#p23022" to null,
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("str1"),
                        POPExpression(dictionary, AOPBuildInCallSTRLANG(AOPVariable("o"), AOPSimpleLiteral("\"", "en-US"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "p" to "<http://example.org/date>",
                                    "o" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "p" to "<http://example.org/date>",
                                    "o" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "p" to "<http://example.org/date>",
                                    "o" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "p" to "<http://example.org/date>",
                                    "o" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n1>",
                                    "p" to "<http://example.org/num>",
                                    "o" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n2>",
                                    "p" to "<http://example.org/num>",
                                    "o" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n3>",
                                    "p" to "<http://example.org/num>",
                                    "o" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n4>",
                                    "p" to "<http://example.org/num>",
                                    "o" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n5>",
                                    "p" to "<http://example.org/num>",
                                    "o" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "str1",
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/d1>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/d2>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/d3>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/d4>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/n1>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/n2>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/n3>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/n4>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/n5>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "str1" to "\"foo\"@en-us",
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "str1" to "\"BAZ\"@en-us",
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "str1" to "\"食べ物\"@en-us",
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "str1" to "\"100%\"@en-us",
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"100%\""
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str1" to null,
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("ceil"),
                        POPExpression(dictionary, AOPBuildInCallCEIL(AOPVariable("num"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p23692",
                                "num"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/n1>",
                                    "#p23692" to null,
                                    "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n2>",
                                    "#p23692" to null,
                                    "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n3>",
                                    "#p23692" to null,
                                    "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n4>",
                                    "#p23692" to null,
                                    "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n5>",
                                    "#p23692" to null,
                                    "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "ceil",
                            "s",
                            "#p23692",
                            "num"
                        ), listOf(
                            mutableMapOf(
                                "ceil" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/n1>",
                                "#p23692" to null,
                                "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "ceil" to "\"-1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example.org/n2>",
                                "#p23692" to null,
                                "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "ceil" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example.org/n3>",
                                "#p23692" to null,
                                "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "ceil" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/n4>",
                                "#p23692" to null,
                                "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "ceil" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example.org/n5>",
                                "#p23692" to null,
                                "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("floor"),
                        POPExpression(dictionary, AOPBuildInCallFLOOR(AOPVariable("num"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p23788",
                                "num"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/n1>",
                                    "#p23788" to null,
                                    "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n2>",
                                    "#p23788" to null,
                                    "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n3>",
                                    "#p23788" to null,
                                    "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n4>",
                                    "#p23788" to null,
                                    "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n5>",
                                    "#p23788" to null,
                                    "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "floor",
                            "s",
                            "#p23788",
                            "num"
                        ), listOf(
                            mutableMapOf(
                                "floor" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/n1>",
                                "#p23788" to null,
                                "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "floor" to "\"-2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example.org/n2>",
                                "#p23788" to null,
                                "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "floor" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example.org/n3>",
                                "#p23788" to null,
                                "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "floor" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/n4>",
                                "#p23788" to null,
                                "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "floor" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example.org/n5>",
                                "#p23788" to null,
                                "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("round"),
                        POPExpression(dictionary, AOPBuildInCallROUND(AOPVariable("num"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p23884",
                                "num"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/n1>",
                                    "#p23884" to null,
                                    "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n2>",
                                    "#p23884" to null,
                                    "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n3>",
                                    "#p23884" to null,
                                    "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n4>",
                                    "#p23884" to null,
                                    "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n5>",
                                    "#p23884" to null,
                                    "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "round",
                            "s",
                            "#p23884",
                            "num"
                        ), listOf(
                            mutableMapOf(
                                "round" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/n1>",
                                "#p23884" to null,
                                "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "round" to "\"-2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example.org/n2>",
                                "#p23884" to null,
                                "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "round" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example.org/n3>",
                                "#p23884" to null,
                                "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "round" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/n4>",
                                "#p23884" to null,
                                "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "round" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example.org/n5>",
                                "#p23884" to null,
                                "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("str"),
                        POPExpression(dictionary, AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "str",
                            "#s23986",
                            "#p23986",
                            "str1",
                            "#s23987",
                            "#p23987",
                            "str2"
                        ), listOf(
                            mutableMapOf(
                                "str" to "\"abcDEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
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
                    POPBind(
                        dictionary,
                        AOPVariable("str"),
                        POPExpression(dictionary, AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "str",
                            "#s24014",
                            "#p24014",
                            "str1",
                            "#s24015",
                            "#p24015",
                            "str2"
                        ), listOf(
                            mutableMapOf(
                                "str" to "\"abcDEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
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
                    POPBind(
                        dictionary,
                        AOPVariable("str"),
                        POPExpression(dictionary, AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "str",
                            "s1",
                            "#p24101",
                            "str1",
                            "s2",
                            "#p24102",
                            "str2"
                        ), listOf(
                            mutableMapOf(
                                "str" to "\"123123\"",
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "str" to "\"日本語123\"",
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "str" to "\"english123\"",
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "str" to "\"français123\"",
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "str" to "\"abc123\"",
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "str" to "\"def123\"",
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "str" to null,
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "str" to "\"123日本語\"",
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "str" to "\"日本語日本語\"@ja",
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "str" to "\"english日本語\"",
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "str" to "\"français日本語\"",
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "str" to "\"abc日本語\"",
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "str" to "\"def日本語\"",
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "str" to null,
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "str" to "\"123english\"",
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "str" to "\"日本語english\"",
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "str" to "\"englishenglish\"@en",
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "str" to "\"françaisenglish\"",
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "str" to "\"abcenglish\"",
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "str" to "\"defenglish\"",
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "str" to null,
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "str" to "\"123français\"",
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "str" to "\"日本語français\"",
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "str" to "\"englishfrançais\"",
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "str" to "\"françaisfrançais\"@fr",
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "str" to "\"abcfrançais\"",
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "str" to "\"deffrançais\"",
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "str" to null,
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "str" to "\"123abc\"",
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to "\"日本語abc\"",
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to "\"englishabc\"",
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to "\"françaisabc\"",
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to "\"abcabc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to "\"defabc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to null,
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to "\"123def\"",
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to "\"日本語def\"",
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to "\"englishdef\"",
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to "\"françaisdef\"",
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to "\"abcdef\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to "\"defdef\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to null,
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "str" to null,
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "str" to null,
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "str" to null,
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "str" to null,
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "str" to null,
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "str" to null,
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "str" to null,
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
                    POPBind(
                        dictionary,
                        AOPVariable("len"),
                        POPExpression(dictionary, AOPBuildInCallSTRLEN(AOPVariable("str"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p24947",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p24947" to null,
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p24947" to null,
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p24947" to null,
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p24947" to null,
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "#p24947" to null,
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p24947" to null,
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "#p24947" to null,
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "len",
                            "s",
                            "#p24947",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s1>",
                                "#p24947" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s2>",
                                "#p24947" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s3>",
                                "#p24947" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s4>",
                                "#p24947" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "len" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s5>",
                                "#p24947" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s6>",
                                "#p24947" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/s7>",
                                "#p24947" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("ustr"),
                        POPExpression(dictionary, AOPBuildInCallUCASE(AOPVariable("str"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p25056",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p25056" to null,
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p25056" to null,
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p25056" to null,
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p25056" to null,
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "#p25056" to null,
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p25056" to null,
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "#p25056" to null,
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "ustr",
                            "s",
                            "#p25056",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "ustr" to "\"FOO\"",
                                "s" to "<http://example.org/s1>",
                                "#p25056" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "ustr" to "\"BAR\"@en",
                                "s" to "<http://example.org/s2>",
                                "#p25056" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "ustr" to "\"BAZ\"",
                                "s" to "<http://example.org/s3>",
                                "#p25056" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "ustr" to "\"食べ物\"",
                                "s" to "<http://example.org/s4>",
                                "#p25056" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "ustr" to "\"100%\"",
                                "s" to "<http://example.org/s5>",
                                "#p25056" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "ustr" to "\"ABC\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s" to "<http://example.org/s6>",
                                "#p25056" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "ustr" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s" to "<http://example.org/s7>",
                                "#p25056" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("lstr"),
                        POPExpression(dictionary, AOPBuildInCallLCASE(AOPVariable("str"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p25165",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p25165" to null,
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p25165" to null,
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p25165" to null,
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p25165" to null,
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "#p25165" to null,
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p25165" to null,
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "#p25165" to null,
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "lstr",
                            "s",
                            "#p25165",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "lstr" to "\"foo\"",
                                "s" to "<http://example.org/s1>",
                                "#p25165" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "lstr" to "\"bar\"@en",
                                "s" to "<http://example.org/s2>",
                                "#p25165" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "lstr" to "\"baz\"",
                                "s" to "<http://example.org/s3>",
                                "#p25165" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "lstr" to "\"食べ物\"",
                                "s" to "<http://example.org/s4>",
                                "#p25165" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "lstr" to "\"100%\"",
                                "s" to "<http://example.org/s5>",
                                "#p25165" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "lstr" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s" to "<http://example.org/s6>",
                                "#p25165" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "lstr" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s" to "<http://example.org/s7>",
                                "#p25165" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("sum"),
                        POPExpression(dictionary, AOPAddition(AOPVariable("y"), AOPVariable("x"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "s",
                            "#p26389",
                            "x",
                            "#p26390",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x1>",
                                "#p26389" to null,
                                "x" to "\"a\"",
                                "#p26390" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x2>",
                                "#p26389" to null,
                                "x" to "_:b",
                                "#p26390" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x3>",
                                "#p26389" to null,
                                "x" to "<http://example/a>",
                                "#p26390" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example/x5>",
                                "#p26389" to null,
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x6>",
                                "#p26389" to null,
                                "x" to "\"1\"",
                                "#p26390" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x7>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26390" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x8>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("sum"),
                        POPExpression(dictionary, AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x")))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "s",
                            "#p26562",
                            "x",
                            "#p26563",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x1>",
                                "#p26562" to null,
                                "x" to "\"a\"",
                                "#p26563" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x2>",
                                "#p26562" to null,
                                "x" to "_:b",
                                "#p26563" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x3>",
                                "#p26562" to null,
                                "x" to "<http://example/a>",
                                "#p26563" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x4>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x5>",
                                "#p26562" to null,
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x6>",
                                "#p26562" to null,
                                "x" to "\"1\"",
                                "#p26563" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x7>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26563" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x8>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("hash"),
                        POPExpression(dictionary, AOPBuildInCallMD5(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "#s26862",
                                "#p26862",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "#s26862" to null,
                                    "#p26862" to null,
                                    "l" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash",
                            "#s26862",
                            "#p26862",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "hash" to "\"acbd18db4cc2f85cedef654fccc4a4d8\"",
                                "#s26862" to null,
                                "#p26862" to null,
                                "l" to "\"foo\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("hash"),
                        POPExpression(dictionary, AOPBuildInCallMD5(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "#s26880",
                                "#p26880",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "#s26880" to null,
                                    "#p26880" to null,
                                    "l" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash",
                            "#s26880",
                            "#p26880",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "hash" to "\"acbd18db4cc2f85cedef654fccc4a4d8\"",
                                "#s26880" to null,
                                "#p26880" to null,
                                "l" to "\"foo\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("hash"),
                        POPExpression(dictionary, AOPBuildInCallMD5(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "#s26943",
                                "#p26943",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "#s26943" to null,
                                    "#p26943" to null,
                                    "l" to "\"食べ物\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash",
                            "#s26943",
                            "#p26943",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "hash" to "\"e7ada485d13b1decf628c9211bc3a97b\"",
                                "#s26943" to null,
                                "#p26943" to null,
                                "l" to "\"食べ物\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("hash"),
                        POPExpression(dictionary, AOPBuildInCallMD5(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "#s26961",
                                "#p26961",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "#s26961" to null,
                                    "#p26961" to null,
                                    "l" to "\"食べ物\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash",
                            "#s26961",
                            "#p26961",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "hash" to "\"e7ada485d13b1decf628c9211bc3a97b\"",
                                "#s26961" to null,
                                "#p26961" to null,
                                "l" to "\"食べ物\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("hash"),
                        POPExpression(dictionary, AOPBuildInCallSHA1(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "#s27024",
                                "#p27024",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "#s27024" to null,
                                    "#p27024" to null,
                                    "l" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash",
                            "#s27024",
                            "#p27024",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "hash" to "\"0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33\"",
                                "#s27024" to null,
                                "#p27024" to null,
                                "l" to "\"foo\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("hash"),
                        POPExpression(dictionary, AOPBuildInCallSHA1(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "#s27042",
                                "#p27042",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "#s27042" to null,
                                    "#p27042" to null,
                                    "l" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash",
                            "#s27042",
                            "#p27042",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "hash" to "\"0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33\"",
                                "#s27042" to null,
                                "#p27042" to null,
                                "l" to "\"foo\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("hash"),
                        POPExpression(dictionary, AOPBuildInCallSHA1(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "#s27105",
                                "#p27105",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "#s27105" to null,
                                    "#p27105" to null,
                                    "l" to "\"食\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash",
                            "#s27105",
                            "#p27105",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "hash" to "\"d46696735b6a09ff407bfc1a9407e008840db9c9\"",
                                "#s27105" to null,
                                "#p27105" to null,
                                "l" to "\"食\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("hash"),
                        POPExpression(dictionary, AOPBuildInCallSHA1(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "#s27123",
                                "#p27123",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "#s27123" to null,
                                    "#p27123" to null,
                                    "l" to "\"食\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash",
                            "#s27123",
                            "#p27123",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "hash" to "\"d46696735b6a09ff407bfc1a9407e008840db9c9\"",
                                "#s27123" to null,
                                "#p27123" to null,
                                "l" to "\"食\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("hash"),
                        POPExpression(dictionary, AOPBuildInCallSHA256(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "#s27186",
                                "#p27186",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "#s27186" to null,
                                    "#p27186" to null,
                                    "l" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash",
                            "#s27186",
                            "#p27186",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "hash" to "\"2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae\"",
                                "#s27186" to null,
                                "#p27186" to null,
                                "l" to "\"foo\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("hash"),
                        POPExpression(dictionary, AOPBuildInCallSHA256(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "#s27204",
                                "#p27204",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "#s27204" to null,
                                    "#p27204" to null,
                                    "l" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash",
                            "#s27204",
                            "#p27204",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "hash" to "\"2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae\"",
                                "#s27204" to null,
                                "#p27204" to null,
                                "l" to "\"foo\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("hash"),
                        POPExpression(dictionary, AOPBuildInCallSHA256(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "#s27267",
                                "#p27267",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "#s27267" to null,
                                    "#p27267" to null,
                                    "l" to "\"食\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash",
                            "#s27267",
                            "#p27267",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "hash" to "\"0fbe868d1df356ca9df7ebff346da3a56280e059a7ea81186ef885b140d254ee\"",
                                "#s27267" to null,
                                "#p27267" to null,
                                "l" to "\"食\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("hash"),
                        POPExpression(dictionary, AOPBuildInCallSHA256(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "#s27285",
                                "#p27285",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "#s27285" to null,
                                    "#p27285" to null,
                                    "l" to "\"食\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash",
                            "#s27285",
                            "#p27285",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "hash" to "\"0fbe868d1df356ca9df7ebff346da3a56280e059a7ea81186ef885b140d254ee\"",
                                "#s27285" to null,
                                "#p27285" to null,
                                "l" to "\"食\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallMINUTES(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p27404",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p27404" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p27404" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p27404" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p27404" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p27404",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"28\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d1>",
                                "#p27404" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"38\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d2>",
                                "#p27404" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"59\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d3>",
                                "#p27404" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d4>",
                                "#p27404" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallMINUTES(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p27446",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p27446" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p27446" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p27446" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p27446" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p27446",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"28\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d1>",
                                "#p27446" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"38\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d2>",
                                "#p27446" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"59\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d3>",
                                "#p27446" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d4>",
                                "#p27446" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallSECONDS(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p27536",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p27536" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p27536" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p27536" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p27536" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p27536",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example.org/d1>",
                                "#p27536" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example.org/d2>",
                                "#p27536" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"0.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example.org/d3>",
                                "#p27536" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example.org/d4>",
                                "#p27536" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallHOURS(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p27621",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p27621" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p27621" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p27621" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p27621" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p27621",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d1>",
                                "#p27621" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"15\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d2>",
                                "#p27621" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d3>",
                                "#p27621" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d4>",
                                "#p27621" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallHOURS(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p27663",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p27663" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p27663" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p27663" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p27663" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p27663",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d1>",
                                "#p27663" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"15\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d2>",
                                "#p27663" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d3>",
                                "#p27663" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d4>",
                                "#p27663" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallMONTH(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p27753",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p27753" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p27753" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p27753" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p27753" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p27753",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d1>",
                                "#p27753" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d2>",
                                "#p27753" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d3>",
                                "#p27753" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d4>",
                                "#p27753" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallMONTH(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p27795",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p27795" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p27795" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p27795" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p27795" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p27795",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d1>",
                                "#p27795" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d2>",
                                "#p27795" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d3>",
                                "#p27795" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d4>",
                                "#p27795" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallYEAR(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p27885",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p27885" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p27885" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p27885" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p27885" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p27885",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"2010\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d1>",
                                "#p27885" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"2010\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d2>",
                                "#p27885" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"2008\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d3>",
                                "#p27885" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"2011\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d4>",
                                "#p27885" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallYEAR(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p27927",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p27927" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p27927" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p27927" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p27927" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p27927",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"2010\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d1>",
                                "#p27927" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"2010\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d2>",
                                "#p27927" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"2008\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d3>",
                                "#p27927" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"2011\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d4>",
                                "#p27927" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallDAY(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p28017",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p28017" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p28017" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p28017" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p28017" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p28017",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"21\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d1>",
                                "#p28017" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"21\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d2>",
                                "#p28017" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"20\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d3>",
                                "#p28017" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d4>",
                                "#p28017" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallDAY(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p28059",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p28059" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p28059" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p28059" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p28059" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p28059",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"21\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d1>",
                                "#p28059" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"21\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d2>",
                                "#p28059" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"20\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d3>",
                                "#p28059" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example.org/d4>",
                                "#p28059" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallTIMEZONE(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p28149",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p28149" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p28149" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p28149" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p28149" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p28149",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>\"",
                                "s" to "<http://example.org/d1>",
                                "#p28149" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"\"-PT8H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>\"",
                                "s" to "<http://example.org/d2>",
                                "#p28149" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>\"",
                                "s" to "<http://example.org/d3>",
                                "#p28149" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"\"",
                                "s" to "<http://example.org/d4>",
                                "#p28149" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/functions/timezone-01.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallTZ(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p28234",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p28234" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p28234" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p28234" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p28234" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p28234",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"Z\"",
                                "s" to "<http://example.org/d1>",
                                "#p28234" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"-08:00\"",
                                "s" to "<http://example.org/d2>",
                                "#p28234" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"Z\"",
                                "s" to "<http://example.org/d3>",
                                "#p28234" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"\"",
                                "s" to "<http://example.org/d4>",
                                "#p28234" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallTZ(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p28276",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "#p28276" to null,
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "#p28276" to null,
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "#p28276" to null,
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "#p28276" to null,
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "s",
                            "#p28276",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "x" to "\"Z\"",
                                "s" to "<http://example.org/d1>",
                                "#p28276" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"-08:00\"",
                                "s" to "<http://example.org/d2>",
                                "#p28276" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"Z\"",
                                "s" to "<http://example.org/d3>",
                                "#p28276" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "x" to "\"\"",
                                "s" to "<http://example.org/d4>",
                                "#p28276" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("b2"),
                        POPExpression(dictionary, AOPBuildInCallBNODE1(AOPVariable("s2"))),
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
                                    "a" to "<http://example.org/s3>",
                                    "#p28431" to null,
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s1>",
                                    "#p28432" to null,
                                    "s2" to "\"foo\""
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
                                    "a" to "<http://example.org/s3>",
                                    "#p28431" to null,
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s3>",
                                    "#p28432" to null,
                                    "s2" to "\"BAZ\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b2",
                            "a",
                            "#p28431",
                            "s1",
                            "b",
                            "#p28432",
                            "s2"
                        ), listOf(
                            mutableMapOf(
                                "b2" to "_:28386\"foo\"",
                                "a" to "<http://example.org/s1>",
                                "#p28431" to null,
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s1>",
                                "#p28432" to null,
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "b2" to "_:28386\"foo\"",
                                "a" to "<http://example.org/s3>",
                                "#p28431" to null,
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s1>",
                                "#p28432" to null,
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "b2" to "_:28386\"BAZ\"",
                                "a" to "<http://example.org/s1>",
                                "#p28431" to null,
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s3>",
                                "#p28432" to null,
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "b2" to "_:28386\"BAZ\"",
                                "a" to "<http://example.org/s3>",
                                "#p28431" to null,
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s3>",
                                "#p28432" to null,
                                "s2" to "\"BAZ\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("b1"),
                        POPExpression(dictionary, AOPBuildInCallBNODE1(AOPVariable("s1"))),
                        POPValues(dictionary, listOf(
                                "b2",
                                "a",
                                "#p28431",
                                "s1",
                                "b",
                                "#p28432",
                                "s2"
                            ), listOf(
                                mutableMapOf(
                                    "b2" to "_:28386\"foo\"",
                                    "a" to "<http://example.org/s1>",
                                    "#p28431" to null,
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s1>",
                                    "#p28432" to null,
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "b2" to "_:28386\"foo\"",
                                    "a" to "<http://example.org/s3>",
                                    "#p28431" to null,
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s1>",
                                    "#p28432" to null,
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "b2" to "_:28386\"BAZ\"",
                                    "a" to "<http://example.org/s1>",
                                    "#p28431" to null,
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s3>",
                                    "#p28432" to null,
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "b2" to "_:28386\"BAZ\"",
                                    "a" to "<http://example.org/s3>",
                                    "#p28431" to null,
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s3>",
                                    "#p28432" to null,
                                    "s2" to "\"BAZ\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b1",
                            "a",
                            "#p28431",
                            "s1",
                            "b",
                            "#p28432",
                            "s2",
                            "b2"
                        ), listOf(
                            mutableMapOf(
                                "b1" to "_:28380\"foo\"",
                                "a" to "<http://example.org/s1>",
                                "#p28431" to null,
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s1>",
                                "#p28432" to null,
                                "s2" to "\"foo\"",
                                "b2" to "_:28386\"foo\""
                            ),
                            mutableMapOf(
                                "b1" to "_:28380\"BAZ\"",
                                "a" to "<http://example.org/s3>",
                                "#p28431" to null,
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s1>",
                                "#p28432" to null,
                                "s2" to "\"foo\"",
                                "b2" to "_:28386\"foo\""
                            ),
                            mutableMapOf(
                                "b1" to "_:28380\"foo\"",
                                "a" to "<http://example.org/s1>",
                                "#p28431" to null,
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s3>",
                                "#p28432" to null,
                                "s2" to "\"BAZ\"",
                                "b2" to "_:28386\"BAZ\""
                            ),
                            mutableMapOf(
                                "b1" to "_:28380\"BAZ\"",
                                "a" to "<http://example.org/s3>",
                                "#p28431" to null,
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s3>",
                                "#p28432" to null,
                                "s2" to "\"BAZ\"",
                                "b2" to "_:28386\"BAZ\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("b2"),
                        POPExpression(dictionary, AOPBuildInCallBNODE0()),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b2"
                        ), listOf(
                            mutableMapOf(
                                "b2" to "_:3119331218"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("b1"),
                        POPExpression(dictionary, AOPBuildInCallBNODE0()),
                        POPValues(dictionary, listOf(
                                "b2"
                            ), listOf(
                                mutableMapOf(
                                    "b2" to "_:3119331218"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b1",
                            "b2"
                        ), listOf(
                            mutableMapOf(
                                "b1" to "_:3118831220",
                                "b2" to "_:3119331218"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("b2"),
                        POPExpression(dictionary, AOPBuildInCallBNODE0()),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b2"
                        ), listOf(
                            mutableMapOf(
                                "b2" to "_:3122431242"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("b1"),
                        POPExpression(dictionary, AOPBuildInCallBNODE0()),
                        POPValues(dictionary, listOf(
                                "b2"
                            ), listOf(
                                mutableMapOf(
                                    "b2" to "_:3122431242"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b1",
                            "b2"
                        ), listOf(
                            mutableMapOf(
                                "b1" to "_:3123131244",
                                "b2" to "_:3122431242"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("n"),
                        POPExpression(dictionary, AOPDateTime("\"2020-02-24T19:33:13Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>")),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "n"
                        ), listOf(
                            mutableMapOf(
                                "n" to "\"2020-02-24T19:33:13Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/now01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("iri"),
                        POPExpression(dictionary, AOPBuildInCallIRI(AOPSimpleLiteral("\"", "iri"), "http://example.org/")),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "iri"
                        ), listOf(
                            mutableMapOf(
                                "iri" to "<http://example.org/iri>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/iri01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("uri"),
                        POPExpression(dictionary, AOPBuildInCallURI(AOPSimpleLiteral("\"", "uri"), "http://example.org/")),
                        POPValues(dictionary, listOf(
                                "iri"
                            ), listOf(
                                mutableMapOf(
                                    "iri" to "<http://example.org/iri>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "uri",
                            "iri"
                        ), listOf(
                            mutableMapOf(
                                "uri" to "<http://example.org/uri>",
                                "iri" to "<http://example.org/iri>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/iri01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("integer"),
                        POPExpression(dictionary, AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false))),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"123\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "p" to "<http://example.org/str>",
                                    "o" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "integer",
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"123\""
                            ),
                            mutableMapOf(
                                "integer" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("error"),
                        POPExpression(dictionary, AOPBuildInCallIF(AOPDivision(AOPInteger(0), AOPInteger(1)), AOPBoolean(false), AOPBoolean(true))),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "error"
                        ), listOf(
                            mutableMapOf(
                                "error" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/if02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("eq"),
                        POPExpression(dictionary, AOPEQ(AOPVariable("y"), AOPVariable("z"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "eq",
                            "x",
                            "#p34833",
                            "y",
                            "#p34834",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "eq" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34833" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34834" to null,
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "eq" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34833" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34834" to null,
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("eq"),
                        POPExpression(dictionary, AOPEQ(AOPVariable("y"), AOPVariable("z"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "eq",
                            "x",
                            "#p34872",
                            "y",
                            "#p34873",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "eq" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34872" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34873" to null,
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "eq" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34872" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34873" to null,
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("sum"),
                        POPExpression(dictionary, AOPAddition(AOPVariable("z"), AOPVariable("y"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "x",
                            "#p34982",
                            "y",
                            "#p34983",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34982" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34983" to null,
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34982" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p34983" to null,
                                "z" to "\"foobar\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("sum"),
                        POPExpression(dictionary, AOPAddition(AOPVariable("z"), AOPVariable("y"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "x",
                            "#p35010",
                            "y",
                            "#p35011",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35010" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35011" to null,
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35010" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35011" to null,
                                "z" to "\"foobar\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("sum"),
                        POPExpression(dictionary, AOPAddition(AOPVariable("z"), AOPVariable("y"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "x",
                            "#p35122",
                            "y",
                            "#p35123",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35122" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35123" to null,
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("twice"),
                        POPExpression(dictionary, AOPMultiplication(AOPVariable("sum"), AOPInteger(2))),
                        POPValues(dictionary, listOf(
                                "sum",
                                "x",
                                "#p35122",
                                "y",
                                "#p35123",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35122" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35123" to null,
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "twice",
                            "x",
                            "#p35122",
                            "y",
                            "#p35123",
                            "z",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "twice" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35122" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35123" to null,
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("sum"),
                        POPExpression(dictionary, AOPAddition(AOPVariable("z"), AOPVariable("y"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "x",
                            "#p35167",
                            "y",
                            "#p35168",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35167" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35168" to null,
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("twice"),
                        POPExpression(dictionary, AOPMultiplication(AOPVariable("sum"), AOPInteger(2))),
                        POPValues(dictionary, listOf(
                                "sum",
                                "x",
                                "#p35167",
                                "y",
                                "#p35168",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35167" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35168" to null,
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "twice",
                            "x",
                            "#p35167",
                            "y",
                            "#p35168",
                            "z",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "twice" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35167" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35168" to null,
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("sum"),
                        POPExpression(dictionary, AOPAddition(AOPVariable("y"), AOPVariable("y"))),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35280",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35280" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35280" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "x",
                            "#p35280",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35280" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35280" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("sum"),
                        POPExpression(dictionary, AOPAddition(AOPVariable("y"), AOPVariable("y"))),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35310",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35310" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35310" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "x",
                            "#p35310",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35310" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35310" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("dt"),
                        POPExpression(dictionary, AOPBuildInCallDATATYPE(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35396",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35396" to null,
                                    "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35396" to null,
                                    "l" to "<http://www.example.org/schema#a>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "dt",
                            "x",
                            "#p35396",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "dt" to "<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35396" to null,
                                "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "dt" to null,
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35396" to null,
                                "l" to "<http://www.example.org/schema#a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("dt"),
                        POPExpression(dictionary, AOPBuildInCallDATATYPE(AOPVariable("l"))),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35421",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35421" to null,
                                    "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35421" to null,
                                    "l" to "<http://www.example.org/schema#a>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "dt",
                            "x",
                            "#p35421",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "dt" to "<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35421" to null,
                                "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "dt" to null,
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35421" to null,
                                "l" to "<http://www.example.org/schema#a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("m"),
                        POPExpression(dictionary, AOPUndef()),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35506",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35506" to null,
                                    "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "m",
                            "x",
                            "#p35506",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "m" to null,
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35506" to null,
                                "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("dt"),
                        POPExpression(dictionary, AOPBuildInCallDATATYPE(AOPVariable("m"))),
                        POPValues(dictionary, listOf(
                                "m",
                                "x",
                                "#p35506",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "m" to null,
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35506" to null,
                                    "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "dt",
                            "x",
                            "#p35506",
                            "l",
                            "m"
                        ), listOf(
                            mutableMapOf(
                                "dt" to null,
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35506" to null,
                                "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "m" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("m"),
                        POPExpression(dictionary, AOPUndef()),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p35530",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35530" to null,
                                    "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "m",
                            "x",
                            "#p35530",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "m" to null,
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35530" to null,
                                "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("dt"),
                        POPExpression(dictionary, AOPBuildInCallDATATYPE(AOPVariable("m"))),
                        POPValues(dictionary, listOf(
                                "m",
                                "x",
                                "#p35530",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "m" to null,
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35530" to null,
                                    "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "dt",
                            "x",
                            "#p35530",
                            "l",
                            "m"
                        ), listOf(
                            mutableMapOf(
                                "dt" to null,
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35530" to null,
                                "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "m" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("dt"),
                        POPExpression(dictionary, AOPBuildInCallDATATYPE(AOPVariable("l"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "dt",
                            "x",
                            "#p35626",
                            "y",
                            "#p35627",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "dt" to "<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35626" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35627" to null,
                                "l" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "dt" to null,
                                "x" to "<http://www.example.org/instance#b>",
                                "#p35626" to null,
                                "y" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p35627" to null,
                                "l" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("FullName"),
                        POPExpression(dictionary, AOPBuildInCallCONCAT(AOPBuildInCallCONCAT(AOPVariable("F"), AOPSimpleLiteral("\"", " ")), AOPVariable("L"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "FullName",
                            "P",
                            "#p37177",
                            "F",
                            "#p37178",
                            "L"
                        ), listOf(
                            mutableMapOf(
                                "FullName" to "\"John Doe\"",
                                "P" to "<http://p1>",
                                "#p37177" to null,
                                "F" to "\"John\"",
                                "#p37178" to null,
                                "L" to "\"Doe\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("P"),
                        POPExpression(dictionary, AOPUndef()),
                        POPValues(dictionary, listOf(
                                "s",
                                "FullName"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://p1>",
                                    "FullName" to "\"John Doe\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "FullName",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "P" to null,
                                "FullName" to "\"John Doe\"",
                                "s" to "<http://p1>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("p"),
                        POPExpression(dictionary, AOPIri("http://xmlns.com/foaf/0.1/name")),
                        POPValues(dictionary, listOf(
                                "P",
                                "FullName",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "P" to null,
                                    "FullName" to "\"John Doe\"",
                                    "s" to "<http://p1>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "p",
                            "FullName",
                            "s",
                            "P"
                        ), listOf(
                            mutableMapOf(
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "FullName" to "\"John Doe\"",
                                "s" to "<http://p1>",
                                "P" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("FullName"),
                        POPExpression(dictionary, AOPBuildInCallCONCAT(AOPBuildInCallCONCAT(AOPVariable("F"), AOPSimpleLiteral("\"", " ")), AOPVariable("L"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "FullName",
                            "P",
                            "#p37232",
                            "F",
                            "#p37233",
                            "L"
                        ), listOf(
                            mutableMapOf(
                                "FullName" to "\"John Doe\"",
                                "P" to "<http://p1>",
                                "#p37232" to null,
                                "F" to "\"John\"",
                                "#p37233" to null,
                                "L" to "\"Doe\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
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
