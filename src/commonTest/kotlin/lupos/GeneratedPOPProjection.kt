package lupos

import lupos.s00misc.*
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
import lupos.s12p2p.P2P
import lupos.s14endpoint.EndpointImpl
import lupos.s15tripleStoreDistributed.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


class GeneratedPOPProjectionTest {
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
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("C")
                                ),
                                POPValues(dictionary, listOf(
                                        "C"
                                ), listOf(
                                        mutableMapOf(
                                                "C" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "C"
                        ), listOf(
                                mutableMapOf(
                                        "C" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("P"),
                                        AOPVariable("C")
                                ),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "C"
                                ), listOf(
                                        mutableMapOf(
                                                "P" to "<http://www.example.org/p1>",
                                                "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "P" to "<http://www.example.org/p2>",
                                                "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "C"
                        ), listOf(
                                mutableMapOf(
                                        "P" to "<http://www.example.org/p1>",
                                        "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "P" to "<http://www.example.org/p2>",
                                        "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("P"),
                                        AOPVariable("C")
                                ),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "#f1162",
                                        "C"
                                ), listOf(
                                        mutableMapOf(
                                                "P" to "<http://www.example.org/p1>",
                                                "#f1162" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                                "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "C"
                        ), listOf(
                                mutableMapOf(
                                        "P" to "<http://www.example.org/p1>",
                                        "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("C")
                                ),
                                POPValues(dictionary, listOf(
                                        "#f1745",
                                        "C"
                                ), listOf(
                                        mutableMapOf(
                                                "#f1745" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                                "C" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "C"
                        ), listOf(
                                mutableMapOf(
                                        "C" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("P"),
                                        AOPVariable("C")
                                ),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "#f2020",
                                        "C"
                                ), listOf(
                                        mutableMapOf(
                                                "P" to "<http://www.example.org/p1>",
                                                "#f2020" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                                "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "C"
                        ), listOf(
                                mutableMapOf(
                                        "P" to "<http://www.example.org/p1>",
                                        "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("O12"),
                                        AOPVariable("C")
                                ),
                                POPValues(dictionary, listOf(
                                        "O12",
                                        "C"
                                ), listOf(
                                        mutableMapOf(
                                                "O12" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "O12" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "O12" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "O12" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "O12",
                                "C"
                        ), listOf(
                                mutableMapOf(
                                        "O12" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "O12" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "O12" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "O12" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("sum")
                                ),
                                POPValues(dictionary, listOf(
                                        "sum"
                                ), listOf(
                                        mutableMapOf(
                                                "sum" to "\"11.100000000000001\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "sum"
                        ), listOf(
                                mutableMapOf(
                                        "sum" to "\"11.100000000000001\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("sum")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "sum"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/decimals>",
                                                "sum" to "\"6.7\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/doubles>",
                                                "sum" to "\"32100.0\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/ints>",
                                                "sum" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/mixed1>",
                                                "sum" to "\"3.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/mixed2>",
                                                "sum" to "\"0.4\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "sum"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://www.example.org/decimals>",
                                        "sum" to "\"6.7\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/doubles>",
                                        "sum" to "\"32100.0\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/ints>",
                                        "sum" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/mixed1>",
                                        "sum" to "\"3.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/mixed2>",
                                        "sum" to "\"0.4\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("avg")
                                ),
                                POPValues(dictionary, listOf(
                                        "avg"
                                ), listOf(
                                        mutableMapOf(
                                                "avg" to "\"2.22\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "avg"
                        ), listOf(
                                mutableMapOf(
                                        "avg" to "\"2.22\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("avg")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#f3633",
                                        "avg"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/ints>",
                                                "#f3633" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                                "avg" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/mixed1>",
                                                "#f3633" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                                "avg" to "\"1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/mixed2>",
                                                "#f3633" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                                "avg" to "\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "avg"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://www.example.org/ints>",
                                        "avg" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/mixed1>",
                                        "avg" to "\"1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/mixed2>",
                                        "avg" to "\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("min")
                                ),
                                POPValues(dictionary, listOf(
                                        "min"
                                ), listOf(
                                        mutableMapOf(
                                                "min" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "min"
                        ), listOf(
                                mutableMapOf(
                                        "min" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("min")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "min"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/decimals>",
                                                "min" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/doubles>",
                                                "min" to "\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/ints>",
                                                "min" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/mixed1>",
                                                "min" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/mixed2>",
                                                "min" to "\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "min"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://www.example.org/decimals>",
                                        "min" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/doubles>",
                                        "min" to "\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/ints>",
                                        "min" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/mixed1>",
                                        "min" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/mixed2>",
                                        "min" to "\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("max")
                                ),
                                POPValues(dictionary, listOf(
                                        "max"
                                ), listOf(
                                        mutableMapOf(
                                                "max" to "\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "max"
                        ), listOf(
                                mutableMapOf(
                                        "max" to "\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("max")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "max"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/decimals>",
                                                "max" to "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/doubles>",
                                                "max" to "\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/ints>",
                                                "max" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/mixed1>",
                                                "max" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://www.example.org/mixed2>",
                                                "max" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "max"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://www.example.org/decimals>",
                                        "max" to "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/doubles>",
                                        "max" to "\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/ints>",
                                        "max" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/mixed1>",
                                        "max" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://www.example.org/mixed2>",
                                        "max" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */,
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("sample")
                        ),
                        POPValues(dictionary, listOf(
                                "sample"
                            ), listOf(
                                mutableMapOf(
                                    "sample" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sample"
                        ), listOf(
                            mutableMapOf(
                                "sample" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/aggregates/agg-sample-01.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("g"),
                            AOPVariable("avg"),
                            AOPVariable("c")
                        ),
                        POPValues(dictionary, listOf(
                                "g",
                                "c",
                                "avg"
                            ), listOf(
                                mutableMapOf(
                                    "g" to "<http://example.com/data/#x>",
                                    "c" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "avg" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "g" to "<http://example.com/data/#y>",
                                    "c" to null,
                                    "avg" to null
                                ),
                                mutableMapOf(
                                    "g" to "<http://example.com/data/#z>",
                                    "c" to "\"0.4\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "avg" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "g",
                            "avg",
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "g" to "<http://example.com/data/#x>",
                                "avg" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "c" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "g" to "<http://example.com/data/#y>",
                                "avg" to null,
                                "c" to null
                            ),
                            mutableMapOf(
                                "g" to "<http://example.com/data/#z>",
                                "avg" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "c" to "\"0.4\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/aggregates/agg-err-01.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("max")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "max"
                            ), listOf(
                                mutableMapOf(
                                    "x" to null,
                                    "max" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "max"
                        ), listOf(
                            mutableMapOf(
                                "x" to null,
                                "max" to null
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/aggregates/agg-empty-group.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("z")
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
                        ),
                        POPValues(dictionary, listOf(
                                "z"
                        ), listOf(
                                mutableMapOf(
                                        "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("o"),
                                        AOPVariable("z"),
                                        AOPVariable("z2")
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
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                mutableMapOf(
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z2" to "\"101\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z2" to "\"102\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z2" to "\"103\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z2" to "\"104\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("z"),
                                        AOPVariable("s1")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z",
                                        "s1",
                                        "p1"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s1" to "<http://example.org/s2>",
                                                "p1" to "<http://example.org/p>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s1" to "<http://example.org/s3>",
                                                "p1" to "<http://example.org/p>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "s1" to "<http://example.org/s4>",
                                                "p1" to "<http://example.org/p>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "z",
                                "s1"
                        ), listOf(
                                mutableMapOf(
                                        "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s3>"
                                ),
                                mutableMapOf(
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "s1" to "<http://example.org/s4>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
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
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
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
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
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
                                                "z" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to null
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
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("v"),
                                        AOPVariable("z")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "v",
                                        "z"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                        "s" to "<http://example.org/s4>",
                                        "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("book"),
                                        AOPVariable("title"),
                                        AOPVariable("price")
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o")
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o1"),
                                        AOPVariable("o2")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/b>",
                                                "p2" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alan@example.org\"",
                                                "p2" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alan\"",
                                                "p2" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/b>",
                                                "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alan@example.org\"",
                                                "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alan\"",
                                                "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o2" to "\"alan@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/b>",
                                                "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alan@example.org\"",
                                                "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alan\"",
                                                "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o2" to "\"Alan\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"bob@example.org\"",
                                                "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Bob\"",
                                                "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o2" to "\"bob@example.org\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"bob@example.org\"",
                                                "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o2" to "\"Bob\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Bob\"",
                                                "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o2" to "\"Bob\""
                                        )
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p1"),
                                        AOPVariable("o1")
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o1"),
                                        AOPVariable("o2")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/b>",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alan@example.org\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/a>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alan\"",
                                                "o2" to "<http://example.org/b>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                                "o1" to "<http://example.org/c>",
                                                "o2" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"bob@example.org\"",
                                                "o2" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/b>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Bob\"",
                                                "o2" to "<http://example.org/c>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                                "o1" to "\"alice@example.org\"",
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/c>",
                                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o1" to "\"Alice\"",
                                                "o2" to null
                                        )
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("book"),
                                        AOPVariable("title"),
                                        AOPVariable("price")
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
                                        )
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
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "<http://example.org/o1>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "<http://example.org/o1>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "<http://example.org/o2>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p>",
                                                "o" to "<http://example.org/o3>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "<http://example.org/o1>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "<http://example.org/o1>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "<http://example.org/o2>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p>",
                                        "o" to "<http://example.org/o3>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
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
                        )
                    ),
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
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
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
                                )
                        ),
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
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"foo\"",
                                                "p2" to null,
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p3>",
                                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                                "p2" to null,
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p4>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "p2" to null,
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s5>",
                                                "p" to "<http://example.org/p5>",
                                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                                "p2" to null,
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s6>",
                                                "p" to "<http://example.org/p6>",
                                                "o" to "_:o6",
                                                "p2" to null,
                                                "o2" to null
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "<http://example.org/s2>",
                                        "p2" to "<http://example.org/p2>",
                                        "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"foo\"",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p3>",
                                        "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s5>",
                                        "p" to "<http://example.org/p5>",
                                        "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "p" to "<http://example.org/p6>",
                                        "o" to "_:o6",
                                        "p2" to null,
                                        "o2" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "p" to "<http://example.org/p7>",
                                    "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/p7>",
                                "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p3>",
                                                "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p4>",
                                                "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s5>",
                                                "p" to "<http://example.org/p5>",
                                                "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s6>",
                                                "p" to "<http://example.org/p6>",
                                                "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s7>",
                                                "p" to "<http://example.org/p7>",
                                                "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p3>",
                                        "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p4>",
                                        "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s5>",
                                        "p" to "<http://example.org/p5>",
                                        "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "p" to "<http://example.org/p6>",
                                        "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s7>",
                                        "p" to "<http://example.org/p7>",
                                        "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://example.org/ns#myBanana>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://example.org/ns#myBanana>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#c"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://example.org/ns#a1>",
                                                "#c" to "_:c1"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/ns#a1>",
                                                "#c" to "_:c2"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://example.org/ns#a1>"
                                ),
                                mutableMapOf(
                                        "x" to "<http://example.org/ns#a1>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/x>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://example.org/x/x>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-01.rq */,
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/ns#b1>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/ns#b1>"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs01.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("c")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "c" to "<http://example.org/x/c>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "c" to "<http://example.org/x/c>"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs05.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs08.rq */,
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/ns#b>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/ns#b>"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs11.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("f")
                                ),
                                POPValues(dictionary, listOf(
                                        "f"
                                ), listOf(
                                        mutableMapOf(
                                                "f" to "<http://example.org/ns#apple>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "f"
                        ), listOf(
                                mutableMapOf(
                                        "f" to "<http://example.org/ns#apple>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/x>",
                                                "y" to "<http://example.org/x/y>"
                                        ),
                                        mutableMapOf(
                                                "x" to "<http://example.org/x/x>",
                                                "y" to "_:y"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://example.org/x/x>",
                                        "y" to "<http://example.org/x/y>"
                                ),
                                mutableMapOf(
                                        "x" to "<http://example.org/x/x>",
                                        "y" to "_:y"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */,
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("c")
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                mutableMapOf(
                                    "c" to "<http://example.org/GraduateAssistant>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "c" to "<http://example.org/GraduateAssistant>"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q1.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("p"),
                                        AOPVariable("v")
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "v"
                                ), listOf(
                                        mutableMapOf(
                                                "p" to "<http://example.org/hasPublication>",
                                                "v" to "<http://example.org/paper1>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                        ), listOf(
                                mutableMapOf(
                                        "p" to "<http://example.org/hasPublication>",
                                        "v" to "<http://example.org/paper1>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        mutableMapOf(
                                                "x" to "<http://example.org/test#b>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://example.org/test#b>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/lang.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y1"),
                                        AOPVariable("Y2")
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y1",
                                        "Y2"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "Y1" to "\"A\"",
                                                "Y2" to "\"Anick\""
                                        ),
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#b>",
                                                "Y1" to "\"B\"",
                                                "Y2" to "\"Bnick\""
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1",
                                "Y2"
                        ), listOf(
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "Y1" to "\"A\"",
                                        "Y2" to "\"Anick\""
                                ),
                                mutableMapOf(
                                        "X" to "<http://example.org/test#b>",
                                        "Y1" to "\"B\"",
                                        "Y2" to "\"Bnick\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                        ),
                        POPValues(dictionary, listOf(
                                "#a"
                            ), listOf(
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#a>"
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#b>"
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#c>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            mutableMapOf(
                            ),
                            mutableMapOf(
                            ),
                            mutableMapOf(
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-05.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y"),
                                        AOPVariable("Z")
                                ),
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "X",
                                        "Y",
                                        "Z"
                                ), listOf(
                                        mutableMapOf(
                                                "#aa" to "<http://example.org/test#aa>",
                                                "X" to "<http://example.org/test#dd>",
                                                "Y" to "<http://example.org/test#bb>",
                                                "Z" to "<http://example.org/test#ee>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                mutableMapOf(
                                        "X" to "<http://example.org/test#dd>",
                                        "Y" to "<http://example.org/test#bb>",
                                        "Z" to "<http://example.org/test#ee>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#a",
                                        "Y"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "#a" to "<http://example.org/test#aa>",
                                                "Y" to "<http://example.org/test#ee>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "Y" to "<http://example.org/test#ee>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#a",
                                        "Y"
                                ), listOf(
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "#a" to "<http://example.org/test#b>",
                                                "Y" to "<http://example.org/test#c>"
                                        ),
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "#a" to "<http://example.org/test#b>",
                                                "Y" to "<http://example.org/test#h>"
                                        ),
                                        mutableMapOf(
                                                "X" to "<http://example.org/test#a>",
                                                "#a" to "<http://example.org/test#b>",
                                                "Y" to "<http://example.org/test#i>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "Y" to "<http://example.org/test#c>"
                                ),
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "Y" to "<http://example.org/test#h>"
                                ),
                                mutableMapOf(
                                        "X" to "<http://example.org/test#a>",
                                        "Y" to "<http://example.org/test#i>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "C"
                            ), listOf(
                                mutableMapOf(
                                    "C" to "<foo://bla/names#Parent>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "C"
                        ), listOf(
                            mutableMapOf(
                                "C" to "<foo://bla/names#Parent>"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-12.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("a"),
                            AOPVariable("b"),
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "a" to "<http://example.org/test#a>",
                                    "b" to "<http://example.org/test#b>",
                                    "x" to "<http://example.org/test#x>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/test#a>",
                                "b" to "<http://example.org/test#b>",
                                "x" to "<http://example.org/test#x>"
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("parent")
                                ),
                                POPValues(dictionary, listOf(
                                        "parent",
                                        "child"
                                ), listOf(
                                        mutableMapOf(
                                                "parent" to "<http://example.org/test#Bob>",
                                                "child" to "<http://example.org/test#Charlie>"
                                        ),
                                        mutableMapOf(
                                                "parent" to "<http://example.org/test#Dudley>",
                                                "child" to "<http://example.org/test#Alice>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "parent"
                        ), listOf(
                                mutableMapOf(
                                        "parent" to "<http://example.org/test#Bob>"
                                ),
                                mutableMapOf(
                                        "parent" to "<http://example.org/test#Dudley>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "str1" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "str1" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/d1>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d2>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d3>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d4>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/n1>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/n2>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/n3>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/n4>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/n5>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "str1" to "\"foo\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "str1" to "\"BAZ\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "str1" to "\"食べ物\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s5>",
                                        "str1" to "\"100%\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s7>",
                                        "str1" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("s2")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "s2" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("s2")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "s2" to "\"bar\"@en-us"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/d1>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d2>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d3>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d4>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/n1>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/n2>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/n3>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/n4>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/n5>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "str1" to "\"foo\"@en-us"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "str1" to "\"BAZ\"@en-us"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "str1" to "\"食べ物\"@en-us"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s5>",
                                        "str1" to "\"100%\"@en-us"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "str1" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s7>",
                                        "str1" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "num"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/n1>",
                                                "p" to "<http://example.org/num>",
                                                "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/n2>",
                                                "p" to "<http://example.org/num>",
                                                "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/n3>",
                                                "p" to "<http://example.org/num>",
                                                "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/n4>",
                                                "p" to "<http://example.org/num>",
                                                "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/n5>",
                                                "p" to "<http://example.org/num>",
                                                "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        )
                                )
                                )
                        ),
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "num"
                                ), listOf(
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
                                "num"
                        ), listOf(
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
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("ceil")
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
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("floor")
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
            }() /* resources/sparql11-test-suite/functions/floor01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("round")
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
            }() /* resources/sparql11-test-suite/functions/round01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("str")
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
                        ),
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                mutableMapOf(
                                        "str" to "\"abcDEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("str")
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
                        ),
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                mutableMapOf(
                                        "str" to "\"123123\""
                                ),
                                mutableMapOf(
                                        "str" to "\"日本語123\""
                                ),
                                mutableMapOf(
                                        "str" to "\"english123\""
                                ),
                                mutableMapOf(
                                        "str" to "\"français123\""
                                ),
                                mutableMapOf(
                                        "str" to "\"abc123\""
                                ),
                                mutableMapOf(
                                        "str" to "\"def123\""
                                ),
                                mutableMapOf(
                                        "str" to null
                                ),
                                mutableMapOf(
                                        "str" to "\"123日本語\""
                                ),
                                mutableMapOf(
                                        "str" to "\"日本語日本語\"@ja"
                                ),
                                mutableMapOf(
                                        "str" to "\"english日本語\""
                                ),
                                mutableMapOf(
                                        "str" to "\"français日本語\""
                                ),
                                mutableMapOf(
                                        "str" to "\"abc日本語\""
                                ),
                                mutableMapOf(
                                        "str" to "\"def日本語\""
                                ),
                                mutableMapOf(
                                        "str" to null
                                ),
                                mutableMapOf(
                                        "str" to "\"123english\""
                                ),
                                mutableMapOf(
                                        "str" to "\"日本語english\""
                                ),
                                mutableMapOf(
                                        "str" to "\"englishenglish\"@en"
                                ),
                                mutableMapOf(
                                        "str" to "\"françaisenglish\""
                                ),
                                mutableMapOf(
                                        "str" to "\"abcenglish\""
                                ),
                                mutableMapOf(
                                        "str" to "\"defenglish\""
                                ),
                                mutableMapOf(
                                        "str" to null
                                ),
                                mutableMapOf(
                                        "str" to "\"123français\""
                                ),
                                mutableMapOf(
                                        "str" to "\"日本語français\""
                                ),
                                mutableMapOf(
                                        "str" to "\"englishfrançais\""
                                ),
                                mutableMapOf(
                                        "str" to "\"françaisfrançais\"@fr"
                                ),
                                mutableMapOf(
                                        "str" to "\"abcfrançais\""
                                ),
                                mutableMapOf(
                                        "str" to "\"deffrançais\""
                                ),
                                mutableMapOf(
                                        "str" to null
                                ),
                                mutableMapOf(
                                        "str" to "\"123abc\""
                                ),
                                mutableMapOf(
                                        "str" to "\"日本語abc\""
                                ),
                                mutableMapOf(
                                        "str" to "\"englishabc\""
                                ),
                                mutableMapOf(
                                        "str" to "\"françaisabc\""
                                ),
                                mutableMapOf(
                                        "str" to "\"abcabc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "str" to "\"defabc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "str" to null
                                ),
                                mutableMapOf(
                                        "str" to "\"123def\""
                                ),
                                mutableMapOf(
                                        "str" to "\"日本語def\""
                                ),
                                mutableMapOf(
                                        "str" to "\"englishdef\""
                                ),
                                mutableMapOf(
                                        "str" to "\"françaisdef\""
                                ),
                                mutableMapOf(
                                        "str" to "\"abcdef\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "str" to "\"defdef\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "str" to null
                                ),
                                mutableMapOf(
                                        "str" to null
                                ),
                                mutableMapOf(
                                        "str" to null
                                ),
                                mutableMapOf(
                                        "str" to null
                                ),
                                mutableMapOf(
                                        "str" to null
                                ),
                                mutableMapOf(
                                        "str" to null
                                ),
                                mutableMapOf(
                                        "str" to null
                                ),
                                mutableMapOf(
                                        "str" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("str"),
                                        AOPVariable("len")
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
                        ),
                        POPValues(dictionary, listOf(
                                "str",
                                "len"
                        ), listOf(
                                mutableMapOf(
                                        "str" to "\"foo\"",
                                        "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "str" to "\"bar\"@en",
                                        "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "str" to "\"BAZ\"",
                                        "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "str" to "\"食べ物\"",
                                        "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "str" to "\"100%\"",
                                        "len" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("ustr")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "ustr"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "ustr" to "\"FOO\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "ustr" to "\"BAR\"@en"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "ustr" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "ustr" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s5>",
                                        "ustr" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "ustr" to "\"ABC\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s7>",
                                        "ustr" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("lstr")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "lstr"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "lstr" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "lstr" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "lstr" to "\"baz\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "lstr" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s5>",
                                        "lstr" to "\"100%\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "lstr" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s7>",
                                        "lstr" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "str" to "\"bar\"@en"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s6>",
                                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "str"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/n3>",
                                                "p" to "<http://example.org/num>",
                                                "str" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s5>",
                                                "p" to "<http://example.org/str>",
                                                "str" to "\"100%\""
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/n3>",
                                        "str" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s5>",
                                        "str" to "\"100%\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "str"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s6>",
                                                "p" to "<http://example.org/str>",
                                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "x",
                                        "y",
                                        "sum"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/x6>",
                                                "x" to "\"1\"",
                                                "y" to "\"2\"",
                                                "sum" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x4>",
                                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x5>",
                                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x1>",
                                                "x" to "\"a\"",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "sum" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x3>",
                                                "x" to "<http://example/a>",
                                                "y" to "\"1\"",
                                                "sum" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x2>",
                                                "x" to "_:b",
                                                "y" to "\"1\"",
                                                "sum" to null
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
                                        "x" to "\"1\"",
                                        "y" to "\"2\"",
                                        "sum" to null
                                ),
                                mutableMapOf(
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "y" to "\"2\"",
                                        "sum" to null
                                ),
                                mutableMapOf(
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "sum" to null
                                ),
                                mutableMapOf(
                                        "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "x" to "\"a\"",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "sum" to null
                                ),
                                mutableMapOf(
                                        "x" to "<http://example/a>",
                                        "y" to "\"1\"",
                                        "sum" to null
                                ),
                                mutableMapOf(
                                        "x" to "_:b",
                                        "y" to "\"1\"",
                                        "sum" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "x",
                                        "y",
                                        "sum"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/x6>",
                                                "x" to "\"1\"",
                                                "y" to "\"2\"",
                                                "sum" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x4>",
                                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
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
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x5>",
                                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "sum" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x1>",
                                                "x" to "\"a\"",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "sum" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x3>",
                                                "x" to "<http://example/a>",
                                                "y" to "\"1\"",
                                                "sum" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/x2>",
                                                "x" to "_:b",
                                                "y" to "\"1\"",
                                                "sum" to null
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
                                        "x" to "\"1\"",
                                        "y" to "\"2\"",
                                        "sum" to null
                                ),
                                mutableMapOf(
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "sum" to null
                                ),
                                mutableMapOf(
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "y" to "\"2\"",
                                        "sum" to null
                                ),
                                mutableMapOf(
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "sum" to null
                                ),
                                mutableMapOf(
                                        "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "sum" to null
                                ),
                                mutableMapOf(
                                        "x" to "\"a\"",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "sum" to null
                                ),
                                mutableMapOf(
                                        "x" to "<http://example/a>",
                                        "y" to "\"1\"",
                                        "sum" to null
                                ),
                                mutableMapOf(
                                        "x" to "_:b",
                                        "y" to "\"1\"",
                                        "sum" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
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
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                mutableMapOf(
                                        "hash" to "\"acbd18db4cc2f85cedef654fccc4a4d8\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
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
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                mutableMapOf(
                                        "hash" to "\"e7ada485d13b1decf628c9211bc3a97b\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
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
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                mutableMapOf(
                                        "hash" to "\"0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
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
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                mutableMapOf(
                                        "hash" to "\"d46696735b6a09ff407bfc1a9407e008840db9c9\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
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
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                mutableMapOf(
                                        "hash" to "\"2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
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
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                mutableMapOf(
                                        "hash" to "\"0fbe868d1df356ca9df7ebff346da3a56280e059a7ea81186ef885b140d254ee\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/d1>",
                                        "x" to "\"28\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d2>",
                                        "x" to "\"38\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d3>",
                                        "x" to "\"59\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d4>",
                                        "x" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/d1>",
                                        "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d2>",
                                        "x" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d3>",
                                        "x" to "\"0.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d4>",
                                        "x" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/d1>",
                                        "x" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d2>",
                                        "x" to "\"15\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d3>",
                                        "x" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d4>",
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/d1>",
                                        "x" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d2>",
                                        "x" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d3>",
                                        "x" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d4>",
                                        "x" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/d1>",
                                        "x" to "\"2010\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d2>",
                                        "x" to "\"2010\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d3>",
                                        "x" to "\"2008\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d4>",
                                        "x" to "\"2011\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/d1>",
                                        "x" to "\"21\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d2>",
                                        "x" to "\"21\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d3>",
                                        "x" to "\"20\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d4>",
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */,
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "x" to "\"\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "x" to "\"\"-PT8H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "x" to "\"\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "x" to "\"\""
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/functions/timezone-01.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/d1>",
                                        "x" to "\"Z\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d2>",
                                        "x" to "\"-08:00\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d3>",
                                        "x" to "\"Z\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/d4>",
                                        "x" to "\"\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s1"),
                                        AOPVariable("s2"),
                                        AOPVariable("b1"),
                                        AOPVariable("b2")
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
                                                "b2" to "_:27515\"foo\"",
                                                "b1" to "_:27509\"foo\""
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s3>",
                                                "s1" to "\"BAZ\"",
                                                "b" to "<http://example.org/s1>",
                                                "s2" to "\"foo\"",
                                                "b2" to "_:27515\"foo\"",
                                                "b1" to "_:27509\"BAZ\""
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s1>",
                                                "s1" to "\"foo\"",
                                                "b" to "<http://example.org/s3>",
                                                "s2" to "\"BAZ\"",
                                                "b2" to "_:27515\"BAZ\"",
                                                "b1" to "_:27509\"foo\""
                                        ),
                                        mutableMapOf(
                                                "a" to "<http://example.org/s3>",
                                                "s1" to "\"BAZ\"",
                                                "b" to "<http://example.org/s3>",
                                                "s2" to "\"BAZ\"",
                                                "b2" to "_:27515\"BAZ\"",
                                                "b1" to "_:27509\"BAZ\""
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "s2",
                                "b1",
                                "b2"
                        ), listOf(
                                mutableMapOf(
                                        "s1" to "\"foo\"",
                                        "s2" to "\"foo\"",
                                        "b1" to "_:27509\"foo\"",
                                        "b2" to "_:27515\"foo\""
                                ),
                                mutableMapOf(
                                        "s1" to "\"BAZ\"",
                                        "s2" to "\"foo\"",
                                        "b1" to "_:27509\"BAZ\"",
                                        "b2" to "_:27515\"foo\""
                                ),
                                mutableMapOf(
                                        "s1" to "\"foo\"",
                                        "s2" to "\"BAZ\"",
                                        "b1" to "_:27509\"foo\"",
                                        "b2" to "_:27515\"BAZ\""
                                ),
                                mutableMapOf(
                                        "s1" to "\"BAZ\"",
                                        "s2" to "\"BAZ\"",
                                        "b1" to "_:27509\"BAZ\"",
                                        "b2" to "_:27515\"BAZ\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("b1"),
                                        AOPVariable("b2")
                                ),
                                POPValues(dictionary, listOf(
                                        "b2",
                                        "b1"
                                ), listOf(
                                        mutableMapOf(
                                                "b2" to "_:2953229557",
                                                "b1" to "_:2952729559"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b1",
                                "b2"
                        ), listOf(
                                mutableMapOf(
                                        "b1" to "_:2952729559",
                                        "b2" to "_:2953229557"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("b1"),
                                        AOPVariable("b2")
                                ),
                                POPValues(dictionary, listOf(
                                        "b2",
                                        "b1"
                                ), listOf(
                                        mutableMapOf(
                                                "b2" to "_:2956329581",
                                                "b1" to "_:2957029583"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b1",
                                "b2"
                        ), listOf(
                                mutableMapOf(
                                        "b1" to "_:2957029583",
                                        "b2" to "_:2956329581"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("n")
                                ),
                                POPValues(dictionary, listOf(
                                        "n"
                                ), listOf(
                                        mutableMapOf(
                                                "n" to "\"2020-02-25T08:04:25Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "n"
                        ), listOf(
                                mutableMapOf(
                                        "n" to "\"2020-02-25T08:04:25Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/now01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("uri"),
                                        AOPVariable("iri")
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
            }() /* resources/sparql11-test-suite/functions/iri01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("o"),
                                        AOPVariable("integer")
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
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "integer"
                        ), listOf(
                                mutableMapOf(
                                        "o" to "\"123\"",
                                        "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                                ),
                                mutableMapOf(
                                        "o" to "\"日本語\"@ja",
                                        "integer" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                                ),
                                mutableMapOf(
                                        "o" to "\"english\"@en",
                                        "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                                ),
                                mutableMapOf(
                                        "o" to "\"français\"@fr",
                                        "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                                ),
                                mutableMapOf(
                                        "o" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                                ),
                                mutableMapOf(
                                        "o" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                                ),
                                mutableMapOf(
                                        "o" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "integer" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("error")
                                ),
                                POPValues(dictionary, listOf(
                                        "error"
                                ), listOf(
                                        mutableMapOf(
                                                "error" to null
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
            }() /* resources/sparql11-test-suite/functions/if02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s")
                                ),
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/s2>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example/s1>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example/s2>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("w"),
                                        AOPVariable("S")
                                ),
                                POPValues(dictionary, listOf(
                                        "w",
                                        "S"
                                ), listOf(
                                        mutableMapOf(
                                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "S" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "w" to null,
                                                "S" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "w",
                                "S"
                        ), listOf(
                                mutableMapOf(
                                        "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "S" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "w" to null,
                                        "S" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("w")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "w"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example/s1>",
                                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/s2>",
                                                "w" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example/s3>",
                                                "w" to null
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "w"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example/s1>",
                                        "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example/s2>",
                                        "w" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example/s3>",
                                        "w" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
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
                                )
                        ),
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
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"foo\"",
                                                "p2" to null,
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                                "p2" to null,
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s4>",
                                                "p" to "<http://example.org/p4>",
                                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "p2" to null,
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s5>",
                                                "p" to "<http://example.org/p5>",
                                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                                "p2" to null,
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s6>",
                                                "p" to "<http://example.org/p6>",
                                                "o" to "_:o6",
                                                "p2" to null,
                                                "o2" to null
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "<http://example.org/s2>",
                                        "p2" to "<http://example.org/p2>",
                                        "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"foo\"",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s4>",
                                        "p" to "<http://example.org/p4>",
                                        "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s5>",
                                        "p" to "<http://example.org/p5>",
                                        "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "p" to "<http://example.org/p6>",
                                        "o" to "_:o6",
                                        "p2" to null,
                                        "o2" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                ),
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        mutableMapOf(
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                        ), listOf(
                                mutableMapOf(
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("z"),
                                        AOPVariable("eq")
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
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("z"),
                                        AOPVariable("sum")
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
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("z"),
                                        AOPVariable("sum"),
                                        AOPVariable("twice")
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
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
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
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("l"),
                                        AOPVariable("dt")
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
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("l"),
                                        AOPVariable("dt")
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
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://www.example.org/instance#a>",
                                        "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "dt" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("dt")
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
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "dt"
                        ), listOf(
                                mutableMapOf(
                                        "x" to "<http://www.example.org/instance#a>",
                                        "dt" to "<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                        "x" to "<http://www.example.org/instance#b>",
                                        "dt" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("O")
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
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("L")
                                ),
                                POPValues(dictionary, listOf(
                                        "#_35218",
                                        "L",
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "#_35218" to "_:_35195",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_35218" to "_:_35198",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_35218" to "_:_35199",
                                                "L" to "\"Pasta\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_35218" to "_:_35196",
                                                "L" to "\"Pizza\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_35218" to "_:_35200",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_35218" to "_:_35197",
                                                "L" to "\"Wine\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                mutableMapOf(
                                        "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                        "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                        "L" to "\"Pasta\""
                                ),
                                mutableMapOf(
                                        "L" to "\"Pizza\""
                                ),
                                mutableMapOf(
                                        "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                        "L" to "\"Wine\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("P"),
                                        AOPVariable("FullName")
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
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "FullName"
                        ), listOf(
                                mutableMapOf(
                                        "P" to "<http://p1>",
                                        "FullName" to "\"John Doe\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "P",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://p1>",
                                                "P" to null,
                                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                                "o" to "\"John Doe\""
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                mutableMapOf(
                                        "s" to "<http://p1>",
                                        "p" to "<http://xmlns.com/foaf/0.1/name>",
                                        "o" to "\"John Doe\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("L")
                                ),
                                POPValues(dictionary, listOf(
                                        "#_35567",
                                        "L",
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "#_35567" to "_:_35544",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_35567" to "_:_35547",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_35567" to "_:_35548",
                                                "L" to "\"Pasta\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_35567" to "_:_35545",
                                                "L" to "\"Pizza\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_35567" to "_:_35549",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_35567" to "_:_35546",
                                                "L" to "\"Wine\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                mutableMapOf(
                                        "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                        "L" to "\"Ice Cream\""
                                ),
                                mutableMapOf(
                                        "L" to "\"Pasta\""
                                ),
                                mutableMapOf(
                                        "L" to "\"Pizza\""
                                ),
                                mutableMapOf(
                                        "L" to "\"Soft Drink\""
                                ),
                                mutableMapOf(
                                        "L" to "\"Wine\""
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
                e.printStackTrace()
                assertTrue(data.expected is Throwable)
            }
        }
    }
}
