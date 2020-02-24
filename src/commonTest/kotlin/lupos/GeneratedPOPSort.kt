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


class GeneratedPOPSortTest {
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
                        POPSort(
                                dictionary,
                                AOPVariable("O12"),
                                true,
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
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
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
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>"
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
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
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
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>"
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
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
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
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("o"),
                                true,
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
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p3>",
                                        "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "<http://example.org/s2>"
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("p"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
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
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p3>",
                                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>"
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("s"),
                                true,
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
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o2"),
                        true,
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
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
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
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
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
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
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
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
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
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
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
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
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
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
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
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
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
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("o2"),
                                true,
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
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "<http://example.org/s2>",
                                        "p2" to "<http://example.org/p2>",
                                        "o2" to "\"foo\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("p2"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
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
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"foo\""
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
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "<http://example.org/s2>",
                                        "p2" to "<http://example.org/p2>",
                                        "o2" to "\"foo\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("o"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
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
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"foo\""
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
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p3>",
                                        "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"foo\"",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "<http://example.org/s2>",
                                        "p2" to "<http://example.org/p2>",
                                        "o2" to "\"foo\""
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("p"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
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
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p3>",
                                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                                "p2" to null,
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"foo\"",
                                                "p2" to null,
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"foo\""
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("s"),
                                true,
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
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
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
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
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
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/p7>",
                                "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
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
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
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
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("o"),
                                true,
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
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p3>",
                                        "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s6>",
                                        "p" to "<http://example.org/p6>",
                                        "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
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
                        POPSort(
                                dictionary,
                                AOPVariable("p"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        mutableMapOf(
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p3>",
                                                "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s6>",
                                                "p" to "<http://example.org/p6>",
                                                "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
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
                        POPSort(
                                dictionary,
                                AOPVariable("s"),
                                true,
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
                        POPSort(
                                dictionary,
                                AOPVariable("sum"),
                                true,
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
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("y"),
                                true,
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
                                        "s" to "<http://example/x1>",
                                        "x" to "\"a\"",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
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
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("x"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "x",
                                        "y",
                                        "sum"
                                ), listOf(
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
                                                "s" to "<http://example/x1>",
                                                "x" to "\"a\"",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
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
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("sum"),
                                true,
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
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("y"),
                                true,
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
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
                                        "s" to "<http://example/x1>",
                                        "x" to "\"a\"",
                                        "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
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
                                        "s" to "<http://example/x8>",
                                        "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                        "sum" to null
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("x"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "x",
                                        "y",
                                        "sum"
                                ), listOf(
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
                                                "s" to "<http://example/x1>",
                                                "x" to "\"a\"",
                                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
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
                                                "s" to "<http://example/x8>",
                                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                                "sum" to null
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
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("o"),
                                true,
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
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "<http://example.org/s2>"
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
                        POPSort(
                                dictionary,
                                AOPVariable("p"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
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
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"foo\""
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>"
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
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"foo\""
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
                        POPSort(
                                dictionary,
                                AOPVariable("s"),
                                true,
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
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"foo\""
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
                        POPSort(
                                dictionary,
                                AOPVariable("o2"),
                                true,
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
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "<http://example.org/s2>",
                                        "p2" to "<http://example.org/p2>",
                                        "o2" to "\"foo\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("p2"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
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
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"foo\""
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
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "<http://example.org/s2>",
                                        "p2" to "<http://example.org/p2>",
                                        "o2" to "\"foo\""
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("o"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
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
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"foo\""
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
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"foo\"",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s1>",
                                        "p" to "<http://example.org/p1>",
                                        "o" to "<http://example.org/s2>",
                                        "p2" to "<http://example.org/p2>",
                                        "o2" to "\"foo\""
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
                        POPSort(
                                dictionary,
                                AOPVariable("p"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
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
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                                "p2" to null,
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"foo\"",
                                                "p2" to null,
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s1>",
                                                "p" to "<http://example.org/p1>",
                                                "o" to "<http://example.org/s2>",
                                                "p2" to "<http://example.org/p2>",
                                                "o2" to "\"foo\""
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
                                        "s" to "<http://example.org/s3>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                        "p2" to null,
                                        "o2" to null
                                ),
                                mutableMapOf(
                                        "s" to "<http://example.org/s2>",
                                        "p" to "<http://example.org/p2>",
                                        "o" to "\"foo\"",
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
                        POPSort(
                                dictionary,
                                AOPVariable("s"),
                                true,
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
                                                "s" to "<http://example.org/s3>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                                "p2" to null,
                                                "o2" to null
                                        ),
                                        mutableMapOf(
                                                "s" to "<http://example.org/s2>",
                                                "p" to "<http://example.org/p2>",
                                                "o" to "\"foo\"",
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
                        POPSort(
                                dictionary,
                                AOPVariable("sum"),
                                true,
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
                        POPSort(
                                dictionary,
                                AOPVariable("O"),
                                true,
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder3>"
                                        ),
                                        mutableMapOf(
                                                "O" to "<http://www.example.orgorder4>"
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
                                ),
                                mutableMapOf(
                                        "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                        "O" to "<http://www.example.orgorder4>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("L"),
                                true,
                                POPValues(dictionary, listOf(
                                        "#_34876",
                                        "L",
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "#_34876" to "_:_34853",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34854",
                                                "L" to "\"Pizza\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34855",
                                                "L" to "\"Wine\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34856",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34857",
                                                "L" to "\"Pasta\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_34876" to "_:_34858",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "#_34876",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_34876" to "_:_34853",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34856",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34857",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34854",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34858",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_34876" to "_:_34855",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
                                )
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        POPSort(
                                dictionary,
                                AOPVariable("L"),
                                true,
                                POPValues(dictionary, listOf(
                                        "#_35225",
                                        "L",
                                        "O"
                                ), listOf(
                                        mutableMapOf(
                                                "#_35225" to "_:_35202",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35203",
                                                "L" to "\"Pizza\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35204",
                                                "L" to "\"Wine\"",
                                                "O" to "<http://www.example.orgorder1>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35205",
                                                "L" to "\"Ice Cream\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35206",
                                                "L" to "\"Pasta\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        ),
                                        mutableMapOf(
                                                "#_35225" to "_:_35207",
                                                "L" to "\"Soft Drink\"",
                                                "O" to "<http://www.example.orgorder2>"
                                        )
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "#_35225",
                                "L",
                                "O"
                        ), listOf(
                                mutableMapOf(
                                        "#_35225" to "_:_35202",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35205",
                                        "L" to "\"Ice Cream\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35206",
                                        "L" to "\"Pasta\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35203",
                                        "L" to "\"Pizza\"",
                                        "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35207",
                                        "L" to "\"Soft Drink\"",
                                        "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                        "#_35225" to "_:_35204",
                                        "L" to "\"Wine\"",
                                        "O" to "<http://www.example.orgorder1>"
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
