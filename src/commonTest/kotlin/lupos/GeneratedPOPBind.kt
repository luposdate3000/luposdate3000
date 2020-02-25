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
    constructor() {
        P2P.knownClients.clear()
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
                                "S",
                                "O1",
                                "O2"
                            ), listOf(
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "<http://www.example.org/s>",
                                    "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "O1",
                            "O2",
                            "O12"
                        ), listOf(
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "s",
                                "p",
                                "o",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z",
                            "z2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z2" to "\"101\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z2" to "\"102\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z2" to "\"103\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z2" to "\"104\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                            "s",
                            "p",
                            "o",
                            "nova"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "nova" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "nova" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "nova" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
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
                        AOPVariable("z"),
                        POPExpression(dictionary, AOPVariable("nova")),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "nova"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "nova" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "nova" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "nova" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "nova" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "nova",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "nova" to null,
                                "z" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "nova" to null,
                                "z" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "nova" to null,
                                "z" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "nova" to null,
                                "z" to null
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
                            "o",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "o" to null,
                                "z" to null
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
                            "o",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "o" to null,
                                "z" to null
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
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to null
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
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/p>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "z" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "_:1",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>",
                                "z" to null
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
                                "s",
                                "p",
                                "o",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/p>",
                                    "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "z" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "_:1",
                                    "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "o" to "<http://www.w3.org/2002/07/owl#Ontology>",
                                    "z" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z",
                            "z2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/p>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "z" to null,
                                "z2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z2" to "\"101\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z2" to "\"102\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z2" to "\"103\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z2" to "\"104\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "_:1",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>",
                                "z" to null,
                                "z2" to null
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
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/p>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                "z" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "_:1",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>",
                                "z" to null
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
                        AOPVariable("str1"),
                        POPExpression(dictionary, AOPBuildInCallSTRDT(AOPVariable("str"), AOPIri("http://www.w3.org/2001/XMLSchema#string"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "str" to "\"bar\"@en"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "str1"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "str" to "\"bar\"@en",
                                "str1" to null
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
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "str" to "\"bar\"@en"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "str1"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "str" to "\"bar\"@en",
                                "str1" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
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
                            "s",
                            "p",
                            "o",
                            "str1"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n1>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n2>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n3>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n4>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n5>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"foo\"",
                                "str1" to "\"foo\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"bar\"@en",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"BAZ\"",
                                "str1" to "\"BAZ\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"食べ物\"",
                                "str1" to "\"食べ物\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"100%\"",
                                "str1" to "\"100%\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str1" to null
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
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "str" to "\"bar\"@en"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "s2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "str" to "\"bar\"@en",
                                "s2" to null
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
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "str" to "\"bar\"@en"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "s2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "str" to "\"bar\"@en",
                                "s2" to "\"bar\"@en-us"
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
                            "s",
                            "p",
                            "o",
                            "str1"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n1>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n2>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n3>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n4>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n5>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"foo\"",
                                "str1" to "\"foo\"@en-us"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"bar\"@en",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"BAZ\"",
                                "str1" to "\"BAZ\"@en-us"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"食べ物\"",
                                "str1" to "\"食べ物\"@en-us"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"100%\"",
                                "str1" to "\"100%\"@en-us"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str1" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str1" to null
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
                                "num"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/n1>",
                                    "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n2>",
                                    "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n3>",
                                    "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n4>",
                                    "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n5>",
                                    "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "num",
                            "ceil"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/n1>",
                                "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "ceil" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n2>",
                                "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "ceil" to "\"-1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n3>",
                                "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "ceil" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n4>",
                                "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "ceil" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n5>",
                                "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "ceil" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
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
                                "num"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/n1>",
                                    "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n2>",
                                    "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n3>",
                                    "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n4>",
                                    "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n5>",
                                    "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "num",
                            "floor"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/n1>",
                                "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "floor" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n2>",
                                "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "floor" to "\"-2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n3>",
                                "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "floor" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n4>",
                                "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "floor" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n5>",
                                "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "floor" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
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
                                "num"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/n1>",
                                    "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n2>",
                                    "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n3>",
                                    "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n4>",
                                    "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n5>",
                                    "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "num",
                            "round"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/n1>",
                                "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "round" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n2>",
                                "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "round" to "\"-2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n3>",
                                "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "round" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n4>",
                                "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "round" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n5>",
                                "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "round" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
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
                                "str1",
                                "str2"
                            ), listOf(
                                mutableMapOf(
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "str1",
                            "str2",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to "\"abcDEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
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
                                "str1",
                                "s2",
                                "str2"
                            ), listOf(
                                mutableMapOf(
                                    "s1" to "<http://example.org/s1>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s1>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s2>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s1>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s3>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s1>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s4>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s1>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s5>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s1>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s6>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s1>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s7>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s1>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s1>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s2>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s2>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s2>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s3>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s2>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s4>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s2>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s5>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s2>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s6>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s2>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s7>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s2>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s1>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s3>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s2>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s3>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s3>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s3>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s4>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s3>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s5>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s3>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s6>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s3>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s7>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s3>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s1>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s4>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s2>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s4>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s3>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s4>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s4>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s4>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s5>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s4>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s6>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s4>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s7>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s4>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s1>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s5>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s2>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s5>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s3>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s5>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s4>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s5>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s5>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s5>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s6>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s5>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s7>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s5>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s1>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s6>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s2>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s6>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s3>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s6>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s4>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s6>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s5>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s6>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s6>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s6>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s7>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s6>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s1>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s7>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s2>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s7>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s3>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s7>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s4>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s7>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s5>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s7>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s6>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s7>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s1" to "<http://example.org/s7>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s7>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s1",
                            "str1",
                            "s2",
                            "str2",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s1>",
                                "str2" to "\"123\"",
                                "str" to "\"123123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s1>",
                                "str2" to "\"123\"",
                                "str" to "\"日本語123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s1>",
                                "str2" to "\"123\"",
                                "str" to "\"english123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s1>",
                                "str2" to "\"123\"",
                                "str" to "\"français123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s1>",
                                "str2" to "\"123\"",
                                "str" to "\"abc123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s1>",
                                "str2" to "\"123\"",
                                "str" to "\"def123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s1>",
                                "str2" to "\"123\"",
                                "str" to null
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s2>",
                                "str2" to "\"日本語\"@ja",
                                "str" to "\"123日本語\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s2>",
                                "str2" to "\"日本語\"@ja",
                                "str" to "\"日本語日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s2>",
                                "str2" to "\"日本語\"@ja",
                                "str" to "\"english日本語\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s2>",
                                "str2" to "\"日本語\"@ja",
                                "str" to "\"français日本語\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s2>",
                                "str2" to "\"日本語\"@ja",
                                "str" to "\"abc日本語\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s2>",
                                "str2" to "\"日本語\"@ja",
                                "str" to "\"def日本語\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s2>",
                                "str2" to "\"日本語\"@ja",
                                "str" to null
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s3>",
                                "str2" to "\"english\"@en",
                                "str" to "\"123english\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s3>",
                                "str2" to "\"english\"@en",
                                "str" to "\"日本語english\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s3>",
                                "str2" to "\"english\"@en",
                                "str" to "\"englishenglish\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s3>",
                                "str2" to "\"english\"@en",
                                "str" to "\"françaisenglish\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s3>",
                                "str2" to "\"english\"@en",
                                "str" to "\"abcenglish\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s3>",
                                "str2" to "\"english\"@en",
                                "str" to "\"defenglish\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s3>",
                                "str2" to "\"english\"@en",
                                "str" to null
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s4>",
                                "str2" to "\"français\"@fr",
                                "str" to "\"123français\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s4>",
                                "str2" to "\"français\"@fr",
                                "str" to "\"日本語français\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s4>",
                                "str2" to "\"français\"@fr",
                                "str" to "\"englishfrançais\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s4>",
                                "str2" to "\"français\"@fr",
                                "str" to "\"françaisfrançais\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s4>",
                                "str2" to "\"français\"@fr",
                                "str" to "\"abcfrançais\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s4>",
                                "str2" to "\"français\"@fr",
                                "str" to "\"deffrançais\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s4>",
                                "str2" to "\"français\"@fr",
                                "str" to null
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s5>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to "\"123abc\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s5>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to "\"日本語abc\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s5>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to "\"englishabc\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s5>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to "\"françaisabc\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s5>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to "\"abcabc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s5>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to "\"defabc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s5>",
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to null
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s6>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to "\"123def\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s6>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to "\"日本語def\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s6>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to "\"englishdef\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s6>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to "\"françaisdef\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s6>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to "\"abcdef\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s6>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to "\"defdef\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s6>",
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "str" to null
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "str1" to "\"123\"",
                                "s2" to "<http://example.org/s7>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "str" to null
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "str1" to "\"日本語\"@ja",
                                "s2" to "<http://example.org/s7>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "str" to null
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "str1" to "\"english\"@en",
                                "s2" to "<http://example.org/s7>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "str" to null
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "str1" to "\"français\"@fr",
                                "s2" to "<http://example.org/s7>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "str" to null
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s7>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "str" to null
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "s2" to "<http://example.org/s7>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "str" to null
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s2" to "<http://example.org/s7>",
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "str" to null
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
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "len"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "str" to "\"foo\"",
                                "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "str" to "\"bar\"@en",
                                "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "str" to "\"BAZ\"",
                                "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "str" to "\"食べ物\"",
                                "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "str" to "\"100%\"",
                                "len" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "ustr"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "str" to "\"foo\"",
                                "ustr" to "\"FOO\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "str" to "\"bar\"@en",
                                "ustr" to "\"BAR\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "str" to "\"BAZ\"",
                                "ustr" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "str" to "\"食べ物\"",
                                "ustr" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "str" to "\"100%\"",
                                "ustr" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "ustr" to "\"ABC\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "ustr" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
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
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "lstr"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "str" to "\"foo\"",
                                "lstr" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "str" to "\"bar\"@en",
                                "lstr" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "str" to "\"BAZ\"",
                                "lstr" to "\"baz\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "str" to "\"食べ物\"",
                                "lstr" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "str" to "\"100%\"",
                                "lstr" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "lstr" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "lstr" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
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
                                "s",
                                "x",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "x" to "\"a\"",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "x" to "_:b",
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "x" to "<http://example/a>",
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "x" to "\"1\"",
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
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
                                "s",
                                "x",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "x" to "\"a\"",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "x" to "_:b",
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "x" to "<http://example/a>",
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "x" to "\"1\"",
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
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
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "l" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            mutableMapOf(
                                "l" to "\"foo\"",
                                "hash" to "\"acbd18db4cc2f85cedef654fccc4a4d8\""
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
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "l" to "\"食べ物\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            mutableMapOf(
                                "l" to "\"食べ物\"",
                                "hash" to "\"e7ada485d13b1decf628c9211bc3a97b\""
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
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "l" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            mutableMapOf(
                                "l" to "\"foo\"",
                                "hash" to "\"0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33\""
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
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "l" to "\"食\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            mutableMapOf(
                                "l" to "\"食\"",
                                "hash" to "\"d46696735b6a09ff407bfc1a9407e008840db9c9\""
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
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "l" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            mutableMapOf(
                                "l" to "\"foo\"",
                                "hash" to "\"2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae\""
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
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "l" to "\"食\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            mutableMapOf(
                                "l" to "\"食\"",
                                "hash" to "\"0fbe868d1df356ca9df7ebff346da3a56280e059a7ea81186ef885b140d254ee\""
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
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"28\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"38\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"59\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"0.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
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
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"15\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"2010\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"2010\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"2008\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"2011\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"21\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"21\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"20\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallTIMEZONE(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"\"-PT8H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"\""
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/functions/timezone-01.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        POPExpression(dictionary, AOPBuildInCallTZ(AOPVariable("date"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"Z\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"-08:00\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"Z\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>",
                                "x" to "\"\""
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
                                "s1",
                                "b",
                                "s2"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/s1>",
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s1>",
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s3>",
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s1>",
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s1>",
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s3>",
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "s1",
                            "b",
                            "s2",
                            "b2"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s1>",
                                "s2" to "\"foo\"",
                                "b2" to "_:27172\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s1>",
                                "s2" to "\"foo\"",
                                "b2" to "_:27172\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s3>",
                                "s2" to "\"BAZ\"",
                                "b2" to "_:27172\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s3>",
                                "s2" to "\"BAZ\"",
                                "b2" to "_:27172\"BAZ\""
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
                                "a",
                                "s1",
                                "b",
                                "s2",
                                "b2"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/s1>",
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s1>",
                                    "s2" to "\"foo\"",
                                    "b2" to "_:27172\"foo\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s3>",
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s1>",
                                    "s2" to "\"foo\"",
                                    "b2" to "_:27172\"foo\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s1>",
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\"",
                                    "b2" to "_:27172\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s3>",
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\"",
                                    "b2" to "_:27172\"BAZ\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "s1",
                            "b",
                            "s2",
                            "b2",
                            "b1"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s1>",
                                "s2" to "\"foo\"",
                                "b2" to "_:27172\"foo\"",
                                "b1" to "_:27166\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s1>",
                                "s2" to "\"foo\"",
                                "b2" to "_:27172\"foo\"",
                                "b1" to "_:27166\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "s1" to "\"foo\"",
                                "b" to "<http://example.org/s3>",
                                "s2" to "\"BAZ\"",
                                "b2" to "_:27172\"BAZ\"",
                                "b1" to "_:27166\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s3>",
                                "s2" to "\"BAZ\"",
                                "b2" to "_:27172\"BAZ\"",
                                "b1" to "_:27166\"BAZ\""
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
                                "b2" to "_:2918829213"
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
                                    "b2" to "_:2918829213"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b2",
                            "b1"
                        ), listOf(
                            mutableMapOf(
                                "b2" to "_:2918829213",
                                "b1" to "_:2918329215"
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
                                "b2" to "_:2921929237"
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
                                    "b2" to "_:2921929237"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b2",
                            "b1"
                        ), listOf(
                            mutableMapOf(
                                "b2" to "_:2921929237",
                                "b1" to "_:2922629239"
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
                        POPExpression(dictionary, AOPDateTime("\"2020-02-25T06:30:50Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>")),
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
                                "n" to "\"2020-02-25T06:30:50Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
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
                            "iri",
                            "uri"
                        ), listOf(
                            mutableMapOf(
                                "iri" to "<http://example.org/iri>",
                                "uri" to "<http://example.org/uri>"
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
                            "s",
                            "p",
                            "o",
                            "integer"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"123\"",
                                "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"日本語\"@ja",
                                "integer" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"english\"@en",
                                "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"français\"@fr",
                                "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
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
                                "x",
                                "y",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "eq"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "eq" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "eq" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
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
                                "x",
                                "y",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"foobar\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"foobar\"",
                                "sum" to null
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
                                "x",
                                "y",
                                "z"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
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
                        AOPVariable("twice"),
                        POPExpression(dictionary, AOPMultiplication(AOPVariable("sum"), AOPInteger(2))),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "sum"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "sum",
                            "twice"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "twice" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "l" to "<http://www.example.org/schema#a>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "l",
                            "dt"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "dt" to "<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "l" to "<http://www.example.org/schema#a>",
                                "dt" to null
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
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "l",
                            "m"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
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
                        POPExpression(dictionary, AOPBuildInCallDATATYPE(AOPVariable("m"))),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "m"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "m" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "l",
                            "m",
                            "dt"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "m" to null,
                                "dt" to null
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
                                "x",
                                "y",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "l" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#b>",
                                    "y" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "l" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "l",
                            "dt"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "l" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "dt" to "<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#b>",
                                "y" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "l" to null,
                                "dt" to null
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
                                "P",
                                "F",
                                "L"
                            ), listOf(
                                mutableMapOf(
                                    "P" to "<http://p1>",
                                    "F" to "\"John\"",
                                    "L" to "\"Doe\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "F",
                            "L",
                            "FullName"
                        ), listOf(
                            mutableMapOf(
                                "P" to "<http://p1>",
                                "F" to "\"John\"",
                                "L" to "\"Doe\"",
                                "FullName" to "\"John Doe\""
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
                                "FullName",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "FullName" to "\"John Doe\"",
                                    "s" to "<http://p1>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "FullName",
                            "s",
                            "P"
                        ), listOf(
                            mutableMapOf(
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
                        AOPVariable("p"),
                        POPExpression(dictionary, AOPIri("http://xmlns.com/foaf/0.1/name")),
                        POPValues(dictionary, listOf(
                                "FullName",
                                "s",
                                "P"
                            ), listOf(
                                mutableMapOf(
                                    "FullName" to "\"John Doe\"",
                                    "s" to "<http://p1>",
                                    "P" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "FullName",
                            "s",
                            "P",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "FullName" to "\"John Doe\"",
                                "s" to "<http://p1>",
                                "P" to null,
                                "p" to "<http://xmlns.com/foaf/0.1/name>"
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
