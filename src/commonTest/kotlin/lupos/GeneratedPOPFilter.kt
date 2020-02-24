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


class GeneratedPOPFilterTest {
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
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPVariable("#f1162")),
                        POPValues(dictionary, listOf(
                                "C",
                                "#f1162",
                                "P"
                            ), listOf(
                                mutableMapOf(
                                    "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#f1162" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "P" to "<http://www.example.org/p1>"
                                ),
                                mutableMapOf(
                                    "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#f1162" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "P" to "<http://www.example.org/p2>"
                                )
                            )
                        )
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
                )
            }() /* resources/sparql11-test-suite/aggregates/agg03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPVariable("#f1745")),
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
                            "C",
                            "#f1745"
                        ), listOf(
                            mutableMapOf(
                                "C" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#f1745" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPVariable("#f2020")),
                        POPValues(dictionary, listOf(
                                "C",
                                "#f2020",
                                "P"
                            ), listOf(
                                mutableMapOf(
                                    "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#f2020" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "P" to "<http://www.example.org/p1>"
                                ),
                                mutableMapOf(
                                    "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#f2020" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "P" to "<http://www.example.org/p2>"
                                )
                            )
                        )
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
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPVariable("#f3807")),
                        POPValues(dictionary, listOf(
                                "avg",
                                "#f3807",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "avg" to "\"2.2333333333333334\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#f3807" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "s" to "<http://www.example.org/decimals>"
                                ),
                                mutableMapOf(
                                    "avg" to "\"10700.0\"^^<http://www.w3.org/2001/XMLSchema#double>",
                                    "#f3807" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "s" to "<http://www.example.org/doubles>"
                                ),
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
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPOr(AOPEQ(AOPVariable("sample"), AOPDecimal(3.5)), AOPOr(AOPEQ(AOPVariable("sample"), AOPDecimal(2.2)), AOPEQ(AOPVariable("sample"), AOPDecimal(1.0))))),
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
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPEQ(AOPVariable("z"), AOPInteger(3))),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPEQ(AOPVariable("v"), AOPVariable("z"))),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "#p7865",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "z" to null,
                                    "s" to "<http://example.org/s1>",
                                    "#p7865" to "<http://example.org/p>",
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z" to null,
                                    "s" to "<http://example.org/s2>",
                                    "#p7865" to "<http://example.org/p>",
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z" to null,
                                    "s" to "<http://example.org/s3>",
                                    "#p7865" to "<http://example.org/p>",
                                    "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z" to null,
                                    "s" to "<http://example.org/s4>",
                                    "#p7865" to "<http://example.org/p>",
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
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPEQ(AOPVariable("v"), AOPVariable("z"))),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "#p7934",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "z" to null,
                                    "s" to "<http://example.org/s1>",
                                    "#p7934" to "<http://example.org/p>",
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z" to null,
                                    "s" to "<http://example.org/s2>",
                                    "#p7934" to "<http://example.org/p>",
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z" to null,
                                    "s" to "<http://example.org/s3>",
                                    "#p7934" to "<http://example.org/p>",
                                    "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z" to null,
                                    "s" to "<http://example.org/s4>",
                                    "#p7934" to "<http://example.org/p>",
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
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPEQ(AOPVariable("v"), AOPVariable("z"))),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "#p8059",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s1>",
                                    "#p8059" to "<http://example.org/p>",
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s2>",
                                    "#p8059" to "<http://example.org/p>",
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s3>",
                                    "#p8059" to "<http://example.org/p>",
                                    "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPEQ(AOPVariable("v"), AOPVariable("z"))),
                        POPValues(dictionary, listOf(
                                "z",
                                "s",
                                "#p8123",
                                "v"
                            ), listOf(
                                mutableMapOf(
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s1>",
                                    "#p8123" to "<http://example.org/p>",
                                    "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s2>",
                                    "#p8123" to "<http://example.org/p>",
                                    "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example.org/s3>",
                                    "#p8123" to "<http://example.org/p>",
                                    "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPEQ(AOPVariable("z"), AOPInteger(3))),
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
                )
            }() /* resources/sparql11-test-suite/entailment/bind05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPEQ(AOPVariable("z"), AOPInteger(3))),
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
                )
            }() /* resources/sparql11-test-suite/entailment/bind05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPEQ(AOPVariable("z"), AOPInteger(3))),
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
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPEQ(AOPVariable("z"), AOPInteger(3))),
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
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p21745",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p21745" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p21745" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p21745" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p21745" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "#p21745" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p21745" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "#p21745" to "<http://example.org/str>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p21745",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p21745" to "<http://example.org/str>",
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p21878",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p21878" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p21878" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p21878" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p21878" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "#p21878" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p21878" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "#p21878" to "<http://example.org/str>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p21878",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p21878" to "<http://example.org/str>",
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p22072",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p22072" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p22072" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p22072" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p22072" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "#p22072" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p22072" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "#p22072" to "<http://example.org/str>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p22072",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p22072" to "<http://example.org/str>",
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p22213",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p22213" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p22213" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p22213" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p22213" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "#p22213" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p22213" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "#p22213" to "<http://example.org/str>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p22213",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p22213" to "<http://example.org/str>",
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p22585",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p22585" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p22585" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p22585" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p22585" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "#p22585" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p22585" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "#p22585" to "<http://example.org/str>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p22585",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p22585" to "<http://example.org/str>",
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p22718",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p22718" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p22718" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p22718" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p22718" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "#p22718" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p22718" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "#p22718" to "<http://example.org/str>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p22718",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p22718" to "<http://example.org/str>",
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p22909",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p22909" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p22909" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p22909" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p22909" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "#p22909" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p22909" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "#p22909" to "<http://example.org/str>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p22909",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p22909" to "<http://example.org/str>",
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p23050",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p23050" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p23050" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p23050" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p23050" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "#p23050" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p23050" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "#p23050" to "<http://example.org/str>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p23050",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p23050" to "<http://example.org/str>",
                                "str" to "\"bar\"@en"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallIsNUMERIC(AOPVariable("num"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "num"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "p" to "<http://example.org/date>",
                                    "num" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "p" to "<http://example.org/date>",
                                    "num" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "p" to "<http://example.org/date>",
                                    "num" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "p" to "<http://example.org/date>",
                                    "num" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
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
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/str>",
                                    "num" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/str>",
                                    "num" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/str>",
                                    "num" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/str>",
                                    "num" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/str>",
                                    "num" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/str>",
                                    "num" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "p" to "<http://example.org/str>",
                                    "num" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p23587",
                                "num"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/n1>",
                                    "#p23587" to "<http://example.org/num>",
                                    "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n2>",
                                    "#p23587" to "<http://example.org/num>",
                                    "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n3>",
                                    "#p23587" to "<http://example.org/num>",
                                    "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
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
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p25341",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p25341" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p25341" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p25341" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p25341" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "#p25341" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p25341" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "#p25341" to "<http://example.org/str>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
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
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "#p25412",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "#p25412" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "#p25412" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "#p25412" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "#p25412" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "#p25412" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "#p25412" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "#p25412" to "<http://example.org/str>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
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
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallSTRSTARTS(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "1"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "p" to "<http://example.org/date>",
                                    "str" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "p" to "<http://example.org/date>",
                                    "str" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "p" to "<http://example.org/date>",
                                    "str" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "p" to "<http://example.org/date>",
                                    "str" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n1>",
                                    "p" to "<http://example.org/num>",
                                    "str" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n2>",
                                    "p" to "<http://example.org/num>",
                                    "str" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n3>",
                                    "p" to "<http://example.org/num>",
                                    "str" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n4>",
                                    "p" to "<http://example.org/num>",
                                    "str" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n5>",
                                    "p" to "<http://example.org/num>",
                                    "str" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
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
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallSTRENDS(AOPVariable("str"), AOPSimpleLiteral("\"", "bc"))),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "str"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/d1>",
                                    "p" to "<http://example.org/date>",
                                    "str" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d2>",
                                    "p" to "<http://example.org/date>",
                                    "str" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d3>",
                                    "p" to "<http://example.org/date>",
                                    "str" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/d4>",
                                    "p" to "<http://example.org/date>",
                                    "str" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n1>",
                                    "p" to "<http://example.org/num>",
                                    "str" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n2>",
                                    "p" to "<http://example.org/num>",
                                    "str" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n3>",
                                    "p" to "<http://example.org/num>",
                                    "str" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n4>",
                                    "p" to "<http://example.org/num>",
                                    "str" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/n5>",
                                    "p" to "<http://example.org/num>",
                                    "str" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "p" to "<http://example.org/str>",
                                    "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
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
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")))),
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
                    ),
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
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")))),
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
                                )
                            )
                        )
                    ),
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
                                "a" to "<http://example.org/s3>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s1>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"foo\""
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
                                "a" to "<http://example.org/s3>",
                                "#p28459" to "<http://example.org/str>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s3>",
                                "#p28460" to "<http://example.org/str>",
                                "s2" to "\"BAZ\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPIn(AOPInteger(2), AOPSet(AOPInteger(1), AOPInteger(2), AOPInteger(3)))),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/functions/in01.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPIn(AOPInteger(2), AOPSet(AOPInteger(1), AOPInteger(3)))),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/functions/in02.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPNotIn(AOPInteger(2), AOPSet())),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/functions/notin01.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPNotIn(AOPInteger(2), AOPSet(AOPDivision(AOPInteger(0), AOPInteger(1)), AOPInteger(2)))),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                mutableMapOf(
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/functions/notin02.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPEQ(AOPBuildInCallDATATYPE(AOPVariable("n")), AOPIri("http://www.w3.org/2001/XMLSchema#dateTime"))),
                        POPValues(dictionary, listOf(
                                "n"
                            ), listOf(
                                mutableMapOf(
                                    "n" to "\"2020-02-24T17:01:06Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "n"
                        ), listOf(
                            mutableMapOf(
                                "n" to "\"2020-02-24T17:01:06Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/now01.rq */ ,
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
