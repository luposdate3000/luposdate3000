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


class GeneratedPOPFilterTest {
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
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPVariable("#f1162")),
                        POPValues(dictionary, listOf(
                                "P",
                                "#f1162",
                                "C"
                            ), listOf(
                                mutableMapOf(
                                    "P" to "<http://www.example.org/p1>",
                                    "#f1162" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "P" to "<http://www.example.org/p2>",
                                    "#f1162" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
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
                )
            }() /* resources/sparql11-test-suite/aggregates/agg03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPVariable("#f1745")),
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
                            "#f1745",
                            "C"
                        ), listOf(
                            mutableMapOf(
                                "#f1745" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                "C" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "P",
                                "#f2020",
                                "C"
                            ), listOf(
                                mutableMapOf(
                                    "P" to "<http://www.example.org/p1>",
                                    "#f2020" to "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "P" to "<http://www.example.org/p2>",
                                    "#f2020" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
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
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPVariable("#f3633")),
                        POPValues(dictionary, listOf(
                                "s",
                                "#f3633",
                                "avg"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://www.example.org/decimals>",
                                    "#f3633" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "avg" to "\"2.2333333333333334\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://www.example.org/doubles>",
                                    "#f3633" to "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>",
                                    "avg" to "\"10700.0\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
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
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPEQ(AOPVariable("v"), AOPVariable("z"))),
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
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "z"
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
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en"))),
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
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
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
                    POPFilter(
                        dictionary,
                        POPExpression(dictionary, AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a"))),
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
                                    "a" to "<http://example.org/s2>",
                                    "s1" to "\"bar\"@en",
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
                                    "a" to "<http://example.org/s4>",
                                    "s1" to "\"食べ物\"",
                                    "b" to "<http://example.org/s1>",
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s5>",
                                    "s1" to "\"100%\"",
                                    "b" to "<http://example.org/s1>",
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s6>",
                                    "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s1>",
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s7>",
                                    "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s1>",
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s1>",
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s2>",
                                    "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s2>",
                                    "s1" to "\"bar\"@en",
                                    "b" to "<http://example.org/s2>",
                                    "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s3>",
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s2>",
                                    "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s4>",
                                    "s1" to "\"食べ物\"",
                                    "b" to "<http://example.org/s2>",
                                    "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s5>",
                                    "s1" to "\"100%\"",
                                    "b" to "<http://example.org/s2>",
                                    "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s6>",
                                    "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s2>",
                                    "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s7>",
                                    "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s2>",
                                    "s2" to "\"bar\"@en"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s1>",
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s2>",
                                    "s1" to "\"bar\"@en",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s3>",
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s4>",
                                    "s1" to "\"食べ物\"",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s5>",
                                    "s1" to "\"100%\"",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s6>",
                                    "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s7>",
                                    "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s1>",
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s4>",
                                    "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s2>",
                                    "s1" to "\"bar\"@en",
                                    "b" to "<http://example.org/s4>",
                                    "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s3>",
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s4>",
                                    "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s4>",
                                    "s1" to "\"食べ物\"",
                                    "b" to "<http://example.org/s4>",
                                    "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s5>",
                                    "s1" to "\"100%\"",
                                    "b" to "<http://example.org/s4>",
                                    "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s6>",
                                    "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s4>",
                                    "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s7>",
                                    "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s4>",
                                    "s2" to "\"食べ物\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s1>",
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s5>",
                                    "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s2>",
                                    "s1" to "\"bar\"@en",
                                    "b" to "<http://example.org/s5>",
                                    "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s3>",
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s5>",
                                    "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s4>",
                                    "s1" to "\"食べ物\"",
                                    "b" to "<http://example.org/s5>",
                                    "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s5>",
                                    "s1" to "\"100%\"",
                                    "b" to "<http://example.org/s5>",
                                    "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s6>",
                                    "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s5>",
                                    "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s7>",
                                    "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s5>",
                                    "s2" to "\"100%\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s1>",
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s6>",
                                    "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s2>",
                                    "s1" to "\"bar\"@en",
                                    "b" to "<http://example.org/s6>",
                                    "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s3>",
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s6>",
                                    "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s4>",
                                    "s1" to "\"食べ物\"",
                                    "b" to "<http://example.org/s6>",
                                    "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s5>",
                                    "s1" to "\"100%\"",
                                    "b" to "<http://example.org/s6>",
                                    "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s6>",
                                    "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s6>",
                                    "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s7>",
                                    "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s6>",
                                    "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s1>",
                                    "s1" to "\"foo\"",
                                    "b" to "<http://example.org/s7>",
                                    "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s2>",
                                    "s1" to "\"bar\"@en",
                                    "b" to "<http://example.org/s7>",
                                    "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s3>",
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s7>",
                                    "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s4>",
                                    "s1" to "\"食べ物\"",
                                    "b" to "<http://example.org/s7>",
                                    "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s5>",
                                    "s1" to "\"100%\"",
                                    "b" to "<http://example.org/s7>",
                                    "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s6>",
                                    "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s7>",
                                    "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s7>",
                                    "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s7>",
                                    "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                )
                            )
                        )
                    ),
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
                                "a" to "<http://example.org/s2>",
                                "s1" to "\"bar\"@en",
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
                                "a" to "<http://example.org/s4>",
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s1>",
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s1>",
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s1>",
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
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
                                "a" to "<http://example.org/s2>",
                                "s1" to "\"bar\"@en",
                                "b" to "<http://example.org/s3>",
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "s1" to "\"BAZ\"",
                                "b" to "<http://example.org/s3>",
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "s1" to "\"食べ物\"",
                                "b" to "<http://example.org/s3>",
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "s1" to "\"100%\"",
                                "b" to "<http://example.org/s3>",
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s3>",
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "b" to "<http://example.org/s3>",
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
                                    "a" to "<http://example.org/s2>",
                                    "s1" to "\"bar\"@en",
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
                                    "a" to "<http://example.org/s4>",
                                    "s1" to "\"食べ物\"",
                                    "b" to "<http://example.org/s1>",
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s5>",
                                    "s1" to "\"100%\"",
                                    "b" to "<http://example.org/s1>",
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s6>",
                                    "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s1>",
                                    "s2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s7>",
                                    "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
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
                                    "a" to "<http://example.org/s2>",
                                    "s1" to "\"bar\"@en",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s3>",
                                    "s1" to "\"BAZ\"",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s4>",
                                    "s1" to "\"食べ物\"",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s5>",
                                    "s1" to "\"100%\"",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s6>",
                                    "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "b" to "<http://example.org/s3>",
                                    "s2" to "\"BAZ\""
                                ),
                                mutableMapOf(
                                    "a" to "<http://example.org/s7>",
                                    "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>",
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
                                    "n" to "\"2020-02-24T21:01:42Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "n"
                        ), listOf(
                            mutableMapOf(
                                "n" to "\"2020-02-24T21:01:42Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
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
                e.printStackTrace()
                assertTrue(data.expected is Throwable)
            }
        }
    }
}
