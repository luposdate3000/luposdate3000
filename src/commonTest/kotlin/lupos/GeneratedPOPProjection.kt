package lupos

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
                                "z",
                                "s",
                                "p",
                                "o"
                            ), listOf(
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
                            AOPVariable("z"),
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
                        POPValues(dictionary, listOf(
                                "z",
                                "nova",
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "z" to null,
                                    "nova" to null,
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z" to null,
                                    "nova" to null,
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z" to null,
                                    "nova" to null,
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z" to null,
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
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to null,
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
                            AOPVariable("z"),
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
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
                                    "#p8059" to "<http://example.org/p>",
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
                                    "#p8123" to "<http://example.org/p>",
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
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ),
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
                                "#p15363",
                                "#o15363"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/ns#myBanana>",
                                    "#p15363" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o15363" to "<http://example.org/ns#banana>"
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
                                "#p15400",
                                "#c"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/ns#a1>",
                                    "#p15400" to "<http://example.org/ns#b1>",
                                    "#c" to "_:c1"
                                ),
                                mutableMapOf(
                                    "x" to "<http://example.org/ns#a1>",
                                    "#p15400" to "<http://example.org/ns#b1>",
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
                                "#p15440",
                                "#o15440"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p15440" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o15440" to "<http://example.org/x/c>"
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
                                "#s15477",
                                "x",
                                "#o15477"
                            ), listOf(
                                mutableMapOf(
                                    "#s15477" to "<http://example.org/ns#a>",
                                    "x" to "<http://example.org/ns#b1>",
                                    "#o15477" to "<http://example.org/ns#c>"
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
                                "#s15773",
                                "#p15773",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "#s15773" to "<http://example.org/ns#d>",
                                    "#p15773" to "<http://www.w3.org/2000/01/rdf-schema#range>",
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
                                "#s15955",
                                "#p15955",
                                "f"
                            ), listOf(
                                mutableMapOf(
                                    "#s15955" to "<http://example.org/ns#favourite-fruit>",
                                    "#p15955" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>",
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
                                "#p16203",
                                "#o16203"
                            ), listOf(
                                mutableMapOf(
                                    "c" to "<http://example.org/GraduateAssistant>",
                                    "#p16203" to "<http://www.w3.org/2000/01/rdf-schema#subClassOf>",
                                    "#o16203" to "<http://example.org/Student>"
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
                                "#p16244",
                                "#o16244"
                            ), listOf(
                                mutableMapOf(
                                    "c" to "<http://example.org/GraduateAssistant>",
                                    "#p16244" to "<http://www.w3.org/2000/01/rdf-schema#subClassOf>",
                                    "#o16244" to "<http://example.org/Student>"
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
                                "#p16675",
                                "#o16675"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p16675" to "<http://xmlns.com/foaf/0.1/name>",
                                    "#o16675" to "\"name\"@en"
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
                            AOPVariable("z"),
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p")
                        ),
                        POPValues(dictionary, listOf(
                                "z",
                                "nova",
                                "s",
                                "o",
                                "#p17164",
                                "#o17164",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "z" to null,
                                    "nova" to null,
                                    "s" to "<http://example.org/s1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17164" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17164" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "z" to null,
                                    "nova" to null,
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17164" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17164" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "z" to null,
                                    "nova" to null,
                                    "s" to "<http://example.org/s3>",
                                    "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17164" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17164" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "p" to "<http://example.org/p>"
                                ),
                                mutableMapOf(
                                    "z" to null,
                                    "nova" to null,
                                    "s" to "<http://example.org/s4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17164" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17164" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
                                    "p" to "<http://example.org/p>"
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
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "z" to null,
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "z",
                                "s",
                                "o",
                                "#p17269",
                                "#o17269",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17269" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17269" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
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
                                "z",
                                "s",
                                "o",
                                "#p17386",
                                "#o17386",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17386" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17386" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
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
                            AOPVariable("z"),
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p")
                        ),
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
                                "z",
                                "s",
                                "o",
                                "#p17820",
                                "#o17820",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17820" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17820" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
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
                                "z",
                                "s",
                                "o",
                                "#p17937",
                                "#o17937",
                                "p"
                            ), listOf(
                                mutableMapOf(
                                    "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s2>",
                                    "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p17937" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o17937" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>",
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
                                "#p18076",
                                "#o18076"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/x/x>",
                                    "#p18076" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18076" to "<http://example.org/x/c>"
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
                                "#p18320",
                                "#o18320"
                            ), listOf(
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#a>",
                                    "#p18320" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18320" to "<http://example.org/test#Person>"
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#b>",
                                    "#p18320" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18320" to "<http://example.org/test#Person>"
                                ),
                                mutableMapOf(
                                    "#a" to "<http://example.org/test#c>",
                                    "#p18320" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o18320" to "<http://example.org/test#Person>"
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
                            AOPVariable("Y"),
                            AOPVariable("Z")
                        ),
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
                                "#s18776",
                                "#p18776",
                                "C"
                            ), listOf(
                                mutableMapOf(
                                    "#s18776" to "<foo://bla/names#child>",
                                    "#p18776" to "<http://www.w3.org/2000/01/rdf-schema#domain>",
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
                                "#p18891",
                                "#o18891"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://example.org/test#b>",
                                    "#p18891" to "<http://xmlns.com/foaf/0.1/name>",
                                    "#o18891" to "\"name\"@en"
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
                                "#p18938",
                                "child"
                            ), listOf(
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Bob>",
                                    "#p18938" to "<http://example.org/test#hasChild>",
                                    "child" to "<http://example.org/test#Charlie>"
                                ),
                                mutableMapOf(
                                    "parent" to "<http://example.org/test#Dudley>",
                                    "#p18938" to "<http://example.org/test#hasChild>",
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
                                "#p21745",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "str1" to null,
                                    "s" to "<http://example.org/s2>",
                                    "#p21745" to "<http://example.org/str>",
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
                                "#p21878",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "str1" to null,
                                    "s" to "<http://example.org/s2>",
                                    "#p21878" to "<http://example.org/str>",
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
                                "#p22072",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "str1" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s" to "<http://example.org/s2>",
                                    "#p22072" to "<http://example.org/str>",
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
                                "#p22213",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "str1" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s" to "<http://example.org/s2>",
                                    "#p22213" to "<http://example.org/str>",
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
                                "#p22585",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s2" to null,
                                    "s" to "<http://example.org/s2>",
                                    "#p22585" to "<http://example.org/str>",
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
                                "#p22718",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s2" to null,
                                    "s" to "<http://example.org/s2>",
                                    "#p22718" to "<http://example.org/str>",
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
                                "#p22909",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s2" to "\"bar\"@en-us",
                                    "s" to "<http://example.org/s2>",
                                    "#p22909" to "<http://example.org/str>",
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
                                "#p23050",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s2" to "\"bar\"@en-us",
                                    "s" to "<http://example.org/s2>",
                                    "#p23050" to "<http://example.org/str>",
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
                                "#p23587",
                                "num"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/n4>",
                                    "#p23587" to "<http://example.org/num>",
                                    "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n5>",
                                    "#p23587" to "<http://example.org/num>",
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
                                "#p23720",
                                "num"
                            ), listOf(
                                mutableMapOf(
                                    "ceil" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/n1>",
                                    "#p23720" to "<http://example.org/num>",
                                    "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "ceil" to "\"-1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example.org/n2>",
                                    "#p23720" to "<http://example.org/num>",
                                    "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "ceil" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example.org/n3>",
                                    "#p23720" to "<http://example.org/num>",
                                    "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "ceil" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/n4>",
                                    "#p23720" to "<http://example.org/num>",
                                    "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "ceil" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example.org/n5>",
                                    "#p23720" to "<http://example.org/num>",
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
                                "#p23816",
                                "num"
                            ), listOf(
                                mutableMapOf(
                                    "floor" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/n1>",
                                    "#p23816" to "<http://example.org/num>",
                                    "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "floor" to "\"-2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example.org/n2>",
                                    "#p23816" to "<http://example.org/num>",
                                    "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "floor" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example.org/n3>",
                                    "#p23816" to "<http://example.org/num>",
                                    "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "floor" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/n4>",
                                    "#p23816" to "<http://example.org/num>",
                                    "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "floor" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example.org/n5>",
                                    "#p23816" to "<http://example.org/num>",
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
                                "#p23912",
                                "num"
                            ), listOf(
                                mutableMapOf(
                                    "round" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/n1>",
                                    "#p23912" to "<http://example.org/num>",
                                    "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "round" to "\"-2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example.org/n2>",
                                    "#p23912" to "<http://example.org/num>",
                                    "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "round" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example.org/n3>",
                                    "#p23912" to "<http://example.org/num>",
                                    "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "round" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/n4>",
                                    "#p23912" to "<http://example.org/num>",
                                    "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "round" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example.org/n5>",
                                    "#p23912" to "<http://example.org/num>",
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
                                "#s24014",
                                "#p24014",
                                "str1",
                                "#s24015",
                                "#p24015",
                                "str2"
                            ), listOf(
                                mutableMapOf(
                                    "str" to "\"abcDEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#s24014" to "<http://example.org/s6>",
                                    "#p24014" to "<http://example.org/str>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#s24015" to "<http://example.org/s7>",
                                    "#p24015" to "<http://example.org/str>",
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
                                "#s24042",
                                "#p24042",
                                "str1",
                                "#s24043",
                                "#p24043",
                                "str2"
                            ), listOf(
                                mutableMapOf(
                                    "str" to "\"abcDEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#s24042" to "<http://example.org/s6>",
                                    "#p24042" to "<http://example.org/str>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#s24043" to "<http://example.org/s7>",
                                    "#p24043" to "<http://example.org/str>",
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
                                "#p24129",
                                "str1",
                                "s2",
                                "#p24130",
                                "str2"
                            ), listOf(
                                mutableMapOf(
                                    "str" to "\"123123\"",
                                    "s1" to "<http://example.org/s1>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s1>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "str" to "\"日本語123\"",
                                    "s1" to "<http://example.org/s2>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s1>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "str" to "\"english123\"",
                                    "s1" to "<http://example.org/s3>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s1>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "str" to "\"français123\"",
                                    "s1" to "<http://example.org/s4>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s1>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "str" to "\"abc123\"",
                                    "s1" to "<http://example.org/s5>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s1>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "str" to "\"def123\"",
                                    "s1" to "<http://example.org/s6>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s1>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "str" to null,
                                    "s1" to "<http://example.org/s7>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s1>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"123\""
                                ),
                                mutableMapOf(
                                    "str" to "\"123日本語\"",
                                    "s1" to "<http://example.org/s1>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s2>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "str" to "\"日本語日本語\"@ja",
                                    "s1" to "<http://example.org/s2>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s2>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "str" to "\"english日本語\"",
                                    "s1" to "<http://example.org/s3>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s2>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "str" to "\"français日本語\"",
                                    "s1" to "<http://example.org/s4>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s2>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "str" to "\"abc日本語\"",
                                    "s1" to "<http://example.org/s5>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s2>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "str" to "\"def日本語\"",
                                    "s1" to "<http://example.org/s6>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s2>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "str" to null,
                                    "s1" to "<http://example.org/s7>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s2>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"日本語\"@ja"
                                ),
                                mutableMapOf(
                                    "str" to "\"123english\"",
                                    "s1" to "<http://example.org/s1>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s3>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "str" to "\"日本語english\"",
                                    "s1" to "<http://example.org/s2>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s3>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "str" to "\"englishenglish\"@en",
                                    "s1" to "<http://example.org/s3>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s3>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "str" to "\"françaisenglish\"",
                                    "s1" to "<http://example.org/s4>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s3>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "str" to "\"abcenglish\"",
                                    "s1" to "<http://example.org/s5>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s3>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "str" to "\"defenglish\"",
                                    "s1" to "<http://example.org/s6>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s3>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "str" to null,
                                    "s1" to "<http://example.org/s7>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s3>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"english\"@en"
                                ),
                                mutableMapOf(
                                    "str" to "\"123français\"",
                                    "s1" to "<http://example.org/s1>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s4>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "str" to "\"日本語français\"",
                                    "s1" to "<http://example.org/s2>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s4>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "str" to "\"englishfrançais\"",
                                    "s1" to "<http://example.org/s3>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s4>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "str" to "\"françaisfrançais\"@fr",
                                    "s1" to "<http://example.org/s4>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s4>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "str" to "\"abcfrançais\"",
                                    "s1" to "<http://example.org/s5>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s4>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "str" to "\"deffrançais\"",
                                    "s1" to "<http://example.org/s6>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s4>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "str" to null,
                                    "s1" to "<http://example.org/s7>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s4>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"français\"@fr"
                                ),
                                mutableMapOf(
                                    "str" to "\"123abc\"",
                                    "s1" to "<http://example.org/s1>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s5>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to "\"日本語abc\"",
                                    "s1" to "<http://example.org/s2>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s5>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to "\"englishabc\"",
                                    "s1" to "<http://example.org/s3>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s5>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to "\"françaisabc\"",
                                    "s1" to "<http://example.org/s4>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s5>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to "\"abcabc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s1" to "<http://example.org/s5>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s5>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to "\"defabc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s1" to "<http://example.org/s6>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s5>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to null,
                                    "s1" to "<http://example.org/s7>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s5>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to "\"123def\"",
                                    "s1" to "<http://example.org/s1>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s6>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to "\"日本語def\"",
                                    "s1" to "<http://example.org/s2>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s6>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to "\"englishdef\"",
                                    "s1" to "<http://example.org/s3>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s6>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to "\"françaisdef\"",
                                    "s1" to "<http://example.org/s4>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s6>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to "\"abcdef\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s1" to "<http://example.org/s5>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s6>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to "\"defdef\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s1" to "<http://example.org/s6>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s6>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to null,
                                    "s1" to "<http://example.org/s7>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s6>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "str" to null,
                                    "s1" to "<http://example.org/s1>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"123\"",
                                    "s2" to "<http://example.org/s7>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "str" to null,
                                    "s1" to "<http://example.org/s2>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"日本語\"@ja",
                                    "s2" to "<http://example.org/s7>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "str" to null,
                                    "s1" to "<http://example.org/s3>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"english\"@en",
                                    "s2" to "<http://example.org/s7>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "str" to null,
                                    "s1" to "<http://example.org/s4>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"français\"@fr",
                                    "s2" to "<http://example.org/s7>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "str" to null,
                                    "s1" to "<http://example.org/s5>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s7>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "str" to null,
                                    "s1" to "<http://example.org/s6>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s2" to "<http://example.org/s7>",
                                    "#p24130" to "<http://example.org/str>",
                                    "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "str" to null,
                                    "s1" to "<http://example.org/s7>",
                                    "#p24129" to "<http://example.org/str>",
                                    "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s2" to "<http://example.org/s7>",
                                    "#p24130" to "<http://example.org/str>",
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
                                "#p24975",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s1>",
                                    "#p24975" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s2>",
                                    "#p24975" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s3>",
                                    "#p24975" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s4>",
                                    "#p24975" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "len" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s5>",
                                    "#p24975" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s6>",
                                    "#p24975" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "len" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s7>",
                                    "#p24975" to "<http://example.org/str>",
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
                                "#p25084",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "ustr" to "\"FOO\"",
                                    "s" to "<http://example.org/s1>",
                                    "#p25084" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "ustr" to "\"BAR\"@en",
                                    "s" to "<http://example.org/s2>",
                                    "#p25084" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "ustr" to "\"BAZ\"",
                                    "s" to "<http://example.org/s3>",
                                    "#p25084" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "ustr" to "\"食べ物\"",
                                    "s" to "<http://example.org/s4>",
                                    "#p25084" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "ustr" to "\"100%\"",
                                    "s" to "<http://example.org/s5>",
                                    "#p25084" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "ustr" to "\"ABC\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s" to "<http://example.org/s6>",
                                    "#p25084" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "ustr" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s" to "<http://example.org/s7>",
                                    "#p25084" to "<http://example.org/str>",
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
                                "#p25193",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "lstr" to "\"foo\"",
                                    "s" to "<http://example.org/s1>",
                                    "#p25193" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "lstr" to "\"bar\"@en",
                                    "s" to "<http://example.org/s2>",
                                    "#p25193" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "lstr" to "\"baz\"",
                                    "s" to "<http://example.org/s3>",
                                    "#p25193" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "lstr" to "\"食べ物\"",
                                    "s" to "<http://example.org/s4>",
                                    "#p25193" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "lstr" to "\"100%\"",
                                    "s" to "<http://example.org/s5>",
                                    "#p25193" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "lstr" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s" to "<http://example.org/s6>",
                                    "#p25193" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "lstr" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "s" to "<http://example.org/s7>",
                                    "#p25193" to "<http://example.org/str>",
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
                                "#p25341",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p25341" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p25341" to "<http://example.org/str>",
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
                                "#p25412",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p25412" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p25412" to "<http://example.org/str>",
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
                                "#p26417",
                                "x",
                                "#p26418",
                                "y",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x6>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x4>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x7>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x8>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x5>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"a\"",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x1>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "<http://example/a>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x3>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "_:b",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x2>"
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
                                "#p26590",
                                "x",
                                "#p26591",
                                "y",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x6>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x4>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x7>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x8>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x5>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"a\"",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x1>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "<http://example/a>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x3>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "_:b",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x2>"
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
                                "#s26890",
                                "#p26890",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "hash" to "\"acbd18db4cc2f85cedef654fccc4a4d8\"",
                                    "#s26890" to "<http://example.org/s1>",
                                    "#p26890" to "<http://example.org/str>",
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
                                "#s26908",
                                "#p26908",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "hash" to "\"acbd18db4cc2f85cedef654fccc4a4d8\"",
                                    "#s26908" to "<http://example.org/s1>",
                                    "#p26908" to "<http://example.org/str>",
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
                                "#s26971",
                                "#p26971",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "hash" to "\"e7ada485d13b1decf628c9211bc3a97b\"",
                                    "#s26971" to "<http://example.org/s4>",
                                    "#p26971" to "<http://example.org/str>",
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
                                "#s26989",
                                "#p26989",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "hash" to "\"e7ada485d13b1decf628c9211bc3a97b\"",
                                    "#s26989" to "<http://example.org/s4>",
                                    "#p26989" to "<http://example.org/str>",
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
                                "#s27052",
                                "#p27052",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "hash" to "\"0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33\"",
                                    "#s27052" to "<http://example.org/s1>",
                                    "#p27052" to "<http://example.org/str>",
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
                                "#s27070",
                                "#p27070",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "hash" to "\"0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33\"",
                                    "#s27070" to "<http://example.org/s1>",
                                    "#p27070" to "<http://example.org/str>",
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
                                "#s27133",
                                "#p27133",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "hash" to "\"d46696735b6a09ff407bfc1a9407e008840db9c9\"",
                                    "#s27133" to "<http://example.org/s8>",
                                    "#p27133" to "<http://example.org/str>",
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
                                "#s27151",
                                "#p27151",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "hash" to "\"d46696735b6a09ff407bfc1a9407e008840db9c9\"",
                                    "#s27151" to "<http://example.org/s8>",
                                    "#p27151" to "<http://example.org/str>",
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
                                "#s27214",
                                "#p27214",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "hash" to "\"2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae\"",
                                    "#s27214" to "<http://example.org/s1>",
                                    "#p27214" to "<http://example.org/str>",
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
                                "#s27232",
                                "#p27232",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "hash" to "\"2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae\"",
                                    "#s27232" to "<http://example.org/s1>",
                                    "#p27232" to "<http://example.org/str>",
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
                                "#s27295",
                                "#p27295",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "hash" to "\"0fbe868d1df356ca9df7ebff346da3a56280e059a7ea81186ef885b140d254ee\"",
                                    "#s27295" to "<http://example.org/s8>",
                                    "#p27295" to "<http://example.org/str>",
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
                                "#s27313",
                                "#p27313",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "hash" to "\"0fbe868d1df356ca9df7ebff346da3a56280e059a7ea81186ef885b140d254ee\"",
                                    "#s27313" to "<http://example.org/s8>",
                                    "#p27313" to "<http://example.org/str>",
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
                                "#p27432",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"28\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d1>",
                                    "#p27432" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"38\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d2>",
                                    "#p27432" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"59\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d3>",
                                    "#p27432" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d4>",
                                    "#p27432" to "<http://example.org/date>",
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
                                "#p27474",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"28\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d1>",
                                    "#p27474" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"38\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d2>",
                                    "#p27474" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"59\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d3>",
                                    "#p27474" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d4>",
                                    "#p27474" to "<http://example.org/date>",
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
                                "#p27564",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example.org/d1>",
                                    "#p27564" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example.org/d2>",
                                    "#p27564" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"0.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example.org/d3>",
                                    "#p27564" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example.org/d4>",
                                    "#p27564" to "<http://example.org/date>",
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
                                "#p27649",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d1>",
                                    "#p27649" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"15\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d2>",
                                    "#p27649" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d3>",
                                    "#p27649" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d4>",
                                    "#p27649" to "<http://example.org/date>",
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
                                "#p27691",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d1>",
                                    "#p27691" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"15\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d2>",
                                    "#p27691" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d3>",
                                    "#p27691" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d4>",
                                    "#p27691" to "<http://example.org/date>",
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
                                "#p27781",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d1>",
                                    "#p27781" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d2>",
                                    "#p27781" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d3>",
                                    "#p27781" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d4>",
                                    "#p27781" to "<http://example.org/date>",
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
                                "#p27823",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d1>",
                                    "#p27823" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d2>",
                                    "#p27823" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d3>",
                                    "#p27823" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d4>",
                                    "#p27823" to "<http://example.org/date>",
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
                                "#p27913",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"2010\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d1>",
                                    "#p27913" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"2010\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d2>",
                                    "#p27913" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"2008\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d3>",
                                    "#p27913" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"2011\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d4>",
                                    "#p27913" to "<http://example.org/date>",
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
                                "#p27955",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"2010\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d1>",
                                    "#p27955" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"2010\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d2>",
                                    "#p27955" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"2008\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d3>",
                                    "#p27955" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"2011\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d4>",
                                    "#p27955" to "<http://example.org/date>",
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
                                "#p28045",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"21\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d1>",
                                    "#p28045" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"21\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d2>",
                                    "#p28045" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"20\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d3>",
                                    "#p28045" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d4>",
                                    "#p28045" to "<http://example.org/date>",
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
                                "#p28087",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"21\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d1>",
                                    "#p28087" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"21\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d2>",
                                    "#p28087" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"20\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d3>",
                                    "#p28087" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/d4>",
                                    "#p28087" to "<http://example.org/date>",
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
                                "#p28177",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>\"",
                                    "s" to "<http://example.org/d1>",
                                    "#p28177" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"\"-PT8H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>\"",
                                    "s" to "<http://example.org/d2>",
                                    "#p28177" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>\"",
                                    "s" to "<http://example.org/d3>",
                                    "#p28177" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"\"",
                                    "s" to "<http://example.org/d4>",
                                    "#p28177" to "<http://example.org/date>",
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
                                "#p28262",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"Z\"",
                                    "s" to "<http://example.org/d1>",
                                    "#p28262" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"-08:00\"",
                                    "s" to "<http://example.org/d2>",
                                    "#p28262" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"Z\"",
                                    "s" to "<http://example.org/d3>",
                                    "#p28262" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"\"",
                                    "s" to "<http://example.org/d4>",
                                    "#p28262" to "<http://example.org/date>",
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
                                "#p28304",
                                "date"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "\"Z\"",
                                    "s" to "<http://example.org/d1>",
                                    "#p28304" to "<http://example.org/date>",
                                    "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"-08:00\"",
                                    "s" to "<http://example.org/d2>",
                                    "#p28304" to "<http://example.org/date>",
                                    "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"Z\"",
                                    "s" to "<http://example.org/d3>",
                                    "#p28304" to "<http://example.org/date>",
                                    "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "x" to "\"\"",
                                    "s" to "<http://example.org/d4>",
                                    "#p28304" to "<http://example.org/date>",
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
                                "b2",
                                "a",
                                "#p28459",
                                "s1",
                                "b",
                                "#p28460",
                                "s2"
                            ), listOf(
                                mutableMapOf(
                                    "b1" to "_:28408\"foo\"",
                                    "b2" to "_:28414\"foo\"",
                                    "a" to "<http://example.org/s1>",
                                    "#p28459" to "<http://example.org/str>",
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s1>",
                                    "#p28460" to "<http://example.org/str>",
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "b1" to "_:28408\"BAZ\"",
                                    "b2" to "_:28414\"foo\"",
                                    "a" to "<http://example.org/s3>",
                                    "#p28459" to "<http://example.org/str>",
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s1>",
                                    "#p28460" to "<http://example.org/str>",
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "b1" to "_:28408\"foo\"",
                                    "b2" to "_:28414\"BAZ\"",
                                    "a" to "<http://example.org/s1>",
                                    "#p28459" to "<http://example.org/str>",
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s3>",
                                    "#p28460" to "<http://example.org/str>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "b1" to "_:28408\"BAZ\"",
                                    "b2" to "_:28414\"BAZ\"",
                                    "a" to "<http://example.org/s3>",
                                    "#p28459" to "<http://example.org/str>",
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s3>",
                                    "#p28460" to "<http://example.org/str>",
                                    "s2" to "\"BAZ\""
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
                                "b1" to "_:28408\"foo\"",
                                "b2" to "_:28414\"foo\""
                            ),
                            mutableMapOf(
                                "s1" to "\"BAZ\"",
                                "s2" to "\"foo\"",
                                "b1" to "_:28408\"BAZ\"",
                                "b2" to "_:28414\"foo\""
                            ),
                            mutableMapOf(
                                "s1" to "\"foo\"",
                                "s2" to "\"BAZ\"",
                                "b1" to "_:28408\"foo\"",
                                "b2" to "_:28414\"BAZ\""
                            ),
                            mutableMapOf(
                                "s1" to "\"BAZ\"",
                                "s2" to "\"BAZ\"",
                                "b1" to "_:28408\"BAZ\"",
                                "b2" to "_:28414\"BAZ\""
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
                                    "b1" to "_:3121431246",
                                    "b2" to "_:3121931244"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b1",
                            "b2"
                        ), listOf(
                            mutableMapOf(
                                "b1" to "_:3121431246",
                                "b2" to "_:3121931244"
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
                                    "b1" to "_:3125731270",
                                    "b2" to "_:3125031268"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b1",
                            "b2"
                        ), listOf(
                            mutableMapOf(
                                "b1" to "_:3125731270",
                                "b2" to "_:3125031268"
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
                                    "n" to "\"2020-02-24T17:22:52Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "n"
                        ), listOf(
                            mutableMapOf(
                                "n" to "\"2020-02-24T17:22:52Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
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
                                "#s33240",
                                "#p33240",
                                "#o33240"
                            ), listOf(
                                mutableMapOf(
                                    "#s33240" to "<http://example.org/s1>",
                                    "#p33240" to "<http://example.org/p1>",
                                    "#o33240" to "<http://example.org/s2>"
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
                                "#s33247",
                                "#p33247",
                                "#o33247"
                            ), listOf(
                                mutableMapOf(
                                    "#s33247" to "<http://example.org/s1>",
                                    "#p33247" to "<http://example.org/p1>",
                                    "#o33247" to "<http://example.org/s2>"
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
                                "#p34861",
                                "y",
                                "#p34862",
                                "z",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "eq" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "#p34861" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p34862" to "<http://www.example.org/schema#q>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>"
                                ),
                                mutableMapOf(
                                    "eq" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "#p34861" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p34862" to "<http://www.example.org/schema#q>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>"
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
                                "#p34900",
                                "y",
                                "#p34901",
                                "z",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "eq" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "#p34900" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p34901" to "<http://www.example.org/schema#q>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>"
                                ),
                                mutableMapOf(
                                    "eq" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "#p34900" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p34901" to "<http://www.example.org/schema#q>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>"
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
                                "#p35010",
                                "y",
                                "#p35011",
                                "z",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35010" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35011" to "<http://www.example.org/schema#q>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p35010" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35011" to "<http://www.example.org/schema#q>",
                                    "z" to "\"foobar\"",
                                    "x" to "<http://www.example.org/instance#a>"
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
                                "#p35038",
                                "y",
                                "#p35039",
                                "z",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35038" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35039" to "<http://www.example.org/schema#q>",
                                    "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p35038" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35039" to "<http://www.example.org/schema#q>",
                                    "z" to "\"foobar\"",
                                    "x" to "<http://www.example.org/instance#a>"
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
                                "sum",
                                "#p35150",
                                "y",
                                "#p35151",
                                "z",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "twice" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35150" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35151" to "<http://www.example.org/schema#q>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>"
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
                                "sum",
                                "#p35195",
                                "y",
                                "#p35196",
                                "z",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "twice" to "\"6\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35195" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35196" to "<http://www.example.org/schema#q>",
                                    "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>"
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
                                "#p35308",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35308" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35308" to "<http://www.example.org/schema#p>",
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
                                "#p35338",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35338" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35338" to "<http://www.example.org/schema#p>",
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
                                "#p35424",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "dt" to "<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35424" to "<http://www.example.org/schema#p>",
                                    "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "dt" to null,
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35424" to "<http://www.example.org/schema#p>",
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
                                "#p35449",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "dt" to "<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35449" to "<http://www.example.org/schema#p>",
                                    "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "dt" to null,
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35449" to "<http://www.example.org/schema#p>",
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
                                "m",
                                "x",
                                "#p35534",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "dt" to null,
                                    "m" to null,
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35534" to "<http://www.example.org/schema#p>",
                                    "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "m",
                                "x",
                                "#p35558",
                                "l"
                            ), listOf(
                                mutableMapOf(
                                    "dt" to null,
                                    "m" to null,
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35558" to "<http://www.example.org/schema#p>",
                                    "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "#p35654",
                                "y",
                                "#p35655",
                                "l",
                                "x"
                            ), listOf(
                                mutableMapOf(
                                    "dt" to "<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35654" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35655" to "<http://www.example.org/schema#q>",
                                    "l" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>"
                                ),
                                mutableMapOf(
                                    "dt" to null,
                                    "#p35654" to "<http://www.example.org/schema#p>",
                                    "y" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p35655" to null,
                                    "l" to null,
                                    "x" to "<http://www.example.org/instance#b>"
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
                                "#p37065",
                                "#o37065"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37065" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37065" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37065" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37065" to "<http://www.example.orgOrder>"
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
                                "#p37062",
                                "L",
                                "#p37063",
                                "#_36991",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#p37062" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37063" to "<http://www.example.orghasItem>",
                                    "#_36991" to "_:_36968",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37062" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37063" to "<http://www.example.orghasItem>",
                                    "#_36991" to "_:_36971",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37062" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "#p37063" to "<http://www.example.orghasItem>",
                                    "#_36991" to "_:_36972",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37062" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "#p37063" to "<http://www.example.orghasItem>",
                                    "#_36991" to "_:_36969",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37062" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "#p37063" to "<http://www.example.orghasItem>",
                                    "#_36991" to "_:_36973",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37062" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "#p37063" to "<http://www.example.orghasItem>",
                                    "#_36991" to "_:_36970",
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
                                "#p37086",
                                "#o37086"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37086" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37086" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37086" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37086" to "<http://www.example.orgOrder>"
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
                                "#p37083",
                                "L",
                                "#p37084",
                                "#_36991",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#p37083" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37084" to "<http://www.example.orghasItem>",
                                    "#_36991" to "_:_36968",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37083" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37084" to "<http://www.example.orghasItem>",
                                    "#_36991" to "_:_36971",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37083" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "#p37084" to "<http://www.example.orghasItem>",
                                    "#_36991" to "_:_36972",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37083" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "#p37084" to "<http://www.example.orghasItem>",
                                    "#_36991" to "_:_36969",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37083" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "#p37084" to "<http://www.example.orghasItem>",
                                    "#_36991" to "_:_36973",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37083" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "#p37084" to "<http://www.example.orghasItem>",
                                    "#_36991" to "_:_36970",
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
                                "#p37205",
                                "F",
                                "#p37206",
                                "L",
                                "P"
                            ), listOf(
                                mutableMapOf(
                                    "FullName" to "\"John Doe\"",
                                    "#p37205" to "<http://xmlns.com/foaf/0.1/firstName>",
                                    "F" to "\"John\"",
                                    "#p37206" to "<http://xmlns.com/foaf/0.1/lastName>",
                                    "L" to "\"Doe\"",
                                    "P" to "<http://p1>"
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
                                "p",
                                "P",
                                "s",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "p" to "<http://xmlns.com/foaf/0.1/name>",
                                    "P" to null,
                                    "s" to "<http://p1>",
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
                                "#p37260",
                                "F",
                                "#p37261",
                                "L",
                                "P"
                            ), listOf(
                                mutableMapOf(
                                    "FullName" to "\"John Doe\"",
                                    "#p37260" to "<http://xmlns.com/foaf/0.1/firstName>",
                                    "F" to "\"John\"",
                                    "#p37261" to "<http://xmlns.com/foaf/0.1/lastName>",
                                    "L" to "\"Doe\"",
                                    "P" to "<http://p1>"
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
                                "#p37430",
                                "#o37430"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37430" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37430" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37430" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37430" to "<http://www.example.orgOrder>"
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
                                "#p37427",
                                "L",
                                "#p37428",
                                "#_37356",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#p37427" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37428" to "<http://www.example.orghasItem>",
                                    "#_37356" to "_:_37333",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37427" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37428" to "<http://www.example.orghasItem>",
                                    "#_37356" to "_:_37336",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37427" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "#p37428" to "<http://www.example.orghasItem>",
                                    "#_37356" to "_:_37337",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37427" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "#p37428" to "<http://www.example.orghasItem>",
                                    "#_37356" to "_:_37334",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37427" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "#p37428" to "<http://www.example.orghasItem>",
                                    "#_37356" to "_:_37338",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37427" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "#p37428" to "<http://www.example.orghasItem>",
                                    "#_37356" to "_:_37335",
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
                                "#p37451",
                                "#o37451"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37451" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37451" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37451" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37451" to "<http://www.example.orgOrder>"
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
                                "#p37448",
                                "L",
                                "#p37449",
                                "#_37356",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#p37448" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37449" to "<http://www.example.orghasItem>",
                                    "#_37356" to "_:_37333",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37448" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37449" to "<http://www.example.orghasItem>",
                                    "#_37356" to "_:_37336",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37448" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "#p37449" to "<http://www.example.orghasItem>",
                                    "#_37356" to "_:_37337",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37448" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "#p37449" to "<http://www.example.orghasItem>",
                                    "#_37356" to "_:_37334",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37448" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "#p37449" to "<http://www.example.orghasItem>",
                                    "#_37356" to "_:_37338",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37448" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "#p37449" to "<http://www.example.orghasItem>",
                                    "#_37356" to "_:_37335",
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
                assertTrue(data.expected is Throwable)
            }
        }
    }
}
