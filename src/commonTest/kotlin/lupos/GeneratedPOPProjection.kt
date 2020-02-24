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


class GeneratedPOPProjectionTest {
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
            }() /* resources/sparql11-test-suite/aggregates/agg04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("P"),
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "P"
                            ), listOf(
                                mutableMapOf(
                                    "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "P" to "<http://www.example.org/p1>"
                                ),
                                mutableMapOf(
                                    "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "P" to "<http://www.example.org/p2>"
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
            }() /* resources/sparql11-test-suite/aggregates/agg05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("P"),
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#f1162",
                                "P"
                            ), listOf(
                                mutableMapOf(
                                    "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#f1162" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "P" to "<http://www.example.org/p1>"
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
            }() /* resources/sparql11-test-suite/aggregates/agg03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#f1745"
                            ), listOf(
                                mutableMapOf(
                                    "C" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#f1745" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
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
            }() /* resources/sparql11-test-suite/aggregates/agg06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("P"),
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#f2020",
                                "P"
                            ), listOf(
                                mutableMapOf(
                                    "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#f2020" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "P" to "<http://www.example.org/p1>"
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
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("O12"),
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "O12"
                            ), listOf(
                                mutableMapOf(
                                    "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O12" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O12" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O12" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O12" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("sum")
                        ),
                        POPValues(dictionary, listOf(
                                "sum",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to "\"6.7\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://www.example.org/decimals>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"32100.0\"^^<http://www.w3.org/2001/XMLSchema#double>",
                                    "s" to "<http://www.example.org/doubles>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://www.example.org/ints>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://www.example.org/mixed1>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"0.4\"^^<http://www.w3.org/2001/XMLSchema#double>",
                                    "s" to "<http://www.example.org/mixed2>"
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
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("avg")
                        ),
                        POPValues(dictionary, listOf(
                                "avg",
                                "#f3807",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "avg" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#f3807" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "s" to "<http://www.example.org/ints>"
                                ),
                                mutableMapOf(
                                    "avg" to "\"1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#f3807" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "s" to "<http://www.example.org/mixed1>"
                                ),
                                mutableMapOf(
                                    "avg" to "\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>",
                                    "#f3807" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "s" to "<http://www.example.org/mixed2>"
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
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/aggregates/agg-min-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("min")
                        ),
                        POPValues(dictionary, listOf(
                                "min",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "min" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://www.example.org/decimals>"
                                ),
                                mutableMapOf(
                                    "min" to "\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>",
                                    "s" to "<http://www.example.org/doubles>"
                                ),
                                mutableMapOf(
                                    "min" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://www.example.org/ints>"
                                ),
                                mutableMapOf(
                                    "min" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://www.example.org/mixed1>"
                                ),
                                mutableMapOf(
                                    "min" to "\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>",
                                    "s" to "<http://www.example.org/mixed2>"
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
            }() /* resources/sparql11-test-suite/aggregates/agg-min-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/aggregates/agg-max-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("max")
                        ),
                        POPValues(dictionary, listOf(
                                "max",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "max" to "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://www.example.org/decimals>"
                                ),
                                mutableMapOf(
                                    "max" to "\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>",
                                    "s" to "<http://www.example.org/doubles>"
                                ),
                                mutableMapOf(
                                    "max" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://www.example.org/ints>"
                                ),
                                mutableMapOf(
                                    "max" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://www.example.org/mixed1>"
                                ),
                                mutableMapOf(
                                    "max" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://www.example.org/mixed2>"
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
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */ ,
            /*{
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
            }()*/ /* resources/sparql11-test-suite/aggregates/agg-sample-01.rq */
            /*{
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
                                "avg",
                                "c",
                                "g"
                            ), listOf(
                                mutableMapOf(
                                    "avg" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "c" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "g" to "<http://example.com/data/#x>"
                                ),
                                mutableMapOf(
                                    "avg" to null,
                                    "c" to null,
                                    "g" to "<http://example.com/data/#y>"
                                ),
                                mutableMapOf(
                                    "avg" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "c" to "\"0.4\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "g" to "<http://example.com/data/#z>"
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
            }()*/ /* resources/sparql11-test-suite/aggregates/agg-err-01.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("max")
                        ),
                        POPValues(dictionary, listOf(
                                "max",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "max" to null,
                                    "x" to null
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
            }()*/ /* resources/sparql11-test-suite/aggregates/agg-empty-group.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("z")
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
            }() /* resources/sparql11-test-suite/bind/bind01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("o"),
                            AOPVariable("z"),
                            AOPVariable("z2")
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
            }() /* resources/sparql11-test-suite/bind/bind02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/bind/bind03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/bind/bind04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "z",
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
            }() /* resources/sparql11-test-suite/bind/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/bind/bind07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("z")
                        ),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "#p8059",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s4>",
                                    "#p8059" to null,
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("z")
                        ),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "#p8123",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s4>",
                                    "#p8123" to null,
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ),
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
            }() /* resources/sparql11-test-suite/bindings/values01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ),
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
            }() /* resources/sparql11-test-suite/bindings/values01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/bindings/values02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ),
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
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/bindings/values06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ),
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
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ),
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
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ),
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
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ),
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
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ),
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
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
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
                                "o",
                                "s",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "o" to "<http://example.org/o1>",
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "o" to "<http://example.org/o1>",
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "o" to "<http://example.org/o2>",
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "o" to "<http://example.org/o3>",
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>"
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
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */ ,
            /*{
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
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            {
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
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            /*{
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
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            {
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
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            /*{
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
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            {
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
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p15338",
                                "#o15338"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/ns#myBanana>",
                                    "#p15338" to null,
                                    "#o15338" to null
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
            }() /* resources/sparql11-test-suite/entailment/rdf02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p15375",
                                "#c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/ns#a1>",
                                    "#p15375" to null,
                                    "#c" to "_:c1"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/ns#a1>",
                                    "#p15375" to null,
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
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p15415",
                                "#o15415"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p15415" to null,
                                    "#o15415" to null
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
            }() /* resources/sparql11-test-suite/entailment/rdf04.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "#s15452",
                                "x",
                                "#o15452"
                            ), listOf(
                                mutableMapOf(
                                    "#s15452" to null,
                                    "x" to "<http://example.org/ns#b1>",
                                    "#o15452" to null
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
            }()*/ /* resources/sparql11-test-suite/entailment/rdfs01.rq */
            /*{
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
            }()*/ /* resources/sparql11-test-suite/entailment/rdfs05.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "#s15748",
                                "#p15748",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "#s15748" to null,
                                    "#p15748" to null,
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
            }() /* resources/sparql11-test-suite/entailment/rdfs08.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
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
            }()*/ /* resources/sparql11-test-suite/entailment/rdfs11.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("f")
                        ),
                        POPValues(dictionary, listOf(
                                "#s15930",
                                "#p15930",
                                "f"
                            ), listOf(
                                mutableMapOf(
                                    "#s15930" to null,
                                    "#p15930" to null,
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
            }() /* resources/sparql11-test-suite/entailment/rdfs12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y")
                        ),
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
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("c")
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p16178",
                                "#o16178"
                            ), listOf(
                                mutableMapOf(
                                    "c" to "<http://example.org/GraduateAssistant>",
                                    "#p16178" to null,
                                    "#o16178" to null
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
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q1.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("c")
                        ),
                        POPValues(dictionary, listOf(
                                "c",
                                "#p16219",
                                "#o16219"
                            ), listOf(
                                mutableMapOf(
                                    "c" to "<http://example.org/GraduateAssistant>",
                                    "#p16219" to null,
                                    "#o16219" to null
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
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q1.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("p"),
                            AOPVariable("v")
                        ),
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
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p16650",
                                "#o16650"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p16650" to null,
                                    "#o16650" to null
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
            }() /* resources/sparql11-test-suite/entailment/plainLit.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("z")
                        ),
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
            }() /* resources/sparql11-test-suite/entailment/bind01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/entailment/bind03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "o",
                                "z",
                                "#p17243",
                                "#o17243",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17243" to null,
                                    "#o17243" to null,
                                    "p" to "<http://example.org/p>"
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
            }() /* resources/sparql11-test-suite/entailment/bind05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "o",
                                "z",
                                "#p17360",
                                "#o17360",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17360" to null,
                                    "#o17360" to null,
                                    "p" to "<http://example.org/p>"
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
            }() /* resources/sparql11-test-suite/entailment/bind05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "o",
                                "z",
                                "#p17793",
                                "#o17793",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17793" to null,
                                    "#o17793" to null,
                                    "p" to "<http://example.org/p>"
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
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "o",
                                "z",
                                "#p17910",
                                "#o17910",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17910" to null,
                                    "#o17910" to null,
                                    "p" to "<http://example.org/p>"
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
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p18049",
                                "#o18049"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18049" to null,
                                    "#o18049" to null
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
            }() /* resources/sparql11-test-suite/entailment/sparqldl-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y1"),
                            AOPVariable("Y2")
                        ),
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
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                        ),
                        POPValues(dictionary, listOf(
                                "#a",
                                "#p18293",
                                "#o18293"
                            ), listOf(
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#a>",
                                    "#p18293" to null,
                                    "#o18293" to null
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#b>",
                                    "#p18293" to null,
                                    "#o18293" to null
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#c>",
                                    "#p18293" to null,
                                    "#o18293" to null
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
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-05.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y"),
                            AOPVariable("Z")
                        ),
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
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ),
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
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ),
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
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "#s18748",
                                "#p18748",
                                "C"
                            ), listOf(
                                mutableMapOf(
                                    "#s18748" to null,
                                    "#p18748" to null,
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
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-12.rq */
            /*{
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
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#p18863",
                                "#o18863"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p18863" to null,
                                    "#o18863" to null
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
            }() /* resources/sparql11-test-suite/entailment/lang.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("parent")
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#p18910",
                                "child"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p18910" to null,
                                    "child" to "<http://example.org/test#Charlie>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p18910" to null,
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
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
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
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
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
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
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
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
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
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
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
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
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
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
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
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
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
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
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
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
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
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p23559",
                                "num"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/n4>",
                                    "#p23559" to null,
                                    "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n5>",
                                    "#p23559" to null,
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
            }() /* resources/sparql11-test-suite/functions/abs01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("ceil")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("floor")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("round")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("str")
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
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("str")
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
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("str")
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
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("str"),
                            AOPVariable("len")
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
            }() /* resources/sparql11-test-suite/functions/length01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("ustr")
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
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("lstr")
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
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p25313",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p25313" to null,
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p25313" to null,
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
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p25384",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p25384" to null,
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p25384" to null,
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
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/functions/ends01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
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
                                    "s" to "<http://example/x6>",
                                    "#p26389" to null,
                                    "x" to "\"1\"",
                                    "#p26390" to null,
                                    "y" to "\"2\""
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
                                    "s" to "<http://example/x1>",
                                    "#p26389" to null,
                                    "x" to "\"a\"",
                                    "#p26390" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                    "sum" to null,
                                    "s" to "<http://example/x2>",
                                    "#p26389" to null,
                                    "x" to "_:b",
                                    "#p26390" to null,
                                    "y" to "\"1\""
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
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
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
                                    "s" to "<http://example/x6>",
                                    "#p26562" to null,
                                    "x" to "\"1\"",
                                    "#p26563" to null,
                                    "y" to "\"2\""
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
                                    "s" to "<http://example/x1>",
                                    "#p26562" to null,
                                    "x" to "\"a\"",
                                    "#p26563" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                    "s" to "<http://example/x2>",
                                    "#p26562" to null,
                                    "x" to "_:b",
                                    "#p26563" to null,
                                    "y" to "\"1\""
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
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
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
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
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
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
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
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
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
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
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
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
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
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
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
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
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
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
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
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
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
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
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
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
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
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }()*/ /* resources/sparql11-test-suite/functions/timezone-01.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                "b1" to "_:28380\"foo\"",
                                "b2" to "_:28386\"foo\""
                            ),
                            mutableMapOf(
                                "s1" to "\"BAZ\"",
                                "s2" to "\"foo\"",
                                "b1" to "_:28380\"BAZ\"",
                                "b2" to "_:28386\"foo\""
                            ),
                            mutableMapOf(
                                "s1" to "\"foo\"",
                                "s2" to "\"BAZ\"",
                                "b1" to "_:28380\"foo\"",
                                "b2" to "_:28386\"BAZ\""
                            ),
                            mutableMapOf(
                                "s1" to "\"BAZ\"",
                                "s2" to "\"BAZ\"",
                                "b1" to "_:28380\"BAZ\"",
                                "b2" to "_:28386\"BAZ\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("b1"),
                            AOPVariable("b2")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("b1"),
                            AOPVariable("b2")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("n")
                        ),
                        POPValues(dictionary, listOf(
                                "n"
                            ), listOf(
                                mutableMapOf(
                                    "n" to "\"2020-02-24T19:33:13Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("uri"),
                            AOPVariable("iri")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("o"),
                            AOPVariable("integer")
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
            }() /* resources/sparql11-test-suite/functions/if01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/functions/if02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/grouping/group01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("w"),
                            AOPVariable("S")
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "w"
                            ), listOf(
                                mutableMapOf(
                                    "S" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "S" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "w" to null
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
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
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
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
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
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                        ),
                        POPValues(dictionary, listOf(
                                "#s33212",
                                "#p33212",
                                "#o33212"
                            ), listOf(
                                mutableMapOf(
                                    "#s33212" to null,
                                    "#p33212" to null,
                                    "#o33212" to null
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
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                        ),
                        POPValues(dictionary, listOf(
                                "#s33219",
                                "#p33219",
                                "#o33219"
                            ), listOf(
                                mutableMapOf(
                                    "#s33219" to null,
                                    "#p33219" to null,
                                    "#o33219" to null
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
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("eq")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("sum")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("sum")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
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
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
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
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("dt")
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
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("O")
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37037",
                                "#o37037"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37037" to null,
                                    "#o37037" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37037" to null,
                                    "#o37037" to null
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
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("L")
                        ),
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
                                    "#_36963" to "_:_36941",
                                    "#p37034" to null,
                                    "L" to "\"Pizza\"",
                                    "#p37035" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36945",
                                    "#p37034" to null,
                                    "L" to "\"Soft Drink\"",
                                    "#p37035" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36942",
                                    "#p37034" to null,
                                    "L" to "\"Wine\"",
                                    "#p37035" to null,
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
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("O")
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37058",
                                "#o37058"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37058" to null,
                                    "#o37058" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37058" to null,
                                    "#o37058" to null
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
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("L")
                        ),
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
                                    "#_36963" to "_:_36941",
                                    "#p37055" to null,
                                    "L" to "\"Pizza\"",
                                    "#p37056" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36945",
                                    "#p37055" to null,
                                    "L" to "\"Soft Drink\"",
                                    "#p37056" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36942",
                                    "#p37055" to null,
                                    "L" to "\"Wine\"",
                                    "#p37056" to null,
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
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("P"),
                            AOPVariable("FullName")
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
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
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
                                "o",
                                "s",
                                "P",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "o" to "\"John Doe\"",
                                    "s" to "<http://p1>",
                                    "P" to null,
                                    "p" to "<http://xmlns.com/foaf/0.1/name>"
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
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("P"),
                            AOPVariable("FullName")
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
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("O")
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37402",
                                "#o37402"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37402" to null,
                                    "#o37402" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37402" to null,
                                    "#o37402" to null
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
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("L")
                        ),
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
                                    "#_37328" to "_:_37306",
                                    "#p37399" to null,
                                    "L" to "\"Pizza\"",
                                    "#p37400" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37310",
                                    "#p37399" to null,
                                    "L" to "\"Soft Drink\"",
                                    "#p37400" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37307",
                                    "#p37399" to null,
                                    "L" to "\"Wine\"",
                                    "#p37400" to null,
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
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("O")
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37423",
                                "#o37423"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37423" to null,
                                    "#o37423" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37423" to null,
                                    "#o37423" to null
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
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("L")
                        ),
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
                                    "#_37328" to "_:_37306",
                                    "#p37420" to null,
                                    "L" to "\"Pizza\"",
                                    "#p37421" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37310",
                                    "#p37420" to null,
                                    "L" to "\"Soft Drink\"",
                                    "#p37421" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37307",
                                    "#p37420" to null,
                                    "L" to "\"Wine\"",
                                    "#p37421" to null,
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
